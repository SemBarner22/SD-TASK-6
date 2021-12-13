package com.six;

import com.six.exceptions.ParserException;
import com.six.states.DefaultState;
import com.six.states.EndingState;
import com.six.states.State;
import com.six.tokens.Token;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Tokenizer {

    public static InputStream inputStream;

    public static State state = new DefaultState();
    private static final List<Token> tokens = new ArrayList<>();

    public static List<Token> tokenize(InputStream inputStream) throws IOException, ParserException {
        Tokenizer.inputStream = inputStream;
        return nextIteration();
    }

    public static List<Token> nextIteration() throws IOException, ParserException {
        Character c = nextByte();
        while (!state.isEndingState()) {
            state = state.nextState(c, tokens);
            c = nextByte();
        }
        return tokens;
    }

    private static Character nextByte() throws IOException, ParserException {
        int readResult = inputStream.read();
        if (readResult == -1) {
            state.nextState(' ', tokens);
            state = new EndingState();
            return ' ';
        } else {
            return (char) readResult;
        }
    }

}
