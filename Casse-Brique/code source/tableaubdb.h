#ifndef TABLEAUBDB_H
#define TABLEAUBDB_H

#include "boutdebrique.h"

class TableauBDB {
private:
	size_t n;
	BoutDeBrique *T;
	size_t nMax;

	void extend();

public:
	// Constructeurs
	TableauBDB();
	TableauBDB(size_t _n);

	// 3 CHOSES A FAIRE IMPERATIVEMENT QUAND ON A DU DYNAMIQUE
	TableauBDB(const TableauBDB& A); // Constructeur par copie
	~TableauBDB(); // Destructeur
	TableauBDB& operator=(const TableauBDB&); // operateur d'affectation

	BoutDeBrique& operator[](int i);
	BoutDeBrique operator[](int i) const;

	// Accesseurs en lecture
	size_t getTaille() const;

	// Accesseurs en ecriture
	void setTaille(size_t _n);

	// Recherche une BoutDeBrique et la récupère si elle apartient au tableau
	int find(const BoutDeBrique& b);

	// Ajouter un BoutDeBrique
	void push_back(const BoutDeBrique& B);

	// Efface la BoutDeBrique à la case a
	void erase(int a);

};

bool estEgale(const BoutDeBrique& B1, const BoutDeBrique& B2);

#endif