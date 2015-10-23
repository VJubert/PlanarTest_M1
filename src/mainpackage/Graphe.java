package mainpackage;

import java.util.List;
import java.util.Map;

public class Graphe {
	private Map<Integer, List<Integer>> graphe;
	private int nb_sommets;

	private int[][] prop_sommets;

	public Graphe(int n, Map<Integer, List<Integer>> g) {
		prop_sommets = new int[n][2];
		// 0 c'est le pere pour parcours
		// 1 c'est la distance au depart (pour parcours)
		if (g != null)
			graphe = g;
		nb_sommets = n;
	}

	public int getNb_sommets() {
		return nb_sommets;
	}

	public List<Integer> voisins(int s) {
		return graphe.get(s);
	}

	public static void parcours_largeur(int dep) {
		// TODO : implementer parcours largeur
	}

	public static void parcours_profondeur(int dep) {
		// TODO : implementer parcours profondeur
	}
}
