package fr.antbig.nms;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface FakePlayer {

	void generate(Location location, Player player);
	
	void destroy();
	
	int getId();
}
