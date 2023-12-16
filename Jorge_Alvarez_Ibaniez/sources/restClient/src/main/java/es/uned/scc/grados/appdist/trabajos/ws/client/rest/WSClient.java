package es.uned.scc.grados.appdist.trabajos.ws.client.rest;
import es.uned.scc.grados.appdist.trabajos.signal.model.data.OperationInfo;
import es.uned.scc.grados.appdist.trabajos.signal.model.data.SignalData;
import es.uned.scc.grados.appdist.trabajos.signal.model.data.SignalParameters;
import es.uned.scc.grados.appdist.trabajos.ws.client.rest.gui.PlottingFrame;
import jakarta.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;

public class WSClient {

    private static final String REST_URI = "http://localhost:9002/SignalGenerator";

    private static final String START_PATH = "/start";
    private static final String STOP_PATH = "/stop";
    private static final String GET_PARAMS_PATH = "/getParams";
    private static final String SET_PARAMS_PATH = "/setParams";
    private static final String ISRUNNING_PATH = "/isrunning";
    private static final String GET_PATH = "/get";

    WebClient client;

    protected WSClient() {
        this.client = WebClient.create(REST_URI);
    }

	public static void main(String[] args) throws Exception {
        WSClient client = new WSClient();

        PlottingFrame gui = new PlottingFrame(client);
        gui.show();
    }
	
    public OperationInfo start(){
        // Set relative path to REST method
        this.client.replacePath(START_PATH);
        // Set type of data received
        this.client.type("application/xml");
        // Call the REST method
        Response r = this.client.get();

        this.client.reset();

        // Get the XML entity in response and cast to class
        OperationInfo i = r.readEntity(OperationInfo.class);
        System.out.println("Response: " + i.getMessage());
        return i;
    }

    public OperationInfo stop(){
        // Set relative path to REST method
        this.client.replacePath(STOP_PATH);
        // Set type of data received
        this.client.type("application/xml");
        // Call the REST method
        Response r = this.client.get();

        this.client.reset();

        // Get the XML entity in response and cast to class
        OperationInfo i = r.readEntity(OperationInfo.class);
        System.out.println("Response: " + i.getMessage());
        return i;
    }

    public OperationInfo isRunning(){
        // Set relative path to REST method
        this.client.replacePath(ISRUNNING_PATH);
        // Set type of data received
        this.client.type("application/xml");
        // Call the REST method
        Response r = this.client.get();

        this.client.reset();

        // Get the XML entity in response and cast to class
        OperationInfo i = r.readEntity(OperationInfo.class);
        System.out.println("Response: " + i.getMessage());
        return i;
    }

    public SignalData get(){
        // Set relative path to REST method
        this.client.replacePath(GET_PATH);
        // Set type of data received
        this.client.type("application/xml");
        // Call the REST method
        Response r = this.client.get();

        this.client.reset();

        // Get the XML entity in response and cast to class
        SignalData i = r.readEntity(SignalData.class);
        System.out.println("Response: " + i);
        return i;
    }

    public SignalParameters getParams(){
        // Set relative path to REST method
        this.client.replacePath(GET_PARAMS_PATH);
        // Set type of data received
        this.client.type("application/xml");
        // Call the REST method
        Response r = this.client.get();

        this.client.reset();

        System.out.println("Raw response: " + r);
        // Get the XML entity in response and cast to class
        SignalParameters i = r.readEntity(SignalParameters.class);
        System.out.println("Response: " + i);
        return i;
    }

    public void setParams(SignalParameters sp){
        // Set relative path to REST method
        this.client.replacePath(SET_PARAMS_PATH);
        // Transform the object to XML
        this.client.accept("application/xml");
        System.out.println("Sending: " + sp);
        // Call the REST method
        this.client.post(sp);

        this.client.reset();

        System.out.println("Parameters set");
    }
    
}
