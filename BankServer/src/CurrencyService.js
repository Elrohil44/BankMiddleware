import grpc from 'grpc';
import { createInterface } from 'readline';


const PROTO_PATH = __dirname + '/../../protos/currencies.proto';

const rl = createInterface({
  input: process.stdin,
  output: process.stdout
});


const currencies_proto = grpc.load(PROTO_PATH).currencies;
const currencyRates = {};
const currenciesSupported = [];

function init(callback) {
  const currencies = new currencies_proto.Currencies('localhost:50051', grpc.credentials.createInsecure());
  const call = currencies.currencyRates();

  call.on('data', (data) => {
    const msg = data[data.msg];
    if (msg.entries) {
      msg.entries.forEach(entry => {
        console.log(entry);
        currencyRates[entry.currencyName] = entry.currencyRate;
      });

      if (!currenciesSupported.length) {
        rl.question('What currencies should be supported? (Comma separated list, eg. "USD, EUR")\t', (currencies) => {
          currencies
            .split(',')
            .map(currency => currency.trim().toUpperCase())
            .forEach(currencyName => {
              call.write({ currencyName });
              currenciesSupported.push(currencyName);
            });
          callback();
          rl.close();
        });
      }
    } else {
      console.log(msg);
      currencyRates[msg.currencyName] = msg.currencyRate;
    }
  });
}

function start(callback) {
  init(callback)
}

export default {
  start,
  currencyRates,
  currenciesSupported,
}
