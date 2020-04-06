#include "window.h"
#include "menu.h"
#include <string.h>


//constructeurs de menu
Menu::Menu():me(LINES-2,COLS-2,0,0,' '){}
Menu::Menu(Window &w):me(w.getHauteur(),w.getLargeur(),w.getX(),w.getY()){}

Window& Menu::getW(){
	return me;
}

const Window& Menu::getW() const {
	return me;
}
//methodes

void Menu::aide()
{

Menu dass;
dass.getW().setCouleurBordure(BRED);
  while(getch()!='m' && getch()!='M')
  {
    std::string a1="- Pour lancer la balle appuyer sur \"ESPACE\"";
    std::string a2="- Pour deplacer la raquette rapidement utiliser les flêches \"<- et ->\"";
    std::string a3="- Appuyer sur \"m\" pour revenir au Menu";
    std::string a4="  pour plus de précision utiliser \"a\" et \"e\""; 

    dass.getW().print((dass.getW().getLargeur()/2)-a1.length()/2,dass.getW().getHauteur()/3,a1);
    dass.getW().print((dass.getW().getLargeur()/2)-a2.length()/2,dass.getW().getHauteur()/(2.3),a2);
    dass.getW().print((dass.getW().getLargeur()/2)-a4.length()/2,dass.getW().getHauteur()/(2.15),a4);
    dass.getW().print((dass.getW().getLargeur()/2)-a3.length()/2,dass.getW().getHauteur()/(2),a3,WBLUE);
}
}

void Menu::afficheMenu(int &kk, std::string& s)
{
	  Menu dass;

int kalf; 
int kam=0;
int cnt=0;
int qtr=0;
int mlt=0;
int m=0;

while(cnt==0 && mlt==0 && m==0){

while(((kalf=getch())!='\n'))
{
  dass.getW().setCouleurBordure(BWHITE);

  std::string str1= "Nouvelle Partie";

  std::string str2= "Aide";
  std::string str3= "Quitter";

      while(kam==0)
      {
        dass.getW().print((dass.getW().getLargeur()/2)-str1.length()/2,dass.getW().getHauteur()/3,str1,BWHITE);

        dass.getW().print((dass.getW().getLargeur()/2)-str2.length()/2,dass.getW().getHauteur()/(2.6),str2);

        dass.getW().print((dass.getW().getLargeur()/2)-str3.length()/2,dass.getW().getHauteur()/(2.4),str3);
        cnt=1;
        qtr=0;
        mlt=0;
        kam++;
      }

switch (kalf) {
  case KEY_UP:
    {
      if(cnt==0 && qtr==1 && mlt==0)
      {
        dass.getW().print((dass.getW().getLargeur()/2)-str1.length()/2,dass.getW().getHauteur()/3,str1,BWHITE);

        dass.getW().print((dass.getW().getLargeur()/2)-str2.length()/2,dass.getW().getHauteur()/(2.6),str2);

        dass.getW().print((dass.getW().getLargeur()/2)-str3.length()/2,dass.getW().getHauteur()/(2.4),str3);
        cnt=1;
        qtr=0;
        m=0;
        mlt=0;
      }
    else if(qtr==0 && cnt==0 && mlt==1)
    {
      dass.getW().print((dass.getW().getLargeur()/2)-str1.length()/2,dass.getW().getHauteur()/3,str1);

      dass.getW().print((dass.getW().getLargeur()/2)-str2.length()/2,dass.getW().getHauteur()/(2.6),str2,BWHITE);

      dass.getW().print((dass.getW().getLargeur()/2)-str3.length()/2,dass.getW().getHauteur()/(2.4),str3);
       
      cnt=0;
      qtr=1;
      m=1;
      mlt=0;  
    }
  }
    
    break;
  case KEY_DOWN:
  {
    if(qtr==0 && cnt==1 && mlt==0){
     dass.getW().print((dass.getW().getLargeur()/2)-str1.length()/2,dass.getW().getHauteur()/3,str1);

      dass.getW().print((dass.getW().getLargeur()/2)-str2.length()/2,dass.getW().getHauteur()/(2.6),str2,BWHITE);

      dass.getW().print((dass.getW().getLargeur()/2)-str3.length()/2,dass.getW().getHauteur()/(2.4),str3);

      cnt=0;
      qtr=1;
      m=1;
      mlt=0;
    }
    else if(qtr==1 && mlt==0 && cnt==0){
      dass.getW().print((dass.getW().getLargeur()/2)-str1.length()/2,dass.getW().getHauteur()/3,str1);

      dass.getW().print((dass.getW().getLargeur()/2)-str2.length()/2,dass.getW().getHauteur()/(2.6),str2);

      dass.getW().print((dass.getW().getLargeur()/2)-str3.length()/2,dass.getW().getHauteur()/2.4,str3,BWHITE);
      
      qtr=0;
      m=0;
      cnt=0;
      mlt=1;
    }
  }
  break;
  // créer le mot cactus si jamais on appuie sur les caractères ci dessous dans le bon ordre pour déverouiller un mode secret (EASTER EGG)
  case 'a': 
    s = s + 'a';
    break;
  case 'c': 
    s = s + 'c';
    break;
  case 't': 
    s = s + 't';
    break;
  case 'u': 
    s = s + 'u';
    break;
  case 's': 
  s = s + 's';
  break;
}
}

if(m==1)
{
  aide();
  m=0;
}
if(mlt==1)
{
	kk=mlt;
}


}
}

void Menu::affichePause(int &k)
{
  Menu pause;

int kalf; 
int kam=0;
int cnt=0;
int qtr=0;
int mlt=0;

while(cnt==0 && mlt==0 && qtr==0){
while(((kalf=getch())!='\n'))
{
  pause.getW().setCouleurBordure(BRED);

  std::string str1= "Continue";

std::string str2= "Aide";
std::string str3= "Quitter";

 while(kam==0)
  {
    pause.getW().print((pause.getW().getLargeur()/2)-str1.length()/2,pause.getW().getHauteur()/3,str1,BWHITE);

    pause.getW().print((pause.getW().getLargeur()/2)-str2.length()/2,pause.getW().getHauteur()/(2.6),str2);

    pause.getW().print((pause.getW().getLargeur()/2)-str3.length()/2,pause.getW().getHauteur()/(2.4),str3);
    cnt=1;
    qtr=0;
    mlt=0;
    kam++;
  }

switch (kalf) {
  case KEY_UP:
    {
      if(cnt==0 && qtr==1 && mlt==0)
      {
        pause.getW().print((pause.getW().getLargeur()/2)-str1.length()/2,pause.getW().getHauteur()/3,str1,BWHITE);

        pause.getW().print((pause.getW().getLargeur()/2)-str2.length()/2,pause.getW().getHauteur()/(2.6),str2);

        pause.getW().print((pause.getW().getLargeur()/2)-str3.length()/2,pause.getW().getHauteur()/(2.4),str3);
        cnt=1;
        qtr=0;
        mlt=0;
      }
    else if(qtr==0 && cnt==0 && mlt==1)
    {
      pause.getW().print((pause.getW().getLargeur()/2)-str1.length()/2,pause.getW().getHauteur()/3,str1);

      pause.getW().print((pause.getW().getLargeur()/2)-str2.length()/2,pause.getW().getHauteur()/(2.6),str2,BWHITE);

      pause.getW().print((pause.getW().getLargeur()/2)-str3.length()/2,pause.getW().getHauteur()/(2.4),str3);
       
      cnt=0;
      qtr=1;
      mlt=0;
    }
  }
  
  break;

  case KEY_DOWN:
  {
    if(qtr==0 && cnt==1 && mlt==0)
    {
     pause.getW().print((pause.getW().getLargeur()/2)-str1.length()/2,pause.getW().getHauteur()/3,str1);

      pause.getW().print((pause.getW().getLargeur()/2)-str2.length()/2,pause.getW().getHauteur()/(2.6),str2,BWHITE);

      pause.getW().print((pause.getW().getLargeur()/2)-str3.length()/2,pause.getW().getHauteur()/(2.4),str3);

      cnt=0;
      qtr=1;
      mlt=0;
    }
    else if(qtr==1 && mlt==0 && cnt==0)
    {
      pause.getW().print((pause.getW().getLargeur()/2)-str1.length()/2,pause.getW().getHauteur()/3,str1);

      pause.getW().print((pause.getW().getLargeur()/2)-str2.length()/2,pause.getW().getHauteur()/(2.6),str2);

      pause.getW().print((pause.getW().getLargeur()/2)-str3.length()/2,pause.getW().getHauteur()/2.4,str3,BWHITE);
      
      qtr=0;
      cnt=0;
      mlt=1;
    }
  }
  break;

}
}

if(mlt==1)
{
k=mlt;	
}
}

}