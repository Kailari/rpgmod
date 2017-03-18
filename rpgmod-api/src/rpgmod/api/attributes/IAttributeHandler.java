package rpgmod.api.attributes;

import net.minecraft.entity.player.EntityPlayer;
import org.jetbrains.annotations.NotNull;

/**
 * Handler for getting/setting PlayerAttribute values.
 */
public interface IAttributeHandler {
    double getValue(@NotNull EntityPlayer player);

    double getBaseValue(@NotNull EntityPlayer player);

    void setBaseValue(@NotNull EntityPlayer player, double value);
}
