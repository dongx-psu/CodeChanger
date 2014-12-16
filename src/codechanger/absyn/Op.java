package codechanger.absyn;

public class Op extends Expr {
	public String label = "Op";
	public OpType opType;
	public Expr left, right;
	
	public Op(Expr l, Expr r, OpType t) {
		left = l;
		right = r;
		opType = t;
		if (opType == OpType.PARAAND || opType == OpType.PARAOR)
			has_para_cond = true;
		has_para_cond = has_para_cond || left.has_para_cond || right.has_para_cond;
		has_func_call = left.has_func_call || right.has_func_call;
		func_names.addAll(left.func_names);
		func_names.addAll(right.func_names);
	}
	
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append(left.toString() + " ");
		if (opType == OpType.ASSIGN) buf.append("=");
		else if (opType == OpType.MUL_ASSIGN) buf.append("*=");
		else if (opType == OpType.DIV_ASSIGN) buf.append("/=");
		else if (opType == OpType.MOD_ASSIGN) buf.append("%=");
		else if (opType == OpType.ADD_ASSIGN) buf.append("+=");
		else if (opType == OpType.SUB_ASSIGN) buf.append("-=");
		else if (opType == OpType.SHL_ASSIGN) buf.append("<<=");
		else if (opType == OpType.SHR_ASSIGN) buf.append(">>=");
		else if (opType == OpType.AND_ASSIGN) buf.append("&=");
		else if (opType == OpType.XOR_ASSIGN) buf.append("^=");
		else if (opType == OpType.OR_ASSIGN) buf.append("|=");
		else if (opType == OpType.PARAOR) buf.append("||");
		else if (opType == OpType.PARAAND) buf.append("&&");
		else if (opType == OpType.OR) buf.append("|");
		else if (opType == OpType.XOR) buf.append("^");
		else if (opType == OpType.AND) buf.append("&");
		else if (opType == OpType.EQ) buf.append("==");
		else if (opType == OpType.NE) buf.append("!=");
		else if (opType == OpType.LT) buf.append("<");
		else if (opType == OpType.GT) buf.append(">");
		else if (opType == OpType.LE) buf.append("<=");
		else if (opType == OpType.GE) buf.append(">=");
		else if (opType == OpType.PLUS) buf.append("+");
		else if (opType == OpType.MINUS) buf.append("-");
		else if (opType == OpType.TIMES) buf.append("*");
		else if (opType == OpType.DIVIDE) buf.append("/");
		else if (opType == OpType.MOD) buf.append("%");
		else if (opType == OpType.SHL) buf.append("<<");
		else if (opType == OpType.SHR) buf.append(">>");
		buf.append(" " + right.toString());
		return buf.toString();
	}
	
	public static enum OpType {
		ASSIGN, MUL_ASSIGN, DIV_ASSIGN, MOD_ASSIGN, ADD_ASSIGN,
		SUB_ASSIGN, SHL_ASSIGN, SHR_ASSIGN, AND_ASSIGN, XOR_ASSIGN,
		OR_ASSIGN, PARAOR, PARAAND, OR, XOR, AND, EQ, NE, LT, GT, LE,
		GE, PLUS, MINUS, TIMES, DIVIDE, MOD, SHL, SHR
	}
}
