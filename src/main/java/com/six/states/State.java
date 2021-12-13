package com.six.states;

import com.six.tokens.Token;
import com.six.exceptions.ParserException;

import java.util.List;

public interface State {
    default boolean isEndingState() {
        return false;
    };
    State nextState(char c, List<Token> tokens) throws ParserException;
}
