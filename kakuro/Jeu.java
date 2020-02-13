package kakuro;

 
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.*;
import javax.swing.border.EmptyBorder;



import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.peer.PanelPeer;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.beans.DesignMode;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
 
 
public class Jeu {
              
              private JPanel panel= new JPanel();
              private JPanel panelButoun1= new JPanel();
              private JPanel panelButoun2= new JPanel();
              private JPanel panelButoun3= new JPanel();
              private JPanel panelButoun4 = new JPanel();
              private JPanel panelButoun5 = new JPanel();
              private JPanel panelButoun6 = new JPanel();
              private JPanel panelButoun7 = new JPanel();
              private JPanel panelButoun8 = new JPanel();
              private JPanel panelButoun9 = new JPanel();
              private JPanel panelConsigne = new JPanel();
              private JFrame frame = new JFrame();
              private JLabel ecoule = new JLabel("Temps écoulée :");
              private JLabel consigne = new JLabel("Consigne : ");
              //private JButton label = new JButton("KAKURO");
              private JButton buttonIndice = new JButton("Indice");
              private JButton buttonValide = new JButton("Valider");
              private JButton buttonQuitter = new JButton("Quitter");
              private JButton buttonSolveur = new JButton("Solveur");
              private JButton buttonImprimer = new JButton("Imprimer");
              private JButton buttonSauvegarder = new JButton("Sauvegarder");
              private JButton buttonClear = new JButton("Clear all");
              private Dessine[][] buttonFacile = new Dessine[8][8];
              //private JFloatField[][] tabText;
              private JPanel panelFinal = new JPanel();
              private Chrono duree = new Chrono();
              
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
             
              
              private GrilleV3 grille;
              private JButton labelPseudo;
              private JPanel panelGagner = new JPanel();
              //private JLabel gagner = new JLabel("Bravo c'est gagné !");
              private JLabel gagner = new JLabel("KAKURO"); //A CHANGER NOM !!!!!!!!!!!!!!!!!!!!!!!!!!
              
              private boolean tricheur = false;
              Thread playWave= new AePlayWave();
              boolean son1=false,son2=false;
              
             
              
              public Jeu(String pseudo,String niveau)
              {
//            	  	panelConsigne.setLocation(50, 50);
//            	  	panelConsigne.setBackground(Color.BLUE);
//            	  	panelConsigne.add(consigne);
//            	  	consigne.setVisible(true);
//            	  	frame.add(panelConsigne);
            	  
            	  if(niveau.equals("Niveau 1") || niveau.equals("Niveau 2")||niveau.equals("Niveau 3"))
            	  {
            		  playWave=null;
            		   playWave=new AePlayWave("./src/kakuro/niveau1.wav");
            		   son1=true;

            	  }
            	  else if(niveau.equals("Niveau 4") || niveau.equals("Niveau 5")||niveau.equals("Niveau 6"))
            	  {
            		  playWave=null;
           		   playWave=new AePlayWave("./src/kakuro/niveau2.wav");
        		   son2=true;

            	  }
            	  else {
            		  playWave=null;
           		   playWave=new AePlayWave("./src/kakuro/niveau3.wav");

            	  }
            	  
            	  	panelFinal.setVisible(true);
            	  	panel.setVisible(true); //ALEX
            	  
            		menuMusique.add(musiqueActive);
            		menuMusique.add(musiqueDesactive);
            	
            		menuAide.add(new PanelDescription());
            		menuDemo.add(new Demo());
            		musiqueActive.setSelected(true);
            		
            		playWave.start();
            		
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
            		
            		frame.setJMenuBar(menuBar);
            	  
            	  
            		class MenuListener implements ActionListener{
            			public void actionPerformed(ActionEvent e) {
            				String action = e.getActionCommand();
            			
            			 if(action.contentEquals("Activée"))
            				{
            					if(musiqueActive.isSelected())
            					{
            						 musiqueDesactive.setSelected(false);
            					}
            					else {
            						musiqueActive.setSelected(true);
            					}
            					
            					if(son1)
            					{
            						playWave = null;
            			        	playWave=new AePlayWave("./src/kakuro/niveau1.wav");
            			        	playWave.start();
            					}
            					else if(son2)
            					{
            						playWave = null;
            			        	playWave=new AePlayWave("./src/kakuro/niveau2.wav");
            			        	playWave.start();
            					}
            					else {
            						playWave = null;
            			        	playWave=new AePlayWave("./src/kakuro/niveau3.wav");
            			        	playWave.start();
            					}
            					 	
            					
            				}
            				else if(action.contentEquals("Désactivée"))
            				{
            					musiqueActive.setSelected(false);
            					((AePlayWave) playWave).arreter();

            				}
            			}
            		}
            	  
            		musiqueActive.addActionListener(new MenuListener());
            		musiqueDesactive.addActionListener(new MenuListener());
            	  
            	  labelPseudo = new JButton(pseudo);
            	  labelPseudo.setPreferredSize(new Dimension(150,50));
                          
                           //label.setPreferredSize(new Dimension(200,50));
//                           for (int i = 0; i < buttonFacile.length; i++) {
//                        	   for (int j = 0; j < buttonFacile.length; j++) {
//                        		   buttonFacile[i][j]= new Dessine("22","35");
//                        		   	
//                        		   
//                        		   buttonFacile[i][j].setPreferredSize(new Dimension(50,40));
//                        		   buttonFacile[i][j].setBackground(Color.pink);
//                        		  
//                        		   panelButoun1.add(buttonFacile[i][j]);
//							}
//                        	   
//						}
                           if (niveau.equals("demo")) {
                        	   grille = new GrilleV3(0);
                        	   panelButoun1 = grille.getPanel();
                        	   panelButoun1.setLocation(250,150);
                        	  // consigne.setVisible(true);
                           }
                           else if (niveau.equals("Niveau 1")){
                        	   grille = new GrilleV3(1);
                          	   panelButoun1 = grille.getPanel();
                          	   panelButoun1.setLocation(190, 100);
                           }
                           else if (niveau.equals("Niveau 2")) {
                        	   grille = new GrilleV3(2);
                          	   panelButoun1 = grille.getPanel();
                          	   panelButoun1.setLocation(180, 100);
                           }else if (niveau.equals("Niveau 3")) {
                        	   grille = new GrilleV3(3);
                          	   panelButoun1 = grille.getPanel();
                          	   panelButoun1.setLocation(180, 100);
                           }else if (niveau.equals("Niveau 4")) {
                        	   grille = new GrilleV3(4);
                          	   panelButoun1 = grille.getPanel();
                          	   panelButoun1.setLocation(140, 100);
                           }else if (niveau.equals("Niveau 5")) {
                        	   grille = new GrilleV3(5);
                          	   panelButoun1 = grille.getPanel();
                          	   panelButoun1.setLocation(140, 100);
                           }else if (niveau.equals("Niveau 6")) {
                        	   grille = new GrilleV3(6);
                          	   panelButoun1 = grille.getPanel();
                          	   panelButoun1.setLocation(140, 100);
                           }else if (niveau.equals("Niveau 7")) {
                        	   grille = new GrilleV3(7);
                          	   panelButoun1 = grille.getPanel();
                          	   panelButoun1.setLocation(140, 100);
                           }else if (niveau.equals("Niveau 8")) {
                        	   grille = new GrilleV3(8);
                          	   panelButoun1 = grille.getPanel();
                          	   panelButoun1.setLocation(40, 100);
                           }else if (niveau.equals("Niveau 9")) {
                        	   grille = new GrilleV3(9);
                          	   panelButoun1 = grille.getPanel();
                          	   panelButoun1.setLocation(40, 100);
                           }else if (niveau.equals("Niveau 10")) {
                        	   grille = new GrilleV3(10);
                          	   panelButoun1 = grille.getPanel();
                          	   panelButoun1.setLocation(80, 100);
                           }else if (niveau.equals("Niveau 11")) {
                        	   grille = new GrilleV3(11);
                          	   panelButoun1 = grille.getPanel();
                          	   panelButoun1.setLocation(150, 150);
                           }
                           
                           buttonSolveur.addActionListener(new UtileListener());
                           buttonIndice.addActionListener(new UtileListener());
                           buttonValide.addActionListener(new UtileListener());
                           buttonQuitter.addActionListener(new UtileListener());
                           buttonImprimer.addActionListener(new Impression1());
                           buttonSauvegarder.addActionListener(new UtileListener());
                           buttonClear.addActionListener(new UtileListener());
                           
                           // TOUTES LES COULEURS
//                           buttonSolveur.setBackground(Color.PINK);
//                           buttonIndice.setBackground(Color.BLACK);
//                           buttonValide.setBackground(Color.GRAY);
//                           buttonQuitter.setBackground(Color.BLUE);
//                           buttonImprimer.setBackground(Color.WHITE);
//                         	 buttonSauvegarder.setBackground(Color.WHITE);
//                           buttonClear.setBackground(Color.WHITE);
//                           
//                           buttonSolveur.setForeground(Color.BLACK);
//                           buttonIndice.setForeground(Color.WHITE);
//                           buttonValide.setForeground(Color.BLACK);
//                           buttonQuitter.setForeground(Color.WHITE);
//                           buttonImprimer.setForeground(Color.BLACK);
//                           buttonSauvegarder.setForeground(Color.BLACK);
//                           buttonClear.setForeground(Color.BLACK);
                           
                           // GRIS
//	                         buttonSolveur.setBackground(Color.GRAY);
//	                         buttonIndice.setBackground(Color.GRAY);
//	                         buttonValide.setBackground(Color.GRAY);
//	                         buttonQuitter.setBackground(Color.GRAY);
//	                         buttonImprimer.setBackground(Color.GRAY);
//                       	 buttonSauvegarder.setBackground(Color.GRAY);
//                           buttonClear.setBackground(Color.GRAY);
	                         
//	                         buttonSolveur.setForeground(Color.BLACK);
//	                         buttonIndice.setForeground(Color.BLACK);
//	                         buttonValide.setForeground(Color.BLACK);
//	                         buttonQuitter.setForeground(Color.BLACK);
//	                         buttonImprimer.setForeground(Color.BLACK);
//                       	 buttonSauvegarder.setForeground(Color.BLACK);
//                         buttonClear.setForeground(Color.BLACK);
                           
//                           buttonSolveur.setForeground(Color.BLUE);
//	                         buttonIndice.setForeground(Color.BLUE);
//	                         buttonValide.setForeground(Color.BLUE);
//	                         buttonQuitter.setForeground(Color.BLUE);
//	                         buttonImprimer.setForeground(Color.BLUE);
//                       buttonSauvegarder.setForeground(Color.BLUE);
//                       buttonClear.setForeground(Color.BLUE);
	                         
	                        // ROSE
//	                         buttonSolveur.setBackground(Color.PINK);
//	                         buttonIndice.setBackground(Color.PINK);
//	                         buttonValide.setBackground(Color.PINK);
//	                         buttonQuitter.setBackground(Color.PINK);
//	                         buttonImprimer.setBackground(Color.PINK);
//                           buttonSauvegarder.setBackground(Color.PINK);
//                           buttonClear.setBackground(Color.PINK);
//	                         
//	                         buttonSolveur.setForeground(Color.BLACK);
//	                         buttonIndice.setForeground(Color.BLACK);
//	                         buttonValide.setForeground(Color.BLACK);
//	                         buttonQuitter.setForeground(Color.BLACK);
//	                         buttonImprimer.setForeground(Color.BLACK);
//                         buttonSauvegarder.setForeground(Color.BLACK);
//                         buttonClear.setForeground(Color.BLACK);
	                         
	                        // NOIR
//	                         buttonSolveur.setBackground(Color.BLACK);
//	                         buttonIndice.setBackground(Color.BLACK);
//	                         buttonValide.setBackground(Color.BLACK);
//	                         buttonQuitter.setBackground(Color.BLACK);
//	                         buttonImprimer.setBackground(Color.BLACK);
//                         buttonSauvegarder.setBackground(Color.BLACK);
//                         buttonClear.setBackground(Color.BLACK);
//	                         
//	                         buttonSolveur.setForeground(Color.WHITE);
//	                         buttonIndice.setForeground(Color.WHITE);
//	                         buttonValide.setForeground(Color.WHITE);
//	                         buttonQuitter.setForeground(Color.WHITE);
//	                         buttonImprimer.setForeground(Color.WHITE);
//                         buttonSauvegarder.setForeground(Color.WHITE);
//                         buttonClear.setForeground(Color.WHITE);
                           
                           // MELANGE ROSE NOIR
                           buttonSolveur.setBackground(Color.PINK);
	                         buttonIndice.setBackground(Color.PINK);
	                         buttonValide.setBackground(Color.PINK);
	                         buttonQuitter.setBackground(Color.BLACK);
	                         buttonImprimer.setBackground(Color.BLACK);
                         buttonSauvegarder.setBackground(Color.BLACK);
                         buttonClear.setBackground(Color.PINK);
	                         
	                         buttonSolveur.setForeground(Color.BLACK);
	                         buttonIndice.setForeground(Color.BLACK);
	                         buttonValide.setForeground(Color.BLACK);
	                         buttonQuitter.setForeground(Color.WHITE);
	                         buttonImprimer.setForeground(Color.WHITE);
                       buttonSauvegarder.setForeground(Color.WHITE);
                       buttonClear.setForeground(Color.BLACK);
	                         
	                        // BLEUE
//	                         buttonSolveur.setBackground(Color.BLUE);
//	                         buttonIndice.setBackground(Color.BLUE);
//	                         buttonValide.setBackground(Color.BLUE);
//	                         buttonQuitter.setBackground(Color.BLUE);
//	                         buttonImprimer.setBackground(Color.BLUE);
//                         buttonSauvegarder.setBackground(Color.BLUE);
//                         buttonClear.setBackground(Color.BLUE);
//	                         
//	                         buttonSolveur.setForeground(Color.WHITE);
//	                         buttonIndice.setForeground(Color.WHITE);
//	                         buttonValide.setForeground(Color.WHITE);
//	                         buttonQuitter.setForeground(Color.WHITE);
//	                         buttonImprimer.setForeground(Color.WHITE);
//                         buttonSauvegarder.setForeground(Color.WHITE);
//                         buttonClear.setForeground(Color.WHITE);
	                         
	                       // BLANC
//                           buttonSolveur.setBackground(Color.WHITE);
//	                         buttonIndice.setBackground(Color.WHITE);
//	                         buttonValide.setBackground(Color.WHITE);
//	                         buttonQuitter.setBackground(Color.WHITE);
//	                         buttonImprimer.setBackground(Color.WHITE);
//                         buttonSauvegarder.setBackground(Color.WHITE);
//                         buttonClear.setBackground(Color.WHITE);
//	                         
//	                         buttonSolveur.setForeground(Color.BLUE);
//	                         buttonIndice.setForeground(Color.BLUE);
//	                         buttonValide.setForeground(Color.BLUE);
//	                         buttonQuitter.setForeground(Color.BLUE);
//	                         buttonImprimer.setForeground(Color.BLUE);
//                         buttonSauvegarder.setForeground(Color.BLUE);
//                         buttonClear.setForeground(Color.BLUE);
                           
                           
                           gagner.setFont(new Font("TimesRoman", Font.ITALIC, 50));
                           gagner.setForeground(Color.GRAY); //RED DEFAULT
                          
                           panelGagner.add(gagner); 
                           panelGagner.setLocation(200,200);
                           panelGagner.setSize(0, 0);
                           panelGagner.setVisible(true);
                           //panelGagner.setSize(400, 90);
                           
                           
                           
                           panelButoun2.add(ecoule);
                           panelButoun3.add(buttonIndice);
                           panelButoun4.add(buttonValide);
                           panelButoun5.add(buttonQuitter);
                           panelButoun6.add(buttonSolveur);
                           panelButoun7.add(buttonImprimer);
                           panelButoun8.add(buttonSauvegarder);
                           panelButoun9.add(buttonClear);
                          
                           
                          // label.setOpaque(false);
                         //  label.setBorderPainted(false);
                          // label.setFocusPainted(false);
                           
                          // label.setBackground(Color.WHITE);
                           
                           labelPseudo.setOpaque(false);
                           labelPseudo.setBorderPainted(false);
                           labelPseudo.setFocusPainted(false);
                           labelPseudo.setBackground(Color.BLUE);
                           labelPseudo.setLayout(null);
                           labelPseudo.setFont(new Font("TimesRoman", Font.BOLD, 40));
                        
                           
                         //  label.setLayout(null);
                           
                           panelButoun2.setLocation(30, 33);
                           panelButoun3.setLocation(240,610);
                           panelButoun4.setLocation(440,610);
                           panelButoun5.setLocation(340,660);
                           panelButoun6.setLocation(340,560);
                           
                           // EN BAS A COTER DE QUITTER
//                           panelButoun7.setLocation(220, 660);
//                           panelButoun8.setLocation(445, 660);
                           
                           // EN DESSOUS DU TEMPS
//                           panelButoun7.setLocation(30, 100);
//                           panelButoun8.setLocation(30, 140);
                           
                           // EN HAUT A DROITE DU TITRE
                           panelButoun7.setLocation(490, 30);
                           panelButoun8.setLocation(600, 30);
                           
                           panelButoun9.setLocation(340,610);
                           panelFinal.setLocation(50,540);
                           
                          duree.setLocation(50,50);
                           
                           panelButoun1.setBackground(Color.BLACK);
                         //  panelButoun2.setBackground(new Color(84,114,174));
                          // panelButoun3.setBackground(new Color(91,114,174));
                          // panelButoun4.setBackground(new Color(91,114,174));
                        // panelButoun6.setBackground(new Color(91,114,174));
                           
                           //label.setFont(new Font("TimesRoman", Font.ITALIC, 40));
                           //label.setVisible(false);
              
                          // panelFinal.add(label);
                           
                           panelFinal.add(labelPseudo);
                           panelFinal.setVisible(true);
                           
                          
                        // panelFinal.setSize(400,50);
                           
//                           panelButoun2.setSize(120,20);
//                           panelButoun3.setSize(70,40);
                           panelButoun2.setSize(110,35);
                           panelButoun3.setSize(90,35);
                           panelButoun4.setSize(90,35);
                           panelButoun5.setSize(90,35);
                           panelButoun6.setSize(90,35);
                           panelButoun7.setSize(110,35);
                           panelButoun8.setSize(110,35);
                           panelButoun9.setSize(90,35);
                           
                           duree.setSize(60,20);
                           
                           
                           
                          
                           frame.add(panelButoun5);
                           frame.add(panelButoun6);
                           frame.add(panelButoun7);
                           frame.add(panelButoun4);
                           frame.add(panelButoun8);
                           frame.add(panelButoun9);
                           frame.add(panelButoun3);
                           frame.add(panelButoun2);
                           frame.add(duree);
                          frame.add(panelButoun1);
                          	frame.add(panelFinal);
                          	frame.add(panelGagner);
                           	
                          	
                           	
                           frame.setSize(750, 770);
                           
                           frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                           frame.setLocationRelativeTo(null);
                           frame.setVisible(true);
                           
                         
                           
                           
                           
                           
              }
              
              public class UtileListener implements ActionListener{
          		
          		public void actionPerformed(ActionEvent e) {
          			String action = e.getActionCommand();
          			Solveur s = new Solveur();
          			if(action.equals("Solveur")) {
          				s.solve(0, grille.getN1(), grille.getN2(), grille.getTabCase(), grille.getTabText(), grille.getListeSomme());
          				tricheur=true;
          			}
          			
          			if(action.contentEquals("Indice")) {
          				grille.clearBorder();
          				s.indice(grille.getN1(), grille.getN2(), 0, 0, grille.getTabCase(), grille.getTabText(),false);
          			}
          			
          			if (action.equals("Valider")) {
          				boolean b = true;
          				if (tricheur) {
          					b = s.verification(grille.getListeSomme(), grille.getTabCase());
          				//	b = s.verificationBis(grille.getListeSomme(), grille.getTabCase(), grille.getN1(), grille.getN2());

          				}
          				else {
          					s.addAllCaseBlanche(grille.getN1(), grille.getN2(), grille.getTabCase(), grille.getTabText());
          					b = s.verificationBis(grille.getListeSomme(), grille.getTabCase(), grille.getN1(), grille.getN2());
          					//b = s.verification(grille.getListeSomme(), grille.getTabCase());
          				}
          				if (b) {
          					//gagner.setVisible(true);
          					panelGagner.setVisible(true);
          					panelGagner.setOpaque(false);
          					//panelValider.setBackground(Color.green);
          					duree.stopChrono();
          					Object[] options = { "Menu"};
          					String t = "<html><font color= #FF0000>FELICITATIONS VOUS AVEZ GAGNE!!!</font>"; //ALEX
          					int j =JOptionPane.showOptionDialog(null, t + " Maintenant, veuillez revenir au menu du jeu", "Félicitation "+labelPseudo.getText(),
          					JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
          					null, options, options[0]);
          					
          					if(!tricheur)
          					{
          						try {
									new MeilleurScore().AlgoScore(labelPseudo.getText(), duree.getHeure(), duree.getMinute(), duree.getSeconde());
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
          					}
          					
          					
          					if(j == 0)
          					{
          						frame.dispose();
                   				duree.arreter();
                   				((AePlayWave) playWave).arreter();
                   				new FenetreMenu();
          					}
          					
          					
          				}
          				else {
          					//System.out.println("DOMMAGE PAS ENCORE GAGNER");
          					grille.clearBorder();
              				s.indice(grille.getN1(), grille.getN2(), 0, 0, grille.getTabCase(), grille.getTabText(),true);

          					
          				}
          				//gagner.setVisible(true);
          				
          			}
          			
          			if (action.equals("Quitter")) {
          				 int i=JOptionPane.showConfirmDialog(null, "Êtes-vous sûr de vouloir quitter?",
                                 "Confirmation",
                                 JOptionPane.YES_NO_OPTION);      
          				 
          				 if(i == 0)
          				 {
          					 
          					frame.dispose();
               				duree.arreter();
               				((AePlayWave) playWave).arreter();
               				new FenetreMenu();
          				 }
          				
          			}
          			
          			
          			
          			if (action.equals("Clear all")) {
          				grille.clearAll();
          				tricheur = false;
          			}
          		}
          	}
              
              
              
//              public class imprimerListener implements ActionListener{
//            	  private void jMenuFichierImprimer_actionPerformed(final ActionEvent e) {
//            		  final JInternalFrame frame = getSelectedFrame();
//            		  if(frame == null) {
//            		    //showErrorMessage("Pas de fenêtre à imprimer." );
//            		    return;
//            		  }//end if 
//            		  final String frameTitle = frame.getTitle();
//            		  PrinterJob printerJob = PrinterJob.getPrinterJob();
//            		  printerJob.setJobName("Printer job for '" + frameTitle + "' window" );
//            		  final PageFormat pf = printerJob.defaultPage();
//            		  final PageFormat pf2 = printerJob.pageDialog(pf);
//            		  if(pf2 != pf) {
//            		    printerJob.setPrintable((Printable) this, pf2);
//            		    if(printerJob.printDialog()) {
//            		      printerJob.print();
//            		    }//end if 
//            		  }//end if 
//            		}//end jMenuFichierImprimer_actionPerformed
//              }
              
              public class Impression1 extends JPanel implements Printable, ActionListener{

            	  private static final double JAVA_DPI = 72.0;
				/** **/

            	//JPanel frameToPrint = panelButoun1;
            	  JFrame  frameToPrint = frame;
            	  
            	  protected void restoreRealDpi(Graphics2D graphics, PageFormat pageFormat){
            		  Rectangle retValue = new Rectangle();
            		  
            		  // Détermine la résolution réelle
            	      Rectangle deviceBounds = graphics.getDeviceConfiguration().getBounds();
            	      double pageWidth72Dpi  = pageFormat.getWidth();
            	      double pageHeight72Dpi = pageFormat.getHeight();
            	      
            	      double widthResolution  = (JAVA_DPI * deviceBounds.getWidth())/pageWidth72Dpi;
            	      double heightResolution = (JAVA_DPI * deviceBounds.getHeight())/pageHeight72Dpi;
            	      
            	      // Détermine les dimensions réelles de la zone imprimable
            	      double realImageableX      = (pageFormat.getImageableX()*widthResolution)/ JAVA_DPI;
            	      double realImageableWidth  = (pageFormat.getImageableWidth()*widthResolution)/ JAVA_DPI;
            	      double realImageableY      = (pageFormat.getImageableY()*heightResolution)/ JAVA_DPI;
            	      double realImageableHeight = (pageFormat.getImageableHeight()*heightResolution)/ JAVA_DPI;
            	      
            	      // Modifie la transformation du contexte graphique
            	      graphics.setTransform(new AffineTransform()); // Passe en résolution réelle
            	      
            	      switch (pageFormat.getOrientation()){
            	         case PageFormat.LANDSCAPE : {
            	            // Les marges retournées par pageFormat prennent en compte la rotation
            	            // Il faut les inverser
            	            double temp = realImageableX;
            	            realImageableX = realImageableY;
            	            realImageableY = temp;
            	            temp = realImageableWidth;
            	            realImageableWidth = realImageableHeight;
            	            realImageableHeight = temp;
            	            
            	            // Effectue la rotation
            	            graphics.rotate(-Math.PI / 2.0);
            	            
            	            // Translation pour s'aligner sur les marges
            	            graphics.translate(-realImageableWidth + realImageableX, realImageableY);
            	            break;
            	         }
            	         case PageFormat.REVERSE_LANDSCAPE : {
            	            // Les marges retournées par pageFormat prennent en compte la rotation
            	            // Il faut les inverser
            	            double temp = realImageableX;
            	            realImageableX = realImageableY;
            	            realImageableY = temp;
            	            temp = realImageableWidth;
            	            realImageableWidth = realImageableHeight;
            	            realImageableHeight = temp;
            	            
            	            // Effectue la rotation
            	            graphics.rotate(Math.PI / 2.0);
            	            // Translation pour s'aligner sur les marges
            	            graphics.translate(realImageableX, realImageableY - realImageableHeight);
            	            break;
            	         }
            	         default : {
            	            // Mode portrait
            	            // Translation pour s'aligner sur les marges
            	            graphics.translate(realImageableX, realImageableY);
            	         }
            	      }
            	      retValue.x      = (int)Math.ceil(realImageableX);
            	      retValue.y      = (int)Math.ceil(realImageableY);
            	      retValue.width  = (int)Math.floor(realImageableWidth);
            	      retValue.height = (int)Math.floor(realImageableHeight);

            	      //return retValue;

            	   }
            	  

            	  public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
            		  
            		restoreRealDpi((Graphics2D)g,pf);

            	    if (page > 0) {
            	      return NO_SUCH_PAGE;
            	    }

            	    Graphics2D g2d = (Graphics2D) g;
            	    g2d.translate(pf.getImageableX(), pf.getImageableY());

            	    frameToPrint.printAll(g);

            	    return PAGE_EXISTS;
            	   }

            	  public void actionPerformed(ActionEvent e) {
            	    PrinterJob job = PrinterJob.getPrinterJob();
            	    job.setPrintable(this);
            	    HashPrintRequestAttributeSet printRequestSet = new HashPrintRequestAttributeSet();
            	      
        	        printRequestSet.add(OrientationRequested.LANDSCAPE);
            	    boolean ok = job.printDialog(printRequestSet);
            	    if (ok) {
            	      try {
            	        job.print();
            	      } catch (PrinterException ex) {
            	     }
            	    }
            	  }

//            	  public Impression1(JPanel f) {
//            	    frameToPrint = f;
//            	  }
            	  
//            	  public Impression1(JFrame f) {
//              	    frameToPrint = f;
//              	  }

            	 }
              
           
              
              public static void main(String[] args) {
                           
                           Jeu niveau = new Jeu("pseudo","Niveau 2");
              }
              
              
              
              
 


}