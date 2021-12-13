package com.six.states;

import com.six.tokens.Token;

import java.util.List;

public class EndingState implements State {
    @Override
    public boolean isEndingState() {
        return true;
    }

    @Override
    public State nextState(char c, List<Token> tokens) {
        return this;
    }
}
