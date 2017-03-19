package rpgmod.common;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.registry.RegistryBuilder;
import rpgmod.api.player.attributes.PlayerAttribute;
import rpgmod.api.player.attributes.RPGAttribute;

@Mod(modid = RPGMod.MODID, version = RPGMod.VERSION, name = RPGMod.NAME)
public class RPGMod {
    public static final String MODID = "rpgmod";
    public static final String VERSION = "1.0.0";
    public static final String NAME = "Kailari's RPG-Mod";

    @Instance
    public static RPGMod INSTANCE;

    @SidedProxy(clientSide = "rpgmod.client.ClientProxy", serverSide = "rpgmod.common.CommonProxy")
    private static CommonProxy proxy = null;

    public RPGMod() {
        buildRegistries();
    }

    // region Registries

    private static final ResourceLocation PATTR_REGISTRY_NAME = new ResourceLocation(MODID, "playerattributes");
    private static final int PATTR_REGISTRY_MIN_ID = 0;
    private static final int PATTR_REGISTRY_MAX_ID = Integer.MAX_VALUE;

    private static final ResourceLocation RPGATTR_REGISTRY_NAME = new ResourceLocation(MODID, "rpgattributes");
    private static final int RPGATTR_REGISTRY_MIN_ID = 0;
    private static final int RPGATTR_REGISTRY_MAX_ID = Integer.MAX_VALUE;

    private static void buildRegistries() {
        new RegistryBuilder<PlayerAttribute>()
                .setType(PlayerAttribute.class)
                .setName(PATTR_REGISTRY_NAME)
                .setIDRange(PATTR_REGISTRY_MIN_ID, PATTR_REGISTRY_MAX_ID)
                .create();

        new RegistryBuilder<RPGAttribute>()
                .setType(RPGAttribute.class)
                .setName(RPGATTR_REGISTRY_NAME)
                .setIDRange(RPGATTR_REGISTRY_MIN_ID, RPGATTR_REGISTRY_MAX_ID)
                .create();
    }

    // endregion Registries

    // region EventHandlers

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

    // endregion EventHandlers
}
