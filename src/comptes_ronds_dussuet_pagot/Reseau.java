package comptes_ronds_dussuet_pagot;

import java.util.ArrayList;

public class Reseau {
	
	ArrayList<Sommet> sommets;
	ArrayList <ArrayList<Integer>> min;
	ArrayList <ArrayList<Integer>> capacite;
	ArrayList <ArrayList<Integer>> flot;
	
	//Constructeur
	Reseau(){
		this.sommets = new ArrayList<Sommet>();
		this.min = new ArrayList <ArrayList<Integer>>();
		this.capacite = new ArrayList <ArrayList<Integer>>();
		this.flot = new ArrayList <ArrayList<Integer>>();
	}
	
	//Remplit une matrice de 0
	static ArrayList <ArrayList<Integer>> init_matrix(ArrayList <ArrayList<Integer>> liste, int size) {
		for (int i = 0; i <size;i++) {
			liste.add(new ArrayList<Integer>());
			for(int j = 0; j<size;j++) {
				liste.get(i).add(null);
			}
		}
		return liste;
	}
	
	
	//Met a jour un reseau residuel
	void update_resi(Reseau reseau, Sommet u, Sommet v){
		
//		
	}
	

	//Preflots
	//Initialise le graphe pour la methode des preflots
	static void initialiser_preflot(Reseau G, Sommet source) {
		for (Sommet u : G.sommets) {
			u.hauteur = 0;
			u.excedent = 0;
		}
		G.sommets.get(0).hauteur = G.sommets.size();
		
		for (int i = 0;i<G.sommets.size();i++) {
			for(int j=0;j<G.sommets.size();j++) {
				if(G.capacite.get(i).get(j) != null) {
					G.flot.get(i).set(j, 0);
					G.flot.get(j).set(i, 0);
				}
			}
		}

		
		for(int i=1;i<G.sommets.size();i++) {
			Integer capa = G.capacite.get(0).get(i);
			if(capa != null) {
				//f(source,u) = 0;
				G.flot.get(0).set(i, capa);
				//f(u,source) = 0
				G.flot.get(i).set(0, -capa);
				
				G.sommets.get(i).excedent=capa;
				G.sommets.get(0).excedent = G.sommets.get(0).excedent - capa; 
			}
		}	
	}
	
	//Avance le flot sur un arc dans le graphe
//	void avancer(Sommet u, Sommet v) {
//		int df = min(u.excedent,residuel.capacite);
//		arc.flot = arc.flot + df;
//		inverse.flot = -arc.flot;
//		u.excedent = u.excedent - df;
//		v.excedent = v.excedent + df;
//	}
	
	//Eleve la hauteur d'un sommet dans le graphe
//	void elever(Sommet u) {
//		//arc => reseau residuel
//		int min = u.successeurs.getFirst().s_arrivee.hauteur;
//		for(Arc arc : u.successeurs) min = Math.min(min,arc.s_arrivee.hauteur);
//		u.hauteur = 1 + min;
//	}
//	
	//Algorithme des preflots
//	void preflot(ArrayList<Sommet> graphe, Sommet source, Sommet puits) {
//		initialiser_preflot(graphe, source);
//		boolean avancer = true;
//		boolean elever = true;
//		while(avancer || elever) { //tq une operation est applicable
//			avancer = false;
//			elever = false;
//			/*algo: choix d'une operation applicable*/
			//avancer
//			//precondition: excedent de u>0, capacite uv>0, hauteur u = hauteur de v +1 => avancer = true
//			
			//elever
			//u different de source et puis
//			//precondition: excedent de u>0 et pour tout arc (u,v) v adjacent à u ds réseau résiduel, hauteur de u <= hauteur de v
//			if(avancer && !elever) avancer(u,v);
//			else if(!avancer && elever) elever(u);
//		}
//	}
	
	//TODO
	void constructionEtape1() {
		
	}
	
	//TODO
	void constructionEtape2() {
			
		}
	
	//TODO
	void constructionEtape3() {
		
	}
	
	//TODO: import fichier + construction
	//Construit le reseau modelisant le probleme Arrondis-2D comme un probleme d’arc-circulation a partir des donnees en entree
	 static Reseau constructionReseau() {
		return new Reseau();
	}
	
	@Override
	public String toString() {
		String res = "";
		res = res+"Liste de sommets\n";
		res = res + this.sommets.toString();
		res = res + "\nMatrice min:\n";
		res = res + "\n\nMatrice capacite:\n";
		for(int i=0;i<this.sommets.size();i++) {
			res = res + this.capacite.get(i).toString() + "\n";
		}
		res = res + "\n\nMatrice flots:\n";
		for(int i=0;i<this.sommets.size();i++) {
			res = res + this.flot.get(i).toString() + "\n";
		}
		/*res = res + "\n\nMatrice min:\n";
		for(int i=0;i<this.sommets.size();i++) {
			res = res + this.min.get(i).toString() + "\n";
		}*/
		res = res+"\nReseau residuel\n";
		return res;
	}
	}
