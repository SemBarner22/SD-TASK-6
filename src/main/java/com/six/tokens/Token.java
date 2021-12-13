package com.six.tokens;

import com.six.exceptions.CalculationException;
import com.six.exceptions.PrintException;
import com.six.visitors.TokenVisitor;

public abstract class Token {
    public abstract void accept(TokenVisitor visitor) throws CalculationException, PrintException;
}

