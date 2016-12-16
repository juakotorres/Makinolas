package cl.makinolas.atk.actors.items;

import cl.makinolas.atk.actors.GameActor;
import cl.makinolas.atk.actors.Hero;
import cl.makinolas.atk.actors.ui.IHero;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class ItemActor extends GameActor {

    private Item item;
    private boolean dead;
    private float lifetime;
    private float x,y;
    private World world;
    private boolean initialized;

    public ItemActor(Item type, World myWorld, float x, float y){
        item = type;
        dead = false;
        lifetime = 2f;
        this.x = x;
        this.y = y;
        world = myWorld;
        initialized = false;
    }

    @Override
    public void act(float delta) {
        if(!initialized){
            initBody();
        }
        lifetime -= delta;
        if(lifetime<=0)
            dead = true;
    }

    private void initBody() {
        BodyDef myBodyDefinition = new BodyDef();
        myBodyDefinition.type = BodyDef.BodyType.DynamicBody;
        myBodyDefinition.position.set(x,y);

        Body myBody = world.createBody(myBodyDefinition);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.3f,0.3f);

        myBody.setGravityScale(1);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 0.4f;
        fixtureDef.shape = shape;
        myBody.createFixture(fixtureDef);
        myBody.resetMassData();
        shape.dispose();

        setBody(myBody);
        initialized = true;
    }

    @Override
    public boolean isDead() {
        return dead;
    }

    @Override
    public void interact(GameActor actor2, WorldManifold worldManifold) {
        actor2.interactWithItem(this);
    }

    @Override
    public void interactWithHero(IHero hero, WorldManifold worldManifold) {
        Hero.getInstance().getInventory().addItem(item);
        dead = true;
    }

    @Override
    public void endInteraction(GameActor actor2, WorldManifold worldManifold) {}

  @Override
    public boolean isItem() {
        return initialized;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(myBody == null) return;
        Vector2 myPosition = myBody.getPosition();
        batch.draw(item.getImage(), myPosition.x * 20 - 10, myPosition.y * 20 - 10, 20, 20);
    }
}
