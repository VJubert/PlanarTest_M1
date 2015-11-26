package mainpackage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Face {
	
	private List<Sommet> sommets;

	public Face(Graphe g) {
		super();
		sommets=g.getSommets();
	}
	public Face(List<Sommet> L){
		sommets=L;
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

	public Face maj(List<Sommet> chemin) {
		Sommet x=chemin.get(0);
		Sommet y=chemin.get(chemin.size()-1);
		Iterator<Sommet> it=sommets.iterator();
		Sommet a;
		boolean avant=true;
		List<Sommet> t=new ArrayList<Sommet>();
		List<Sommet> t2=new ArrayList<Sommet>();
		while(it.hasNext()){
			a=it.next();
			if(a.equals(x)){
				avant=false;
				t.addAll(chemin);
				if(a.equals(x)){
					avant=false;
					t.addAll(chemin);
					continue;					
				}
				if(a.equals(y)){
					avant=true;
					t2.addAll(chemin);
					continue;
				}
				if(avant)
					t.add(a);
				else
					t2.add(a);					
			}
		}
		sommets=t;
		return new Face(t2);
		
	}
	
	

}
