package net.succubu3.skript.effects;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import net.succubu3.skfilter;

public class EffWhitelistLink extends Effect {
    private Expression<String> linkExpr;

    @Override
    protected void execute(Event event) {
        String link = linkExpr.getSingle(event);
        if (link != null) {
            skfilter.getWordFilter().addLinkToWhitelist(link);
        }
    }

    @Override
    public String toString(Event event, boolean b) {
        return "whitelist link " + linkExpr.toString(event, b);
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        linkExpr = (Expression<String>) expressions[0];
        return true;
    }
}
