package mainpackage;

import java.util.Iterator;
import java.util.List;

public class Face {
	
	private List<Sommet> sommets;

	public Face(List<Sommet> sommets) {
		super();
		this.sommets = sommets;
	}

	public List<Sommet> getSommets() {
		return sommets;
	}

	@Override
	public String toString() {
		String s="";
		for (Sommet sommet : sommets) {
			s+=sommet.getNum_sommet()+" ";
		}
		return s;
	}
	
	

}
