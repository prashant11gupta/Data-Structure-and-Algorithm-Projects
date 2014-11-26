

public class MyFour<T> 
{
private T item1,item2,item3,item4;

	public MyFour(T a,T b,T c,T d)
	{
		item1 = a;
		item2 = b;
		item3 = c;
		item4= d;
	}
	public boolean allEqual()
	{
		if (item1.equals(item2))
		{
			if (item2.equals(item3))
			{
				if (item3.equals(item4))
					return true;
				else return false;
			}
			else return false;
		}
		else return false;
	}
	public void shiftLeft()
	{
		T x = item1;
		item1 = item2;
		item2 = item3;
		item3 = item4;
		item4 = x;
		
	}
	public String toString()
	{
		return "("+item1+","+item2+","+item3+","+item4+")";
		 
	}
	public static void main(String[] args)
	{
		MyFour<String> m1 = new MyFour<String>("abc","abc","abc","abc");
		System.out.println(m1.allEqual());
		MyFour<Integer> m2 = new MyFour<Integer>(1,2,3,4);
		System.out.println(m2.allEqual());
		m2.shiftLeft();
		System.out.println(m2);
		
	}

}
