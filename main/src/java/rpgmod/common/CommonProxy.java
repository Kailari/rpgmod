package rpgmod.common;

import net.minecraft.command.ICommand;
import net.minecraftforge.common.MinecraftForge;
import rpgmod.api.attributes.PlayerAttributeManager;
import rpgmod.common.commands.CommandAttribute;
import rpgmod.common.player.attributes.PlayerAttributeManagerImpl;

import java.util.function.Consumer;

/**
 * Common proxy for mod-main level code shared with the client and the server.
 */
public class CommonProxy {
    public void preInit() {
        // Assign API instances.
        PlayerAttributeManager.INSTANCE = new PlayerAttributeManagerImpl();

        Capabilities.register();
        MinecraftForge.EVENT_BUS.register(Capabilities.class);
    }

    public void init() {

    }

    public void postInit() {

    }

    public void serverStart(Consumer<ICommand> cmdRegFunc) {
        cmdRegFunc.accept(new CommandAttribute());
    }
}
