package cl.makinolas.atk.stages;

import com.badlogic.gdx.scenes.scene2d.Stage;

import cl.makinolas.atk.actors.Background;

public class MenuStage extends Stage {
  
  public MenuStage(){
    addActor(new Background("Background/MenuBackground.jpg", getCamera().position.x, getCamera().position.y));
    addActor(new Background("Background/atk.png", 320, 350 ));
  }
}
