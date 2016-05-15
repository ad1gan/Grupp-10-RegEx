/** Data Type for a Pair of two Datatypes
 * @param <A> Type of the first Element
 * @param <B> Type of the second Element
 * @author Lukas
 */
public class Pair<A,B>{
	private A a;
	private B b;
	/** Initializes the Pair
	 * @param a The value of the first  variable
	 * @param b The value of the second variable
	 */
	public Pair(A a, B b) {
		this.a = a;
		this.b = b;
	}
	/** Returns the first Element
	 * @return The first Element
	 */
	public A first(){
		return a;
	}
	/** Returns the second Element
	 * @return The second Element
	 */
	public B second(){
		return b;
	}

	public void setFirst(A anew){
		this.a = anew;
	}

	public void setSecond(B bnew){
		this.b = bnew;
	}
}