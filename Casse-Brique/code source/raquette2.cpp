#include <iostream>
#include <string>
#include "raquette2.h"



// Constructeurs
Raquette::Raquette() : _xpos((COLS-3)/2), _ypos(LINES-5), _long("--------"), _col(BRED) {}
Raquette::Raquette(size_t x, size_t y, std::string l, Color c): _xpos(x), _ypos(y), _long(l), _col(c) {}

// Accesseurs en lecture
size_t Raquette::getXpos() const {return _xpos;}
size_t Raquette::getYpos() const {return _ypos;}
std::string Raquette::getLong() const {return _long;}
Color Raquette::getCol() const {return _col;}

void Raquette::setXpos(size_t x){_xpos =x;}
void Raquette::setYpos(size_t y){_ypos =y;}
void Raquette::setLong(std::string l){_long =l;}
void Raquette::setCol(Color c){_col=c;}
 
size_t Raquette::sizeR() const {return _long.length()-1;}


std::string creerRaq()
{
	std::string str = "//";
	for (int i=0; i<(COLS/8); i++) {
		str = str + ' ';
	}
	str = str + "\\\\";
	return str;
}

