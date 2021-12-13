package com.six.states;

import com.six.tokens.NumberToken;
import com.six.tokens.Token;
import com.six.exceptions.ParserException;

import java.util.List;

public class NumberState implements State {

    StringBuffer number = new StringBuffer();

    @Override
    public State nextState(char c, List<Token> tokens) throws ParserException {
        if (Character.isDigit(c)) {
            number.append(c);
            return this;
        }
        tokens.add(new NumberToken(Long.parseLong(number.toString())));
        if (Character.isWhitespace(c)) {
            return new DefaultState();
        } else {
            return new DefaultState().nextState(c, tokens);
        }
    }
}
