CC = g++
SOURCE = boutdebrique.cpp tableaubdb.cpp menu.cpp brique.cpp populationbriqueV2.cpp raquette2.cpp window.cpp balle.cpp joueur.cpp terrain_v2.cpp option.cpp taboption.cpp kassbrik.cpp 
PROG = kassbrik

all : $(PROG)

clear:
	rm *.o ; make all

window.o : window.cpp
	$(CC) -c window.cpp

raquette2.o : raquette2.cpp
	$(CC) -c raquette2.cpp

boutdebrique.o : boutdebrique.cpp
	$(CC) -c boutdebrique.cpp 

tableaubdb.o : tableaubdb.cpp
	$(CC) -c tableaubdb.cpp 

brique.o : brique.cpp
	$(CC) -c brique.cpp

populationbriqueV2.o : populationbriqueV2.cpp
	$(CC) -c populationbriqueV2.cpp

balle.o : balle.cpp
	$(CC) -c balle.cpp

joueur.o : joueur.cpp
	$(CC) -c joueur.cpp

terrain_v2.o : terrain_v2.cpp
	$(CC) -c terrain_v2.cpp

option.o : option.cpp
	$(CC) -c option.cpp

taboption.o : taboption.cpp
	$(CC) -c taboption.cpp

menu.o : menu.cpp
	$(CC) -c menu.cpp

kassbrik.o : kassbrik.cpp
	$(CC) -c kassbrik.cpp


$(PROG) : $(SOURCE:.cpp=.o)
	$(CC) $(SOURCE:.cpp=.o) -o $(PROG) -lncurses