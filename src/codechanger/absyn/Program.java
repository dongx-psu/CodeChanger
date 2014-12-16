package codechanger.absyn;

public class Program {
	public String label = "Decls";
	public Preprocessors pres;
	public Decls decls;
	
	public Program(Preprocessors ps, Decls ds) {
		pres = ps;
		decls = ds;
	}
	
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append(pres.toString());
		buf.append("#include \"trace_tool.h\"\n\n");
		buf.append(decls.toString());
		return buf.toString();
	}
}
