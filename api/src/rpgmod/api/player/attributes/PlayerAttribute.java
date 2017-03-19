package rpgmod.api.player.attributes;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.registry.IForgeRegistryEntry;
import org.jetbrains.annotations.NotNull;

/**
 * Player attribute registry entry.
 */
public abstract class PlayerAttribute extends IForgeRegistryEntry.Impl<PlayerAttribute> {
    private final @NotNull String unlocalizedName;

    public PlayerAttribute(@NotNull String modid, @NotNull String registryName) {
        this.unlocalizedName = modid + ":pattr." + registryName + ".name";
        setRegistryName(modid, registryName);
    }

    /**
     * Gets the unlocalized name for this attribute.
     * <p>
     * Format:<br>
     * <code>[modid]:pattr.[registry name].name</code>
     * <p>
     * Example:<br>
     * <code>
     * rpgmod:pattr.max_hp.name<br>
     * rpgmod:pattr.movement_speed_base.name
     * </code>
     *
     * @return The unlocalized name.
     */
    public @NotNull String getUnlocalizedName() {
        return unlocalizedName;
    }

    // region Abstract methods

    /**
     * Gets the value of given attribute. Modifiers are applied.
     *
     * @param player Player whose attributes to use.
     *
     * @return Value of the attribute for given player.
     */
    public abstract double getValue(@NotNull EntityPlayer player);

    /**
     * Gets the unmodified base value of given attribute.
     *
     * @param player Player whose attributes to use.
     *
     * @return Base value of the attribute for given player.
     */
    public abstract double getBaseValue(@NotNull EntityPlayer player);

    /**
     * Sets the base value of given attribute.
     *
     * @param player Player whose attributes to use.
     * @param value  The new value.
     */
    public abstract void setBaseValue(@NotNull EntityPlayer player, double value);

    /**
     * Applies a new attribute modifier to the given attribute.
     *
     * @param player   Player whose attributes to use.
     * @param modifier The modifier to apply.
     */
    public abstract void applyModifier(@NotNull EntityPlayer player, @NotNull AttributeModifier modifier);

    /**
     * Removes the attribute modifier from the given attribute.
     *
     * @param player   Player whose attributes to use.
     * @param modifier The modifier to remove.
     */
    public abstract void removeModifier(@NotNull EntityPlayer player, @NotNull AttributeModifier modifier);

    // endregion Abstract methods
}
