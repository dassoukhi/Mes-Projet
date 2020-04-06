#include <iostream>
#include <string>
#include "option.h"

using namespace std;

Option::Option() : _id(-1), _nom(), _raccourci(), _desc(), _type() {}
Option::Option(int id, const string& nom, const string& raccourci, const string& desc, Type type) : _id(id), _nom(nom), _raccourci(raccourci), _desc(desc), _type(type) {}

// Accesseurs en lecture
int Option::getId() const {return _id;}
string Option::getNom() const {return _nom;}
string Option::getRaccourci() const {return _raccourci;}
string Option::getDesc() const {return _desc;}
Option::Type Option::getType() const {return _type;}

// Accesseurs en écriture
void Option::setId(int id){
	_id = id;
}
void Option::setNom(const string &nom){
	_nom = nom;
}
void Option::setRaccourci(const string &raccourci){
	_raccourci = raccourci;
}
void Option::setDesc( const string &desc){
	_desc = desc;
}
void Option::setType(Type type){
	_type = type;
}

// Méthodes
void Option::print() const{
	cout<<_nom<<" ("<<_raccourci<<") "<<type2String(_type)<<"\t"<<_desc<<endl;
}

string type2String(Option::Type type){
	string tmp;
	switch (type){
		case Option::AUCUN: tmp = ""; break;
		case Option::BOOLEEN: tmp = "<booléen>"; break;
		case Option::ENTIER: tmp = "<entier>"; break;
		case Option::REEL: tmp = "<réel>"; break;
		case Option::CHAR: tmp = "<caratère>"; break;
		case Option::CHAINE: tmp = "<chaîne>"; break;
	}
	return tmp;
}