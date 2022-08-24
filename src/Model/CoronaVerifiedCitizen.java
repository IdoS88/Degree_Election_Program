package Model;

import java.io.Serializable;

public class CoronaVerifiedCitizen extends Citizen implements Serializable  {

	private int daysSick;
	public CoronaVerifiedCitizen(String name, int id, int birthYear, boolean inQuarantine,int daysSick ) {
		super(name, id, birthYear, inQuarantine);
		this.daysSick = daysSick;

	}
	public int getDaysSick() {
		return daysSick;
	}
	
}


