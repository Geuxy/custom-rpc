import me.arch32.DiscordRP;
import me.arch32.util.Logger;
import net.arikia.dev.drpc.DiscordRPC;

public class Main {

    public static void main(String[] args) {
        DiscordRP.init();

        Runtime.getRuntime().addShutdownHook(new Thread("Shutdown") {

            @Override
            public void run() { DiscordRP.stop(); }

        });
    }

}
