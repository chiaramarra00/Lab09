package it.polito.tdp.borders.model;

import java.util.Objects;

public class Country {
	
	private int cCode;
	private String stateAbb;
	private String stateNme;
	
	public Country(int cCode, String stateAbb, String stateNme) {
		super();
		this.cCode = cCode;
		this.stateAbb = stateAbb;
		this.stateNme = stateNme;
	}

	public int getcCode() {
		return cCode;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cCode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		return cCode == other.cCode;
	}

	@Override
	public String toString() {
		return stateNme;
	}
	
	
}
