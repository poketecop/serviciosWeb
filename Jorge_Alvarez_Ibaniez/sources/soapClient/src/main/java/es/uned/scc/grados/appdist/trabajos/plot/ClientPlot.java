package es.uned.scc.grados.appdist.trabajos.plot;

import es.uned.scc.grados.appdist.trabajos.ws.client.jaxws.generated.SignalData;
import es.uned.scc.grados.appdist.trabajos.ws.client.jaxws.generated.SignalParameters;

public interface ClientPlot {
	boolean start();
	boolean stop();
	SignalData getSignalValue();
	public SignalParameters getSignalParameters();
	public void setSignalParameters(SignalParameters sp);
}
