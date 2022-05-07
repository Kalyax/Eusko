package fr.kalyax.eusko;

import fr.kalyax.eusko.tokens.Token;
import fr.kalyax.eusko.tokens.TokenType;

public class Tokenizer {

    private int cursor;
    private String sentence;

    public Tokenizer(String sentence){
        this.sentence = sentence;
        this.cursor = 0;
    }

    public boolean hasNext(){
        return this.cursor < this.sentence.length();
    }

    public Token getNextToken(){
        if(this.hasNext()){
            //Spaces
            if(this.sentence.charAt(0) == ' ' && this.cursor == 0){
                int spaceCount = 1;
                while(this.sentence.charAt(spaceCount) == ' '){
                    spaceCount++;
                    this.cursor++;
                }
                return new Token(TokenType.SPACE, String.valueOf(spaceCount));
            }


            //TODO: negative numbers and floats
            if(this.sentence.charAt(this.cursor) == ' ') this.cursor++;
            //Integers
            if(Character.toString(this.sentence.charAt(this.cursor)).matches("[0-9]+")){
                StringBuilder token = new StringBuilder();
                while(!Character.toString(this.sentence.charAt(this.cursor)).matches("[^0-9]+")){
                    token.append(this.sentence.charAt(this.cursor++));
                }
                return new Token(TokenType.INTEGER, token.toString());
            }
            //String
            else if(this.sentence.charAt(this.cursor) == '"'){
                StringBuilder token = new StringBuilder();
                this.cursor++;
                while(this.sentence.charAt(this.cursor) != '"'){
                    token.append(this.sentence.charAt(this.cursor++));
                }
                this.cursor++;
                return new Token(TokenType.STRING, token.toString());
            }
            //Operators
            else if(Character.toString(this.sentence.charAt(this.cursor)).matches("[+\\-*/<>=:]")){
                StringBuilder token = new StringBuilder();
                token.append(this.sentence.charAt(this.cursor++));
                return new Token(TokenType.OPERATOR, token.toString());
            }
            //Keywords
            else if(this.sentence.charAt(this.cursor) != ' '){
                StringBuilder token = new StringBuilder();
                while(this.hasNext() && this.sentence.charAt(this.cursor) != ' ' && !(Character.toString(this.sentence.charAt(this.cursor)).matches("[+\\-*/<>=:]"))){
                    token.append(this.sentence.charAt(this.cursor++));
                }
                return new Token(TokenType.KEYWORD, token.toString());
            }
        }
        this.cursor++;
        return null;
    }

}
