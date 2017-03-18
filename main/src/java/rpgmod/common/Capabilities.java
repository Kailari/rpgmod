package rpgmod.common;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Centralized storage for mod's Capabilities.
 */
public class Capabilities {
    static void register() {

    }

    @SubscribeEvent
    public static void onAttachCapabilitiesEntity(AttachCapabilitiesEvent<Entity> event) {
        Entity entity = event.getObject();
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;
        }
    }

    private static ResourceLocation key(String key) {
        return new ResourceLocation(RPGMod.MODID, key);
    }

    @SubscribeEvent
    public static void onAttachCapabilitiesTileEntity(AttachCapabilitiesEvent<TileEntity> event) {

    }

    @SubscribeEvent
    public static void onAttachCapabilitiesItem(AttachCapabilitiesEvent<Item> event) {

    }

    @SubscribeEvent
    public static void onAttachCapabilitiesWorld(AttachCapabilitiesEvent<World> event) {

    }
}
