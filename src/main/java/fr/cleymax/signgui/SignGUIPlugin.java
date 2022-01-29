package fr.cleymax.signgui;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import net.minecraft.core.BlockPosition;
import net.minecraft.network.protocol.game.PacketPlayInUpdateSign;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_18_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class SignGUIPlugin extends JavaPlugin implements Listener {

    private static SignGUIPlugin instance;

    private Map<UUID, SignGUI> guiMap;

    @Override
    public void onEnable() {
        instance = this;
        guiMap = new HashMap<>();
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            var channel = ((CraftPlayer) player).getHandle().b.a.k;
            channel.eventLoop().submit(() -> channel.pipeline().remove("SignGUI"));
        }
    }

    public static SignGUI newSignGUI(SignClickCompleteHandler handler) {
        return new SignGUI(instance, handler);
    }

    /**
     * Add New gui
     *
     * @param uuid    - UUID of the player
     * @param signGUI - {@link SignGUI} instance
     */
    void addGui(UUID uuid, SignGUI signGUI) {
        this.guiMap.put(uuid, signGUI);
    }

    @EventHandler()
    public void onPlayerJoin(PlayerJoinEvent event) {
        final var player = event.getPlayer();
        ChannelDuplexHandler channelDuplexHandler = new ChannelDuplexHandler() {
            @Override
            public void channelRead(ChannelHandlerContext ctx, Object packet) throws Exception {
                if (packet instanceof PacketPlayInUpdateSign) {
                    var inUpdateSign = (PacketPlayInUpdateSign) packet;
                    if (guiMap.containsKey(player.getUniqueId())) {
                        var signGUI = guiMap.get(player.getUniqueId());

                        BlockPosition blockPosition = SignReflection.getValue(inUpdateSign, "b");
                        String[] lines = SignReflection.getValue(inUpdateSign, "c");

                        signGUI.getCompleteHandler().onAnvilClick(new SignCompleteEvent(player, blockPosition, lines));
                        guiMap.remove(player.getUniqueId());
                    }
                }
                super.channelRead(ctx, packet);
            }
        };
        final ChannelPipeline pipeline = ((CraftPlayer) player).getHandle().b.a.k.pipeline();
        pipeline.addBefore("packet_handler", "SignGUI", channelDuplexHandler);
    }

    @EventHandler()
    public void onPlayerQuit(PlayerQuitEvent event) {
        final var channel = ((CraftPlayer) event.getPlayer()).getHandle().b.a.k;
        channel.eventLoop().submit(() -> channel.pipeline().remove("SignGUI"));
        guiMap.remove(event.getPlayer().getUniqueId());
    }

}
