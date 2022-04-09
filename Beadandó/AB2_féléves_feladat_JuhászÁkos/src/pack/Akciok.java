package pack;

public class Akciok {
	private int eid;
	private int learazas;
	private int honap;
	
	//konstruktor
	public Akciok(int eid, int learazas, int honap) {
		super();
		this.eid = eid;
		this.learazas = learazas;
		this.honap = honap;
	}

	//getters, setters
	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public int getLearazas() {
		return learazas;
	}

	public void setLearazas(int learazas) {
		this.learazas = learazas;
	}

	public int getHonap() {
		return honap;
	}

	public void setHonap(int honap) {
		this.honap = honap;
	}
	
}
