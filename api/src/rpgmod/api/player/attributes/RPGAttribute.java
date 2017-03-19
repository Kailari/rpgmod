package rpgmod.api.player.attributes;

import net.minecraftforge.fml.common.registry.IForgeRegistryEntry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Base for RPGAttributes.
 */
public class RPGAttribute extends IForgeRegistryEntry.Impl<RPGAttribute> {
    private final @NotNull String unlocalizedName;
    private final @NotNull IXPRamp xpRamp;
    private final @Nullable ModifierInfo[] modifiers;

    public RPGAttribute(@NotNull String modid,
                        @NotNull String registryName,
                        @NotNull IXPRamp xpRamp,
                        @Nullable ModifierInfo... modifiers) {
        this.xpRamp = xpRamp;
        this.modifiers = modifiers;
        this.unlocalizedName = modid + ":rpgattr." + registryName + ".name";
        setRegistryName(modid, registryName);
    }

    /**
     * Gets the unlocalized name for this attribute.
     * <p>
     * Format:<br>
     * <code>[modid]:rpgattr.[registry name].name</code>
     * <p>
     * Example:<br>
     * <code>
     * rpgmod:rpgattr.strength.name<br>
     * rpgmod:rpgattr.wisdom.name
     * </code>
     *
     * @return The unlocalized name.
     */
    @NotNull
    public String getUnlocalizedName() {
        return this.unlocalizedName;
    }

    /**
     * Gets the xp ramp for this attribute.
     *
     * @return The xp ramp.
     */
    @NotNull
    public IXPRamp getXpRamp() {
        return this.xpRamp;
    }

    /**
     * Gets the modifiers for this attribute.
     *
     * @return Array containing the modifiers. Null if this attribute has none.
     */
    @Nullable
    public ModifierInfo[] getModifiers() {
        return this.modifiers;
    }

    // TODO: Methods for getting/setting
    // XXX: If implementation hiding is desired (as it most likely is), a separate manager-class is needed
}
