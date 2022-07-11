package io.github.zohiu.smplyblockcatapult;

import org.bukkit.*;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.util.Vector;

import java.util.Objects;

public class WalkOnBlock implements Listener {
    @EventHandler
    public void onPlayerMoveEvent(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location loc = player.getLocation().subtract(0, 1, 0);

        if (player.getWorld().getBlockAt(loc).getBlockData().getMaterial() == SMPlyBlockCatapult.getInstance().block) {
            if (SMPlyBlockCatapult.getInstance().particles) {
                player.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, player.getLocation(), 50, 0, 1, 0, 0.1, null);
                player.getWorld().spawnParticle(Particle.SPELL_WITCH, player.getLocation(), 50, 0, 0, 0, 0.1, null);
                player.getWorld().spawnParticle(Particle.TOTEM, player.getLocation(), 50, 0, 1, 0, 0.5, null);
                player.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, player.getLocation(), 35, 0, 0, 0, 0.1, null);
                player.getWorld().spawnParticle(Particle.FLASH, player.getLocation(), 1, 0, 1, 0, 0.1, null);
            }

            if (SMPlyBlockCatapult.getInstance().fireworks) {
                Firework firework = player.getWorld().spawn(player.getLocation(), Firework.class);
                FireworkMeta meta = firework.getFireworkMeta();
                meta.addEffect(FireworkEffect.builder().withColor(Color.PURPLE).withColor(Color.LIME).withColor(Color.AQUA).with(FireworkEffect.Type.STAR).withFlicker().withTrail().build());
                meta.setPower(0);
                firework.setFireworkMeta(meta);
            }

            player.setVelocity(new Vector(0.0, SMPlyBlockCatapult.getInstance().force, 0.0));

        }
    }
}
