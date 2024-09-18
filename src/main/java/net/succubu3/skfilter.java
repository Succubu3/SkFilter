package net.succubu3;

import ch.njol.skript.Skript;
import net.succubu3.skript.conditions.CondCaughtByFilter;
import net.succubu3.skript.conditions.CondLinkIsBlocked;
import net.succubu3.skript.conditions.CondMessageContainsLink;
import net.succubu3.skript.effects.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class skfilter extends JavaPlugin {

    private static WordFilter wordFilter;

    @Override
    public void onEnable() {

        getLogger().info("skFilter plugin enabled.");

        wordFilter = new WordFilter();

        registerSkriptElements();

    }

    @Override
    public void onDisable() {

        getLogger().info("skFilter plugin disabled.");

        wordFilter = null;
    }

    public static WordFilter getWordFilter() {
        return wordFilter;
    }

    private void registerSkriptElements() {
        if (Skript.isAcceptRegistrations()) {
            Skript.registerEffect(EffAddToFilter.class, "add %string% to filter");
            Skript.registerEffect(EffBlacklistLink.class, "blacklist link %string%");
            Skript.registerEffect(EffWhitelistLink.class, "whitelist link %string%");
            Skript.registerEffect(EffSetGlobalLinkBlacklist.class, "set global link blacklist to %boolean%");
            Skript.registerCondition(CondMessageContainsLink.class, "message contains a link");
            Skript.registerCondition(CondLinkIsBlocked.class, "link is blocked");

            Skript.registerCondition(CondCaughtByFilter.class, "%string% is caught by filter", "%strings% is caught by filter", "caught by filter");
            Skript.registerEffect(EffWhitelistWord.class, "whitelist word %string%");
        } else {
            getLogger().warning("Skript is not ready for registration.");
        }
    }
}