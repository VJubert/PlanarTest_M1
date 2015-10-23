package mainpackage;

import java.util.List;

public class Face {
	
	private List<Integer> sommets;

	public Face(List<Integer> sommets) {
		super();
		this.sommets = sommets;
	}

	public List<Integer> getSommets() {
		return sommets;
	}

	@Override
	public String toString() {
		String s="";
		for (Integer integer : sommets) {
			s+=integer+" ";
		}
		return s;
	}
	
	

}
