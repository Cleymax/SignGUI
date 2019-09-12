package fr.cleymax.signgui;

import net.minecraft.server.v1_14_R1.*;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_14_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_14_R1.block.CraftSign;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_14_R1.util.CraftMagicNumbers;
import org.bukkit.entity.Player;

/**
 * File <b>SignGUI</b> located on fr.cleymax.signgui SignGUI is a part of SignGUI.
 * <p>
 * Copyright (c) 2019 SignGUI .
 * <p>
 *
 * @author Clément P. (Cleymax), {@literal <cleymaxpro@gmail.com>} Created the 11/09/2019 at 23:32
 */

public final class SignGUI {

	private final SignManager              signManager;
	private final SignClickCompleteHandler completeHandler;
	private       Player                   player;
	private       String[]                 lines;

	public SignGUI(SignManager signManager, SignClickCompleteHandler completeHandler)
	{
		this.signManager = signManager;
		this.completeHandler = completeHandler;
		this.lines = new String[4];
	}

	public SignGUI withLines(String... lines)
	{
		if (lines.length != 4)
		{
			throw new IllegalArgumentException("Must have at least 4 lines");
		}

		this.lines = lines;
		return this;
	}

	public void open(Player player)
	{
		this.player = player;

		BlockPosition blockPosition = new BlockPosition(player.getLocation().getBlockX(), 1, player.getLocation().getBlockZ());

		PacketPlayOutBlockChange packet = new PacketPlayOutBlockChange(((CraftWorld) player.getWorld()).getHandle(), blockPosition);
		packet.block = CraftMagicNumbers.getBlock(Material.OAK_SIGN, (byte) 0);
		sendPacket(packet);

		IChatBaseComponent[] components = CraftSign.sanitizeLines(lines);
		TileEntitySign       sign       = new TileEntitySign();
		sign.setPosition(new BlockPosition(blockPosition.getX(), blockPosition.getY(), blockPosition.getZ()));
		System.arraycopy(components, 0, sign.lines, 0, sign.lines.length);
		sendPacket(sign.getUpdatePacket());

		PacketPlayOutOpenSignEditor outOpenSignEditor = new PacketPlayOutOpenSignEditor(blockPosition);
		sendPacket(outOpenSignEditor);

		this.signManager.addGui(player.getUniqueId(), this);
	}

	private void sendPacket(Packet<?> packet)
	{
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
	}

	SignClickCompleteHandler getCompleteHandler()
	{
		return completeHandler;
	}
}
