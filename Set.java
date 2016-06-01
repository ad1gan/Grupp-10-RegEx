import java.util.ArrayList;

/** Data type for (ordered) set of elements of type T
 * @param <T> type of value being boxed
 * @see Set<T>
 * @author Frederick Wehr
 */
public class Set<T>{
	
	private ArrayList<T> set;

	/** Default/Null Constructor
	 */
	public Set(){
		set = new ArrayList<T>() ;
	}
	public Set(ArrayList<T> s){
		set = new ArrayList<T>();
		for(int i=0;i<s.size();i++)
			this.addElement(s.get(i));
	}
	public void copy(Set<T> s){
		set.clear();
		for(int i=0;i<s.size();i++)
			set.add(s.getSet().get(i));
	}

	public int size(){
		return this.set.size();
	}

	public T getElement(int i){
		if (i < this.size())
			return this.set.get(i);
		T failure = null;

		return failure;
	}
	/** Removes all elements from the list
	 */
	public boolean contains(T elem){
		return this.set.contains(elem);
	}
	/** Removes all elements from the list
	 */
	public void clear(){
		this.set.clear();
	}

	/** @return set which is of type T[]
	 */
	public ArrayList<T> getSet(){
		return this.set;
	}
	/** Adds element elem to set if it's not in there already
	 * @param elem The element that get's added to the set (or not)
	 * @return Boolean if the element could be added successfully
	 */
	public void addElement(T elem){
		if (!(this.set.contains(elem)))
			this.set.add(elem);
	}
}