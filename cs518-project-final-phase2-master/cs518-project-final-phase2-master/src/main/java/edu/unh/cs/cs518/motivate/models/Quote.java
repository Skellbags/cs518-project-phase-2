package edu.unh.cs.cs518.motivate.models;

public class Quote {
    public final String quote;
    public final String author;

    public Quote(String quote, String author) {
        this.quote = quote;
        this.author = author;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "quote='" + quote + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
