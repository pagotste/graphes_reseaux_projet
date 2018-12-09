package comptes_ronds_dussuet_pagot;

import java.util.ArrayList;
import java.util.Collections;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.Math;

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
	
	//Initialise un reseau residuel
	@SuppressWarnings("unchecked") //pour le cast en ArrayList
	Reseau create_resi() {
		Reseau resi = new Reseau();
		for (Sommet s : this.sommets) resi.sommets.add((Sommet)s.clone());
		for (int i=0;i<this.capacite.size();i++) resi.capacite.add((ArrayList<Integer>)this.capacite.get(i).clone());
		return resi;
	}
	
	//Met a jour un reseau residuel
	Reseau update_resi(Reseau res_resi, int u, int v){
		int new_c = res_resi.get_capacite(u, v)-this.get_flot(u, v);
		if(new_c == 0) {
			res_resi.set_capacite(u, v, null);
			res_resi.set_capacite(v, u, this.get_flot(u, v));
		} else if (this.get_flot(u, v)==0) {
			res_resi.set_capacite(u, v, this.get_capacite(u, v));
			res_resi.set_capacite(v, u, null);
		} else {
			res_resi.set_capacite(u, v, new_c);
			res_resi.set_capacite(v,u,this.get_flot(u, v));
		}
		return res_resi; 
	}
	
	//Getter et setter
	//sommet representes par des int
	Integer get_flot(int u, int v) {
		return this.flot.get(u).get(v);
	}
	
	void set_flot(int u, int v, Integer flot) {
		this.flot.get(u).set(v, flot);
	}
	
	Integer get_capacite(int u, int v) {
		return this.capacite.get(u).get(v);
	}
	
	void set_capacite(int u, int v, Integer capacite) {
		this.capacite.get(u).set(v, capacite);
	}
	
	Integer get_min(int u, int v) {
		return this.min.get(u).get(v);
	}
	
	void set_min(int u, int v, Integer flot) {
		this.min.get(u).set(v, flot);
	}
	
	//sommet representes par le type sommet
	
	
	Integer get_flot(Sommet u, Sommet v) {
		return this.flot.get(this.sommets.indexOf(u)).get(this.sommets.indexOf(v));
	}
	
	void set_flot(Sommet u, Sommet v, Integer flot) {
		this.flot.get(this.sommets.indexOf(u)).set(this.sommets.indexOf(v), flot);
	}
	
	Integer get_capacite(Sommet u, Sommet v) {
		return this.capacite.get(this.sommets.indexOf(u)).get(this.sommets.indexOf(v));
	}
	
	void set_capacite(Sommet u, Sommet v, Integer capacite) {
		this.capacite.get(this.sommets.indexOf(u)).set(this.sommets.indexOf(v), capacite);
	}
	Integer get_min(Sommet u, Sommet v) {
		return this.min.get(this.sommets.indexOf(u)).get(this.sommets.indexOf(v));
	}
	
	void set_min(Sommet u, Sommet v, Integer flot) {
		this.min.get(this.sommets.indexOf(u)).set(this.sommets.indexOf(v), flot);
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
	void avancer(Reseau res_resi,Sommet u, Sommet v) {
		int df = Math.min(u.excedent,res_resi.get_capacite(u, v));
		this.set_flot(u, v, this.get_flot(u,v) + df);
		this.set_flot(v, u, -this.get_flot(u, v));
		u.excedent = u.excedent - df;
		v.excedent = v.excedent + df;
	}
	
	//Eleve la hauteur d'un sommet dans le graphe
	void elever(Reseau res_resi,Sommet u) {
		ArrayList<Integer> h_list = new ArrayList<Integer>();
		for (int i = 0; i<this.sommets.size();i++) {
			if(this.get_capacite(this.sommets.indexOf(u), i) != null) h_list.add(this.sommets.get(i).hauteur);
		}
		int min = Collections.min(h_list);
		u.hauteur = 1 + min;
	}
	
	//Algorithme des preflots
	void preflot(Reseau graphe, Sommet source, Sommet puits) {
		initialiser_preflot(graphe, source);
		
//		while(!end) { //tq une operation est applicable
//			Sommet u = graphe.sommets.get(0);
//			while()
//			
//			//avancer
//			//precondition: excedent de u>0, capacite uv>0, hauteur u = hauteur de v +1 => avancer = true
//			
//			//elever
//			//u different de source et puis
//			//precondition: excedent de u>0 et pour tout arc (u,v) v adjacent a u ds reseau residuel, hauteur de u <= hauteur de v
//			if(avancer && !elever) avancer(u,v);
//			else if(!avancer && elever) elever(u);
//		}
	}
	
	//TODO
	void constructionEtape1(int v) {
		// ajout du nouveau puits
		this.sommets.add(new Sommet("puits_bis",0));
		// ajout capacite du puits en colonne
		for (int i=0;i<this.sommets.size()-2;i++){
			this.capacite.get(i).add(null);
		}
		// ajout capacite sur arc paratant de l'ancien puits vers le nouveau
		this.capacite.get(this.sommets.size()-2).add(v);
		// ajout dans capacite du puits en ligne
		this.capacite.add(new ArrayList<Integer>());
		for(int j = 0; j<this.sommets.size();j++) {
			this.capacite.get(this.sommets.size()-1).add(null);
		}
	}
	
	//TODO
	void constructionEtape2() {
		//ajout de la nouvelle source des demandes négatives
		this.sommets.add(0, new Sommet("s_dem_neg",0));
		
		
		// ajout capacite sur les arcs partant de la nouvelle source vers les autres sommets
		this.capacite.add(0,new ArrayList<Integer>());
		for(int k = 1; k < this.sommets.size();k++) {
			int demande = this.sommets.get(k).demande;
			this.capacite.get(0).add((demande<0)? -demande : null );
		}
			
		// ajout dans capacite de la nouvelle source en colonne
		for(int j = 0; j< this.sommets.size();j++) {
			this.capacite.get(j).add(0,null);
		}
		
		// ajout du nouveau puits des demandes positives
		this.sommets.add(new Sommet("s_dem_pos",0));
		
		// ajout capacite sur les arcs partant des sommets vers le nouveau puits
		for (int i=0;i<this.sommets.size()-1;i++){
			int demande = this.sommets.get(i).demande;
			this.capacite.get(i).add((demande>0)? demande : null);
		}
		// ajout dans capacite du nouveau puits en ligne
		this.capacite.add(new ArrayList<Integer>());
		for(int j = 0; j<this.sommets.size();j++) {
			this.capacite.get(this.sommets.size()-1).add(null);
		}
	}
	
	//TODO
	void constructionEtape3() {
		
		int size = this.sommets.size();
		int d_source = 0;
		int d_puits = 0;
		for (int i=1; i<size-1;i++) {
			Integer cap = this.get_capacite(0, i);
			if(cap != null) {
				int min = this.get_min(0, i);
				this.set_capacite(0, i, cap-min);
				this.sommets.get(i).demande -= min;
				d_source += this.get_capacite(0, i);
			}
		}
		
		for (int i=1; i<size-1;i++) {
			for(int j=0; j<size-1;j++) {
				Integer cap = this.get_capacite(i, j);
				if(cap != null) {
					int min = this.get_min(i,j);
					this.set_capacite(i, j, cap-min);
					this.sommets.get(i).demande += min;
					this.sommets.get(j).demande -= min;
				}
			}
		}
		for (int i=1; i<size-1;i++) {
			Integer cap = this.get_capacite(i, size-1);
			if(cap != null) {
				int min = this.get_min(i, size-1);
				this.set_capacite(i, size-1, cap-min);
				this.sommets.get(i).demande += min;
				d_puits += this.get_capacite(i, size-1);
			}
		}
		
		Integer cap = this.get_capacite(0, size-1);
		if(cap != null) {
			int min = this.get_min(0, size-1);
			this.set_capacite(0, size-1, cap-min);
			// nouvelle valeur de la capacite
			cap = this.get_capacite(0, size-1);
			d_source += cap;
			d_puits += cap;
		}
		this.sommets.get(0).demande = -d_source;
		this.sommets.get(size-1).demande = d_puits;
	}
	
	//TODO: import fichier + construction
	//Construit le reseau modelisant le probleme Arrondis-2D comme un probleme d�arc-circulation a partir des donnees en entree
	static Reseau constructionReseau(String nomfichier) {
		//Variables
			int nbli = 0,nbcol;
			Reseau r = new Reseau();
			// lecture du fichier texte et traitement des données
			try {
				FileReader f = new FileReader(nomfichier);
				BufferedReader b = new BufferedReader(f);
				nbli = Integer.parseInt(b.readLine());
				nbcol = Integer.parseInt(b.readLine());
				
				// ajout des sommets dans le graphe
				r.sommets.add(new Sommet("source",0));
				for (int i=1; i<nbli+1;i++) {
					r.sommets.add(new Sommet("somme_l"+String.valueOf(i),0));
				}
				for (int i=1;i<nbli+1;i++) {
					for(int j=1;j<nbcol+1;j++) {
						r.sommets.add(new Sommet("m"+String.valueOf(i)+String.valueOf(j),0));
					}
				}
				for (int i=1; i<nbcol+1;i++) {
					r.sommets.add(new Sommet("somme_c"+String.valueOf(i),0));
				}
				r.sommets.add(new Sommet("puits",0));
				
				
				int size = nbli*nbcol+nbli+nbcol+2;
				
				r.min = Reseau.init_matrix(r.min,size);
				r.capacite = Reseau.init_matrix(r.capacite,size);
				String ligne;
				// le numéro de la première ligne 
				int m_ligne = 1;
				ligne = b.readLine();
				ligne = ligne.replace(",", ".");
				// initialisation d'un  tableau de somme des colonnes 
				float[] s_col = new float[nbcol];
				
				
				// ajout des min, capacite sur les arcs du graphe
				while( !ligne.equals(".") && (ligne != null)  ) {
					// récupération des valeurs séparées par un ou plusieurs espaces dans un tableau de chaînes de caractères
					String[] t = ligne.split("\\s+");
					// initialisation d'une variable pour les sommes des lignes
					float s_ligne = 0;
					
					for (int i = 0; i < t.length; i++) {
						float t_i = Float.parseFloat(t[i]);
						s_ligne += t_i;
						s_col[i] += t_i;
						// ajout min, capacite sur les arcs partantdes sommes des lignes vers les valeurs mij de la matrice
						r.min.get(m_ligne).set((nbli)*m_ligne+1+i, (int) Math.floor(t_i));
						r.capacite.get(m_ligne).set((nbli)*m_ligne+1+i, (int) Math.ceil(t_i));
						// ajout min,capacite sur les arcs partant des valeurs mij de la matrice vers les sommes des colonnes
						r.min.get((nbli)*m_ligne+1+i).set((size-1)-nbcol+i, (int) Math.floor(t_i));
						r.capacite.get((nbli)*m_ligne+1+i).set((size-1)-nbcol+i, (int) Math.ceil(t_i));
					}
					// ajout min, capacite sur les arcs partant de la source sur les sommes des lignes
					r.min.get(0).set(m_ligne, (int) Math.floor(s_ligne));
					r.capacite.get(0).set(m_ligne, (int) Math.ceil(s_ligne));
					// passage à la ligne suivante et lecture de la ligne suivante
					ligne = b.readLine();
					ligne = ligne.replace(",", ".");
					m_ligne++;
				}
				// ajout min, capacite sur les arcs partant des sommes des colonnes vers le puits
				for(int i = 0; i<nbcol;i++) {
					r.min.get((size-1)-nbcol+i).set(size-1,(int) Math.floor(s_col[i]));
					r.capacite.get((size-1)-nbcol+i).set(size-1,(int) Math.ceil(s_col[i]));
				}
				
				// arrêt de la lecture du fichier
				b.close();
				f.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
		// retourne le réseau construit avec les données du fichier si pas d'erreur sinon un réseau vide
		return r;
	}
	
	@Override
	public String toString() {
		String res = "";
		res = res+"Liste de sommets\n";
		res = res + this.sommets.toString();
		
		if(this.capacite.size()!=0) {
			res = res + "\n\nMatrice capacite:\n";
			for(int i=0;i<this.capacite.size();i++) {
				res = res + this.capacite.get(i).toString() + "\n";
			}
		}
		
		if(this.flot.size()!=0) {
			res = res + "\n\nMatrice flots:\n";
			for(int i=0;i<this.flot.size();i++) {
				res = res + this.flot.get(i).toString() + "\n";
			}
		}
		
		if(this.min.size()!=0) {
			res = res + "\n\nMatrice min:\n";
			for(int i=0;i<this.min.size();i++) {
				res = res + this.min.get(i).toString() + "\n";
			}
		}
		
		return res;
	}
}

