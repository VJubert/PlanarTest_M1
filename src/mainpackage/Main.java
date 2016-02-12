package mainpackage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

	private static Graphe g;
	private static Graphe h;
	private static List<Fragment> list_frag = new ArrayList<Fragment>();
	private static List<Face> list_face = new ArrayList<Face>();

	public static void main(String[] args) {
		String file=null;
		try {
			file=args[0];
		} catch (ArrayIndexOutOfBoundsException e){
			System.err.println("Besoin d'un graphe en entrée ");
			return;
		}
		try {
			file_to_graph(args[0]);
		} catch (NumberFormatException e) {
			System.err.println("Entrée incorrect");
			e.printStackTrace();
			return;
		} catch (IOException e) {
			System.err.println("Entrée inconnu");
			return;
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("Convention des fichiers .graphe modifié !");
			return;
		}
		if (!g.calculCycle(g.getPremierSommet(), h)) {
			System.out.println("true");
			System.out.println("Pas de cycle => Arbre => Toujours planaire");
			return;
		}
		h = g.createH();
		init_face();
		boolean une_seul_face = false;
		// O((n+m)3)
		while (g.has_frag(h)) {
			// O(n+m)
			calcul_frag();
			une_seul_face = false;
			// O((n+m)²)
			for (Fragment frag : list_frag) {
				if (!une_seul_face) {
					// O(n+m)
					int nb_face_admissible = frag.calcul_face_admissible(list_face);
					switch (nb_face_admissible) {
					case 0:
						System.out.println(false);
						return;
					case 1:
						// O(n+m)
						list_face.add(frag.plonger(h));
						une_seul_face = true;
						break;
					}
				} else {
					break;
				}
			}
			if (!une_seul_face) {
				// O(n+m)
				list_face.add(list_frag.get(0).plonger(h));
			}
		}
		System.out.println("true");
		System.out.println("Face : " + list_face);
	}

	private static void init_face() {
		list_face.clear();
		list_face.add(new Face(h));
		list_face.add(new Face(h));
	}

	// O(n+m)
	private static void calcul_frag() {
		list_frag.clear();
		// O(n+m)
		Graphe inter = g.diff(h);
		// O(n+m)
		List<List<Sommet>> comp_con = inter.calcul_comp_connexe();

		// O(m)
		// on rétablit la non orientation des fragments
		for (List<Sommet> list : comp_con) {
			for (Sommet sommet : list) {
				for (Sommet voisin : sommet.getVoisins()) {
					if (h.have_sommet(voisin)) {
						voisin.ajouterVoisins(sommet);
					}
				}
			}
		}
		// O(m)
		// ajouter les arêtes solo
		List<Sommet> list;
		for (Sommet som : g.sommets.values()) {
			if (h.have_sommet(som)) {
				for (Sommet voi : som.getVoisins()) {
					if (h.have_sommet(voi)) {
						if (!h.have_edge(som, voi)) {
							// O(1) (un seul voisin)
							inter.ajouterVoisins(som.getNum_sommet(), voi.getNum_sommet());
							list = new ArrayList<Sommet>();
							list.add(som);
							list.add(voi);
							comp_con.add(list);
						}
					}
				}
			}
		}
		// O(m)
		// création des fragments
		comp_con.forEach(x -> list_frag.add(new Fragment(x)));
		// O(m)
		// définition des sommets de contact pour chaque fragment
		list_frag.forEach(x -> x.def_contact(h));
	}

	private static void file_to_graph(String fileName) throws ArrayIndexOutOfBoundsException, IOException, NumberFormatException{
		long n=0;
		g=new Graphe();
		try (Stream<String> s = Files.lines(Paths.get(fileName))) {
			n=s.map(String::trim).map(x -> x.split(":"))
					.map(x -> g.ajouterVoisins(Integer.parseInt(x[0]), parserVoisins(x[1]))).count();
		}
		g.setNb_sommets((int)n);
	}

	private static int[] parserVoisins(String s) throws NumberFormatException{
		s=s.replaceAll("\\[", "");
		s=s.replaceAll("\\]", "");
		s=s.replaceAll(" ", "");
		String[] s2 = s.split(",");
		int[] n = new int[s2.length];
		for (int i = 0; i < n.length; i++) {
			n[i] = Integer.parseInt(s2[i]);
		}
		return n;
	}
}