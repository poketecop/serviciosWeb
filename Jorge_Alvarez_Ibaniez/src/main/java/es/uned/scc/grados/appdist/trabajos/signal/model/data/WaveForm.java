package es.uned.scc.grados.appdist.trabajos.signal.model.data;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "WaveForm")
public enum WaveForm {
	SINE, SQUARE, TRIANGLE
}
