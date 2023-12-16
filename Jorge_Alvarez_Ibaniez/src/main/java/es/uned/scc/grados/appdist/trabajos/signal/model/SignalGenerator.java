package es.uned.scc.grados.appdist.trabajos.signal.model;

import java.text.DecimalFormat;

import es.uned.scc.grados.appdist.trabajos.signal.model.data.*;

/**
 * @author rpastor
 * 
 */
public class SignalGenerator {


	private WaveForm type = WaveForm.SINE;

	private double amplitude = 1.0;
	private double frequency = 1.0;
	private double sample_time = 0.0;

	private double initial_amplitude = 1.0;
	private double initial_frequency = 1.0;
	private WaveForm initial_type = WaveForm.SINE;

	// VAlues SINE, SQUARE y TRIANGLE
	private SignalFunction actualFunct = null;
	private double output = 0.0;
	private double time = 0.0;

	public SignalGenerator(double sampleTime){
		initData();
		this.sample_time = sampleTime;
	}
	
	SignalFunction fsine = new SignalFunction() {

		@Override
		public double f(double f, double t, double ampl) {
			return ampl * Math.sin(2.0 * Math.PI * f * t);
		}
	};

	SignalFunction ftriangle = new SignalFunction() {

		@Override
		public double f(double f, double t, double ampl) {
			double q = 4.0 * (t * f % 1.0);
			q = (q > 1.0) ? 2 - q : q;
			double out = (q < -1) ? -2 - q : q;
			out = ampl * out;
			return out;
		}
	};

	SignalFunction fsquare = new SignalFunction() {

		@Override
		public double f(double f, double t, double ampl) {
			if (f == 0) {
				return 0;
			}
			double q = 0.5 - t * f % 1.0;
			double out = (q > 0) ? 1 : -1;
			out = ampl * out;
			return out;
		}
	};

	private void initData() {
		// Default values

		this.amplitude = this.initial_amplitude;
		this.frequency = this.initial_frequency; // Hz
		if (initial_type == WaveForm.SQUARE) {
			type = WaveForm.SQUARE;
			this.actualFunct = fsquare;
		} else if (initial_type == WaveForm.TRIANGLE) {
			type = WaveForm.TRIANGLE;
			this.actualFunct = ftriangle;
		} else {
			// Default sine
			type = WaveForm.SINE;
			this.actualFunct = fsine;
		}
	}

	public synchronized void updateSignal() {
		// Calculate output
		this.output = actualFunct.f(this.frequency, this.time, this.amplitude);
	}
	
	public synchronized void incrementTime(){
		// Increment time
		this.time = time + this.sample_time;
	}

	public synchronized void reset(){
		this.time = 0;
		this.output = 0;
	}
	
	public double getFrequency(){
		return this.frequency;
	}
	
	public double getAmplitude(){
		return this.amplitude;
	}
	
	public WaveForm getType() {
		return this.type;
	}

	public void setFrequency(double value) {
		this.frequency = value;
	}

	public void setAmplitude(double value) {
		this.amplitude = value;
	}

	public void setSignalType(WaveForm type) {
		if (type == WaveForm.SINE) {
			this.type = WaveForm.SINE;
			this.actualFunct = this.fsine;
		} else if (type == WaveForm.SQUARE) {
			this.type = WaveForm.SQUARE;
			this.actualFunct = this.fsquare;
		} else if (type == WaveForm.TRIANGLE) {
			this.type = WaveForm.TRIANGLE;
			this.actualFunct = this.ftriangle;
		}
	}

	public double getOutput() {
		return output;
	}

	public double getTime() {
		return this.time;
	}
	
	public double getSampleTime(){
		return this.sample_time;
	}
	
	public void setSimpleTime(double value){
		this.sample_time = value;
	}
	
	public String toString(){
		DecimalFormat df = new DecimalFormat("#.###");
		String msg = "Time: " + df.format(this.time);
		msg += " Output: " + df.format(this.output);
		msg += " || SG Values: " + this.type;
		msg += " , Amplitude: " + df.format(this.amplitude);
		msg += " , Frequency: " + df.format(this.frequency);
		return msg;
	}
}

interface SignalFunction {
	public double f(double frequency, double time, double amplitude);
};
