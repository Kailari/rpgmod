package rpgmod.api.attributes.player;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import org.jetbrains.annotations.NotNull;

/**
 * Exposes capability for conveniently accessing the player attributes.
 */
public enum PlayerAttributes implements IPlayerAttribute {

////////////////////////////////////////////////////////////////////////////////////////////////////
// VANILLA OVERRIDES

    /**
     * Maximum HP
     */
    MAX_HP("rpgmod.attr.max_hp", 20.0D),

    /**
     * Base movement speed
     */
    MOVEMENT_SPEED("rpgmod.attr.movement_speed_base", 0.1D),

    /**
     * Armor
     */
    ARMOR("rpgmod.attr.armor", 0.0D),

    /**
     * Armor toughness
     */
    ARMOR_TOUGHNESS("rpgmod.attr.armor_toughness", 0.0D),

    /**
     * Luck
     */
    LUCK("rpgmod.attr.luck", 0.0D),

    /**
     * Base attack damage
     */
    ATTACK_DAMAGE("rpgmod.attr.attack_damage_base", 1.0D),

    /**
     * Base attack speed
     */
    ATTACK_SPEED("rpgmod.attr.attack_speed_base", 4.0D),

    /**
     * Knockback resistance
     */
    KNOCKBACK_RESISTANCE("rpgmod.attr.resistance_knockback", 0.0D);


////////////////////////////////////////////////////////////////////////////////////////////////////
// MOVEMENT

////////////////////////////////////////////////////////////////////////////////////////////////////
// COMBAT

////////////////////////////////////////////////////////////////////////////////////////////////////
// MINING


////////////////////////////////////////////////////////////////////////////////////////////////////
// IMPLEMENTATION
////////////////////////////////////////////////////////////////////////////////////////////////////

    private final @NotNull String name;
    private final @NotNull String unlocalizedName;
    private final double defaultValue; // Default "fallback" value

    PlayerAttributes(@NotNull String name, double defaultValue) {
        this.name = name;
        this.unlocalizedName = name + ".name";
        this.defaultValue = defaultValue;
    }

    @Override
    public @NotNull String getName() {
        return name;
    }

    @Override
    public @NotNull String getUnlocalizedName() {
        return unlocalizedName;
    }

    @Override
    public double getDefaultValue() {
        return defaultValue;
    }


////////////////////////////////////////////////////////////////////////////////////////////////////
// STATIC HELPERS
////////////////////////////////////////////////////////////////////////////////////////////////////

    public static double getValue(@NotNull EntityPlayer player, @NotNull IPlayerAttribute attribute) {
        IAttributeHandler handler = PlayerAttributeManager.INSTANCE.get(attribute);
        return handler != null ? handler.getValue(player) : attribute.getDefaultValue();
    }

    public static double getBaseValue(@NotNull EntityPlayer player, @NotNull IPlayerAttribute attribute) {
        IAttributeHandler handler = PlayerAttributeManager.INSTANCE.get(attribute);
        return handler != null ? handler.getBaseValue(player) : attribute.getDefaultValue();
    }

    public static void setBaseValue(@NotNull EntityPlayer player, @NotNull IPlayerAttribute attribute, double value) {
        IAttributeHandler handler = PlayerAttributeManager.INSTANCE.get(attribute);
        if (handler != null) handler.setBaseValue(player, value);
    }

    public static void applyModifier(@NotNull EntityPlayer player,
                                     @NotNull IPlayerAttribute attribute,
                                     @NotNull AttributeModifier modifier) {
        IAttributeHandler handler = PlayerAttributeManager.INSTANCE.get(attribute);
        if (handler != null) handler.applyModifier(player, modifier);
    }

    public static void removeModifier(@NotNull EntityPlayer player,
                                      @NotNull IPlayerAttribute attribute,
                                      @NotNull AttributeModifier modifier) {
        IAttributeHandler handler = PlayerAttributeManager.INSTANCE.get(attribute);
        if (handler != null) handler.removeModifier(player, modifier);
    }
}
