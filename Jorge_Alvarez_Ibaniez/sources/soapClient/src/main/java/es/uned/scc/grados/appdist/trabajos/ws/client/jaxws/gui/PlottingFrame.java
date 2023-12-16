package es.uned.scc.grados.appdist.trabajos.ws.client.jaxws.gui;

import es.uned.scc.grados.appdist.trabajos.plot.ClientGUI;
import es.uned.scc.grados.appdist.trabajos.plot.ClientPlot;
import es.uned.scc.grados.appdist.trabajos.ws.client.jaxws.generated.SignalData;
import es.uned.scc.grados.appdist.trabajos.ws.client.jaxws.generated.SignalGeneratorWS;
import es.uned.scc.grados.appdist.trabajos.ws.client.jaxws.generated.SignalParameters;

public class PlottingFrame implements ClientPlot {

    SignalGeneratorWS client;
    ClientGUI gui;

    public PlottingFrame(SignalGeneratorWS client) {
        this.client = client;
        this.gui = new ClientGUI(this);
    }

    public void show() {
        this.gui.getFrame().setVisible(true);
    }

    @Override
    public boolean start() {
        return this.client.start().isOk();
    }

    @Override
    public boolean stop() {
        return this.client.stop().isOk();
    }
    
    @Override
    public SignalData getSignalValue() {
        return this.client.getSignalValue();
    }

    @Override
    public SignalParameters getSignalParameters() {
        return this.client.getSignalParameters();
    }

    @Override
    public void setSignalParameters(SignalParameters sp) {
        this.client.setSignalParameters(sp);
    }
    
}
