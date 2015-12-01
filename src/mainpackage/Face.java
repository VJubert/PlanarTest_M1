package mainpackage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Face {

	private List<Sommet> sommets;

	public Face(Graphe g) {
		super();
		sommets = g.getSommets();
	}

	public Face(List<Sommet> L) {
		sommets = L;
	}

	public List<Sommet> getSommets() {
		return sommets;
	}

	@Override
	public String toString() {
		String s = "";
		for (Sommet sommet : sommets) {
			s += sommet.getNum_sommet() + " ";
		}
		return s;
	}

	public Face maj(List<Sommet> chemin) {
		Sommet x = chemin.get(0);
		Sommet y = chemin.get(chemin.size() - 1);
		//Si besoin on inverse le chemin
		for (Sommet sommet : sommets) {
			if (sommet.equals(x))
				break;
			if (sommet.equals(y)) {
				x = chemin.get(chemin.size() - 1);
				y = chemin.get(0);
				chemin = reverseList(chemin);
				break;
			}
		}

		Iterator<Sommet> it = sommets.iterator();
		Sommet a;
		boolean avant = true;
		List<Sommet> t1 = new ArrayList<Sommet>();
		List<Sommet> t2 = new ArrayList<Sommet>();

		while (it.hasNext()) {
			a = it.next();

			if (a.equals(x)) {
				avant = false;
				t1.addAll(chemin);
			}

			if (avant)
				t1.add(a);
			else
				t2.add(a);

			if (a.equals(y))
				avant = true;
		}
		//on ajoute le chemin dans la seconde face
		chemin = reverseList(chemin);
		for (Sommet sommet : chemin) {
			if (!(sommet.equals(x) || sommet.equals(y))) {
				t2.add(sommet);
			}
		}
		t1 = removeDouble(t1);
		t2 = removeDouble(t2);

		sommets = t1;
		return new Face(t2);

	}

	public static <E> List<E> removeDouble(List<E> l) {
		List<E> res = new ArrayList<>();
		for (E object : l) {
			if (!res.contains(object))
				res.add(object);
		}
		return res;
	}

	public static <E> List<E> reverseList(List<E> l) {
		List<E> res = new ArrayList<>();
		int size = l.size();
		for (int i = size - 1; i >= 0; i--) {
			res.add(l.get(i));
		}
		return res;
	}

}
