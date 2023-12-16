package es.uned.scc.grados.appdist.trabajos.ws.client.rest.gui;

import es.uned.scc.grados.appdist.trabajos.plot.ClientGUI;
import es.uned.scc.grados.appdist.trabajos.plot.ClientPlot;
import es.uned.scc.grados.appdist.trabajos.signal.model.data.SignalData;
import es.uned.scc.grados.appdist.trabajos.signal.model.data.SignalParameters;
import es.uned.scc.grados.appdist.trabajos.ws.client.rest.WSClient;

public class PlottingFrame implements ClientPlot {

    WSClient client;
    ClientGUI gui;

    public PlottingFrame(WSClient client) {
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
        return this.client.get();
    }

    @Override
    public SignalParameters getSignalParameters() {
        return this.client.getParams();
    }

    @Override
    public void setSignalParameters(SignalParameters sp) {
        this.client.setParams(sp);
    }
    
}
