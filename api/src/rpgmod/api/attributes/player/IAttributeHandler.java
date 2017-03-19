package rpgmod.api.attributes.player;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import org.jetbrains.annotations.NotNull;

/**
 * Handler for getting/setting PlayerAttribute values.
 */
public interface IAttributeHandler {
    double getValue(@NotNull EntityPlayer player);

    double getBaseValue(@NotNull EntityPlayer player);

    void setBaseValue(@NotNull EntityPlayer player, double value);

    void applyModifier(@NotNull EntityPlayer player, @NotNull AttributeModifier modifier);

    void removeModifier(@NotNull EntityPlayer player, @NotNull AttributeModifier modifier);
}
