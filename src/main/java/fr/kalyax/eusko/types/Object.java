package fr.kalyax.eusko.types;

public record Object<T> (Type type, T value) {
    public Type getType() {
        return type;
    }

    public T getValue() {
        return value;
    }
}
