package codechanger.absyn;

import java.util.LinkedList;

public class Preprocessors {
	public String label = "Decls";
	public LinkedList<String> list = new LinkedList<String>();
	
	public Preprocessors(String s) {
		list.addFirst(s);
	}
	
	public String toString() {
		StringBuffer tmp = new StringBuffer();
		for (String s: list)
			tmp.append(s);
		
		return tmp.toString();
	}
}
