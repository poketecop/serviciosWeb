package es.uned.scc.grados.appdist.trabajos.ws.server;

import es.uned.scc.grados.appdist.trabajos.ws.SignalGeneratorWS;
import es.uned.scc.grados.appdist.trabajos.ws.SignalGeneratorWSImpl;
import jakarta.xml.ws.Endpoint;

public class WSServer {
	
	protected WSServer() throws Exception {
        System.out.println("Starting Server");
        SignalGeneratorWSImpl implementor = new SignalGeneratorWSImpl();
        SignalGeneratorWS service = (SignalGeneratorWS) implementor;
        String address = "http://localhost:9000/SignalGeneratorWS";
        Endpoint.publish(address, service);
    }

    public static void main(String args[]) throws Exception {
        new WSServer();
        System.out.println("Server ready...");

        Thread.sleep(5 * 60 * 1000);
        System.out.println("Server exiting");
        System.exit(0);
    }
}
