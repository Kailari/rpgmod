package rpgmod.common;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = RPGMod.MODID, version = RPGMod.VERSION, name = RPGMod.NAME)
public class RPGMod {
    public static final String MODID = "rpgmod";
    public static final String VERSION = "1.0.0";
    public static final String NAME = "Kailari's RPG-Mod";

    @Instance
    public static RPGMod INSTANCE;

    @SidedProxy(clientSide = "rpgmod.client.ClientProxy", serverSide = "rpgmod.common.CommonProxy")
    private static CommonProxy proxy = null;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit();
    }

    @EventHandler
    public void serverStart(FMLServerStartingEvent event) {
        proxy.serverStart(event::registerServerCommand);
    }
}
