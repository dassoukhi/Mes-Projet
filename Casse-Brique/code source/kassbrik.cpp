#include <iostream>
#include <string>
#include <time.h>
#include <cstddef>
#include <ctime>
#include <string>
#include <unistd.h>
#include "balle.h"
#include "window.h"
#include "joueur.h"
#include "terrain_v2.h"
#include "boutdebrique.h"
#include "brique.h"
#include "populationbriqueV2.h"
#include "tableaubdb.h"
#include "option.h"
#include "taboption.h"
#include "raquette2.h"
#include "menu.h"

using namespace std;

void myprogram()
{
	string s = "";
	int u=0;
	Menu dass;
	dass.afficheMenu(u,s);

	while(u==0)
	{
		int h = LINES/1.2, w = COLS/1.2;

		PopulationBrique POP;
		POP.niveau1(5,3,"rectangle","norandom",h,w);

		string str = creerRaq();
		Raquette r(w/2-str.length()/2,h-3,str,WGREEN);

		Window _w1(h,w,1,1,' ');
		int Xw = _w1.getX();
		int Yw = _w1.getY();
		
		Joueur j;
		
		Terrain jeu(_w1,POP,r,j);
		(jeu.getW1()).setCouleurBordure(BBLUE);

		Window perso(5, COLS-w-7, Xw+w+3, Yw, ' ');
		perso.setCouleurBordure(BYELLOW);

		perso.print(1,0,"Joueur : "+j.getNom());

		string score = to_string(j.getScore());
		perso.print(1,2,"Score : "+score);

		string vie = to_string(j.getVie());
		perso.print(1,4,"Vie : "+vie);

		Window aide(LINES - perso.getHauteur()-7,perso.getLargeur(),perso.getX(),perso.getY()+perso.getHauteur()+3, ' ');
		aide.setCouleurBordure(BGREEN);

		int ch;
		jeu.affiche();

		while ((ch = getch()) != ' ') //permet de déplacer la raquette avant d'avoir lancé la balle
		{
			switch (ch) 
			{
				case 'Q' : 
				{
					return;
				}
				break;

				default : 
				{
					jeu.startMove(ch,s);
				}
				break;
			}
		}

		string tmp = "aucun";
		while ((ch = getch()) != 'Q') 
		{
			if (s == "cactus")
			{
				//easter egg ?
				int couleur;
				srand(time(NULL));
				couleur = rand()%14+1;
				Color c = inttoCol(couleur);
				(jeu.getW1()).setCouleurBordure(c);
				perso.setCouleurFenetre(c);
				aide.setCouleurFenetre(c);
				tmp ="cactus";
			}

			jeu.move(ch,tmp);
			jeu.collisionBrique();
			jeu.affiche();
			jeu.collisionTerrain(ch,tmp);
			string score = to_string((jeu.getJoueur()).getScore());
			perso.print(1,2,"Score : "+score);
			string vie = to_string((jeu.getJoueur()).getVie());
			perso.print(1,4,"Vie : "+vie);

			if (jeu.nextLvl("noforce")) 
			{
				while ((ch = getch()) != ' ')//sert à replacer la raquette apres avoir perdu une vie
				{
					switch (ch)
					{
						case 'Q' : 
						{
							return;
						}
						break;
						default : 
						{
							jeu.startMove(ch,tmp);

						}
						break;
					}
					(jeu.getB()).setVerti(-1);
				}
			}
			if (jeu.loose()) break;
			
			aide.print(2,2,"LA BRIQUEEEEEEEEEEEEEEEEEEEEEE");
		}

		if (jeu.loose()) 
		{
			aide.print(2,2,"Vous avez perdu..                                                                                                                                     :) ");
			aide.print(2,6,"Rejouer? Y or N");
			char ch;
			while (((ch = getch()) != 'Q'))
			{
				if (ch == 'N')
				{
					u=1;
					break;
				}
				else if (ch == 'Y')
				{
					u=0;
					break;
				}
			}
		}
	}
}

int main(int argc, char const *argv[])
{

	string h("-h");
	string v("-v");
	string a("-a");
	TabOption opts;
	opts.addOption(Option (0, "--help", "-h","Affiche l'aide du jeu", Option::AUCUN));
	opts.addOption(Option (1, "--version", "-v","Affiche la version du jeu", Option::AUCUN));
	opts.addOption(Option (2, "--auteur", "-a","Affiche les auteurs du jeu", Option::AUCUN));

	switch(argc){
		case 1 :	
		{
			startProgramX();
			myprogram();
			stopProgramX();
		}
		break;

		case 2 :	
		{
			if (argv[1] == h)
			{
				cout<<"Utiliser '<-' et '->' pour un déplacement important à gauche ou à droite,'a' et 'e' pour plus de précision"<<endl;
				cout<<"'ESPACE' pour lancer la balle"<<endl;
				cout<<"'MAJ' + 'q' pour quitter"<<endl;
				cout<<"'MAJ' + 'n' pour forcer le passage au niveau suivant apres que la balle ait touché le sol"<<endl; 
				cout<<"Une fois à court de vie 'MAJ' + 'y' pour rjouer, 'MAJ' + 'n' pour quiter"<<endl;
			}
				
				
			else if (argv[1] == v)
			{
				cout<<"La version de jeu est la CAPEP (Cela Aurait Pu Être Pire)"<<endl;
			}
			else if (argv[1] == a)
			{
				cout<<"Jeu réalisé par : Fontaine Quentin - Jehanno Jules - Prud'Homme Gateau Sebastien - Saleh Dassoukhi"<<endl;
			} else
			{
				cout << "Options passées en ligne de commande au programme " << argv[0] << " :" << endl;

				for (int i = 1; i < argc ; i++)
				{
					if (opts.getOptionId(argv[i]) != -1)
					{
						cout << "L'option " << argv[i] << " a été trouvée.";
						if (opts.optionHasArgument(argv[i]))
						{
							cout << " Elle attend un argument de type " << type2String(opts.optionHasArgumentType(argv[i]));
							cout << " => " << (++i < argc ? argv[i] : "Erreur");
						}
						cout << endl;
					} 
					else
					{
						cout << "Usage : " << argv[0] << " [Options]" << endl;
						opts.print();
					}
				}
			}
		}
		break;
	}
	return 0;
}