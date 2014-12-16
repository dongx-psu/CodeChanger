package codechanger.absyn;

import java.util.LinkedList;

public class Para {
	public String label = "Para";
	public LinkedList<PlDecl> list = new LinkedList<PlDecl>();
	
	public Para(PlDecl pd) {
		list.addFirst(pd);
	}
	
	public String toString() {
		if (list.size() == 0) return "";
		StringBuffer buf = new StringBuffer();
		buf.append(list.get(0).toString());
		for (int i = 1; i < list.size(); ++i)
			buf.append(" ," + list.get(i).toString());
		return buf.toString();
	}
}
