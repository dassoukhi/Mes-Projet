package kakuro;



import javax.swing.*;



import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.security.Policy;
import java.awt.event.*;
import java.util.*;

public class FenetreMenu {
	private JFrame menuFrame = new JFrame();
	private JPanel menuPanel = new JPanel();
	private JPanel menuPanel1 = new JPanel();
	private JPanel menuPanel2 = new JPanel();
	private JPanel menuPanel3 = new JPanel();
	private JPanel menuPanel4 = new JPanel();
	private JPanel menuPanel5 = new JPanel();
	
	// Jules
	private JPanel menuPanel6 = new JPanel();
	
	private JPanel panelCaseNom = new JPanel();
	
	private JButton label = new JButton("KAKURO");
	private JLabel labelNom = new JLabel("Entrez votre pseudo");
	private JButton buttonEntre = new JButton("Entrer");
	private JTextField caseNom = new JTextField();
	private JButton newGame = new JButton("Nouvelle Partie");
	private JButton creerGrille = new JButton("Creer Votre Grille");
	private JButton enArriere = new JButton("Back");
	private JButton exit = new JButton("Exit");
	
	// Jules
	private JButton demo = new JButton("Demo");
	
	private JButton labelNiveau = new JButton("Niveau du jeu");
	  private JButton buttonFacile = new JButton("Facile");
      private JButton buttonMoyen = new JButton("Moyen");
      private JButton buttonDiff = new JButton("Difficile");
      private JButton buttonArr = new JButton("Back");
      
      // JULES
      private JButton buttonFacile1 = new JButton("Niveau 1");
      private JButton buttonFacile2 = new JButton("Niveau 2");
      private JButton buttonFacile3 = new JButton("Niveau 3");
      private JButton backFacile = new JButton("Back Facile");
      
      private JButton buttonMoyen4 = new JButton("Niveau 4");
      private JButton buttonMoyen5 = new JButton("Niveau 5");
      private JButton buttonMoyen6 = new JButton("Niveau 6");
      private JButton buttonMoyen7 = new JButton("Niveau 7");
      private JButton backMoyen = new JButton("Back Moyen");
      
      private JButton buttonDifficile8 = new JButton("Niveau 8");
      private JButton buttonDifficile9 = new JButton("Niveau 9");
      private JButton buttonDifficile10 = new JButton("Niveau 10");
      private JButton backDifficile = new JButton("Back Difficile");
      
      private JMenuBar menuBar = new JMenuBar();
      private JMenu menuScore = new JMenu("Score");
      private JMenu menuAide = new JMenu("Aide");
      private JMenu menuMusique = new JMenu("Musique");
      private JMenu menuDemo = new JMenu("Démo");
      private JRadioButtonMenuItem musiqueActive = new JRadioButtonMenuItem("Activée");
      private JRadioButtonMenuItem musiqueDesactive = new JRadioButtonMenuItem("Désactivée");
      
      private JMenuItem joueur1 =new JMenuItem("1 -");
      private JMenuItem joueur2 =new JMenuItem("2 -");
      private JMenuItem joueur3 =new JMenuItem("3 -");
      private JMenuItem joueur4 =new JMenuItem("4 -");
      private JMenuItem joueur5 =new JMenuItem("5 -");
      
      
      @SuppressWarnings("unused")
  	private Thread playWave=new AePlayWave("./src/kakuro/instru.wav");
    		  
      

	
	
	public FenetreMenu() {
		
		enArriere.setPreferredSize(new Dimension(100,50));
		newGame.setPreferredSize(new Dimension(200,50));
		creerGrille.setPreferredSize(new Dimension(200,50));
		exit.setPreferredSize(new Dimension(200,50));
		label.setPreferredSize(new Dimension(200,50));
		
		// Jules
		demo.setPreferredSize(new Dimension(200,50));
		
		 buttonFacile.setPreferredSize(new Dimension(200,50));
         buttonMoyen.setPreferredSize(new Dimension(200,50));
         buttonDiff.setPreferredSize(new Dimension(200,50));
         buttonArr.setPreferredSize(new Dimension(100,50));
         
         // JULES
         buttonFacile1.setPreferredSize(new Dimension(200,50));
         buttonFacile2.setPreferredSize(new Dimension(200,50));
         buttonFacile3.setPreferredSize(new Dimension(200,50));
         
         buttonMoyen4.setPreferredSize(new Dimension(200,50));
         buttonMoyen5.setPreferredSize(new Dimension(200,50));
         buttonMoyen6.setPreferredSize(new Dimension(200,50));
         buttonMoyen7.setPreferredSize(new Dimension(200,50));
         
         buttonDifficile8.setPreferredSize(new Dimension(200,50));
         buttonDifficile9.setPreferredSize(new Dimension(200,50));
         buttonDifficile10.setPreferredSize(new Dimension(200,50));
         
         backFacile.setPreferredSize(new Dimension(200,50));
         backMoyen.setPreferredSize(new Dimension(200,50));
         backDifficile.setPreferredSize(new Dimension(200,50));
         
         buttonFacile.setBackground(Color.WHITE);
         buttonMoyen.setBackground(Color.WHITE);
         buttonDiff.setBackground(Color.WHITE);
         buttonArr.setBackground(Color.WHITE);
         
         buttonFacile1.setBackground(Color.WHITE);
         buttonFacile2.setBackground(Color.WHITE);
         buttonFacile3.setBackground(Color.WHITE);
         
         buttonMoyen4.setBackground(Color.WHITE);
         buttonMoyen5.setBackground(Color.WHITE);
         buttonMoyen6.setBackground(Color.WHITE);
         buttonMoyen7.setBackground(Color.WHITE);
         
         buttonDifficile8.setBackground(Color.WHITE);
         buttonDifficile9.setBackground(Color.WHITE);
         buttonDifficile10.setBackground(Color.WHITE);
         
         backFacile.setBackground(Color.WHITE);
         backMoyen.setBackground(Color.WHITE);
         backDifficile.setBackground(Color.WHITE);
         
         
         buttonFacile.setVisible(false);
         buttonMoyen.setVisible(false);
         buttonDiff.setVisible(false);
         buttonArr.setVisible(false);
         
         buttonFacile1.setVisible(false);
         buttonFacile2.setVisible(false);
         buttonFacile3.setVisible(false);
         
         buttonMoyen4.setVisible(false);
         buttonMoyen5.setVisible(false);
         buttonMoyen6.setVisible(false);
         buttonMoyen7.setVisible(false);
         
         buttonDifficile8.setVisible(false);
         buttonDifficile9.setVisible(false);
         buttonDifficile10.setVisible(false);
         
         backFacile.setVisible(false);
         backMoyen.setVisible(false);
         backDifficile.setVisible(false);
		
		label.setOpaque(false);
		label.setBorderPainted(false);
		label.setFocusPainted(false);
		label.setBackground(Color.WHITE);
		label.setLayout(null);
		label.setFont(new Font("TimesRoman", Font.ITALIC, 30));
		labelNom.setFont(new Font("TimesRoman", Font.ITALIC, 20));
		
		  labelNiveau.setPreferredSize(new Dimension(200,50));
          labelNiveau.setOpaque(false);
          labelNiveau.setBorderPainted(false);
          labelNiveau.setFocusPainted(false);
          labelNiveau.setLayout(null);
          labelNiveau.setBackground(Color.WHITE);
          labelNiveau.setFont(new Font("TimesRoman", Font.ITALIC, 30));
          labelNiveau.setVisible(false);
		
		buttonEntre.setBackground(Color.white);
		buttonEntre.addActionListener(new entrerListener());
	
		
		menuPanel1.setSize(200,60);
		menuPanel2.setSize(200,60);
		menuPanel3.setSize(200,60);
		menuPanel4.setSize(100,60);
		menuPanel5.setSize(200, 40);
		panelCaseNom.setSize(200, 40);
		
		// Jules
		menuPanel6.setSize(200,60);
		
		panelCaseNom.setSize(200, 40);
		
		menuPanel1.setLocation(140, 180);
		menuPanel2.setLocation(140, 260);
		menuPanel3.setLocation(140, 340);
		menuPanel4.setLocation(195, 400);
		panelCaseNom.setLocation(140,170);
		menuPanel5.setLocation(140,220);
		
		// Jules
		menuPanel6.setLocation(140,100);
		
		menuPanel6.add(buttonFacile);
		
		// JULES
		menuPanel6.add(buttonFacile1);
		menuPanel6.add(buttonMoyen4);
		menuPanel6.add(buttonDifficile8);
		
		menuPanel1.add(buttonFacile2);
		menuPanel1.add(buttonMoyen5);
		menuPanel1.add(buttonDifficile9);
		
		menuPanel2.add(buttonFacile3);
		menuPanel2.add(buttonMoyen6);
		//menuPanel2.add(buttonMoyen7);
		menuPanel2.add(buttonDifficile10);
		
		menuPanel4.add(backFacile);
		menuPanel4.add(backMoyen);
		menuPanel4.add(backDifficile);
		
		
		menuPanel.add(label);
		menuPanel.add(labelNiveau);
		
		menuPanel1.add(newGame);
		menuPanel1.add(buttonMoyen);
		
		panelCaseNom.add(caseNom); panelCaseNom.setVisible(false);
		
		menuPanel2.add(creerGrille);
		menuPanel2.add(buttonDiff);
		
		
		menuPanel3.add(exit);
		//menuPanel3.add(buttonFacile);
		
		menuPanel4.add(enArriere);
		menuPanel4.setVisible(false);
		
		menuPanel5.add(buttonEntre); menuPanel5.setVisible(false);
		
		menuPanel1.setBackground(new Color(91,114,174));
		menuPanel2.setBackground(new Color(91,114,174));
		menuPanel3.setBackground(new Color(91,114,174));
		menuPanel4.setBackground(new Color(91,114,174));
		panelCaseNom.setBackground(new Color(91,114,174));
		//menuPanel5.setBackground(new Color(91,114,174));
		
		// Jules
		menuPanel6.setBackground(new Color(91,114,174));
		menuPanel6.add(demo);
		
		
		newGame.setBackground(Color.WHITE);
		creerGrille.setBackground(Color.WHITE);
		exit.setBackground(Color.WHITE);
		demo.setBackground(Color.WHITE);
		enArriere.setBackground(Color.WHITE);
		
		newGame.addActionListener(new MenuListener());
		creerGrille.addActionListener(new MenuListener());
		exit.addActionListener(new MenuListener());
		enArriere.addActionListener(new MenuListener());
		musiqueActive.addActionListener(new MenuListener());
		musiqueDesactive.addActionListener(new MenuListener());
		buttonFacile.addActionListener(new NiveauListener());
		buttonMoyen.addActionListener(new NiveauListener());
		buttonDiff.addActionListener(new NiveauListener());
		
		// Jules
		demo.addActionListener(new MenuListener());
		
		buttonFacile1.addActionListener(new NiveauListener());
		buttonFacile2.addActionListener(new NiveauListener());
		buttonFacile3.addActionListener(new NiveauListener());
		buttonMoyen4.addActionListener(new NiveauListener());
		buttonMoyen5.addActionListener(new NiveauListener());
		buttonMoyen6.addActionListener(new NiveauListener());
		buttonMoyen7.addActionListener(new NiveauListener());
		buttonDifficile8.addActionListener(new NiveauListener());
		buttonDifficile9.addActionListener(new NiveauListener());
		buttonDifficile10.addActionListener(new NiveauListener());
		backFacile.addActionListener(new NiveauListener());
		backMoyen.addActionListener(new NiveauListener());
		backDifficile.addActionListener(new NiveauListener());
		
		musiqueActive.setSelected(true);

		menuFrame.setSize(500, 550);
		menuFrame.add(menuPanel1);
		menuFrame.add(panelCaseNom);
		
		// Jules
		menuFrame.add(menuPanel6);
		
		menuFrame.add(menuPanel5);
		menuFrame.add(menuPanel2);
		menuFrame.add(menuPanel3);
		menuFrame.add(menuPanel4);
		menuFrame.add(menuPanel);
		
		// Jules
		
		
		menuMusique.add(musiqueActive);
		menuMusique.add(musiqueDesactive);
	
		menuAide.add(new PanelDescription());
		menuDemo.add(new Demo());
	
		try {
 			new MeilleurScore().MajLesItem(joueur1, joueur2, joueur3, joueur4, joueur5);
 		} catch (FileNotFoundException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
 		
 		menuScore.add(joueur1);
 		menuScore.add(joueur2);
 		menuScore.add(joueur3);
 		menuScore.add(joueur4);
 		menuScore.add(joueur5);
 		
	
		menuBar.add(menuScore);
		//menuBar.add(menuDemo);
		menuBar.add(menuAide);
		menuBar.add(menuMusique);
		
		  playWave.start();
		
		menuFrame.setJMenuBar(menuBar);
		
		menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuFrame.setLocationRelativeTo(null);
		menuFrame.setVisible(true);
		
	}
	public class MenuListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String action = e.getActionCommand();
			if(action.equals("Nouvelle Partie")) {
				
				newGame.setVisible(false);
				labelNom.setVisible(true);
				
				caseNom.setPreferredSize(new Dimension(170,30));
				menuPanel6.add(labelNom);
				menuPanel1.setSize(200, 40);
			
				panelCaseNom.setVisible(true);
				
				
				
				menuPanel2.setVisible(false);
				menuPanel5.setVisible(true);
				//menuPanel6.setBackground(null);
				//menuPanel1.setBackground(null);
				
				// Jules
				demo.setVisible(false);
				menuPanel6.setVisible(true);
				menuPanel6.setBackground(null);
				
				
				
			exit.setText("Retour");
	 
			}
			else if(action.equals("Creer Votre Grille")){
				
				((AePlayWave) playWave).arreter();
				Interaction inter = new Interaction();
				menuFrame.dispose(); 
				
			}
			else if(action.equals("Exit")){
				((AePlayWave) playWave).arreter();
				menuFrame.dispose(); 
			}
			else if(action.equals("Retour"))
			{
				newGame.setVisible(true);
				labelNom.setVisible(false);
				menuPanel1.setSize(200,60);
				panelCaseNom.setVisible(false);

				menuPanel2.setVisible(true);
				menuPanel5.setVisible(false);
				menuPanel1.setBackground(new Color(91,114,174));
				
				// Jules
				menuPanel6.setVisible(true);
				demo.setVisible(true);
				menuPanel6.setBackground(new Color(91,114,174));
				
				exit.setText("Exit");
			}
			else if(action.contentEquals("Back"))
			{
				newGame.setVisible(false);
				creerGrille.setVisible(true);
				exit.setVisible(true);
				menuPanel5.setVisible(true);
				panelCaseNom.setVisible(true);
				label.setVisible(true);
				
				menuPanel4.setVisible(false);
				
				menuPanel2.setVisible(false);
				labelNiveau.setVisible(false);
				buttonFacile.setVisible(false);
				buttonMoyen.setVisible(false);
				buttonDiff.setVisible(false);
				
				menuPanel1.setBackground(null);
				caseNom.setPreferredSize(new Dimension(170,30));
			
				
				menuPanel1.setSize(200, 40);
				menuPanel3.setVisible(true);
				labelNom.setVisible(true);
				menuPanel6.setBackground(null);
				//JULES
				//menuPanel6.setVisible(false);
				menuPanel3.setVisible(true);
				
				
				
			}
			 else if(action.contentEquals("Activée"))
				{
					if(musiqueActive.isSelected())
					{
						 musiqueDesactive.setSelected(false);
					}
					else {
						musiqueActive.setSelected(true);
					}
					 
					playWave = null;
		        	playWave=new AePlayWave("./src/kakuro/instru.wav");
		        	playWave.start();
					
				}
				else if(action.contentEquals("Désactivée"))
				{
					musiqueActive.setSelected(false);
					((AePlayWave) playWave).arreter();
				}
			else if(action.contentEquals("Demo")) {
				Jeu demo = new Jeu(caseNom.getText(),"demo");
				menuFrame.dispose();
			}
		}
	}
	public class entrerListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
		
			Object ob = e.getSource();
			
			if(ob.equals(buttonEntre) && !caseNom.getText().equals(""))
			{
				newGame.setVisible(false);
				creerGrille.setVisible(false);
				exit.setVisible(false);
				menuPanel5.setVisible(false);
				panelCaseNom.setVisible(false);
				label.setVisible(false);
				menuPanel3.setVisible(false);
				
				menuPanel4.setVisible(true);
				
				menuPanel6.setVisible(true);
				demo.setVisible(false);
				labelNom.setVisible(false);
				//menuPanel5.setVisible(false);
				
				menuPanel2.setVisible(true);
				labelNiveau.setVisible(true);
				buttonFacile.setVisible(true);
				buttonMoyen.setVisible(true);
				buttonDiff.setVisible(true);
				
				menuPanel1.setBackground(new Color(91,114,174));
				menuPanel1.setSize(200,60);
				
				menuPanel6.setBackground(new Color(91,114,174));
				
				
			}
			
		}
		
	}
	
public class NiveauListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			String action = e.getActionCommand();
			if(action.equals("Back")) {
				menuFrame.dispose(); 
				((AePlayWave) playWave).arreter();
				FenetreMenu menu = new FenetreMenu();
			
			}else if(action.equals("Back Facile")) {
				buttonFacile1.setVisible(false);
				buttonFacile2.setVisible(false);
				buttonFacile3.setVisible(false);
				backFacile.setVisible(false);
				
				buttonFacile.setVisible(true);
				buttonMoyen.setVisible(true);
				buttonDiff.setVisible(true);
				enArriere.setVisible(true);
			}else if(action.equals("Back Moyen")) {
				buttonMoyen4.setVisible(false);
				buttonMoyen5.setVisible(false);
				buttonMoyen6.setVisible(false);
				//buttonMoyen7.setVisible(false);
				backMoyen.setVisible(false);
				
				buttonFacile.setVisible(true);
				buttonMoyen.setVisible(true);
				buttonDiff.setVisible(true);
				enArriere.setVisible(true);
			}else if(action.equals("Back Difficile")) {
				buttonDifficile8.setVisible(false);
				buttonDifficile9.setVisible(false);
				buttonDifficile10.setVisible(false);
				backDifficile.setVisible(false);
				
				buttonFacile.setVisible(true);
				buttonMoyen.setVisible(true);
				buttonDiff.setVisible(true);
				enArriere.setVisible(true);
			}else if(action.equals("Facile")) {
				
				buttonFacile.setVisible(false);
				buttonMoyen.setVisible(false);
				buttonDiff.setVisible(false);
				enArriere.setVisible(false);
		
				buttonFacile1.setVisible(true);
				buttonFacile2.setVisible(true);
				buttonFacile3.setVisible(true);
				backFacile.setVisible(true);
			}else if(action.equals("Moyen")) {
				
				buttonFacile.setVisible(false);
				buttonMoyen.setVisible(false);
				buttonDiff.setVisible(false);
				enArriere.setVisible(false);
		
				buttonMoyen4.setVisible(true);
				buttonMoyen5.setVisible(true);
				buttonMoyen6.setVisible(true);
				//buttonMoyen7.setVisible(true);
				backMoyen.setVisible(true);
			}else if(action.equals("Difficile")) {
				buttonFacile.setVisible(false);
				buttonMoyen.setVisible(false);
				buttonDiff.setVisible(false);
				enArriere.setVisible(false);
		
				buttonDifficile8.setVisible(true);
				buttonDifficile9.setVisible(true);
				buttonDifficile10.setVisible(true);
				backDifficile.setVisible(true);
			}else if(action.equals("Niveau 1")) {
				Jeu facile1 = new Jeu(caseNom.getText(),"Niveau 1");
				((AePlayWave) playWave).arreter();
				menuFrame.dispose();
			}else if(action.equals("Niveau 2")) {
				Jeu facile2 = new Jeu(caseNom.getText(),"Niveau 2");
				((AePlayWave) playWave).arreter();
				menuFrame.dispose();
			}else if(action.equals("Niveau 3")) {
				Jeu facile3 = new Jeu(caseNom.getText(),"Niveau 3");
				((AePlayWave) playWave).arreter();
				menuFrame.dispose();
			}else if(action.equals("Niveau 4")) {
				Jeu moyen4 = new Jeu(caseNom.getText(),"Niveau 4");
				((AePlayWave) playWave).arreter();
				menuFrame.dispose();
			}else if(action.equals("Niveau 5")) {
				Jeu moyen5 = new Jeu(caseNom.getText(),"Niveau 5");
				menuFrame.dispose();
				((AePlayWave) playWave).arreter();
			}else if(action.equals("Niveau 6")) {
				Jeu moyen6 = new Jeu(caseNom.getText(),"Niveau 6");
				menuFrame.dispose();
				((AePlayWave) playWave).arreter();
			}else if(action.equals("Niveau 7")) {
				Jeu moyen7 = new Jeu(caseNom.getText(),"Niveau 7");
				menuFrame.dispose();
				((AePlayWave) playWave).arreter();
			}else if(action.equals("Niveau 8")) {
				Jeu difficile8 = new Jeu(caseNom.getText(),"Niveau 8");
				menuFrame.dispose();
				((AePlayWave) playWave).arreter();
			}else if(action.equals("Niveau 9")) {
				Jeu difficile9 = new Jeu(caseNom.getText(),"Niveau 9");
				menuFrame.dispose();
				((AePlayWave) playWave).arreter();
			}else if(action.equals("Niveau 10")) {
				Jeu difficile10 = new Jeu(caseNom.getText(),"Niveau 10");
				menuFrame.dispose();
				((AePlayWave) playWave).arreter();
			}
		}
	}
			
	public static void main( String[] args){		
		FenetreMenu menu = new FenetreMenu();
		
	}
	
}