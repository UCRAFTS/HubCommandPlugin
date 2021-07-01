package net.ucrafts.hub;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ServerConnection;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import de.leonhard.storage.internal.FlatFile;

@CommandAlias("hub")
public class HubCommand extends BaseCommand
{

    private final HubCommandPlugin plugin;
    private final FlatFile config;

    public HubCommand(HubCommandPlugin plugin)
    {
        this.plugin = plugin;
        this.config = this.plugin.getConfig();
    }

    @Default
    public void onCommand(CommandSource source)
    {
        if (!(source instanceof Player)) {
            return;
        }

        Player player = (Player) source;

        if (!player.getCurrentServer().isPresent()) {
            return;
        }

        ServerConnection server = player.getCurrentServer().get();

        if (this.config.getList(ConfigType.BLACK_LIST.getName()).contains(server.getServerInfo().getName())) {
            return;
        }

        if (!this.plugin.getServer().getServer(this.config.getString(ConfigType.HUB.getName())).isPresent()) {
            return;
        }

        RegisteredServer hub = this.plugin.getServer().getServer(this.config.getString(ConfigType.HUB.getName())).get();
        player.createConnectionRequest(hub).connect();
    }
}
