package com.six.visitors;

import com.six.exceptions.CalculationException;
import com.six.exceptions.PrintException;
import com.six.tokens.BraceToken;
import com.six.Main;
import com.six.tokens.NumberToken;
import com.six.tokens.OperationToken;
import com.six.tokens.Token;

import java.util.List;

public class PrintVisitor extends TokenVisitor {

    @Override
    public void visit(NumberToken token) {
        Main.writer.write(Long.toString(token.value).toCharArray());
    }

    @Override
    public void visit(BraceToken token) throws PrintException {
        throw new PrintException("No braces in polish notation!");
    }

    @Override
    public void visit(OperationToken token) throws CalculationException, PrintException {
        Main.writer.write(token.printOperation());
    }

    public void print(List<Token> tokens) throws PrintException, CalculationException {
        for (var token: tokens) {
            token.accept(this);
            Main.writer.write(' ');
        }
        Main.writer.println();
        Main.writer.flush();
    }
}
