

public class Square
{
	private  int length;
	
	public Square(int len)
	{
	length = len;	
	}
	
	public Square()
	{
		length = 1;
	}
	
	public int getArea()
	{
		return length * length;
	}	
}
