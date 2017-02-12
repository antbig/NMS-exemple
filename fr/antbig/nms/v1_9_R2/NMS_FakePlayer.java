package fr.antbig.nms.v1_9_R2;

import com.mojang.authlib.GameProfile;
import fr.antbig.nms.FakePlayer;
import net.minecraft.server.v1_9_R2.*;
import net.minecraft.server.v1_9_R2.PacketPlayOutPlayerInfo.EnumPlayerInfoAction;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_9_R2.CraftServer;
import org.bukkit.craftbukkit.v1_9_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class NMS_FakePlayer implements FakePlayer {
	
	int id;
	Player player;

	@Override
	public void generate(Location location, Player player) {
		this.player = player;
		MinecraftServer nmsServer = ((CraftServer) Bukkit.getServer()).getServer();
		WorldServer nmsWorld = ((CraftWorld) location.getWorld()).getHandle();
		EntityPlayer npc = new EntityPlayer(nmsServer, nmsWorld, new GameProfile(UUID.randomUUID(), "CheatOs"), new PlayerInteractManager(nmsWorld));
		npc.setLocation(location.getX(), location.getY(), location.getZ(), 0F, 0F);
		npc.setHealth((float) 0.5);
		this.id = npc.getId();
		/*
		 * on affiche le fakeplayer au joueur
		 */
		PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
		connection.sendPacket(new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.ADD_PLAYER, npc));
		connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
		//on le retire de la tablist
		connection.sendPacket(new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.REMOVE_PLAYER, npc));
	}

	@Override
	public void destroy() {
		PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
		connection.sendPacket(new PacketPlayOutEntityDestroy(id));
	}

	@Override
	public int getId() {
		return this.id;
	}

}
