package View;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Controller.ElectionController;
import Model.Citizen;
import Model.Election;
import Model.Party;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ElectionView {
	// tabs:
	TabPane tPaneElection = new TabPane();
	Tab tPaneLoadSaveFile = new Tab("Load/Save File");
	Tab tPaneAddCitizen = new Tab("Add Citizen");
	Tab tPaneAddBallotBox = new Tab("Add BallotBox");
	Tab tPaneAddPoliticalParty = new Tab("Add Political-Party");
	Tab tPaneAddCandidate = new Tab("Add Political-Candidate");
	Tab tPaneStartVote = new Tab("Start a vote");
	// load/save file
	private BorderPane bpSaveLoadFile = new BorderPane();
	private Button btnSave = new Button("save file");
	private Button btnLoad = new Button("load file");
	private HBox hbSaveLoad = new HBox(10);

	// kalpi
	private BorderPane bpBallotBox = new BorderPane();
	private VBox vboxBallotBox = new VBox(30);
	private HBox hboxBallotBox = new HBox(10);
	private Label printBallotBox = new Label("Current Ballot boxes:");
	private Label ballotBoxTitle = new Label("add a Ballot Box");
	private Button addBallot = new Button("Add a ballot box"); // adding ballot box button
	private TextField tf = new TextField(); // text field and label to enter info from user
	private Label address = new Label("Enter address: ");
	private ComboBox<String> cbBallotBoxTypes = new ComboBox<String>();
	private Label ballotBoxType = new Label("Ballot box type: "); // untill here
	// citizen
	ToggleGroup radioButtonGroupQuarantine = new ToggleGroup();
	private BorderPane bpCitizen = new BorderPane();
	private VBox vbCitizen = new VBox(10);
	private HBox hbCitizen = new HBox(10);
	private Label printCitizens = new Label("Current Citizens:");
	private Label citizenTitle = new Label("add a Citizen");
	private Button addCitizen = new Button("Add a Citizen"); // adding ballot box button

	private Label lblIdCitizen = new Label("Id ");
	private TextField tfIdCitizen = new TextField();
	private Label lblFirstNameCitizen = new Label("First Name ");
	private TextField tfFirstNameCitizen = new TextField();
	private Label lblIsQuarantineCitizen = new Label("Are You in Quartine ? ");
	private HBox hPaneRadioButtonsQuratine = new HBox();
	private RadioButton rbIsQuarantineYesCitizen = new RadioButton("Yes");
	private RadioButton rbIsQuarantineNoCitizen = new RadioButton("No");
	private Label lbldaysSickCitizen = new Label("How many days is sick? ");
	private TextField tfDaysSickCitizen = new TextField();
	private Label lblYearDateCitizen = new Label("Year Date ");
	private TextField tfYearCitizen = new TextField();

	private BorderPane bPanePoliticalParty = new BorderPane();
	// add Political party
	private BorderPane bPaneAddPoliticalParty = new BorderPane();
	private Label lblTitleAddPoliticalParty = new Label("Add Political-Party ");
	private Label lblTitleListPoliticalParty = new Label("Political-Party List");
	private GridPane gPaneAddCitizenToPparty = new GridPane();
	private GridPane gPaneAddPoliticalParty = new GridPane();
	private VBox vPaneListPoliticalParty = new VBox();
	private Label lblNamePoliticalParty = new Label("Name of Political-Party ");
	private TextField tfNamePoliticalParty = new TextField();
	private Label lblOpinionPoliticalParty = new Label("Opinion ");
	private ObservableList<String> politicalPartyOpinions = FXCollections.observableArrayList("Right", "Center",
			"Left");
	private ComboBox comboBoxPoliticalPartyOpinion = new ComboBox(politicalPartyOpinions);
	private Button btnSubmitAddPoliticalParty = new Button("SUBMIT");

	// add a political candidate
	private BorderPane bPaneAddCandidate = new BorderPane();
	private Label lblCandidate = new Label("Add a candidate to a party");
	private Label lblCandidateName = new Label("Select Candidate from the list");
	private ComboBox<String> cbCandidateName = new ComboBox<String>();
	private Label lblCandidateParty = new Label("Select a party for the candidate");
	private ComboBox<String> cbCandidateParty = new ComboBox<String>();
	private Button sumbitCandidate = new Button("Submit candidate");
	private VBox vbCandidate = new VBox(30);
	private HBox hbCandidateName = new HBox(10);
	private HBox hbCandidateParty = new HBox(10);
	private Label lblCandidateList = new Label("Candidates List");

	// Start a vote tab
	private BorderPane bPaneVotenResults = new BorderPane();
	private Button bStartVote = new Button("Press to start");

	public ElectionView(Stage primaryStage) {
		hbSaveLoad.getChildren().addAll(btnLoad, btnSave);
		hbSaveLoad.setAlignment(Pos.CENTER);
		bpSaveLoadFile.setTop(hbSaveLoad);
		bpSaveLoadFile.setPadding(new Insets(30));

		ballotBoxTitle.setStyle(
				"-fx-font-family: TRON; -fx-font-size: 16; -fx-font-style: italic; -fx-font-weight: bold; -fx-border-color: black; -fx-border-width: 0 0 2 0;");
		bpBallotBox.setTop(ballotBoxTitle);
		bpBallotBox.setCenter(vboxBallotBox);
		cbBallotBoxTypes.getItems().addAll("Army", "Normal", "Corona", "Army/Corona");
		hboxBallotBox.getChildren().addAll(address, tf, ballotBoxType, cbBallotBoxTypes, addBallot);
		vboxBallotBox.getChildren().addAll(hboxBallotBox, printBallotBox);
		vboxBallotBox.setPadding(new Insets(50));

		citizenTitle.setStyle(
				"-fx-font-family: TRON; -fx-font-size: 16; -fx-font-style: italic; -fx-font-weight: bold; -fx-border-color: black; -fx-border-width: 0 0 2 0;");
		bpCitizen.setTop(citizenTitle);
		bpCitizen.setLeft(vbCitizen);
		rbIsQuarantineYesCitizen.setToggleGroup(radioButtonGroupQuarantine);
		rbIsQuarantineNoCitizen.setToggleGroup(radioButtonGroupQuarantine);
		rbIsQuarantineYesCitizen.setSelected(false);

		hPaneRadioButtonsQuratine.getChildren().addAll(rbIsQuarantineYesCitizen, rbIsQuarantineNoCitizen);
		vbCitizen.getChildren().addAll(lblFirstNameCitizen, tfFirstNameCitizen, lblIdCitizen, tfIdCitizen,
				lblIsQuarantineCitizen, hPaneRadioButtonsQuratine, lbldaysSickCitizen, tfDaysSickCitizen,
				lblYearDateCitizen, tfYearCitizen, addCitizen);
		vbCitizen.setPadding(new Insets(50));

		lbldaysSickCitizen.setVisible(false);
		tfDaysSickCitizen.setVisible(false);
		rbIsQuarantineYesCitizen.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				lbldaysSickCitizen.setVisible(rbIsQuarantineYesCitizen.isSelected());
				tfDaysSickCitizen.setVisible(rbIsQuarantineYesCitizen.isSelected());

			}
		});
		rbIsQuarantineNoCitizen.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				lbldaysSickCitizen.setVisible(rbIsQuarantineYesCitizen.isSelected());
				tfDaysSickCitizen.setVisible(rbIsQuarantineYesCitizen.isSelected());

			}
		});

		lblNamePoliticalParty.setStyle("-fx-font-family: TRON; -fx-font-size: 14; -fx-font-style: italic;");
		lblOpinionPoliticalParty.setStyle("-fx-font-family: TRON; -fx-font-size: 14; -fx-font-style: italic;");
		lblTitleAddPoliticalParty.setStyle(
				"-fx-font-family: TRON; -fx-font-size: 16; -fx-font-style: italic; -fx-font-weight: bold; -fx-border-color: black; -fx-border-width: 0 0 2 0;");
		comboBoxPoliticalPartyOpinion.getSelectionModel().select(0);
		gPaneAddPoliticalParty.add(lblNamePoliticalParty, 0, 0);
		gPaneAddPoliticalParty.add(tfNamePoliticalParty, 1, 0);
		gPaneAddPoliticalParty.add(lblOpinionPoliticalParty, 0, 1);
		gPaneAddPoliticalParty.add(comboBoxPoliticalPartyOpinion, 1, 1);
		gPaneAddPoliticalParty.add(btnSubmitAddPoliticalParty, 0, 2);
		gPaneAddPoliticalParty.setPadding(new Insets(10));
		gPaneAddPoliticalParty.setVgap(10);
		gPaneAddPoliticalParty.setHgap(20);
		gPaneAddPoliticalParty.paddingProperty().set(new Insets(30, 0, 0, 0));
		bPaneAddPoliticalParty.setPadding(new Insets(20));
		bPaneAddPoliticalParty.setTop(lblTitleAddPoliticalParty);
		bPaneAddPoliticalParty.setLeft(gPaneAddPoliticalParty);
		bPaneAddPoliticalParty.setAlignment(lblTitleAddPoliticalParty, Pos.CENTER);

		lblTitleListPoliticalParty.setStyle(
				"-fx-font-family: TRON; -fx-font-size: 16; -fx-font-style: italic; -fx-font-weight: bold; -fx-border-color: black; -fx-border-width: 0 0 2 0;");
		vPaneListPoliticalParty.getChildren().add(lblTitleListPoliticalParty);
		vPaneListPoliticalParty.setSpacing(20);
		vPaneListPoliticalParty.setAlignment(Pos.CENTER_LEFT);
		vPaneListPoliticalParty.setPadding(new Insets(20, 0, 0, 0));
		gPaneAddPoliticalParty.add(vPaneListPoliticalParty, 0, 3);

		bPanePoliticalParty.setLeft(bPaneAddPoliticalParty);
		// add a political candidate tab
		lblCandidate.setStyle(
				"-fx-font-family: TRON; -fx-font-size: 16; -fx-font-style: italic; -fx-font-weight: bold; -fx-border-color: black; -fx-border-width: 0 0 2 0;");
		bPaneAddCandidate.setTop(lblCandidate);
		hbCandidateName.getChildren().addAll(lblCandidateName, cbCandidateName);
		hbCandidateParty.getChildren().addAll(lblCandidateParty, cbCandidateParty);
		vbCandidate.setPadding(new Insets(30));
		vbCandidate.getChildren().addAll(hbCandidateName, hbCandidateParty, sumbitCandidate);
		bPaneAddCandidate.setCenter(vbCandidate);
		// Start a vote tab
		bPaneVotenResults.setPadding(new Insets(30));
		bPaneVotenResults.setTop(bStartVote);
		bPaneVotenResults.setAlignment(bStartVote, Pos.CENTER);
		tPaneStartVote.setContent(bPaneVotenResults);
		tPaneLoadSaveFile.setContent(bpSaveLoadFile);
		tPaneElection.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		tPaneAddCitizen.setContent(bpCitizen);
		tPaneAddBallotBox.setContent(bpBallotBox);
		tPaneAddPoliticalParty.setContent(bPanePoliticalParty);
		tPaneAddCandidate.setContent(bPaneAddCandidate);
		tPaneElection.getTabs().addAll(tPaneLoadSaveFile, tPaneAddCitizen, tPaneAddBallotBox, tPaneAddPoliticalParty,
				tPaneAddCandidate, tPaneStartVote);
		Scene scene = new Scene(tPaneElection, 800, 500);
		primaryStage.setFullScreen(true);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Election");
		primaryStage.setResizable(true);
		primaryStage.setAlwaysOnTop(false);
		primaryStage.show();
	}

	public void showAlert(String msg) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Alert!");
		alert.setContentText(msg);
		alert.showAndWait();

	}

	public String getBallotAddress() { // return user address
		return tf.getText();
	}

	public void LoadFile(EventHandler<ActionEvent> e) { // method to handle "load file" button event
		btnLoad.setOnAction(e);
	}

	public void SaveFile(EventHandler<ActionEvent> e) { // method to handle "save file" button event
		btnSave.setOnAction(e);
	}

	public void addBallotBox(EventHandler<ActionEvent> e) { // method to handle "add ballot box" button event
		addBallot.setOnAction(e);
	}

	public String getBallotBoxType() { // return user ballot box type
		if (cbBallotBoxTypes.getValue() == "Army/Corona") {
			return "armycorona";
		}
		return cbBallotBoxTypes.getValue();
	}

	public void addLblBallotBoxList(String ballotBoxAddress, String type) {
		Label label = new Label("- Address: " + ballotBoxAddress + " , Belong to: " + type);
		label.setStyle("-fx-font-family: STIXIntegralsUpD; -fx-font-size: 14; -fx-font-style: italic;");
		vboxBallotBox.getChildren().add(label);
	}

	public void addCitizen(EventHandler<ActionEvent> e) { // method to handle "add Citizen" button event
		addCitizen.setOnAction(e);
	}

	public String getCitizenName() {
		return tfFirstNameCitizen.getText();
	}

	public int getCitizenID() {
		return Integer.parseInt(tfIdCitizen.getText());
	}

	public int getCitizenYear() {
		return Integer.parseInt(tfYearCitizen.getText());
	}

	public RadioButton getRbIsQuarantineYesCitizen() {
		return rbIsQuarantineYesCitizen;
	}

	public int getDaysSick() {
		return Integer.parseInt(tfDaysSickCitizen.getText());
	}

	public void addLblCitizensList(String name, int id, int year, boolean isQuarantine, String type, int daysSick) {
		if (isQuarantine) {
			Label label = new Label("- name: " + name + " , id: " + id + " , year: " + year + " , is in quarantine?: "
					+ isQuarantine + " type: " + type + " , days is sick: " + daysSick);
			label.setStyle("-fx-font-family: STIXIntegralsUpD; -fx-font-size: 14; -fx-font-style: italic;");
			vbCitizen.getChildren().add(label);
		} else {
			Label label = new Label(
					"- name: " + name + " , id: " + id + " , year: " + year + " , is in quarantine?: " + isQuarantine+" type: " + type);
			label.setStyle("-fx-font-family: STIXIntegralsUpD; -fx-font-size: 14; -fx-font-style: italic;");
			vbCitizen.getChildren().add(label);
		}
	}

	public void sumbitCandidate(EventHandler<ActionEvent> e) {
		sumbitCandidate.setOnAction(e);
	}

	public ComboBox<String> getComboBoxPoliticalPartyOpinion() {
		return comboBoxPoliticalPartyOpinion;
	}

	public TextField getTfNamePoliticalParty() {
		return tfNamePoliticalParty;
	}

	public void addLblPoliticalPartyList(String name, String opinion) {
		Label label = new Label("- Name: " + name + " , Opinion: " + opinion);
		label.setStyle("-fx-font-family: STIXIntegralsUpD; -fx-font-size: 14; -fx-font-style: italic;");
		vPaneListPoliticalParty.getChildren().add(label);
	}

	public void addCitizenstoComboBox(String name) {
		getComboBoxCandidateNameOptions().getItems().add(name);

	}

	public ComboBox<String> getComboBoxCandidateNameOptions() {
		return cbCandidateName;
	}

	public ComboBox<String> getComboBoxPartiesOptions() {
		return cbCandidateParty;
	}

	public void addPartiesToComboBox(String name) {
		getComboBoxPartiesOptions().getItems().add(name);

	}

	public void onClickSubmitAddPoliticalParty(EventHandler<ActionEvent> e) {
		btnSubmitAddPoliticalParty.setOnAction(e);
	}

	public String showChoiceDialogVote(List<String> choices, String name) {
		ChoiceDialog<String> dialog = new ChoiceDialog<String>(choices.get(0), choices);
		dialog.setTitle("Voting");
		// show dialog msg
		dialog.setContentText("Hello " + name + ", please vote:");
		System.out.println(dialog.isShowing());
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent())
			return result.get();
		return "";
	}

	public void bStartVote(EventHandler<ActionEvent> e) {
		bStartVote.setOnAction(e);
	}

	public Button getBtnVote() {
		return bStartVote;
	}

	public void addLblVotingResultsLeft(String results) {
		Label label = new Label(results);
		label.setStyle("-fx-font-family: STIXIntegralsUpD; -fx-font-size: 10; -fx-font-style: italic;");
		bPaneVotenResults.setLeft(label);
	}

	public void addLblVotingResultsRight(String results) {
		Label label = new Label(results);
		label.setStyle("-fx-font-family: STIXIntegralsUpD; -fx-font-size: 10; -fx-font-style: italic;");
		bPaneVotenResults.setCenter(label);
	}
}
