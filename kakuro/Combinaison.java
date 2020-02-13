package kakuro;
import java.awt.*;
import java.util.*;

import javax.swing.JOptionPane;

public class Combinaison {
	
	   public static int sommeMin(int nbrCases) {
	        return (nbrCases * (nbrCases + 1)) / 2;
	    }

	    public static int sommeMax(int nbrCases) {
	        return (10 * nbrCases) - sommeMin(nbrCases);
	    }
	
	    
	    
	    
	    public static void completerSequence(StringBuilder chaineTampon,int somme, int nbrCases,int iDebut) 
            
	    {     
	    
			
			int i, chiffreAns, chiffreCur, ri, ni, sommeAnsDebut = 0;
			String s;
			
		
			if (iDebut >= 2) {
			chiffreAns = (chaineTampon.charAt(2 * (iDebut - 1) - 2) - '0');
			                
			} else {
			chiffreAns = 0;
			}
			
//			System.out.println(chiffreAns);
//			System.out.println(iDebut);
			
			for (i = 0; i <= 2 * (iDebut - 1) - 2; i = i + 2) {
			sommeAnsDebut +=
			(chaineTampon.charAt(i) - '0');
			}
			
		
			ri = somme - sommeAnsDebut;
			ni = nbrCases - iDebut + 1;
			
			
			for (i = iDebut; i <= nbrCases; i++) {
			
			if ((chiffreAns == 9) && (i <= nbrCases)) {
			s = new String("9");
			} else {
			s = new String(Integer.toString(Math.max(chiffreAns + 1,
			ri - sommeMax(ni - 1))));
			}
			chaineTampon.setCharAt(2 * i - 2, s.charAt(0));
			chiffreCur = chaineTampon.charAt(2 * i - 2) - '0';//ici on prend la valeur qu'on a inserer pour soustraire du somme 
			ri -= chiffreCur;
			chiffreAns = chiffreCur;
			ni -= 1;
			
			}
			
			}
	
	
	
	
	
	
	public static ArrayList<ArrayList<Integer>> combinaisonsPossibles(int somme, int nbrCases){
       
            int i, pointeur, sommeSeqTemp;
            char c;
            String s;
            ArrayList<String> sequences = new ArrayList<String>(0);

       
            StringBuilder seqTemp = new StringBuilder("0");
            for (i = 2; i <= nbrCases; i++) {

                seqTemp.append("+0");
            }
            

            completerSequence(seqTemp, somme, nbrCases, 1);

            //ici
            pointeur = nbrCases - 1;

            while (true) {
               
                sommeSeqTemp = 0;
                for (i = 0; i <= 2 * nbrCases - 2; i = i + 2) {
                    sommeSeqTemp += seqTemp.charAt(i) - '0';
                }
                
                if (sommeSeqTemp == somme) {
                    sequences.add(seqTemp.toString());
                    pointeur = nbrCases - 1;
                }
               
                else {
                    if (pointeur > 1) {
                        pointeur -= 1;
                    } else {
                        break;
                    }
                }
                seqTemp.setCharAt(2 * pointeur - 2,
                                  (char) (seqTemp.charAt(2 * pointeur - 2) + 1));
                completerSequence(seqTemp, somme, nbrCases, pointeur + 1);
            }

            return toListListInt(sequences);
       
	}
	
	public static ArrayList<Integer> getTabPermutation(String s) {
		int n=s.length();
		n=n-(n/2);
		//System.out.println(n);
		
		ArrayList<Integer> list = new ArrayList<>();
		
		
		int m=0;
		for (int j = 0; j < s.length(); j++) {
			
			
			if (s.charAt(j)!='+') {
				
				//System.out.println(k.charAt(j));
				list.add(Character.getNumericValue(s.charAt(j)));
				
				
			}
		}
		return list;
	}
	
	public static ArrayList<ArrayList<Integer>> toListListInt(ArrayList<String> as) {
		ArrayList<ArrayList<Integer>> listPermutation = new ArrayList<ArrayList<Integer>>();
		for (String s : as) {
			listPermutation.add(getTabPermutation(s));
		}
		return listPermutation;
	}
	
	public static void main(String[] args) {
		
		System.out.println(combinaisonsPossibles(8, 3));
	}
}
