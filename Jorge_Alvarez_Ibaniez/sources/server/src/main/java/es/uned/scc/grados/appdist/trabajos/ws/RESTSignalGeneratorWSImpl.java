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

    private SignalGeneratorThread signalGeneratorThread;

    public RESTSignalGeneratorWSImpl() {
        this.signalGeneratorThread = new SignalGeneratorThread(0.01); // sampleTime is 0.01 seconds
    }

    @Path("start")
    @GET
    @Produces({"text/xml"})
    @Override
    public OperationInfo start() {
        return this.signalGeneratorThread.start();
    }

    @Path("stop")
    @GET
    @Produces({"text/xml"})
    @Override
    public OperationInfo stop() {
        return this.signalGeneratorThread.stop();
    }

    @Path("isrunning")
    @GET
    @Produces({"text/xml"})
    @Override
    public OperationInfo isRunning() {
        return this.signalGeneratorThread.isThreadRunning();
    }

    @Path("get")
    @GET
    @Produces({"text/xml"})
    @Override
    public SignalData getSignalValue() {
        return new SignalData(this.signalGeneratorThread.getSignalgenerator().getTime(), 
								this.signalGeneratorThread.getSignalgenerator().getOutput());
    }

    @Path("getParams")
    @GET
    @Produces({"text/xml"})
    @Override
    public SignalParameters getSignalParameters() {
        SignalParameters params = new SignalParameters();
        SignalGenerator sg = this.signalGeneratorThread.getSignalgenerator();
        params.setAmplitude(sg.getAmplitude());
        params.setFrequency(sg.getFrequency());
        params.setType(sg.getType());
        return params;
    }

    @Path("setParams")
    @POST
    @Produces({"text/xml"})
    @Override
    public void setSignalParameters(SignalParameters signal_parameters) {
        SignalGenerator sg = this.signalGeneratorThread.getSignalgenerator();
        sg.setAmplitude(signal_parameters.getAmplitude());
        sg.setFrequency(signal_parameters.getFrequency());
        sg.setSignalType(signal_parameters.getType());
    }
    
}
