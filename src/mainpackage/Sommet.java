package mainpackage;

import java.util.ArrayList;
import java.util.List;

public class Sommet {
	
	private int num_sommet;
	private boolean appartenance_cycle;
	private Sommet pere;
	private Etat etat;
	private List<Sommet> voisins;
	
	public Sommet(int num_sommet) {
		super();
		this.num_sommet = num_sommet;
		etat=Etat.Non_Atteint;
		voisins=new ArrayList<Sommet>();
	}

	public int getNum_sommet() {
		return num_sommet;
	}

	public boolean isAppartenance_cycle() {
		return appartenance_cycle;
	}

	public Sommet getPere() {
		return pere;
	}

	public Etat getEtat() {
		return etat;
	}

	public void setAppartenance_cycle(boolean appartenance_cycle) {
		this.appartenance_cycle = appartenance_cycle;
	}

	public void setPere(Sommet pere) {
		this.pere = pere;
	}

	public void setEtat(Etat etat) {
		this.etat = etat;
	}
	public void cleanProperties(){
		pere=null;
		etat=Etat.Non_Atteint;
	}
	public void ajouterVoisins(Sommet s){
		voisins.add(s);
	}

	public List<Sommet> getVoisins() {
		return voisins;
	}
	
	
	

}
