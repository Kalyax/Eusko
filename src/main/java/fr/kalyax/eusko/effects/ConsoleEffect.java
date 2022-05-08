package fr.kalyax.eusko.effects;

import fr.kalyax.eusko.pattern.PatternRegister;
import fr.kalyax.eusko.plugins.ExpressionBuilder;
import fr.kalyax.eusko.types.Object;
import fr.kalyax.eusko.types.Type;

public class ConsoleEffect implements ExpressionBuilder{

    @Override
    public Type returnType() {
        return Type.NULL;
    }

    @Override
    public PatternRegister getPattern() {
        return new PatternRegister("console %string%");
    }

    @Override
    public void execute(Object[] args) {
        System.out.println(args[0].getValue());
    }
}
