package codechanger;

%%

%class Lexer
%unicode
%line
%column
%cup
%implements Symbols

%{
	StringBuffer string = new StringBuffer();
	char ch;
	int flag = 0;

	private void err(String message) {
		System.out.println("Scanning error in line " + yyline + ", column " + yycolumn + ": " + message);
	}

	private java_cup.runtime.Symbol tok(int kind) {
		return new java_cup.runtime.Symbol(kind, yyline, yycolumn);
	}

	private java_cup.runtime.Symbol tok(int kind, Object value) {
		return new java_cup.runtime.Symbol(kind, yyline, yycolumn, value);
	}
%}

%eofval{
	{
		if (yystate() == YYSTRING) {
			err("String boarder doesn't match");
		}
		return tok(EOF, null);
	}
%eofval}

LineTerm = \n|\r|\r\n
InputCharacter = [^\r\n]
Whitespace = {LineTerm}|[ \t\f]

Comment = {TraditionalComment} | {SingleLineComment} | {DocumentationComment}
TraditionalComment   = "/*" [^*] ~"*/"
SingleLineComment = "//" {InputCharacter}* {LineTerm}
DocumentationComment = "/**" {CommentContent} "*"+ "/"
CommentContent       = ( [^*] | \*+ [^/*] )*
PreProcessor = #[^\n\r]*{LineTerm}

Identifier = [_$a-zA-Z][_$a-zA-Z0-9]*
Integer = 0|[1-9][0-9]*

%state YYSTRING, YYCHAR

%%

<YYINITIAL> {
	{Comment} {}
	{PreProcessor} {
		return tok(PREPROCESSOR, yytext());
	}
	{Whitespace} {}
	
	\" { string.setLength(0); yybegin(YYSTRING); }
	\' { flag = 0; yybegin(YYCHAR); }

	"DEBUG_RETURN" { return tok(DEBUG_RETURN); }
	"void" { return tok(VOID); }
	"char" { return tok(CHAR); }
	"int"    { return tok(INT); }
	"bool"	{return tok(BOOL); }
	"struct" { return tok(STRUCT); }
	"union" { return tok(UNION); }
	"if"     { return tok(IF); }
	"else" { return tok(ELSE); }
	"while"	{ return tok(WHILE); }
	"for" { return tok(FOR); }
	"continue" { return tok(CONTINUE); }
	"break" { return tok(BREAK); }
	"return" { return tok(RETURN); }
	"sizeof" { return tok(SIZEOF); }
	"goto" {return tok(GOTO); }


	"(" { return tok(LPAREN); }
	")" { return tok(RPAREN); }
	"{" { return tok(LBRACE); }
	"}" { return tok(RBRACE); }
	"[" { return tok(LBRAKET); }
	"]" { return tok(RBRAKET); }
	"," { return tok(COMMAR); }
	";" { return tok(SEMICOLON); }
	"." { return tok(DOT); }
	":" { return tok(COLON);}

	"+" { return tok(PLUS); }
	"-" { return tok(MINUS); }
	"*" { return tok(TIMES); }
	"/" { return tok(DIVIDE); }
	"%" { return tok(MOD); }
	"=" { return tok(ASSIGN); }
	"!" { return tok(NOT); }
	"~" { return tok(TIDLE); }
	"<"  { return tok(LT); }
	">"  { return tok(GT); }
	"|" { return tok(OR); }
	"&" { return tok(AND); }
	"^" { return tok(XOR); }

	"||" { return tok(PARAOR); }
	"&&" { return tok(PARAAND); }
	"==" { return tok(EQ); }
	"!=" { return tok(NE) ;}
	"<=" { return tok(LE); }
	">=" { return tok(GE); }
	"<<" { return tok(SHL); }
	">>" { return tok(SHR); }
	"++" { return tok(INC); }
	"--" { return tok(DEC); }
	"->" { return tok(PTR); }
	"..." { return tok(ELLIPSIS); }
	"*=" { return tok(MUL_ASSIGN); }
	"/=" { return tok(DIV_ASSIGN); }
	"%=" { return tok(MOD_ASSIGN); }
	"+=" { return tok(ADD_ASSIGN); }
	"-=" { return tok(SUB_ASSIGN); }
	"<<=" { return tok(SHL_ASSIGN); }
	">>=" { return tok(SHR_ASSIGN); }
	"&=" { return tok(AND_ASSIGN); }
	"^=" { return tok(XOR_ASSIGN); }
	"|=" { return tok(OR_ASSIGN); }

	{Identifier} {
		return tok(ID, yytext());
	}
	{Integer} { return tok(NUM, new Integer(yytext())); }

	[^] { throw new RuntimeException("Illegal character!"); }
}

<YYSTRING> {
	\" { yybegin(YYINITIAL); return tok(STRING, string.toString()); }
	[^\n\r\t\"\\]+ { string.append(yytext()); }
	\\t { string.append('\t'); }
	\\n { string.append('\n'); }
	\\r { string.append('\r'); }
	\\\" { string.append('\"'); }
	\\\\ { string.append('\\'); }
}

<YYCHAR> {
	\' { yybegin(YYINITIAL); flag = 0; return tok(CHARCONST, ch); }
	[^\'\n\r\t\'\\] {
		if (flag != 0) throw new RuntimeException("Char Err");
		else {
			ch = yytext().charAt(0); flag = 1;
		}
	}
	\\t {
		if (flag != 0) throw new RuntimeException("Char Err");
		else {
			ch = '\t'; flag = 1;
		}
	}
	\\n {
		if (flag != 0) throw new RuntimeException("Char Err");
		else {
			ch = '\n'; flag = 1;
		}
	}
	\\r {
		if (flag != 0) throw new RuntimeException("Char Err");
		else {
			ch = '\r'; flag = 1;
		}
	}
	\\\' {
		if (flag != 0) throw new RuntimeException("Char Err");
		else {
			ch = '\''; flag = 1;
		}
	}
	\\\\ {
		if (flag != 0) throw new RuntimeException("Char Err");
		else {
			ch = '\\'; flag = 1;
		}
	}
}
