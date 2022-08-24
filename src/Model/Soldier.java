package Model;

import java.io.Serializable;

public class Soldier extends Citizen implements hasWeapon, Serializable  {

	public Soldier(String name, int id, int birthYear, boolean inQuarantine) {
		super(name, id, birthYear, inQuarantine);
	}
	@Override
	public boolean carryWeapon() {
		return true;
	}

}
