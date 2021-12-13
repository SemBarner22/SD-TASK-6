package com.six.tokens;

import com.six.visitors.TokenVisitor;

public class NumberToken extends Token {

    public Long value;

    public NumberToken(long value) {
        this.value = value;
    }

    @Override
    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }
}
