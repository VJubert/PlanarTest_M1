package mainpackage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class Fragment extends Graphe {

	private List<Face> face;
	
	public Fragment(int n, Map<Integer, Sommet> g) {
		super(n, g);
	}

	public Fragment(int n) {
		super(n);
	}

	private List<Sommet> trouverChemin(Sommet x, Sommet y) {
		parcours_largeur(x.getNum_sommet(),new Predicate<Sommet>() {
			
			@Override
			public boolean test(Sommet arg0) {
				return true;
			}
		});
		Sommet dep = y;
		List<Sommet> res = new ArrayList<Sommet>();
		while (dep.equals(x)) {
			res.add(dep);
			dep = dep.getPere();
		}
		res.add(x);
		return res;
	}

	public Face plonger(Graphe h) {
		Face f=face.get(0);
		ArrayList<Sommet> s=new ArrayList<Sommet>();
		for (Sommet sommet : f.getSommets()) {
			if(h.have_sommet(sommet))
				s.add(sommet);
		}
		List<Sommet> chemin=trouverChemin(s.get(0), s.get(1));
		h.ajouterchemin(chemin);
		Face f2=f.maj(chemin);
		return f2;		
	}

	public List<Face> getFace() {
		return face;
	}

	public int calcul_face_admissible(List<Face> list_face) {
		face.clear();
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