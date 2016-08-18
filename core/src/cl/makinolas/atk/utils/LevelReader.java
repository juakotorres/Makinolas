package cl.makinolas.atk.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import cl.makinolas.atk.actors.GameActor;
import cl.makinolas.atk.actors.Portal;
import cl.makinolas.atk.actors.enemies.EnemyCreator;
import cl.makinolas.atk.actors.platform.Platform;
import cl.makinolas.atk.stages.AbstractStage;

public class LevelReader {

    private static LevelReader reader = new LevelReader();
    private World world;
    private Game game;
    private Vector2 heroPos;
    private AbstractStage gameStage;

    private LevelReader(){
        heroPos = new Vector2(2,3);
    }

    public static LevelReader getInstance() {
        return reader;
    }

    public void setWorld(World w){
        world = w;
    }

    public void setGame(Game g){
        game = g;
    }
    
    public void setStage(AbstractStage stage){
      gameStage = stage;
    }

    public Array<GameActor> loadLevel(String name) throws IOException {
        FileHandle handler = Gdx.files.internal("Levels/"+name+".lvl");
        BufferedReader reader = new BufferedReader(new InputStreamReader(handler.read()), 2048);

        Array<GameActor> platforms = new Array<GameActor>();
        String line = reader.readLine();
        while(line!=null){
            String[] comps = line.split(",");
            switch (comps[0]){
                case "#":
                    break;
                case "%P":
                    platforms.add(new Portal(world,Integer.parseInt(comps[1]),Integer.parseInt(comps[2]),game));
                    break;
                case "%S":
                    heroPos = new Vector2(Integer.parseInt(comps[1])*1.8f,Integer.parseInt(comps[2])*1.8f);
                    break;
                case "%E":
                    new EnemyCreator(gameStage, comps[1], Integer.parseInt(comps[2]),Integer.parseInt(comps[3]));
                    break;
                default:
                    if(comps[0].length()<=3)
                        platforms.add(parsePlatform(comps));
                    break;
                //Add other cases
            }
            line = reader.readLine();
        }

        return platforms;
    }

    private GameActor parsePlatform(String[] comps) {
        return new Platform(world,comps[0],Integer.parseInt(comps[1]),Integer.parseInt(comps[2]),Integer.parseInt(comps[3]),Integer.parseInt(comps[4]));
    }

    public Vector2 getHeroPosition() {
        return heroPos;
    }
}
