package es.uned.scc.grados.appdist.trabajos.signal.model;

import es.uned.scc.grados.appdist.trabajos.signal.model.data.OperationInfo;

public class SignalGeneratorThread implements Runnable {

	private SignalGenerator sg = null;
	private volatile boolean running = false;
	private long sampleTime = 10; // In miliseconds
	Thread thread = null;

	public SignalGeneratorThread(double sampleTime) {
		long time = Math.round(sampleTime*1000);
		this.sampleTime = time;
		this.sg = new SignalGenerator(sampleTime);
	}

	@Override
	public void run() {
		while (running) {
			sg.updateSignal();
			sg.incrementTime();
			try {
				Thread.sleep(sampleTime);
			} catch (InterruptedException e) {
				running = false;
			}
		}
	}

	public SignalGenerator getSignalgenerator() {
		return this.sg;
	}

	public OperationInfo start() {
		OperationInfo info = new OperationInfo();
		if (thread != null) {
			info.setMessage("Signal generator is already running");
			info.setOk(false);
		} else {
			info.setOk(true);
		}
		if (info.isOk()){
			// Reset values
			sg.reset();
			this.running = true;			
			thread = new Thread(this, "Signal Generator Thread instance");
			thread.start();
			info.setMessage("Signal generator started correctly");
			info.setOk(true);
		}
		return info;
	}

	public OperationInfo stop() {
		OperationInfo info = new OperationInfo();
		if (thread != null) {
			this.running = false;
			// Wait for thread finish
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			info.setMessage("Signal generator stopped correctly");
			info.setOk(true);
			thread = null;
		} else {
			info.setMessage("Signal generator is already stopped");
			info.setOk(false);
		}
		return info;
	}
	
	private synchronized boolean isRunning(){
		return this.running;
	}
	
	public OperationInfo isThreadRunning(){
		OperationInfo info = new OperationInfo();
		if (isRunning()){
			info.setMessage("Signal Generator thread is running");
			info.setOk(true);
		} else {
			info.setMessage("Signal Generator thread is not running");
			info.setOk(false);
		}
		return info;
	}

}
