package kakuro;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Scanner;

public class Niveau {
	
	//attributs
	
	private int levelid;
	private int xdim;
	private int ydim;
	private boolean unlocked;
	private String levelname;
	private ArrayList<Somme> lstSom;
	private ArrayList<Pair<Integer,Integer>> lstccs;
	private Case[][] t;
	
	//getteurs
	
	public int getLevelid() {return levelid;}
	public int getX() {return xdim;}
	public int getY() {return ydim;}
	public boolean isUnlocked() {return unlocked;}
	public String getLevelname() {return levelname;}
	public ArrayList<Somme> getSom() {return lstSom;}
	public ArrayList<Pair<Integer,Integer>> getlstccs(){return lstccs;}
	
	
	//setteurs
	public void setLevelid(int levelid) {this.levelid = levelid;}
	public void setUnlocked(boolean unlocked) {this.unlocked = unlocked;}
	public void setint(int x) {this.xdim = x;}
	public void setY(int y) {this.ydim = y;}

	//constructeur
	public Niveau(int i) 
	{
		this.levelid=i;
		this.levelname="./Niveaux/niveau"+i+".lvl";
		//this.levelname="./Niveaux/tuto.lvl";
		this.lstSom = new ArrayList<Somme>();
		this.lstccs = new ArrayList<Pair<Integer,Integer>>();
	}
	
	//teste si un string est un nombre	
public static boolean isNumeric(String str) 
	{
	  NumberFormat formatter = NumberFormat.getInstance();
	  ParsePosition pos = new ParsePosition(0);
	  formatter.parse(str, pos);
	  return str.length() == pos.getIndex();
	}


public CaseNoire SommeTransfo(String[] S, int x,int y)
{
	String[] subcase = S[x].split(",");
	String[] somb = subcase[0].split("'");
	String[] somd = subcase[1].split("'");
	
	int valsombas=Integer.parseInt(somb[0]);
	int valsomdroite=Integer.parseInt(somd[0]);
	
	int nbcasesbas=0;
	int nbcasesdroite=0;
	
	ArrayList<Pair<Integer,Integer>> LstCasesImpbas = new ArrayList<Pair<Integer,Integer>>();
	ArrayList<Pair<Integer,Integer>> LstCasesImpdroite = new ArrayList<Pair<Integer,Integer>>();
	
	Somme Sommebas =new Somme();
	Somme Sommedroite =new Somme();
	
	if (somb.length!=1) {
		
		nbcasesbas=Integer.parseInt(somb[1]);		
//		for (int i = 1; i <= nbcasesbas; i++) {
//			LstCasesImpbas.add(new Pair<Integer,Integer>(x-1,y+i));	
//		}
		LstCasesImpbas=new Solveur().getListePair(y,x-1,nbcasesbas,true);
		ArrayList<ArrayList<Integer>> LstPermuBas = Combinaison.combinaisonsPossibles(valsombas, nbcasesbas);;
		Sommebas =new Somme(valsombas,true,LstCasesImpbas,LstPermuBas);
		lstSom.add(Sommebas);

	}
	
	if (somd.length!=1) {
		
		nbcasesdroite=Integer.parseInt(somd[1]);
//		for (int i = 1; i <= nbcasesdroite; i++) {
//			LstCasesImpdroite.add(new Pair<Integer,Integer>(x,y));	
//		}
		LstCasesImpdroite=new Solveur().getListePair(y,x-1,nbcasesdroite,false);
		ArrayList<ArrayList<Integer>> LstPermuDroite = Combinaison.combinaisonsPossibles(valsomdroite, nbcasesdroite);
		Sommedroite =new Somme(valsomdroite,false,LstCasesImpdroite,LstPermuDroite);
		lstSom.add(Sommedroite);

	}
	lstccs.add(new Pair<Integer, Integer>(x-1, y));
	return new CaseNoire(x-1,y,Sommedroite,Sommebas);
}


public void initialiseNiveau(int i)
{	
	setUnlocked(false);
	xdim=0;
	ydim=0;
try{
	
	File fichier = new File(levelname); //ouvre le fichier

	Scanner scanner = new Scanner(fichier); //crée le lecteur de fichier
	
	while (scanner.hasNextLine())//lit ligne par ligne
	{	
		String line =  scanner.nextLine(); //line contient ligne
		String[] linecutted = line.split(":"); //split line par le caractère ":"
			switch (linecutted[0]) {
			
			case "L": // numero du niveau
				
				if (isNumeric(linecutted[1])) {levelid = Integer.parseInt(linecutted[1]);} //transforme le string en int puis affecte le niveau
				else levelid = 99;	//Au cas où...
				break;
				
			case "U": // vérouillé ? 
				
				if (linecutted[1].equals("true")){unlocked=true;}
				else {unlocked=false;}
				break;
				
			case "D": //dimensions de la grille
				
				if (linecutted.length!=3){ System.out.println("Erreur de chargement.\n 3D+ error");}
				else
				{
					xdim = Integer.parseInt(linecutted[1]);//affectation abscisse
					ydim = Integer.parseInt(linecutted[2]);//affectation ordonnées
				}
				break;

			default:
				break;
			}
		}
	scanner.close();
	}
catch (FileNotFoundException e)
{
	System.out.println("Erreur de chargement 1.");
	}
}

public void initLstPossb() {
for (int i=0; i<xdim; i++) {
	for (int j=0; j<ydim; j++) {
		if (t[i][j].getType().equals("blanche")) {
			((CaseBlanche)t[i][j]).setPossibilite(new Solveur().listePossibilite(i,j,lstSom));
		}
	}
}
}

public Case[][] parse(){
	
	//initialisation

	int x=0, y=0;
	t = new Case[xdim][ydim];

		try{
			
			File fichier = new File(levelname); //ouvre le fichier
			Scanner scanner = new Scanner(fichier); //crée le lecteur de fichier
			
			
			while (scanner.hasNextLine())//lit ligne par ligne
			{
				
				String line = scanner.nextLine(); //line contient ligne
				String[] linecutted = line.split(":"); //split line par le caractère ":"
		
				switch (linecutted[0]) {
								
				case "G": //LIGNES DE LA GRILLE
					
					if (linecutted.length!=ydim+1/*ATTENTEION au #G dans le fichier*/){ System.out.println("Erreur de chargement.\n invalid "+x+" #G length ");}
					else
					{
						y=0;
						for (int i = 1; i < ydim+1; i++) 
						{
							System.out.println("i = "+i);
							System.out.println("x = "+x);
							System.out.println("y = "+y+"\n");
							System.out.println(linecutted[i]);
							
							switch (linecutted[i]) 
							{
							case "n":
								t[x][y] = new CaseNoire(x,y,new Somme(),new Somme());
							break;
							
							case "v":
								t[x][y] = new CaseBlanche(x,y,0, new ArrayList<Integer>()); 
							break;

							default:
//								if (linecutted[i].startsWith("+"))
//								{
//									String[] Sval=linecutted[i].split(" ");
//									int val=0;
//									val=Integer.parseInt(Sval[1]);
//									
//									t[x][y] = new CaseBlanche(x,y,val, new ArrayList<Integer>());
//								}
								
								t[x][y] = SommeTransfo(linecutted,i,x);
								
							break;
							}
							y++;
						}					
					}
					x++;
					break;

				default:
					break;
				}
				
			}
			scanner.close();
		} catch (FileNotFoundException e) 
		{
			System.out.println("Erreur de chargement 2."); 
		}
		initLstPossb();
		return t;
		}


//public static void main(String[] args) {
//		Niveau niv = new Niveau(2);
//		niv.initialiseNiveau(2);;
//		niv.parse();
//	}
}


