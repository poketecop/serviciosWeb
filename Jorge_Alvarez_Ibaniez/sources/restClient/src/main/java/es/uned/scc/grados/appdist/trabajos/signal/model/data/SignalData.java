package es.uned.scc.grados.appdist.trabajos.signal.model.data;

import java.io.Serializable;
import java.text.DecimalFormat;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "SignalData")
public class SignalData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8562146843030989942L;
	private double time = 0.0;
	private double output = 0.0;
	
	public SignalData(){
		this.time = 0.0;
		this.output = 0.0;
	}
	
	public SignalData(double time, double output){
		this.time = time;
		this.output = output;
	}
	
	public double getTime() {
		return time;
	}
	public void setTime(double time) {
		this.time = time;
	}
	public double getOutput() {
		return output;
	}
	public void setOutput(double output) {
		this.output = output;
	}
	
	public String toString(){
		DecimalFormat df = new DecimalFormat("#.###");
		String msg = "Time: " + df.format(this.time);
		msg += " Output: " + df.format(this.output);
		return msg;
	}
}
