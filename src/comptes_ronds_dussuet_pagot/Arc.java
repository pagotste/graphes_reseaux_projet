package comptes_ronds_dussuet_pagot;

public class Arc {
	Sommet s_arrivee;
	int min;
	int capacite;
	int flot;
	
	Arc(Sommet s, int min, int c){
		this.s_arrivee = s;
		this.min = min;
		this.capacite = c;
		this.flot = 0;
	}
	
	@Override
	public String toString() {
		return "[ " + this.s_arrivee.nom + ", min= " + this.min + ", c= "+  this.capacite + ", flot=" + this.flot + "]";
	}
}
