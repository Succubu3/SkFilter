package net.succubu3.skript.conditions;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import net.succubu3.skfilter;
import org.bukkit.event.Event;

public class CondIsFlaggedByFilter extends Condition {

    private Expression<String> wordExpr;

    @Override
    public boolean check(Event event) {
        String word = wordExpr.getSingle(event);
        if (word != null) {
            return skfilter.getWordFilter().isFlagged(word);
        }
        return false;
    }

    @Override
    public String toString(Event event, boolean debug) {
        return "%string% is flagged by filter";
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        wordExpr = (Expression<String>) expressions[0];
        return true;
    }
}