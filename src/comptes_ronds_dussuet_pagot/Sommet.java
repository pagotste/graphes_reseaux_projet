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
}

