package rpgmod.api.attributes.player;

import org.jetbrains.annotations.NotNull;

/**
 * Player attribute.
 */
public interface IPlayerAttribute {
    @NotNull String getName();

    @NotNull String getUnlocalizedName();

    double getDefaultValue();
}
