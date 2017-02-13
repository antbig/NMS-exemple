package fr.antbig.nms;

import org.bukkit.Bukkit;

public class NMS {


    public static Utils getUtils() {
        String version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3] + ".";
        String name = "fr.antbig.nms." + version + "NMS_Utils";
        try {
            Class<?> nmsClass = Class.forName(name);
            return (Utils)(nmsClass).newInstance() ;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static FakePlayer getFakePlayer() {
        String version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3] + ".";
        String name = "fr.antbig.nms." + version + "NMS_FakePlayer";
        try {
            Class<?> nmsClass = Class.forName(name);
            return (FakePlayer)(nmsClass).newInstance() ;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}
