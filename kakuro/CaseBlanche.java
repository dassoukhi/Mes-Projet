package kakuro;

import java.util.ArrayList;

public class CaseBlanche extends Case {
	private int valeur;
	private ArrayList<Integer> possibilite = new ArrayList<>();
	
	// CONSTRUCTEURS
	public CaseBlanche() {
		super();
		valeur = 0;
		possibilite = null;
	}
	
	public CaseBlanche(int x1, int y1, int val, ArrayList<Integer> p) {
		super(x1, y1);
		valeur = val;
		possibilite = p;
	}
	
	// ACCESSEURS EN LECTURE
	public int getValeur() {return valeur;}
	
	public String getType() {return "blanche";}
	
	public ArrayList<Integer> getPossibilite() {return possibilite;}
	
	public String toString() {return super.toString() + " CaseBlanche " + valeur;}
	
	// ACCESSEURS EN ECRITURE
	public void setValeur(int val) {valeur = val;}
	
	public void setPossibilite(int val) {possibilite.add(val);}
	public void setPossibilite(ArrayList<Integer>listeSomme) {possibilite=listeSomme;}

	
	
	
}
