package pack;

public class Etel {
	private int eid;
	private int kisar;
	private int nagyar;
	private String tipus;
	private String nev;
	
	//konstruktor	
	public Etel(int eid, int kisar, int nagyar, String tipus, String nev) {
		super();
		this.eid = eid;
		this.kisar = kisar;
		this.nagyar = nagyar;
		this.tipus = tipus;
		this.nev = nev;
	}
	
	//getters, setters
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public int getKisar() {
		return kisar;
	}
	public void setKisar(int kisar) {
		this.kisar = kisar;
	}
	public int getNagyar() {
		return nagyar;
	}
	public void setNagyar(int nagyar) {
		this.nagyar = nagyar;
	}
	public String getTipus() {
		return tipus;
	}
	public void setTipus(String tipus) {
		this.tipus = tipus;
	}
	public String getNev() {
		return nev;
	}
	public void setNev(String nev) {
		this.nev = nev;
	}

}
