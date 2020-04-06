#ifndef BALLE_H
#define BALLE_H

#include "brique.h"

class Balle{
private:
	size_t _x, _y;
	short _horiz, _verti;

public:
	// Constructeurs
	Balle();
	Balle(size_t x,size_t y);

	// Accesseurs en lecture
	size_t getX() const;
	size_t getY() const;
	short getHoriz() const;
	short getVerti() const;

	// Accesseurs en écriture
	void setX(size_t x);
	void setY(size_t y);
	void setHoriz(short horiz);
	void setVerti(short verti);

	void update();

	// Fonctions pour savoir si la balle touche une brique, et si oui sur quelle face ou coin
	bool toucheBrique(Brique& B);
	bool toucheBas(Brique& B);
	bool toucheHaut(Brique& B);
	bool toucheGauche(Brique& B);
	bool toucheDroit(Brique& B);
	bool toucheCoinHG(Brique& B);
	bool toucheCoinHD(Brique& B);
	bool toucheCoinBG(Brique& B);
	bool toucheCoinBD(Brique& B);
};

#endif