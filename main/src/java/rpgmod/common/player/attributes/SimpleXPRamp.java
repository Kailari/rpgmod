package rpgmod.common.player.attributes;

import net.minecraft.util.math.MathHelper;
import org.apache.commons.lang3.Validate;
import rpgmod.api.player.attributes.IXPRamp;

/**
 * Simple XP ramp.
 * <p>
 * XP is calculated using:<br/>
 * <code>
 * let xpForLevel(1) = base
 * <br/>
 * let level > 0
 * <br/>
 * xpForLevel(level) = xpForLevel(level - 1) + xpForLevel(level - 1) * levelMultiplier
 * <br/>
 * </code>
 * </p>
 */
public class SimpleXPRamp implements IXPRamp {
    private final double baseLevelMultiplier;
    private final int base;

    public double getLevelMultiplier() {
        return this.baseLevelMultiplier;
    }

    public SimpleXPRamp(double levelMultiplier, int base) {
        this.baseLevelMultiplier = levelMultiplier;
        this.base = base;
    }

    @Override
    public int getXPRequiredForLevel(int level) {
        Validate.isTrue(level > 0);
        if (level == 1) {
            return base;
        } else {
            int requiredForPrevious = getXPRequiredForLevel(level - 1);
            return MathHelper.ceil(requiredForPrevious + requiredForPrevious * getLevelMultiplier());
        }
    }
}
