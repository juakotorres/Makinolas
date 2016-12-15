package cl.makinolas.atk.actors.platform;

import cl.makinolas.atk.actors.GameActor;
import cl.makinolas.atk.actors.enemies.Enemy;
import cl.makinolas.atk.actors.enemies.EnemyCreator;
import cl.makinolas.atk.actors.friend.Gastly;
import cl.makinolas.atk.gamemodes.SurvivalModeStage;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

/**
 * Created by belisariops on 12/7/16.
 */
public class SurvivalPlatform extends Platform {
    private Enemy enemy;
    private SurvivalModeStage survivalModeStage;

    public SurvivalPlatform(World myWorld, String textureCode, int x, int y, int widthTiles, int heightTiles,SurvivalModeStage stage, Group mons,float cameraPositionY){
        super (myWorld, textureCode, x, y, widthTiles, heightTiles);
        //enemy = new Gastly().returnStayAndShootEnemy(myWorld,x+1,y,true);
        //stage.addGameActor(enemy);
        cameraPositionWhenCreated = cameraPositionY;
        this.survivalModeStage = stage;
        //mons.addActor(enemy);
    }

    public SurvivalPlatform(World myWorld, int x, int y, int widthTiles,Array<GameActor> gameActors) {
        super(myWorld,x,y,widthTiles);
        //enemy = new Gastly().returnStayAndShootEnemy(myWorld,x,y,true);
        //gameActors.add(enemy);
    }

    @Override
    public void act(float delta) {
       // if ((myBody.getPosition().y/1.8) +100 <getCameraPositionWhenCreated() ) {
         //   destroySurvivalPlatform(survivalModeStage.gameActors,survivalModeStage.ground,survivalModeStage.survivalWorld,this.getBody());
       // }
    }







}
