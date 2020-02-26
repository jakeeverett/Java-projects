package cs1302.lists;
/**
 * The ArrayGenList class implements the abstract methods in the GenList class.
 * @author Jacob Everett
 */
public class ArrayGenList<T> implements GenList<T>{

	private int size;//size == number of elem that arn't null in the array of Box's called boxy
	private Box<T>[] boxy;//array of box's called boxy

	/**
	 * This is a constuctor for the ArrayGenList class.
	 */
	public ArrayGenList() {
		size =0;
		boxy = Box.<T>array(10);
	}

	/**
	 * This method extends the boxy array's length by 10.
	 */
	public void extendArray() {
		int length = boxy.length;
		Box<T>[] holder = Box.<T>array(length+10);
		for (int i = 0;i<length;i++) {
			holder[i] = boxy[i];
		}
		boxy = holder;
	}
	
/**
 * Makes a copy of the boxy array that is one larger in length and returns it.
 * This method is ment to be called by the overloded add method,
 * so that it can inert a new element.
 * @return A copy of boxy array that is one longer in length.
 */
	public Box<T>[] copy(){
		int length = this.size();
		Box<T>[] holder = Box.<T>array(length+1);
		for (int i =0; i<length;i++) {
			holder[i] = boxy[i];
		}
		return holder;
	}
	
	/**
	 * Adds the specified element to the list at an implementation-specific
	 * position.
	 * @param elem element to be inserted
	 * @throws NullPointerException if elem is null
	 */
	public void add(T elem) throws NullPointerException {
		if (elem == null) {
			throw new NullPointerException();
		}
		this.boxy[size] = new Box<T>(elem);
		size++;
		if (size>=boxy.length) {//calls extendArray() if the array is out of space
			this.extendArray();
		}
	}

	

	/**
	 * Inserts the specified element at the specified position in this list. 
	 * Shifts the element currently at that position (if any) and any subsequent
	 * elements to the right (adds one to their indices).
	 * @param index index at which the specified element is to be inserted
	 * @param elem  element to be inserted
	 * @throws NullPointerException if elem is null
	 * @throws IndexOutOfBoundsException if the index is out of range 
	 *         {@code (index < 0 || index > size())}
	 */
	public void add(int index, T elem) throws NullPointerException, IndexOutOfBoundsException {
		if ((index < 0 || index > size())) {
			throw new IndexOutOfBoundsException();
		}
		if (elem == null) {
			throw new NullPointerException();
		}
		if(index==this.size()) {//if the index happens to be the last position
			this.add(elem);//this calls the nonoverloded add() method
		}
		else {
			Box<T>[] holder = this.copy();//call the copy method
      //so that we have an extra space in the array
			for(int i=0;i<this.size();i++) {
				if( i == index) { //when i =index fill holder[i] with elem to insert
					holder[i] = new Box<T>(elem);
					for (int j =i;j<this.size();j++) {
						holder[j+1] = boxy[j];//shift right by one and
            //fills the remeaning indicies with the original boxy aray values
					}
					boxy = holder;
					size++;
				}
			}
		}
	}


	/** 
	 * Removes all of the elements from this list. The list will be empty after
	 * this call returns.
	 */
	public void clear() {
		for (int i =0; i<boxy.length;i++) {
			boxy[i] = null;//makes all of boxy's elems null
		}
		size =0;//resets size to 0
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
		T elem = boxy[index].get();//uses the get() method from the box calss
    //to get the elem at boxy[index]
		return elem;
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
		if ((index < 0 || index > size())) {
			throw new IndexOutOfBoundsException();
		}
		if (elem == null) {
			throw new NullPointerException();
		}
		while (index>boxy.length) {//extends array if index is larger than the length of the array
			this.extendArray();
		}
		T holder = this.boxy[index].get();//gets the old elem being replaiced
		boxy[index].set(elem);
		return holder;//returning the replaced elem
	}
	
	/**
	 * Returns the number of elements in this list. If this list contains more 
	 * than <code>Integer.MAX_VALUE</code> elements, return 
	 * <code>Integer.MAX_VALUE</code>.
	 * @return the number of elements in this list
	 */
	public int size() {
		int counter=0;
		for (int i =0;i<boxy.length;i++) {
			if (boxy[i]!=null) {
				counter++;//counts the indices where the elem isnt null
			}
		}
		return counter;
	}
	
	/**
	 * Returns true if and only if this list contains no elements.
	 * @return <code>true</code> if this list contains no elements
	 */ 
	public boolean isEmpty() {
		boolean test = true;
		for (int i =0;i<boxy.length;i++) {
			if (boxy[i]!=null) {
				test = false;//returns true only if all elems are null
			}
		}
		return test;
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
			throw new NullPointerException();
		}
		boolean test = false;
		for(int i=0;i<boxy.length;i++) {
			if (boxy[i] != null) {//if the elem at boxy[i] is not null
				if (boxy[i].get().equals(elem)) {//testing elem by elem equality
					test = true;
				}
			}
		}
		return test;
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
			Box<T>[] holder = Box.<T>array(boxy.length-1);
			for (int j=0;j<this.size();j++) {
      //fills a holder array with all of the elems of boxy
      //but the first instance of the elem to be removed
				if (loc>j) {
					holder[j]= boxy[j];
				}
				else if (loc<j) {
					holder[j-1]= boxy[j];
				}
			}
			boxy = holder;
			this.size--;//decrements size
			test = true;
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
			int loc[] = new int[boxy.length];
			int counter =0;
			for(int i=0;i<boxy.length;i++) {
				if (boxy[i] != null) {//makes sure elem isnt null
					if (boxy[i].get().equals(elem)) {
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
	

}
