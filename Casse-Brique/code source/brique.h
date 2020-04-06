#ifndef BRIQUE_H
#define BRIQUE_H

#include "tableaubdb.h"
#include <string>

class Brique {
private:
	size_t x, y, longueur, hauteur;
	TableauBDB T;
	std::string type;

public:
	//Constructeurs
	Brique();
	Brique(size_t _x, size_t _y, size_t _longueur, size_t _hauteur, TableauBDB _T);
	Brique(size_t _x, size_t _y, size_t _longueur, size_t _hauteur, size_t _resistance, std::string _s);

	//Accesseurs en lecture
	size_t getX() const;
	size_t getY() const;
	size_t getLongueur() const;
	size_t getHauteur() const;
	std::string getType() const;

	size_t getXGauche() const; // renvoi la coordonée X du côté gauche de la brique
	size_t getXDroit() const; // renvoi la coordonée X du côté droit de la brique
	size_t getYHaut() const; // renvoi la coordonée Y du côté haut de la brique
	size_t getYBas() const; // renvoi la coordonée Y du côté bas de la brique

	//Accesseurs en ecriture
	void setX(size_t x);
	void setY(size_t y);
	void setLongueur(size_t l);
	void setHauteur(size_t h);
	void setResistanceBR(size_t r);

	bool estMorte(); // vérifie si la brique est morte 

	void updateBrique(); // update la couleur de la brique

	BoutDeBrique& operator[](int i);
	BoutDeBrique operator[](int i) const;
	

	~Brique(); 

};
 

#endif 


