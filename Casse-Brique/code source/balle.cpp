#include <iostream>
#include "balle.h"
#include "brique.h"


// Constructeurs
Balle::Balle() : _x(1), _y(1), _horiz(0), _verti(-1) {}
Balle::Balle(size_t x, size_t y): _x(x), _y(y),_horiz(0), _verti(-1) {}

// Accesseurs en lecture
size_t Balle::getX() const {return _x;}
size_t Balle::getY() const {return _y;}
short Balle::getHoriz() const {return _horiz;}
short Balle::getVerti() const {return _verti;}

// Accesseurs en écriture
void Balle::setX(size_t x){
	_x=x;
}

void Balle::setY(size_t y){
	_y=y;
}

void Balle::setHoriz(short horiz) {
	_horiz=horiz;
}

void Balle::setVerti(short verti){
	_verti=verti;
}


void Balle::update(){
	_x+=_horiz;
	_y+=_verti;
}

bool Balle::toucheBrique(Brique& b) {
	if (b.getType() == "rectangle") {
		if ( ((_x+1 >= b.getXGauche()) && (_x+1 <= b.getXDroit())) && ((_y+1 >= b.getYBas()) && (_y+1 <= b.getYHaut())) ) {
			return true;
		}
		else {
			return false;
		}
	}
	if (b.getType() == "triangle") {
		if ( (((_x+1 >= (b.getXGauche() + b[1].getX()) ) && (_x+1 <= (b.getXGauche() + b[1].getX() + b[1].getLongueur()) )) && ((_y+1 >= b.getYHaut() + b[1].getHauteur() + b[1].getY()) && (_y+1 <= b.getYHaut()))) \
			|| (((_x+1 >= b.getXGauche() + b[2].getX()) && (_x+1 <= b.getXGauche() + b[2].getX() + b[2].getLongueur())) && ((_y+1 >= b.getYHaut() + b[2].getHauteur() + b[2].getY()) && (_y+1 <= b.getYHaut() + b[2].getY()))) \
			|| (((_x+1 >= b.getXGauche()) && (_x+1 <= b.getXDroit())) && ((_y+1 >= b.getYBas()) && (_y+1 <= b.getYHaut() + b[3].getY()))) ) { \
			return true;
		}
		else {
			return false;
		}
	}
}

bool Balle::toucheBas(Brique& b) {
	if ( ((_x >= b.getXGauche()) && (_x <= b.getXDroit())) && (_y+_verti == b.getYBas()) ){
		return true;
	}
	else {
		return false;
	}
}

bool Balle::toucheHaut(Brique& b) {
	if (b.getType() == "rectangle") {
		if ( ((_x >= b.getXGauche()) && (_x <= b.getXDroit())) && (_y+_verti == b.getYHaut()) ) {
			return true;
		}
		else {
			return false;
		}
	}
	if (b.getType() == "triangle") {
		if ( (((_x >= (b.getXGauche() + b[1].getX()) ) && (_x <= (b.getXGauche() + b[1].getX() + b[1].getLongueur()) )) && ((_y+1 >= b.getYHaut() + b[1].getHauteur() + b[1].getY()) && (_y+1 <= b.getYHaut()))) \
			|| (((_x >= b.getXGauche() + b[2].getX()) && (_x <= b.getXGauche() + b[2].getX() + b[2].getLongueur()) && !((_x >= b.getXGauche() + b[1].getX()) && (_x <= (b.getXGauche() + b[1].getX() + b[1].getLongueur()))) && ((_y+1 >= b.getYHaut() + b[2].getHauteur() + b[2].getY()) && (_y+1 <= b.getYHaut() + b[2].getY()))) ) \
			|| (((_x >= b.getXGauche()) && (_x <= b.getXDroit())) && !((((_x >= b.getXGauche() + b[2].getX()) && (_x <= b.getXGauche() + b[2].getX() + b[2].getLongueur())))) && ((_y+1 >= b.getYBas()) && (_y+1 <= b.getYHaut() + b[3].getY()))) ) { \
			return true;
		}
		else {
			return false;
		}
	}
}

bool Balle::toucheGauche(Brique& b) {
	if (b.getType() == "rectangle") {
		if ( (_x+_horiz == b.getXGauche()) && ((_y <= b.getYBas()) && (_y >= b.getYHaut())) ){
			return true;
		}
		else {
			return false;
		}
	}
	if (b.getType() == "triangle") {
		if ( (((_x+1 >= (b.getXGauche() + b[1].getX()) ) && (_x+1 <= (b.getXGauche() + b[1].getX() + b[1].getLongueur()) )) && ((_y >= b.getYHaut() + b[1].getHauteur() + b[1].getY()) && (_y <= b.getYHaut()))) \
			|| true) {
			return true;
		}
	}
}

bool Balle::toucheDroit(Brique& b) {
	if ( (_x+_horiz == b.getXDroit()) && ((_y <= b.getYBas()) && (_y >= b.getYHaut())) ){
		return true;
	}
	else {
		return false;
	}
}

bool Balle::toucheCoinHG(Brique& b) {
	if ((_x+_horiz == b.getXGauche()) && (_y+_verti == b.getYHaut())) {
		return true;
	}
	else {
		return false;
	}
}

bool Balle::toucheCoinHD(Brique& b) {
	if ((_x+_horiz == b.getXDroit()) && (_y+_verti == b.getYHaut())) {
		return true;
	}
	else {
		return false;
	}
}

bool Balle::toucheCoinBG(Brique& b) {
	if ((_x+_horiz == b.getXGauche()) && (_y+_verti == b.getYBas())) {
		return true;
	}
	else {
		return false;
	}
}

bool Balle::toucheCoinBD(Brique& b) {
	if ((_x+_horiz == b.getXDroit()) && (_y+_verti == b.getYBas())) {
		return true;
	}
	else {
		return false;
	}
}