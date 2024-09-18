package net.succubu3.skript.effects;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import net.succubu3.skfilter;

public class EffSetGlobalLinkBlacklist extends Effect {
    private Expression<Boolean> enabledExpr;

    @Override
    protected void execute(Event event) {
        Boolean enabled = enabledExpr.getSingle(event);
        if (enabled != null) {
            skfilter.getWordFilter().setGlobalLinkBlacklist(enabled);
        }
    }

    @Override
    public String toString(Event event, boolean b) {
        return "set global link blacklist to " + enabledExpr.toString(event, b);
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        enabledExpr = (Expression<Boolean>) expressions[0];
        return true;
    }
}
