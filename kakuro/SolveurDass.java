package kakuro;

import java.awt.Color;
import java.util.ArrayList;

public class SolveurDass {
	
	   public static int sommeMin(int nbrCases) {
	        return (nbrCases * (nbrCases + 1)) / 2;
	    }

	    public static int sommeMax(int nbrCases) {
	        return (10 * nbrCases) - sommeMin(nbrCases);
	    }
	
	    
	    public static int nbrCaseX(int[][] grille, int i, int j, ArrayList<Integer> listeSom)
	    {
	    	int k=j+1;
	    	int cpt=0;
	    	int p = (i * grille.length) + k;
	    	
	    	while( k < grille.length && !estCaseSomme(listeSom, p) && grille[i][k]!=-1)
	    	{
	    		cpt++;
	    		p++;
	    		k++;
	    	}
		
	    	return cpt;
	    	
	    }
	    
	    public static int nbrCaseY(int[][] grille, int i, int j, ArrayList<Integer> listeSom)
	    {
	    	int k=i+1;
	    	int cpt=0;
	    	int p = (k * grille.length) + j;
	    	
	    	
	    	while( k < grille.length && !estCaseSomme(listeSom, p) && grille[k][j]!=-1)
	    	{
	    		cpt++;
	    		k++;
	    		p = (k * grille.length) + j;
	    	}
		
	    	return cpt;
	    	
	    }
	    
	 public static ArrayList<Integer> touteSomme(int[][] grille)
	    {
	    	ArrayList<Integer> listeSomme = new ArrayList<Integer>();
	    	
	    	for (int i = 0; i < grille.length; i++) {
	    		for (int j = 0; j < grille.length; j++) {
	    			
	    			if(grille[i][j] != -1 && grille[i][j] != 0)
	    			{
	    				listeSomme.add(grille[i][j]);
	    			}
				}
				
			}
	    	return listeSomme;
	    	
	    }
	 
	 public static ArrayList<Integer> toutCaseSome(int[][] grille)
	 {
		 ArrayList<Integer> listeCaseSomme = new ArrayList<Integer>();
		 
		 for (int i = 0; i < grille.length; i++) {
			 for (int j = 0; j < grille.length; j++) {
				
				 if(grille[i][j] != -1 && grille[i][j] != 0)
	    			{
					 int p= (i*grille.length) + j;
	    				listeCaseSomme.add(p);
	    			}
			}
			
		}
		 
		 
		 return listeCaseSomme;
	 }
public static boolean estCaseSomme(ArrayList<Integer> liste, int p)
{
	
	
	for (int i = 0; i < liste.size(); i++) {
		
		if(liste.get(i)==p)
		{
			return true;
		}
		
	   }
	
	return false;
	
	
}

	public static int[] convertCombi(ArrayList<String> allCombi, int i)
	{
		int n =allCombi.get(i).length();
		n=n - (n/2);
		int[] tab= new int[n];
		
		String k=allCombi.get(i);
		int m=0;
		for (int j = 0; j < k.length(); j++) {
			
			
			if (k.charAt(j)!='+') {
				
				
				tab[m]=Character.getNumericValue(k.charAt(j));
				m++;	
				
			}
		}
		
		
		return tab;
	}

	public static boolean estRempliX(int i, int j, int[][] grille, ArrayList<Integer> listeSom)
	{
		int m=0,k=j+1;
	
	
		while(m < nbrCaseX( grille, i, j, listeSom) )
		{
			if( grille[i][k] == 0 )
				{
				return false ;
				}
	
		k++;
		m++;
		
		}

		return true;

	}
	
	public static boolean estRempliY(int i, int j, int[][] grille, ArrayList<Integer> listeSom)
	{
		int m=0,k=i+1;
	
	
		while(m < nbrCaseY( grille, i, j, listeSom) )
		{
			if( grille[k][j] == 0 )
				{
				return false ;
				}
	
		k++;
		m++;
		
		}

		return true;

	}

	public static void reinitialiseCaseSomX(int[][] grille, int i, int j,ArrayList<Integer> listeSom)
	{
		int m=0,k=j+1;
		
		
	
		while(m < nbrCaseX( grille,i, j, listeSom) )
		{
			grille[i][k]= 0;
		
			k++;
			m++;
		}


	}
	
	public static void reinitialiseCaseSomY(int[][] grille, int i, int j,ArrayList<Integer> listeSom)
	{
		int m=0,k=i+1;
		
		
	
		while(m < nbrCaseY( grille,i, j, listeSom) )
		{
			grille[k][j]= 0;
		
			k++;
			m++;
		}


	}


	public static ArrayList<Integer> listeSomCommunX(int i, int j, int[][] grille, ArrayList<Integer> listeCaseSom)
	{
		j++;
		
		//int cpt=0;
		
		ArrayList<Integer> sommeCommun = new ArrayList<Integer>();
		
		while( j < grille.length && !estCaseSomme(listeCaseSom,(grille.length*i)+j) && grille[i][j] !=-1)
		{
			int m=i-1;
			
			while(m >= 0 && grille[m][j] != -1)
			{
				if( estCaseSomme(listeCaseSom, (grille.length * m) + j))
				{
					//cpt++;
					sommeCommun.add(grille[m][j]);
					m=0;
				}
				
				m--;
			}
		
			j++;
		}
		return sommeCommun;

	}

	public static ArrayList<Integer> listeSomCommunY(int i, int j, int[][] grille, ArrayList<Integer> listeCaseSom)
	{
		i++;
		
		//int cpt=0;
		
		ArrayList<Integer> sommeCommun = new ArrayList<Integer>();
		
		while( i < grille.length && !estCaseSomme(listeCaseSom,(grille.length*i)+j) && grille[i][j] !=-1)
		{
			int m=j-1;
			
			while(m >= 0 && grille[i][m] != -1)
			{
				if( estCaseSomme(listeCaseSom, (grille.length * i) + m))
				{
					//cpt++;
					sommeCommun.add((grille.length * i) + m);
					m=0;
				}
				
				m--;
			}
		
			i++;
		}
		return sommeCommun;

	}
	
	public static ArrayList<Integer> valeursCommun(ArrayList<String> un,ArrayList<String> deux )
	{
	ArrayList<Integer> listeValCom = new ArrayList<>();
	
	for (int i = 0; i < un.size(); i++) {
		
		int[] tab1 = convertCombi(un, i);
		
		for (int j = 0; j < deux.size(); j++) {
			
			int[] tab2 = convertCombi(deux, j);
			
			for (int k = 0; k < tab1.length; k++) {
				
				for (int k2 = 0; k2 < tab2.length; k2++) {
					
					if( tab1[k] == tab2[k2] && !listeValCom.contains(tab1[k]))
						{
							listeValCom.add(tab1[k]);
						
						}
					
				}
				
			}
			
		}
		
	}
		
		return listeValCom;
	}
	
	public static boolean existeDejaY(int[][] grille,ArrayList<Integer> listeSom, int i ,  int j, int val )
	{
	
		while ( (i - 1) >= 0 && grille[i-1][j] != -1 && !estCaseSomme(listeSom, (grille.length * (i-1) ) + j)) {
			
			if (val == grille[i-1][j]) {
				return true;
				
			}
			
			i--;
			
		}
		
		return false;
	}
	
	public static int totalSommeY(int[][] grille, ArrayList<Integer> listeSom, int i, int j)
	{
		int som=0;
		
		int m=0,k=i+1;
		
		
		while(m < nbrCaseY( grille, i, j, listeSom) )
		{
			som+=grille[k][j]; 
		k++;
		m++;
		
		}
		
		return som;
	}
	
	public static boolean caseSolveurY(int[][] grille, ArrayList<Integer> listeSom,int a, int b, int i, int j,int m,int nbr,int somme)
	{
		
	
	
		if (m == nbr){
			
			return true;
			
		}
		
		ArrayList<String> combiY= new Combinaison().combinaisonsPossibles((grille[a][b] / 100), nbr);
		
		ArrayList<Integer> sommeCommun = listeSomCommunY(a, b, grille, listeSom);
		//System.out.println("   hhh  "+m);
		
		int p = sommeCommun.get(m);
		int k=  p/grille.length;
		int h =  p%grille.length;
		
		//System.out.println(nbrCaseX(grille, p/grille.length, p%grille.length, listeSom));
		
		
		
		ArrayList<String> combiX = new Combinaison().combinaisonsPossibles(grille[k][h]%100, nbrCaseX(grille, k, h, listeSom));
		
		ArrayList<Integer> valCom = valeursCommun(combiY, combiX);
		
		
	
		for (int l = 0; l < valCom.size(); l++) {
			
		
			if( !existeDejaY(grille, listeSom, i+1, j, valCom.get(l)))
			{
				
				//System.out.println(!existeDejaY(grille, listeSom, i+1, j, valCom.get(l)));
				
				grille[i+1][b] = valCom.get(l);
				
				if(caseSolveurY(grille, listeSom, a, b, i+1, j, m+1, nbr, somme))
				{
					//if(estRempliY(a, b, grille, listeSom) && (somme == totalSommeY(grille, listeSom, a, b)))
					
						return true;
					
					
				}
			}
			
			
			grille[i+1][j]=0;
		}
			
		
		return false;
		
		
		
		
	}


	public static boolean solveur(int[][] grille, ArrayList<Integer> listeSom, int p)
	{
		
		if(p == grille.length * grille.length)
		{
			//System.out.println("  p  "+p);
			
			return true;
		}
		
		if( !estCaseSomme(listeSom, p))
		{
			//System.out.println(!estCaseSomme(listeSom, p));
			return solveur(grille, listeSom, p+1);
		}
		
		//System.out.println("  p  "+p);
		
		int i=p / grille.length;
		//System.out.println("i  "+i);
		
		int j= p % grille.length;
		
		//System.out.println("j  "+j);
	
		if((grille[i][j] % 100 ) != 0 ) 
		{
			
			
			
		
		ArrayList<String> combiSomX = new Combinaison().combinaisonsPossibles((grille[i][j] % 100 ), nbrCaseX(grille, i, j,listeSom)); 
		
		ArrayList<Integer> sommeCommunX = listeSomCommunX(i, j, grille, listeSom);
		
		int n1 = nbrCaseX(grille, i, j,listeSom);
		
			for (int k = 0; k < combiSomX.size(); k++) {
				 i=p / grille.length;
				 j= p % grille.length;
				
				int[] tab=convertCombi(combiSomX, k);
				
				int m1=0;
				 while(m1 < n1)
				 {
					 j++;
					 
					grille[i][j]=tab[m1];
					
					m1++;
					
					 
					 
				 }
				
			}
			
			
		}
		
		
		
		if((grille[i][j] / 100) != 0 ) 
		{
			
			
			//System.out.println("bbbbbb"+(grille[i][j] / 100) );
			
			ArrayList<String> combiSomY = new Combinaison().combinaisonsPossibles((grille[i][j] / 100), nbrCaseY(grille, i, j,listeSom)); 
			
			ArrayList<Integer> sommeCommunY = listeSomCommunY(i, j, grille, listeSom);
		
			
			
			int n2 = nbrCaseY(grille, i, j,listeSom);
			
			for (int k = 0; k < combiSomY.size(); k++) {
				
				 i=p / grille.length;
				 j= p % grille.length;
				
				int[] tab=convertCombi(combiSomY, k);
				
				int m2=0;
				 while(m2 < n2)
				 {
					 i++;
					 
					grille[i][j]=tab[m2];
					
					m2++;
					
					 
					 
				 }
				
			}
			
		}
		
	
		
		return solveur(grille, listeSom, p+1);
		
		
		
	}

public static void main(String[] args) {
	
	    int[][] grille = new int[][]{
		new int[]{-1, 1900, 900, -1, 2400, 1200, 1600},
		new int[]{4, 0, 0, 23, 0, 0, 0},
		new int[]{10, 0, 0, 1813, 0, 0, 0},
		new int[]{24, 0, 0, 0, 0, 2100, 1100},
		new int[]{-1, 900, 1027, 0, 0, 0, 0},
		new int[]{7, 0, 0, 0, 13, 0, 0},
		new int[]{23, 0, 0, 0, 5, 0, 0}
		};

		ArrayList<Integer> listeSomme = toutCaseSome(grille);
		
		
		
		 //solveur(grille, listeSomme, 0);
		 
		 afficheGrille(grille);
		 
		 ArrayList<String> combiSomX = new Combinaison().combinaisonsPossibles(9, 3);
		
		
		
		System.out.println(combiSomX);
		 ArrayList<String> combiSom = new Combinaison().combinaisonsPossibles(24,4);
		
		
		
		System.out.println(combiSom);
		System.out.println(valeursCommun(combiSomX, combiSom));
	
		
		caseSolveurY(grille, listeSomme, 0, 1, 0, 1, 0, nbrCaseY(grille, 0, 1, listeSomme), grille[0][1] / 100);
		
		
	
		
		afficheGrille(grille);
		
		
		
		System.out.println(existeDejaY(grille, listeSomme, 0, 1, 9));
		
}



public static void afficheGrille(int[][] grille)
{
	
	for (int i = 0; i < grille.length; i++) {
		
		for (int j = 0; j < grille.length; j++) {
			
			System.out.print(grille[i][j]+" "); 
			
		}
		
		System.out.println();
	}
	
}

}
