package dm3_gym_app;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import dm3_gym_data.Membre;
import dm3_gym_data.Professionnel;
import dm3_gym_data.Seance;
import dm3_gym_data.Service;
import dm3_gym_data.Usager;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class CentreDeDonnees extends JFrame{	
	// APP MODULES
	private GestionDeComptes gdc;	
	private RepertoireDesServices rds;
	//private ProcedureComptable pc;
	
	private Usager currentUser;
	private Service currentService;
	private boolean memberFlag = false, createFlag = false, serviceFlag = false;
	
	// UI ELEMENTS
	private JPanel menuPanel, userPanel, servicePanel, listPanel;
	private JLabel menuTitle, inputUserTitle, inputServiceTitle, lblUserID, lblPrenom, lblNom, lblAdresse, lblTelephone, lblCourriel, 
				   lblDate, lblAccountFeedback, lblServiceFeedback, lblVille, lblCodePostal,lblProvTerr,lblProNum, listTitle;
	private ButtonListener buttonListener;
	private JButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btnUserCancel, btnUserConfirm, btnServiceCancel, btnServiceConfirm,btnListConfirm,btnListCancel;
	private UtilDateModel naissanceModel, startModel, endModel;
	private JDatePanelImpl naissancePanel,startDatePanel, endDatePanel;
	private JDatePickerImpl dateNaissance, dateStart, dateEnd;
	private JTextField txtUserID, txtPrenom, txtNom, txtAdresse, txtPhone1, txtEmail1, txtEmail2, txtEmail3, txtPhone2, txtPhone3,
					   txtVille,txtCodePostal,txtServiceName;
	private JButton[] menuButtons;
	private JLabel[] inputLabels;
	private JTextField[] inputFields;
	private JComboBox<String> comboProvTerr;
	private JTextArea txtCommentaire;
	private JSpinner spnPrix, spnHeure, spnMins, spnCapacite;
	private JCheckBox chckbxLundi, chckbxMardi, chckbxMercredi, chckbxJeudi, chckbxVendredi, chckbxSamedi, chckbxDimanche;
	private JCheckBox[] checkBoxes;
	private MyTableModel attendeeModel, seanceModel;
	
	// UI LOGIC
	private int panelID = -1;	
	private final int MENU_PANEL_LIMIT = 10, USER_PANEL_LIMIT = 20, SERVICE_PANEL_LIMIT = 30; // Section delimiters	
	private final int MAIN_MENU = 0, USER_MENU = 1, MEMBER_APP_MENU = 2, PRO_APP_MENU = 3, SERVICE_MENU = 4, ACCOUNT_MENU = 5; // Menu panels	
	private final int LOGIN_INPUT = 11, CREATE_USER_INPUT = 12, USER_CODE_INPUT = 13, MODIFY_USER_INPUT = 14; // User screen panels
	private final int CREATE_SERVICE_INPUT = 21, MODIFY_SERVICE_INPUT = 22; // Service screen panels
	private final int ATTENDEE_LIST = 31, SEANCE_LIST = 32;
	private JTable tableList;
	private ArrayList<String[]> listeBuffer;

	private class ButtonListener implements ActionListener {		
		public void actionPerformed(ActionEvent arg0) {	
			// Get source button
			JButton source = (JButton)arg0.getSource(); 
		
			/*
			 * MENU BUTTONS
			 */
			
			// Button 1
			if( source == btn1 ) {
				switch(panelID) {	
					case MAIN_MENU : app_LogIn(); break;
					case USER_MENU: createUser(true); break;
					case MEMBER_APP_MENU: showAccessCard(); break;
					case ACCOUNT_MENU: modifyUser(); break;
					case SERVICE_MENU: selectSeance(); break;
				}
			}
			// Button 2
			else if( source == btn2 ) {
				switch(panelID) {	
					case MAIN_MENU : break;
					case USER_MENU: createUser(false); break;
					case MEMBER_APP_MENU: payerFrais(); break;
					case ACCOUNT_MENU: deleteUser();
					case SERVICE_MENU: confirmPresence(); break;
				}
			}
			// Button 3
			else if( source == btn3 ) {
				switch(panelID) {	
					case MAIN_MENU : break;
					case USER_MENU: break;
					case MEMBER_APP_MENU: modifyUser(); break;
					case ACCOUNT_MENU: payerFrais(); break;
					case SERVICE_MENU: createService(); break;
				}			
			}
			// Button 4
			else if( source == btn4 ) {
				switch(panelID) {	
					case MAIN_MENU : displayUserMenu(); break;
					case USER_MENU: userLogIn(false); break;
					case ACCOUNT_MENU: renouvellerMembre(); break;
					case MEMBER_APP_MENU: deleteUser(); break;		
					case SERVICE_MENU: modifyService(); break;
				}		
			}
			// Button 5
			else if( source == btn5 ) {
				switch(panelID) {	
					case MAIN_MENU : userLogIn(true); break;
					case USER_MENU: break;
					case MEMBER_APP_MENU: showAttendees(); break;		
					case SERVICE_MENU: showAttendees(); break;
				}	
			}
			// Button 6
			else if( source == btn6 ) {
				switch(panelID) {	
					case MAIN_MENU : break;
					case USER_MENU: break;
					case MEMBER_APP_MENU: selectSeance(); break;		
					case SERVICE_MENU: break;
				}		
			}
			// Button 7
			else if( source == btn7 ) {
				switch(panelID) {	
					case MAIN_MENU : System.exit(0); break;
					case USER_MENU: displayMainMenu(); break;
					case MEMBER_APP_MENU: displayMainMenu(); break;
					case SERVICE_MENU: displayMainMenu(); break;
					case ACCOUNT_MENU: displayUserMenu();
				}
			}	
			
			/*
			 * Input screen button
			 */
			else if( source == btnUserCancel ) {
				switch(panelID) {	
					case CREATE_USER_INPUT : displayUserMenu(); break;						
					case USER_CODE_INPUT: displayUserMenu(); break;
					case MODIFY_USER_INPUT: displayAccountMenu(); break;					
				}
			}
			else if( source == btnUserConfirm ) {
				switch(panelID) {	
					case CREATE_USER_INPUT : confirmUserEdit(); break;		
					case USER_CODE_INPUT: confirmLogIn(); break;
					case MODIFY_USER_INPUT: confirmUserEdit(); break;					
				}
			}
			else if( source == btnServiceCancel) {
				switch(panelID) {						
					case CREATE_SERVICE_INPUT: displayServiceMenu(); break;
					case MODIFY_SERVICE_INPUT: displayServiceMenu(); break;
				}
			}
			else if( source == btnServiceConfirm) {
				switch(panelID) {						
					case CREATE_SERVICE_INPUT: confirmServiceEdit(); break;
					case MODIFY_SERVICE_INPUT: confirmServiceEdit(); break;
				}
			}
			else if( source == btnListCancel ) {
				switch(panelID) {						
					case SEANCE_LIST: displayServiceMenu(); break;
					case ATTENDEE_LIST: displayServiceMenu(); break;
				}
			}
			else if( source == btnListConfirm ) {
				switch(panelID) {						
					case SEANCE_LIST: confirmSelection(); break;					
				}
			}
		}
	}
	
	private class JTextFieldFormatter extends PlainDocument {
		  private boolean forceUpperCase;
		  private boolean acceptsNumerical;
		  private boolean acceptsAlphabetical;
		  private int maxLength;
	
		  JTextFieldFormatter(boolean upper, boolean num, boolean alpha, int length) {
			   super();
			   forceUpperCase = upper;
			   acceptsNumerical = num;
			   acceptsAlphabetical = alpha;
			   maxLength = length;
		   }
		  		 
		  public void insertString (int offset, String  str, AttributeSet attr) throws BadLocationException {
			   // Return conditions
			   if (str == null) return;
			   char currentChar = str.charAt(0);
			   if ( Character.isDigit(currentChar) && !acceptsNumerical ) return;
			   if ( Character.isAlphabetic(currentChar) && !acceptsAlphabetical ) return;
		
			   //Test for length first
			   if ( (getLength() + str.length()) <= maxLength ) {
				   	 
				   //Enforce format		
				     if (forceUpperCase) str = str.toUpperCase();
				     super.insertString(offset, str, attr);
			     }
		   }
	}
	
	private class MyTableModel extends DefaultTableModel{

		public MyTableModel(String col1, String col2, String col3) {
			setColumnIdentifiers(new String[] {col1,col2,col3});		
		}
		
		public void insertRow(String col1, String col2, String col3) {
			addRow(new String[] {col1,col2,col3});
		}
		
		public void clearRows() {
			for (int i = getRowCount() - 1; i >= 0; i--) {
			    removeRow(i);
			}
		}
		
		@Override
		public boolean isCellEditable(int row, int colum) {
			return false;
		}				
	}
	
	/*******************************************************************************************************
	 * MAIN APP METHODS
	 *******************************************************************************************************/
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CentreDeDonnees app = new CentreDeDonnees();
					app.setResizable(false);
					app.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the App.
	 */
	public CentreDeDonnees() {		
		// Initialize application module
		gdc = new GestionDeComptes();
		rds = new RepertoireDesServices();
		//pc  = new ProcedureComptable();
			
		// Initialize application frame		
		setTitle("Equipe 16 : DM3");
		getContentPane().setBackground(Color.DARK_GRAY);
		setBounds(0, 0, 750, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		// Initialize UI elements
		buttonListener = new ButtonListener();			
		initializeMenuPanel();
		initializeUserPanel();
		initializeServicePanel();
		initializeListPanel();
		
		// Opening page		
		displayMainMenu();		
	}
	
	/*******************************************************************************************************
	 * APP MODULES METHODS
	 *******************************************************************************************************/
	private void app_LogIn() {	
		String input = JOptionPane.showInputDialog(getContentPane(), "Entrez votre compte Facebook (Courriel)");                         
		Usager found = gdc.getUserByMail(input);
		
		if (found == null)
		    JOptionPane.showMessageDialog(getContentPane(), "Compte introuvable !");
		else {
			currentUser = found;
		    app_displayAccountMenu();
		} 
				
	}
	
	private void app_confirmLogIn() {
		
	}
	
	private void userLogIn( boolean serviceScreen ) {
		serviceFlag = serviceScreen;
		displayLoginByCodeScreen( serviceScreen );
	}
	
	private void confirmLogIn() {		
		//Retrieve data
		String num = txtUserID.getText();
		
		//Validate input
		if(num.length() != Usager.ID_LENGTH) { displayFeedback("Code ne contient pas 9 chiffres !",true,true); return; }
		
		//If reached, input is valid
		Usager usager = gdc.getUserByID(num);
		
		if( usager == null ) {
			displayFeedback("Numéro invalide, usager introuvable.",true,true); return; 
		}
		
		// Branch according to user type and status
		if( usager instanceof Membre ) {
			String statut = ((Membre) usager).getStatutMembre();
			if( statut.equals(Membre.VALID) ) {
				displayFeedback(statut,false,true);
				currentUser = usager;
				
				//Branch to correct screen
				if(serviceFlag)
					displayServiceMenu();
				else
					displayAccountMenu();
				
			}
			else {
				displayFeedback(statut,true,true); return; 
			}
		}
		else {
			displayFeedback("Code valide",false,true);
			currentUser = usager;
			//Branch to correct screen
			if(serviceFlag)
				displayServiceMenu();
			else
				displayAccountMenu();
		}
	}
	
	private void createUser(boolean member) {
		createFlag = true;
		memberFlag = member;
		displayUserCreationScreen(member,false);
	}
	
	private void modifyUser() {
		createFlag = false;		
		displayUserCreationScreen(false,true); 
	}
	
	private void deleteUser() {
		//Show confirmation screen
		int userChoice = JOptionPane.showOptionDialog(getContentPane(), "Êtes vous sur ? ", "Supprimer compte : "+currentUser.getUserID(), 
				JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null, new String[] {"Oui","Non"},null);
		
		if (userChoice == JOptionPane.YES_OPTION) {
			gdc.supprimerCompteMembre((Membre)currentUser);
			currentUser = null;
		    JOptionPane.showMessageDialog(getContentPane(), "Compte supprimé");
		    displayUserMenu();
		} 
	}
	
	private void confirmUserEdit() {
		// Retrieve data
		String prenom = txtPrenom.getText(), 
			   nom = txtNom.getText(), 
			   adresse = txtAdresse.getText(),
			   ville = txtVille.getText(), 
			   codePostal = txtCodePostal.getText(), 
			   provTerr = (String) comboProvTerr.getSelectedItem(),
			   numeroDeTel = "("+txtPhone1.getText()+") "+txtPhone2.getText()+"-"+txtPhone3.getText(),
			   courriel = txtEmail1.getText()+"@"+txtEmail2.getText()+"."+txtEmail3.getText();
		
		
		//Validate Data
		if(prenom.length() == 0) { displayFeedback("Prénom vide !",true,true); return; }
		
		if(nom.length() == 0) { displayFeedback("Nom vide !",true,true); return; }
		
		if(adresse.length() == 0) { displayFeedback("Adresse vide !",true,true); return; }
		
		if(ville.length() == 0) { displayFeedback("Ville vide !",true,true); return; }
		
		if(codePostal.length() != Usager.CODE_POSTAL_LENGTH) { displayFeedback("Code postal invalide !",true,true); return; }
		
		if(numeroDeTel.length() != Usager.NUMTEL_LENGTH) { displayFeedback("Numéro de télephone invalide !",true,true); return; }
		
		if( txtEmail1.getText().length() == 0 || txtEmail2.getText().length() == 0 || txtEmail3.getText().length() == 0) {			
			displayFeedback("Courriel invalide !",true,true); return; 
		}		
		
		if(createFlag) {
			//Only verify dateDeNaissance when creating since it's not editable
			Instant dateDeNaissance = ((Date) dateNaissance.getModel().getValue()).toInstant();
			if( Instant.now().isBefore(dateDeNaissance) ) {
				displayFeedback("Date de naissance dans le futur !",true,true); return; 
			}
			
			//If reached, everything is valid
			if(memberFlag) {
				String newID = gdc.nouveauMembre(prenom, nom, adresse, ville, codePostal, provTerr, numeroDeTel, courriel, dateDeNaissance).getUserID();
				displayFeedback("Membre crée ! Code : "+newID,false,true); 	
			}
			else {
				String newID = gdc.nouveauProfessionnel(prenom, nom, adresse, ville, codePostal, provTerr, numeroDeTel, courriel, dateDeNaissance).getUserID();
				displayFeedback("Professionnel crée ! Code : "+newID,false,true); 	
			}
		}
		else {
			//If reached, everything is valid
			gdc.modifierCompteUsager(currentUser,prenom, nom, adresse, ville, codePostal, provTerr, numeroDeTel, courriel);
			displayFeedback("Membre modifié avec succès",false,true); 	
		}
		
		btnUserCancel.setText("Retour");
		btnUserConfirm.setVisible(false);	
	}
	
	private void createService() {
		createFlag = true;
		displayServiceCreationScreen(false);		
	}
	
	private void modifyService() {
		createFlag = false;
		
		String input = JOptionPane.showInputDialog(getContentPane(), "Entrez le numéro de service");                         
		Service found = rds.findServiceByID((Professionnel)currentUser,input);
		
		if (found == null)
		    JOptionPane.showMessageDialog(getContentPane(), "Service introuvable");
		else {
			currentService = found;
			displayServiceCreationScreen(true);
		}
		   
	}
	
	private void confirmServiceEdit() {
		// Retrieve data
		int capacite = (int) spnCapacite.getValue(),
			heure = (int) spnHeure.getValue(),
			mins = (int) spnMins.getValue();
		float frais = (float) spnPrix.getValue();	
		String commentaire = txtCommentaire.getText();
		String nom = txtServiceName.getText();
		boolean noneChecked = true;
		boolean[] recurrences = new boolean[7];
		for(int i = 0; i < 7; i++) {
			recurrences[i] = checkBoxes[i].isSelected();
			if(recurrences[i] == true) noneChecked = false;
		}
		
		LocalDate startTime = ((Date) dateStart.getModel().getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate endTime   = ((Date) dateEnd.getModel().getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
						
		if(createFlag) {
			//Validate Data		
			if( startTime.isBefore(LocalDate.now())) { displayFeedback("Date de début dans le passé !",true,false); return; }
			if( endTime.isBefore(LocalDate.now())) { displayFeedback("Date de fin dans le passé !",true,false); return; }
			if( endTime.isBefore(startTime) ){ displayFeedback("Date de fin avant date de début !",true,false); return; }
			if( endTime.equals(startTime) ){ displayFeedback("Dates de début et de fin indentiques !",true,false); return; }
			if(noneChecked){ displayFeedback("Horaire vide !",true,false); return; }		
			if( nom.length() == 0 ) { displayFeedback("Nom de Service vide !",true,false); return; }
						
			//If reached, everything is valid
			String newID = rds.newService(nom, startTime, endTime, heure, mins, (Professionnel) currentUser, commentaire, capacite, frais, recurrences).getServiceID();
			displayFeedback("Service crée ! Code : "+newID,false,false);
		}
		else {
			//Validate Data		
			if( nom.length() == 0 ) { displayFeedback("Nom de Service vide !",true,false); return; }			
			rds.modifierService(currentService,nom, heure, mins, commentaire, capacite );
			displayFeedback("Service modifié avec succès ! Code : "+currentService.getServiceID(),false,false);
		}
		
		btnServiceCancel.setText("Retour");
		btnServiceConfirm.setVisible(false);	
	}
	
	private boolean payerFrais() {
		//Show confirmation screen
		float fees = ((Membre) currentUser).getFraisAPayer();
		
		if(fees > 0) {		
			int userChoice = JOptionPane.showOptionDialog(getContentPane(), "Payer frais de : "+fees+" ?", "Frais du compte : "+currentUser.getUserID(), 
					JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null, new String[] {"Oui","Non"},null);
			
			if (userChoice == JOptionPane.YES_OPTION) {
				gdc.paiementDeFrais((Membre) currentUser);				
			    JOptionPane.showMessageDialog(getContentPane(), "Frais payés !");
			    return true;			    
			} 
		}
		else {
			JOptionPane.showMessageDialog(getContentPane(), "Aucun frais à votre compte !");
		}
		
		return false;
	}
	
	private void renouvellerMembre() {
		//Show confirmation screen
		String statut = ((Membre) currentUser).getStatutMembre();
		
		if(statut.equals(Membre.INVALID)) {		
			if( payerFrais() ) {
				gdc.renouvelerAbonnement((Membre)currentUser);
				JOptionPane.showMessageDialog(getContentPane(), "Compte : "+currentUser.getUserID()+" renouvellé !");				
			}
			else
				JOptionPane.showMessageDialog(getContentPane(), "Renouvellement annulé !");	
				
		}
		else {
			JOptionPane.showMessageDialog(getContentPane(), "Membre déja valide !");
		}
	}
	
	private void selectSeance() {
		displaySeanceList();
	}
	
	private void confirmSelection() {
		int selection = tableList.getSelectedRow();
		//Validate data
		if(selection >= 0) { 
			//Show confirmation screen
			int userChoice = JOptionPane.showOptionDialog(getContentPane(), "Êtes vous sur ? ", "S'inscrire à la séance "+listeBuffer.get(selection)[0], 
					JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null, new String[] {"Oui","Non"},null);
			
			if (userChoice == JOptionPane.YES_OPTION) {				
				String[] hook = listeBuffer.get(selection)[3].split("@");
				int seance = Integer.parseInt(hook[0]);
				int service = Integer.parseInt(hook[1]);
				if( rds.inscriptionSeance((Membre) currentUser, seance, service) )				
					JOptionPane.showMessageDialog(getContentPane(), "Inscription réussite !");
				else
					JOptionPane.showMessageDialog(getContentPane(), "Membre déja inscrit !");
					
			    displaySeanceList();
			} 
		}
		else
			JOptionPane.showMessageDialog(getContentPane(), "Aucune selection !");
	}
	
	private void showAttendees() {		
		displayAttendeeList();
	}
	
	private void confirmPresence() {
		String input = JOptionPane.showInputDialog(getContentPane(), "Entrez le numéro de séance");                         
		Seance found = rds.findSeanceByID(input);
		
		if (found == null)
		    JOptionPane.showMessageDialog(getContentPane(), "Séance introuvable !");
		else {
			int userChoice = JOptionPane.showOptionDialog(getContentPane(), "Êtes vous sur ? ", "Confirmation à la séance "+found.getSeanceID(), 
					JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null, new String[] {"Oui","Non"},null);
			
			if (userChoice == JOptionPane.YES_OPTION) {	
				//Si pas inscrit
				if( !found.alreadySubscribed((Membre)currentUser)){
					JOptionPane.showMessageDialog(getContentPane(), "Membre n'est pas inscrit à cette séance !");
					return;
				}
				
				// Si inscrit
				if( rds.confirmerPresence((Membre)currentUser,found) )							
					JOptionPane.showMessageDialog(getContentPane(), "Confirmation réussite !");
				else
					JOptionPane.showMessageDialog(getContentPane(), "Membre déja confirmé !");
					
			    displaySeanceList();
			} 
		}		
	}
	
	private void showAccessCard() {
		
	}
	
	/*******************************************************************************************************
	 * UI METHODS
	 *******************************************************************************************************/
	
	/**
	 * UI Displaying Methods
	 */
	private void displayMainMenu() {
		toggleMenu(MAIN_MENU);
		menuTitle.setText("Centre de Données");
		
		btn1.setText("[APP] Log-in");
		btn2.setText("---");
		btn3.setText("---");
		btn4.setText("[AGENT] Gestion des comptes");
		btn5.setText("[AGENT] Repertoire des Services");
		btn6.setText("[AGENT] Procedure Comptable");
		btn7.setText("Quitter");	
		
		buttonVisibilityCheck();
	}
	
	private void displayUserMenu() {
		//Log out/Reset
		currentUser = null;
		toggleMenu(USER_MENU);
		menuTitle.setText("Gestion des comptes");
		
		btn1.setText("Créer un compte membre");
		btn2.setText("Créer un compte professionnel");
		btn3.setText("---"); 
		btn4.setText("Menu usager");
		btn5.setText("---");
		btn6.setText("---");
		btn7.setText("Retour");		
		
		buttonVisibilityCheck();
	}
	
	private void displayAccountMenu() {
		toggleMenu(ACCOUNT_MENU);
				
		btn1.setText("Modifier compte");		
		if(currentUser instanceof Membre) {
			menuTitle.setText("Membre : "+currentUser.getUserID());
			btn2.setText("Supprimer compte");
			btn3.setText("Payer frais");
			btn4.setText("Renouvellement d'abonnement");
		}
		else {
			menuTitle.setText("Prof. : "+currentUser.getUserID());
			btn2.setText("---");
			btn3.setText("---");
			btn4.setText("---");
		}
		btn5.setText("---");
		btn6.setText("---");
		btn7.setText("Retour");		
		
		buttonVisibilityCheck();
	}
	
	private void app_displayAccountMenu() {
		toggleMenu(MEMBER_APP_MENU);
			
		if(currentUser instanceof Membre) {
			menuTitle.setText("[App] Membre : "+currentUser.getUserID());
			btn1.setText("[APP] Ma carte d'accès");
			btn2.setText("[APP] Payer mes frais");
			btn3.setText("[APP] Modifier mon compte");
			btn4.setText("[APP] Supprimer mon compte");
			btn5.setText("---");
			btn6.setText("[APP] S'inscrire à une séance");
			btn7.setText("Retour");		
		}
		else {
			menuTitle.setText("[App] Prof. : "+currentUser.getUserID());
			btn1.setText("---");
			btn2.setText("---");
			btn3.setText("---");
			btn4.setText("---");
			btn5.setText("[APP]Consulter inscriptions");
			btn6.setText("---");
			btn7.setText("Retour");	
		}
		
		buttonVisibilityCheck();
	}

	private void displayServiceMenu() {
		toggleMenu(SERVICE_MENU);	
		if(currentUser instanceof Membre) {
			menuTitle.setText("Membre : "+currentUser.getUserID());
			btn1.setText("Inscription à une séance");		
			btn2.setText("Confirmer présence");
			btn3.setText("---");
			btn4.setText("---");
			btn5.setText("---");
		}
		else {
			menuTitle.setText("Prof. : "+currentUser.getUserID());
			btn1.setText("---");		
			btn2.setText("---");
			btn3.setText("Créer un service");
			btn4.setText("Modifier un service");
			btn5.setText("Consulter inscriptions");
		}
		
		btn6.setText("---");
		btn7.setText("Retour");		
		
		buttonVisibilityCheck();
	}
	
	private void displayLoginByCodeScreen( boolean serviceScreen ) {
		toggleInput(USER_CODE_INPUT);	
		
		if(serviceScreen)
			inputUserTitle.setText("Demande de");	
		else
			inputUserTitle.setText("Acceder au compte");	
		
		inputVisibilityCheck(false);
	}
	
	private void displayUserCreationScreen( boolean member, boolean modify ) {
		if(!modify)
			toggleInput(CREATE_USER_INPUT);
		else
			toggleInput(MODIFY_USER_INPUT);
		
		if(modify) {
			inputUserTitle.setText("Modifier un compte");	
			//Display user data
			txtPrenom.setText(currentUser.getPrenom());
			txtNom.setText(currentUser.getNom());
			txtAdresse.setText(currentUser.getAdresse());
			txtVille.setText(currentUser.getVille());
			txtCodePostal.setText(currentUser.getCodePostal());
			comboProvTerr.setSelectedItem(currentUser.getProvTerr());
			String num = currentUser.getNumeroDeTel();
			txtPhone1.setText(num.substring(1, 4));
			txtPhone2.setText(num.substring(6, 9));
			txtPhone3.setText(num.substring(10, 14));
			String[] mail1 = currentUser.getEmail().split("@");
			String[] mail2 = mail1[1].split("\\.");
			txtEmail1.setText(mail1[0]);
			txtEmail2.setText(mail2[0]);
			txtEmail3.setText(mail2[1]);
		}
		else if(member ) {
			inputUserTitle.setText("Compte membre");			
		}
		else {
			inputUserTitle.setText("Compte professionnel");
			modify = false;
		}		
		
		inputVisibilityCheck(modify);
	}
	
	private void displayServiceCreationScreen( boolean modify ) {
		if(!modify) {
			toggleInput(CREATE_SERVICE_INPUT);
			inputServiceTitle.setText("Création de service");
			lblProNum.setText("Professionnel : "+currentUser.getUserID());
			
		}
		else {
			toggleInput(MODIFY_SERVICE_INPUT);
			inputServiceTitle.setText("Modification du service : "+currentService.getServiceID());
			//Display service data
			spnPrix.setValue(currentService.getServiceFees());
			spnHeure.setValue(currentService.getServiceHour());
			spnMins.setValue(currentService.getServiceMin());
			spnCapacite.setValue(currentService.getCapacity());			
			txtServiceName.setText(currentService.getNom());
			txtCommentaire.setText(currentService.getComments());
			for(int i =0; i < 7; i++)
				checkBoxes[i].setSelected(currentService.getRecurrences()[i]);
			startModel.setValue(Date.from(currentService.getStartDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
			endModel.setValue(Date.from(currentService.getEndDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		}
		
		inputVisibilityCheck(false);
	}
	
	private void displaySeanceList() {
		toggleInput(SEANCE_LIST);
		changeList(false);
		listeBuffer = rds.listeDesSeancesDispos();
		for(String[] row : listeBuffer) {
			seanceModel.insertRow(row[0], row[1], row[2]);
		}		
	}
	
	private void displayAttendeeList() {
		toggleInput(ATTENDEE_LIST);
		changeList(true);
		listeBuffer = rds.listeDesInscriptions( (Professionnel)currentUser );		
		for(String[] row : listeBuffer) {
			attendeeModel.insertRow(row[0], row[1], row[2]);
		}		
	}
	
	private void displayFeedback(String message, boolean error, boolean account) {
		if(account) {
			if(error) lblAccountFeedback.setForeground(Color.RED);
			else      lblAccountFeedback.setForeground(new Color(0,155,0));
			lblAccountFeedback.setText(message);
		}
		else {
			if(error) lblServiceFeedback.setForeground(Color.RED);
			else      lblServiceFeedback.setForeground(new Color(0,155,0));
			lblServiceFeedback.setText(message);
		}		
	}
		
	/**
	 * UI Internal Methods
	 */
	private void buttonVisibilityCheck() {
		for(int i = 0; i < menuButtons.length; i++) {
			JButton current = menuButtons[i];
			if( current.getText().equals("---") )
				current.setVisible(false);
			else
				current.setVisible(true);
		}
	}
	
	private void inputVisibilityCheck(boolean modify) {
		// Show/Hide UI elements
		if(panelID == USER_CODE_INPUT) {
			for(int i = 0; i < inputLabels.length; i++) {
				inputLabels[i].setVisible(false);
			}
			for(int j = 0; j < inputFields.length; j++) {
				inputFields[j].setVisible(false);
			}
			dateNaissance.setVisible(false);
			lblUserID.setVisible(true);
			txtUserID.setVisible(true);
			comboProvTerr.setVisible(false);
		}
		else {
			for(int i = 0; i < inputLabels.length; i++) {
				inputLabels[i].setVisible(true);
			}
			for(int j = 0; j < inputFields.length; j++) {
				inputFields[j].setVisible(true);
			}
			if(modify) {
				lblDate.setVisible(false);
				dateNaissance.setVisible(false);				
			}
			else {
				lblDate.setVisible(true);
				dateNaissance.setVisible(true);				
			}
			lblUserID.setVisible(false);
			txtUserID.setVisible(false);
			comboProvTerr.setVisible(true);
		}	
		
		if(panelID == MODIFY_SERVICE_INPUT) {
			dateStart.getComponent(1).setEnabled(false);
			dateEnd.getComponent(1).setEnabled(false);			
			spnPrix.setEnabled(false);
			for(int i = 0; i < 7; i++) {
				checkBoxes[i].setEnabled(false);;
			}
		}
		else {
			dateStart.getComponent(1).setEnabled(true);
			dateEnd.getComponent(1).setEnabled(true);			
			spnPrix.setEnabled(true);
			for(int i = 0; i < 7; i++) {
				checkBoxes[i].setEnabled(true);
			}
		}
			
	}
	
	private void resetInput() {
		// Reset input to blank or default values
		lblAccountFeedback.setText("");	
		lblServiceFeedback.setText("");
		btnUserConfirm.setVisible(true);
		btnUserCancel.setText("Annuler");
		btnServiceConfirm.setVisible(true);
		btnServiceCancel.setText("Annuler");
		txtUserID.setText("");
		comboProvTerr.setSelectedIndex(0);
		naissanceModel.setValue(Date.from(Instant.now()));
		startModel.setValue(Date.from(Instant.now()));
		endModel.setValue(Date.from(Instant.now()));
		spnCapacite.setValue(1);
		spnPrix.setValue(new Float(Service.MIN_FEE));
		spnHeure.setValue(0);
		spnMins.setValue(0);
		txtCommentaire.setText("");
		txtServiceName.setText("");
			
		for(int j = 0; j < inputFields.length; j++) {
			inputFields[j].setText("");
		}
		
		for(int i = 0; i < 7; i++) {
			checkBoxes[i].setSelected(false);
		}
	}
	
	private void toggleMenu( int id ) {
		// Check for panel swap
		if(panelID > MENU_PANEL_LIMIT || panelID < 0) {
			getContentPane().removeAll();
			menuPanel.revalidate();
			menuPanel.repaint();
			getContentPane().add(menuPanel);
			getContentPane().revalidate();
			getContentPane().repaint();
		}
		
		panelID = id;
	}
	
	private void toggleInput( int id ) {
		//Reset		
		resetInput();
		
		// Check for panel swap
		if(id > MENU_PANEL_LIMIT && id <= USER_PANEL_LIMIT ) {	
			getContentPane().removeAll();
			userPanel.revalidate();
			userPanel.repaint();
			getContentPane().add(userPanel);
			getContentPane().revalidate();
			getContentPane().repaint();
		}
		else if(id > USER_PANEL_LIMIT && id < SERVICE_PANEL_LIMIT){				
			getContentPane().removeAll();
			servicePanel.revalidate();
			servicePanel.repaint();
			getContentPane().add(servicePanel);	
			getContentPane().revalidate();
			getContentPane().repaint();
		}
		else {
			getContentPane().removeAll();
			listPanel.revalidate();
			listPanel.repaint();
			getContentPane().add(listPanel);	
			getContentPane().revalidate();
			getContentPane().repaint();
		}
		
		panelID = id;
		getContentPane().repaint();		
	}
	
	private void initializeMenuPanel() {
		// Initialize menu panel 
		menuPanel = new JPanel();
		menuPanel.setBounds(220, 50, 300, 360);
		menuPanel.setBorder(new LineBorder(new Color(0, 0, 0), 5));		
		menuPanel.setLayout(null);
		
		menuTitle = new JLabel("Menu Placeholder");
		menuTitle.setBackground(Color.LIGHT_GRAY);
		menuTitle.setHorizontalAlignment(SwingConstants.CENTER);
		menuTitle.setBounds(10, 10, 280, 40);
		menuTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
		menuPanel.add(menuTitle);
		
		btn1 = new JButton("New button");
		btn1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn1.setBounds(30, 60, 240, 30);
		btn1.addActionListener(buttonListener);
		btn1.setFocusPainted(false);
		menuPanel.add(btn1);
		
		btn2 = new JButton("New button");
		btn2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn2.setBounds(30, 101, 240, 30);
		btn2.addActionListener(buttonListener);
		btn2.setFocusPainted(false);
		menuPanel.add(btn2);
		
		btn3 = new JButton("New button");
		btn3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn3.setBounds(30, 142, 240, 30);
		btn3.addActionListener(buttonListener);
		btn3.setFocusPainted(false);
		menuPanel.add(btn3);
		
		btn4 = new JButton("New button");
		btn4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn4.setBounds(30, 183, 240, 30);
		btn4.addActionListener(buttonListener);
		btn4.setFocusPainted(false);
		menuPanel.add(btn4);
		
		btn5 = new JButton("New button");
		btn5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn5.setBounds(30, 224, 240, 30);
		btn5.addActionListener(buttonListener);
		btn5.setFocusPainted(false);
		menuPanel.add(btn5);
		
		btn6 = new JButton("New button");
		btn6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn6.setBounds(30, 265, 240, 30);
		btn6.addActionListener(buttonListener);
		btn6.setFocusPainted(false);
		menuPanel.add(btn6);
		
		btn7 = new JButton("New button");
		btn7.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn7.setBounds(30, 306, 240, 30);
		btn7.addActionListener(buttonListener);
		btn7.setFocusPainted(false);
		menuPanel.add(btn7);
		
		menuButtons = new JButton[]{btn1,btn2,btn3,btn4,btn5,btn6,btn7};
	}
	
	private void initializeUserPanel() {
		// Initialize user panel 
		userPanel = new JPanel();
		userPanel.setBounds(220, 11, 300, 439);
		userPanel.setBorder(new LineBorder(new Color(0, 0, 0), 5));		
		userPanel.setLayout(null);
		
		inputUserTitle = new JLabel("Menu Placeholder");
		inputUserTitle.setBackground(Color.LIGHT_GRAY);
		inputUserTitle.setHorizontalAlignment(SwingConstants.CENTER);
		inputUserTitle.setBounds(10, 10, 280, 40);
		inputUserTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
		userPanel.add(inputUserTitle);
		
		btnUserCancel = new JButton("Annuler");
		btnUserCancel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnUserCancel.setBounds(20, 398, 130, 30);
		btnUserCancel.addActionListener(buttonListener);
		btnUserCancel.setFocusPainted(false);
		btnUserCancel.addActionListener(buttonListener);
		userPanel.add(btnUserCancel);
		
		btnUserConfirm = new JButton("Confirmer");
		btnUserConfirm.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnUserConfirm.setBounds(160, 398, 130, 30);
		btnUserConfirm.setFocusPainted(false);
		btnUserConfirm.addActionListener(buttonListener);
		userPanel.add(btnUserConfirm);		
		
		lblUserID = new JLabel("Numéro d'usager");
		lblUserID.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUserID.setHorizontalAlignment(SwingConstants.LEFT);
		lblUserID.setBounds(20, 61, 102, 21);
		lblUserID.setVisible(false);
		userPanel.add(lblUserID);
		
		txtUserID = new JTextField();	
		txtUserID.setBounds(130, 61, 151, 20);
		txtUserID.setDocument( new JTextFieldFormatter(false,true,false, Usager.ID_LENGTH) );
		txtUserID.setVisible(false);
		userPanel.add(txtUserID);
		txtUserID.setColumns(10);
		
		lblPrenom = new JLabel("Prénom");
		lblPrenom.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPrenom.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrenom.setBounds(20, 61, 102, 21);
		userPanel.add(lblPrenom);
		
		txtPrenom = new JTextField();	
		txtPrenom.setBounds(90, 61, 191, 20);
		txtPrenom.setDocument( new JTextFieldFormatter(false,false,true, Usager.NAME_LENGTH) );
		userPanel.add(txtPrenom);
		txtPrenom.setColumns(10);
		
		lblNom = new JLabel("Nom");
		lblNom.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNom.setHorizontalAlignment(SwingConstants.LEFT);
		lblNom.setBounds(19, 93, 103, 21);
		userPanel.add(lblNom);
		
		txtNom = new JTextField();
		txtNom.setColumns(10);
		txtNom.setBounds(90, 93, 192, 20);
		txtNom.setDocument( new JTextFieldFormatter(false,false,true, Usager.NAME_LENGTH) );
		userPanel.add(txtNom);
		
		lblAdresse = new JLabel("Adresse");
		lblAdresse.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAdresse.setHorizontalAlignment(SwingConstants.LEFT);
		lblAdresse.setBounds(20, 125, 102, 21);
		userPanel.add(lblAdresse);
		
		txtAdresse = new JTextField();
		txtAdresse.setColumns(10);
		txtAdresse.setBounds(90, 125, 191, 20);
		txtAdresse.setDocument( new JTextFieldFormatter(false,true,true, Usager.ADRESSE_LENGTH) );
		userPanel.add(txtAdresse);
		
		lblTelephone = new JLabel("Telephone   (                    )                  -");
		lblTelephone.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTelephone.setHorizontalAlignment(SwingConstants.LEFT);
		lblTelephone.setBounds(19, 253, 259, 21);
		userPanel.add(lblTelephone);
		
		txtPhone1 = new JTextField();
		txtPhone1.setColumns(10);
		txtPhone1.setBounds(98, 253, 46, 20);
		txtPhone1.setDocument( new JTextFieldFormatter(false,true,false, 3) );
		userPanel.add(txtPhone1);
				
		txtPhone2 = new JTextField();
		txtPhone2.setColumns(10);
		txtPhone2.setBounds(160, 253, 46, 20);
		txtPhone2.setDocument( new JTextFieldFormatter(false,true,false, 3) );
		userPanel.add(txtPhone2);
		
		txtPhone3 = new JTextField();
		txtPhone3.setColumns(10);
		txtPhone3.setBounds(220, 253, 60, 20);
		txtPhone3.setDocument( new JTextFieldFormatter(false,true,false, 4) );		
		userPanel.add(txtPhone3);	
		
		lblCourriel = new JLabel("Courriel                                    @                     .");
		lblCourriel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCourriel.setHorizontalAlignment(SwingConstants.LEFT);
		lblCourriel.setBounds(20, 285, 260, 21);
		userPanel.add(lblCourriel);
		
		txtEmail1 = new JTextField();
		txtEmail1.setColumns(10);
		txtEmail1.setBounds(90, 285, 82, 20);
		userPanel.add(txtEmail1);		

		txtEmail2 = new JTextField();
		txtEmail2.setColumns(10);
		txtEmail2.setBounds(182, 285, 60, 20);
		userPanel.add(txtEmail2);
		
		txtEmail3 = new JTextField();
		txtEmail3.setColumns(10);
		txtEmail3.setBounds(251, 285, 28, 20);
		userPanel.add(txtEmail3);		
		
		lblDate = new JLabel("Date de naissance");
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDate.setHorizontalAlignment(SwingConstants.LEFT);
		lblDate.setBounds(20, 317, 113, 21);
		userPanel.add(lblDate);
		
		lblAccountFeedback = new JLabel("");
		lblAccountFeedback.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAccountFeedback.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccountFeedback.setBounds(26, 341, 259, 46);
		userPanel.add(lblAccountFeedback);
				
		lblVille = new JLabel("Ville");
		lblVille.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblVille.setHorizontalAlignment(SwingConstants.LEFT);
		lblVille.setBounds(20, 157, 102, 21);
		userPanel.add(lblVille);
		
		txtVille = new JTextField();
		txtVille.setColumns(10);
		txtVille.setBounds(90, 157, 191, 20);
		txtVille.setDocument( new JTextFieldFormatter(false,false,true, Usager.VILLE_LENGTH) );
		userPanel.add(txtVille);
		
		lblCodePostal = new JLabel("Code Postal");
		lblCodePostal.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCodePostal.setHorizontalAlignment(SwingConstants.LEFT);
		lblCodePostal.setBounds(20, 189, 102, 21);
		userPanel.add(lblCodePostal);
		
		txtCodePostal = new JTextField();
		txtCodePostal.setColumns(10);
		txtCodePostal.setBounds(90, 189, 60, 20);
		txtCodePostal.setDocument( new JTextFieldFormatter(true,true,true, Usager.CODE_POSTAL_LENGTH) );
		userPanel.add(txtCodePostal);
		
		lblProvTerr = new JLabel("Prov/T\u00E9rr");
		lblProvTerr.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblProvTerr.setHorizontalAlignment(SwingConstants.LEFT);
		lblProvTerr.setBounds(20, 221, 60, 21);
		userPanel.add(lblProvTerr);
		
		comboProvTerr = new JComboBox<String>();
		comboProvTerr.setModel(new DefaultComboBoxModel<String>(new String[] {"(AB) Alberta", "(BC) Colombie-Britannique", "(PE) \u00CEle-du-Prince-\u00C9douard", "(MB) Manitoba", "(NB) Nouveau-Brunswick", "(NS) Nouvelle-\u00C9cosse", "(NU) Nunavut", "(ON) Ontario", "(QC) Qu\u00E9bec", "(SK) Saskatchewan", "(NL) Terre-Neuve-et-Labrador", "(NT) Territoires du Nord-Ouest", "(YT) Yukon"}));
		comboProvTerr.setBounds(90, 220, 191, 20);
		userPanel.add(comboProvTerr);
		
		naissanceModel = new UtilDateModel();	
		naissanceModel.setSelected(true);
		Properties dateProperties = new Properties();
		dateProperties.put("text.today", "Aujourd'hui");
		dateProperties.put("text.month", "Mois");
		dateProperties.put("text.year", "Année");
		
		naissancePanel = new JDatePanelImpl(naissanceModel, dateProperties);
		dateNaissance = new JDatePickerImpl(naissancePanel, new DateComponentFormatter());		dateNaissance.setBounds(137, 313, 141, 26);
		
		userPanel.add(dateNaissance);
					
		inputLabels = new JLabel[] {lblPrenom, lblNom, lblAdresse, lblTelephone, lblCourriel, lblDate, lblVille, lblCodePostal,lblProvTerr};
		inputFields = new JTextField[] {txtPrenom, txtNom, txtAdresse, txtPhone1, txtEmail1, txtEmail2, txtEmail3, txtPhone2, txtPhone3,txtVille,txtCodePostal};		
	}

	private void initializeServicePanel() {
		// Initialize service panel 
		servicePanel = new JPanel();
		servicePanel.setBounds(220, 11, 300, 439);
		servicePanel.setBorder(new LineBorder(new Color(0, 0, 0), 5));		
		servicePanel.setLayout(null);
		
		inputServiceTitle = new JLabel("Menu Placeholder");
		inputServiceTitle.setBackground(Color.LIGHT_GRAY);
		inputServiceTitle.setHorizontalAlignment(SwingConstants.CENTER);
		inputServiceTitle.setBounds(6, 6, 280, 30);
		inputServiceTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
		servicePanel.add(inputServiceTitle);
		
		btnServiceCancel = new JButton("Annuler");
		btnServiceCancel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnServiceCancel.setBounds(20, 398, 130, 30);
		btnServiceCancel.addActionListener(buttonListener);
		btnServiceCancel.setFocusPainted(false);
		btnServiceCancel.addActionListener(buttonListener);
		servicePanel.add(btnServiceCancel);
		
		btnServiceConfirm = new JButton("Confirmer");
		btnServiceConfirm.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnServiceConfirm.setBounds(160, 398, 130, 30);
		btnServiceConfirm.setFocusPainted(false);
		btnServiceConfirm.addActionListener(buttonListener);
		servicePanel.add(btnServiceConfirm);		
		
		lblProNum = new JLabel("New label");
		lblProNum.setHorizontalAlignment(SwingConstants.CENTER);
		lblProNum.setBounds(16, 29, 258, 16);
		servicePanel.add(lblProNum);
		
		JLabel lblStartDate = new JLabel("D\u00E9but");
		lblStartDate.setBounds(16, 56, 130, 16);
		servicePanel.add(lblStartDate);
		
		JLabel lblFin = new JLabel("Fin");
		lblFin.setBounds(16, 84, 130, 16);
		servicePanel.add(lblFin);
		
		JLabel lblCapacite = new JLabel("Capacit\u00E9");
		lblCapacite.setBounds(16, 116, 130, 16);
		servicePanel.add(lblCapacite);
		
		JLabel lblPrix = new JLabel("Frais de s\u00E9ance                            $");
		lblPrix.setBounds(16, 150, 258, 16);
		servicePanel.add(lblPrix);
		
		JLabel lblHeure = new JLabel("Heure                                               :");
		lblHeure.setBounds(16, 184, 258, 16);
		servicePanel.add(lblHeure);
		
		JLabel lblHoraire = new JLabel("Horaire");
		lblHoraire.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblHoraire.setHorizontalAlignment(SwingConstants.CENTER);
		lblHoraire.setBounds(16, 208, 130, 16);
		servicePanel.add(lblHoraire);
		
		chckbxLundi = new JCheckBox("Lundi");
		chckbxLundi.setHorizontalAlignment(SwingConstants.LEFT);
		chckbxLundi.setBounds(16, 224, 104, 18);
		servicePanel.add(chckbxLundi);
		
		chckbxMardi = new JCheckBox("Mardi");
		chckbxMardi.setHorizontalAlignment(SwingConstants.LEFT);
		chckbxMardi.setBounds(16, 244, 104, 18);
		servicePanel.add(chckbxMardi);
		
		chckbxMercredi = new JCheckBox("Mercredi");
		chckbxMercredi.setHorizontalAlignment(SwingConstants.LEFT);
		chckbxMercredi.setBounds(16, 264, 104, 18);
		servicePanel.add(chckbxMercredi);
		
		chckbxJeudi = new JCheckBox("Jeudi");
		chckbxJeudi.setHorizontalAlignment(SwingConstants.LEFT);
		chckbxJeudi.setBounds(16, 284, 104, 18);
		servicePanel.add(chckbxJeudi);
		
		chckbxVendredi = new JCheckBox("Vendredi");
		chckbxVendredi.setHorizontalAlignment(SwingConstants.LEFT);
		chckbxVendredi.setBounds(16, 304, 104, 18);
		servicePanel.add(chckbxVendredi);
		
		chckbxSamedi = new JCheckBox("Samedi");
		chckbxSamedi.setHorizontalAlignment(SwingConstants.LEFT);
		chckbxSamedi.setBounds(16, 324, 104, 18);
		servicePanel.add(chckbxSamedi);
		
		chckbxDimanche = new JCheckBox("Dimanche");
		chckbxDimanche.setHorizontalAlignment(SwingConstants.LEFT);
		chckbxDimanche.setBounds(16, 344, 104, 18);
		servicePanel.add(chckbxDimanche);
		
		JLabel lblNom = new JLabel("Nom du service");
		lblNom.setHorizontalAlignment(SwingConstants.CENTER);
		lblNom.setFont(new Font("SansSerif", Font.BOLD, 10));
		lblNom.setBounds(121, 212, 153, 16);
		servicePanel.add(lblNom);
				
		txtServiceName = new JTextField();	
		txtServiceName.setBounds(121, 225, 153, 20);
		txtServiceName.setDocument( new JTextFieldFormatter(false,true,true, Service.NAME_LENGTH) );		
		servicePanel.add(txtServiceName);
		txtServiceName.setColumns(10);
		
		JLabel lblCommentaire = new JLabel("Commentaire (facultatif)");
		lblCommentaire.setHorizontalAlignment(SwingConstants.CENTER);
		lblCommentaire.setFont(new Font("SansSerif", Font.BOLD, 10));
		lblCommentaire.setBounds(121, 246, 153, 16);
		servicePanel.add(lblCommentaire);
		
		txtCommentaire = new JTextArea();
		txtCommentaire.setBounds(121, 264, 153, 98);
		txtCommentaire.setLineWrap(true);
		txtCommentaire.setDocument( new JTextFieldFormatter(false,true,true, Service.COMMENT_LENGTH));
		servicePanel.add(txtCommentaire);
		
		spnCapacite = new JSpinner();
		spnCapacite.setModel(new SpinnerNumberModel(10, 1, Service.MAX_CAPACITY, 1));
		spnCapacite.setBounds(134, 110, 54, 28);
		servicePanel.add(spnCapacite);
		
		spnHeure = new JSpinner();
		spnHeure.setModel(new SpinnerNumberModel(0, 0, 23, 1));
		spnHeure.setBounds(134, 178, 54, 28);
		servicePanel.add(spnHeure);
		
		spnMins = new JSpinner();
		spnMins.setModel(new SpinnerNumberModel(0, 0, 59, 1));
		spnMins.setBounds(197, 178, 54, 28);
		servicePanel.add(spnMins);
		
		spnPrix = new JSpinner();
		spnPrix.setModel(new SpinnerNumberModel(new Float(Service.MIN_FEE), new Float(Service.MIN_FEE), new Float(Service.MAX_FEE), new Float(1)));
		spnPrix.setBounds(134, 144, 54, 28);
		servicePanel.add(spnPrix);
		
		Properties dateProperties = new Properties();
		dateProperties.put("text.today", "Aujourd'hui");
		dateProperties.put("text.month", "Mois");
		dateProperties.put("text.year", "Année");
		
		startModel = new UtilDateModel();	
		startModel.setSelected(true);		
		startDatePanel = new JDatePanelImpl(startModel, dateProperties);
		dateStart = new JDatePickerImpl(startDatePanel, new DateComponentFormatter());
		dateStart.setBounds(133, 46, 141, 26);
		servicePanel.add(dateStart);
		
		endModel = new UtilDateModel();	
		endModel.setSelected(true);		
		endDatePanel = new JDatePanelImpl(endModel, dateProperties);
		dateEnd = new JDatePickerImpl(endDatePanel, new DateComponentFormatter());		
		dateEnd.setBounds(133, 78, 141, 26);
		servicePanel.add(dateEnd);		
		
		lblServiceFeedback = new JLabel("");
		lblServiceFeedback.setHorizontalAlignment(SwingConstants.CENTER);
		lblServiceFeedback.setBounds(20, 365, 254, 30);
		servicePanel.add(lblServiceFeedback);
		
		checkBoxes = new JCheckBox[] {chckbxLundi, chckbxMardi, chckbxMercredi, chckbxJeudi, chckbxVendredi, chckbxSamedi, chckbxDimanche}; 
	}

	private void initializeListPanel() {
		// Initialize list panel 
		listPanel = new JPanel();
		listPanel.setBounds(6, 11, 722, 439);
		listPanel.setBorder(new LineBorder(new Color(0, 0, 0), 5));		
		listPanel.setLayout(null);
		
		listTitle = new JLabel("Menu Placeholder");
		listTitle.setBackground(Color.LIGHT_GRAY);
		listTitle.setHorizontalAlignment(SwingConstants.CENTER);
		listTitle.setBounds(6, 6, 697, 30);
		listTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
		listPanel.add(listTitle);
		
		btnListCancel = new JButton("Annuler");
		btnListCancel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnListCancel.setBounds(205, 398, 130, 30);
		btnListCancel.addActionListener(buttonListener);
		btnListCancel.setFocusPainted(false);		
		listPanel.add(btnListCancel);
		
		btnListConfirm = new JButton("Confirmer");
		btnListConfirm.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnListConfirm.setBounds(399, 398, 130, 30);
		btnListConfirm.setFocusPainted(false);
		btnListConfirm.addActionListener(buttonListener);
		listPanel.add(btnListConfirm);	
			
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(16, 48, 687, 338);
		listPanel.add(scrollPane);
		
		tableList = new JTable();	
		tableList.getTableHeader().setReorderingAllowed(false);		
		tableList.getTableHeader().setResizingAllowed(false);
		scrollPane.setViewportView(tableList);
		
		attendeeModel = new MyTableModel("Séance","Membre","Date [Confirmation]");
		seanceModel = new MyTableModel("Séance","Service","Commentaire");	
	}
	
	private void changeList( boolean attendees ) {		
		if(attendees) {
			attendeeModel.clearRows();
			listTitle.setText("Inscriptions aux séances de : "+currentUser.getUserID());
			tableList.setModel(attendeeModel);			
			tableList.getColumnModel().getColumn(0).setPreferredWidth(220);	
			tableList.getColumnModel().getColumn(1).setPreferredWidth(220);	
			tableList.getColumnModel().getColumn(2).setPreferredWidth(240);		
			btnListConfirm.setVisible(false);
		}
		else {
			seanceModel.clearRows();
			listTitle.setText("Séances disponibles aujourd'hui");
			tableList.setModel(seanceModel);
			tableList.getColumnModel().getColumn(0).setPreferredWidth(60);	
			tableList.getColumnModel().getColumn(1).setPreferredWidth(120);	
			tableList.getColumnModel().getColumn(2).setPreferredWidth(500);
			btnListConfirm.setVisible(true);
		}
	}
}
