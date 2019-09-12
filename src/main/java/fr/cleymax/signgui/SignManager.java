package fr.cleymax.signgui;

import io.netty.channel.Channel;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import net.minecraft.server.v1_14_R1.BlockPosition;
import net.minecraft.server.v1_14_R1.PacketPlayInUpdateSign;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import java.beans.ConstructorProperties;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * File <b>SignManager</b> located on fr.cleymax.signgui SignManager is a part of SignGUI.
 * <p>
 * Copyright (c) 2019 SignGUI .
 * <p>
 *
 * @author Clément P. (Cleymax), {@literal <cleymaxpro@gmail.com>} Created the 11/09/2019 at 23:17
 */

public final class SignManager {

	private final Plugin             plugin;
	private final Map<UUID, SignGUI> guiMap;
	private       PluginManager      pluginManager;

	@ConstructorProperties({"plugin"})
	public SignManager(Plugin plugin)
	{
		this.plugin = plugin;
		this.guiMap = new HashMap<>();
		this.pluginManager = Bukkit.getPluginManager();
	}

	public void init()
	{
		this.pluginManager.registerEvents(new SignListener(), this.plugin);
	}

	private class SignListener implements Listener {

		@EventHandler()
		public void onPlayerJoin(PlayerJoinEvent event)
		{
			final Player player = event.getPlayer();
			ChannelDuplexHandler channelDuplexHandler = new ChannelDuplexHandler() {
				@Override
				public void channelRead(ChannelHandlerContext ctx, Object packet) throws Exception
				{
					if (packet instanceof PacketPlayInUpdateSign)
					{
						PacketPlayInUpdateSign inUpdateSign = (PacketPlayInUpdateSign) packet;
						if (guiMap.containsKey(player.getUniqueId()))
						{
							SignGUI signGUI = guiMap.get(player.getUniqueId());

							BlockPosition blockPosition = SignReflection.getValue(inUpdateSign, "a");
							String[]      lines         = SignReflection.getValue(inUpdateSign, "b");

							signGUI.getCompleteHandler().onAnvilClick(new SignCompleteEvent(player, blockPosition, lines));
						}
					}
					super.channelRead(ctx, packet);
				}
			};
			final ChannelPipeline pipeline = ((CraftPlayer) player).getHandle().playerConnection.networkManager.channel.pipeline();
			pipeline.addBefore("packet_handler", player.getName(), channelDuplexHandler);
		}

		@EventHandler()
		public void onPlayerQuit(PlayerQuitEvent event)
		{
			final Channel channel = ((CraftPlayer) event.getPlayer()).getHandle().playerConnection.networkManager.channel;
			channel.eventLoop().submit(() -> channel.pipeline().remove(event.getPlayer().getName()));
		}
	}

	public void addGui(UUID uuid, SignGUI signGUI)
	{
		this.guiMap.put(uuid, signGUI);
	}

	protected Map<UUID, SignGUI> getGUIMap()
	{
		return guiMap;
	}
}
