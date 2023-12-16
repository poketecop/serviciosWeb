package es.uned.scc.grados.appdist.trabajos.ws;

import javax.jws.WebService;

import es.uned.scc.grados.appdist.trabajos.signal.model.SignalGenerator;
import es.uned.scc.grados.appdist.trabajos.signal.model.SignalGeneratorThread;
import es.uned.scc.grados.appdist.trabajos.signal.model.data.OperationInfo;
import es.uned.scc.grados.appdist.trabajos.signal.model.data.SignalData;
import es.uned.scc.grados.appdist.trabajos.signal.model.data.SignalParameters;

@WebService(endpointInterface = "es.uned.scc.grados.appdist.trabajos.ws.SignalGeneratorWS", serviceName = "SignalGenerator")
public class SignalGeneratorWSImpl implements SignalGeneratorWS {
    private SignalGeneratorThread signalGeneratorThread;

    public SignalGeneratorWSImpl() {
        this.signalGeneratorThread = new SignalGeneratorThread(0.01); // sampleTime is 0.01 seconds
    }

    @Override
    public OperationInfo start() {
        return this.signalGeneratorThread.start();
    }

    @Override
    public OperationInfo stop() {
        return this.signalGeneratorThread.stop();
    }

    @Override
    public OperationInfo isRunning() {
        return this.signalGeneratorThread.isThreadRunning();
    }

    @Override
    public SignalData getSignalValue() {
        return new SignalData(this.signalGeneratorThread.getSignalgenerator().getTime(), 
								this.signalGeneratorThread.getSignalgenerator().getOutput());
    }

    @Override
    public SignalParameters getSignalParameters() {
        SignalParameters params = new SignalParameters();
        SignalGenerator sg = this.signalGeneratorThread.getSignalgenerator();
        params.setAmplitude(sg.getAmplitude());
        params.setFrequency(sg.getFrequency());
        params.setType(sg.getType());
        return params;
    }

    @Override
    public void setSignalParameters(SignalParameters signal_parameters) {
        SignalGenerator sg = this.signalGeneratorThread.getSignalgenerator();
        sg.setAmplitude(signal_parameters.getAmplitude());
        sg.setFrequency(signal_parameters.getFrequency());
        sg.setSignalType(signal_parameters.getType());
    }
}
