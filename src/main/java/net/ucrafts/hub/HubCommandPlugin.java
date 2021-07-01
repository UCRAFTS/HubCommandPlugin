package net.ucrafts.hub;

import co.aikar.commands.VelocityCommandManager;
import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import de.leonhard.storage.internal.FlatFile;

@Plugin(
        id = "hubcommand",
        name = "HubCommand",
        version = "1.0.0",
        url = "https://ucrafts.net",
        description = "Hub command with blacklist servers",
        authors = {
                "Alexander Repin / oDD1"
        }
)
public class HubCommandPlugin
{
    private final ProxyServer server;
    private final Config config;

    @Inject
    public HubCommandPlugin(ProxyServer server, Config config)
    {
        this.server = server;
        this.config = config;
    }

    @Subscribe
    public void onProxyInitialize(ProxyInitializeEvent e)
    {
        VelocityCommandManager manager = new VelocityCommandManager(this.server, this);
        manager.registerCommand(new HubCommand(this));
    }

    public FlatFile getConfig()
    {
        return this.config.getConfig();
    }

    public ProxyServer getServer()
    {
        return this.server;
    }
}
