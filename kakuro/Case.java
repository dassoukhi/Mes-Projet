package kakuro;
public class Case {
	private int x;
	private int y;
	
	// CONSTRUCTEURS
	public Case() {
		x = 0;
		y = 0;
	}
	
	public Case(int x1, int y1) {
		x = x1;
		y = y1;
	}
	
	// ACCESSEURS EN LECTURE
	public int getX() {return x;}
	public int getY() {return y;}
	
	// ACCESSEURS EN ECRITURE
	public void setX(int x1) {x = x1;}
	public void setY(int y1) {y = y1;}
	
	public String getType() {
		return "vide";
	}
	
	public String toString() {
		return "(" + x + "," + y + ")";
	}
}
