package br.com.datasalles.Bean;


import java.io.Serializable;
import javax.faces.bean.ManagedBean;




@SuppressWarnings("serial")
@ManagedBean
public class OrientacaoBean implements Serializable {
	private String orientation = "horizontal";

	public String getOrientation() {
		return orientation;
	}

	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}


}