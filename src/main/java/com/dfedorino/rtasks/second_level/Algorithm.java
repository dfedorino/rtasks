package com.dfedorino.rtasks.second_level;

public abstract class Algorithm<I, O> {
    public O outputFor(I input) {
        return null;
    }

    @SafeVarargs
    public final O outputFor(I... input) {
        return null;
    }

    @Override
    public String toString() {
        return "";
    }
}
