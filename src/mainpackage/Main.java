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
		try {
			file_to_graph(args[0]);
		} catch (NumberFormatException e) {
			System.err.println("Entrée incorrect");
			return;
		} catch (IOException e) {
			System.err.println("Entrée inconnu");
			return;
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("Besoin d'un graphe en entrée");
		}
		if (!g.calculCycle(g.getPremierSommet(), h)) {
			System.out.println("true");
			System.out.println("Pas de cycle => Arbre => Toujours planaire");
			return;
		}
		h = g.createH();
		init_face();
		boolean une_seul_face = false;
		//O((n+m)3)
		while (g.has_frag(h)) {
			//O(n+m)
			calcul_frag();
			une_seul_face = false;
			//O((n+m)²)
			for (Fragment frag : list_frag) {
				if (!une_seul_face) {
					//O(n+m)
					int nb_face_admissible = frag.calcul_face_admissible(list_face);
					switch (nb_face_admissible) {
					case 0:
						System.out.println(false);
						return;
					case 1:
						//O(n+m)
						list_face.add(frag.plonger(h));
						une_seul_face = true;
						break;
					}
				} else {
					break;
				}
			}
			if (!une_seul_face) {
				//O(n+m)
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
	//O(n+m)
	private static void calcul_frag() {
		list_frag.clear();
		//O(n+m)
		Graphe inter = g.diff(h);
		//O(n+m)
		List<List<Sommet>> comp_con = inter.calcul_comp_connexe();

		//O(m)
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
		//O(m)
		// ajouter les arêtes solo
		List<Sommet> list;
		for (Sommet som : g.sommets.values()) {
			if (h.have_sommet(som)) {
				for (Sommet voi : som.getVoisins()) {
					if (h.have_sommet(voi)) {
						if (!h.have_edge(som, voi)) {
							//O(1) (un seul voisin)
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

	private static void file_to_graph(String fileName) throws IOException, NumberFormatException {
		ArrayList<Integer> tableauVoisins;
		Scanner fileScanner, lineScanner;
		String ligne, voisins;
		int nbSommets, numLigne, numSommet, numVoisin;
		numLigne = 1;
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
		fileScanner.close();
	}
}