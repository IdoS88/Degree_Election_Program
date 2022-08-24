package Model;

import java.io.Serializable;
import java.util.Comparator;

public class Citizen implements Comparator<Citizen>,Serializable{
	private String name;
	private int id;
	private int birthYear;
	private BallotBox kalpi;
	private boolean inQuarantine;


	public Citizen(String name, int id, int birthYear, boolean inQuarantine) {
		this.name = name;
		this.birthYear = birthYear;
		this.id = id;                    
		this.kalpi = null;				 //TODO - check exceptions
		this.inQuarantine = inQuarantine;
	
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public int getBirthYear() {
		return birthYear;
	}

	public boolean setKalpi(BallotBox kalpi) {
		this.kalpi = kalpi;
		return true;
	}

	public BallotBox getKalpi() {
		return kalpi;
	}

	public boolean isInQuarantine() {
		return inQuarantine;
	}

	@Override
	public String toString() {
		return "Citizen name: " + name + ", id: " + id + ", birthYear: " + birthYear + ", kalpi: " + kalpi
				+ ", inQuarantine: " + inQuarantine;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Citizen))
			return false;
		Citizen temp = (Citizen) obj;
		return name == temp.name && id == temp.id;
	}

	@Override
	public int compare(Citizen c1 , Citizen c2) {
		if (c1.getId() == c2.getId()) {
			return 0;
		}else {
			return 1;
		}
	}
}
