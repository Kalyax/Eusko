package fr.kalyax.eusko.plugins;

import fr.kalyax.eusko.pattern.PatternRegister;
import fr.kalyax.eusko.types.Object;

public interface Sentence {

    PatternRegister getPattern();
    void execute(Object[] args);

}
