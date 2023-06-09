package hotciv.broker.main;

import com.google.gson.Gson;
import frds.broker.IPCException;
import hotciv.broker.ClientRequestHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClientRequestHandler implements ClientRequestHandler {

    private String hostname;
    private int port;

    public SocketClientRequestHandler() {
        Gson gson = new Gson();
    }

    @Override
    public String sendToServerAndAwaitReply(String request) {
        Socket clientSocket = null;

        // Create the socket connection to the host
        PrintWriter out;
        BufferedReader in;
        try {
            clientSocket = new Socket(hostname, port);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(
                    clientSocket.getInputStream()));
        } catch (IOException e ) {
            throw new IPCException("Socket creation problems", e);
        }

        // Send it to the server (= write it to the socket stream)
        out.println(request);

        // Block until a reply is received
        String reply;
        try {
            reply = in.readLine();

        } catch (IOException e) {
            throw new IPCException("Socket read problems", e);
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                throw new IPCException("Socket close problems (1)", e);
            }
        }
        // ... and close the connection
        try {
            clientSocket.close();
        } catch (IOException e) {
            throw new IPCException("Socket close problems (2)", e);
        }

        return reply;
    }

    @Override
    public void setServer(String hostname, int port) {
        setServer(hostname, port, false);

    }


    @Override
    public void setServer(String hostname, int port, boolean useTLS) {
        if (useTLS) {
            throw new RuntimeException("TLS is not implemented for the SocketClientRequestHandler."
                    + "If you need secure communication, use the URITunnel variant instead.");
        }
        this.hostname = hostname;
        this.port = port;

    }

    public SocketClientRequestHandler(String hostname, int port) {
        this();
        setServer(hostname, port);
    }

    @Override
    public void close() {

    }

    @Override
    public String toString() {
        return getClass().getCanonicalName() +
                ", " + hostname + ':' + port;
    }

}
