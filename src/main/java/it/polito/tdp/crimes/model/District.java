package it.polito.tdp.crimes.model;

import java.util.Objects;

import com.javadocmd.simplelatlng.LatLng;

public class District {

	private int id;
	private LatLng posizione;
	private int crimini;
	
	public District(int id, LatLng posizione, int crimini) {
		super();
		this.id = id;
		this.posizione = posizione;
		this.crimini = crimini;
	}
	public int getId() {
		return id;
	}
	public LatLng getPosizione() {
		return posizione;
	}
	public void setPosizione(LatLng posizione) {
		this.posizione = posizione;
	}
	
	public int getCrimini() {
		return crimini;
	}
	public void setCrimini(int crimini) {
		this.crimini = crimini;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(crimini, id, posizione);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		District other = (District) obj;
		return crimini == other.crimini && id == other.id && Objects.equals(posizione, other.posizione);
	}
	@Override
	public String toString() {
		return "District: " + id +" ";
	}
	
	
}
