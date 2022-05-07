package fr.kalyax.eusko;

import fr.kalyax.eusko.tokens.Token;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Eusko {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\kalya\\Documents\\IntelliJWS\\Eusko\\src\\main\\resources\\main.esk"));

        while(reader.ready()){
            String line = reader.readLine();
            Tokenizer tokenizer = new Tokenizer(line);
            ArrayList<Token> tokens = new ArrayList<>();
            while(tokenizer.hasNext()){
                tokens.add(tokenizer.getNextToken());
            }
            for (Token token : tokens) {
                System.out.println(token.getValue());
            }
        }
        reader.close();
    }

}
