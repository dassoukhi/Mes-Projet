#include <iostream>
#include <string>
#include "taboption.h"

using namespace std;

TabOption::TabOption() : _opts(), _nb_opt(0) {}

void TabOption::addOption(const Option &opt){
	if (_nb_opt == 3) {
		cerr << "Erreur: Impossible d'ajouter une nouvelle option." << endl
		<< "        Nombre maximum d'option atteint"<< " (3)." << endl;
		terminate();
	}
	bool found = (getOptionIndex(opt.getNom()) !=  -1) || (getOptionIndex(opt.getRaccourci()) !=  -1);
	if (found){
		cerr << "Avertissement: L'identifiant " << opt.getId() << " est déjà utilisé." << endl;
	} else{
		_opts[_nb_opt] = opt;
		_nb_opt++;
	}
}

void TabOption::print() const{
	cout << "Options :" << endl;
	for (size_t i = 0; i < 3; i++){
		_opts[i].print();
	}
}

int TabOption::getOptionIndex(const string &opt) const{
	bool found(false);
	size_t i = 0;
	while (!found && (i < _nb_opt)){
		found = ((_opts[i].getNom() == opt ) || (_opts[i].getRaccourci() == opt ));
		i++;
	}
	return found ? i-1 : -1;
}

int TabOption::getOptionId(const string &opt) const{
	int i = getOptionIndex(opt);
	return (i>=0 ? _opts[i].getId() : -1);
}

bool TabOption::optionHasArgument(const string &opt) const{
	size_t i = getOptionIndex(opt);
	return (_opts[i].getType() != Option::AUCUN);
}

Option::Type TabOption::optionHasArgumentType(const string &opt) const{
	size_t i = getOptionIndex(opt);
	return _opts[i].getType();
}