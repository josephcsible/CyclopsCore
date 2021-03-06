package org.cyclops.cyclopscore.command;

import com.mojang.authlib.GameProfile;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.FMLCommonHandler;
import org.cyclops.cyclopscore.helper.L10NHelpers;
import org.cyclops.cyclopscore.init.ModBase;

import java.util.List;

/**
 * Command for igniting players by name.
 * @author rubensworks
 *
 */
public class CommandIgnite extends CommandMod {

    public static final String NAME = "ignite";

    public CommandIgnite(ModBase mod) {
        super(mod, NAME);
    }

    @Override
    public String getFullCommand() {
        return super.getFullCommand() + " " + NAME;
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] parts, BlockPos blockPos) {
        return parts.length >= 1 ?
                CommandBase.getListOfStringsMatchingLastWord(parts, FMLCommonHandler.instance().getMinecraftServerInstance().getOnlinePlayerNames()) : null;
    }

    @Override
    public void processCommandHelp(ICommandSender icommandsender, String[] astring) throws CommandException {
        throw new WrongUsageException("/" + getFullCommand() + " <player> [duration]");
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] parts) throws CommandException {
        if (parts.length < 1 || parts.length > 2 || parts[0].length() == 0) {
            processCommandHelp(sender, parts);
        }

        if(parts.length >= 1 && parts[0].length() > 0) {
            MinecraftServer minecraftserver = FMLCommonHandler.instance().getMinecraftServerInstance();
            GameProfile gameprofile = minecraftserver.getPlayerProfileCache().getGameProfileForUsername(parts[0]);

            if (gameprofile == null) {
                sender.sendMessage(new TextComponentString(L10NHelpers.localize("chat.cyclopscore.command.invalidPlayer", parts[0])));
            } else {
                EntityPlayerMP player = minecraftserver.getPlayerList().getPlayerByUsername(parts[0]);
                int duration = 2;
                if(parts.length > 1) {
                    try {
                        duration = Integer.parseInt(parts[1]);
                    } catch (NumberFormatException e) {
                        // Invalid duration amount, ignore.
                    }
                }
                player.setFire(duration);
                sender.sendMessage(new TextComponentString(L10NHelpers.localize("chat.cyclopscore.command.ignitedPlayer", parts[0], duration)));
            }
        }
    }
}
