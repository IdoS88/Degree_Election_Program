package Controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import javax.swing.JOptionPane;

import Model.AllCitizens;
import Model.BallotBox;
import Model.Citizen;
import Model.Election;
import Model.Party;
import View.ElectionView;
import javafx.scene.control.ChoiceDialog;
import Model.CoronaVerifiedCitizen;
import Model.CoronaVerifiedSoldier;
import Model.Soldier;

public class ElectionController {
	private Election election;
	private ElectionView view;

	public static void saveToFile(Election e) throws IOException, FileNotFoundException {
		ObjectOutputStream outFile = new ObjectOutputStream(new FileOutputStream("Election.dat"));
		outFile.writeObject(e);
		outFile.close();

	}

	public static Election loadFromFile() throws IOException, FileNotFoundException, ClassNotFoundException {
		ObjectInputStream inFile = new ObjectInputStream(new FileInputStream("Election.dat"));
		Election e = (Election) inFile.readObject();
		inFile.close();
		return e;

	}

	public ElectionController(Election e1, ElectionView view) {
		this.election = e1;
		this.view = view;

		view.LoadFile(e -> {
			try {
				Election electionFromFile = loadFromFile();
				for (int i = 0; i < electionFromFile.allBallotBoxes.size(); i++) {
					// if the ballot box already in the original election then dont add it
					if (!election.allBallotBoxes.contains(electionFromFile.allBallotBoxes.get(i))) {
						view.addLblBallotBoxList(electionFromFile.allBallotBoxes.get(i).getAddress(),
								electionFromFile.allBallotBoxes.get(i).geteKalpitype().toString());
					}
				}

				for (int i = 0; i < electionFromFile.allCitizens.getSize(); i++) {
					if (!election.allCitizens.contains(electionFromFile.allCitizens.getCitizen(i))) {
						view.addLblCitizensList(electionFromFile.allCitizens.getCitizen(i).getName(),
								electionFromFile.allCitizens.getCitizen(i).getId(),
								electionFromFile.allCitizens.getCitizen(i).getBirthYear(),
								electionFromFile.allCitizens.getCitizen(i).isInQuarantine(),
								electionFromFile.allCitizens.getCitizen(i).getClass().getSimpleName(), 0);
						view.addCitizenstoComboBox(electionFromFile.allCitizens.getCitizen(i).getName());
					}
				}

				for (int i = 0; i < electionFromFile.allParties.size(); i++) {
					if (!election.allParties.contains(electionFromFile.allParties.get(i))) {
						view.addLblPoliticalPartyList(electionFromFile.allParties.get(i).getName(),
								electionFromFile.allParties.get(i).getWing().toString());
						view.addPartiesToComboBox(electionFromFile.allParties.get(i).getName());
					}
				}

				this.election = electionFromFile;
				view.showAlert("Data loaded successfully");
			} catch (Exception e2) {
				view.showAlert("No file to load, use hard-coded data ");
				boolean fileExists = false;
			}
		});

		view.SaveFile(e -> {
			try {
				saveToFile(election);
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			view.showAlert("Data saved");
		});
		for (int i = 0; i < e1.allBallotBoxes.size(); i++) {
			view.addLblBallotBoxList(e1.allBallotBoxes.get(i).getAddress(),
					e1.allBallotBoxes.get(i).geteKalpitype().name());
		}
		view.addBallotBox(e -> {
			String address = view.getBallotAddress();
			String type = view.getBallotBoxType();
			election.addBallotBox(new BallotBox<Citizen>(address, type));
			view.addLblBallotBoxList(address, type);
		});

		for (int i = 0; i < e1.allCitizens.getSize(); i++) {
			if (e1.allCitizens.getCitizen(i) instanceof CoronaVerifiedCitizen) {
				view.addLblCitizensList(e1.allCitizens.getCitizen(i).getName(), e1.allCitizens.getCitizen(i).getId(),
						e1.allCitizens.getCitizen(i).getBirthYear(), e1.allCitizens.getCitizen(i).isInQuarantine(),
						e1.allCitizens.getCitizen(i).getClass().getSimpleName(),
						((CoronaVerifiedCitizen) e1.allCitizens.getCitizen(i)).getDaysSick());
			} else if (e1.allCitizens.getCitizen(i) instanceof CoronaVerifiedSoldier)
				view.addLblCitizensList(e1.allCitizens.getCitizen(i).getName(), e1.allCitizens.getCitizen(i).getId(),
						e1.allCitizens.getCitizen(i).getBirthYear(), e1.allCitizens.getCitizen(i).isInQuarantine(),
						e1.allCitizens.getCitizen(i).getClass().getSimpleName(),
						((CoronaVerifiedSoldier) e1.allCitizens.getCitizen(i)).getDaysSick());
			else
				view.addLblCitizensList(e1.allCitizens.getCitizen(i).getName(), e1.allCitizens.getCitizen(i).getId(),
						e1.allCitizens.getCitizen(i).getBirthYear(), e1.allCitizens.getCitizen(i).isInQuarantine(),
						e1.allCitizens.getCitizen(i).getClass().getSimpleName(), 0);

		}
		view.addCitizen(e -> {
			String name = view.getCitizenName();
			int id = view.getCitizenID();
			int year = view.getCitizenYear();
			if (!isIDValid(id)) {
				view.showAlert("Invalid ID, try again ..");

			} else {
				if (!isYearBirthValid(year)) {
					view.showAlert("Invalid birth year, try again ..");
				} else {
					boolean isQuarantine = view.getRbIsQuarantineYesCitizen().isSelected() ? true : false;
					if (isQuarantine) {
						int daysSick = view.getDaysSick();
						if (LocalDate.now().getYear() - year > 18 && LocalDate.now().getYear() - year < 21) {
							CoronaVerifiedSoldier s1 = new CoronaVerifiedSoldier(name, id, year, isQuarantine,
									daysSick);
							election.addCitizen(s1); // if
														// soldier
														// and has
														// corona
							view.addLblCitizensList(name, id, year, isQuarantine, s1.getClass().getSimpleName(),
									daysSick);
							view.addCitizenstoComboBox(name);
						} else {
							CoronaVerifiedCitizen c1 = new CoronaVerifiedCitizen(name, id, year, isQuarantine,
									daysSick);
							election.addCitizen(c1); // if a
														// citizen
														// and has
														// corona
							view.addLblCitizensList(name, id, year, isQuarantine, c1.getClass().getSimpleName(),
									daysSick);
							view.addCitizenstoComboBox(name);
						}
					} else if (LocalDate.now().getYear() - year >= 18 && LocalDate.now().getYear() - year <= 21) {
						Soldier s1 = new Soldier(name, id, year, isQuarantine);
						election.addCitizen(s1); // if soldier and has corona
						view.addLblCitizensList(name, id, year, isQuarantine, s1.getClass().getSimpleName(), 0);
						view.addCitizenstoComboBox(name);
					} else {
						Citizen c1 = new Citizen(name, id, year, isQuarantine);
						election.addCitizen(c1); // if a citizen and has corona
						view.addLblCitizensList(name, id, year, isQuarantine, c1.getClass().getSimpleName(), 0);
						view.addCitizenstoComboBox(name);
					}
				}
			}

		});

		for (int i = 0; i < e1.allParties.size(); i++) {
			view.addLblPoliticalPartyList(e1.allParties.get(i).getName(), e1.allParties.get(i).getWing().name());
		}
		view.onClickSubmitAddPoliticalParty(e -> {
			String opinion = view.getComboBoxPoliticalPartyOpinion().getValue();
			String name = view.getTfNamePoliticalParty().getText();
			election.addParty(new Party(name, opinion.toLowerCase()));
			view.addLblPoliticalPartyList(name, opinion);
			view.addPartiesToComboBox(name);
		});

		for (int i = 0; i < e1.allCitizens.getSize(); i++) {
			view.addCitizenstoComboBox(e1.allCitizens.getCitizen(i).getName());
		}
		for (int i = 0; i < e1.allParties.size(); i++) {
			view.addPartiesToComboBox(e1.allParties.get(i).getName());
		}

		view.sumbitCandidate(e -> {
			// save party and candidate name
			String candidateName = view.getComboBoxCandidateNameOptions().getValue();
			String partyName = view.getComboBoxPartiesOptions().getValue();
			// get indexes of candidate and party
			int candidateIndex = e1.searchCandidate(candidateName);
			int partyIndex = e1.searchParty(partyName);
			e1.setCandidate(candidateIndex, partyIndex);

		});

		view.bStartVote(e -> {
			election.hasElectionHappened = true;
			election.sortCitizens();
			election.setNewResults();
			election.vote(view);
			view.addLblVotingResultsLeft(election.showResults());
			view.addLblVotingResultsRight(election.showResultsTotal());
			view.getBtnVote().setDisable(true);
		});

	}

	private boolean isYearBirthValid(int year) {
		if (LocalDate.now().getYear() - year < 18) {
			return false;
		}
		return true;
	}

	private boolean isIDValid(int id) {
		String StringID = id + "";
		if (StringID.length() != 9)
			return false;
		return true;
	}

}
