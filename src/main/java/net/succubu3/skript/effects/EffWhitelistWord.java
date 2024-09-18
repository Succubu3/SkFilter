package net.succubu3.skript.effects;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import net.succubu3.skfilter;
import org.bukkit.event.Event;

public class EffWhitelistWord extends Effect {

    private Expression<String> wordExpr;

    @Override
    protected void execute(Event event) {
        String word = wordExpr.getSingle(event);
        if (word != null) {
            skfilter.getWordFilter().addToWhitelist(word);
        }
    }

    @Override
    public String toString(Event event, boolean b) {
        return "whitelist word " + wordExpr.toString(event, b);
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        wordExpr = (Expression<String>) expressions[0];
        return true;
    }
}
