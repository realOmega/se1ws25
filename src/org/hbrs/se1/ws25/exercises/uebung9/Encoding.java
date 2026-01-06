package org.hbrs.se1.ws25.exercises.uebung9;

public enum Encoding {
    UTF8("UTF-8"),
    UTF16("UTF-16"),
    UTF32("UTF-32");

    private final String charsetName;

    Encoding(String charsetName) {
        this.charsetName = charsetName;
    }
    public String getCharsetName() {
        return charsetName;
    }
}
