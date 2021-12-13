package com.six.visitors;

import com.six.exceptions.CalculationException;
import com.six.exceptions.PrintException;
import com.six.tokens.BraceToken;
import com.six.tokens.NumberToken;
import com.six.tokens.OperationToken;
import com.six.tokens.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ParserVisitor extends TokenVisitor {
    private final Stack<Token> stack = new Stack<>();
    private final List<Token> polish = new ArrayList<>();
    private final List<Token> tokens;

    public ParserVisitor(List<Token> tokenList) {
        this.tokens = tokenList;
    }

    @Override
    public void visit(NumberToken token) {
        polish.add(token);
    }

    @Override
    public void visit(BraceToken token) {
        if (token.typeToken == BraceToken.BraceType.LEFT_BRACE) {
            stack.push(token);
        } else {
            while (!stack.isEmpty()) {
                Token topToken = stack.pop();
                if (topToken instanceof BraceToken) {
                    if (((BraceToken) topToken).typeToken == BraceToken.BraceType.LEFT_BRACE) {
                        return;
                    }
                } else {
                    polish.add(topToken);
                }
            }
            throw new IllegalStateException("Brace balance is under zero: more closed braces than should be");
        }
    }

    @Override
    public void visit(OperationToken token) {
        Priority priority = calculatePriority(token);

        while (!stack.isEmpty()) {
            final Token top = stack.peek();
            if (top instanceof OperationToken && calculatePriority((OperationToken) top).getValue()
                    >= priority.getValue()) {
                polish.add(stack.pop());
            } else {
                break;
            }
        }

        stack.add(token);
    }

    public List<Token> toRPN() throws PrintException, CalculationException {
        for (var token: tokens) {
            token.accept(this);
        }
        while (!stack.empty()) {
            if (stack.peek() instanceof BraceToken) {
                throw new IllegalStateException("Too many open brackets");
            }
            polish.add(stack.pop());
        }
        return polish;
    }


    private Priority calculatePriority(OperationToken token) {
        if (token.typeToken == OperationToken.OperationType.ADD ||
                token.typeToken == OperationToken.OperationType.SUBTRACT) {
            return Priority.LOW;
        } else {
            return Priority.HIGH;
        }
    }

    enum Priority {
        LOW(1), HIGH(2);

        private final int id;
        Priority(int id) { this.id = id; }
        public int getValue() { return id; }
    }
}
