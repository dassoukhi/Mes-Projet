#include <iostream>
#include <fstream>
#include <ctime>
#include <math.h>
#include <string>
#include "terrain_v2.h"
#include <unistd.h>
#include "window.h"
#include "joueur.h"

using namespace std;

// Constructeurs
Terrain::Terrain() : _w1(LINES/1.2,COLS/1.2,1,1,' ')
{
	_w1.setCouleurBordure(BBLUE);
	POP.niveau1(5,3,"rectangle","norandom",LINES/1.2,COLS/1.2); // on initialise notre jeu avec le niveau 1
}

Terrain::Terrain(Window w, PopulationBrique p, Raquette r, Joueur j) : _w1(w.getHauteur(),w.getLargeur(),w.getX(),w.getY(),' '), _b(r.getXpos()+r.sizeR()/2, r.getYpos()-1 ), _r(r.getXpos(), r.getYpos(), r.getLong(), r.getCol() ), POP(p), _j(j) {}

// Accesseurs en lecture
Balle& Terrain::getB() {return _b;}
Window& Terrain::getW1() {return _w1;}
const Window& Terrain::getW1() const {return _w1;}
PopulationBrique& Terrain::getPop() {return POP;}
Raquette& Terrain::getR() {return _r;}
Joueur& Terrain::getJoueur() {return _j;}

// Efface la raquette
string raqcleaner(string r)
{
	string s(" ");
	for (int i = 0; i < r.length()+1; i++)
	{
		s=s+' ';
	}

	return s;
}

void Terrain::collisionTerrain(int c,string& s)
{
    _w1.print(_b.getX(),_b.getY(),' ',WBLACK);
    _b.update();
    size_t bx=_b.getX(), by=_b.getY();
    size_t rx=_r.getXpos(),ry =_r.getYpos(),rd=rx+_r.sizeR();
    _w1.print(bx,by,'@',WBLACK);

    if (by==_w1.getHauteur()-1) //si la balle touche le sol
    {
        _b.setHoriz(0);
        _b.setVerti(0);
        //on l'immobilise
        _b.setX(rx+(_r.sizeR())/2);
        _b.setY(ry-1);
        //on la replace sur la raquette
        

        if (_j.getVie() > 0) // si le joueur à encore de la vie
        {
       	 	_j.setVie(_j.getVie()-1); //on la diminue 
       	}

        while ((c = getch()) != ' ') //on doit appuyer sur espace pour relancer la balle
        {
            switch(c)
            {
                case'Q':
                {
                    return;
                }
                break;

                case 'N':
                {
                    nextLvl("force"); // permet de forcer le passage au niveau suivant
                }
                break;

                default: {startMove(c,s);} //permet de déplacer la raquette après perte de vie
                break;
            }
        }
        _b.setVerti(-1);
    }
    if (bx<0 || bx>_w1.getLargeur()-1) //si la balle touche un coté du terrain
    {
        _b.setHoriz(-_b.getHoriz()); //on modifie sa direction horizontale
    }

    if (by==0) // si la balle touche le haut du terrain
    {
        _b.setVerti(1); //on modifie sa trajectoire verticale
    }

    if ( ( (bx >rx+1) && (bx <rd-1) ) && ((by ==ry-1) )) //si la balle touche la raquette
    {                                                    
        _b.setVerti(-_b.getVerti()); // on inverse sa trajectoire verticale
    }

    else if (( (bx <=rx+1) && (bx >=rx-1) ) && ((by ==ry-1) ))//si la balle touche l'extremité 
    {                                                         //gauche de a raquette
        _b.setVerti(-_b.getVerti()); // on inverse sa trajectoire verticale

        if (_b.getHoriz()!=0) //si la balle avait une direction horizontale
        {
            _b.setHoriz(0); //on la met à zéro
        }
        else _b.setHoriz(-1); //sinon on la fait aller vers la gauche
    }
    else if (( (bx >= rd-1) && (bx <= rd+1)) && ((by ==ry-1) ))
    {
        _b.setVerti(-_b.getVerti()); // on inverse sa trajectoire verticale

        if (_b.getHoriz()!=0)//si la balle avait une direction horizontale
        {
            _b.setHoriz(0); //on la met à zéro
        }
        else _b.setHoriz(1);//sinon on la fait aller vers la droite
    }
}

void Terrain::startMove(int c,string& s)
{
    size_t bx=_b.getX(), by=_b.getY();
    size_t rx=_r.getXpos(), ry=_r.getYpos(),rd=rx+_r.sizeR();
    size_t wg=_w1.getX(), wd=_w1.getX()+_w1.getLargeur()-1;
    std::string rl= _r.getLong();
    std::string clean = raqcleaner(_r.getLong());

    Color col=_r.getCol();

    if (s == "cactus") 
    {
        srand(time(NULL));
        int r = rand()%14+1;
        col = inttoCol(r);
    }

    _w1.print(bx,by,'@',WBLACK);
    _w1.print(rx,ry,rl,col);

    switch(c)
    {

        case KEY_LEFT:

        {
            _w1.print(rx,ry, clean, WBLACK); //efface la raquette
            _w1.print(bx,by,' ',WBLACK);     //efface la balle

            if (rx>wg+3) //si la raquette est à plus de 3 de distance du coté gauche du terrain
            {
                _r.setXpos(rx-3); //on la déplace de 3 vers la gauche
                _b.setX(bx-3); //on fait de même avec la balle
            }
            else
            {
                _r.setXpos(wg-1); // sinon on colle la raquette au bord

                if (bx>wg-1)//si la balle n'est pas collée au bord alors
                {
                    _b.setX(bx-1); //on déplace la balle vers le bord 
                }
            }

            bx=_b.getX();
            rx=_r.getXpos();

            _w1.print(rx,ry,rl,col);     //affiche la raquette à sa nouvelle position
            _w1.print(bx,by,'@',WBLACK); //affiche la balle à sa nouvelle position
        }

        break;

        case KEY_RIGHT:

        {
            _w1.print(rx,ry, clean, WBLACK); //efface la raquette 
            _w1.print(bx,by,' ',WBLACK);     //efface la balle
        
            if (rd<wd-3)
            {
                _r.setXpos(rx+3);
                _b.setX(bx+3);
            }
        
            else
            {
                _r.setXpos(wd-(_r.sizeR()+1));
        
                if (bx<wd-1)
                {
                    _b.setX(bx+1);
                }
            }
        
            bx=_b.getX();
            rx=_r.getXpos();
        
            _w1.print(rx,ry,rl,col);     //affiche la raquette à sa nouvelle position
            _w1.print(bx,by,'@',WBLACK); //affiche la balle à sa nouvelle position
        }

        break;

        case 'a':

        {
            _w1.print(rx,ry, clean, WBLACK); //efface la raquette
            _w1.print(bx,by,' ',WBLACK);     //efface la balle

            if (rx>=wg)
            {
                _r.setXpos(rx-1);
                _b.setX(bx-1);
            }

            else
            {
                if (bx>wg-1)
                {
                    _b.setX(bx-1);
                }
            }

            bx=_b.getX();
            rx=_r.getXpos();

            _w1.print(rx,ry,rl,col);     //affiche la raquette à sa nouvelle position
            _w1.print(bx,by,'@',WBLACK); //affiche la balle à sa nouvelle position //affiche la balle à sa nouvelle position
        }

        break;

        case 'e':

        {
            _w1.print(rx,ry, clean, WBLACK); //efface la raquette
            _w1.print(bx,by,' ',WBLACK);     //efface la balle

            if (rd<wd-1)
            {
                _r.setXpos(rx+1);
                _b.setX(bx+1);
            }

            else
            {
                if (bx<wd-1)
                {
                    _b.setX(bx+1);
                }
            }

            bx=_b.getX();
            rx=_r.getXpos();

            _w1.print(rx,ry,rl,col);     //affiche la raquette à sa nouvelle position
            _w1.print(bx,by,'@',WBLACK); //affiche la balle à sa nouvelle position
        }

        break;

        default:
        {
            return;
        }
    }
}

void Terrain::move(int c,string& s)
{
    size_t rg = _r.getXpos(), ry=_r.getYpos(), rd=_r.getXpos()+_r.sizeR();
    size_t wg=_w1.getX(), wd=_w1.getX()+_w1.getLargeur()-1;
    size_t bx=_b.getX(), by=_b.getY();
    std::string rl= _r.getLong();
	std::string clean = raqcleaner(_r.getLong());
    Color col=_r.getCol();

     if (s == "cactus") {
    	srand(time(NULL));
    	int r = rand()%14+1;
    	col = inttoCol(r);
     }
    _w1.print(rg,ry,rl,col);

    switch (c)
    {
        case KEY_LEFT:
        {
        	_w1.print(rg,ry,clean,WBLACK); //efface la raquette

            if (rg>wg+3) {_r.setXpos(rg-3);} //si la raquette est à plus de 3 de distance
                                             //du bord on la déplace de 3 
            else {_r.setXpos(wg-1);}         //sinon on la déplace de 1

            if ( ( (bx >=rg) && (bx <=rd) ) && (((by <=_r.getYpos())&&(by >=_r.getYpos()-1)) ))
            {
                _w1.print(bx,by,' ',WBLACK);//efface la balle 
                _b.setX(bx-3); //décale la balle en meme temps que la raquette
                _w1.print(_b.getX(),by,'@',WBLACK);// raffiche la balle
            }
        }
        // ce sera le même principe pour le reste de la fonction

        break;

        case KEY_RIGHT:
        {
        	_w1.print(rg,ry,clean,WBLACK);

            if (rd<wd-3) {_r.setXpos(rg+3);}

            else {_r.setXpos(wd-(_r.sizeR()+1));}

            if ( ( (bx >=rg) && (bx <=rd) ) && ((by ==_r.getYpos()-1) ))
            {
                _w1.print(bx,by,' ',WBLACK);
                _b.setX(bx+3);
                _w1.print(_b.getX(),by,'@',WBLACK);   
            }
        }
            
        break;

        case 'a':
        {
        	_w1.print(rg,ry,clean,WBLACK);

            if (rg>wg-1) {_r.setXpos(rg-1);} 

            if ( ( (bx >=rg) && (bx <=rd) ) && ((by ==_r.getYpos()-1) ))
            {
                _w1.print(bx,by,' ',WBLACK);
                _b.setX(bx-1);
                _w1.print(_b.getX(),by,'@',WBLACK);
            }
        }

        break;

        case 'e':
        {
        	_w1.print(rg,ry,clean,WBLACK);

            if (rg<wd-(1+_r.sizeR())) {_r.setXpos(rg+1);}

            if ( ( (bx >=rg) && (bx <=rd) ) && ((by ==_r.getYpos()-1) ))
            {
                _w1.print(bx,by,' ',WBLACK);
                _b.setX(bx+1);
                _w1.print(_b.getX(),by,'@',WBLACK);   
            }
        }
            
        break;

        default:

        {return;}

        break;
    }
        
    rg = _r.getXpos();
    _w1.print(rg,ry,rl,col);
}

void Terrain::balleOnRaquette()
{
	size_t rx=_r.getXpos(),ry =_r.getYpos();
	size_t bx=_b.getX(), by=_b.getY();

	_w1.print(bx,by,' ',WBLACK);

	_b.setHoriz(0); //imobilise la balle horizontalement
    _b.setVerti(0); //imobilise la balle verticalement

	_b.setX(rx+(_r.sizeR())/2);
    _b.setY(ry-1);
    //place la balle au milieu la raquette
}

void Terrain::collisionBrique() {
	for (int i=1; i<POP.getTaille(); i++) {

        if (!(POP[i].estMorte()) ) { // si la brique n'est pas morte
        	if (_b.toucheCoinHG(POP[i])) { // touche le coin haut gauche
        		
        		_b.setVerti(-1);
    			_b.setHoriz(-1);
    			POP.updateCoul(POP[i]); 
    			_j.setScore(_j.getScore()+10);

        	}
        	else if (_b.toucheCoinHD(POP[i])) {  // touche le coin haut droit
        		
    			_b.setVerti(-1);
    			_b.setHoriz(1);
				POP.updateCoul(POP[i]);   
				_j.setScore(_j.getScore()+10);

        	}
        	else if (_b.toucheCoinBG(POP[i])) { // touche le coin bas gauche
        		
    			_b.setVerti(1);
    			_b.setHoriz(-1);
    			POP.updateCoul(POP[i]); 
    			_j.setScore(_j.getScore()+10);
        		
        	}
        	else if (_b.toucheCoinBD(POP[i])) { // touche le coin bas droit
        		
    			_b.setVerti(1);
    			_b.setHoriz(1);
    			POP.updateCoul(POP[i]);
    			_j.setScore(_j.getScore()+10);
        		
        	}
        	else {
	        	if (_b.getVerti() < 0) { // la balle va vers le haut
		        	if (_b.toucheBas(POP[i])) { // touche la face du bas
		        		_b.setVerti(-_b.getVerti());
		        		POP.updateCoul(POP[i]);
		        		_j.setScore(_j.getScore()+10);

		        	}
		        }
		        else {
		        	if (_b.getVerti() > 0) { // la balle va vers le haut
			        	if (_b.toucheHaut(POP[i])) { // touche la face du haut
			        		_b.setVerti(-_b.getVerti());
			        		POP.updateCoul(POP[i]);
			        		_j.setScore(_j.getScore()+10);
			        	}
			        }
		        }
	     
	        	if (_b.getHoriz() < 0) { // la balle va vers la gauche
		        	if (_b.toucheDroit(POP[i])) { // touche la face droite
		        		_b.setHoriz(-_b.getHoriz());
		        		POP.updateCoul(POP[i]);
		        		_j.setScore(_j.getScore()+10);
		        	}
		        }
		       else {
		        	if (_b.getHoriz() > 0) { // la balle va vers la droite
			        	if (_b.toucheGauche(POP[i])) { // touche la face gauche
		        			_b.setHoriz(-_b.getHoriz());
		        			POP.updateCoul(POP[i]);
		        			_j.setScore(_j.getScore()+10);
		        		}
		        	}
	        	}

	        }
        	
        }
	        
	}
}

void Terrain::affiche() {
	for (int j=1; j<POP.getTaille(); j++) {
		Brique b1 = POP[j];
		if (b1.getType() == "rectangle") {
			for (int k=1; k<=(b1.getLongueur()*(b1.getHauteur())); k++) {
				_w1.print(b1.getX()+b1[k].getX(), b1.getY()+b1[k].getY(), std::string(b1[k].getLongueur(), ' '), (Color)b1[k].getCouleur());
			}
		}
		if (b1.getType() == "triangle") {
			for (int k=1; k<=(b1.getLongueur()*(b1.getHauteur()-2))+2; k++) {
				_w1.print(b1.getX()+b1[k].getX(), b1.getY()+b1[k].getY(), std::string(b1[k].getLongueur(), ' '), (Color)b1[k].getCouleur());
			}
		}
	}
}

bool Terrain::nextLvl(string s) {
	bool r = true;
	if (s != "force") { // si l'on ne passe pas par force (sans appuyer sur 'N')
		for (int i=1; i<POP.getTaille(); i++) { // Teste si toutes les briques sont mortes
			if (!(POP[i].estMorte())) {
				r = false;
			}
		}
	}
	if (r == true) {
		_w1.clear(); // clear le terrain
		POP.setTaille(1); // set la taille de la population à 1 
		POP.setLvl(POP.getLvl()+1); // augmente le niveau
		balleOnRaquette(); // remet la balle sur la raquete

		switch (POP.getLvl()) {
			case 1:
				
				POP.niveau1(2,1,"rectangle","norandom",LINES/1.2,COLS/1.2); // construit le niveau1 dans population brique
				break;

			case 2:
				POP.niveau2(2,1,"rectangle","random",LINES/1.2,COLS/1.2); // construit le niveau2 dans population brique
				break;

			case 3:
				POP.niveau3(2,1,"rectangle","norandom",LINES/1.2,COLS/1.2); // construit le niveau3 dans population brique
				break;

			case 4:
				POP.niveau4(2,1,"random",LINES/1.2,COLS/1.2); // construit le niveau4 dans population brique
				break;

			case 5:
				POP.gagner(1,1,LINES/1.2,COLS/1.2); // construit le message gagner dans population brique
		}
		affiche(); // affiche le nouveau niveau
	}
	return r;
}

bool Terrain::loose()
{
	if (_j.getVie() == 0)// vie du joueur à 0?
    { 
		return true;
	}
    return false;
}