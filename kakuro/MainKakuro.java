package kakuro;


import java.util.Scanner;

public class MainKakuro {
	public static void main(String[] args){
	GrilleV3 grille = new GrilleV3(10);
		
		Boolean envoyer = false, fini = false;
		int choix = 0;
		Scanner sc = new Scanner(System.in);
		
		Solveur solveur = new Solveur();
		
		while (!fini) {
			System.out.println("Solveur (1) ou Solution (2) ? ");
			choix = sc.nextInt();
			
			if (choix == 1) {
				solveur.solve(0,grille.getN1(), grille.getN2(), grille.getTabCase(), grille.getTabText(), grille.getListeSomme());
			}
			else {
//				if (solveur.verification(grille.getListeSomme(), grille.getTabCase()) ) {
//					System.out.println("Bravo c'est gagné !!!!!!!!");
//				}
//				else {
					System.out.println("Je suis assez déçu de toi.... tu as perdu.");
					System.out.println("Un indice peut-être ?");
					solveur.indice(0,0,grille.getTabCase(), grille.getTabText());
//				}
			}
			
			System.out.println("Fini ?");
			fini = sc.nextBoolean();
		}
		sc.close();
	}
}