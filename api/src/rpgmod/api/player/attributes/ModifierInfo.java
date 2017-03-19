package rpgmod.api.player.attributes;

import net.minecraft.util.math.MathHelper;
import org.apache.commons.lang3.Validate;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Required info for constructing player attribute modifiers from RPGAttributes.
 */
public class ModifierInfo {
    private final @NotNull PlayerAttribute target;
    private final @NotNull UUID modifierUUID;

    private final double base;
    private final double gainPerLevel;
    private final double min;
    private final double max;

    /**
     * Gets the targeted PlayerAttribute
     *
     * @return The target attribute.
     */
    @NotNull
    public PlayerAttribute getTarget() {
        return this.target;
    }

    /**
     * Gets the UUID for AttributeModifier created from this info.
     *
     * @return The modifier UUID.
     */
    @NotNull
    public UUID getModifierUUID() {
        return this.modifierUUID;
    }

    /**
     * Gets the base modifier value. (Value at level one)
     *
     * @return Modifier value at level one.
     */
    public double getBase() {
        return this.base;
    }

    /**
     * Gets the amount the modifier grows per level.
     *
     * @return The gain per level.
     */
    public double getGainPerLevel() {
        return this.gainPerLevel;
    }

    /**
     * Gets the minimum modifier value.
     *
     * @return The minimum modifier value.
     */
    public double getMin() {
        return this.min;
    }

    /**
     * Gets the maximum modifier value.
     *
     * @return The maximum modifier value.
     */
    public double getMax() {
        return this.max;
    }

    /**
     * Gets the modifier value for given level.
     *
     * @return The modifier value.
     */
    public double getValueForLevel(int level) {
        Validate.isTrue(level > 0);
        return MathHelper.clamp(getBase() + (getGainPerLevel() * (level - 1)), getMin(), getMax());
    }

    /**
     * Constructs a new modifier info.
     *
     * @param target       The target attribute.
     * @param base         Modifier value at level one.
     * @param gainPerLevel Modifier value gained per level.
     * @param min          Minimum modifier value.
     * @param max          Maximum modifier value.
     */
    public ModifierInfo(@NotNull PlayerAttribute target,
                        double base,
                        double gainPerLevel,
                        double min,
                        double max) {
        this.target = target;
        this.base = base;
        this.gainPerLevel = gainPerLevel;
        this.min = min;
        this.max = max;

        // Get random UUID
        this.modifierUUID = MathHelper.getRandomUUID(ThreadLocalRandom.current());
    }
}
