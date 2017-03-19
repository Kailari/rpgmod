package rpgmod.common.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rpgmod.api.player.PlayerAttributes;
import rpgmod.common.Attributes;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Command for controlling attributes.
 */
public class CommandAttribute extends CommandBase {
    @NotNull
    @Override
    public String getName() {
        return "pattr";
    }

    @NotNull
    @Override
    public String getUsage(@NotNull ICommandSender sender) {
        return "commands.pattr.usage";
    }

    @Override
    public void execute(@NotNull MinecraftServer server,
                        @NotNull ICommandSender sender,
                        @NotNull String[] args)
            throws CommandException {
        if (args.length < 1) {
            sender.sendMessage(new TextComponentTranslation("commands.pattr.err.no_sub_command"));
            return;
        }

        switch (args[0].toLowerCase()) {
            case "set":
                if (args.length < 3) {
                    sender.sendMessage(new TextComponentTranslation("commands.pattr.err.too_few_args", args.length, 3));
                    return;
                }
                executeSet(sender, args[1], args[2]);
                break;
            case "show":
                if (args.length < 2) {
                    sender.sendMessage(new TextComponentTranslation("commands.pattr.err.too_few_args", args.length, 2));
                    return;
                }
                executeShow(sender, args[1]);
                break;
            case "list":
                executeList(sender);
                break;
        }
    }

    private void executeShow(@NotNull ICommandSender sender, @NotNull String attrKey) {
        EntityPlayer player = getPlayer(sender);
        if (player == null) return;

        Attributes.Player attribute = findAttribute(sender, attrKey);
        if (attribute == null) return;

        double value = PlayerAttributes.getValue(player, attribute);
        double base = PlayerAttributes.getBaseValue(player, attribute);

        sender.sendMessage(new TextComponentTranslation("commands.pattr.show.result", attrKey, value, base));
    }

    private void executeSet(@NotNull ICommandSender sender, @NotNull String attrKey, @NotNull String attrVal) {
        EntityPlayer player = getPlayer(sender);
        if (player == null) return;

        Attributes.Player attribute = findAttribute(sender, attrKey);
        if (attribute == null) return;

        double value;
        try {
            value = Double.parseDouble(attrVal);
        } catch (NumberFormatException e) {
            sender.sendMessage(new TextComponentTranslation("commands.pattr.set.err.number_format"));
            return;
        }

        PlayerAttributes.setBaseValue(player, attribute, value);
        sender.sendMessage(new TextComponentTranslation(
                "commands.pattr.set.result",
                attrKey,
                value,
                PlayerAttributes.getValue(player, attribute)));
    }

    private void executeList(@NotNull ICommandSender sender) {
        for (Attributes.Player attribute : Attributes.Player.values()) {
            sender.sendMessage(new TextComponentString(attribute.getName()));
        }
    }

    @Nullable
    private EntityPlayer getPlayer(@NotNull ICommandSender sender) {
        try {
            return getCommandSenderAsPlayer(sender);
        } catch (PlayerNotFoundException e) {
            sender.sendMessage(new TextComponentTranslation("commands.pattr.err.player_not_found"));
            return null;
        }
    }

    private Attributes.Player findAttribute(ICommandSender sender, @NotNull String attrKey) {
        for (Attributes.Player attribute : Attributes.Player.values()) {
            if (attribute.getName().equalsIgnoreCase(attrKey)) {
                return attribute;
            }
        }
        sender.sendMessage(new TextComponentTranslation("commands.pattr.err.attr_not_found"));
        return null;
    }

    @NotNull
    @Override
    public List<String> getTabCompletions(@NotNull MinecraftServer server,
                                          @NotNull ICommandSender sender,
                                          @NotNull String[] args,
                                          @Nullable BlockPos targetPos) {
        if (args.length == 1) {
            return getListOfStringsMatchingLastWord(args, "set", "show", "list");
        } else if (args.length == 2) {
            String subCmd = args[0];
            if (subCmd.equals("set") || subCmd.equals("show")) {
                return getListOfStringsMatchingLastWord(args,
                        Arrays.stream(Attributes.Player.values())
                                .map(Attributes.Player::getName)
                                .collect(Collectors.toList()));
            }
        }
        return super.getTabCompletions(server, sender, args, targetPos);
    }
}
