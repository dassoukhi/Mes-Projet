#include <iostream>
#include "populationbriqueV2.h"
#include "brique.h"
#include "window.h"
#include <time.h>
#include <ctime>
#include <cstddef>
#include <unistd.h>

using namespace std;

PopulationBrique::PopulationBrique() : n(1), nMax(1), T(new Brique[1]), lvl(1) {

}

PopulationBrique::PopulationBrique(size_t _n) : n(_n), nMax(_n), lvl(1) { T = new Brique[n]; }

PopulationBrique::PopulationBrique(const PopulationBrique& A) : n(A.n), nMax(A.nMax), lvl(A.lvl) {
	T=new Brique[nMax];
	for(size_t i=0; i<n; i++) {
		T[i] = A.T[i];
	}
}

PopulationBrique::~PopulationBrique() { delete[] T; } 

PopulationBrique& PopulationBrique::operator=(const PopulationBrique& A) {
	if (this!=&A) {
		delete[] T;
		n=A.n;
		nMax = n;
		T=new Brique[nMax];
		for(size_t i=0; i<n; i++) {
			T[i]=A.T[i];
		}
	}
	return *this;
}

Brique& PopulationBrique::operator[](int i) {
	if ((i>=0) && (i<n)) {
		return T[i];
	}
	else {
		cerr << "problème avec l'index POP!"<<i;
		exit(1);
	}
}

Brique PopulationBrique::operator[](int i) const {
	if ((i>=0) && (i<n)) {
		return T[i];
	}
	else {
		cerr << "problème avec l'index POP!";
		exit(1);
	}
}

size_t PopulationBrique::getTaille() const { return n; }
size_t PopulationBrique::getLvl() const { return lvl; }

void PopulationBrique::setTaille(size_t _t) { 
	n = _t;
	nMax = _t;
}
void PopulationBrique::setLvl(size_t _l) { lvl = _l; }

bool estEgale(const Brique& B1, const Brique& B2) {
	return ((B1.getX() == B2.getX()) && (B1.getY() == B2.getY()));
}

int PopulationBrique::find(const Brique& B) {
	for (size_t i=0; i<n; i++) {
		if (estEgale(B, T[i])) { return i; }
	}
	return -1;
}

void PopulationBrique::extend(){
  if (n<nMax) return;
  nMax=2*n;
  Brique* tmp=new Brique[nMax];
  for(size_t i=0;i<n;i++){tmp[i]=T[i];}
  delete[] T;
  T=tmp;
}

void PopulationBrique::push_back(const Brique& B){
  extend();
  T[n]=B;
  n++;
}


void PopulationBrique::erase(int indice) {
	if (indice >= 1 && indice < (int)n) {
		for (int i=indice; i<n-1; i++) {
			T[i] = T[i+1];
		}
		n--;
	}
}

void PopulationBrique::clearPOP() {
	for (int i=1; i<n; i++) {
		T[i].setResistanceBR(0);
		updateCoul(T[i]);
	}
	n = 1;
	nMax = 1;
}

void PopulationBrique::updateCoul(Brique& B) {
	B.updateBrique();

}

void PopulationBrique::niveau1(int firstx, int firsty, string _s, string _r2, int _h, int _w) { // coordonées de départ (X,Y), type des briques, random ou norandom, taille du terrain (X,Y)
	int x=firstx,y=firsty;
	int tmp=0,tmp2=0; // variable qui garde la derniere position X de la derniere brique placée, pareil pour Y
	int ecart=3,ecart2=0.5; // écart entre chaque brique sur X et ecart2 entre chaque brique sur Y
	string s = _s;
	string r2 = _r2;
	(*this).setLvl(1); // Set le niveau actuel du jeu

	// Modifie la taille des briques en fonction de la taille du terrain
	int li;
	if (_h > 40) {
		li = (_h-(_h/2)-firsty)/(7);
	}
	else if (_h > 20) {
		li = (_h-(_h/3)-firsty)/(7);
	}
	else {
		li = (_h-1-firsty)/(7);
	}
	int h = li;
	int co = (_w-firstx)/(10);
	int lg = co-ecart;

	// mur(*this,1,10,s,r2,lg,h,7,x,y,2,1,firstx,firsty,tmp,tmp2,ecart,ecart2);
	// mur(*this,1,10,s,r2,lg,h,6,x,y,2,1,firstx,firsty,tmp,tmp2,ecart,ecart2);
	// mur(*this,1,10,s,r2,lg,h,5,x,y,2,1,firstx,firsty,tmp,tmp2,ecart,ecart2);
	// mur(*this,1,10,s,r2,lg,h,4,x,y,2,1,firstx,firsty,tmp,tmp2,ecart,ecart2);
	mur(*this,1,10,s,r2,lg,h,3,x,y,2,1,firstx,firsty,tmp,tmp2,ecart,ecart2);
	mur(*this,1,10,s,r2,lg,h,2,x,y,2,1,firstx,firsty,tmp,tmp2,ecart,ecart2);
	mur(*this,1,10,s,r2,lg,h,1,x,y,2,1,firstx,firsty,tmp,tmp2,ecart,ecart2);
}


// Même principe que pour le niveau 1 mais avec des dispositions différentes
void PopulationBrique::niveau2(int firstx, int firsty, string _s, string _r2, int _h, int _w) {
	int x=firstx,y=firsty;
	int tmp=0,tmp2=0;
	int ecart=4,ecart2=1;
	string s = _s;
	string r2 = _r2;
	(*this).setLvl(2);

	int li;
	if (_h > 40) {
		li = (_h-(_h/2)-firsty)/(7);
	}
	else if (_h > 20) {
		li = (_h-(_h/3)-firsty)/(7);
	}
	else {
		li = (_h-1-firsty)/(7);
	}
	int h = li;
	int co = (_w-firstx)/(10);
	int lg = co-ecart;

	mur(*this,1,10,s,r2,lg,h,7,x,y,2,1,firstx,firsty,tmp,tmp2,ecart,ecart2);
	mur(*this,1,10,s,r2,lg,h,6,x,y,2,1,firstx,firsty,tmp,tmp2,ecart,ecart2);
	mur(*this,1,10,s,r2,lg,h,5,x,y,2,1,firstx,firsty,tmp,tmp2,ecart,ecart2);

	mur(*this,1,10,s,r2,lg,h,7,x,y,2,1,firstx,firsty,tmp,tmp2,ecart,ecart2);
	// mur(*this,1,10,s,r2,lg,h,6,x,y,2,1,firstx,firsty,tmp,tmp2,ecart,ecart2);
	// mur(*this,1,10,s,r2,lg,h,5,x,y,2,1,firstx,firsty,tmp,tmp2,ecart,ecart2);

	// mur(*this,1,10,s,r2,lg,h,6,x,y,0,0,firstx,firsty,tmp,tmp2,ecart,ecart2);
	// mur(*this,1,10,s,r2,lg,h,5,x,y,2,1,firstx,firsty,tmp,tmp2,ecart,ecart2);
	// mur(*this,1,10,s,r2,lg,h,4,x,y,1,0,firstx,firsty,tmp,tmp2,ecart,ecart2);
	// mur(*this,_h,_w,s,r2,20,3,5,x,y,1,2,firstx,firsty,tmp,tmp2,ecart,ecart2);
	// mur(*this,_h,_w,s,r2,20,3,3,x,y,2,1,firstx,firsty,tmp,tmp2,ecart,ecart2);
	// mur(*this,_h,_w,s,r2,20,3,2,x,y,2,1,firstx,firsty,tmp,tmp2,ecart,ecart2);
	// mur(*this,_h,_w,s,r2,20,3,1,x,y,1,0,firstx,firsty,tmp,tmp2,ecart,ecart2);
}

void PopulationBrique::niveau3(int firstx, int firsty, string _s, string _r2, int _h, int _w) {
	int x=firstx,y=firsty;
	int tmp=0,tmp2=0,mode=2,mode2=1; // mode correspond à si l'on veut continuer de placer sur l'axe des X, mode2 sur l'axe des Y
	int ecart=2,ecart2=1;
	string s = _s;
	string r2 = _r2;
	(*this).setLvl(3);

	murMulticolor(*this,_h,_w,s,r2,5,3,x,y,firstx,firsty,tmp,tmp2,4,3);
}

void PopulationBrique::niveau4(int firstx, int firsty, string _r2, int _h, int _w) {
	int x=firstx,y=firsty;
	int tmp=0,tmp2=0,mode=2,mode2=1;
	int ecart=2,ecart2=1;
	string s = "random"; // couleur obligatoirement random
	string r2 = _r2;
	(*this).setLvl(4);



	murMulticolor(*this,_h,_w,s,r2,10,3,x,y,firstx,firsty,tmp,tmp2,4,3);
}

// Affiche le message gagner à l'ecran avec des briques
void affichegagner(PopulationBrique& POP, int firstx, int firsty) {
	Brique b1(firstx,firsty,3,3,7,"rectangle");
	Brique b2(firstx+2,firsty+3,3,3,7,"rectangle");
	Brique b3(firstx+5,firsty+6,3,3,7,"rectangle");
	Brique b4(firstx+13,firsty+3,3,3,7,"rectangle");
	Brique b5(firstx+10,firsty,3,3,7,"rectangle");
	Brique b6(firstx+7,firsty+3,3,3,7,"rectangle");
	Brique b7(firstx+15,firsty+6,3,3,7,"rectangle");
	Brique b8(firstx+18,firsty+3,3,3,7,"rectangle");
	Brique b9(firstx+20,firsty,3,3,7,"rectangle");
	POP.push_back(b1);
	POP.push_back(b2);
	POP.push_back(b3);
	POP.push_back(b4);
	POP.push_back(b5);
	POP.push_back(b6);
	POP.push_back(b7);
	POP.push_back(b8);
	POP.push_back(b9);

	Brique b10(firstx+27,firsty,3,3,7,"rectangle");
	Brique b11(firstx+27,firsty+3,3,3,7,"rectangle");
	Brique b12(firstx+27,firsty+6,3,3,7,"rectangle");
	POP.push_back(b10);
	POP.push_back(b11);
	POP.push_back(b12);

	Brique b13(firstx+35,firsty,3,3,7,"rectangle");
	Brique b14(firstx+35,firsty+3,3,3,7,"rectangle");
	Brique b15(firstx+35,firsty+6,3,3,7,"rectangle");
	Brique b16(firstx+38,firsty,3,3,7,"rectangle");
	Brique b17(firstx+40,firsty+3,3,3,7,"rectangle");
	Brique b18(firstx+42,firsty+6,3,3,7,"rectangle");
	Brique b19(firstx+45,firsty+6,3,3,7,"rectangle");
	Brique b20(firstx+45,firsty+3,3,3,7,"rectangle");
	Brique b21(firstx+45,firsty,3,3,7,"rectangle");
	POP.push_back(b13);
	POP.push_back(b14);
	POP.push_back(b15);
	POP.push_back(b16);
	POP.push_back(b17);
	POP.push_back(b18);
	POP.push_back(b19);
	POP.push_back(b20);
	POP.push_back(b21);

}

void PopulationBrique::gagner(int firstx, int firsty, int _h, int _w) {
	affichegagner(*this,_w/3,_h/3);
}

// Paramètres: PopulationBrique, nombre de ligne de brique que l'on veut, pareil en colonne, type des briques, random ou no random, longueur et hauteur des briques, coordonées des briques, mode de changement sur X ou Y, premières coordonées de placement, variable qui garde les dernières positions des briques, ecart sur X et sur Y
void mur(PopulationBrique& POP, int nbrligne, int nbrBrique, string& s, string& r2, int lg, int ht, int resistance, int& coordx, int& coordy, int mode, int mode2, int firstx, int firsty, int& tmp, int& tmp2, int ecart, int ecart2) {
	
	int distance=lg+ecart; // distance sur l'axe des X entre la position X d'une brique et la prochaine position X de la prochaine brique
	int distance2 = ht + ecart2; // distance sur l'axe des Y entre la position Y d'une brique et la prochaine position Y de la prochaine brique

	// int ligne = ((nbrBrique-3-coordy)/distance;
	// int colonne = (nbrligne-3-coordx)/distance2;

	if (mode == 1) {
		coordx = tmp; //continue de placer sur X après la dernière brique
		if (mode2 == 1) { // remet Y à la position de départ
			coordy = firsty;
			tmp2 = firsty;
		}
		if (mode2 == 2) { // positionne à une positon plus haut
			tmp2 = firsty-1;
		}
		if (mode2 == 3) { // continue de placer sur Y après la dernière brique
			coordy = tmp2;
		}
		if (mode2 == 4) { 
			coordy = tmp2+firsty-1;
		}
	}
	if (mode == 2) { //continue de placer sur Y après la dernière brique
		coordy = tmp2;
		if (mode2 == 1) { // repositionne X à la postion de départ
			coordx = firstx;
			tmp = firstx;
			tmp2 = firsty-1;
		}
		if (mode2 == 2) { // positionne à une position plus à gauche
			tmp2 = firsty-1;
		}
	}

	int x=coordx,y=coordy,l=lg,h=ht,r=resistance; // set les paramètre pour la création des briques
	srand(time(NULL));
	for (int k=0; k<nbrligne; k++) {
		for (int i=0; i<nbrBrique; i++) {
			if (r2 == "random") {
				r = rand()%6+1; // resistance random
			}
			Brique b(x,y,l,h,r,s);
			POP.push_back(b);
			x+=distance;
			
		}
		x=coordx;
		y+=distance2;
		
	}
	tmp += (nbrBrique*distance);
	tmp2 += y;
}

void murMulticolor(PopulationBrique& POP, int nbrligne, int nbrBrique, string& s, string& r2, int lg, int ht, int& coordx, int& coordy, int firstx, int firsty, int& tmp, int& tmp2, int ecart, int ecart2) {

	int x=coordx,y=coordy,l=lg,h=ht;
	int distance=lg+ecart;
	int distance2 = ht + ecart2;
	int r,num;

	int ligne = (nbrBrique-3-coordy)/distance; // défini le nombre de ligne en fonction de la taille du terrain
	int colonne = (nbrligne-(nbrligne/2)-coordx)/distance2; // défini le nombre de colonne en fonction de la taille du terrain

	srand(time(NULL));
	for (int k=0; k<colonne; k++) {
		for (int i=0; i<ligne; i++) {
			
			r = rand()%6+1;
			if (r2 == "random") { // forme random
				if (s == "rectangle") {
					l = rand()%20+1;
					h = rand()%5+1;
				}
				if (s == "triangle") {
					l = rand()%20+1;
					h = rand()%3+2;
				}
			}
			if (s == "random") { // resistance random
				num = rand()%2+1;
				if (num == 1) {
					Brique b(x,y,l,h,r,"rectangle");
					POP.push_back(b);
				}
				if (num == 2) {
					Brique b(x,y,l,h,r,"triangle");
					POP.push_back(b);
				}
			}
			else {
				Brique b(x,y,l,h,r,s);
				POP.push_back(b);
			}
			
			
			x+=distance;
			
		}
		x=coordx;
		y+=distance2;
		
	}
	tmp += (nbrBrique*distance);
	tmp2 += y;
}
