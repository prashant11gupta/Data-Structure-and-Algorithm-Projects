public class MyStack {

	private MyLinkedList<Character> ml;

	public MyStack() {
		ml = new MyLinkedList<Character>();
	}

	private void push(char c) {
		ml.add(0, c);
	}

	private char pop() {
		return ml.remove(0);
	}

	public boolean checkExpression(String str) throws Exception {
		for (int i = 0; i < str.length(); i++) {
			Character c = str.charAt(i);
			if ("{[(".contains(c.toString())) {
				// push opening braces
				push(c);
			} else if ("}])".contains(c.toString())) {
				// closing braces
				if (ml.isEmpty()) {
					throw new Exception("brace does not match");
				}

				if (c.equals('}') && '{' == pop()) {
					continue;
				} else if (c.equals(']') && '[' == pop()) {
					continue;
				} else if (c.equals(')') && '(' == pop()) {
					continue;
				} else {
					throw new Exception("brace does not match");
				}
			}
		}
		if (!ml.isEmpty()) {
			throw new Exception("brace does not match");

		}
		return true;
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		MyStack ms = new MyStack();
		boolean flag = ms.checkExpression("{Prashant}");
		 ms.checkExpression("P{[]}rashant");
		System.out.println(flag);
	}

}
