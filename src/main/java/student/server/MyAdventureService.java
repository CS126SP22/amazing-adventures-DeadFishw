package student.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import student.adventure.Character;
import student.adventure.GameEngineForServer;
import student.adventure.Layout;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.SortedMap;

public class MyAdventureService implements AdventureService {
    private Layout layout;
    private Map<Integer, GameEngineForServer> games;
    public MyAdventureService(File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        layout = mapper.readValue(file, Layout.class);
        games = new HashMap<>();
    }
    @Override
    public void reset() {
        games.clear();
        layout = null;
    }

    @Override
    public int newGame() throws AdventureException {
        Character character = new Character(layout);
        Random generater = new Random();
        int id = generater.nextInt(200000);;
        while (games.keySet().contains(id)) {
            generater.nextInt(200000);
        }
        games.put(id, new GameEngineForServer(character,
                new GameStatus(false, id, "",
                        "", "", new AdventureState(), character.getCommandOptions()),
                layout));
        return id;
    }

    @Override
    public GameStatus getGame(int id) {
        return games.get(id).getGameStatus();
    }

    @Override
    public boolean destroyGame(int id) {
        return false;
    }

    @Override
    public void executeCommand(int id, Command command) {
        games.get(id).runGame(command);
    }

    @Override
    public SortedMap<String, Integer> fetchLeaderboard() {
        return null;
    }
}
