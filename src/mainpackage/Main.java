package mainpackage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	private static Graphe g;
	private static List<Fragment> list_frag=new ArrayList<Fragment>();
	private static List<Face> list_face=new ArrayList<Face>();

	public static void main(String[] args) {		
		file_to_graph(args[0]);
		if(!g.calculCycle(g,g.getPremierSommet())){
			System.out.println("true");
			System.out.println("Pas de cycle => Arbre => Toujours planaire");
		}
		init_face();
		boolean une_seul_face=false;
		while(has_frag(g)){
			calcul_frag(g);
			une_seul_face=false;
			for (Fragment frag : list_frag) {
				if(!une_seul_face){
					calcul_face_admissible(frag);
					if(!frag.hasFaceAdmissible()){
						System.out.println("false");
						System.exit(0);
					}
					else {
						if(frag.hasOneFace()){
							plonger(frag);
							une_seul_face=true;
						}
					}
				}
			}
			if(!une_seul_face){
				plonger(list_frag.get(0));
			}
		}
		System.out.println("true");
		afficherCartePlanaire();
	}

	private static void init_face() {
		list_face.clear();
		list_face.add(new Face(g.getCycle()));
		list_face.add(new Face(g.getCycle()));		
	}

	private static void afficherCartePlanaire() {
		// TODO Auto-generated method stub
		
	}

	private static void plonger(Fragment frag) {
		// TODO Auto-generated method stub
		
	}

	private static void calcul_face_admissible(Fragment frag) {
		// TODO Auto-generated method stub
		
	}

	private static void calcul_frag(Graphe g2) {
		// TODO Auto-generated method stub
		
	}

	private static boolean has_frag(Graphe g2) {
		// TODO Auto-generated method stub
		return false;
	}

	private static void calcul_face() {
		// TODO Auto-generated method stub
		
	}

	private static void file_to_graph(String fileName) {
		ArrayList<Integer> tableauVoisins;
		Scanner fileScanner, lineScanner;
		String ligne, voisins;
		int nbSommets, numLigne, numSommet, numVoisin;
		numLigne = 1;
		try {
			fileScanner = new Scanner(new File(fileName));
			while(fileScanner.hasNextLine()) {
				ligne = fileScanner.nextLine();
				if(numLigne == 1){
					nbSommets = Integer.parseInt(ligne);
					g=new Graphe(nbSommets);
				} else {
					// Récupération du numéro du sommet.
					lineScanner = new Scanner(ligne);
					lineScanner.useDelimiter(":");
					numSommet = Integer.parseInt(lineScanner.next());
					// Récupération de l'ensemble des voisins du sommet.
					// L'ensemble récupéré est de la forme "1, 2, 3".
					voisins = ligne.substring(4+(numLigne/12), ligne.length()-1);
					lineScanner = new Scanner(voisins);
					lineScanner.useDelimiter(", ");
					tableauVoisins = new ArrayList<Integer>();
					while(lineScanner.hasNext()) {
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
		} catch(IOException ioException) {}
	}

	private static void generation_graphe(){
		g=new Graphe(8);
		g.ajouterVoisins(0, 1,2,3,4);

		g.ajouterVoisins(1, 0,2,5,6);

		g.ajouterVoisins(2, 0,1,3,6);
		
		g.ajouterVoisins(3, 0,2,4,7);

		g.ajouterVoisins(4, 0,3,5,7);

		g.ajouterVoisins(5, 1,4,6,7);

		g.ajouterVoisins(6, 1,2,5,7);
		
		g.ajouterVoisins(7, 3,4,5,6);
	}
}
