package me.arch32.config;

import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class JsonConfig {

    public static void write(String location, JSONObject obj) {
        try (FileWriter writer = new FileWriter(location)) {
            writer.write(obj.toString());
            writer.flush();

        } catch (IOException e) { e.printStackTrace(); }

    }

}
