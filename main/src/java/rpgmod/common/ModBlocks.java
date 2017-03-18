package rpgmod.common;

import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Centralized storage for mod blocks.
 */
@EventBusSubscriber
public class ModBlocks {
    @SubscribeEvent
    public static void register(RegistryEvent.Register<Block> event) {
        //event.getRegistry().register(...);
    }
}
