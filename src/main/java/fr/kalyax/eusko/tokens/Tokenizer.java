package fr.kalyax.eusko.tokens;

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
            if(this.cursor == 0 && this.sentence.charAt(0) == ' '){
                int spaceCount = 1;
                while(this.sentence.charAt(spaceCount) == ' '){
                    spaceCount++;
                    this.cursor++;
                }
                return new Token<String>(TokenType.SPACE, String.valueOf(spaceCount));
            }


            //TODO: negative numbers and floats
            if(this.sentence.charAt(this.cursor) == ' ') this.cursor++;
            //Integers
            if(Character.toString(this.sentence.charAt(this.cursor)).matches("[0-9]+")){
                StringBuilder token = new StringBuilder();
                while(this.hasNext() && !Character.toString(this.sentence.charAt(this.cursor)).matches("[^0-9]+")){
                    token.append(this.sentence.charAt(this.cursor++));
                }
                return new Token<Integer>(TokenType.INTEGER, Integer.parseInt(token.toString()));
            }
            //String
            else if(this.sentence.charAt(this.cursor) == '"'){
                StringBuilder token = new StringBuilder();
                this.cursor++;
                while(this.hasNext() && this.sentence.charAt(this.cursor) != '"'){
                    token.append(this.sentence.charAt(this.cursor++));
                }
                this.cursor++;
                return new Token<String>(TokenType.STRING, token.toString());
            }
            //Operators
            else if(Character.toString(this.sentence.charAt(this.cursor)).matches("[+\\-*/<>=]")){
                return new Token<String>(TokenType.OPERATOR, String.valueOf(this.sentence.charAt(this.cursor++)));
            }
            //Identifiers
            else if(Character.toString(this.sentence.charAt(this.cursor)).matches("[:(){}]")){
                return new Token<String>(TokenType.BRACKETS, String.valueOf(this.sentence.charAt(this.cursor++)));
            }
            //Keywords
            else if(this.sentence.charAt(this.cursor) != ' '){
                StringBuilder token = new StringBuilder();
                while(this.hasNext() && this.sentence.charAt(this.cursor) != ' ' && !(Character.toString(this.sentence.charAt(this.cursor)).matches("[+\\-*/<>=:(){}]"))){
                    token.append(this.sentence.charAt(this.cursor++));
                }
                return new Token<String>(TokenType.KEYWORD, token.toString());
            }
        }
        this.cursor++;
        return null;
    }

}
