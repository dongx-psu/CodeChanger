package codechanger.absyn;

import codechanger.Main;
import codechanger.Util;

public class ForStmt extends IterStmt {
	public String label = "ForStmt";
	public Exprs expra, exprb, exprc;
	public Stmt stmt;
	
	public ForStmt(Exprs ea, Exprs eb, Exprs ec, Stmt s) {
		expra = ea;
		exprb = eb;
		exprc = ec;
		stmt = s;
		
		if (exprb.has_func_call && Util.checkPara(exprb.list.getLast())) {
			Util.insertShortCurcuitToNameSet(exprb.list.getLast());
		} else if (exprb.has_func_call) {
			Util.result_name_set.add(Util.makeVarName(exprb));
		}
	}
	
	//TODO Func Call Stuff
	public String toString() {
		StringBuffer buf = new StringBuffer();
		String tmpa = (expra == null) ? "" : expra.toString();
		String tmpb = (exprb == null) ? "" : exprb.toString();
		String tmpc = (exprc == null) ? "" : exprc.toString();
		String condName = exprb.toString();
		if (Util.selected_func && exprb.has_func_call) {
			//Notice only value of the last expression would be meaningful for the predicate
			buf.append(Util.generateIndention() + "for (" + tmpa + "; ; " + tmpc + ")" + " {\n");
			++Util.indent;
			if (Util.checkPara(exprb.list.getLast())) {
				condName = Util.handleShortCircuit(exprb.list.getLast(), buf);
			} else {
				condName = Util.makeVarName(exprb);
				buf.append(Util.generateIndention() + "TRACE_START();\n");
				buf.append(Util.generateIndention() + condName + " = " + exprb.toString() + ";\n");
				buf.append(Util.generateIndention() + "TRACE_END(" + (++Main.post_fix_cnt) + ");\n");
				Main.insertFuncCall(exprb);
				condName = "(" + condName + ")";
			}
			buf.append(Util.generateIndention() + "if (!" + condName + ") break;\n");
			if (stmt instanceof CpdStmt) {
				((CpdStmt)stmt).no_first_bracket = true;
				buf.append(stmt.toString());
			} else {
				buf.append(stmt.toString());
				--Util.indent;
				buf.append("}");
			}
		} else {
			buf.append(Util.generateIndention() + "for (" + tmpa + "; " + tmpb + "; " + tmpc + ")" + "\n");
			if (stmt instanceof CpdStmt) buf.append(stmt.toString());
			else {
				++Util.indent;
				buf.append(stmt.toString());
				--Util.indent;
			}
		}
		return buf.toString();
	}
}
