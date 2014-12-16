package codechanger.absyn;

import java.util.LinkedList;

public class Initers {
	public String label = "Initers";
	public LinkedList<Initer> list = new LinkedList<Initer>();
	
	public Initers(Initer inter) {
		list.addFirst(inter);
	}
	
	public String toString() {
		if (list.size() == 0) return "";
		StringBuffer buf = new StringBuffer();
		buf.append(list.get(0).toString());
		for (int i = 1; i < list.size(); ++i)
			buf.append(", " + list.get(i).toString());
		return buf.toString();
	}
}
