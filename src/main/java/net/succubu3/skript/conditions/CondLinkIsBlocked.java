package net.succubu3.skript.conditions;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import net.succubu3.skfilter;

public class CondLinkIsBlocked extends Condition {

    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {

        return true;
    }

    @Override
    public boolean check(Event event) {
        if (event instanceof AsyncPlayerChatEvent) {
            String message = ((AsyncPlayerChatEvent) event).getMessage();

            return skfilter.getWordFilter().isLinkBlocked(message);
        }
        return false;
    }

    @Override
    public String toString(Event event, boolean debug) {
        return "link is blocked";
    }
}