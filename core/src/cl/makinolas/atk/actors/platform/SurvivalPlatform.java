package cl.makinolas.atk.actors.platform;

import cl.makinolas.atk.actors.GameActor;
import cl.makinolas.atk.actors.enemies.Enemy;
import cl.makinolas.atk.actors.enemies.EnemyCreator;
import cl.makinolas.atk.actors.friend.Gastly;
import cl.makinolas.atk.gamemodes.SurvivalModeStage;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

/**
 * Created by belisariops on 12/7/16.
 */
public class SurvivalPlatform extends Platform {
    private Enemy enemy;

    public SurvivalPlatform(World myWorld, String textureCode, int x, int y, int widthTiles, int heightTiles,SurvivalModeStage stage){
        super (myWorld, textureCode, x, y, widthTiles, heightTiles);
        enemy = new Gastly().returnStayAndShootEnemy(myWorld,x,y+10,true);
        stage.addGameActor(enemy);
    }

    public SurvivalPlatform(World myWorld, int x, int y, int widthTiles,Array<GameActor> gameActors) {
        super(myWorld,x,y,widthTiles);
        enemy = new Gastly().returnStayAndShootEnemy(myWorld,x,y,true);
        gameActors.add(enemy);
    }




}
