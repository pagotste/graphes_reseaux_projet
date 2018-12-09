package comptes_ronds_dussuet_pagot;


public class Programme {
	public static void main(String[] args) {
		Reseau graphe = new Reseau();
		graphe.sommets.add(new Sommet("source",-28));
		graphe.sommets.add(new Sommet("s1",2));
		graphe.sommets.add(new Sommet("s2",3));
		graphe.sommets.add(new Sommet("s3",5));
		graphe.sommets.add(new Sommet("s4",4));
		graphe.sommets.add(new Sommet("puits",0));
		
		graphe.flot = Reseau.init_matrix(graphe.flot,graphe.sommets.size());
		graphe.capacite = Reseau.init_matrix(graphe.capacite,graphe.sommets.size());
		
		graphe.capacite.get(0).set(1,5);
		graphe.capacite.get(0).set(2,6);
		graphe.capacite.get(1).set(3,5);
		graphe.capacite.get(1).set(4,5);
		graphe.capacite.get(2).set(4,5);
		graphe.capacite.get(3).set(5,7);
		graphe.capacite.get(4).set(5,20);
		
		//Test reseau residuel
//		Reseau residuel = graphe.create_resi();
//		System.out.println("\nReseau residuel:\n" + residuel.toString());
//		graphe.set_flot(0,1,0);
//		residuel = graphe.update_resi(residuel,0,1);
//		System.out.println("\nReseau residuel:\n" + residuel.toString());	
		
		//Test initialiser preflots
//		Reseau.initialiser_preflot(graphe, graphe.sommets.get(0));
//		System.out.println("Reseau initial:\n" + graphe.toString());
//		
//		System.out.println("\nReseau residuel:\n" + residuel.toString());
		//System.out.println(Reseau.constructionReseau("dataset1.txt").toString());
		//System.out.println(Reseau.constructionReseau("dataset_exemple.txt").toString());
		
	}
}
