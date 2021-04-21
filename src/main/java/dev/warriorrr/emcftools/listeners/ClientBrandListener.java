package dev.warriorrr.emcftools.listeners;

import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import static dev.warriorrr.emcftools.EMCFTools.clientBrands;

public class ClientBrandListener implements PluginMessageListener {
    @Override
    public void onPluginMessageReceived(String channel, Player p, byte[] msg) {
        String clientBrand = new String(msg).substring(1);
        clientBrands.put(p.getUniqueId(), clientBrand);
    }
}
