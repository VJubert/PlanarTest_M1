package mainpackage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Fragment extends Graphe {

	private List<Face> face;
	private List<Sommet> contact;

	public Fragment(Map<Integer, Sommet> g) {
		super(g.size(), g);
		face = new ArrayList<Face>();
		contact = new ArrayList<Sommet>();
	}

	public Fragment(List<Sommet> l) {
		super(l.size());
		l.forEach(x -> sommets.put(x.getNum_sommet(), x));
		face = new ArrayList<Face>();
		contact = new ArrayList<Sommet>();
	}

	public Fragment(int n) {
		super(n);
	}

	private List<Sommet> trouverChemin(Sommet x, Sommet y) {
		parcours_largeur(x.getNum_sommet());
		Sommet dep = sommets.get(y.getNum_sommet());
		List<Sommet> res = new ArrayList<Sommet>();
		while (!dep.equals(x)) {
			res.add(dep);
			dep = dep.getPere();
		}
		res.add(x);
		return res;
	}

	public Face plonger(Graphe h) {
		Face f = face.get(0);

		// trouver les sommets qui sont dans le cycle
		ArrayList<Sommet> s = new ArrayList<Sommet>();
		for (Sommet sommet : f.getSommets()) {
			if (h.have_sommet(sommet)) {
				s.add(sommet);
			}
		}

		List<Sommet> chemin = trouverChemin(s.get(0), s.get(1));
		h.ajouterchemin(chemin);
		Face f2 = f.maj(chemin);
		return f2;
	}

	public List<Face> getFace() {
		return face;
	}

	public int calcul_face_admissible(List<Face> list_face) {
		face.clear();
		for (Face f : list_face) {
			if (f.getSommets().containsAll(contact))
				face.add(f);
		}
		return face.size();
	}

	public void def_contact(Graphe h) {
		for (Sommet sommet : sommets.values()) {
			if (h.have_sommet(sommet))
				contact.add(sommet);
		}

	}

}