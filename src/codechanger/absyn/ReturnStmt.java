package codechanger.absyn;

import codechanger.Main;
import codechanger.Util;

public class ReturnStmt extends JmpStmt {
	public String label = "ReturnStmt";	
	public Exprs rtne;
	public boolean debug;
	
	public ReturnStmt(Exprs e, boolean db) {
		rtne = e;
		debug = db;
		if (rtne.has_func_call)
			Util.result_name_set.add(Util.makeVarName(rtne));
	}
	
	public String toString() {
		/*if (rtne.has_func_call)
			Util.post_fix_map.put(this, ++Util.post_fix_cnt);*/
		StringBuffer buf = new StringBuffer();
		String returnVarName = rtne.toString();
		if (Util.selected_func) {
			if (rtne.has_func_call) {
				returnVarName = Util.makeVarName(rtne);
				buf.append(Util.generateIndention() + "TRACE_START();\n");
				buf.append(Util.generateIndention() + returnVarName + " = " + rtne.toString() + ";\n");
				buf.append(Util.generateIndention() + "TRACE_END(" + (++Main.post_fix_cnt) + ");\n");
				Main.insertFuncCall(rtne);
			}
			buf.append(Util.generateIndention() + "TRACE_FUNCTION_END();\n");
		}
		if (!debug) {
			if (rtne == null) buf.append(Util.generateIndention() + "return;");
			buf.append(Util.generateIndention() + "return " + returnVarName +";");
		} else  {
			if (rtne == null) buf.append(Util.generateIndention() + "DEBUG_RETURN();");
			buf.append(Util.generateIndention() + "DEBUG_RETURN(" + returnVarName +");");
		}
		return buf.toString();
	}
}
