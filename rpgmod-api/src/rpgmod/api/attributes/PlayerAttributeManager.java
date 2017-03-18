package rpgmod.api.attributes;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Manager for player attribute handlers.
 */
public abstract class PlayerAttributeManager {
    public static PlayerAttributeManager INSTANCE;

    /**
     * Binds the given handler to an attribute.
     *
     * @param attribute Attribute to bind to.
     * @param handler   Handler to bind.
     */
    public abstract void bind(@NotNull IPlayerAttribute attribute, @NotNull IAttributeHandler handler);

    /**
     * Gets the handler bound to given attribute.
     *
     * @param attribute Attribute which bound handler to get.
     *
     * @return The bound handler or a dummy-handler if such does not exists.
     */
    @Nullable
    public abstract IAttributeHandler get(@NotNull IPlayerAttribute attribute);

    @Nullable
    public abstract IAttributeHandler get(@NotNull String key);
}
