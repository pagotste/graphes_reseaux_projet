package comptes_ronds_dussuet_pagot;


public class Programme {
	public static int nbli;
	public static int nbcol;
	
	public String resultats(Reseau r) {
		int size = r.sommets.size();
		String res = "";
		res = res+"Résultats des arrondis\n";
		
		res = res + "[ ";
		
		for(int k = 1 ; k < nbli+1;k++) {
			for(int t= 0; t < nbcol; t++) {
				res = res + (r.get_min(1+k, nbli+2*k+t)+r.get_flot(1+k, nbli+2*k+t)) +" ";
			}
		}
		res = res + " sommes des lignes arrondies :\n";
		for (int i = 1; i < nbli+1;i++) {
			res = res + "ligne " + i + " :" + (r.get_min(1, 1+i)+ r.get_flot(1, 1+i)) + "\n";
		}
		res = res + " sommes des colonnes arrondies :\n";
		for (int j = 1; j < nbcol+1;j++) {
			res = res + "colonne " + j + " :" + (r.get_min(size-4-nbcol+j, size-3)+ r.get_flot(size-4-nbcol+j, size-3)) + "\n";
		}
		
		return res;
	}
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
		//Test preflot
//		Reseau.preflot(graphe, graphe.sommets.get(0), graphe.sommets.get(graphe.sommets.size()-1));
//		System.out.println("Reseau initial:\n" + graphe.toString());
		
//		System.out.println("\nReseau residuel:\n" + residuel.toString());
		
		//System.out.println(Reseau.constructionReseau("dataset1.txt").toString());
		//System.out.println(Reseau.constructionReseau("dataset_exemple.txt").toString());
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
		//Programme de jeux de données
				/*
				if(args.length==0) {
					Reseau reseau = Reseau.constructionReseau(args[0]);
					reseau.constructionEtape3();
					reseau.constructionEtape2();
					// somme des demandes positives des sommets
					int somme_r = 0;
					for(int i = 0; i < reseau.sommets.size()-1;i++) {
						int demande = reseau.sommets.get(i).demande;
						somme_r += (demande>0) ? demande : 0 ;
					}
					reseau.constructionEtape1(somme_r);
					Reseau.preflot(reseau, reseau.sommets.get(0), reseau.sommets.get(reseau.sommets.size()-1));
					
					resultats(reseau);
				}*/
		
		
	}
}
