package mainpackage;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graphe {
	private static final int NBPROP=3;
	
	private static final int PERE=0;
	private static final int DISTANCE=1;
	private static final int ETAT=2;

	private static final int NONATTEINT=0;
	private static final int ATTEINT=1;
	private static final int TRAITE=2;
	
	private Map<Integer, List<Integer>> graphe;
	private int nb_sommets;
	private List<Integer> cycle;
	private int[][] prop_sommets;

	public Graphe(int n) {
		graphe = new HashMap<Integer, List<Integer>>();
		prop_sommets=new int[n][NBPROP];
		nb_sommets=n;
	}

	public Graphe(int n, Map<Integer, List<Integer>> g) {
		prop_sommets = new int[n][NBPROP];
		if (g != null)
			graphe = g;
		nb_sommets = n;
	}

	public int getNb_sommets() {
		return nb_sommets;
	}

	public List<Integer> getCycle() {
		return cycle;
	}

	public void setCycle(List<Integer> cycle) {
		if (this.cycle == null)
			this.cycle = cycle;
	}

	public List<Integer> voisins(int s) {
		return graphe.get(s);
	}

	@Override
	public String toString() {
		String s="";
		for (int i=0;i<nb_sommets;i++){
			s+=i+" : [ ";
			for (Integer integer : graphe.get(i)) {
				s+=integer+", ";
			}
			s+="]\r\n";
		}
		return s;
	}
	
	public String toStringCycle(){
		String s="";
		for (Integer integer : cycle) {
			s+=integer+", ";
		}
		return s;
	}
	public static void parcours_largeur(Graphe g, int dep) {
		ArrayDeque<Integer> q=new ArrayDeque<Integer>();
		for (int i = 0; i < g.getNb_sommets(); i++) {
			g.prop_sommets[i][PERE]=-1;
			g.prop_sommets[i][ETAT]=NONATTEINT;
		}
		g.prop_sommets[dep][ETAT]=TRAITE;
		g.prop_sommets[dep][DISTANCE]=0;
		q.add(dep);
		int u;
		while(!q.isEmpty()){
			u=q.peek();
			for (Integer v : g.voisins(u)) {
				if(g.prop_sommets[v][ETAT]==NONATTEINT){
					g.prop_sommets[v][ETAT]=ATTEINT;
					g.prop_sommets[v][DISTANCE]=g.prop_sommets[u][1]+1;
					g.prop_sommets[v][PERE]=u;
					q.add(v);
				}
			}
			q.poll();
			g.prop_sommets[u][ETAT]=TRAITE;
		}
	}
	public static void parcours_profondeur(Graphe g, int dep) {
		for (int i = 0; i < g.nb_sommets; i++) {
			g.prop_sommets[i][ETAT]=NONATTEINT;
			g.prop_sommets[i][PERE]=-1;
		}
		visiter(g,dep);
	}

	private static void visiter(Graphe g, int u) {
		g.prop_sommets[u][ETAT]=ATTEINT;
		for (Integer v : g.voisins(u)) {
			if(g.prop_sommets[v][ETAT]==NONATTEINT) {
				g.prop_sommets[v][PERE]=u;
				visiter(g,v);
			}
		}
		g.prop_sommets[u][ETAT]=TRAITE;		
	}

	public boolean calcul_cycle() {
		return false;
		// TODO Auto-generated method stub
		
	}
	public void ajouterVoisins(int sommets, int... voisin){
		List<Integer> voisins=graphe.get(sommets);
		if(voisins==null){
			voisins=new ArrayList<Integer>();
			graphe.put(sommets, voisins);
		}
		for (int i : voisin) {
			voisins.add(i);
		}
	}
	
}
