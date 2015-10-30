package mainpackage;

import java.util.ArrayList;
import java.util.List;

public class Main {
	
	private static Graphe g;
	private static List<Fragment> list_frag=new ArrayList<Fragment>();

	public static void main(String[] args) {		
		file_to_graph(args[0]);
		if(!g.calcul_cycle()){
			System.out.println("true");
			System.out.println("Pas de cycle => Arbre => Toujours planaire");
		}
		calcul_face();
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

	private static void file_to_graph(String string) {
		// TODO Auto-generated method stub
		
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
