#ifndef OPTION_H
#define OPTION_H

#include <string>

class Option{
public:
	enum Type{
		AUCUN,
		BOOLEEN,
		ENTIER,
		REEL,
		CHAR,
		CHAINE
	};

private:
	int _id;
	std::string _nom, _raccourci, _desc;
	Type _type;

public:
	Option();
	Option(int id, const std::string &nom, const std::string &raccourci, const std::string &desc, Type type);

	// Accesseurs en lecture
	int getId() const;
	std::string getNom() const;
	std::string getRaccourci() const;
	std::string getDesc() const;
	Type getType() const;

	// Accesseurs en écriture
	void setId(int id);
	void setNom(const std::string &nom);
	void setRaccourci(const std::string &raccourci);
	void setDesc( const std::string &desc);
	void setType(Type type);

	// Méthodes
	void print() const;
};

std::string type2String(Option::Type type);

#endif