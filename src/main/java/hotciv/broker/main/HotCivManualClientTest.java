package hotciv.broker.main;
import hotciv.framework.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import frds.broker.ipc.socket.SocketClientRequestHandler;

public class HotCivManualClientTest {
    public static void main(String[] args) throws Exception{
        new HotCivManualClientTest(Player.RED.toString());

    }

    public HotCivManualClientTest(String hostname){
        SocketClientRequestHandler crh = new SocketClientRequestHandler();
        crh.setServer(hostname, 37321);
        System.out.println("hej");

    }



}
