package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;


public class BallotBox<T extends Citizen> implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	public final int MAX_NUMBER_OF_VOTERS = 100;
	public final int MAX_NUMBER_OF_PARTIES = 30;
	private int serialId;
	private transient static int SERIAL_NUMBER = 1;
	private String Address;
	private ArrayList<T> authorizedCitizens; // if above 18
	private double votersCounter;
	
	
	
	private int numOfCitizens = 0;
	public int getNumOfCitizens() {
		return numOfCitizens;
	}

	public enum Kalpitype {
		army, normal, corona, armycorona
	};

	protected Kalpitype eKalpitype; // army/corona/normal/armyCorona
	public ArrayList<Integer> results; // array for counting votes.. not sure if needed
	// results

	public BallotBox(String address, String kalpitype) {
		this.Address = address.toLowerCase();
		this.serialId = SERIAL_NUMBER++;
		this.authorizedCitizens = new ArrayList<>();
		this.eKalpitype = Kalpitype.valueOf(kalpitype.toLowerCase());	
		
		this.results = new ArrayList<Integer>();								
																		
	}

	public int getSerialId() {
		return serialId;
	}

	public String getAddress() {
		return Address;
	}
	
	public ArrayList<T> getAuthorizedCitizens() {
		return authorizedCitizens;
	}

	public double getPercentageOfVoters() { // TODO: return percentage of voters: vote counts % total votes
		if (this.authorizedCitizens.size() == 0) {
			return 0;
		}
		return (votersCounter / (this.authorizedCitizens.size())) * 100;
	}

	public Kalpitype geteKalpitype() { // army / normal / corona

		return eKalpitype;
	}

	public double getVotersCounter() { // return total amounts of votes, may help with counting the results
		return votersCounter;
	}

	public void addAuthorizedCitizen(T citizen) {
		authorizedCitizens.add(citizen);
		}
	public void addVoterCounter() {
		votersCounter++;
	}
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("BallotBox serialId: " + serialId + ", Address: " + Address + ", votersCounter: " + votersCounter + ", numOfCitizens: "
				+ numOfCitizens + ", eKalpitype: " + eKalpitype + ", results: "+ results.toString() );
		return sb.toString();
	}


	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof BallotBox))
			return false;
		BallotBox<T> temp = (BallotBox<T>) obj;
		return serialId == temp.serialId && Address.equals(temp.Address);
	
	}
		
	}

