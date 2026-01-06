package org.hbrs.se1.ws25.exercises.uebung9;

import java.io.UnsupportedEncodingException;

public class TextDocument extends CoreDocument {
    private String content;
    private Encoding encoding;

    public TextDocument(String content, Encoding encoding) {
        this.content = content;
        this.encoding = encoding;
    }

    @Override
    public long getSize() {
        try {
            byte[] bytes = content.getBytes(encoding.getCharsetName());
            return bytes.length;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
