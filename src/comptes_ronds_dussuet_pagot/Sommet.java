package comptes_ronds_dussuet_pagot;


public class Sommet implements Cloneable{
	String nom;
	int demande;
	int hauteur;
	int excedent;
	
	Sommet(String n,int dem){
		this.nom = n;
		this.demande = dem;
		this.hauteur = 0;
		this.excedent = 0;
	}
	
	@Override
	public String toString() {
		return "[ "+this.nom+", d="+this.demande+ ", h= "+this.hauteur+", exe = "+this.excedent+ " ]";
	}
	
	public Object clone(){  
	    try{  
	        return super.clone();  
	    }catch(Exception e){ 
	        return null; 
	    }
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(obj == null) {
			return false;
		}
		if(getClass() != obj.getClass()) {
			return false;
		}
		Sommet s = (Sommet) obj;
		return this.nom.equals(s.nom) && this.demande == s.demande && this.hauteur == s.hauteur && this.excedent == s.excedent;
	}
}

