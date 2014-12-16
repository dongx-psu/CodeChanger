package codechanger.absyn;

import codechanger.Main;
import codechanger.Util;

public class ExprStmt extends Stmt {
	public String label = "ExprStmt";
	public Exprs expr;
	
	public ExprStmt(Exprs e) {
		expr = e;
		//if (expr.has_func_call)
		//	Util.result_name_set.add(Util.makeVarName(expr));;
	}
	
	public String toString() {
		/*if (expr.has_func_call) {
			Util.post_fix_map.put(this, ++Util.post_fix_cnt);
		}*/
		StringBuffer buf = new StringBuffer();
		if (Util.selected_func && expr.has_func_call)
			buf.append(Util.generateIndention() + "TRACE_START();\n");
		if (expr == null) buf.append(Util.generateIndention() + ";");
		else buf.append(Util.generateIndention() + expr.toString() + ";");
		if (Util.selected_func && expr.has_func_call) {
			buf.append("\n" + Util.generateIndention() + "TRACE_END(" + (++Main.post_fix_cnt) + ");");
			Main.insertFuncCall(expr);
		}
		return buf.toString();
	}
}
