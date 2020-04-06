#include <cstddef>
#include <string>
#include "window.h"

#ifndef RAQUETTE_H
#define RAQUETTE_H

class Raquette{
private:
        size_t _xpos;
        size_t _ypos;
        std::string _long;
        Color _col;

public:
        Raquette();
        Raquette(size_t x, size_t y, std::string l, Color c);

        // Accesseurs en lecture
        size_t getXpos() const;
        size_t getYpos() const;
        std::string getLong() const;
        Color getCol() const;

        // Accesseurs en écriture
        void setXpos(size_t x);
        void setYpos(size_t y);
        void setLong(std::string l);
        void setCol(Color c);


        size_t sizeR() const; //renvoie la longeur de la raquette

};

std::string creerRaq(); // crée un string en fonction de la taille du terminal 

#endif