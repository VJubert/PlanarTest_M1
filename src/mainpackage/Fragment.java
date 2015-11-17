package mainpackage;

import java.util.ArrayList;
import java.util.List;

public class Fragment {
	
	private List<Sommet> sommets;
	private List<Face> face;
	
	public Fragment(List<Sommet> sommets) {
		this.sommets = sommets;
		face=new ArrayList<Face>();
	}

	public List<Sommet> getSommets() {
		return sommets;
	}

	public List<Face> getFace() {
		return face;
	}

	public int calcul_face_admissible(List<Face> list_face) {
		Sommet s=sommets.get(0);
		Sommet s2=sommets.get(sommets.size()-1);
		ArrayList<Sommet> a=new ArrayList<Sommet>();
		a.add(s);
		a.add(s2);
		for (Face f : list_face) {
			if(f.getSommets().containsAll(a)){
				face.add(f);
			}
		}
		return face.size();
	}

}
