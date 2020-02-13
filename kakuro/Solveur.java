package kakuro;



import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class Solveur {
	public Random rdm= new Random();
	
	public boolean checkSolution(Somme s, Case[][] tabCase) {
		boolean b = false,b2 = true,b3 = false;
		for (ArrayList<Integer> permu : s.getPermutation()) {
			b = false; 
			b2 = true;
			for (Pair<Integer,Integer> pair : s.getCaseImp()) {
				int i = pair.getLeft();
				int j = pair.getRight();
				for (int elem : permu ) {
					if (tabCase[i][j].getType() == "blanche") {
						if (((((CaseBlanche)tabCase[i][j]).getValeur()) == elem) || ((((CaseBlanche)tabCase[i][j]).getValeur()) == 0)) {
							b = true;
						}
					}
				}
				if (!b) {
					b2 = false;
				}
			}
			if (b2) {
				b3 = true;
			}
		}
		return b3;
	}
	
	public boolean repetition(Somme som, Case[][] tabCase) {
		boolean b = true;

		if (som.getDirection() == false) {
			for (Pair<Integer,Integer> pair : som.getCaseImp()) {
				int i = pair.getLeft();
				int j = pair.getRight();
				for (Pair<Integer,Integer> pair2 : som.getCaseImp()) {
					int k = pair2.getLeft();
					int l = pair2.getRight();
					if (((CaseBlanche)tabCase[i][j]).getValeur() == 0 || ((CaseBlanche)tabCase[k][l]).getValeur() == 0) {
						return true;
					}
					if ((k == i && l != j) && (((CaseBlanche)tabCase[i][j]).getValeur() == ((CaseBlanche)tabCase[k][l]).getValeur())) {
						b = false;
					}
					
				}
			}
		}
		else {
			for (Pair<Integer,Integer> pair : som.getCaseImp()) {
				int i = pair.getLeft();
				int j = pair.getRight();
				for (Pair<Integer,Integer> pair2 : som.getCaseImp()) {
					int k = pair2.getLeft();
					int l = pair2.getRight();
					if (((CaseBlanche)tabCase[i][j]).getValeur() == 0 || ((CaseBlanche)tabCase[k][l]).getValeur() == 0) {
						return true;
					}
					if ((k != i && l == j) && (((CaseBlanche)tabCase[i][j]).getValeur() == ((CaseBlanche)tabCase[k][l]).getValeur())) {
						b = false;
					}
					
				}
			}
		}
		return b;
	}

	public boolean verification(ArrayList<Somme> listeSomme, Case[][] tabCase ) {
    	for (Somme s : listeSomme) {
    		if (!repetition(s,tabCase) || !checkSolution(s,tabCase) || !estEgaleSomme(s, tabCase)) {
    			return false;
    		}
    	}
    	return true;
	}
	
	public boolean verificationBis(ArrayList<Somme> listeSomme, Case[][] tabCase, int n1, int n2) {
    	for (Somme s : listeSomme) {
    		if (!zero(listeSomme,tabCase,n1,n2) || !repetition(s,tabCase) || !checkSolution(s,tabCase) || !estEgaleSomme(s, tabCase)) {
    			return false;
    		}
    	}
    	return true;
	}
	
	public boolean zero(ArrayList<Somme> listeSomme, Case[][] tabCase, int n1, int n2) {
		for (int i=0; i<n1; i++) {
			for (int j=0; j<n2; j++) {
				if ((tabCase[i][j].getType()).equals("blanche")) {
					if (((CaseBlanche)tabCase[i][j]).getValeur() == 0) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public boolean croisementSomme(int i, int j, ArrayList<Somme> listeSomme, Case[][] tabCase) {
		int valTest = -1;
		int valTest2 = -2;
		for (Somme s : listeSomme) {
			if (s.caseAppartient(i, j)) {
				for (ArrayList<Integer> permu : s.getPermutation()) {
					for (int elem : permu) {
						if (elem == ((CaseBlanche)tabCase[i][j]).getValeur()) {
							if (valTest == -1) {
								valTest = elem;
							}
							else {
								valTest2 = elem;
							}
						}
					}
				}
			}
		}
		return valTest == valTest2;
	}
	
	public boolean estEgaleSomme(Somme s, Case[][] tabCase) {
		int count = 0;
		for (Pair<Integer,Integer> pair : s.getCaseImp()) {
			int i = pair.getLeft();
			int j = pair.getRight();
			count += ((CaseBlanche)tabCase[i][j]).getValeur();
			if (((CaseBlanche)tabCase[i][j]).getValeur() == 0) {
				return true;
			}
		}
		if (count == s.getSomme()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void addCaseBlanche(int i, int j, int val, Case[][] tabCase, JFloatField[][] tabText) {
		if ((tabCase[i][j].getType()).equals("blanche")) {
			((CaseBlanche)tabCase[i][j]).setValeur(val);
			String s = String.valueOf(val);
			tabText[i][j].setText(s);
		}
	}
	
	public boolean solve(int indice, int n1, int n2, Case[][] tabCase, JFloatField[][] tabText, ArrayList<Somme> listeSomme) {
		if (indice == n1*n2) {
			return true;
			
		}

	    int i = indice/n2, j = indice%n2;

	    if ((tabCase[i][j].getType()).equals("noire") || ((CaseBlanche)tabCase[i][j]).getValeur() != 0) {
	        return solve(indice+1, n1, n2, tabCase, tabText, listeSomme);
	    }

	    for (int elem : (((CaseBlanche)tabCase[i][j]).getPossibilite())) {
	    	
	    	addCaseBlanche(i,j,elem, tabCase, tabText);
	    	
	        if (verification(listeSomme, tabCase)) {
	        	//addCaseBlanche(i,j,elem);

	            if ( solve (indice+1, n1, n2, tabCase, tabText, listeSomme) ) {
	                return true;
	            }
	        }
	   }
	    addCaseBlanche(i,j,0,tabCase, tabText);

	    return false;
	}
	
	public void indice(int n1, int n2, int x, int y, Case[][] tabCase, JFloatField[][] tabText, boolean erreur) {
		int i = rdm.nextInt(n1);
		int j = rdm.nextInt(n2);
		
		if ((tabCase[i][j].getType()).equals("blanche")) {
			boolean b = false;
			for (Integer val : ((CaseBlanche)tabCase[i][j]).getPossibilite()) {
				if ((((CaseBlanche)tabCase[i][j]).getValeur()) == val) {
					b = true;
				}
			}
			
			if (!b) {
				//tabText[i][j].setBackground(Color.RED);
				tabText[i][j].setBorder(new LineBorder(Color.red,1));
			}
			else if (b && !erreur) {
				//tabText[i][j].setBackground(Color.GREEN);
				tabText[i][j].setBorder(new LineBorder(Color.green,1));
			}
		}
		else {
			indice(n1,n2,i,j, tabCase, tabText,erreur);
		}
	}
	
	public ArrayList<Pair<Integer,Integer>> getListePair(int i, int j, int nbrCases, boolean direction) {
		ArrayList<Pair<Integer,Integer>> listePair = new ArrayList<>();
		if (!direction) {
			int indiceFin = j + nbrCases;
			j++;
			while (j <= indiceFin) {
				Pair<Integer,Integer> p = new Pair<Integer,Integer>(i,j);
				listePair.add(p);
				j++;
			}
		}
		else {
			int indiceFin = i + nbrCases;
			i++;
			while (i <= indiceFin) {
				Pair<Integer,Integer> p = new Pair<Integer,Integer>(i,j);
				listePair.add(p);
				i++;
			}
		}
		return listePair;
	}
	
	public void addAllCaseBlanche( int n1, int n2, Case[][] tabCase, JFloatField[][] tabText) {
		for (int i=0; i<n1; i++) {
			for (int j=0; j<n2; j++) {
				if (tabCase[i][j].getType().contentEquals("blanche")) {
					if (tabText[i][j].getText().equals("")) {
						((CaseBlanche) tabCase[i][j]).setValeur(0);
					}
					else {
						((CaseBlanche) tabCase[i][j]).setValeur(Integer.valueOf(tabText[i][j].getText()));
					}
				}
			}
		}
	}
	
	public ArrayList<Integer> listePossibilite(int i, int j, ArrayList<Somme> listeSomme) {
		ArrayList<Integer> listePossib = new ArrayList<>();
		ArrayList<Integer> listePossib2 = new ArrayList<>();
		ArrayList<Integer> listePossibFinal = new ArrayList<>();
		int count = 0;
		
		for (Somme s : listeSomme) {
			if (s.caseAppartient(i, j)) {
				count++;
				if (listePossib.isEmpty()) {
					for (ArrayList<Integer> permu : s.getPermutation()) {
						for (int elem : permu) {
							if (!(listePossib.contains(elem))) {
								listePossib.add(elem);
							}
						}
					}
				}
				else {
					for (ArrayList<Integer> permu : s.getPermutation()) {
						for (int elem : permu) {
							if (!(listePossib2.contains(elem))) {
								listePossib2.add(elem);
							}
						}
					}
				}
			}
		}
		if (count > 1) {
			for (Integer e : listePossib) {
				if (listePossib2.contains(e)) {
					listePossibFinal.add(e);
				}
			}
			
			return listePossibFinal;
		}
		else {
			return listePossib;
		}
	}
	

}