package codechanger;

import java.util.HashMap;
import java.util.TreeSet;

import codechanger.absyn.Expr;
import codechanger.absyn.Exprs;
import codechanger.absyn.Op;
import codechanger.absyn.Op.OpType;

public final class Util {
	public static int indent = 0;
	public static boolean mark = false;
	public static boolean selected_func = false;
	public static TreeSet<String> result_name_set = new TreeSet<String>();
	public static HashMap<String, Integer> func_call_posfix = new HashMap<String, Integer>(); 
	public static HashMap<String, Integer> func_call_sit = new HashMap<String, Integer>();
	
	public static String escape(char ch) {
		if (ch == '\n') return "\\n";
		else if (ch == '\r') return "\\r";
		else if (ch == '\t') return "\\t";
		else if (ch == '\'') return "\\'";
		else if (ch == '\\') return "\\\\";
		else return ""+ch;
	}
	
	public static String escape(String s) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < s.length(); ++i) {
			if (s.charAt(i) == '\n') buf.append("\\n");
			else if (s.charAt(i) == '\r') buf.append("\\r");
			else if (s.charAt(i) == '\t') buf.append("\\t");
			else if (s.charAt(i) == '"') buf.append("\\\"");
			else if (s.charAt(i) == '\\') buf.append("\\\\");
			else buf.append(s.charAt(i));
		}
		return buf.toString();
	}

	public static String generateIndention() {
		if (mark) {
			mark = false;
			return " ";
		}
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < indent; ++i)
			buf.append("\t");
		return buf.toString();
	}

	public static String makeVarName(Expr expr) {
		StringBuffer buf = new StringBuffer();
		buf.append("result");
		for (String s: expr.func_names) {
			buf.append(s);
		}
		return buf.toString();
	}
	
	public static String makeFuncCall(Expr expr) {
		StringBuffer buf = new StringBuffer();
		for (String s: expr.func_names) {
			buf.append(s);
		}
		return buf.toString();
	}

	public static String handleShortCircuit(Expr expr, StringBuffer buf) {
		//assert(expr.opType == OpType.PARAAND || expr.opType == OpType.PARAAND);
		OpType ot;
		Expr left, right;
		ot = ((Op)expr).opType;
		if (((Op)expr).left instanceof Exprs) left = ((Exprs)((Op)expr).left).list.getLast();
		else left = ((Op)expr).left;
		if (((Op)expr).right instanceof Exprs) right = ((Exprs)((Op)expr).right).list.getLast();
		else right = ((Op)expr).right;
		
		String leftName;
		if (checkPara(left) && left.has_func_call) {
			leftName = handleShortCircuit(left, buf);
		} else if (left.has_func_call) {
			leftName = makeVarName(left);
			buf.append(Util.generateIndention() + "TRACE_START();\n");
			buf.append(Util.generateIndention() + leftName + " = " + left.toString() + ";\n");
			buf.append(Util.generateIndention() + "TRACE_END(" + (++Main.post_fix_cnt) + ");\n");
			Main.insertFuncCall(left);
		} else leftName = left.toString();
		if (ot == OpType.PARAAND) {
			buf.append(Util.generateIndention() + "if (" + leftName + ") {\n");
			++Util.indent;
			String rightName;
			if (checkPara(right) && right.has_func_call) {
				rightName = handleShortCircuit(right, buf);
			} else if (right.has_func_call) {
				rightName = makeVarName(right);
				buf.append(Util.generateIndention() + "TRACE_START();\n");
				buf.append(Util.generateIndention() + rightName + " = " + right.toString() + ";\n");
				buf.append(Util.generateIndention() + "TRACE_END(" + (++Main.post_fix_cnt) + ");\n");
				Main.insertFuncCall(right);
			} else rightName = right.toString();
			--Util.indent;
			buf.append(Util.generateIndention() + "}\n");
			return ("(" + leftName + " && " + rightName + ")");
		} else if (ot == OpType.PARAOR) {
			buf.append(Util.generateIndention() + "if (!" + leftName + ") {\n");
			++Util.indent;
			String rightName;
			if (checkPara(right) && right.has_func_call) {
				rightName = handleShortCircuit(right, buf);
			} else if (right.has_func_call) {
				rightName = makeVarName(right);
				buf.append(Util.generateIndention() + "TRACE_START();\n");
				buf.append(Util.generateIndention() + rightName + " = " + right.toString() + ";\n");
				buf.append(Util.generateIndention() + "TRACE_END(" + (++Main.post_fix_cnt) + ");\n");
				Main.insertFuncCall(right);
			} else rightName = right.toString();
			--Util.indent;
			buf.append(Util.generateIndention() + "}\n");
			return ("(" + leftName + " || " + rightName + ")");
		}
		return null;
	}
	
	public static boolean checkPara(Expr e) {
		return ((e instanceof Op) && (((Op)e).opType == OpType.PARAAND || ((Op)e).opType == OpType.PARAOR));
	}

	public static void insertShortCurcuitToNameSet(Expr expr) {
		Expr left, right;
		if (((Op)expr).left instanceof Exprs) left = ((Exprs)((Op)expr).left).list.getLast();
		else left = ((Op)expr).left;
		if (((Op)expr).right instanceof Exprs) right = ((Exprs)((Op)expr).right).list.getLast();
		else right = ((Op)expr).right;
		
		if (checkPara(left) && left.has_func_call) {
			insertShortCurcuitToNameSet(left);
		} else if (left.has_func_call) {
			Util.result_name_set.add(makeVarName(left));
		}
		if (checkPara(right) && right.has_func_call) {
			insertShortCurcuitToNameSet(right);
		} else if (right.has_func_call) {
			Util.result_name_set.add(makeVarName(right));
		}
	}
}
