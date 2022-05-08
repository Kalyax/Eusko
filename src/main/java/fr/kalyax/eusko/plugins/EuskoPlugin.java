package fr.kalyax.eusko.plugins;

import java.util.ArrayList;

public class EuskoPlugin {

    private static ArrayList<EuskoPlugin> plugins = new ArrayList<EuskoPlugin>();
    private static ArrayList<ExpressionBuilder> expressions = new ArrayList<ExpressionBuilder>();

    public EuskoPlugin() {
        EuskoPlugin.plugins.add(this);
    }

    public void registerSentences(){

    }

    public static void registerExpression(ExpressionBuilder builder) {
        EuskoPlugin.expressions.add(builder);
    }

}
