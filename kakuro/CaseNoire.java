package kakuro;

public class CaseNoire extends Case {
	private Somme sommeDroite;
	private Somme sommeBas;
	
	// CONSTRUCTEURS
	public CaseNoire() {
		super();
		sommeDroite = new Somme();
		sommeBas = new Somme();
	}
	
	public CaseNoire(int x1, int y1, Somme sd, Somme sb) {
		super(x1, y1);
		sommeDroite = sd;
		sommeBas = sb;
	}
	
	// ACCESSEURS EN LECTURE
	public Somme getSommeDroite() {return sommeDroite;}
	public Somme getSommeBas() {return sommeBas;}
	
	// ACCESSEURS EN ECRITURE
	public void setSommeDroite(Somme sd) {sommeDroite = sd;}
	public void setSommeBas(Somme sb) {sommeBas = sb;}

	public String getType() {
		return "noire";
	}
	
	public String toString() {
		return super.toString() + " CaseNoire " + "\n" + "Somme droite:" + sommeDroite.toString() + "\n" + "Somme bas:" + sommeBas.toString();
	}
}
