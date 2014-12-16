#include "A.h"
#include "B.h"
#include "C.h"
#include "trace_tool.h"

bool A()
{
	TRACE_FUNCTION_START();
	bool resultC, resultD, resultEF, resultI, resultJ, resultK, resultP, resultX, resultYZ;
	int i;
	TRACE_START();
	B();
	TRACE_END(1);
	TRACE_START();
	C();
	TRACE_END(2);
	while (true) {
		TRACE_START();
		resultX = X();
		TRACE_END(3);
		if (!resultX) {
			TRACE_START();
			resultYZ = Y() <= Z();
			TRACE_END(4);
		}
		if (!(resultX || resultYZ)) break;
		TRACE_START();
		W();
		TRACE_END(5);
	}
	for (i = 0; ; ++i) {
		if (i < 10) {
			TRACE_START();
			resultK = K();
			TRACE_END(6);
		}
		if (!(i < 10 && resultK)) break;
		TRACE_START();
		L();
		TRACE_END(7);
	}
	TRACE_START();
	resultC = C();
	TRACE_END(8);
	if (!resultC) {
		TRACE_START();
		resultP = P();
		TRACE_END(9);
	}
	if ((resultC || resultP)) {
		TRACE_START();
		resultD = D();
		TRACE_END(10);
		if (!resultD) {
			TRACE_START();
			resultEF = E() == F();
			TRACE_END(11);
		}
	}
	if ((resultC || resultP) && (resultD || resultEF)) {
		TRACE_START();
		G();
		TRACE_END(12);
	} else {
		TRACE_START();
		resultK = K() == MACRO_DEFINED_SOMEWHERE_ELSE;
		TRACE_END(13);
		if (resultK) {
			TRACE_START();
			C();
			TRACE_END(14);
			TRACE_START();
			H();
			TRACE_END(15);
			TRACE_FUNCTION_END();
			DEBUG_RETURN(false);
		} else {
			TRACE_START();
			H();
			TRACE_END(16);
		}
	}
	TRACE_START();
	resultI = I();
	TRACE_END(17);
	if (resultI) {
		goto final;
	}
	TRACE_FUNCTION_END();
	DEBUG_RETURN(true);
final:
	TRACE_START();
	resultJ = J();
	TRACE_END(18);
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

