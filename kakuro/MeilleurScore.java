package kakuro;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.swing.*;


public class MeilleurScore {
	
	
	
	
	public boolean heureMin(int h1,int m1, int s1,int h2, int m2,int s2)
	{
		
		if(h2<h1)
    	{
			return true;
    	}
    	else if(h2==h1 && m2 <m1)
    	{
    		return true;    	}
    	else if(m2==m1 && s2<s1)
    	{
    		return true;    	
    		}
		
		return false;
	}
	
	public void AlgoScore(String nom, int h, int m, int s) throws IOException {
		
		String newJouer = nom +" "+((h/10)==0?"0"+h:+h)+":"+((m/10)==0?"0"+m:+m)+":"+((s/10)==0?"0"+s:+s);
		
		//System.out.println(newJouer);
		
		File file = new File("C:\\Users\\Dass\\eclipse-workspace\\kakuro\\src\\kakuro\\score.txt");
		Scanner entrer = new Scanner(file);
		
		String ligne1 = "",ligne2 = "",ligne3 = "",ligne4 = "",ligne5 = "";
		String duree1="",duree2 = "", duree3 = "", duree4 = "", duree5 = "";
		int cpt=0;
		
		int h1 = 0,m1 = 0,s1 = 0;
		int h2 = 0,m2 = 0,s2 = 0;
		int h3 = 0,m3 = 0,s3 = 0;
		int h4 = 0,m4 = 0,s4 = 0;
		int h5 = 0,m5 = 0,s5 = 0;
		

		while(entrer.hasNext() && cpt <5)
		{
			
			
			switch (cpt) {
			case 0:
				ligne1 = entrer.next();
				duree1 = entrer.next();
				
				cpt++;
				break;
			case 1:
				ligne2 = entrer.next();
				duree2 = entrer.next();
				cpt++;
				break;
			case 2:
				ligne3 = entrer.next();
				duree3 = entrer.next();
				cpt++;
				break;
			case 3:
				ligne4 = entrer.next();
				duree4 = entrer.next();
				cpt++;
				break;
			case 4:
				ligne5 = entrer.next();
				duree5 = entrer.next();
				cpt++;
				break;

			
			}
			
			
			
		}
		
		
		

		
		entrer.close();
		
		int nbrligne = cpt;
		
		if(!duree1.equals(""))
		{
			char a = duree1.charAt(0);
			char b = duree1.charAt(1);
			String k= String.valueOf(a) + String.valueOf(b);
			h1 = Integer.valueOf(k);
			
			 a = duree1.charAt(3);
			 b = duree1.charAt(4);
			 k= String.valueOf(a) + String.valueOf(b);
			 m1 = Integer.valueOf(k);
			
			 a = duree1.charAt(6);
			 b = duree1.charAt(7);
			 k= String.valueOf(a) + String.valueOf(b);
			 s1 = Integer.valueOf(k);
			
			
			
		}
		if(!duree2.equals(""))
		{
			char a = duree2.charAt(0);
			char b = duree2.charAt(1);
			String k= String.valueOf(a) + String.valueOf(b);
			h2 = Integer.valueOf(k);
			
			 a = duree2.charAt(3);
			 b = duree2.charAt(4);
			 k= String.valueOf(a) + String.valueOf(b);
			 m2 = Integer.valueOf(k);
			
			 a = duree2.charAt(6);
			 b = duree2.charAt(7);
			 k= String.valueOf(a) + String.valueOf(b);
			 s2 = Integer.valueOf(k);
			
			
			
		}
		if(!duree3.equals(""))
		{
			char a = duree3.charAt(0);
			char b = duree3.charAt(1);
			String k= String.valueOf(a) + String.valueOf(b);
			h3 = Integer.valueOf(k);
			
			 a = duree3.charAt(3);
			 b = duree3.charAt(4);
			 k= String.valueOf(a) + String.valueOf(b);
			 m3 = Integer.valueOf(k);
			
			 a = duree3.charAt(6);
			 b = duree3.charAt(7);
			 k= String.valueOf(a) + String.valueOf(b);
			 s3 = Integer.valueOf(k);
			
			
			
		}
		
		if(!duree4.equals(""))
		{
			char a = duree4.charAt(0);
			char b = duree4.charAt(1);
			String k= String.valueOf(a) + String.valueOf(b);
			h4 = Integer.valueOf(k);
			
			 a = duree4.charAt(3);
			 b = duree4.charAt(4);
			 k= String.valueOf(a) + String.valueOf(b);
			 m4 = Integer.valueOf(k);
			
			 a = duree4.charAt(6);
			 b = duree4.charAt(7);
			 k= String.valueOf(a) + String.valueOf(b);
			 s4 = Integer.valueOf(k);
			
			
			
		}
		
		if(!duree5.equals(""))
		{
			char a = duree5.charAt(0);
			char b = duree5.charAt(1);
			String k= String.valueOf(a) + String.valueOf(b);
			h5 = Integer.valueOf(k);
			
			 a = duree5.charAt(3);
			 b = duree5.charAt(4);
			 k= String.valueOf(a) + String.valueOf(b);
			 m5 = Integer.valueOf(k);
			
			 a = duree5.charAt(6);
			 b = duree5.charAt(7);
			 k= String.valueOf(a) + String.valueOf(b);
			 s5 = Integer.valueOf(k);
			
			
		
		}
		
		 
	
		
		PrintWriter sortie=new PrintWriter(new FileWriter(file));
		
		if(cpt == 0)
		{
			cpt = 1;
		}
		   
		boolean ditMoi=false; //pour savoir si le nouveau joueur est placé dans la liste ou pas
		
		    for(int i=0;i<cpt;i++){
		    
		    
		    	
		    	switch (i) {
				case 0:
					
					
					
							
						if(duree1.equals(""))
						{
							sortie.println(""+newJouer);
						}
					else if(!duree1.equals("") && heureMin(h1, m1, s1, h, m, s))
						{
							ditMoi=true;
							sortie.println(""+newJouer);
							if(duree2.equals(""))
							{
								sortie.println(ligne1+" "+duree1);
							}
						}
						else {
							sortie.println(""+ligne1+" "+duree1);
							
							if(duree2.equals(""))
							{
								sortie.println(""+newJouer);
							}
						}
						 
					
					break;
				case 1:
					if(ditMoi)
					{
						sortie.println(""+ligne1 + " "+duree1);
					}
					else if(!duree2.equals("") && heureMin(h2, m2, s2, h, m, s))   //remplacer h2,m2,S2
					{
						ditMoi=true;
						sortie.println(""+newJouer);
						if(duree3.equals(""))
						{
							sortie.println(ligne2+" "+duree2);
						}
					}
					else {
						sortie.println(""+ligne2+" "+duree2);
						
						if(duree3.equals(""))
						{
							sortie.println(""+newJouer);
						}
					}
					 
					break;
				case 2:
					if(ditMoi)
					{
						sortie.println(""+ligne2+" "+duree2);
						
						if((i+1)==cpt && !ligne3.equals(""))
						{
							sortie.println(ligne3+" "+duree3);
						}
					}
					else if(!duree3.equals("") && heureMin(h3, m3, s3, h, m, s))   
					{
						ditMoi=true;
						sortie.println(""+newJouer);
						
						if(duree4.equals(""))
						{
							sortie.println(ligne3+" "+duree3);
						}
					}
					else {
						sortie.println(""+ligne3+" "+duree3);
						
						if(duree4.equals(""))
						{
							sortie.println(""+newJouer);
						}
					}
					 
				
					break;
				case 3:
					if(ditMoi)
					{
						sortie.println(""+ligne3+" "+duree3);
						
						if((i+1)==cpt && !ligne4.equals(""))
						{
							sortie.println(ligne4+" "+duree4);
						}
					}
					else if(!duree4.equals("") && heureMin(h4, m4, s4, h, m, s))   
					{
						ditMoi=true;
						sortie.println(""+newJouer);
						
						if(duree5.equals(""))
						{
							sortie.println(ligne4+" "+duree4);
						}
					}
					else {
						sortie.println(""+ligne4+" "+duree4);
						
						if(duree5.equals(""))
						{
							sortie.println(""+newJouer);
						}
					}
					 
					break;
				case 4:
					if(ditMoi)
					{
						sortie.println(""+ligne4+" "+duree4);
						
						
					}
					else if(!duree5.equals("") && heureMin(h5, m5, s5, h, m, s))  
					{
						ditMoi=true;
						sortie.println(""+newJouer);
					}
					else {
						 sortie.println(""+ligne5+" "+duree5);
					}
					
					break;

				
				}

		 
		     
		  }
		sortie.close();
		
		System.out.println("tout est OK");
		
	}
	
	
	
	public void MajLesItem(JMenuItem j1, JMenuItem j2 ,JMenuItem j3,JMenuItem j4,JMenuItem j5) throws FileNotFoundException
	{
		File file = new File("C:\\Users\\Dass\\eclipse-workspace\\kakuro\\src\\kakuro\\score.txt");
		
		Scanner entrer = new Scanner(file);
		
		
		String ligne1 = "",ligne2 = "",ligne3 = "",ligne4 = "",ligne5 = "";

		int cpt=0;
		
		int tmp1=0,tmp2=0,tmp3=0,tmp4=0,tmp5=0;

		

		while(entrer.hasNextLine() && cpt <5)
		{
			
			
			switch (cpt) {
			case 0:
				ligne1 = entrer.nextLine();
				
				j1.setText(j1.getText()+"  "+ligne1);
				tmp1++;

				break;
			case 1:
				ligne2 = entrer.nextLine();
				j2.setText(j2.getText()+"  "+ligne2);
				tmp2++;
				break;
			case 2:
				ligne3 = entrer.nextLine();
				j3.setText(j3.getText()+"  "+ligne3);
				tmp3++;
				break;
			case 3:
				ligne4 = entrer.nextLine();
				j4.setText(j4.getText()+"  "+ligne4);
				tmp4++;
				break;
			case 4:
				ligne5 = entrer.nextLine();
				j5.setText(j5.getText()+"  "+ligne5);
				tmp5++;
				break;

			
			}
			
			cpt++;
			
		}
		
		
		

		
		entrer.close();
		
		
		if(tmp1==0)
		{
			j1.setVisible(false);
		}
		if(tmp2==0)
		{
			j2.setVisible(false);
		}
		if(tmp3==0)
		{
			j3.setVisible(false);
		}
		if(tmp4==0)
		{
			j4.setVisible(false);
		}
		if(tmp5==0)
		{
			j5.setVisible(false);
		}
		
		
		
	}
	
	public static void main(String[] args) throws IOException {
		
		new MeilleurScore().AlgoScore("Dass", 0, 3, 43);
		
	}

}
