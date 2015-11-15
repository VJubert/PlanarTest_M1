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
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Couple other = (Couple) obj;
		if (left == null) {
			if (other.left != null)
				return false;
		} else if (!left.equals(other.left))
			return false;
		if (right == null) {
			if (other.right != null)
				return false;
		} else if (!right.equals(other.right))
			return false;
		return true;
	}
	public Couple(K left, V right) {
		super();
		this.left = left;
		this.right = right;
	}
	
	
	
	
}
