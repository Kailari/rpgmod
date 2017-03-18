package rpgmod.common;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Centralized storage for mod items.
 */
@EventBusSubscriber
public class ModItems {
    @SubscribeEvent
    public static void register(RegistryEvent.Register<Item> event) {
        //event.getRegistry().register(...);
    }
}
