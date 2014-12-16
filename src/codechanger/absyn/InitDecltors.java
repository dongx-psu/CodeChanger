package codechanger.absyn;

import java.util.LinkedList;

public class InitDecltors {
	public String label = "InitDecltors";
	public LinkedList<InitDecltor> list = new LinkedList<InitDecltor>();
	
	public InitDecltors(InitDecltor idtor) {
		list.addFirst(idtor);
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
