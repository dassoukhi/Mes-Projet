#include "window.h"
#include "raquette2.h"
#include "balle.h"
#include "joueur.h"
#include "populationbriqueV2.h"
#include <string>
using namespace std;

#ifndef TERRAIN_H
#define TERRAIN_H

class Terrain{
private:
	Window _w1;
    Balle _b;
    PopulationBrique POP;
    Raquette _r;
    Joueur _j;

public:
	// Constructeurs
    Terrain();
    Terrain(Window w,PopulationBrique p,Raquette r, Joueur j);

    // Accesseurs en lecture
    Balle& getB();
    Window& getW1(); // renvoie l'attribut window par référence
    const Window& getW1() const;
    PopulationBrique& getPop();
    Raquette& getR();
    Joueur& getJoueur();

    void collisionTerrain(int c,std::string& s); // collision avec les bords du terrain
    void collisionBrique(); // collision avec les briques présentes dans le terrain
    void move(int c,std::string& s); // permet de bouger la raquette
    void startMove(int c,std::string& s); // permet de bouger la raquette au départ
    void balleOnRaquette(); // remet la balle sur la raquette

    void affiche(); // affiche la population de brique

    bool nextLvl(std::string s); // passe au niveau suivant 

    bool loose(); // test si le joueur n'a plus de vie, et donc a perdu

};

string raqcleaner(string r); // crée un string de la taille de la raquette composé d'espaces pour l'effacer

#endif