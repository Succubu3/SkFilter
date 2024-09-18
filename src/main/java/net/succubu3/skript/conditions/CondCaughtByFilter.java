package net.succubu3.skript.conditions;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import net.succubu3.skfilter;

import java.util.Arrays;

public class CondCaughtByFilter extends Condition {

    private Expression<String> messageExpr;
    private Expression<String[]> listExpr;

    @Override
    public boolean check(Event event) {

        if (event instanceof AsyncPlayerChatEvent) {
            AsyncPlayerChatEvent chatEvent = (AsyncPlayerChatEvent) event;
            String message = chatEvent.getMessage();

            if (message != null && skfilter.getWordFilter() != null) {
                return skfilter.getWordFilter().isFiltered(message);
            }
        }

        if (listExpr != null) {
            String[][] list = listExpr.getArray(event);

            if (list != null && skfilter.getWordFilter() != null) {
                for (String[] item : list) {
                    if (skfilter.getWordFilter().isFiltered(Arrays.toString(item))) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    @Override
    public String toString(Event event, boolean b) {
        return messageExpr != null ? "if " + messageExpr.toString(event, b) + " is caught by filter" : "if list is caught by filter";
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        if (expressions.length == 1) {
            if (expressions[0].isSingle()) {
                this.messageExpr = (Expression<String>) expressions[0];
            } else {
                this.listExpr = (Expression<String[]>) expressions[0];
            }
        }

        return true;
    }
}
