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

}
