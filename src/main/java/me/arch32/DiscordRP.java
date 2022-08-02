package me.arch32;

import me.arch32.config.JsonConfig;
import me.arch32.util.Logger;
import net.arikia.dev.drpc.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.Scanner;

public class DiscordRP {
    public static boolean running = true;

    public static void init() {
        String configIn;
        Scanner scanner = new Scanner(System.in);

        Logger.printInfo("Configuration directory (<directory> -c to create):");
        configIn = scanner.nextLine();

        if(new File(configIn).exists()) {
            Logger.printInfo("Attempting to run config...");

            try {
                JSONObject jsonObj = (JSONObject) new JSONParser().parse(new FileReader(configIn));

                String image = (String) jsonObj.get("image"),
                        imageText = (String) jsonObj.get("imageText"),
                        firstLine = (String) jsonObj.get("firstLine"),
                        secondLine = (String) jsonObj.get("secondLine"),
                        discordId = (String) jsonObj.get("discordId"),
                        timeStamp = (String) jsonObj.get("timeStamp"),
                        imageSmall = (String) jsonObj.get("imageSmall"),
                        imageSmallText = (String) jsonObj.get("imageSmallText");

                long created = System.currentTimeMillis();

                DiscordEventHandlers handlers = new DiscordEventHandlers.Builder().setReadyEventHandler(user -> System.out.println("Welcome " + user.username + "#" + user.discriminator + ".")).build();
                DiscordRPC.discordInitialize(discordId, handlers, true);

                new Thread("Discord RPC Callback") {

                    @Override
                    public void run() {
                        while(running) { DiscordRPC.discordRunCallbacks(); }
                    }

                }.start();

                DiscordRichPresence.Builder builder = new DiscordRichPresence.Builder(secondLine)
                        .setBigImage(image, imageText)
                        .setDetails(firstLine)
                        .setSmallImage(imageSmall, imageSmallText)
                        .setStartTimestamps(timeStamp.equals("true") ? created : 0);
                DiscordRPC.discordUpdatePresence(builder.build());

            } catch(Exception e) { e.printStackTrace(); }

        } else {

            if(configIn.endsWith(" -c")) {
                JSONObject obj = new JSONObject();
                obj.put("image", "large");
                obj.put("imageText", "hello there");
                obj.put("firstLine", "this is a line!");
                obj.put("secondLine", "This is another line!");
                obj.put("imageSmall", "large");
                obj.put("imageSmallText", "hello again");
                obj.put("discordId", "1003186523946090496");
                obj.put("timeStamp", "true");

                JsonConfig.write(configIn.replace(" -c", ""), obj);
                Logger.printInfo("Created new file.");

            } else { Logger.printError("Cannot find config."); }

        }
        scanner.close();

    }

    public static void stop() {
        running = false;
        DiscordRPC.discordShutdown();
        System.out.println("\nClosed.");
    }

}
