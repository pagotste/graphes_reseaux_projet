package comptes_ronds_dussuet_pagot;

import java.util.ArrayList;

public class Preflots {
	
	void initialiser_preflot(ArrayList<Sommet> G, Sommet source) {
		for (Sommet u : G) {
			u.hauteur = 0;
			u.excedent = 0;
			source.hauteur = G.size();
		}
		
		for (/*tout arc de G*/) {
			arc.flot = 0;
			inverse.flot = 0;
		}
		
		for(/*tout arc d'origine source*/) {
			//iterateur => arc
			arc.flot = arc.capacite;
			inverse.flot = -arc.capacite;
			u.excedent = arc.capacite;
			source.excedent = source.excedent - arc.capacite;
		}	
	}
	
	void avancer(Sommet u, Sommet v) {
		//precondition: excedent de u>0, capacite uv>0, hauteur u = hauteur de v +1
		int df = min(u.excedent,arc.capacite);
		arc.flot = arc.flot + df;
		inverse.flot = -arc.flot;
		u.excedent = u.excedent - df;
		v.excedent = v.excedent + df;
	}
	
	void elever(Sommet u) {
		//u different de source et puis
		//precondition: excedent de u>0 et pour tout successeur v de u, hauteur de u <= hauteur de v
		u.hauteur = 1 + min(/*hauteur de tous les successeurs de u*/);
	}
	
	void preflot_generique(ArrayList<Sommet> graphe, Sommet source, Sommet puits) {
		initialiser_preflot(graphe, source);
		boolean avancer = true;
		boolean elever = true;
		while(avancer || elever) { //tq une operation est applicable
			avancer = false;
			elever = false;
			/*algo: choix d'une operation applicable*/
			if(avancer && !elever) avancer(u,v);
			else if(!avancer && elever) elever(u);
		}
	}
	public static void main(String[] args) {
	//test
	}
}
