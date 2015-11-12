package consultaonibus;

import consultaonibus.reader.Reader;

import java.util.ArrayList;

public class ParadaLinha {

	private String idLinha;
	private String idParada;

	public ParadaLinha(String idLinha, String idParada) {
		this.idLinha  = idLinha;
		this.idParada = idParada;
	}

	public String getIdLinha() {
		return idLinha;
	}

	public void setIdLinha(String idLinha) {
		this.idLinha = idLinha;
	}

	public String getIdParada() {
		return idParada;
	}

	public void setIdParada(String idParada) {
		this.idParada = idParada;
	}

}