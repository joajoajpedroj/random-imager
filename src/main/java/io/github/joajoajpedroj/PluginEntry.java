package io.github.joajoajpedroj;

import java.io.File;

import com.destroystokyo.paper.event.server.PaperServerListPingEvent;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.CachedServerIcon;

import net.kyori.adventure.text.Component;

/**
 * This plugin selects random images
 * from a folder and sets them as the
 * server icon.
 * @author joajoajpedroj;
 * @version 0.1-BETA;
 * @since 0.1-BETA;
 */

 public final class PluginEntry extends JavaPlugin implements Listener {
	// Variable for folder that's going to store the desired images
		File imagesFolder = new File ("server-icons/");
		File[] listImages;
		// Variable for the selected icon
		CachedServerIcon icon;

		@Override
		public void onEnable() {
				// Copy the config.yml in the plugin configuration folder if it doesn't exists.
				this.saveDefaultConfig();

				// Make images directory (if one does not exist)
				if (imagesFolder.mkdir()){
						getLogger().info(imagesFolder.getName() + " has been created.");
				}

				// List files in images folder
				listImages = imagesFolder.listFiles();
				// Log how many images were found
				getLogger().info("Found " + listImages.length + " images.");

				// Inform bukkit that we may want to react to events
				getServer().getPluginManager().registerEvents(this, this);
		}
		// When someone pings the server from the login screen...
		@EventHandler
		public void onPing(PaperServerListPingEvent event) throws Exception {
				// Log message for debugging
				getLogger().info("\"" + event.getAddress() + "\" is pinging us!!!");

				// Update list of images
				listImages = imagesFolder.listFiles();
				// Generate random number to select a specific image
				int toSelect = (int)(Math.random() * listImages.length);
				// Select image
				File image = listImages[toSelect];
				// Get name of image file and remove ".png"
				String imageName = image.getName();
				imageName = imageName.substring(0, imageName.lastIndexOf('.'));
				// Debugging message
				getLogger().info("Choosing image \"" + imageName + "\".");

				// Load image
				this.icon = Bukkit.loadServerIcon(image);
				// Set image as icon
				event.setServerIcon(this.icon);

				//change motd to image name
				Component motd = Component.text(imageName);
				event.motd(motd);

		}

		@Override
		public void onDisable() {
		}
 }