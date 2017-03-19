package rpgmod.api.attributes.rpg;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.NotNull;
import rpgmod.api.attributes.player.IPlayerAttribute;
import rpgmod.api.attributes.player.PlayerAttributes;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Handles applying the modifiers from RPGAttributes to PlayerAttributes.
 */
public class RPGAttributeLink {
    private final @NotNull IRPGAttribute source;
    private final ModifierInfo[] modifiers;

    public IRPGAttribute getSource() {
        return this.source;
    }

    public RPGAttributeLink(@NotNull IRPGAttribute source, ModifierInfo... modifiers) {
        this.source = source;
        this.modifiers = modifiers;
    }

    public void applyTo(@NotNull EntityPlayer player) {
        for (ModifierInfo info : modifiers) {
            int level = RPGAttributes.getPlayerLevel(player, getSource());

            // If we have been applied to the player already, remove the modifiers.
            AttributeModifier modifier = info.getForLevel(level);

            // Apply new modifiers
            PlayerAttributes.removeModifier(player, info.target, modifier);
        }
    }

    public static class ModifierInfo {
        private final @NotNull IPlayerAttribute target;
        private final @NotNull UUID modifierUUID;

        private final double base;
        private final double gainPerLevel;
        private final double min;
        private final double max;

        private ModifierInfo(@NotNull IPlayerAttribute target,
                             double base,
                             double gainPerLevel,
                             double min,
                             double max) {
            this.target = target;
            this.base = base;
            this.gainPerLevel = gainPerLevel;
            this.min = min;
            this.max = max;

            this.modifierUUID = MathHelper.getRandomUUID(ThreadLocalRandom.current());
        }

        public AttributeModifier getForLevel(int level) {
            return new AttributeModifier(
                    modifierUUID,
                    target.getName() + "_RPGAttrModifier",
                    getValueForLevel(level),
                    0).setSaved(false);
        }

        private double getValueForLevel(int level) {
            // value = base + (gain * max(0, level - 1))
            return MathHelper.clamp(base + (gainPerLevel * Math.max(0, level - 1)), min, max);
        }
    }
}
