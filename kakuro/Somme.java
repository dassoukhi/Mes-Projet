package kakuro;

import java.util.ArrayList;

public class Somme {
	private int valSomme;
	private boolean direction; // 0 = DROITE, 1 = BAS
	private ArrayList<Pair<Integer,Integer>> listeCasesImplique;
	private ArrayList<ArrayList<Integer>> listePermutation;

	public Somme() {
		valSomme = 0;
		direction = false;
		listeCasesImplique = new ArrayList<Pair<Integer,Integer>>();
		listePermutation = new ArrayList<ArrayList<Integer>>();
	}

	public Somme(int s, boolean b, ArrayList<Pair<Integer,Integer>> lCI, ArrayList<ArrayList<Integer>> lP) {
		valSomme = s;
		direction = b;
		listeCasesImplique = lCI;
		listePermutation = lP;
	}

	public int getSomme() {
		return valSomme;
	}
	
	public void setSomme(int s) {
		valSomme = s;
	}
	
	public boolean getDirection() {
		return direction;
	}
	
	public void setDirection(boolean d) {
		direction = d;
	}

	public ArrayList<Pair<Integer,Integer>> getCaseImp() {
		return listeCasesImplique;
	}

	public ArrayList<ArrayList<Integer>> getPermutation() {
		return listePermutation;
	}

	public boolean caseAppartient(int i, int j) {
		for (Pair<Integer,Integer> p : listeCasesImplique) {
			if (p.getLeft().equals(i) && p.getRight().equals(j)) {
				return true;
			}
		}
		return false;
	}

	public String toString() {
		return valSomme + " / " + direction + " / " + listeCasesImplique.toString() + listePermutation.toString();
	}
}