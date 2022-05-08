package fr.kalyax.eusko.parser;

import fr.kalyax.eusko.plugins.Sentence;
import fr.kalyax.eusko.tokens.Token;
import fr.kalyax.eusko.tokens.TokenType;

import java.util.ArrayList;

public class Parser {

    private ArrayList<Token> tokens;

    public Parser(ArrayList<Token> tokens){
        this.tokens = tokens;
    }

    public ArrayList<GenericSentence> parse() {
        ArrayList<GenericSentence> sentences = new ArrayList<>();
        for(int i = 0; i < tokens.size(); i++){
            String sentence = "";
            if(tokens.get(i).getType() == TokenType.KEYWORD){
                sentence += tokens.get(i).getValue();
            }

            //TODO: operations simplification
            /*else if(tokens.get(i).getType() == TokenType.STRING || tokens.get(i).getType() == TokenType.INTEGER){
                int j = 1;
                while(tokens.get(i+j).getType() == TokenType.OPERATOR){

                }
            }*/
        }
        return sentences;
    }

    private void simplifyOperations(){
        
    }
}
