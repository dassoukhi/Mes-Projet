#include "brique.h"
#include "tableaubdb.h"
#include "boutdebrique.h"
#include "window.h"
#include <iostream>
#include <string>
using namespace std;


Brique::Brique() : x(0), y(0), longueur(1), hauteur(1) {}
Brique::Brique(size_t _x, size_t _y, size_t _l, size_t _h, TableauBDB _T) : x(_x), y(_y), longueur(_l), hauteur(_h), T(_T) {}
Brique::Brique(size_t _x, size_t _y, size_t _l, size_t _h, size_t _r, string _s) : x(_x), y(_y), longueur(_l), hauteur(_h), type(_s) { // crée une brique en fonction de son type
	TableauBDB TAB;
	if (_s == "rectangle") {
		for (int j=0; j<_h; j++) {
			for (int k=0; k<_l; k++) {
				BoutDeBrique b2(k,j,2,1,_r);
				TAB.push_back(b2);
			}
		}
	}
	if (_s == "triangle") {
		BoutDeBrique b1((_l/2)-1,0,((_l/2)/2)/2,1,_r);
		TAB.push_back(b1);
		BoutDeBrique b2((_l/2)/2,1,(_l/2),1,_r);
		TAB.push_back(b2);
		for (int j=2; j<_h; j++) {
			for (int k=0; k<_l; k++) {
				BoutDeBrique b3(k,j,2,1,_r);
				TAB.push_back(b3);
			}
		}
	}
	T = TAB;
}


//Accesseurs en lecture
size_t Brique::getX() const { return x; }
size_t Brique::getY() const { return y; }
size_t Brique::getLongueur() const { return longueur; }
size_t Brique::getHauteur() const { return hauteur; }
string Brique::getType() const { return type; }

size_t Brique::getXGauche() const { return x; }
size_t Brique::getXDroit() const { return x+longueur; }
size_t Brique::getYHaut() const { return y; }
size_t Brique::getYBas() const { return y+hauteur-1; }

//Accesseurs en ecriture
void Brique::setX(size_t _x) { x = _x; }
void Brique::setY(size_t _y) { y = _y; }
void Brique::setLongueur(size_t l) { longueur = l; }
void Brique::setHauteur(size_t h) { hauteur = h; }
void Brique::setResistanceBR(size_t r) {
	for (int i=1; i<T.getTaille(); i++) {
		T[i].setResistance(r);
	}
}

bool Brique::estMorte() {
	bool r = true;
	for (int i=1; i<T.getTaille(); i++) {
		if (T[i].getResistance() > 0) {
			r = false;
		}
	}
	return r;
}

void Brique::updateBrique() {
	int t = T.getTaille();
	for (int i=1; i<t; i++) {
	 	T[i].update();
	 	if (T[i].getResistance() == 0) {
	 	 	//T.erase(i);
		}

	}
}

BoutDeBrique& Brique::operator[](int i) {
	return T[i];
}

BoutDeBrique Brique::operator[](int i) const {
	return T[i];
}


Brique::~Brique() {
	//T.~TableauBDB();
} 

