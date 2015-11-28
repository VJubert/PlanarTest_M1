package mainpackage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	private static Graphe g;
	private static Graphe h;
	private static List<Fragment> list_frag = new ArrayList<Fragment>();
	private static List<Face> list_face = new ArrayList<Face>();

	public static void main(String[] args) {
		file_to_graph(args[0]);
		if (!g.calculCycle(g.getPremierSommet(), h)) {
			System.out.println("true");
			System.out.println("Pas de cycle => Arbre => Toujours planaire");
			return;
		}
		h = g.createH();
		init_face();
		boolean une_seul_face = false;
		int i = 0;
		while (g.has_frag(h)) {
			i++;
			for (int j = 0; j < 50; j++) {
				System.out.print(i);
			}
			System.out.println();
			System.out.println(h);
			System.out.println(list_face);
			calcul_frag();
//			System.out.println(list_frag);
			une_seul_face = false;
			for (Fragment frag : list_frag) {
				if (!une_seul_face) {
					// ici ça bug
					int nb_face_admissible = frag.calcul_face_admissible(list_face);
					switch (nb_face_admissible) {
					case 0:
						System.out.println(false);
						return;
					case 1:
						list_face.add(frag.plonger(h));
						une_seul_face = true;
						break;
					}
				} else {
					break;
				}
			}
			if (!une_seul_face) {
				list_face.add(list_frag.get(0).plonger(h));
			}
		}
		System.out.println("true");
		afficherCartePlanaire();
	}

	private static void init_face() {
		list_face.clear();
		list_face.add(new Face(h));
		list_face.add(new Face(h));
	}

	private static void afficherCartePlanaire() {
		list_face.forEach(System.out::println);
	}

	private static void calcul_frag() {
		list_frag.clear();
		Graphe inter = g.diff(h);
		List<List<Sommet>> comp_con = inter.calcul_comp_connexe();

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
		// ajouter les arêtes solo
		List<Sommet> list;
		for (Sommet som : g.sommets.values()) {
			if (h.have_sommet(som)) {
				for (Sommet voi : som.getVoisins()) {
					if (h.have_sommet(voi)) {
						if (!h.have_edge(som, voi)) {
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

		// création des fragments
		comp_con.forEach(x -> list_frag.add(new Fragment(x)));

		// définition des sommets de contact pour chaque fragment
		list_frag.forEach(x -> x.def_contact(h));
	}

	private static void file_to_graph(String fileName) {
		ArrayList<Integer> tableauVoisins;
		Scanner fileScanner, lineScanner;
		String ligne, voisins;
		int nbSommets, numLigne, numSommet, numVoisin;
		numLigne = 1;
		try {
			fileScanner = new Scanner(new File(fileName));
			while (fileScanner.hasNextLine()) {
				ligne = fileScanner.nextLine();
				if (numLigne == 1) {
					nbSommets = Integer.parseInt(ligne);
					g = new Graphe(nbSommets);
					h = new Graphe(nbSommets);
				} else {
					// Récupération du numéro du sommet.
					lineScanner = new Scanner(ligne);
					lineScanner.useDelimiter(":");
					numSommet = Integer.parseInt(lineScanner.next());
					// Récupération de l'ensemble des voisins du sommet.
					// L'ensemble récupéré est de la forme "1, 2, 3".
					voisins = ligne.substring(4 + (numLigne / 12), ligne.length() - 1);
					lineScanner = new Scanner(voisins);
					lineScanner.useDelimiter(", ");
					tableauVoisins = new ArrayList<Integer>();
					while (lineScanner.hasNext()) {
						numVoisin = Integer.parseInt(lineScanner.next());
						// On ajoute numVoisin dans tableauVoisins.
						tableauVoisins.add(Integer.valueOf(numVoisin));
					}
					int size = tableauVoisins.size();
					int[] tabVoisins = new int[size];
					for (int i = 0; i < size; i++)
						tabVoisins[i] = tableauVoisins.get(i);
					g.ajouterVoisins(numSommet, tabVoisins);
				}
				numLigne++;
			}
		} catch (IOException ioException) {
		}
	}
}