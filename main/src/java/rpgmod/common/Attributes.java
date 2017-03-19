package rpgmod.common;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.IForgeRegistry;
import rpgmod.api.player.attributes.ModifierInfo;
import rpgmod.api.player.attributes.PlayerAttribute;
import rpgmod.api.player.attributes.RPGAttribute;
import rpgmod.api.player.attributes.VanillaAttributePlayerAttribute;
import rpgmod.common.player.attributes.SimpleXPRamp;

import static net.minecraftforge.fml.common.Mod.EventBusSubscriber;

/**
 * Provides access to attributes.
 */
@EventBusSubscriber
public class Attributes {

    public static final IForgeRegistry<PlayerAttribute> PLAYER = GameRegistry.findRegistry(PlayerAttribute.class);

    @SubscribeEvent
    public static void registerPlayerAttributes(RegistryEvent.Register<PlayerAttribute> event) {
        event.getRegistry().register(Player.MAX_HP);
        event.getRegistry().register(Player.MOVEMENT_SPEED);
        event.getRegistry().register(Player.ARMOR);
        event.getRegistry().register(Player.ARMOR_TOUGHNESS);
        event.getRegistry().register(Player.LUCK);
        event.getRegistry().register(Player.ATTACK_DAMAGE);
        event.getRegistry().register(Player.ATTACK_SPEED);
        event.getRegistry().register(Player.KNOCKBACK_RESISTANCE);
    }

    @SubscribeEvent
    public static void registerRPGAttributes(RegistryEvent.Register<RPGAttribute> event) {
    }

    /**
     * Enumeration of available default player attributes.
     */
    public static final class Player {
        ////////////////////////////////////////////////////////////////////////////////////////////
        // VANILLA HOOKS

        public static final PlayerAttribute MAX_HP = lnk("max_hp", SharedMonsterAttributes.MAX_HEALTH);
        public static final PlayerAttribute MOVEMENT_SPEED = lnk("movement_speed_base", SharedMonsterAttributes.MOVEMENT_SPEED);
        public static final PlayerAttribute ARMOR = lnk("armor", SharedMonsterAttributes.ARMOR);
        public static final PlayerAttribute ARMOR_TOUGHNESS = lnk("armor_toughness", SharedMonsterAttributes.ARMOR_TOUGHNESS);
        public static final PlayerAttribute LUCK = lnk("luck", SharedMonsterAttributes.LUCK);
        public static final PlayerAttribute ATTACK_DAMAGE = lnk("attack_damage_base", SharedMonsterAttributes.ATTACK_DAMAGE);
        public static final PlayerAttribute ATTACK_SPEED = lnk("attack_speed_base", SharedMonsterAttributes.ATTACK_SPEED);
        public static final PlayerAttribute KNOCKBACK_RESISTANCE = lnk("resistance_knockback", SharedMonsterAttributes.KNOCKBACK_RESISTANCE);


        ////////////////////////////////////////////////////////////////////////////////////////////
        // MOVEMENT

        ////////////////////////////////////////////////////////////////////////////////////////////
        // COMBAT

        ////////////////////////////////////////////////////////////////////////////////////////////
        // MINING

        // region Helpers

        private static PlayerAttribute lnk(String registryName, IAttribute linked) {
            return new VanillaAttributePlayerAttribute(RPGMod.MODID, registryName, linked);
        }

        // endregion Helpers
    }

    public static final class RPG {
        public static final RPGAttribute STRENGTH = new RPGAttribute(
                RPGMod.MODID,
                "strength",
                new SimpleXPRamp(0.1d, 10),
                new ModifierInfo(Player.MAX_HP, 0.0d, 0.01d, 0.0d, 1024.0d),
                new ModifierInfo(Player.ATTACK_DAMAGE, 0.0d, 0.01d, 0.0d, 1024.0d));


    }

}
