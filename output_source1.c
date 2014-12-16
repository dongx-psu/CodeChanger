#include "A.h"
#include "B.h"
#include "C.h"
#include "trace_tool.h"

bool A()
{
	TRACE_FUNCTION_START();
	bool resultD, resultEF, resultI, resultJ, resultK;
	TRACE_START();
	B();
	TRACE_END(1);
	TRACE_START();
	C();
	TRACE_END(2);
	TRACE_START();
	resultD = D();
	TRACE_END(3);
	if (!resultD) {
		TRACE_START();
		resultEF = E() == F();
		TRACE_END(4);
	}
	if (resultD || resultEF) {
		TRACE_START();
		G();
		TRACE_END(5);
	} else {
		TRACE_START();
		resultK = K() == MACRO_DEFINED_SOMEWHERE_ELSE;
		TRACE_END(6);
		if (resultK) {
			TRACE_START();
			C();
			TRACE_END(7);
			TRACE_START();
			H();
			TRACE_END(8);
			TRACE_FUNCTION_END();
			DEBUG_RETURN(false);
		} else {
			TRACE_START();
			H();
			TRACE_END(9);
		}
	}
	TRACE_START();
	resultI = I();
	TRACE_END(10);
	if (resultI) {
		goto final;
	}
	TRACE_FUNCTION_END();
	DEBUG_RETURN(true);
final:
	TRACE_START();
	resultJ = J();
	TRACE_END(11);
	TRACE_FUNCTION_END();
	DEBUG_RETURN(resultJ);
}
void B()
{
	C();
}
int G()
{
	return 1;
}

