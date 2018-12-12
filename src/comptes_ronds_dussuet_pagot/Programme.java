package comptes_ronds_dussuet_pagot;


public class Programme {
	public static void main(String[] args) {
		
		//Graphe de test
//		Reseau graphe = new Reseau();
//		graphe.sommets.add(new Sommet("source",0));
//		graphe.sommets.add(new Sommet("s1",2));
//		graphe.sommets.add(new Sommet("s2",3));
//		graphe.sommets.add(new Sommet("s3",5));
//		graphe.sommets.add(new Sommet("s4",4));
//		graphe.sommets.add(new Sommet("puits",0));
//		
//		graphe.flot = Reseau.init_matrix(graphe.flot,graphe.sommets.size());
//		graphe.capacite = Reseau.init_matrix(graphe.capacite,graphe.sommets.size());
//		graphe.adjacence = Reseau.init_matrix_zero(graphe.adjacence,graphe.sommets.size());
//		
//		graphe.capacite.get(0).set(1,16);
//		graphe.set_adj(0,1,1);
//		graphe.capacite.get(0).set(2,13);
//		graphe.set_adj(0,2,1);
//		graphe.capacite.get(1).set(2,10);
//		graphe.set_adj(1,2,1);
//		graphe.capacite.get(1).set(3,12);
//		graphe.set_adj(1,3,1);
//		graphe.capacite.get(2).set(4,14);
//		graphe.set_adj(2,4,1);
//		graphe.capacite.get(3).set(2,9);
//		graphe.set_adj(3,2,1);
//		graphe.capacite.get(4).set(3,7);
//		graphe.set_adj(4,3,1);
//		graphe.capacite.get(3).set(5,20);
//		graphe.set_adj(3,5,1);
//		graphe.capacite.get(4).set(5,4);
//		graphe.set_adj(4,5,1);
		
		//Test reseau residuel
//		Reseau residuel = graphe.create_resi();
//		System.out.println("\nReseau residuel:\n" + residuel.toString());
//		graphe.set_flot(1,3,5);
//		residuel = graphe.update_resi(residuel,1,3);
//		System.out.println("\nReseau residuel:\n" + residuel.toString());	
		
		//Test initialiser preflots
//		Reseau.initialiser_preflot(graphe, graphe.sommets.get(0));
//		System.out.println("Reseau initial:\n" + graphe.toString());
//		
		//Test preflot
		//Reseau.preflot(graphe, graphe.sommets.get(0), graphe.sommets.get(graphe.sommets.size()-1));
		//System.out.println("Reseau initial:\n" + graphe.toString());
		
		//System.out.println("\nReseau residuel:\n" + residuel.toString());
		
		//System.out.println(Reseau.constructionReseau("dataset1.txt").toString());
		//System.out.println(Reseau.constructionReseau("dataset_exemple.txt").toString());

		//Test avec l'exemple
//		Reseau exercice = Reseau.constructionReseau("dataset_exemple.txt");
//		exercice.constructionEtape3();
//		exercice.constructionEtape2();
//		exercice.constructionEtape1(6);
//		exercice.flot = Reseau.init_matrix(exercice.flot,exercice.sommets.size());
//		exercice.adjacence = Reseau.init_matrix_zero(exercice.adjacence,exercice.sommets.size());
//		
//		Reseau.preflot(exercice, exercice.sommets.get(0), exercice.sommets.get(exercice.sommets.size()-1));
//		System.out.println(exercice.toString());
		
		/*
		Reseau test = Reseau.constructionReseau("dataset_exemple.txt");
		System.out.println(test.toString());
		System.out.println((Integer)1 == (Integer)null);
		test.constructionEtape3();
		test.constructionEtape2();
		// somme des demandes positives des sommets
		int somme = 0;
		for(int i = 0; i < test.sommets.size()-1;i++) {
			int demande = test.sommets.get(i).demande;
			somme += (demande>0) ? demande : 0 ;
		}
		test.constructionEtape1(somme);
		System.out.println(test.toString());*/
	}
}
