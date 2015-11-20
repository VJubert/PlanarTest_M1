package mainpackage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Fragment extends Graphe {

	private List<Face> face;

	public Fragment(int n, Map<Integer, Sommet> g) {
		super(n, g);
	}

	public Fragment(int n) {
		super(n);
	}

	private List<Sommet> trouverChemin(Sommet x, Sommet y) {
		parcours_largeur(x.getNum_sommet());
		Sommet dep = y;
		List<Sommet> res = new ArrayList<Sommet>();
		while (dep.equals(x)) {
			res.add(dep);
			dep = dep.getPere();
		}
		res.add(x);
		return res;
	}

	public void plonger(Face f) {

	}

	public List<Face> getFace() {
		return face;
	}

	public int calcul_face_admissible(List<Face> list_face) {
		Sommet s = sommets.get(0);
		Sommet s2 = sommets.get(sommets.size() - 1);
		ArrayList<Sommet> a = new ArrayList<Sommet>();
		a.add(s);
		a.add(s2);
		for (Face f : list_face) {
			if (f.getSommets().containsAll(a)) {
				face.add(f);
			}
		}
		return face.size();
	}

}