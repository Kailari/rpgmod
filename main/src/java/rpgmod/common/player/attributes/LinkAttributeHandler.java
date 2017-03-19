package rpgmod.common.player.attributes;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rpgmod.api.attributes.player.IAttributeHandler;

/**
 * Attribute handler for handling attributes linked to an EntityAttribute.
 */
public class LinkAttributeHandler implements IAttributeHandler {
    private final IAttribute attribute;
    private final boolean autoRegister;

    public LinkAttributeHandler(@NotNull IAttribute attribute) {
        this(attribute, false);
    }

    public LinkAttributeHandler(@NotNull IAttribute attribute, boolean autoRegister) {
        this.attribute = attribute;
        this.autoRegister = autoRegister;
    }

    // region IAttributeHandler Implementation

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

    @Nullable
    private IAttributeInstance getInstance(@NotNull EntityPlayer player) {
        IAttributeInstance instance = player.getEntityAttribute(attribute);
        if (instance == null && autoRegister) {
            instance = player.getAttributeMap().registerAttribute(attribute);
        }
        return instance;
    }
}
