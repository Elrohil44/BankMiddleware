import bank.*;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main (String[] args) throws TTransportException {
        String host = "localhost";

        TProtocol protocol;
        TTransport transport;

        transport = new TSocket(host, 9090);
        protocol = new TBinaryProtocol(transport, true, true);

        AccountManagement.Client accountManagement = new AccountManagement
                .Client(new TMultiplexedProtocol(protocol, "AccountManagement"));
        AccountService.Client accountService = new AccountService
                .Client(new TMultiplexedProtocol(protocol, "AccountService"));
        PremiumAccountService.Client premiumAccountService = new PremiumAccountService
                .Client(new TMultiplexedProtocol(protocol, "PremiumAccountService"));

        transport.open();


        try {
            System.out.println(accountManagement.createAccount(new Account(
                    "12321411111",
                    "Jan",
                    "Kowalski",
                    50000.0,
                    "PLN"
            )));

            System.out.println(premiumAccountService.getAccountDetails("0"));

            System.out.println(premiumAccountService.getLoanDetails("0",
                    new LoanParameters(
                            "USD",
                            1000.0,
                            "2018-06-01",
                            "2019-06-01"
                    )
            ));

        } catch (InvalidArgumentException e) {
            System.out.println("InvalidArgumentException: " + e.why);
        } catch (AuthorizationException e) {
            System.out.println("AuthorizationException: " + e.why);
        } catch (TException e) {
            e.printStackTrace();
        }


    }
}
