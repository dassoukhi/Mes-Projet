#ifndef __MENU_H
#define __MENU_H
#include "window.h"
#include<string.h>

class Menu {

private:
          Window me;  

 public :

	// constructeur par défaut 
	Menu();
	// constructeur parametré 
	Menu(Window &w);
	//accesseur en lecture
	//Color getBKgdColorMenu()const;
	Color getBordColorMenu()const; 
	Window& getW();
	const Window& getW() const;
	
	//methodes

	void afficheMenu(int &kk, std::string& s);

	void aide();

	void affichePause(int &k);

};
#endif 
