package comptes_ronds_dussuet_pagot;

import java.util.ArrayList;

public class Reseau {
	
	ArrayList<Sommet> reseau;
	ArrayList<Sommet> reseau_residuel;
	
	//Constructeur
	Reseau(){
		this.reseau = new ArrayList<Sommet>();
		this.reseau_residuel= new ArrayList<Sommet>();
	}
	
	//Cree un reseau residuel a partir d'un graphe
	void cree_resi(Reseau reseau){
		ArrayList<Sommet> residuel = new ArrayList<Sommet>();
		
		this.reseau_residuel = residuel;
	}
	
	//Met a jour un reseau residuel
	void update_resi(Reseau reseau){
		ArrayList<Sommet> residuel = new ArrayList<Sommet>();
		
		this.reseau_residuel = residuel;
	}
	
	
	
	//Preflots
	//Initialise le graphe pour la methode des preflots
	static void initialiser_preflot(ArrayList<Sommet> G, Sommet source) {
		for (Sommet u : G) {
			u.hauteur = 0;
			u.excedent = 0;
		}
		source.hauteur = G.size();
		
		for (Sommet u : G) {
			for (Arc arc : u.successeurs) {
				arc.flot = 0;
			}
			
			for (Arc inverse : u.inverse) {
				inverse.flot = 0;
			}
		}
		
		for(Arc arc : source.successeurs) {
			//f(source,u) = 0
			arc.flot = arc.capacite;
			
			//f(u,source) = 0
			arc.s_arrivee.inverse.getFirst().flot = -arc.capacite; //On considere que la source est le 1er elem. de la liste d'inverse
			
			arc.s_arrivee.excedent = arc.capacite;
			source.excedent = source.excedent - arc.capacite;
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
	
	void constructionEtape1() {
		
	}
	
	void constructionEtape2() {
			
		}
	
	void constructionEtape3() {
		
	}
	
	//Construit le reseau modelisant le probleme Arrondis-2D comme un probleme d’arc-circulation a partir des donnees en entree
	void constructionReseau() {
		
	}
	
	//TODO
	@Override
	public String toString() {
		String res = "";
		
		return res;
	}
	
	public static void main(String[] args) {
		ArrayList<Sommet> graphe = new ArrayList<Sommet>();
		Sommet source = new Sommet("source",-28);
		Sommet s1 = new Sommet("s1",2);
		Sommet s2 = new Sommet("s2",3);
		Sommet s3 = new Sommet("s3",5);
		Sommet s4 = new Sommet("s4",4);
		Sommet puits = new Sommet("puits",0);
		
		graphe.add(source);		graphe.add(s1);		graphe.add(s2);		graphe.add(s3);		graphe.add(s4);		graphe.add(puits);
		
		source.addSuccesseur(s1, 3, 5);
		source.addSuccesseur(s2, 2, 6);
		s1.addSuccesseur(s3, 1, 5);
		s1.addSuccesseur(s4, 0, 5);
		s2.addSuccesseur(s4, 5, 5);
		s3.addSuccesseur(puits, 3, 7);
		s4.addSuccesseur(puits, 4, 20);
		
		initialiser_preflot(graphe,source);
		System.out.println("source:" + source.toString() + "->" + source.printSuccesseurs());
		System.out.println("source:" + source.toString() + "->" + source.printInverse());
		System.out.println("S1:" + s1.toString() + "->" + s1.printSuccesseurs());
		System.out.println("S1:" + s1.toString() + "->" + s1.printInverse());
		System.out.println("s2:" + s2.toString() + "->" + s2.printSuccesseurs());
		System.out.println("S2:" + s2.toString() + "->" + s2.printInverse());
		System.out.println("S3:" + s3.toString() + "->" + s3.printSuccesseurs());
		System.out.println("S3:" + s3.toString() + "->" + s3.printInverse());
		System.out.println("\nHauteur source = " + source.hauteur);
		System.out.println("Excedent source = " + source.excedent);
		System.out.println("\nHauteur S1 = " + s1.hauteur);
		System.out.println("Excedent S1 = " + s1.excedent);
		System.out.println("\nHauteur S2 = " + s2.hauteur);
		System.out.println("Excedent S2 = " + s2.excedent);
		System.out.println("\nHauteur S3 = " + s3.hauteur);
		System.out.println("Excedent S3 = " + s3.excedent);
		
	}
}
