#include <iostream>
#include <string>
#include "joueur.h"

using namespace std;

// Constructeur
Joueur::Joueur(const string& nom) : _nom(nom), _score(0), _vie(5) {}

// Accesseurs en lecture

std::string Joueur::getNom() const {return _nom;}
size_t Joueur::getScore() const {return _score;}
size_t Joueur::getVie() const {return _vie;}

// Accesseurs en écriture

void Joueur::setNom(string nom){_nom = nom;}
void Joueur::setScore(size_t score){_score = score;}
void Joueur::setVie(size_t vie){_vie = vie;}