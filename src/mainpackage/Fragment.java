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
		//O(n)
		l.forEach(x -> sommets.put(x.getNum_sommet(), x));
		face = new ArrayList<Face>();
		contact = new ArrayList<Sommet>();
	}

	public Fragment(int n) {
		super(n);
	}
	//O(n+m)
	private List<Sommet> trouverChemin(Sommet x, Sommet y) {
		//O(n+m)
		parcours_largeur(x.getNum_sommet());
		Sommet dep = sommets.get(y.getNum_sommet());
		List<Sommet> res = new ArrayList<Sommet>();
		//O(n)
		while (!dep.equals(x)) {
			res.add(dep);
			dep = dep.getPere();
			//si dans le chemin il y a un sommet de contact on coupe le chemin
			if (contact.contains(dep))
				break;
		}
		res.add(dep);
		return res;
	}
	//O(n+m)
	public Face plonger(Graphe h) {
		Face f = face.get(0);
		//O(n+m)
		List<Sommet> chemin = trouverChemin(contact.get(0), contact.get(1));
		//O(n)
		h.ajouterchemin(chemin);
		//O(n)
		Face f2 = f.maj(chemin);
		return f2;
	}

	public List<Face> getFace() {
		return face;
	}
	//O(n+m)
	public int calcul_face_admissible(List<Face> list_face) {
		face.clear();
		//O(n+m)
		for (Face f : list_face) {
			if (f.getSommets().containsAll(contact))
				face.add(f);
		}
		return face.size();
	}

	public void def_contact(Graphe h) {
		//O(n)
		for (Sommet sommet : sommets.values()) {
			if (h.have_sommet(sommet))
				contact.add(sommet);
		}

	}

}