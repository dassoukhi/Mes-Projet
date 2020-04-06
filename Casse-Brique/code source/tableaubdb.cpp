#include <iostream>
#include "tableaubdb.h"
#include "boutdebrique.h"

using namespace std;

TableauBDB::TableauBDB() : n(1), nMax(1), T(new BoutDeBrique[1]) {
}

TableauBDB::TableauBDB(size_t _n) : n(_n), nMax(_n) { T = new BoutDeBrique[n]; }

TableauBDB::TableauBDB(const TableauBDB& A) : n(A.n), nMax(A.nMax) {
	T=new BoutDeBrique[nMax];
	for(size_t i=0; i<n; i++) {
		T[i] = A.T[i];
	}
}

TableauBDB::~TableauBDB() { delete[] T; } 

TableauBDB& TableauBDB::operator=(const TableauBDB& A) {
	if (this!=&A) {
		delete[] T;
		n=A.n;
		nMax = n;
		T=new BoutDeBrique[nMax];
		for(size_t i=0; i<n; i++) {
			T[i]=A.T[i];
		}
	}
	return *this;
}

BoutDeBrique& TableauBDB::operator[](int i) {
	if ((i>=1) && (i<n)) {
		return T[i];
	}
	else {
		cerr << "problème avec l'index BDB!"<<i;
		exit(1);
	}
}

BoutDeBrique TableauBDB::operator[](int i) const {
	if ((i>=1) && (i<n)) {
		return T[i];
	}
	else {
		cerr << "problème avec l'index BDB!";
		exit(1);
	}
}

size_t TableauBDB::getTaille() const { return n; }

void TableauBDB::setTaille(size_t _n) {
	n = _n;
}

bool estEgale(const BoutDeBrique& B1, const BoutDeBrique& B2) {
	return ((B1.getX() == B2.getX()) && (B1.getY() == B2.getY()) && (B1.getResistance() == B2.getResistance()));
}

int TableauBDB::find(const BoutDeBrique& B) {
	for (size_t i=0; i<n; i++) {
		if (estEgale(B, T[i])) { return i; }
	}
	return -1;
}

void TableauBDB::extend(){
  if (n<nMax) return;
  nMax=2*n;
  BoutDeBrique* tmp=new BoutDeBrique[nMax];
  for(size_t i=0;i<n;i++){tmp[i]=T[i];}
  delete[] T;
  T=tmp;
}

void TableauBDB::push_back(const BoutDeBrique& B){
  extend();
  T[n]=B;
  n++;
}


void TableauBDB::erase(int indice) {
	if (indice >= 1 && indice < (int)n) {
		for (int i=indice; i<n-1; i++) {
			T[i] = T[i+1];
		}
		n--;
	}
}