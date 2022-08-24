package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class AllCitizens<T extends Citizen> implements Serializable,Cloneable {
	ArrayList<T> allCitizens;

	public AllCitizens(ArrayList<T> allCitizens) {
		this.allCitizens = allCitizens;
	}

	public AllCitizens() {
		this.allCitizens = new ArrayList<>();
	}

	public boolean AddCitizen(T citizen){
		if( !(citizen instanceof Citizen)) { 	//checking if we got an object that is not a citizen
			System.out.println("Incorrect type.. Method can only add citizens");
			return false;
		}
		if (allCitizens.contains(citizen)) {
			return false;
		}
		allCitizens.add(citizen);
		return true;
	}
	public Citizen getCitizen(int index) {
		return allCitizens.get(index);
	}
	public int getSize() {
		return allCitizens.size();
	}

	public boolean contains(Citizen citizen) {
		for (int i=0; i < allCitizens.size(); i++) {
			if(allCitizens.get(i).getName().equalsIgnoreCase(citizen.getName())) {
				return true;
			}
		}
		return false;
	}


	
}
