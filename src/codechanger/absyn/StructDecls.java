package codechanger.absyn;

import java.util.LinkedList;

public class StructDecls {
	public String label = "StructDecls";
	public class StructDecl {
		public Ty type;
		public Decltors decltors;
		
		public StructDecl(Ty t, Decltors d) {
			type = t;
			decltors = d;
		}
	}
	
	public LinkedList<StructDecl> list = new LinkedList<StructDecl>();
	
	public StructDecls(Ty t, Decltors d) {
		list.addFirst(new StructDecl(t, d));
	}
	
	public void adds(Ty t, Decltors d) {
		list.addFirst(new StructDecl(t, d));
	}
	
	public String toString() {
		StringBuffer buf = new StringBuffer();
		for (StructDecl s : list) {
			buf.append(s.type.toString() + " " + s.decltors.toString() + ";\n");
		}
		return buf.toString();
	}
}
