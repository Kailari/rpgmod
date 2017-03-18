package rpgmod.api.attributes;

import org.jetbrains.annotations.NotNull;

/**
 * Player attribute.
 */
public interface IPlayerAttribute {
    @NotNull String getName();

    @NotNull String getUnlocalizedName();

    double getDefaultValue();
}
