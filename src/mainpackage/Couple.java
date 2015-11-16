package mainpackage;

public class Couple<K,V> {
	private K left;
	private V right;
	public K getLeft() {
		return left;
	}
	public V getRight() {
		return right;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		if(left.hashCode()>right.hashCode()){
		result = prime * result + ((left == null) ? 0 : left.hashCode());
		result = prime * result + ((right == null) ? 0 : right.hashCode());
		} else {
			result = prime * result + ((right == null) ? 0 : right.hashCode());	
			result = prime * result + ((left == null) ? 0 : left.hashCode());		
		}
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Couple))
			return false;
		Couple other = (Couple) obj;
		if(left ==null || right==null || other.left == null || other.right ==null)
			return false;
		return (left.equals(other.left) && right.equals(other.right)) || (left.equals(other.right) && right.equals(other.left));
		
	}
	public Couple(K left, V right) {
		super();
		this.left = left;
		this.right = right;
	}
	
	
	
	
}
