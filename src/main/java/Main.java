import com.fasterxml.jackson.databind.ObjectMapper;
import org.glassfish.grizzly.http.server.HttpServer;
import student.adventure.GameEngine;
import student.adventure.Layout;
import student.server.AdventureResource;
import student.server.AdventureServer;
import student.server.MyAdventureService;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // Wishing you good luck on your Adventure!
        HttpServer server = AdventureServer.createServer(AdventureResource.class);
        server.start();
//        File file = new File("src/main/resources/longLakeTown.json");
//        ObjectMapper mapper = new ObjectMapper();
//        MyAdventureService adventure = new MyAdventureService(file);
//        Layout layout = mapper.readValue(file, Layout.class);
//        GameEngine engine = new GameEngine(layout);
//        engine.start();
    }
}
