package kakuro;


import java.awt.BorderLayout;

//IMPORT INTERFACE GRAPHIQUE
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import java.awt.event.ActionEvent;
import java.awt.event.*;
import javax.swing.text.*;



//IMPORT ALGO
//import java.util.LinkedList;
import java.util.List;
//import java.util.ListIterator;
import java.util.ArrayList;

// TEST GRAPHIQUE
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GrilleV3 {
	private int n1,n2;
	private Case[][] tabCase;
	private ArrayList<Pair<Integer,Integer>> listeCoordCase = new ArrayList<>();
	private ArrayList<Somme> listeSomme = new ArrayList<>();
	public Random rdm= new Random();
	
	// TEST GRAPHIQUE
	private Dessine[][] tabButon ;
	private JFloatField[][] tabText;
	private JFrame frame=new JFrame();
	private JPanel panel = new JPanel();
	private Dimension dimoi = new Dimension(50,40);
	
	public JPanel getPanel() {
		return panel;
	}
	
	public Case[][] getTabCase() {
		return tabCase;
	}
	
	public ArrayList<Somme> getListeSomme() {
		return listeSomme;
	}
	
	public ArrayList<Pair<Integer,Integer>> getListeCoordCase () {
		return listeCoordCase;
	}
	
	public int getN1() {
		return n1;
	}
	
	public int getN2() {
		return n2;
	}
	
	public Dessine[][] getTabButon() {
		return tabButon;
	}
	
	public JFloatField[][] getTabText() {
		return tabText;
	}
	
	public void setN1(int n) {
		n1 = n;
	}
	
	public void setN2(int n) {
		n2 = n;
	}
	
	public void initTab(int n, int M) {
		tabCase = new Case[n][M];
//		for (int i=0; i<n; i++) {
//			for (int j=0; j<M; j++) {
//				//tabCase[i][j] = null;
//			}
//		}
	}
	
	public void initCaseNoire() {
		Combinaison c = new Combinaison();
		Somme sommeNull1 = new Somme();
		Somme sommeNull2 = new Somme();
		ArrayList<Pair<Integer,Integer>> listePairNull = new ArrayList<>();
		Solveur s = new Solveur();
		
		// Case noire vide
		tabCase[0][0] = new CaseNoire(0,0,sommeNull1,sommeNull2);
		listeCoordCase.add(new Pair<Integer,Integer>(0,0));
		tabCase[0][3] = new CaseNoire(0,3,sommeNull1,sommeNull2);
		listeCoordCase.add(new Pair<Integer,Integer>(0,3));
		tabCase[0][4] = new CaseNoire(0,4,sommeNull1,sommeNull2);
		listeCoordCase.add(new Pair<Integer,Integer>(0,4));
		tabCase[1][3] = new CaseNoire(1,3,sommeNull1,sommeNull2);
		listeCoordCase.add(new Pair<Integer,Integer>(1,3));
		tabCase[3][7] = new CaseNoire(3,7,sommeNull1,sommeNull2);
		listeCoordCase.add(new Pair<Integer,Integer>(3,7));
		tabCase[4][0] = new CaseNoire(4,0,sommeNull1,sommeNull2);
		listeCoordCase.add(new Pair<Integer,Integer>(4,0));
		tabCase[5][0] = new CaseNoire(5,0,sommeNull1,sommeNull2);
		listeCoordCase.add(new Pair<Integer,Integer>(5,0));
		tabCase[7][4] = new CaseNoire(7,4,sommeNull1,sommeNull2);
		listeCoordCase.add(new Pair<Integer,Integer>(7,4));
		
		// Case noire avec somme
		// Somme 1
		ArrayList<Pair<Integer,Integer>> listePair1 = s.getListePair(0,1,3,true);
		
		Somme somme1 = new Somme(23,true,listePair1,c.combinaisonsPossibles(23, listePair1.size()));
		
		tabCase[0][1] = new CaseNoire(0 , 1, new Somme(), somme1);
		listeSomme.add(somme1);
		listeCoordCase.add(new Pair<Integer,Integer>(0,1));
		
		// Somme 2
		ArrayList<Pair<Integer,Integer>> listePair2 = s.getListePair(0,2,4,true);
        
		Somme somme2 = new Somme(30,true,listePair2,c.combinaisonsPossibles(30, listePair2.size()));
		
		tabCase[0][2] = new CaseNoire(0 , 2, new Somme(), somme2);
		listeSomme.add(somme2);
		listeCoordCase.add(new Pair<Integer,Integer>(0,2));
		
		// Somme 3
		ArrayList<Pair<Integer,Integer>> listePair3 = s.getListePair(0,5,5,true);
        
		Somme somme3 = new Somme(27,true,listePair3,c.combinaisonsPossibles(27, listePair3.size()));
		
		tabCase[0][5] = new CaseNoire(0 , 5, new Somme(), somme3);
		listeSomme.add(somme3);
		listeCoordCase.add(new Pair<Integer,Integer>(0,5));
		
		// Somme 4
		ArrayList<Pair<Integer,Integer>> listePair4 = s.getListePair(0,6,2,true);
        
		Somme somme4 = new Somme(12,true,listePair4,c.combinaisonsPossibles(12, listePair4.size()));
		
		tabCase[0][6] = new CaseNoire(0 , 6, new Somme(), somme4);
		listeSomme.add(somme4);
		listeCoordCase.add(new Pair<Integer,Integer>(0,6));
		
		// Somme 5
		ArrayList<Pair<Integer,Integer>> listePair5 = s.getListePair(0,7,2,true);
        
		Somme somme5 = new Somme(16,true,listePair5,c.combinaisonsPossibles(16, listePair5.size()));
		
		tabCase[0][7] = new CaseNoire(0 , 7,new Somme(), somme5);
		listeSomme.add(somme5);
		listeCoordCase.add(new Pair<Integer,Integer>(0,7));
		
		// Somme 6
		ArrayList<Pair<Integer,Integer>> listePair6 = s.getListePair(1,0,2,false);
        
		Somme somme6 = new Somme(16,false,listePair6,c.combinaisonsPossibles(16, listePair6.size()));
		
		tabCase[1][0] = new CaseNoire(1 , 0, somme6, new Somme());
		listeSomme.add(somme6);
		listeCoordCase.add(new Pair<Integer,Integer>(1,0));
		
		// Somme 7
		ArrayList<Pair<Integer,Integer>> listePair7 = s.getListePair(1,4,2,true);
        
		Somme somme7 = new Somme(17,true,listePair7,c.combinaisonsPossibles(17, listePair7.size()));
		
		// Somme 8
		ArrayList<Pair<Integer,Integer>> listePair8 = s.getListePair(1,4,3,false);
        
		Somme somme8 = new Somme(24,false,listePair8,c.combinaisonsPossibles(24, listePair8.size()));
		
		tabCase[1][4] = new CaseNoire(1 , 4, somme8, somme7);
		listeSomme.add(somme7);
		listeSomme.add(somme8);
		listeCoordCase.add(new Pair<Integer,Integer>(1,4));
		
		// Somme 9
		ArrayList<Pair<Integer,Integer>> listePair9 = s.getListePair(2,0,2,false);
        
		Somme somme9 = new Somme(17,false,listePair9,c.combinaisonsPossibles(17, listePair9.size()));
		
		tabCase[2][0] = new CaseNoire(2 , 0, somme9, new Somme());
		listeSomme.add(somme9);
		listeCoordCase.add(new Pair<Integer,Integer>(2,0));
		
		// Somme 10
		ArrayList<Pair<Integer,Integer>> listePair10 = s.getListePair(2,3,5,true);
        
		Somme somme10 = new Somme(15,true,listePair10,c.combinaisonsPossibles(15, listePair10.size()));
		
		// Somme 11
		ArrayList<Pair<Integer,Integer>> listePair11 = s.getListePair(2,3,4,false);
        
		Somme somme11 = new Somme(29,false,listePair11,c.combinaisonsPossibles(29, listePair11.size()));
		
		tabCase[2][3] = new CaseNoire(2 , 3, somme11, somme10);
		listeSomme.add(somme10);
		listeSomme.add(somme11);
		listeCoordCase.add(new Pair<Integer,Integer>(2,3));
		
		// Somme 12
		ArrayList<Pair<Integer,Integer>> listePair12 = s.getListePair(3,0,5,false);
        
		Somme somme12 = new Somme(35,false,listePair12,c.combinaisonsPossibles(35, listePair12.size()));
		
		tabCase[3][0] = new CaseNoire(3 , 0, somme12, new Somme());
		listeSomme.add(somme12);
		listeCoordCase.add(new Pair<Integer,Integer>(3,0));
		
		// Somme 13
		ArrayList<Pair<Integer,Integer>> listePair13 = s.getListePair(3,6,4,true);
        
		Somme somme13 = new Somme(12,true,listePair13,c.combinaisonsPossibles(12, listePair13.size()));
		
		tabCase[3][6] = new CaseNoire(3 , 6, new Somme(), somme13);
		listeSomme.add(somme13);
		listeCoordCase.add(new Pair<Integer,Integer>(3,6));
		
		// Somme 14
		ArrayList<Pair<Integer,Integer>> listePair14 = s.getListePair(4,1,2,false);
        
		Somme somme14 = new Somme(7,false,listePair14,c.combinaisonsPossibles(7, listePair14.size()));
		
		tabCase[4][1] = new CaseNoire(4 , 1, somme14, new Somme());
		listeSomme.add(somme14);
		listeCoordCase.add(new Pair<Integer,Integer>(4,1));
		
		// Somme 15
		ArrayList<Pair<Integer,Integer>> listePair15 = s.getListePair(4,4,2,true);
        
		Somme somme15 = new Somme(7,true,listePair15,c.combinaisonsPossibles(7, listePair15.size()));
		
		// Somme 16
		ArrayList<Pair<Integer,Integer>> listePair16 = s.getListePair(4,4,2,false);
        
		Somme somme16 = new Somme(8,false,listePair16,c.combinaisonsPossibles(8, listePair16.size()));
		
		tabCase[4][4] = new CaseNoire(4 , 4, somme16, somme15);
		listeSomme.add(somme15);
		listeSomme.add(somme16);
		listeCoordCase.add(new Pair<Integer,Integer>(4,4));
		
		// Somme 17
		ArrayList<Pair<Integer,Integer>> listePair17 = s.getListePair(4,7,3,true);
        
		Somme somme17 = new Somme(7,true,listePair17,c.combinaisonsPossibles(7, listePair17.size()));
		
		tabCase[4][7] = new CaseNoire(4 , 7, new Somme(), somme17);
		listeSomme.add(somme17);
		listeCoordCase.add(new Pair<Integer,Integer>(4,7));
		
		// Somme 18
		ArrayList<Pair<Integer,Integer>> listePair18 = s.getListePair(5,1,2,true);
        
		Somme somme18 = new Somme(11,true,listePair18,c.combinaisonsPossibles(11, listePair18.size()));
		
		tabCase[5][1] = new CaseNoire(5 , 1, new Somme(), somme18);
		listeSomme.add(somme18);
		listeCoordCase.add(new Pair<Integer,Integer>(5,1));
		
		// Somme 19
		ArrayList<Pair<Integer,Integer>> listePair19 = s.getListePair(5,2,2,true);
        
		Somme somme19 = new Somme(10,true,listePair19,c.combinaisonsPossibles(10, listePair19.size()));
		
		// Somme 20
		ArrayList<Pair<Integer,Integer>> listePair20 = s.getListePair(5,2,5,false);
        
		Somme somme20 = new Somme(16,false,listePair20,c.combinaisonsPossibles(16, listePair20.size()));
		
		tabCase[5][2] = new CaseNoire(5 , 2, somme20, somme19);
		listeSomme.add(somme19);
		listeSomme.add(somme20);
		listeCoordCase.add(new Pair<Integer,Integer>(5,2));
		
		// Somme 21
		ArrayList<Pair<Integer,Integer>> listePair21 = s.getListePair(6,0,4,false);
        
		Somme somme21 = new Somme(21,false,listePair21,c.combinaisonsPossibles(21, listePair21.size()));
		
		tabCase[6][0] = new CaseNoire(6 , 0, somme21, new Somme());
		listeSomme.add(somme21);
		listeCoordCase.add(new Pair<Integer,Integer>(6,0));
		
		// Somme 22
		ArrayList<Pair<Integer,Integer>> listePair22 = s.getListePair(6,5,2,false);
        
		Somme somme22 = new Somme(5,true,listePair22,c.combinaisonsPossibles(5, listePair22.size()));
		
		tabCase[6][5] = new CaseNoire(6 , 5, somme22, new Somme());
		listeSomme.add(somme22);
		listeCoordCase.add(new Pair<Integer,Integer>(6,5));
		
		// Somme 23
		ArrayList<Pair<Integer,Integer>> listePair23 = s.getListePair(7,0,3,false);
        
		Somme somme23 = new Somme(6,false,listePair23,c.combinaisonsPossibles(6, listePair23.size()));
		
		tabCase[7][0] = new CaseNoire(7 , 0, somme23, new Somme());
		listeSomme.add(somme23);
		listeCoordCase.add(new Pair<Integer,Integer>(7,0));
		
		// Somme 24
		ArrayList<Pair<Integer,Integer>> listePair24 = s.getListePair(7,5,2,false);
        
		Somme somme24 = new Somme(3,false,listePair24,c.combinaisonsPossibles(3, listePair24.size()));
		
		tabCase[7][5] = new CaseNoire(7 , 5, somme24, new Somme());
		listeSomme.add(somme24);
		listeCoordCase.add(new Pair<Integer,Integer>(7,5));
	}
	
	
	
	public void initCaseBlanche() {
		Solveur s = new Solveur();
		Pair<Integer,Integer> p = new Pair<Integer,Integer>(0,0);
		for (int i=0; i<8; i++) {
			for (int j=0; j<8; j++) {
				p.setLeft(i);
				p.setRight(j);
				if (!listeCoordCase.contains(p)) {
//					tabCase[i][j] = null;
					tabCase[i][j] = new CaseBlanche(i,j,0,s.listePossibilite(i,j,listeSomme));
				}
			}
		}
	}
	
	public void initListeSomme() {
		listeCoordCase.add(new Pair<Integer,Integer>(0,0));
		listeCoordCase.add(new Pair<Integer,Integer>(0,3));
		listeCoordCase.add(new Pair<Integer,Integer>(0,4));
		listeCoordCase.add(new Pair<Integer,Integer>(1,3));
		listeCoordCase.add(new Pair<Integer,Integer>(3,7));
		listeCoordCase.add(new Pair<Integer,Integer>(4,0));
		listeCoordCase.add(new Pair<Integer,Integer>(5,0));
		listeCoordCase.add(new Pair<Integer,Integer>(7,4));
		
		for (int i=0; i<n1; i++) {
			for (int j=0; j<n2; j++) {
				if ((tabCase[i][j].getType()).equals("noire")) {
					Somme s1 = ((CaseNoire)tabCase[i][j]).getSommeDroite();
					Somme s2 = ((CaseNoire)tabCase[i][j]).getSommeBas();
					
					if (!(s2.getSomme() == 0)) {
						listeSomme.add(s1);
					}
					if (!(s1.getSomme() == 0))  {
						listeSomme.add(s2);
					}
					
					if (!(listeCoordCase.contains(new Pair<Integer,Integer>(i,j)))) {
						listeCoordCase.add(new Pair<Integer,Integer>(i,j));
					}
				}
			}
		}
	}
	
	public void afficheGrille() {
		for (int i=0; i<8; i++) {
			for (int j=0; j<8; j++) {
				if (tabCase[i][j] != null) {
					System.out.println("|" + tabCase[i][j].toString() + "|");
				}
			}
			System.out.println(" ");
		}
	}

	public void graphiqueToAlgo() {
		Combinaison c = new Combinaison();
		Interaction interaction  = new Interaction();
		int sommeDroite = 0,sommeBas = 0;
		int nbrCasesDroite = 0, nbrCasesBas = 0;
		Solveur s = new Solveur();
		for (int i=0; i<n1; i++) {
			for (int j=0; j<n2; j++) {
				if (tabButon[i][j].isVisible()) {
					if (!((tabButon[i][j].getSomX()).equals(""))) {
						sommeDroite = Integer.valueOf(tabButon[i][j].getX());
					}
					else {
						sommeDroite = 0;
					}
					if (!((tabButon[i][j].getSomY()).equals(""))) {
						sommeBas = Integer.valueOf(tabButon[i][j].getY());
					}
					else {
						sommeBas = 0;
					}
					nbrCasesDroite = interaction.nbrCaseX(i,j);
					nbrCasesBas = interaction.nbrCaseY(i,j);

					ArrayList<Pair<Integer,Integer>> listePairDroite = s.getListePair(i,j,nbrCasesDroite,false);
					ArrayList<Pair<Integer,Integer>> listePairBas = s.getListePair(i,j,nbrCasesBas,true);

					ArrayList<ArrayList<Integer>> listePermutationDroite = c.combinaisonsPossibles(sommeDroite, listePairDroite.size());
					ArrayList<ArrayList<Integer>> listePermutationBas = c.combinaisonsPossibles(sommeBas, listePairBas.size());

					Somme sommeDroiteS = new Somme();
					Somme sommeBasS = new Somme();
					if (sommeBas != 0) {
						sommeBasS = new Somme(sommeBas,true,listePairBas,listePermutationBas);
						listeSomme.add(sommeBasS);
					}
					
					if (sommeDroite != 0) {
						sommeDroiteS = new Somme(sommeDroite,false,listePairDroite,listePermutationDroite);
						listeSomme.add(sommeDroiteS);
					}

					tabCase[i][j] = new CaseNoire(i,j,sommeDroiteS,sommeBasS);
					listeCoordCase.add(new Pair<Integer,Integer>(i,j));
				}
				else {
					tabCase[i][j] = new CaseBlanche(i,j,0,s.listePossibilite(i,j,listeSomme));
					listeCoordCase.add(new Pair<Integer,Integer>(i,j));
				}
			}
		}
	}

	public void algoToGraphique() {
		//initButon(n1,n2);
		//initTabText(n1, n2);
		tabButon = new Dessine[n1][n2];
		tabText = new JFloatField[n1][n2];

		for (int i=0; i<n1; i++) {
			for (int j=0; j<n2; j++) {
				if ((tabCase[i][j].getType()).equals("noire")) {
					//System.out.println(i + " " + j + "noire");
					String sommeBas = null;
					CaseNoire cn1 = (CaseNoire)tabCase[i][j];
					int sB = (cn1.getSommeBas()).getSomme();
					if (sB == 0) {
						sommeBas = "";
					}
					else {
						sommeBas = String.valueOf(sB);
					}
					String sommeDroite = null;
					CaseNoire cn2 = (CaseNoire)tabCase[i][j];
					int sD = (cn2.getSommeDroite()).getSomme();
					if (sD == 0) {
						sommeDroite = "";
					}
					else {
						sommeDroite = String.valueOf(sD);
					}

					tabButon[i][j]=new Dessine("","");
		    		tabButon[i][j].setPreferredSize(dimoi);
		    		
					if (sD != 0 || sB != 0) {
						tabButon[i][j].setXY(sommeDroite,sommeBas);
						tabButon[i][j].setBackground(Color.pink);
						//tabButon[i][j].setText(null);
						tabButon[i][j].setVisible(true);
					}
					else {
						tabButon[i][j].setBackground(Color.black);
						tabButon[i][j].setVisible(true);
					}
					panel.add(tabButon[i][j]);
				}
				else /*if ((tabCase[i][j].getType()).equals("blanche"))*/ {
					//System.out.println(i + " " + j + "blanche");
					tabText[i][j]=new JFloatField();
					tabText[i][j].setPreferredSize(new Dimension(50,40));
					tabText[i][j].setVisible(true);
					panel.add(tabText[i][j]);
					//tabButon[i][j].setVisible(false);
				}
			}
		}
	}

	public void	initTabText(int n1, int n2) {
		tabText = new JFloatField[n1][n2];
	
		for (int i = 0;  i < n1; i++) {
			for (int j = 0; j < n2; j++) {
				tabText[i][j]=new JFloatField();
				tabText[i][j].setPreferredSize(new Dimension(50,40));
				tabText[i][j].setVisible(false);
				panel.add(tabText[i][j]);
			}
		}
	}
	
	public void refresh() {
		for (int i=0; i<n1; i++) {
			for (int j=0; j<n2; j++) {
				tabButon[i][j].setText("");
			}
		}
	}

	public void initButon(int n1, int n2) {
		tabButon = new Dessine[n1][n2];
		for (int i = 0; i < n1; i++) {
	    	for (int j = 0; j < n2; j++) {
	    		tabButon[i][j]=new Dessine("","");
	    		tabButon[i][j].setPreferredSize(dimoi);
	    		tabButon[i][j].setVisible(false);
	    		//tabButon[i][j].addActionListener(new BouttonListener());
	    		panel.add(tabButon[i][j]);	
			}	
		}
	}
	
	public void createCaseNoire(int i, int j, int sommeDroite, int nbrCaseIncluDroit, int sommeBas, int nbrCaseIncluBas) {
		Combinaison c = new Combinaison();
		
		Somme sommeDroiteS = new Somme();
		Somme sommeBasS = new Somme();
		
		Solveur s = new Solveur();
		
		if (sommeDroite != 0) {
			ArrayList<Pair<Integer,Integer>> listePair = s.getListePair(i,j,nbrCaseIncluDroit,false);
			
			sommeDroiteS = new Somme(sommeDroite,false,listePair,c.combinaisonsPossibles(sommeDroite, listePair.size()));
		}
		
		if (sommeBas != 0) {
			ArrayList<Pair<Integer,Integer>> listePair = s.getListePair(i,j,nbrCaseIncluBas,true);
			
			sommeBasS = new Somme(sommeBas,true,listePair,c.combinaisonsPossibles(sommeBas, listePair.size()));
		}
		
		tabCase[i][j] = new CaseNoire(i , j, sommeBasS, sommeDroiteS);
		listeSomme.add(sommeBasS);
		listeSomme.add(sommeDroiteS);
		listeCoordCase.add(new Pair<Integer,Integer>(i,j));
	}
	
	public void createCaseBlanche(int i, int j) {
		Solveur s = new Solveur();
		tabCase[i][j] = new CaseBlanche(i , j, 0, s.listePossibilite(i,j,listeSomme));
	}
	
	public void recupValeur() {
		for (int i=0; i<n1; i++) {
			for (int j=0; j<n2; j++) {
				if ((tabCase[i][j].getType()).equals("blanche")) {
					int val = Integer.valueOf(tabText[i][j].getText());
					((CaseBlanche)tabCase[i][j]).setValeur(val);
				}
			}
		}
	}
	
	public void clearAll() {
		for (int i=0; i<n1; i++) {
			for (int j=0; j<n2; j++) {
				if ((tabCase[i][j].getType()).equals("blanche")) {
					((CaseBlanche)tabCase[i][j]).setValeur(0);
					tabText[i][j].setText(null);
				}
			}
		}
	}
	
	public void clearBorder() {
		for (int i=0; i<n1; i++) {
			for (int j=0; j<n2; j++) {
				if ((tabCase[i][j].getType()).equals("blanche")) {
					tabText[i][j].setBorder(null);
				}
			}
		}
	}
	
	public GrilleV3() {
		setN1(8);
		setN2(8);
		initTab(8,8);
		initCaseNoire();
		
		//initListeSomme();
		initCaseBlanche();
		algoToGraphique();
	}
	
	public GrilleV3(boolean b) {
		setN1(8);
		setN2(8);
		initTab(8,8);
		initCaseNoire();
		
		//initListeSomme();
		initCaseBlanche();
		//afficheGrille();
		algoToGraphique();
		
		
		
		frame.setSize((50*n1+60),(40*n2+100));
		panel.setSize((50*n1+10),(40*n2+80));
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BorderLayout());
		frame.add(panel,BorderLayout.NORTH);
		frame.setContentPane(panel);
		frame.setVisible(false);
	}
	
	public GrilleV3(int numlvl) {
		
		Niveau n = new Niveau(numlvl);
		n.initialiseNiveau(numlvl);
		int xdim = n.getX();
		int ydim = n.getY();
		initTab(xdim, ydim);
		tabCase=n.parse();
		listeSomme=n.getSom();
		listeCoordCase=n.getlstccs();
		setN1(xdim);
		setN2(ydim);

		
		algoToGraphique();
		
		if (n1>n2){
			frame.setSize((40*n1+60),(50*n2+30));
			panel.setSize((40*n1+60),(50*n2+30));
		}
		else if (n1<n2){
			frame.setSize((70*n1+60),(30*n2+60));
			panel.setSize((70*n1+60),(30*n2+60));
		} 
		else{
			frame.setSize((50*n1+60),(40*n2+50));
			panel.setSize((50*n1+60),(40*n2+50));
		}
		//panel.setSize((50*n1+200),(40*n2+80));
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BorderLayout());
		frame.add(panel,BorderLayout.NORTH);
		frame.setContentPane(panel);
		frame.setVisible(false);
	}

	public static void main(String[] args) {
		GrilleV3 grille = new GrilleV3(1);
		new Solveur().solve(0, grille.getN1(), grille.getN2(), grille.getTabCase(), grille.getTabText(), grille.getListeSomme());
	}
}