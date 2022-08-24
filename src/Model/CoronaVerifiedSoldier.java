package Model;

import java.io.Serializable;

public class CoronaVerifiedSoldier extends Citizen implements Serializable {
	private int daysSick;
	public CoronaVerifiedSoldier(String name, int id, int birthYear, boolean inQuarantine,int daysSick ) {
		super(name, id, birthYear, inQuarantine);
		this.daysSick = daysSick;

	}
	public int getDaysSick() {
		return daysSick;
	}
}
