package es.uned.scc.grados.appdist.trabajos.ws;

import es.uned.scc.grados.appdist.trabajos.signal.model.data.OperationInfo;
import es.uned.scc.grados.appdist.trabajos.signal.model.data.SignalData;
import es.uned.scc.grados.appdist.trabajos.signal.model.data.SignalParameters;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

@WebService(serviceName = "SignalGeneratorWS", targetNamespace = "http://ws.sample.org/")
public interface SignalGeneratorWS {

    public OperationInfo start();

    public OperationInfo stop();

    public OperationInfo isRunning();

    public SignalData getSignalValue();

    public SignalParameters getSignalParameters();

    public void setSignalParameters(@WebParam(name = "signal_parameters") SignalParameters signal_parameters);

}
