package net.succubu3.skript.effects;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import net.succubu3.skfilter;
import org.bukkit.event.Event;

public class EffAddToFilter extends Effect {
    private Expression<String> wordExpr;

    @Override
    protected void execute(Event event) {
        String word = wordExpr.getSingle(event);
        skfilter.getWordFilter().addToBlacklist(word);
    }

    @Override
    public String toString(Event event, boolean b) {
        return "add word to filter";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        wordExpr = (Expression<String>) expressions[0];
        return true;
    }
}
