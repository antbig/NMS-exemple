package fr.antbig.nms;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface Utils {
	double getLatency(Player player);
	
	boolean inWater(Player player);
	
	boolean canSee(Player player, Location loc2);

}
