import bank.Account;
import bank.AccountManagement;
import bank.AccountService;
import bank.PremiumAccountService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

public class Main {
    public static void main (String[] args) throws TException {
        String host = "localhost";

        TProtocol protocol = null;
        TSocket socket = null;
        TTransport transport = null;

        transport = new TSocket(host, 9090);
        protocol = new TBinaryProtocol(transport, true, true);

        AccountManagement.Client accountManagement = new AccountManagement
                .Client(new TMultiplexedProtocol(protocol, "AccountManagement"));
        AccountService.Client accountService = new AccountService
                .Client(new TMultiplexedProtocol(protocol, "AccountService"));
        PremiumAccountService.Client premiumAccountService = new PremiumAccountService
                .Client(new TMultiplexedProtocol(protocol, "PremiumAccountService"));

        transport.open();

        System.out.println(accountManagement.createAccount(new Account(
                "123214",
                "Jan",
                "Kowalski",
                5000.0,
                "USD"
        )));


    }
}
