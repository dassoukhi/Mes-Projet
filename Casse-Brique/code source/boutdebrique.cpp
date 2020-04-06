#include "boutdebrique.h"
#include <iostream>
#include <string>
using namespace std;


BoutDeBrique::BoutDeBrique() : x(0), y(0), longueur(2), hauteur(1), resistance(1) {} // bout de brique de base 
BoutDeBrique::BoutDeBrique(size_t x, size_t y, size_t l, size_t h, size_t r) : x(x), y(y), longueur(l), hauteur(h), resistance(r), coul(r==0? BLACK : r==1? WHITE : r==2? YELLOW : r==3? RED : r==4? CYAN : r==5? BLUE : r==6? GREEN : MAGENTA) {} // attribut la couleur en fonction de la resistance donnée

//Accesseurs en lecture
size_t BoutDeBrique::getX() const { return x; }
size_t BoutDeBrique::getY() const { return y; }
size_t BoutDeBrique::getLongueur() const { return longueur; }
size_t BoutDeBrique::getHauteur() const { return hauteur; }
size_t BoutDeBrique::getResistance() const { return resistance; }
BoutDeBrique::Couleur BoutDeBrique::getCouleur() const {
	return coul;
}

//Accesseurs en ecriture
void BoutDeBrique::setX(size_t _x) { x = _x; }
void BoutDeBrique::setY(size_t _y) { y = _y; }
void BoutDeBrique::setLongueur(size_t l) { longueur = l; }
void BoutDeBrique::setHauteur(size_t h) { hauteur = h; }
void BoutDeBrique::setResistance(size_t r) { resistance = r; }
void BoutDeBrique::setCouleur(Couleur c) { coul = c; }

void BoutDeBrique::update() {
	// Change la couleur en fonctions de la resistance du BoutDeBrique
	if (resistance != 0) {
		resistance--;
	}
	switch (resistance) {
	case 0:
		coul = BLACK;
		break;
	case 1:
		coul = WHITE;
		break;
	case 2:
		coul = YELLOW;
		break;
	case 3:
		coul = RED;
		break;
	case 4:
		coul = CYAN;
		break;
	case 5:
		coul = BLUE;
		break;
	case 6:
		coul = GREEN;
		break;
	case 7:
		coul = MAGENTA;
		break;
	}
}

BoutDeBrique::~BoutDeBrique() {}