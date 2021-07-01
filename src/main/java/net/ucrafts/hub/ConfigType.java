package net.ucrafts.hub;

import org.jetbrains.annotations.NotNull;

public enum ConfigType
{
    BLACK_LIST("blackList"),
    HUB("hub");

    private final String name;

    ConfigType(@NotNull final String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }
}
