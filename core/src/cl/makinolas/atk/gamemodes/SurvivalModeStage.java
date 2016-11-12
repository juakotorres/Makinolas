package cl.makinolas.atk.gamemodes;


import cl.makinolas.atk.actors.Background;
import cl.makinolas.atk.actors.GameActor;
import cl.makinolas.atk.actors.fx.FxManager;
import cl.makinolas.atk.actors.platform.Platform;
import cl.makinolas.atk.actors.ui.BagVis;
import cl.makinolas.atk.actors.ui.MobileGroup;
import cl.makinolas.atk.minigames.MinigameCharacter;
import cl.makinolas.atk.minigames.MinigameInputController;
import cl.makinolas.atk.minigames.PlatformCreator;
import cl.makinolas.atk.screen.GameScreen;
import cl.makinolas.atk.stages.AbstractStage;
import cl.makinolas.atk.stages.CameraPosition;
import cl.makinolas.atk.utils.SaveManager;
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

    //Agregar requisitode calidad y restriccion
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
        deco = new Group();
        addActor(deco);
        addActor(ground);
        mons = new Group();
        addActor(mons);
        ui = new Group();
        addActor(ui);

        MobileGroup group = new MobileGroup(false);
        Gdx.input.setInputProcessor(this);

        cameraObserver = new CameraPosition();

        ground.addActor(initialPlatform);
        ground.addActor(new PlatformCreator(survivalWorld, this, 20, 0, ground));
        hero = new SurvivalHero();
        hero.setWorld(survivalWorld);
        addGameActor(hero);

        //cameraObserver.setPosition(hero.getBody().getPosition().x, hero.getBody().getPosition().y);




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

    @Override
    public void addGameActor(GameActor actor) {
        mons.addActor(actor);
        gameActors.add(actor);
    }

    @Override
    public void act(float delta){
        super.act(delta);
        survivalWorld.step(frameTime, 0, 0);


    }



}
