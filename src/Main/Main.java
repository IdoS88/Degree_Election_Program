package Main;



import Controller.ElectionController;
import Model.BallotBox;
import Model.Citizen;
import Model.CoronaVerifiedCitizen;
import Model.CoronaVerifiedSoldier;
import Model.Election;
import Model.Party;
import Model.Soldier;
import View.ElectionView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{

		public static void main(String[] args) {

			launch();
		}

		@Override
		public void start(Stage primaryStage) throws Exception {
			ElectionView theView = new ElectionView(primaryStage);
			Election e1 = new Election(2021, 3);
			

			e1.addBallotBox(new BallotBox<Citizen>("Tel aviv", "normal"));
			e1.addBallotBox(new BallotBox<CoronaVerifiedCitizen>("Ramat gan", "corona"));
			e1.addBallotBox(new BallotBox<Soldier>("Petah Tikva", "army"));

			e1.addCitizen(new CoronaVerifiedSoldier("zohan", 123456789, 1993, true, 3));
			e1.addCitizen(new Citizen("gogo", 123456889, 1995, false));
			e1.addCitizen(new Citizen("yakov", 173456789, 1991, false));
			e1.addCitizen(new CoronaVerifiedSoldier("koko", 1234534789, 2001, true, 14));
			e1.addCitizen(new Citizen("avram", 129456789, 1992, false));

			e1.addParty(new Party("bamba", "center"));
			e1.addParty(new Party("bisli", "right"));
			e1.addParty(new Party("bagel", "left"));
			ElectionController controller = new ElectionController(e1, theView);
		}


	}


