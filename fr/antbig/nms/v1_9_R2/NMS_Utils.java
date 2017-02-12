package fr.antbig.nms.v1_9_R2;

import fr.antbig.nms.Utils;
import net.minecraft.server.v1_9_R2.Entity;
import net.minecraft.server.v1_9_R2.EntityPlayer;
import net.minecraft.server.v1_9_R2.Vec3D;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_9_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;

/**
 * Class pour obtenir le ping d'un joueur
 * @author Antbig
 *
 *	@version 1_9_R1
 */
public class NMS_Utils implements Utils {
	
	@Override
	public double getLatency(Player player) {
		CraftPlayer cp = (CraftPlayer) Bukkit.getPlayer(player.getUniqueId());
		EntityPlayer ep = cp.getHandle(); 
		return ep.ping;
	}
	
	@Override
	public boolean inWater(Player player) {
		boolean inWater = false;
	    try {
	        Field f = Entity.class.getDeclaredField("inWater");
	        f.setAccessible(true);

	        inWater = f.getBoolean(((CraftPlayer) player).getHandle());
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }

	    return inWater;
	}
	
	@Override
	public boolean canSee(Player player, Location loc2) {
	       return ((CraftWorld) player.getLocation().getWorld()).getHandle().rayTrace(
	    		   new Vec3D(player.getLocation().getX(), player.getLocation().getY() + player.getEyeHeight(), player.getLocation().getZ()),
	    		   new Vec3D(loc2.getX(), loc2.getY(), loc2.getZ())) == null;
	}
}
