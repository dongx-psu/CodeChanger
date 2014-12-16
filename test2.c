#include "A.h"
#include "B.h"
#include "C.h"

bool A()
{
    int i;
    B();
    C();
    while (X() || (Y() <= Z()))
    {
        W();
    }
    for (i = 0; i < 10 && K(); ++i) {
        L();
    }
    if ((C() || P()) && (D() || (E() == F())))
    {
        G();
    }
    else if (K() == MACRO_DEFINED_SOMEWHERE_ELSE)
    {
        C();
        H();
        DEBUG_RETURN(false);
    }
    else
    {
        H();
    }

    if (I())
    {
        goto final;
    }
    DEBUG_RETURN(true);

final:
    DEBUG_RETURN(J());
}


void B() {
    C();
}

int G() {
    return 1;
}


