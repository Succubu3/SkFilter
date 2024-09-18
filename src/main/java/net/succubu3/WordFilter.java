package net.succubu3;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class WordFilter {
    private Set<String> blacklist = new HashSet<>();
    private Set<String> whitelist = new HashSet<>();
    private Set<Pattern> regexPatterns = new HashSet<>();
    private Set<String> linkBlacklist = new HashSet<>();
    private Set<String> linkWhitelist = new HashSet<>();
    private boolean globalLinkBlacklist = false;

    public void addToBlacklist(String word) {
        blacklist.add(word);
        regexPatterns.add(generateRegexForWord(word));
    }

    public void addToWhitelist(String word) {
        whitelist.add(word);
    }

    public boolean isFiltered(List<String> messages) {
        for (String message : messages) {
            if (isFiltered(message)) {
                return true;
            }
        }
        return false;
    }

    public boolean isFiltered(String message) {
        String normalizedMessage = normalizeSymbols(message);

        for (Pattern pattern : regexPatterns) {
            if (pattern.matcher(normalizedMessage).find()) {
                if (!isWhitelisted(normalizedMessage)) {
                    return true;
                }
            }
        }

        return isLinkBlocked(message);
    }

    public boolean isFlagged(String word) {
        String normalizedWord = normalizeSymbols(word);

        for (Pattern pattern : regexPatterns) {
            if (pattern.matcher(normalizedWord).find()) {
                return true;
            }
        }
        return false;
    }

    public void addLinkToBlacklist(String link) {
        linkBlacklist.add(link);
    }

    public void addLinkToWhitelist(String link) {
        linkWhitelist.add(link);
    }

    public void setGlobalLinkBlacklist(boolean enabled) {
        this.globalLinkBlacklist = enabled;
    }

    private boolean isWhitelisted(String word) {
        return whitelist.stream().anyMatch(word::contains);
    }

    public boolean isLinkBlocked(String message) {
        for (String word : message.split("\\s+")) {
            if (isLink(word)) {
                if (globalLinkBlacklist && !linkWhitelist.contains(word)) {
                    return true;
                } else if (linkBlacklist.contains(word)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isLink(String word) {
        String linkRegex = "^(http|https)://.*$";
        return word.matches(linkRegex);
    }

    private Pattern generateRegexForWord(String word) {
        StringBuilder regexBuilder = new StringBuilder();

        for (char c : word.toCharArray()) {
            regexBuilder.append("[")
                    .append(getCharacterAlternatives(c))
                    .append("]+[\\W_]*");
        }

        return Pattern.compile(regexBuilder.toString(), Pattern.CASE_INSENSITIVE);
    }

    private String getCharacterAlternatives(char c) {
        switch (Character.toLowerCase(c)) {
            case 'a': return "a@4α";
            case 'i': return "i!1|¡";
            case 'e': return "e3€";
            case 'o': return "o0°";
            case 's': return "s$5§";
            case 'l': return "l1!|";
            case 'c': return "c¢©";
            case 'u': return "uûùü";
            case 'f': return "fƒ";
            case 't': return "t7+";
            case 'd': return "dđ";
            default: return Character.toString(c);
        }
    }

    private String normalizeSymbols(String message) {
        return message.replaceAll("[@4α]", "a")
                .replaceAll("[1|!¡]", "i")
                .replaceAll("[3€]", "e")
                .replaceAll("[0°]", "o")
                .replaceAll("[$5§]", "s")
                .replaceAll("[¢©]", "c")
                .replaceAll("[ûùü]", "u")
                .replaceAll("ƒ", "f")
                .replaceAll("[7+]", "t")
                .replaceAll("đ", "d")
                .replaceAll("[^a-zA-Z0-9]", "");
    }
}