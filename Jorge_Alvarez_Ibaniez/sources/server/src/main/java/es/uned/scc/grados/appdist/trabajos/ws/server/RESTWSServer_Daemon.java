package es.uned.scc.grados.appdist.trabajos.ws.server;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;

import es.uned.scc.grados.appdist.trabajos.ws.RESTSignalGeneratorWSImpl;

public class RESTWSServer_Daemon {

    public static void main(String args[]) throws Exception {
        // Create the JAX-RS Server with CXF
        JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
        // Set REST implementor class
        sf.setResourceClasses(RESTSignalGeneratorWSImpl.class);
        // Create absolute Path
        sf.setAddress("http://localhost:9002/");
        // Start JAX-RS Server
        Server restServer = sf.create();

        System.out.println("Server ready...");

        // Thread.sleep 2 horas en formato entendible
        Thread.sleep(2 * 60 * 60 * 1000);
        System.out.println("Server exiting");
        restServer.stop();
        System.exit(0);
    }

}