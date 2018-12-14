package comptes_ronds_dussuet_pagot;


public class Programme {
	public static int nbli;// le nombre de ligne
	public static int nbcol;// le nombre de colonne
	public static int d_source_max;// la somme des capacites des arcs sortants de la source lors de la constructionEtape3
	
	// Donne la solution au probleme arrondi-2D 
	static public String resultats(Reseau r) {
		int size = r.sommets.size();
		String res = "";
		res = res+"Resultats des arrondis\n";
		// recupere les arrondis des mij de la matrice M de depart
		for(int k = 0 ; k < nbli;k++) {
			for(int t= 0; t < nbcol; t++) {
				res = res + (r.get_min(k+1, nbli+1+t)+r.get_flot(k+2, nbli+2+t)) +" ";
			}
			res = res + "\n";
		}
		// recupere les arrondis des sommes des lignes de la matrice M de depart
		res = res + "\nSommes des lignes arrondies :\n";
		for (int i = 1; i < nbli+1;i++) {
			res = res + "ligne " + i + " : " + (r.get_min(0,i)+r.get_flot(1, 1+i) + "\n");
		}
		// recupere les arrondis des sommes des colonnes de la matrice M de depart
		res = res + "Sommes des colonnes arrondies :\n";
		for (int j = 0; j < nbcol;j++) {
			res = res + "colonne " + (j+1) + " : " + (r.get_min(nbli+1+j, size-4)+ r.get_flot(nbli+2+j, size-3)) + "\n";
		}
		
		return res;
	}
	
	/* teste si le flot qui a ete trouve par le preflot sur les arcs de la construction initiale du reseau
	 * rend bien une solution au probleme arrondi-2D 
	 */
	static public boolean test_probleme(Reseau r) {
		int size = r.sommets.size();// nombre de sommets
		boolean test = true;// initialisation du test
		int k = 0;// initialisation de k
		int[] somme_col = new int[nbcol];
		while(test && (k<nbli)) {
			int somme = 0;
			for(int j = 0; j < nbcol; j++) {
				somme += r.get_min(k+1, nbli+1+j)+r.get_flot(k+2, nbli+2+j);
				somme_col[j] += r.get_min(k+1, nbli+1+j)+r.get_flot(k+2, nbli+2+j);
			}
			/* si la valeur arrondie de la somme de la ligne k+1 n'est pas egale à 
			 * la somme des valeurs arrondies de la ligne k+1 alors
			 * ce n'est pas une solution au probleme
			 */
			if(somme != (r.get_min(0,k+1)+r.get_flot(1, k+2))) {
				test=false;
			}
			k++;
		}
		k = 0;
		/*Pour chaque colonne si la valeur arrondie de la somme de la colonne n'est pas egale a 
		 * la somme des valeurs arrondies de la colonne alors
		 * ce n'est pas une solution au probleme
		 */
		while(test && (k<nbcol)) {
			if(somme_col[k] != (r.get_min(nbli+1+k,size-4)+r.get_flot(nbli+2+k, size-3))) {
				test = false;
			}
			k++;
		}
		
		return test;
	}

	public static void main(String[] args) {
		// si un ou plusieurs fichier sont entres en parametre
		if(args.length>0) {
			// si plusieurs fichiers sont entres en parametre on avertit l'utilisateur
			if(args.length>1) {
				System.out.println("Il ne faut qu'un seul fichier, seul le premier fichier sera pris en compte dans les résultats ci-dessous");
			}
				// on execute la resolution au probleme arrondi-2D sur le fichier qui a ete saisi en premier argument
				System.out.println("Solution au probleme arrondi-2D pour la matrice du fichier :" + args[0]);
				Reseau R = Reseau.constructionReseau(args[0]);
				R.constructionEtape3();
				R.constructionEtape2();
				// on cherche la somme des demandes positives
				int somme = 0;
				for(int i = 0; i < R.sommets.size()-1;i++) {
					int demande = R.sommets.get(i).demande;
					somme += (demande>0) ? demande : 0 ;
				}
				// on effectue l'etape 3 avec la somme des demandes positives en parametre
				R.constructionEtape1(somme);
				// on initialise la matrice de flot et celle d'adjacence
				R.flot = Reseau.init_matrix(R.flot,R.sommets.size());
				R.adjacence = Reseau.init_matrix_zero(R.adjacence,R.sommets.size());
				// on entre en parametre du preflot le reseau, sa source et son puits
				Reseau.preflot(R, R.sommets.get(0), R.sommets.get(R.sommets.size()-1));
				
				int i = 1;
				// tant que le resultat trouve ne repond pas au probleme arrondi-2D
				while(!test_probleme(R) && i<d_source_max) {
					// on incrémente de 1 la capacite de l'arc partant de la source ( s_dem_neg) vers l'ancienne source (s) 
					R.set_capacite(0, 1, i+1);
					// on reexcute le preflot sur le reseau modifie
					Reseau.preflot(R, R.sommets.get(0), R.sommets.get(R.sommets.size()-1));
					i++;
				}
				// affichage des resultats
				System.out.println(resultats(R));
		}else {
			System.out.println("vous n'avez pas entrer de fichier en paramètre");

		}
							
	}
}