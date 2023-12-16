package es.uned.scc.grados.appdist.trabajos.signal.model.data;

import java.io.Serializable;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "SignalParameters")
public class SignalParameters implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -878007721471507505L;
	private WaveForm type;
	private double amplitude = 1.0;
	private double frequency = 1.0;
	
	public SignalParameters(){
		this.type = WaveForm.SINE;
		this.amplitude = 1.0;
		this.frequency = 1.0;
	}
	
	public SignalParameters(WaveForm type,
			double amplitude,
			double frequency){
		this.type = type;
		this.amplitude = amplitude;
		this.frequency = frequency;
	}
	
	@XmlElement
	public WaveForm getType() {
		return type;
	}
	public void setType(WaveForm type) {
		this.type = type;
	}

	@XmlElement
	public double getAmplitude() {
		return amplitude;
	}
	public void setAmplitude(double amplitude) {
		this.amplitude = amplitude;
	}

	@XmlElement
	public double getFrequency() {
		return frequency;
	}
	public void setFrequency(double frequency) {
		this.frequency = frequency;
	}
	

}
