package Model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import View.ElectionView;


public class Election implements Serializable {
	private int month;
	private int year;

	public ArrayList<BallotBox> allBallotBoxes;
	public AllCitizens<Citizen> allCitizens;
	public ArrayList<Party> allParties;
	private ArrayList<Integer> sumOfResult;
	public boolean hasElectionHappened = false;

	public Election(int year, int month) {
		allBallotBoxes = new ArrayList<BallotBox>();
		allCitizens = new AllCitizens<>();
		allParties = new ArrayList<Party>();
		this.year = year;
		this.month = month;
		this.sumOfResult = new ArrayList<Integer>();
	}

	public Election(String fileName) throws FileNotFoundException {
		Scanner s = new Scanner(fileName);
		this.month = s.nextInt();
		this.year = s.nextInt();

	}

	public void showBallotBoxes() {
		for (int i = 0; i < allBallotBoxes.size(); i++) {
			System.out.println(allBallotBoxes.get(i).toString());
		}
	}

	public void showCitizens() {
		for (int i = 0; i < allCitizens.getSize(); i++) {
			System.out.println(
					i + 1 + ") " + allCitizens.getCitizen(i).getName() + "\n id: " + allCitizens.getCitizen(i).getId());

		}
	}

	public void showParties() {
		for (int i = 0; i < allParties.size(); i++) {
			System.out.println(i + 1 + ") " + allParties.get(i));
		}
	}

	public boolean addCitizen(Citizen citizen) {
		return allCitizens.AddCitizen(citizen);

	}

	public void addParty(Party party) {
		allParties.add(party);
		for (int i = 0; i < allBallotBoxes.size(); i++) {
			allBallotBoxes.get(i).results.add(0);
		}
	}

	public void addBallotBox(BallotBox ballotBox) {
		allBallotBoxes.add(ballotBox);
		System.out.println("BallotBox added successfully");
	}

	public void sortCitizens() {
		for (int i = 0; i < allCitizens.getSize(); i++) {
			if (year - allCitizens.getCitizen(i).getBirthYear() > 18) { // check if the citizen is old enough to vote
				if (year - allCitizens.getCitizen(i).getBirthYear() < 21) {
					if (allCitizens.getCitizen(i).isInQuarantine()) { // check if he is a solider
						for (int j = 0; j < allBallotBoxes.size(); j++) {
							if (allBallotBoxes.get(j).geteKalpitype().ordinal() == 4) { // check if the kalpi is a
																						// armyCorona kalpi
								allCitizens.getCitizen(i).setKalpi(allBallotBoxes.get(j)); // add the solider to an army
																							// kalpi
								allBallotBoxes.get(i).addAuthorizedCitizen(allCitizens.getCitizen(i)); // add citizen to
																										// kalpi
								break;
							}

						}
					} else
						for (int j = 0; j < allBallotBoxes.size(); j++) {
							if (allBallotBoxes.get(j).geteKalpitype().ordinal() == 0) { // check if the kalpi is a army
																						// kalpi
								allCitizens.getCitizen(i).setKalpi(allBallotBoxes.get(j)); // add the solider to an army
																							// kalpi
								allBallotBoxes.get(i).addAuthorizedCitizen(allCitizens.getCitizen(i)); // add citizen to
																										// kalpi
								break;
							}

						}
				}
				if (allCitizens.getCitizen(i).isInQuarantine()) { // check if the
																	// citizen is in
																	// quarantine
					for (int k = 0; k < allBallotBoxes.size(); k++) {
						if (allBallotBoxes.get(k).geteKalpitype().ordinal() == 2) { // check if the kalpi is a corona
																					// kalpi
							allCitizens.getCitizen(i).setKalpi(allBallotBoxes.get(k)); // add the citizen to an corona
																						// kalpi
							allBallotBoxes.get(k).addAuthorizedCitizen(allCitizens.getCitizen(i)); // add citizen to
																									// kalpi
							break;
						}
					}
				} else {

					for (int c = 0; c < allBallotBoxes.size(); c++) {
						if (allBallotBoxes.get(c).geteKalpitype().ordinal() == 1) { // check if the kalpi is a normal
																					// kalpi
							allCitizens.getCitizen(i).setKalpi(allBallotBoxes.get(c)); // add the citizen to an normal
																						// kalpi
							allBallotBoxes.get(c).addAuthorizedCitizen(allCitizens.getCitizen(i)); // add citizen to
																									// kalpi
							break;
						}
					}
				}
			}
		}
	}

	public boolean setCandidate(int citizenIndex, int partyIndex) {
		allParties.get(partyIndex)
				.addCandidate(new Candidate(allCitizens.getCitizen(citizenIndex), allParties.get(partyIndex)));
		System.out.println("Candidate added");
		return true;
	}

	public String showResults() {
		if (hasElectionHappened) {
			StringBuffer sb = new StringBuffer();
			sb.append("election results:\n");
			for (int i = 0; i < allBallotBoxes.size(); i++) {
				sb.append("\nResults for kalpi ID: " + allBallotBoxes.get(i).getSerialId() + ", located in: "
						+ allBallotBoxes.get(i).getAddress() + "\n");
				sb.append("Percentage of voters: " + allBallotBoxes.get(i).getPercentageOfVoters() + "%\n");
				for (int j = 0; j < allParties.size(); j++) {
					sb.append(allParties.get(j).getName() + " ,votes: " + allBallotBoxes.get(i).results.get(j) + "\n");
					int temp = (int) sumOfResult.get(j) + (int) allBallotBoxes.get(i).results.get(j);
					sumOfResult.set(j, temp);
				}
			}
			return sb.toString();
		}
		return "";
	}

	public String showResultsTotal() {
		if (hasElectionHappened) {
			StringBuffer sb = new StringBuffer();
			sb.append("\nTotal results per party: \n");
			for (int i = 0; i < allParties.size(); i++) {
				sb.append("\nParty's name: " + allParties.get(i).getName() + "\nTotal results: " + sumOfResult.get(i));

			}
			return sb.toString();
		}
		return "";
	}

	public void setNewResults() {
		for (int i = 0; i < allBallotBoxes.size(); i++)
			for (int k = 0; k < allParties.size(); k++)
				allBallotBoxes.get(i).results.add((Integer) 0);
		for (int i = 0; i < allParties.size(); i++)
			sumOfResult.add((Integer) 0);
	}

	public void save() throws FileNotFoundException {
		PrintWriter pw = new PrintWriter("savedElections");
		pw.print(this.month);
		pw.print(this.year);
		pw.print(this.allBallotBoxes);
		pw.print(this.allCitizens);
		pw.print(this.allParties);
		pw.print(this.hasElectionHappened);
		pw.print(this.sumOfResult);
		pw.close();
	}

	public int searchParty(String partyName) {
		for (int i = 0; i < allParties.size(); i++) {
			if (partyName.equalsIgnoreCase(allParties.get(i).getName())) {
				return i;
			}
		}
		return -1;

	}

	public int searchCandidate(String candidateName) {
		for (int i = 0; i < allCitizens.getSize(); i++) {
			if (candidateName.equalsIgnoreCase(allCitizens.getCitizen(i).getName())) {
				return i;
			}
		}
		return -1;

	}

	public List<String> Parties() {
		List<String> choices = new ArrayList<String>();
		// get party names
		for (Party p : allParties) {
			choices.add(p.getName());
		}
		return choices;
	}

	public void vote(ElectionView view) {
		for (int i = 0; i < allBallotBoxes.size(); i++) {
			for (int k = 0; k < allBallotBoxes.get(i).getAuthorizedCitizens().size(); k++) {
				int partyIndex = searchParty(view.showChoiceDialogVote(Parties(),
						((Citizen) allBallotBoxes.get(i).getAuthorizedCitizens().get(k)).getName()));
				System.out.println(partyIndex); // print to see that it works
				// increase voting count for current party by 1
				int temp = (int) allBallotBoxes.get(i).results.get(partyIndex);
				temp++;
				// save new result
				allBallotBoxes.get(i).results.set(partyIndex, temp);
				// increase balllot box total voters count
				allBallotBoxes.get(i).addVoterCounter();
			}
		}
	}

	
}
