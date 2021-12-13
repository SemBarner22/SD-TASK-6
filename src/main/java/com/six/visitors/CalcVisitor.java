package com.six.visitors;

import com.six.exceptions.PrintException;
import com.six.tokens.BraceToken;
import com.six.tokens.NumberToken;
import com.six.tokens.OperationToken;
import com.six.tokens.Token;
import com.six.exceptions.CalculationException;

import java.util.List;
import java.util.Stack;

public class CalcVisitor extends TokenVisitor {
    private final Stack<Long> stack = new Stack<>();

    @Override
    public void visit(final NumberToken token) {
        stack.push(token.value);
    }

    @Override
    public void visit(final BraceToken token) throws CalculationException {
        throw new CalculationException("Should not be any braces");
    }

    @Override
    public void visit(final OperationToken token) throws CalculationException {
        if (stack.size() < 2) {
            throw new CalculationException("Invalid reverse polish expression");
        }
        long a = stack.pop();
        long b = stack.pop();
        stack.push(token.applyOperation(a, b));
    }

    public long calculate(final List<Token> tokens) throws CalculationException, PrintException {
        if (stack.isEmpty()) {
            for (Token token : tokens) {
                token.accept(this);
            }
        }
        return stack.peek();
    }
}
