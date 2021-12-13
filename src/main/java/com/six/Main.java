package com.six;

import com.six.tokens.Token;
import com.six.exceptions.CalculationException;
import com.six.exceptions.ParserException;
import com.six.exceptions.PrintException;
import com.six.visitors.CalcVisitor;
import com.six.visitors.ParserVisitor;
import com.six.visitors.PrintVisitor;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws ParserException, IOException, PrintException, CalculationException {
        Scanner scanner = new Scanner(System.in);
        String string  = scanner.nextLine();
        InputStream inputStream = new ByteArrayInputStream(string.getBytes(StandardCharsets.UTF_8));
        ParserVisitor parserVisitor = new ParserVisitor(Tokenizer.tokenize(inputStream));
        List<Token> rpnTokens = parserVisitor.toRPN();
        PrintVisitor printVisitor = new PrintVisitor();
        printVisitor.print(rpnTokens);
        CalcVisitor calcVisitor = new CalcVisitor();
        System.out.println(calcVisitor.calculate(rpnTokens));
    }

}
