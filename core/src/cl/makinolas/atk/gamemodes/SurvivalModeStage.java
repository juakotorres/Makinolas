package cl.makinolas.atk.gamemodes;


import cl.makinolas.atk.actors.Background;
import cl.makinolas.atk.actors.GameActor;
import cl.makinolas.atk.actors.fx.FxManager;
import cl.makinolas.atk.actors.platform.Platform;
import cl.makinolas.atk.actors.ui.BagVis;
import cl.makinolas.atk.actors.ui.MobileGroup;
import cl.makinolas.atk.minigames.MinigameInputController;
import cl.makinolas.atk.screen.GameScreen;
import cl.makinolas.atk.stages.AbstractStage;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;

public class SurvivalModeStage extends AbstractStage implements ContactListener{
    private World survivalWorld;
    private final float frameTime = 1 / 600f;
    private Array<GameActor> gameActors;
    private OrthographicCamera camera;
    private Box2DDebugRenderer renderer;
    private Platform initialPlatform;
    private Group ground, mons, ui, deco;
    private SurvivalHero hero;


    public SurvivalModeStage(Viewport v, GameScreen actualScreen, Game game) {
        super(v);
        myScreen = actualScreen;
        gameActors = new Array<GameActor>();
        survivalWorld = new World(new Vector2(0, -30), true);
        survivalWorld.setContactListener(this);
        initialPlatform =  new Platform(survivalWorld, "CU", 0, 0, 9, 1);
        addActor(new Background("Background/Night.png", getCamera()));
        ground = new Group();
        ground.addActor(initialPlatform);
        addActor(ground);
        /*MobileGroup group = new MobileGroup(false);
        Gdx.input.setInputProcessor(this);
        hero = new SurvivalHero();
        addActor(hero);
        ui.addActor(group);
        ui.addActor(BagVis.getInstance());

        FxManager.getInstance().setParent(ui);
        addListener(new SurvivalInputController(hero,group));*/
    }

    @Override
    public void changeCamera(float x, float y) {

    }

    @Override
    public void beginContact(Contact contact) {

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
