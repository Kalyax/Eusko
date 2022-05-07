package fr.kalyax.eusko.tokens;

public class Token {

    private final TokenType type;
    private final String value;

    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    public Token(TokenType space) {
        this.type = space;
        this.value = "";
    }

    public TokenType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

}
