package cs1302.lists;
/**
* The ArrayGenList class implements the abstract methods in the GenList class.
* It sorts and stores elem's in an array of Box's called sortedboxy.
* @author Jacob Everett
*/
public class SortedArrayGenList<T extends Comparable<T>> implements GenList<T>{
	private int size;//size == number of elem that arnt null in sortedboxy
	private Box<T>[] sortedboxy;//array of box's called boxy

  /**
   * This is the constructor for the SortedArrayGenList class
   */
	public SortedArrayGenList() {
		size = 0;
		sortedboxy = Box.<T>array(10);
	}

  /**
   * This method extends the sortedboxy array's length by 10.
   */
	public void extendArray() {
		int length = sortedboxy.length;
		Box<T>[] holder = Box.<T>array(length+10);
		for (int i = 0;i<length;i++) {
			holder[i] = sortedboxy[i];
		}
		sortedboxy = holder;
	}
	
  /**
	 * Adds the specified element to the list in sorted order.
	 * @param elem element to be inserted
	 * @throws NullPointerException if elem is null
	 */
	public void add(T elem) throws NullPointerException {
		if (elem == null) {
			throw new NullPointerException("NULL");
		}
		while (size > sortedboxy.length) {
			this.extendArray();;
		}
		if (this.size()==0) {//elem is added to the first position in the array if array empty
			this.sortedboxy[0] = new Box<T>(elem);
		}
		else {
			this.incersion(elem);//method was to long so it calls the incersion(T elem) method
		}
	}
	
  /**
	 * This method is intended to be called by the add(T elem) method.
   * It is called when an elem needs to be added to the array
   * in at any position other than the first indice.
   * It then adds the specified element to the list in sorted order.
   * The elem is already check to ensure that it is not null before this method is called,
   * so it wont throw a null pointer exeption inside of this method.
	 * @param elem element to be inserted
	 */
	public void incersion(T elem) {
		int length = sortedboxy.length;//filing a holder here, called copy
		Box<T>[] holder = Box.<T>array(length+1);//increcesed length by one to make room for new elem
		for (int i =0; i<length;i++) {//asssigning all of sortedboxy to a holder array
			holder[i] = sortedboxy[i];
		}
		boolean largest = true;//for testing if the elem wil be placed at the end of array
		for(int i=0;i<this.size();i++) {
			if (sortedboxy[i].get().compareTo(elem)>=0) { //if the test elem is less or eq to
      //the elem at i in sortedboxy it is added to the ith index
      //in holder and then holder is shifted right by one
				holder[i] = new Box<T>(elem);
				for (int j =i;j<this.size();j++) {
					holder[j+1] = sortedboxy[j];
				}
				sortedboxy = holder;
				size++;
				largest = false;//sets largest to false if elem has been placed
				break;
			}
		}
		if (largest == true) {//if the elem is the largest it is added to the end of the array here
			this.sortedboxy[this.size()] = new Box<T>(elem);
			size++;
		}
	}
	
	
	/**
	 * This method is the overloaded version of the add(T elem) method.
   * The index position is ignored other that to check for a IndexOutOfBoundsException.
   * Other than testing exeptions it calls the add(T elem) method since the elems will
   * only be added in sorted order.
	 * @param index index at which the specified element is to be inserted
	 * @param elem  element to be inserted
	 * @throws NullPointerException if elem is null
	 * @throws IndexOutOfBoundsException if the index is out of range 
	 *         {@code (index < 0 || index > size())}
	 */
	public void add(int index, T elem) throws NullPointerException, IndexOutOfBoundsException {
		if (elem == null) {
			throw new NullPointerException("NULL");
		}
		if ((index < 0 || index > size())) {
			throw new IndexOutOfBoundsException("TO_FAR");
		}
		this.add(elem);
	}
	
	/**
	 * Returns the number of elements in this list. If this list contains more 
	 * than <code>Integer.MAX_VALUE</code> elements, return 
	 * <code>Integer.MAX_VALUE</code>.
	 * @return the number of elements in this list
	 */
	public int size() {
		int counter=0;
		for (int i =0;i<sortedboxy.length;i++) {
			if (sortedboxy[i]!=null) {
				counter++;//counts the indices where the elem isnt null
			}
		}
		return counter;
	}
	
	/**
	 * Returns the element at the specified position in this list.
	 * @param index index of the element to return
	 * @return the element at the specified position in this list
	 * @throws IndexOutOfBoundsException if the index is out of range
	 *         {@code (index < 0 || index >= size())}
	 */
	public T get(int index) throws IndexOutOfBoundsException {
		if ((index < 0 || index > size())) {
			throw new IndexOutOfBoundsException("TOO FAR");
		}
		T elem = sortedboxy[index].get();//uses the get() method from the box calss
    //to get the elem at sortedboxy[index]
		return elem;
	}
	
	/**
	 * Returns <code>true</code> if this list contains the specified element. 
	 * More formally, returns <code>true</code> if and only if this list 
	 * contains at least one element <code>o</code> such that 
	 * <code>o.equals(elem)</code>.
	 * @param elem element whose presence in this list is to be tested
	 * @return <code>true</code> if this list contains the specified element
	 * @throws NullPointerException if elem is null
	 */
	public boolean contains(T elem) throws NullPointerException {
		if (elem == null) {
			throw new NullPointerException("NULL");
		}
		boolean test = false;
		for(int i=0;i<sortedboxy.length;i++) {
			if (sortedboxy[i] != null) {//if the elem at sortedboxy[i] is not null
				if (sortedboxy[i].get().equals(elem)) {//testing elem by elem equality
					test = true;
				}
			}
		}
		return test;
	}
	
	
	/** 
	 * Compares the specified list with this list for equality. Returns 
	 * <code>true</code> if and only if the specified object is also a 
	 * <code>GenList</code>, both lists have the same size, and all corresponding 
	 * pairs of elements in the two lists are equal.
	 * If <code>list</code> is a list of the same list type, then students
	 * may safely cast the object to the appropriat list type when implementing
	 * this method.
	 * @param list the object to be compared for equality with this list
	 * @return <code>true</code> if the specified object is equal to this list
	 */
	public boolean equals(Object list) {
		boolean test = true;
		if (list instanceof GenList) {//this is the typecast safety check
			GenList<T> t = (GenList) list;//typecasting
			if (t.size() == this.size()) {//testing if size is equal
				for (int i = 0;i<this.size();i++) {//elem by elem comparison
					if (this.get(i).equals(t.get(i))!=true) {
						test = false;
					}
				}
			}
			else {
				test = false;
			}
		}
		else {
			test = false;
		}
		return test;
	}
	
	
	/**
	 * Replaces the element at the specified position in this list with the 
	 * specified element
	 * @param index index of the element to replace
	 * @param elem element to be stored at the specified position
	 * @return the element previously at the specified position
	 * @throws NullPointerException if the specified element is null
	 * @throws IndexOutOfBoundsException if the index is out of range
	 *         {@code (index < 0 || index >= size())}
	 */
	public T set(int index, T elem) throws NullPointerException, IndexOutOfBoundsException {
		if (elem == null) {
			throw new NullPointerException("NULL");
		}
		if ((index < 0 || index > size())) {
			throw new IndexOutOfBoundsException("TO_FAR");
		}
		while (index>sortedboxy.length) {//extends array if index is larger than the length of the array
			this.extendArray();
		}
		T holder = this.sortedboxy[index].get();//gets the old elem being replaiced
		sortedboxy[index].set(elem);
		return holder;//returning the replaced elem
	}

	
	/**
	 * Returns true if and only if this list contains no elements.
	 * @return <code>true</code> if this list contains no elements
	 */ 
	public boolean isEmpty() {
		boolean test = true;
		for (int i =0;i<sortedboxy.length;i++) {
			if (sortedboxy[i]!=null) {
				test = false;//returns true iff all elems are null
			}
		}
		return test;
	}
	
	/**
	 * Returns the index of the first occurrence of the specified element in 
	 * this list, or -1 if this list does not contain the element.
	 * @param elem element to search for
	 * @return the index of the first occurrence of the specified element in 
	 *         this list, or -1 if this list does not contain the element
	 * @throws NullPointerException if elem is null
	 */
	public int indexOf(T elem) throws NullPointerException {
		if (elem == null) {
			throw new NullPointerException("NULL");
		}
		if (this.contains(elem)) {//checks if the elem is in the array
			int loc[] = new int[sortedboxy.length];
			int counter =0;//why do i have counter can prob remove also could probably make loc an int
			for(int i=0;i<sortedboxy.length;i++) {
				if (sortedboxy[i] != null) {//makes sure elem isnt null
					if (sortedboxy[i].get().equals(elem)) {
						loc[counter] = i;//if the elems are equal its location gets stored in the loc array
						counter++;
					}
				}
	
			}
			return loc[0];
		}
		else {
			return -1;
		}
	}
	
	/** 
	 * Removes the first occurrence of the specified element from this list, if 
	 * it is present. If this list does not contain the element, it is 
	 * unchanged. More formally, removes the element with the lowest index 
	 * <code>i</code> such that <code>elem.equals(get(i))</code> (if such an 
	 * element exists). Returns <code>true</code> if this list contained the 
	 * specified element (or equivalently, if this list changed as a result of 
	 * the call).
	 * @param elem element to be removed from this list, if present
	 * @return <code>true</code> if this list contained the specified element
	 * @throws NullPointerException if elem is null
	 */
	public boolean remove(T elem) throws NullPointerException {
		if (elem == null) {
			throw new NullPointerException("NULL");
		}
		boolean test = false;
		int loc = this.indexOf(elem);
		if (loc!=-1) {//tests if the elem to be removed is in the array
			Box<T>[] holder = Box.<T>array(sortedboxy.length-1);
			for (int j=0;j<this.size();j++) {
      //fills a holder array with all of the elems of sortedboxy
      //but the first instance of the elem to be removed
				if (loc>j) {
					holder[j]= sortedboxy[j];
				}
				else if (loc<j) {
					holder[j-1]= sortedboxy[j];
				}
			}
			sortedboxy = holder;
			this.size--;//decrements size
			test = true;
		}
		return test;
	}

	
	/** 
	 * Removes all of the elements from this list. The list will be empty after 
	 * this call returns.
	 */
	public void clear() {
		for (int i =0; i<sortedboxy.length;i++) {
			sortedboxy[i] = null;//makes all of boxy's elems null
		}
		size =0;//resets size to 0
	}
	
	
	
}

