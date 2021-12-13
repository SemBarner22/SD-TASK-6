package com.six.visitors;

import com.six.tokens.BraceToken;
import com.six.tokens.NumberToken;
import com.six.tokens.OperationToken;
import com.six.exceptions.CalculationException;
import com.six.exceptions.PrintException;

public abstract class TokenVisitor {
    public abstract void visit(NumberToken token);
    public abstract void visit(BraceToken token) throws PrintException, CalculationException;
    public abstract void visit(OperationToken token) throws CalculationException, PrintException;
}
