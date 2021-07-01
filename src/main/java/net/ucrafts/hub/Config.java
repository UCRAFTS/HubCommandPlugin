package net.ucrafts.hub;

import com.google.inject.Inject;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import de.leonhard.storage.Json;
import de.leonhard.storage.internal.FlatFile;

import java.nio.file.Path;
import java.util.Arrays;

public class Config
{

    private final FlatFile config;

    @Inject
    public Config(@DataDirectory Path dataDirectory)
    {
        this.config = new Json("config", dataDirectory.toString());
        this.config.setDefault(ConfigType.BLACK_LIST.getName(), Arrays.asList("auth-1", "auth-2"));
        this.config.setDefault(ConfigType.HUB.getName(), "hub-1");
    }

    public FlatFile getConfig()
    {
        return this.config;
    }
}
