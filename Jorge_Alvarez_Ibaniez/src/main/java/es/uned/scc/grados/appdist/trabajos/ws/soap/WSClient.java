package es.uned.scc.grados.appdist.trabajos.ws.soap;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;

public class WSClient {

	public static void main(String[] args) throws Exception {
        QName serviceName = new QName("http://ws.trabajos.appdist.grados.scc.uned.es/", "SignalGeneratorWS");
        QName portName = new QName("http://ws.trabajos.appdist.grados.scc.uned.es/", "SignalGeneratorWSImplPort");

        Service service = Service.create(serviceName);
        service.addPort(portName, SOAPBinding.SOAP11HTTP_BINDING,
                        "http://localhost:9090/SignalGeneratorWSImplPort");
        es.uned.scc.grados.appdist.trabajos.ws.SignalGeneratorWS client = service.getPort(portName,  es.uned.scc.grados.appdist.trabajos.ws.SignalGeneratorWS.class);

        // Insert code to invoke methods on the client here
    }
	
}