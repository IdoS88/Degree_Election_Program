package Model;

import java.io.Serializable;

public class Candidate extends Citizen implements Serializable {
	private Party party;
	private int numberInParty;
	
	public Candidate(Citizen citizen, Party party) {
		super(citizen.getName(),citizen.getId(), citizen.getBirthYear(),citizen.isInQuarantine());
		this.party = party;
	}
	
	public Candidate(String name, int id, int birthYear, boolean inQuarantine, Party party) {
		super(name, id, birthYear, inQuarantine);
		this.party = party;
		
	}

	@Override
	public String toString() {
		return "name: " + this.getName() ;
	}
	
}
