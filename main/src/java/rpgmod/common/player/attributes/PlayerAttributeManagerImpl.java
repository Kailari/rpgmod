package rpgmod.common.player.attributes;

import net.minecraft.entity.SharedMonsterAttributes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rpgmod.api.attributes.IAttributeHandler;
import rpgmod.api.attributes.IPlayerAttribute;
import rpgmod.api.attributes.PlayerAttributeManager;
import rpgmod.api.attributes.PlayerAttributes;

import java.util.HashMap;

/**
 * Implementation of the attribute handler manager.
 */
public final class PlayerAttributeManagerImpl extends PlayerAttributeManager {
    private final HashMap<String, IAttributeHandler> handlers = new HashMap<>();

    public PlayerAttributeManagerImpl() {
        registerDefaultHandlers();
    }

    private void registerDefaultHandlers() {
        bind(PlayerAttributes.MAX_HP, new LinkAttributeHandler(SharedMonsterAttributes.MAX_HEALTH));
        bind(PlayerAttributes.LUCK, new LinkAttributeHandler(SharedMonsterAttributes.LUCK));
        bind(PlayerAttributes.ARMOR, new LinkAttributeHandler(SharedMonsterAttributes.ARMOR));
        bind(PlayerAttributes.ARMOR_TOUGHNESS, new LinkAttributeHandler(SharedMonsterAttributes.ARMOR_TOUGHNESS));
        bind(PlayerAttributes.ATTACK_DAMAGE, new LinkAttributeHandler(SharedMonsterAttributes.ATTACK_DAMAGE));
        bind(PlayerAttributes.ATTACK_SPEED, new LinkAttributeHandler(SharedMonsterAttributes.ATTACK_SPEED));
        bind(PlayerAttributes.KNOCKBACK_RESISTANCE, new LinkAttributeHandler(SharedMonsterAttributes.KNOCKBACK_RESISTANCE));
        bind(PlayerAttributes.MOVEMENT_SPEED, new LinkAttributeHandler(SharedMonsterAttributes.MOVEMENT_SPEED));
    }

    @Override
    public void bind(@NotNull IPlayerAttribute attribute, @NotNull IAttributeHandler handler) {
        handlers.put(key(attribute), handler);
    }

    @Nullable
    @Override
    public IAttributeHandler get(@NotNull IPlayerAttribute attribute) {
        return get(key(attribute));
    }

    @Nullable
    @Override
    public IAttributeHandler get(@NotNull String key) {
        return handlers.get(key.toLowerCase());
    }

    private String key(IPlayerAttribute attribute) {
        return attribute.getName().toLowerCase();
    }
}
