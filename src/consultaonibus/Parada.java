package consultaonibus;

public class Parada {
	
	private String id;
	private String codigo;
	private String longitude;
	private String latitude;
	private String terminal;
	
	public Parada(String id, String codigo, String longitude, String latitude, String terminal) {
		this.id = id;
		this.codigo = codigo;
		this.longitude = longitude;
		this.latitude = latitude;
		this.terminal = terminal;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	@Override
	public String toString() {
		return "Parada [id=" + id + ", codigo=" + codigo + ", longitude="
				+ longitude + ", latitude=" + latitude + ", terminal="
				+ terminal + "]";
	}
}