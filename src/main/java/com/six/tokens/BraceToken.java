package com.six.tokens;

import com.six.exceptions.CalculationException;
import com.six.exceptions.PrintException;
import com.six.visitors.TokenVisitor;

public class BraceToken extends Token {

    public BraceType typeToken;

    public BraceToken(BraceType type) {
        this.typeToken = type;
    }

    @Override
    public void accept(TokenVisitor visitor) throws PrintException, CalculationException {
        visitor.visit(this);
    }

    public enum BraceType {
        LEFT_BRACE, RIGHT_BRACE
    }
}
