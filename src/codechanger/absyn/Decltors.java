package codechanger.absyn;

import java.util.LinkedList;

public class Decltors {
	public String label = "Decltors";
	public LinkedList<Decltor> list = new LinkedList<Decltor>();
	
	public Decltors(Decltor d) {
		list.addFirst(d);
	}
	
	public String toString() {
		if (list.size() == 0) return "";
		StringBuffer tmp = new StringBuffer();
		tmp.append(list.get(0).toString());
		for (int i = 1; i < list.size(); ++i)
			tmp.append(", " + list.get(i).toString());
		return tmp.toString();
	}
}
