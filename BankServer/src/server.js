import thrift from 'thrift';
import moment from 'moment';

import { Processor as AccountManagement } from '../gen-nodejs/AccountManagement';
import { Processor as AccountService } from '../gen-nodejs/AccountService';
import { Processor as PremiumAccountService } from '../gen-nodejs/PremiumAccountService';
import {
  Account,
  LoanCosts,
  AccountDetails,
  AuthorizationException,
  InvalidArgumentException,
  LoanParameters,
} from '../gen-nodejs/bank_types';
import CurrencyService from './CurrencyService';

const PESEL_checker = new RegExp('[0-9]{11}');

const settings = {};
const defaultSettings = {
  PREMIUM_THRESHOLD: 10000,
  COMMISION: 0.001,
  CREDIT_RATE: 0.05,
};

let guid = 0;

const accounts = {};
const clients = {};


function authenticate(guid) {
  if (!guid && guid !== '0') {
    console.log(parameters.guid);
    throw new InvalidArgumentException({ why: 'Missing guid' })
  }

  if (!accounts[guid]) {
    throw new AuthorizationException({ why: 'Authorization failure, wrong guid' });
  }
}

function createAccount(account) {
  const accountObject = new Account(account);

  if (!PESEL_checker.test(accountObject.pesel)) {
    throw new InvalidArgumentException({why: 'Wrong PESEL format'});
  }

  if (!accountObject.firstname) {
    throw new InvalidArgumentException({why: 'Missing firstname'});
  }

  if (!accountObject.lastname) {
    throw new InvalidArgumentException({why: 'Missing lastname'});
  }

  if (!accountObject.incomeThreshold || accountObject.incomeThreshold < 0) {
    throw new InvalidArgumentException({why: 'Invalid incomeThreshold value'});
  }

  if (!accountObject.nativeCurrency) {
    throw new InvalidArgumentException({why: 'Missing nativeCurrency'});
  }

  if (CurrencyService.currencyRates[accountObject.nativeCurrency.toUpperCase()] === 'undefined') {
    throw new InvalidArgumentException({why: 'Currency not supported'});
  }

  const premiumThreshold = settings.PREMIUM_THRESHOLD || defaultSettings.PREMIUM_THRESHOLD;
  const isPremium =
    accountObject.incomeThreshold * CurrencyService.currencyRates[accountObject.nativeCurrency] > premiumThreshold;
  const account_guid = String(guid++);

  clients[accountObject.pesel] = accountObject;

  accounts[account_guid] = new AccountDetails({
    balance: 0,
    currency: accountObject.nativeCurrency,
    isPremium,
    guid: account_guid,
  });

  return accounts[account_guid];
}

function getLoanDetails(guid, loanParameters) {
  const parameters = new LoanParameters(loanParameters);

  authenticate(guid);

  if (!accounts[guid].isPremium) {
    throw new InvalidArgumentException({ why: 'Account is not a premium account' })
  }

  if (!parameters.startDate) {
    throw new InvalidArgumentException({ why: 'Missing startDate' })
  }

  if (!parameters.closeDate) {
    throw new InvalidArgumentException({ why: 'Missing closeDate' })
  }

  if (!parameters.currency) {
    throw new InvalidArgumentException({ why: 'Missing currency' })
  }

  if (!parameters.moneyAmount || parameters.moneyAmount < 0) {
    throw new InvalidArgumentException({ why: 'Missing moneyAmount or invalid value' })
  }

  if (!moment(parameters.startDate).isValid()) {
    throw new InvalidArgumentException({ why: 'Invalid startDate format' })
  }

  if (!moment(parameters.closeDate).isValid()) {
    throw new InvalidArgumentException({ why: 'Invalid closeDate format' })
  }

  if (moment(parameters.closeDate).isBefore(parameters.startDate)) {
    throw new InvalidArgumentException({ why: 'Close date should be after the startDate' })
  }

  if (moment(parameters.startDate).isBefore(moment())) {
    throw new InvalidArgumentException({ why: 'startDate should be in future' })
  }

  if (!CurrencyService.currencyRates[parameters.currency]) {
    throw new InvalidArgumentException({ why: 'Unsupported currency' })
  }

  const account = accounts[guid];
  const loanCosts = new LoanCosts();
  const commisionCosts = parameters.moneyAmount * (settings.COMMISION || defaultSettings.COMMISION);
  const duration = Math.round(moment.duration(moment(parameters.closeDate) - moment(parameters.startDate)).asDays());
  const rates = parameters.moneyAmount * (settings.CREDIT_RATE || defaultSettings.CREDIT_RATE) * duration / 365;

  loanCosts.nativeCurrencyCost = parameters.moneyAmount + rates + commisionCosts;
  loanCosts.requestedCurrencyCost = loanCosts.nativeCurrencyCost /
    CurrencyService.currencyRates[parameters.currency] * CurrencyService.currencyRates[account.currency];

  return loanCosts;
}

function getAccountDetails(guid) {
  authenticate(guid);

  return accounts[guid];
}

const accountManagement = new AccountManagement({
  createAccount,
});

const premiumAccountService = new PremiumAccountService({
  getLoanDetails,
  getAccountDetails,
});

const accountService = new AccountService({
  getAccountDetails,
});

async function startBankService(port) {
  const multiplexedProcessor = new thrift.MultiplexedProcessor();
  multiplexedProcessor.registerProcessor('AccountManagement', accountManagement);
  multiplexedProcessor.registerProcessor('AccountService', accountService);
  multiplexedProcessor.registerProcessor('PremiumAccountService', premiumAccountService);
  const server = thrift.createMultiplexServer(multiplexedProcessor);
  console.log(`Starting server on port ${port}`);
  server.listen(port);
  console.log('Bank service started');
}

function main(port) {
  CurrencyService.start(() => startBankService(port));
}

const port = parseInt(process.argv[2]) || 9090;

main(port);