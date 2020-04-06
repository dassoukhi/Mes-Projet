#ifndef POPULATIONBRIQUEV2_H
#define POPULATIONBRIQUEV2_H

#include "brique.h"

class PopulationBrique {
private:
	size_t n;
	Brique *T;
	size_t nMax;
	size_t lvl;

	void extend();

public:
	// Constructeurs
	PopulationBrique();
	PopulationBrique(size_t _n);

	// 3 CHOSES A FAIRE IMPERATIVEMENT QUAND ON A DU DYNAMIQUE
	PopulationBrique(const PopulationBrique& A); // Constructeur par copie
	~PopulationBrique(); // Destructeur
	PopulationBrique& operator=(const PopulationBrique&); // operateur d'affectation

	Brique& operator[](int i);
	Brique operator[](int i) const;

	// Accesseurs en lecture
	size_t getTaille() const;
	size_t getLvl() const;

	// Accesseurs en ecriture
	void setTaille(size_t _t);
	void setLvl(size_t _l);

	// Recherche une brique et la récupère si elle apartient au tableau
	int find(const Brique& b);

	// Ajouter une brique
	void push_back(const Brique& B);

	// Efface la brique à la case a
	void erase(int a);

	// Efface toutes les briques du terrain
	void clearPOP();

	// update la couleur d'une brique 
	void updateCoul(Brique& B);

	// Constructeur de niveau
	void niveau1(int firstx, int firsty, std::string _s, std::string r2, int h, int w);
	void niveau2(int firstx, int firsty, std::string _s, std::string r2, int h, int w);
	void niveau3(int firstx, int firsty, std::string _s, std::string r2, int h, int w);
	void niveau4(int firstx, int firsty, std::string r2, int h, int w);

	// Affichage final
	void gagner(int firstx, int firsty, int h, int w);
	void perdu(int firstx, int firsty, int h, int w);

};

// Création de murs de briques (utilisé pour les niveaux)
void mur(PopulationBrique& POP, int nbrligne, int nbrBrique, std::string& s, std::string& r2, int lg, int ht, int resistance, int& coordx, int& coordy, int mode, int mode2, int firstx, int firsty, int& tmp, int& tmp2, int ecart, int ecart2);

void murMulticolor(PopulationBrique& POP, int nbrligne, int nbrBrique, std::string& s, std::string& r2, int lg, int ht, int& coordx, int& coordy, int firstx, int firsty, int& tmp, int& tmp2, int ecart, int ecart2);

bool estEgale(const Brique& B1, const Brique& B2);

#endif