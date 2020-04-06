#ifndef BOUTDEBRIQUE_H
#define BOUTDEBRIQUE_H

#include <string>

class BoutDeBrique {
public:
	enum Couleur {
		BLACK,
		WHITE,
		YELLOW,
		RED,
		CYAN,
		BLUE,
		GREEN,
		MAGENTA
	};


private:
	size_t x, y, longueur, hauteur;
	size_t resistance;
	Couleur coul;

public:
	// Constructeurs
	BoutDeBrique();
	BoutDeBrique(size_t x, size_t y, size_t longueur, size_t hauteur, size_t r);

	//Accesseurs en lecture
	size_t getX() const;
	size_t getY() const;
	size_t getLongueur() const;
	size_t getHauteur() const;
	size_t getResistance() const;
	Couleur getCouleur() const;

	//Accesseurs en ecriture
	void setX(size_t x);
	void setY(size_t y);
	void setLongueur(size_t l);
	void setHauteur(size_t h);
	void setResistance(size_t r);
	void setCouleur(Couleur c);

	// Update les couleurs 
	void update(); 

	~BoutDeBrique();
};

#endif