package com.six.states;

import com.six.tokens.BraceToken;
import com.six.tokens.NumberToken;
import com.six.tokens.OperationToken;
import com.six.tokens.Token;
import com.six.exceptions.ParserException;

import java.util.List;

public class DefaultState implements State {

    @Override
    public State nextState(char c, List<Token> tokens) throws ParserException {
        switch (c) {
            case ' ':
                return this;
            case '(':
                tokens.add(new BraceToken(BraceToken.BraceType.LEFT_BRACE));
                return this;
            case ')':
                tokens.add(new BraceToken(BraceToken.BraceType.RIGHT_BRACE));
                return this;
            case '+':
                tokens.add(new OperationToken(OperationToken.OperationType.ADD));
                return this;
            case '-':
                tokens.add(new OperationToken(OperationToken.OperationType.SUBTRACT));
                return this;
            case '/':
                tokens.add(new OperationToken(OperationToken.OperationType.DIVIDE));
                return this;
            case '*':
                tokens.add(new OperationToken(OperationToken.OperationType.MULTIPLY));
                return this;
            default:
                if (Character.isDigit(c)) {
                    return new NumberState().nextState(c, tokens);
                } else {
                    throw new ParserException("Can't parse input");
                }
        }
    }
}
