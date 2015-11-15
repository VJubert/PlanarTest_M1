package mainpackage;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Graphe {

	private int nb_sommets;
	private Map<Integer, Sommet> sommets;
	private List<Sommet> cycle;
	private Map<Couple<Sommet,Sommet>,Boolean> arete;

	public List<Sommet> getCycle() {
		return cycle;
	}

	public Graphe(int n) {
		nb_sommets = n;
		sommets = new HashMap<Integer, Sommet>();
		arete=new HashMap<Couple<Sommet, Sommet>, Boolean>();
	}

	public Graphe(int n, Map<Integer, Sommet> g) {
		nb_sommets = n;
		sommets = g;
		arete=new HashMap<Couple<Sommet, Sommet>, Boolean>();
	}

	public int getNb_sommets() {
		return nb_sommets;
	}


	public Sommet getPremierSommet(){
		int firstKey = (Integer) sommets.keySet().toArray()[0];
		Sommet s = sommets.get(firstKey);
		return s;
	}
	@Override
	public String toString() {
		String s = "";
		for (Sommet so : sommets.values()) {
			s += so + System.getProperty("line.separator");
		}
		return s;
	}

	public String toStringCycle() {
		String s = "";

		for(Sommet u : getCycle()){
			s += u.getNum_sommet() +  " ";
		}
		return s+System.getProperty("line.separator");
	}


	public void parcours_largeur(int dep) {
		ArrayDeque<Sommet> q = new ArrayDeque<Sommet>();
		for (Sommet sommet : sommets.values()) {
			sommet.cleanProperties();
		}
		Sommet s_dep = sommets.get(dep);
		s_dep.setEtat(Etat.Traite);
		s_dep.setDistance(0);
		q.add(s_dep);
		Sommet peek;
		while (!q.isEmpty()) {
			peek = q.peek();
			for (Sommet sommet : peek.getVoisins()) {
				if (sommet.getEtat() == Etat.Non_Atteint) {
					sommet.setEtat(Etat.Atteint);
					sommet.setDistance(peek.getDistance() + 1);
					sommet.setPere(peek);
					q.add(sommet);
				}
			}
			q.poll();
			peek.setEtat(Etat.Traite);
		}
	}

	public void parcours_profondeur(int dep) {
		for (Sommet sommet : sommets.values()) {
			sommet.cleanProperties();
		}
		visiter(dep);
	}

	private void visiter(int u) {
		Sommet s = sommets.get(u);
		s.setEtat(Etat.Atteint);
		for (Sommet sommet : s.getVoisins()) {
			if (sommet.getEtat() == Etat.Non_Atteint) {
				sommet.setPere(s);
				visiter(s.getNum_sommet());
			}
		}
		s.setEtat(Etat.Traite);
	}

	public boolean calculCycle(Sommet u){
		cycle = new ArrayList<Sommet>();
		for (Sommet sommet : sommets.values()) {
			sommet.cleanProperties();
		}
		return calculCycleRec(u);
	}

	private boolean calculCycleRec(Sommet u) {
		u.setEtat(Etat.Atteint);
		cycle.add(u);
		List<Sommet> voisins = new ArrayList<Sommet>();
		voisins = u.getVoisins();
		if (voisins.contains(u.getPere())) {
			int indexOfPere = voisins.indexOf(u.getPere());
			voisins.remove(indexOfPere);// exclure le père du parcours
		}
		for (Sommet v : voisins) {

			if (v.getEtat() == Etat.Atteint) {
				v.setAppartenance_cycle(true);
				cycle.add(v);// a ce stade, le même sommet doit etre présent
				// deux fois dans la liste
				// je vais tronquer la liste cycle et y laisse uniquement ce
				// qu'il y a entre ces deux sommets

				int firstIndex = cycle.indexOf(v);
				int lastIndex = cycle.lastIndexOf(v);
				cycle = cycle.subList(firstIndex, lastIndex + 1);
				for(Sommet w : cycle){
					w.setAppartenance_cycle(true);
				}

				return true;
			}
			if (v.getEtat() == Etat.Non_Atteint) {
				v.setPere(u);
				return calculCycleRec(v);
			}

		}
		u.setEtat(Etat.Traite);
		cycle.clear();
		return false;
	}

	public void ajouterVoisins(int sommet, int... voisin) {
		Sommet s = sommets.get(sommet);
		if (s == null) {
			s = new Sommet(sommet);
			sommets.put(sommet, s);
		}
		Sommet s2;
		for (int i : voisin) {
			s2 = sommets.get(i);
			if (s2 == null) {
				s2 = new Sommet(i);
				sommets.put(i, s2);
			}
			s.ajouterVoisins(s2);
			arete.put(new Couple<Sommet,Sommet>(s,s2), false);
		}
	}

	public boolean has_frag() {
		for (boolean b : arete.values()) {
			if(!b)
				return true;
		}
		return false;
	}

	public void majmarquage() {
		int n=cycle.size();
		Sommet s,s2;
		for(int i=0;i<n-1;i++){
			s=cycle.get(i);
			s2=cycle.get(i+1);
			arete.put(new Couple<Sommet, Sommet>(s,s2), true);
		}
	}

}
