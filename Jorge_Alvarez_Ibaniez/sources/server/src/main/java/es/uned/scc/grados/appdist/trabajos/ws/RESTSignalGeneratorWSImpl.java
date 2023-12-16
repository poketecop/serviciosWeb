package es.uned.scc.grados.appdist.trabajos.ws;

import es.uned.scc.grados.appdist.trabajos.signal.model.SignalGenerator;
import es.uned.scc.grados.appdist.trabajos.signal.model.SignalGeneratorThread;
import es.uned.scc.grados.appdist.trabajos.signal.model.data.OperationInfo;
import es.uned.scc.grados.appdist.trabajos.signal.model.data.SignalData;
import es.uned.scc.grados.appdist.trabajos.signal.model.data.SignalParameters;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("SignalGenerator")
public class RESTSignalGeneratorWSImpl implements RESTSignalGenerator {

    private static SignalGeneratorThread signalGeneratorThread = new SignalGeneratorThread(0.01);

    @Path("start")
    @GET
    @Produces({"text/xml"})
    @Override
    public OperationInfo start() {
        return signalGeneratorThread.start();
    }

    @Path("stop")
    @GET
    @Produces({"text/xml"})
    @Override
    public OperationInfo stop() {
        return signalGeneratorThread.stop();
    }

    @Path("isrunning")
    @GET
    @Produces({"text/xml"})
    @Override
    public OperationInfo isRunning() {
        return signalGeneratorThread.isThreadRunning();
    }

    @Path("get")
    @GET
    @Produces({"text/xml"})
    @Override
    public SignalData getSignalValue() {
        return new SignalData(signalGeneratorThread.getSignalgenerator().getTime(), 
								signalGeneratorThread.getSignalgenerator().getOutput());
    }

    @Path("getParams")
    @GET
    @Produces({"text/xml"})
    @Override
    public SignalParameters getSignalParameters() {
        SignalParameters params = new SignalParameters();
        SignalGenerator sg = signalGeneratorThread.getSignalgenerator();
        params.setAmplitude(sg.getAmplitude());
        params.setFrequency(sg.getFrequency());
        params.setType(sg.getType());
        return params;
    }

    @Path("setParams")
    @POST
    @Override
    public void setSignalParameters(SignalParameters signal_parameters) {
        SignalGenerator sg = signalGeneratorThread.getSignalgenerator();
        sg.setAmplitude(signal_parameters.getAmplitude());
        sg.setFrequency(signal_parameters.getFrequency());
        sg.setSignalType(signal_parameters.getType());
    }
    
}
