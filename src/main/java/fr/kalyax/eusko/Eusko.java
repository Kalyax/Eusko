package fr.kalyax.eusko;

import fr.kalyax.eusko.parser.Parser;
import fr.kalyax.eusko.tokens.Token;
import fr.kalyax.eusko.tokens.TokenType;
import fr.kalyax.eusko.tokens.Tokenizer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Eusko {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\kalya\\Documents\\IntelliJWS\\Eusko\\src\\main\\resources\\main.esk"));
        ArrayList<Token> tokens = new ArrayList<>();
        while(reader.ready()){
            String line = reader.readLine();
            Tokenizer tokenizer = new Tokenizer(line);
            while(tokenizer.hasNext()){
                tokens.add(tokenizer.getNextToken());
            }
            if(reader.ready()) tokens.add(new Token(TokenType.NEWLINE, "\n"));
            else tokens.add(new Token(TokenType.EOF, "EOF"));
        }
        reader.close();
        Parser parser = new Parser(tokens);
        System.out.println(parser.parse());
    }

}
