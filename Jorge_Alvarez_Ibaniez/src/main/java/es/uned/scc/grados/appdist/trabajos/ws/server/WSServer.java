package es.uned.scc.grados.appdist.trabajos.ws.server;

import javax.xml.ws.Endpoint;

public class WSServer {
	
	protected WSServer() throws Exception {
        System.out.println("Starting Server");
        Object implementor = new es.uned.scc.grados.appdist.trabajos.ws.SignalGeneratorWSImpl();
        String address = "http://localhost:9090/SignalGeneratorWSImplPort";
        Endpoint.publish(address, implementor);
    }

    public static void main(String args[]) throws Exception {
        new WSServer();
        System.out.println("Server ready...");

        Thread.sleep(5 * 60 * 1000);
        System.out.println("Server exiting");
        System.exit(0);
    }
}
