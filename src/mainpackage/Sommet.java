package mainpackage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Sommet {

	private int num_sommet;
	private boolean appartenance_cycle;
	private Sommet pere;
	private Etat etat;
	private List<Sommet> voisins;
	private int distance;

	public Sommet(int num_sommet) {
		this.num_sommet = num_sommet;
		etat = Etat.Non_Atteint;
		voisins = new ArrayList<Sommet>();
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
		if (pere != null)
			this.pere = pere;
	}

	public void setEtat(Etat etat) {
		this.etat = etat;
	}

	public void cleanProperties() {
		pere = null;
		etat = Etat.Non_Atteint;
		distance = -1;
	}

	public void ajouterVoisins(Sommet s) {
		voisins.add(s);
	}

	public List<Sommet> getVoisins() {
		return voisins;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	@Override
	public String toString() {
		String s=num_sommet+" [";
		for (Sommet sommet : voisins) {
			s+= sommet.getNum_sommet()+", ";
		}
		s+="]";
		return s;
		
	}

}
