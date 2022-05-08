package fr.kalyax.eusko.parser;

import fr.kalyax.eusko.types.Object;

import java.util.ArrayList;

public class GenericSentence {

    private String sentence;
    private ArrayList<Object> args;

    public GenericSentence(String sentence, ArrayList<Object> args) {
        this.sentence = sentence;
        this.args = args;
    }

    public String getSentence() {
        return sentence;
    }

    public ArrayList<Object> getArgs() {
        return args;
    }

}
