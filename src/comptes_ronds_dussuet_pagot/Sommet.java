package comptes_ronds_dussuet_pagot;

import java.util.LinkedList;
import java.util.ListIterator;

public class Sommet {
	String nom;
	int demande;
	int hauteur;
	int excedent;
	LinkedList <Arc> successeurs;
	LinkedList <Arc> inverse;
	
	Sommet(String n,int dem){
		this.nom = n;
		this.demande = dem;
		this.successeurs = new LinkedList<Arc>();
		this.inverse = new LinkedList<Arc>();
		this.hauteur = 0;
		this.excedent = 0;
	}
	
	void addSuccesseur(Sommet s,int min, int c) {
		this.successeurs.addLast(new Arc(s,min,c));
		s.inverse.addLast(new Arc(this,min,c));
	}
	
	String printSuccesseurs() {
		String res = "";
		ListIterator<Arc> li = this.successeurs.listIterator();
		while(li.hasNext()) {
			res = res+li.next().toString()+" ";
		}
		return res;
	}
	
	String printInverse() {
		String res = "";
		ListIterator<Arc> li = this.inverse.listIterator();
		while(li.hasNext()) {
			res = res+li.next().toString()+" ";
		}
		return res;
	}
	
	@Override
	public String toString() {
		return "[ "+this.nom+", d="+this.demande+ ", h= "+this.hauteur+", exe = "+this.excedent+ " ]";
	}

	
	
	public static void main(String[] args) {
		Sommet s1 = new Sommet("s1",-5);
		Sommet s2 = new Sommet("s2",3);
		Sommet s3 = new Sommet("s3",4);
		
		s1.addSuccesseur(s2,1,3);
		s1.addSuccesseur(s3,5,6);
		s2.addSuccesseur(s3,3,5);
		
		System.out.println("S1:" + s1.toString() + "->" + s1.printSuccesseurs());
		System.out.println("S2:" + s2.toString() + "->" + s2.printSuccesseurs());
		System.out.println("S3:" + s3.toString() + "->" + s3.printSuccesseurs());
		
		
	}
}

