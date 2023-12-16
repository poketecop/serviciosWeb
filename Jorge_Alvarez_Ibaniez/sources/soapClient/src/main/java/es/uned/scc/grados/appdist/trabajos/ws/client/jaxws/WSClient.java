package es.uned.scc.grados.appdist.trabajos.ws.client.jaxws;

import es.uned.scc.grados.appdist.trabajos.ws.client.jaxws.generated.SignalGeneratorWS;
import es.uned.scc.grados.appdist.trabajos.ws.client.jaxws.generated.SignalGeneratorWSImplService;
import es.uned.scc.grados.appdist.trabajos.ws.client.jaxws.gui.PlottingFrame;

public class WSClient {

	public static void main(String[] args) throws Exception {
        SignalGeneratorWS service = new SignalGeneratorWSImplService().getSignalGeneratorWSImplPort();

        PlottingFrame gui = new PlottingFrame(service);
        gui.show();
    }
	
}
