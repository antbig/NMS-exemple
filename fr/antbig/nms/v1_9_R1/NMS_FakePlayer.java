package fr.antbig.nms.v1_9_R1;

import java.util.UUID;

import net.minecraft.server.v1_9_R1.EntityPlayer;
import net.minecraft.server.v1_9_R1.MinecraftServer;
import net.minecraft.server.v1_9_R1.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_9_R1.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_9_R1.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_9_R1.PacketPlayOutPlayerInfo.EnumPlayerInfoAction;
import net.minecraft.server.v1_9_R1.PlayerConnection;
import net.minecraft.server.v1_9_R1.PlayerInteractManager;
import net.minecraft.server.v1_9_R1.WorldServer;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_9_R1.CraftServer;
import org.bukkit.craftbukkit.v1_9_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_9_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.mojang.authlib.GameProfile;

import fr.antbig.nms.FakePlayer;

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
