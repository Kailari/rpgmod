package rpgmod.api.player.attributes;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Attribute handler for handling player attributes linked to an Entity Attribute.
 */
public class VanillaAttributePlayerAttribute extends PlayerAttribute {
    private final IAttribute attribute;
    private final boolean register;

    /**
     * Constructs a new VanillaAttributePlayerAttribute instance, which does not automatically register
     * the internal attribute.
     *
     * @param modid        Modid of the mod defining this attribute.
     * @param registryName Registry name for this attribute.
     * @param attribute    The target attribute to link to.
     */
    public VanillaAttributePlayerAttribute(@NotNull String modid,
                                           @NotNull String registryName,
                                           @NotNull IAttribute attribute) {
        this(modid, registryName, attribute, false);
    }

    /**
     * Constructs a new VanillaAttributePlayerAttribute instance.
     *
     * @param modid        Modid of the mod defining this attribute.
     * @param registryName Registry name for this attribute.
     * @param attribute    The target attribute to link to.
     * @param register     Should the IAttribute be automatically registered if it does not exist?
     */
    public VanillaAttributePlayerAttribute(@NotNull String modid,
                                           @NotNull String registryName,
                                           @NotNull IAttribute attribute,
                                           boolean register) {
        super(modid, registryName);
        this.attribute = attribute;
        this.register = register;
    }

    // region PlayerAttribute Implementation

    @Override
    public double getValue(@NotNull EntityPlayer player) {
        IAttributeInstance instance = getInstance(player);
        return instance == null ? attribute.getDefaultValue() : instance.getAttributeValue();
    }

    @Override
    public double getBaseValue(@NotNull EntityPlayer player) {
        IAttributeInstance instance = getInstance(player);
        return instance == null ? attribute.getDefaultValue() : instance.getBaseValue();
    }

    @Override
    public void setBaseValue(@NotNull EntityPlayer player, double value) {
        IAttributeInstance instance = getInstance(player);
        if (instance != null) instance.setBaseValue(value);
    }

    @Override
    public void applyModifier(@NotNull EntityPlayer player, @NotNull AttributeModifier modifier) {
        IAttributeInstance instance = getInstance(player);
        if (instance != null && !instance.hasModifier(modifier)) instance.applyModifier(modifier);
    }

    @Override
    public void removeModifier(@NotNull EntityPlayer player, @NotNull AttributeModifier modifier) {
        IAttributeInstance instance = getInstance(player);
        if (instance != null && instance.hasModifier(modifier)) instance.removeModifier(modifier);
    }

    // endregion

    // region Helpers

    @Nullable
    private IAttributeInstance getInstance(@NotNull EntityPlayer player) {
        IAttributeInstance instance = player.getEntityAttribute(attribute);
        if (instance == null && register) {
            instance = player.getAttributeMap().registerAttribute(attribute);
        }
        return instance;
    }

    // endregion Helpers
}
