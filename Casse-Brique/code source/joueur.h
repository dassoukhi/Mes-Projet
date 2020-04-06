#ifndef JOUEUR_H
#define JOUEUR_H

#include <string>
#include "window.h"

class Joueur{
private:
	std::string _nom;
	size_t _score, _vie;

public:
	// Constructeur
	Joueur(const std::string& nom="inconnu");

	// Accesseurs en lecture
	std::string getNom() const;
	size_t getScore() const;
	size_t getVie() const;

	// Accesseurs en écriture
	void setNom(std::string nom);
	void setScore(size_t score);
	void setVie(size_t vie);
};

#endif