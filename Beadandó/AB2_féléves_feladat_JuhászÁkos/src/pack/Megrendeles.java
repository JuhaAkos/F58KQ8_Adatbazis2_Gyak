package pack;

import java.sql.Date;

public class Megrendeles {
	private int mid;
	private Date datum;
	private String megrendelo;
	private String cim;
	private String telefon; //a telefonszámok,ugye számokból állnak de mivel mûveleteket nem kell végeznem velük a string bõven megfelelt és így kevesebb probléma volt a mérettel

	
	//konstruktor
	public Megrendeles(int mid, Date datum, String megrendelo, String cim, String telefon) {
		super();
		this.mid = mid;
		this.datum = datum;
		this.megrendelo = megrendelo;
		this.cim = cim;
		this.telefon = telefon;

	}

	//getters, setters
	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public String getMegrendelo() {
		return megrendelo;
	}

	public void setMegrendelo(String megrendelo) {
		this.megrendelo = megrendelo;
	}

	public String getCim() {
		return cim;
	}

	public void setCim(String cim) {
		this.cim = cim;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	
		
}
