package es.uned.scc.grados.appdist.trabajos.ws;

import javax.jws.WebService;

import es.uned.scc.grados.appdist.trabajos.signal.model.data.OperationInfo;
import es.uned.scc.grados.appdist.trabajos.signal.model.data.SignalData;
import es.uned.scc.grados.appdist.trabajos.signal.model.data.SignalParameters;

@WebService(endpointInterface = "es.uned.scc.grados.appdist.trabajos.ws.SignalGeneratorWS",
serviceName = "SignalGeneratorWS")
public class SignalGeneratorWSImpl implements SignalGeneratorWS {

	@Override
	public OperationInfo start() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OperationInfo stop() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OperationInfo isRunning() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SignalData getSignalValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SignalParameters getSignalParameters() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSignalParameters(SignalParameters signal_parameters) {
		// TODO Auto-generated method stub

	}

}
