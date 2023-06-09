package hotciv.broker.main;


import hotciv.broker.Invoker;
import hotciv.broker.RootInvoker;
import hotciv.framework.Game;
import hotciv.standard.GameImpl;
import hotciv.variants.alphaCiv.AlphaGameFactory;

public class HotCivServer {

        private static Thread daemon;

        public static void main(String[] args) throws Exception {
            int port = 37321;

            Game tsServant = new GameImpl(new AlphaGameFactory());
            Invoker invoker = new RootInvoker(tsServant);

            // Configure a socket based server request handler
            SocketServerRequestHandler ssrh =
                    new SocketServerRequestHandler();
            ssrh.setPortAndInvoker(port,  invoker);

            // Welcome message
            System.out.println("=== HotCiv Socket based Server Request Handler (port:"
                    + port + ") ===");
            System.out.println(" Use ctrl-c to terminate!");
            ssrh.start();
    }



}
