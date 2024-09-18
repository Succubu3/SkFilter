**NOTICE! This is still in beta, some stuff may get bypassed in the filter when you make the filter.**

SkFilter is a skript addon based upon making filters for your server more easy and desirable. SkFilter introduces a new way to blacklist/whitelist words & links with a simple pattern recognition system that matches words.

Seeing the filter list is just you adding words to the filter, you can iterate through a list of words and add them manually at any point in time. You can view the documentation for this on SkriptHub. (Will be posted in here when its available)


Effects:
  - add %string% to filter
  - whitelist word %string%
  - blacklist link %string%
  - whitelist link %string%
  - set global link blacklist to %boolean%

Conditions:
  - if link is blocked
  - if message contains a link
  - if message is caught by filter

here is an example:

```yaml
on load:
    set {list::*} to "fuck", "shit", "crap", "sex"

    loop {list::*}:
        add loop-value to filter

on chat:
    if message is caught by filter:
        cancel event
        send "&cYour message contains prohibited language and has been blocked!" to player
```
