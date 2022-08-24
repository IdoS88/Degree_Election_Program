
package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class Party implements Serializable{
	private String name;
	public enum ePartyWings { right, left, center };
	private ePartyWings wing;
	private LocalDate dateCreated;
	public Candidate[] allCandidates;
	private int numOfCandidates = 0;
	
	public Party(String name, String wing) {
		this.name = name;
		this.wing = ePartyWings.valueOf(wing);
		this.allCandidates = new Candidate[120];
		this.dateCreated = LocalDate.now();
	}
	public void addCandidate(Candidate candidate) {
		allCandidates[numOfCandidates++] = candidate;
	}
	
	public ePartyWings getWing() {
		return wing;
	}

	public LocalDate getDateCreated() {
		return dateCreated;
	}

	
	public String getName() {
		return name;
	}
	@Override
	public String toString() {
		return "Party name: " + name + ", wing: " + wing + ", dateCreated: " + dateCreated;
	}
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Party))
			return false;
		Party temp = (Party) obj;
		return this.name.equalsIgnoreCase(temp.name);
	
	} 
	
}
