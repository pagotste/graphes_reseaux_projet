package comptes_ronds_dussuet_pagot;

public class Arc {
	Sommet successeur;
	int min;
	int capacite;
	int flot;
	
	Arc(Sommet s, int min, int c, int f){
		this.successeur = s;
		this.min = min;
		this.capacite = c;
		this.flot = f;
	}
	
	@Override
	public String toString() {
		return "[ " + this.successeur.nom + ", min= " + this.min + ", c= "+  this.capacite + ", flot=" + this.flot + "]";
	}
}
