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

	public List<Sommet> getCycle() {
		return cycle;
	}

	public Graphe(int n) {
		nb_sommets = n;
		sommets = new HashMap<Integer, Sommet>();
	}

	public Graphe(int n, Map<Integer, Sommet> g) {
		nb_sommets = n;
		sommets = g;
	}

	public int getNb_sommets() {
		return nb_sommets;
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
		for (Sommet so : sommets.values()) {
			if (so.isAppartenance_cycle())
				s += so + System.getProperty("line.separator");
		}
		return s;
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

	//TODO : NE PAS PRENDRE DE GRAPHE DANS LES PARAMETRES !!!!!!!!!!!!!!
	public boolean calculCycle(Graphe g, int u) {
//		// /!\ la méthode ne fonctionne pas si le premier sommet n'est pas 0 !
//		// Je pense que ce n'est pas la seule à ne pas fonctionner dans ce cas
//
//		g.prop_sommets[u][ETAT] = ATTEINT;
//		cycle.add(u);
//		List<Integer> voisins = new ArrayList<Integer>();
//		voisins = g.voisins(u);
//		if (voisins.contains(g.prop_sommets[u][PERE])) {
//			int indexOfPere = voisins.indexOf(g.prop_sommets[u][PERE]);
//			voisins.remove(indexOfPere);// exclure le père du parcours
//		}
//		for (Integer v : voisins) {
//
//			if (g.prop_sommets[v][ETAT] == ATTEINT) {
//				cycle.add(v);// a ce stade, le même sommet doit etre présent
//								// deux fois dans la liste
//				// je vais tronquer la liste cycle et y laisse uniquement ce
//				// qu'il y a entre ces deux sommets
//				int firstIndex = cycle.indexOf(v);
//				int lastIndex = cycle.lastIndexOf(v);
//				cycle = cycle.subList(firstIndex, lastIndex + 1);
//
//				return true;
//			}
//			if (g.prop_sommets[v][ETAT] == NONATTEINT) {
//				g.prop_sommets[v][PERE] = u;
//				return calculCycle(g, v);
//			}
//
//		}
//		g.prop_sommets[u][ETAT] = TRAITE;
//		cycle.clear();
//		return false;
		return true;
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
		}
	}

}
