
public class LinearProbing<AnyType> {

	private int maxSize;
	private AnyType[] array;
	private AnyType[] temp;
	private int count = 0;

	@SuppressWarnings("unchecked")
	public LinearProbing(int size) {
		maxSize = size;
		this.array = (AnyType[]) new Object[maxSize];

	}

	public int getSize(){
		return maxSize;
	}
	
	public AnyType[] getArray(){
		return array;
	}
 
	// To find item in hash Table
	public boolean contains(AnyType value) {

		return getValue(value);
	}
	
	private boolean getValue(AnyType value) {
		
		int i = getHashCode(value, maxSize);

		if (i < 0) {
			i += maxSize;
		}
		
		while (array[i] != null) {
			if (array[i] != null && array[i].equals(value))
				return true;
			else
				i = (++i) % maxSize;
	
		}
		return false;
	}
	// To insert value in hash table
	public boolean insert(AnyType value) 
	{
		if (count > maxSize / 2) 
		{
			maxSize *= 2;
			expandTable(array, maxSize);

		}

		boolean x = insertValue(value, array);
		count++;
		return x != false;
	}

	private boolean insertValue(AnyType value, AnyType[] array) 
	{

		int i = getHashCode(value, maxSize);

		if (i < 0) 
		{
			i += maxSize;

		}

		while (array[i] != null) 
		{
			if (array[i] == value)
				return false;
			else
				i = (++i) % maxSize;
		}

		array[i] = value;

	
		return true;

	}

	@SuppressWarnings("unchecked")
	private void expandTable(AnyType[] array, int size) {

		this.temp = (AnyType[]) new Object[size];
		for (AnyType e : array) {
			if (e != null) {
				insertValue(e, temp);
			}
		}
		this.array = this.temp;
	}
// To get hash code
	public int getHashCode(AnyType value, int size) {
		return value.hashCode() % size;
	}

	public static void main(String[] args) {
		LinearProbing<Integer> lp = new LinearProbing<Integer>(5);
		System.out.println(lp.insert(8));
		System.out.println(lp.insert(3));
		System.out.println(lp.insert(4));
		System.out.println(lp.insert(5));
		System.out.println(lp.insert(1));
		System.out.println(lp.contains(2));
		System.out.println(lp.contains(9));
		System.out.println(lp.contains(8));
		System.out.println(lp.contains(1));
		System.out.println(lp.contains(6));
	}

}