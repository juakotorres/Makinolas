package cl.makinolas.atk.utils;

import cl.makinolas.atk.actors.GameActor;
import cl.makinolas.atk.actors.Platform;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LevelReader {

    private static LevelReader reader = new LevelReader();
    private World world;

    private LevelReader(){}

    public static LevelReader getInstance() {
        return reader;
    }

    public void setWorld(World w){
        world = w;
    }

    public Array<GameActor> loadLevel(String name) throws IOException {
        FileHandle handler = Gdx.files.internal("Levels/"+name+".lvl");
        BufferedReader reader = new BufferedReader(new InputStreamReader(handler.read()), 2048);

        Array<GameActor> platforms = new Array<GameActor>();
        String line = reader.readLine();
        while(line!=null){
            String[] comps = line.split(",");
            switch (comps[0]){
                case "P":
                    platforms.add(parsePlatform(comps));
                    break;
                case "#": //used for comments
                    break;
                //Add other cases
            }
            line = reader.readLine();
        }

        return platforms;
    }

    private GameActor parsePlatform(String[] comps) {
        return new Platform(world,Integer.parseInt(comps[1]),Integer.parseInt(comps[2]),Integer.parseInt(comps[3]),Integer.parseInt(comps[4]));
    }

}
