package io.github.zohiu.smplyblockcatapult;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class FallDamage implements Listener {
    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        if (!SMPlyBlockCatapult.getInstance().falldamage) {
            if (event.getEntity() instanceof Player) {
                if (event.getCause() == EntityDamageEvent.DamageCause.FALL) {
                    Location loc = event.getEntity().getLocation().subtract(0, 1, 0);
                    if (event.getEntity().getWorld().getBlockAt(loc).getBlockData().getMaterial() == SMPlyBlockCatapult.getInstance().block) {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }
}
