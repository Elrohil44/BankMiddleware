import thrift from 'thrift';
import { Processor as AccountManagement } from '../gen-nodejs/AccountManagement';
import { Processor as AccountService } from '../gen-nodejs/AccountService';
import { Processor as PremiumAccountService } from '../gen-nodejs/PremiumAccountService';
import { LoanCosts } from '../gen-nodejs/bank_types';
import CurrencyService from './CurrencyService';

CurrencyService.start(startBankService);


const accounts = {};

function createAccount(account, result) {
  result(null, "1");
}

function getLoanDetails(guid, loanParameters, result) {
  result(new LoanCosts());
}

function getAccountDetails(guid, result) {
  result(accounts[guid]);
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

async function startBankService() {
  const multiplexedProcessor = new thrift.MultiplexedProcessor();
  multiplexedProcessor.registerProcessor('AccountManagement', accountManagement);
  multiplexedProcessor.registerProcessor('AccountService', accountService);
  multiplexedProcessor.registerProcessor('PremiumAccountService', premiumAccountService);
  const server = thrift.createMultiplexServer(multiplexedProcessor);
  server.listen(9090);
  console.log('Bank service started');
}