package rpgmod.api.player.attributes;

/**
 * Defines an xp ramp
 */
public interface IXPRamp {
    /**
     * Gets the total xp required for given level.
     *
     * @param level Level which required xp to get.
     *
     * @return XP required for the level.
     */
    int getXPRequiredForLevel(int level);

    /**
     * Gets the level the given xp value corresponds to.
     * <p>
     * NOTE FOR IMPLEMENTORS:
     * Default implementation should be sufficient, unless for some unknown reason your attributes
     * might level up to some crazy-high values. (We're talking about something at least lvl +100k)
     *
     * @param xp The xp value.
     *
     * @return The level for given amount of xp.
     */
    default int getLevelFromXP(int xp) {
        int level = 0;
        while (xp > getXPRequiredForLevel(level + 1)) level++;
        return level;
    }
}
