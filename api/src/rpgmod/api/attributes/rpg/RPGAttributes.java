package rpgmod.api.attributes.rpg;

import net.minecraft.entity.player.EntityPlayer;
import org.jetbrains.annotations.NotNull;

/**
 * Extension to PlayerAttributes which provides a bit simpler and more "RPG-like" attributes.
 */
public enum RPGAttributes {
    ;

    private final @NotNull IXPRamp xpRamp;

    RPGAttributes(@NotNull IXPRamp xpRamp) {
        this.xpRamp = xpRamp;
    }

    public static int getPlayerLevel(@NotNull EntityPlayer player, @NotNull IRPGAttribute attribute) {
        return 0;
    }
}
