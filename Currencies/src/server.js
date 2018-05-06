import grpc from 'grpc';
import vorpal from 'vorpal';

const PROTO_PATH = __dirname + '/../../protos/currencies.proto';

const currencies_proto = grpc.load(PROTO_PATH).currencies;
let id = 0;

const settings = {};
const defaultSettings = {
  currencyChangeInterval: 5000,
  port: 50051,
};

const currencies = {'PLN': 1};
const subscribers = {};
const userInputHandler = vorpal();

userInputHandler
  .command('addCurrency <currency> <initValue>')
  .description('Adds supported currency with initial value expressed in PLN')
  .validate((args) => {
    if (args.currency === 'PLN') return 'PLN in constant equal to 1';
    return (typeof(parseFloat(args.initValue)) === 'number') || 'initValue should be valid float number'
  })
  .action((args, callback) => {
    currencies[args.currency.toUpperCase()] = parseFloat(args.initValue);
    callback();
  });

userInputHandler
  .command('setCurrencyChangeInterval <interval>')
  .description('Sets currency change interval expressed in seconds')
  .validate((args) => {
    return (typeof(parseFloat(args.interval)) === 'number') || 'interval should be valid float number'
  })
  .action((args, callback) => {
    settings.currencyChangeInterval = parseFloat(args.interval) * 1000;
    callback();
  });

userInputHandler
  .command('start')
  .description('Starts server')
  .action(main);

userInputHandler
  .delimiter('currencyServer$')
  .show();

function getEntries() {
  return Object
    .entries(currencies)
    .map(([currencyName, currencyRate]) => ({ currencyRate, currencyName }));
}

function notifySubscribers(currency) {
  console.log(`Notifying subscribers about ${currency} rate changed`);
  subscribers[currency].forEach(subscriber => {
    try {
      subscriber.write({currencyRate: {currencyRate: currencies[currency], currencyName: currency}})
    } catch(_) {
      console.log('Error while sending message to bank. Removing it from subscribers');
      removeSubscriber(subscriber);
    }
  })
}

function removeSubscriber(subscriber) {
  Object.keys(subscribers).forEach((key) => {
      subscribers[key] = subscribers[key].filter(sub => sub !== subscriber);
    }
  );
}

function simulateCurrenciesRates() {
  const interval = settings.currencyChangeInterval || defaultSettings.currencyChangeInterval;
  console.log(`Initiating currency changing with interval ${interval} ms`);
  setInterval(() => {
    Object.entries(currencies).forEach(([currency, rate]) => {
      if (currency === 'PLN') return;
      if (Math.random() > 0.5) {
        currencies[currency] = Math.abs(rate + Math.random() - 0.5);
        notifySubscribers(currency);
      }
    })
  }, interval)
}

function currencyRates(call) {
  const bankId = id++;

  console.log(`Bank ${bankId} connected`);

  call.write({ currenciesRates: { entries: getEntries() } });

  call.on('data', (msg) => {
    if (msg.currencyName && currencies[msg.currencyName]) {
      if (subscribers[msg.currencyName].indexOf(call) === -1){
        console.log(`Bank ${bankId} subscribed ${msg.currencyName}`);
        subscribers[msg.currencyName].push(call);
      }
    } else {
      call.write({ currenciesRates: { entries: getEntries() } })
    }
  });

  call.on('end', () => {
    console.log(`Bank ${bankId} disconnected`);
    removeSubscriber(call);
  });
}


function main() {
  const port = settings.port || defaultSettings.port;
  console.log(`Starting server on port ${port}`);

  Object.keys(currencies).forEach(curr => {
    subscribers[curr] = [];
  });

  const server = new grpc.Server();
  server.addService(currencies_proto.Currencies.service, { currencyRates });
  server.bind(`0.0.0.0:${port}`, grpc.ServerCredentials.createInsecure());
  server.start();
  simulateCurrenciesRates();
}
