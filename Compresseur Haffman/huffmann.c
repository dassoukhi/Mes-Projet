#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#ifndef EOF
#define EOF (-1)
#endif

int taille=0;
int nbocc[256];

//return 0 si errors

//-------------------------------------------------------------------------
//-------------------------------------------------------------------------
//-------------------------------------------------------------------------

typedef int bool;
#define true 1
#define false 0

typedef struct plante plante;
struct plante
{
	bool feuille;	//si true, plante est une feuille, sinon c'est un noeud
	char ch;		//caractere stocké si feuille == True
	int nbocc;		//si feuille alors occurences du caractere, sinon NULL
	plante *pere;	//adresse du pere
	plante *fils_g;	//adresse du fils gauche si plante est un noeud , NULL si feuille
	plante *fils_d;	//adresse du fils droit  si plante est un noeud , NULL si feuille
	char* d;		//chaine pour stocker le code binaire de chaque caractere
};

//--------------------------------------------------------------//
//--------------------------------------------------------------//
//--------------------------------------------------------------//
plante creerplante() //fonction qui crée des noeuds... voilà quoi XD
{

  plante p;
  {
    p.feuille =  false;
    p.ch =  ' ';
    p.nbocc =  0;
    p.pere =  NULL;
    p.fils_g =  NULL;
    p.fils_d =  NULL;
    p.d="²";
  }
  return p;
}
//--------------------------------------------------------------//
//--------------------------------------------------------------//
//--------------------------------------------------------------//
//fonction qui affiche grossierement les plantes
//	||
//  \/	
void afficheplante(plante p) 						
{
 	printf("\n %i '%c' %i %p %p %p %s \n", p.feuille, p.ch, p.nbocc, p.pere, p.fils_g, p.fils_d, p.d );
}

//--------------------------------------------------------------//
//--------------------------------------------------------------//
//  FONCTION QUI RANGE DANS DES STRUCTURES LES CARACTERES DONT  //
//  l'OCCURENCE EST > 0------||---------------------------------//
//---------------------------||---------------------------------//
//---------------------------\/---------------------------------//

plante* rangefeuille()
{
plante* T = malloc((2*taille-1)*sizeof(plante));

int j=0;

for (int i = 0; i < 256; i++)		//on malloc la taille nécéssaire au tableau,
{									//puis on parcourt nbocc et on transforme
									//chaque caractre en feuille tout en gardant
	if (nbocc[i]>0)					//son nombre d'occurence dans le fichier
	{
		plante p;
		{
			p.feuille =  true;
			p.ch =  i;
			p.nbocc =  nbocc[i];
			p.pere =  NULL;
			p.fils_g =  NULL;
			p.fils_d =  NULL;
			p.d="²";
		}
		T[j]=p;
		j++;						
	}									
}
return T;
}

//-------------------------------------------------------------------------
//-------------------------------------------------------------------------
//-------------------------------------------------------------------------
int calculnbocc(char* monfic){ //NE PAS TOUCHER, truc du prof QUI MARCHE !!

FILE* f = fopen(monfic, "r");  //w,rw,w+      man fopen
	if (f==NULL)
		{return 1;}

	int c;
	while (EOF != (c=fgetc(f))){
		if (nbocc[c]==0)
		{
			taille++;
		}
		nbocc[c]++;
	}
	fclose(f);
	return 0;
}

//--------------------------------------------------------------//
//--------------------------------------------------------------//
//  FONCTION QUI RENVOIE LA PLUS PETITE OCCURRENCE PARMI LES    //
//  PLANTES DU TABLEAU DE    ||---------------------------------//
//-------------- PLANTES     ||---------------------------------//
//---------------------------\/---------------------------------//
int pluspetiteoccur(plante* T)
{
	plante a= creerplante();
	int x = 0;
	a=T[x];

	while(a.pere != 0) //pour etre min il faut qu'il soit sans père
	{	x++;		//du coup on cherche d'abord le premier sans père
		a=T[x];		//parmi les plantes du tableau
	}
	int min = a.nbocc;   //on l'affecte a min et on va le verif dessous

	for (int i = 0; i < taille; i++) //ici c'est taille ou taille-1
	{	if  (T[i].pere == NULL)		 //les deux marchent (je sais pas pq)
		{
			if (T[i].nbocc <= min)
			{
				min = T[i].nbocc;
			}
		}
	}
	//printf("le min est : %i \n",min );
	return min;
}
//--------------------------------------------------------------//
//--------------------------------------------------------------//
// FONCTION QUI CREE L'ARBRE, ELLE CREE DES NOEUDS QUI SERVENT  //
// DE PERE AUX STRUCTURES DEJA DANS LE TABLEAU ET LES RAJOUTE   //
// ENSUITE ELLES-AUSSI DANS LE TABLEAU  ------------------------//
//---------------------------||---------------------------------//
//---------------------------||---------------------------------//
//---------------------------\/---------------------------------//
void creerarbre(plante* T)
{
	int nbfeuilles=taille;
	int maxnoeuds=2*taille-1; //demande à jules pour 2*taille-1 (formule)

	for (int x = 0; x < (maxnoeuds-nbfeuilles); x++) 
	{
		plante p= creerplante();	//on crée la plante qui servira de père
		
		int i = 0;
		while( i< taille && p.fils_g== NULL)
		{
			if (T[i].pere==NULL && T[i].nbocc == pluspetiteoccur(T))
			{
			  T[i].pere = &p;		//on affecte le pere a son fils gauche
		      p.fils_g  = &T[i];	//on affecte le fils gauche à son père
		      p.nbocc = (T[i].nbocc); //le nb occ du pere doit etre la somme
			}						  // des nb occ de ses fils 
			i++;
		}	//-------------------------------------------------------//
			//on a affecté le fils gauche on recommence pour le droit//
			//-------------------------------------------------------//
		int j=0;
		while( j< taille && p.fils_d== NULL)
		{
			if (T[j].pere==NULL && T[j].nbocc == pluspetiteoccur(T))
			{
			  T[j].pere = &p;		//on affecte le pere a son fils droit
		      p.fils_d  = &T[j];	//on affecte le fils droit à son père
		      p.nbocc  += (T[j].nbocc);
			}
			j++;
		}
		  T[taille]=p; //on place le nouveau père à la position 'taille'
		  			  // on incrémente taille pour pouvoir mettre le prochain apres

	      //printf(" noeud créé, new taille = %i; T[].nbocc =%i\n", ++taille,T[taille].nbocc );
	      taille++;
		}
}

//-------------------------------------------------------------------------
//-------------------------------------------------------------------------
//-------------------------------------------------------------------------

void parcours(plante *T,char* bin)
{
	char* q1=malloc(strlen(bin)+1);
	char* q2=malloc(strlen(bin)+1);
	strcpy(q1,bin);
	strcpy(q2,bin);

	if(T->feuille==1)
	{
		T->d=q1;
		printf("\n T.d : %s\n",T->d );
		afficheplante(*T);
	}
	else
	{
		//printf("\n q1 =  %s\n",q1 );
		parcours(T->fils_g, strcat(q1,"0"));
		//printf("\n q2 =  %s\n",q2 );
		parcours(T->fils_d, strcat(q2,"1"));
	}
}

void comprim(char* monfic, plante* T){

FILE* f = fopen(monfic, "r");
	if (f==NULL)
		{return;}
char* comp;

FILE* a= fopen(comp, "w+");

	int c;
	while (EOF != (c=fgetc(f)))
	{
		for (int i = 0; i <(taille-(taille-1)/2) ; i++)
		{
			if (c==T[i].ch)
			{
				fprintf(a, "%s",T[i].d );


				/*for (int j = 0; j < strlen(T[j].d); j++)
				{
					((T[i].d)[j], a);
				}*/	
			}
		}
	}
	fclose(f);
	fclose(a);
	return;
}






















int main(int argc, char *argv[])
{
	if (argc != 2)
	{
		
		(stderr, "syntaxe : 1\n");
		return 1;
	}

	int res=calculnbocc(argv[1]);
	if (res == 1 )
	{
		printf("Erreur d'ouverture de fichier\n");
		return 2;
	}

printf(" taille = %i\n",taille );

/*int bintab[taille];*/

	for (int i = 0; i < 256; i++) //on a pas BESOIN de ça mais
	{							  //ça aide à debugger
		if (nbocc[i]>0)
		{
			printf("'%c' apparait %i fois\n",i,nbocc[i]);
		}
	}
	plante* tabfeuille = rangefeuille(); //DEUXIEME TRUC :
									//mettre les caracteres dans struct

	creerarbre(tabfeuille); //TROISIEME : faire l'arbre (et ça marche ^^)

printf(" \n------------------------------------------\n");

parcours(&(tabfeuille[taille-1]),"");
printf(" taille = %i\n",taille );

printf(" \n------------------------------------------\n");

/*for (int i = 0; i < taille; i++)
{
	afficheplante(tabfeuille[i]);
}*/


comprim(argv[1], tabfeuille);
	return 0;
}
