package fr.kalyax.eusko.tokens;

public record Token<T>(TokenType type, T value) {

    public TokenType getType() {
        return type;
    }

    public T getValue() {
        return value;
    }

}
