package com.six.tokens;

import com.six.exceptions.CalculationException;
import com.six.exceptions.PrintException;
import com.six.visitors.TokenVisitor;

public class OperationToken extends Token {

    public OperationType typeToken;

    public OperationToken(OperationToken.OperationType type) {
        this.typeToken = type;
    }

    @Override
    public void accept(TokenVisitor visitor) throws CalculationException, PrintException {
        visitor.visit(this);
    }

    public char printOperation() throws PrintException {
        if (typeToken == OperationType.ADD) {
            return '+';
        }
        if (typeToken == OperationType.SUBTRACT) {
            return '-';
        }
        if (typeToken == OperationType.MULTIPLY) {
            return '*';
        }
        if (typeToken == OperationType.DIVIDE) {
            return '/';
        }
        throw new PrintException("Unexpected operation type");
    }

    public long applyOperation(long x, long y) throws CalculationException {
        if (typeToken == OperationType.ADD) {
            return x + y;
        }
        if (typeToken == OperationType.SUBTRACT) {
            return y - x;
        }
        if (typeToken == OperationType.MULTIPLY) {
            return x * y;
        }
        if (typeToken == OperationType.DIVIDE) {
            return y / x;
        }
        throw new CalculationException("Type isn't expected");
    }

    public enum OperationType {
        ADD, SUBTRACT, MULTIPLY, DIVIDE
    }
}
