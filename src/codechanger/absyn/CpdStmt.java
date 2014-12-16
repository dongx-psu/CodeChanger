package codechanger.absyn;

import java.util.Iterator;

import codechanger.Util;

public class CpdStmt extends Stmt {
	public boolean no_first_bracket = false;
	public String label = "CpdStmt";
	public Declars ds;
	public Stmts sts;
	public boolean flag = false;
	
	public CpdStmt(Declars d, Stmts s) {
		ds = d;
		sts = s;
	}
	
	public String toString() {
		StringBuffer buf = new StringBuffer();
		if (!no_first_bracket) {
			buf.append(Util.generateIndention() + "{\n");
			++Util.indent;
		}
		if (flag) {
			buf.append(Util.generateIndention() + "TRACE_FUNCTION_START();\n");
			//TODO type resolve
			if (Util.result_name_set.size() != 0) {
				buf.append(Util.generateIndention() + "bool ");
				Iterator<String> iter = Util.result_name_set.iterator();
				buf.append(iter.next());
				while (iter.hasNext())
					buf.append(", " + iter.next());
				buf.append(";\n");
			}
		}
		if (ds != null) buf.append(ds.toString());
		if (sts != null) buf.append(sts.toString());
		if (flag) if (!(sts.list.getLast() instanceof ReturnStmt)) buf.append(Util.generateIndention() + "TRACE_FUNCTION_END();\n");
		--Util.indent;
		buf.append(Util.generateIndention() + "}");
		return buf.toString();
	}
}
