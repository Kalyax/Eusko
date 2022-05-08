package fr.kalyax.eusko.parser;

import fr.kalyax.eusko.plugins.Sentence;
import fr.kalyax.eusko.tokens.Token;
import fr.kalyax.eusko.tokens.TokenType;
import fr.kalyax.eusko.types.Object;
import fr.kalyax.eusko.types.Type;

import java.util.ArrayList;

public class Parser {

    private ArrayList<Token> tokens;

    public Parser(ArrayList<Token> tokens){
        this.tokens = tokens;
    }

    public ArrayList<GenericSentence> parse() {
        ArrayList<GenericSentence> sentences = new ArrayList<>();
        String sentence = "";
        ArrayList<Object> types = new ArrayList<>();
        for(int i = 0; i < this.tokens.size(); i++){
            System.out.println(this.tokens.get(i).getType() + " " + this.tokens.get(i).getValue());
            if(this.tokens.get(i).getType() == TokenType.KEYWORD){
                sentence += this.tokens.get(i).getValue();
            }

            //TODO: operations simplification
            else if(this.tokens.get(i).getType() == TokenType.STRING ||
                    this.tokens.get(i).getType() == TokenType.INTEGER ||
                    this.tokens.get(i).getType() == TokenType.BRACKETS ||
                    this.tokens.get(i).getType() == TokenType.OPERATOR){
                int j = 0;
                ArrayList<Token> operationTokens = new ArrayList<>();
                while(tokens.get(i+j).getType() == TokenType.STRING ||
                        tokens.get(i+j).getType() == TokenType.INTEGER ||
                        tokens.get(i+j).getType() == TokenType.BRACKETS ||
                        tokens.get(i+j).getType() == TokenType.OPERATOR){
                    operationTokens.add(tokens.get(i+j));
                    j++;
                }
                ArrayList<Token> simplifiedOperation = simplifyOperations(operationTokens);
                assert simplifiedOperation != null;
                for(Token token : simplifiedOperation){
                    if(token.getType() == TokenType.STRING){
                        types.add(new Object(Type.STRING, token.getValue()));
                        sentence += "%string%";
                    }
                    else if(token.getType() == TokenType.INTEGER){
                        types.add(new Object(Type.INTEGER, token.getValue()));
                        sentence += "%integer%";
                    }
                }
                i += j-1;
            }
            else if(this.tokens.get(i).getType() == TokenType.NEWLINE || this.tokens.get(i).getType() == TokenType.EOF){
                sentences.add(new GenericSentence(sentence.substring(0, sentence.length()-1), types));
                sentence = "";
                types = new ArrayList<>();
                continue;
            }
            if(this.tokens.get(i).getType() != TokenType.NEWLINE || this.tokens.get(i).getType() != TokenType.EOF) sentence += " ";
        }
        return sentences;
    }

    private ArrayList<Token> simplifyOperations(ArrayList<Token> tokens){
        ArrayList<Token> simplifiedTokens = new ArrayList<>();
        for(int i = 1; i < tokens.size(); i++){
            if(tokens.get(i).getType() == TokenType.BRACKETS){
                if(tokens.get(i).getValue().equals("(")){
                    int j = i+1;
                    int innerBrackets = 0;
                    ArrayList<Token> innerTokens = new ArrayList<>();
                    while(!tokens.get(j).getValue().equals(")") && innerBrackets == 0){
                        if(tokens.get(j).getValue().equals("(")) innerBrackets++;
                        if(tokens.get(j).getValue().equals(")") && innerBrackets != 0) innerBrackets--;
                        innerTokens.add(tokens.get(j));
                        j++;
                    }
                    if (j > i) {
                        tokens.subList(i, j+1).clear();
                    }
                    tokens.addAll(i, simplifyOperations(innerTokens));
                    i = 0;
                }
            }
            else if(tokens.get(i).getType() == TokenType.OPERATOR){
                if(tokens.get(i-1).getType() == TokenType.INTEGER && tokens.get(i+1).getType() == TokenType.INTEGER){
                    int j = (int) tokens.get(i-1).getValue();
                    int k = (int) tokens.get(i+1).getValue();
                    //TODO: apply on / and %
                    if(k == 0){
                        //throw new Exception("Division by 0");
                        return null;
                    }
                    int result = switch ((String) tokens.get(i).getValue()) {
                        case "+" -> j + k;
                        case "-" -> j - k;
                        case "*" -> j * k;
                        case "/" -> j / k;
                        case "%" -> j % k;
                        default -> 0;
                    };
                    simplifiedTokens.add(new Token(TokenType.INTEGER, result));
                    i = i+2;
                }
                else if(tokens.get(i+1).getType() == TokenType.BRACKETS){
                    if(tokens.get(i+1).getValue().equals("(")){
                        continue;
                    }
                    else if(tokens.get(i+1).getValue().equals("{")){
                        //TODO: implement variables evaluation
                    }
                }
                else if(tokens.get(i-1).getType() == TokenType.STRING && tokens.get(i+1).getType() == TokenType.STRING){
                    int j = i-1;
                    int k = i+1;
                    String result = "";
                    if ("+".equals(tokens.get(i).getValue())) {
                        result = tokens.get(j).getValue() + (String) tokens.get(k).getValue();
                    }
                    else{
                        //throw Exception("Invalid operator");
                        return null;
                    }
                    simplifiedTokens.add(new Token(TokenType.STRING, result));
                    i = i+2;
                }
            }
            else if(tokens.get(i).getType() == TokenType.INTEGER) i = i-2;
        }
        return simplifiedTokens;
    }
}
