package es.uned.scc.grados.appdist.trabajos.signal.model.data;

import java.io.Serializable;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "OperationInfo")
public class OperationInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4041754425246038792L;
	private boolean ok = false;
	private String message = "";
	
	public boolean isOk() {
		return ok;
	}
	
	public void setOk(boolean ok) {
		this.ok = ok;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}