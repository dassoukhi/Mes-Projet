package kakuro;

public class backtrack {

	
	static boolean absLigne(int k, int[][] grille, int i)
	{
		
		for(int j=0; j<4;j++)
		{
			if(grille[i][j]==k)
			{
				return false;
			}
		}
		return true;
	}
	
	static boolean absColonne(int k, int[][] grille,int j)
	{
		for(int i=0;i<4;i++)
		{
			if(grille[i][j]==k)
			{
				return false;
			}
		}
		
		return true;
	}
	
	
	static boolean absBlock(int k, int[][] grille, int i, int j)
	{
		int i_=i-(i%2);
		int j_=j-(j%2);
		
		for(i=i_;i<i_+2;i++)
		{
			for(j=j_;j<j_+2;j++)
			{
				
				if(grille[i][j]==k)
				{
					return false;
				}
			}	
		}
		
		return true ;
	}
	
	
	static boolean estValide(int[][] grille, int position)
	{
		if(position==4*4)
		{
			return true;
		}
			
		int i=position/4;
		int j=position%4;
		
		if(grille[i][j]!=0)
		{
			affiche(grille);
			System.out.println("ppppppp    "+position);
			return estValide(grille, position+1);
			
		}
		 
		for(int k=1;k<=4;k++)
		{
			
			if(absLigne(k, grille, i) && absColonne(k, grille, j) && absBlock(k, grille, i, j))
			{
				
				grille[i][j]=k;
				
				if(estValide(grille, position+1))
				{
					affiche(grille);
					System.out.println("skurtttttttt");
					
					return true;
					
				}
			}
			
		}
		
		grille[i][j]=0;
		
		
		return false;
	}
	
	static void affiche(int[][] grille)
	{
		for (int i = 0; i < grille.length; i++) {
			
			for (int j = 0; j < grille.length; j++) {
				
				System.out.print(grille[i][j]);
				
			}
			System.out.println();
			
		}
		
		
		
	}
	
	public static void main(String[] args) {
		
		int[][] grille= new int [4][4];
		grille[0][0]=2;
		grille[0][1]=0;
		grille[0][2]=4;
		grille[0][3]=3;
		grille[1][0]=0;
		grille[1][1]=0;
		grille[1][2]=2;
		grille[1][3]=0;
		grille[2][0]=4;
		grille[2][1]=3;
		grille[2][2]=0;
		grille[2][3]=0;
		grille[3][0]=0;
		grille[3][1]=0;
		grille[3][2]=3;
		grille[3][3]=4;
		
		
		
//		System.out.println("AVANT");
//		affiche(grille);
		
		estValide(grille,0);
		
		System.out.println("APREST");
		affiche(grille);
		
		
		System.out.println(5*(5+1)/2);
		
		
		
		
	}
	
}
