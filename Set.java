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
		set.clear();
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

	public boolean contains(T elem){
		return this.set.contains(elem);
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
	public boolean addElement(T elem){
		if (!(set.contains(elem))){
			set.add(elem);
			return set.add(elem);
		}
		return false;
	}

	/** Proofs the set condition (no element twice)
	 * @return true or false
	 */
	/*private boolean isSet(){			//evtl NullPointerHurensohn checken!
		
		if (set.isEmpty())
			return true;
		
		for (int i = 0; i < set.size(); i++){
			for (int j = i+1; j < set.size(); j++){
				if (this.set[i] == this.set[j])
					return false
			}
		}

		return true;
	}*/

	/** Sets the set p on which it is called onto the intersection of setA and setB
	 *   Attention: p will be cleared before getting set onto the intersection!
	 *   @param setA is of type Set<T>
	 *   @param setB is of type Set<T>
	 */
	public void intersect2(Set<T> setA, Set<T> setB){
		this.set.clear();
		for(int i = 0; i < setA.set.size(); i++){
			if (setB.set.contains(setA.set.get(i))){
				this.set.add(setA.set.get(i));
			}
		}
	}

	public void intersect(Set<T> setI){
		for(int i = 0; i < this.size(); i++){
			if ( !(setI.set.contains(this.getElement(i))) )
				this.set.remove(this.getElement(i));
		}
	}

	/** Sets the set p on which it is called onto the union of setA and setB
	 *   Attention: p will be cleared before getting set onto the union!
	 *   @param setA is of type Set<T>
	 *   @param setB is of type Set<T>
	 */
	public void union2(Set<T> setA, Set<T> setB){
		this.set.clear();
		this.set.addAll(setA.set);
		this.set.addAll(setB.set);
		
		Set<T> helper = new Set<T>();
		helper.intersect2(setA, setB);
		this.set.removeAll(helper.set);
	}

	public void union(Set<T> setU){
		for(int i = 0; i < setU.size(); i++){
			if ( !(this.set.contains(setU.getElement(i))) )
				this.set.add(setU.getElement(i));
		}
	}

}