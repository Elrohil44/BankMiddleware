import grpc from 'grpc';

const PROTO_PATH = __dirname + '/../../protos/currencies.proto';

const currencies_proto = grpc.load(PROTO_PATH).currencies;
let id = 0;

const currencies = {
  'USD': 3.5,
  'EUR': 4.0,
  'CHF': 4.0,
  'GBP': 5.0,
};

const subscribers = {};
Object.keys(currencies).forEach(curr => {
  subscribers[curr] = [];
});

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
  setInterval(() => {
    Object.entries(currencies).forEach(([currency, rate]) => {
      if (Math.random() > 0.5) {
        currencies[currency] = Math.abs(rate + Math.random() - 0.5);
        notifySubscribers(currency);
      }
    })
  }, 5000)
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
  const server = new grpc.Server();
  server.addService(currencies_proto.Currencies.service, { currencyRates });
  server.bind('0.0.0.0:50051', grpc.ServerCredentials.createInsecure());
  server.start();
  simulateCurrenciesRates();
}

main();

setInterval(() => {
  Object.entries(subscribers).forEach(([currency, currSubscribers]) =>
    console.log(currency, currSubscribers.length));
  console.log('-------------------------')
}, 5000);