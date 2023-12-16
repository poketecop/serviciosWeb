package es.uned.scc.grados.appdist.trabajos.plot;

import es.uned.scc.grados.appdist.trabajos.signal.model.data.SignalData;
import es.uned.scc.grados.appdist.trabajos.signal.model.data.SignalParameters;


public interface ClientPlot {
	boolean start();
	boolean stop();
	SignalData getSignalValue();
	public SignalParameters getSignalParameters();
	public void setSignalParameters(SignalParameters sp);
}
