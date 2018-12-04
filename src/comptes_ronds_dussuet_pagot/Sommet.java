package comptes_ronds_dussuet_pagot;

import java.util.LinkedList;
import java.util.ListIterator;

public class Sommet {
	String nom;
	int demande;
		
	LinkedList <Arc> successeurs;
	
	Sommet(int dem,String n){
		this.nom = n;
		this.demande = dem;
		this.successeurs = new LinkedList<Arc>();
	}
	
	void addSuccesseur(Sommet s,int min, int c, int f) {
		Arc a = new Arc(s,min,c,f);
		this.successeurs.addLast(a);
	}
	
	String printSuccesseurs() {
		String res = "";
		ListIterator<Arc> li = this.successeurs.listIterator();
		while(li.hasNext()) {
			res = res+li.next().toString()+" ";
		}
		return res;
	}
	
	@Override
	public String toString() {
		return "[ "+this.nom+", d="+this.demande+" ]";
	}

	
	
	public static void main(String[] args) {
		Sommet s1 = new Sommet(-5,"s1");
		Sommet s2 = new Sommet(3,"s2");
		Sommet s3 = new Sommet(4,"s3");
		
		s1.addSuccesseur(s2,1,3,0);
		s1.addSuccesseur(s3,5,6,0);
		s2.addSuccesseur(s3,3,5,0);
		
		System.out.println("S1:" + s1.toString() + "->" + s1.printSuccesseurs());
		System.out.println("S2:" + s2.toString() + "->" + s2.printSuccesseurs());
		System.out.println("S3:" + s3.toString() + "->" + s3.printSuccesseurs());
		
		
	}
}

