package fr.kalyax.eusko.parser;

import fr.kalyax.eusko.types.Type;

public class GenericSentence {

    private String sentence;
    private Type[] args;

    public GenericSentence(String sentence, Type[] args) {
        this.sentence = sentence;
        this.args = args;
    }

    public String getSentence() {
        return sentence;
    }

    public Type[] getArgs() {
        return args;
    }

}
