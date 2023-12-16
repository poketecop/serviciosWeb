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

    @Override
    public boolean start() {
        this.gui.getFrame().setVisible(true);

        return true;
    }
    @Override
    public boolean stop() {
        this.gui.getFrame().setVisible(false);

        return true;
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
