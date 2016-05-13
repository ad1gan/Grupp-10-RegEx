
public class Test{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Auf gehts
		Tree a = new Tree("a");
		Tree b = new Tree("b");
		Tree c = new Tree(a,"c",b);
		System.out.println(a.getKey());
		
		System.out.println(c.getLeft().getKey());
		System.out.println(c.getRight().getKey());
		
		System.out.println(c.getSize());
	}

}
