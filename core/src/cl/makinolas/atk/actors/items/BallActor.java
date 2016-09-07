package cl.makinolas.atk.actors.items;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.WorldManifold;

import cl.makinolas.atk.actors.GameActor;
import cl.makinolas.atk.actors.platform.Platform;
import cl.makinolas.atk.actors.enemies.Enemy;

public class BallActor extends GameActor {

    private TextureRegion region;
    private Ball.BallType type;
    private boolean dead;

    public BallActor(Ball.BallType t, World myWorld, float x, float y){
        type = t;
        dead = false;

        BodyDef myBodyDefinition = new BodyDef();
        myBodyDefinition.type = BodyDef.BodyType.DynamicBody;
        myBodyDefinition.position.set(x,y);

        Body myBody = myWorld.createBody(myBodyDefinition);

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

        region = type.texture;
    }

    public Ball.BallType getType(){
        return type;
    }

    @Override
    public void interact(GameActor actor2, WorldManifold worldManifold) {
        actor2.interactWithBall(this);
    }

    @Override
    public void interactWithPlatform(Platform platform, WorldManifold worldManifold){
        dead = true;
    }

    @Override
    public void interactWithEnemy(Enemy enemy) {
        enemy.interactWithBall(this);
    }

    @Override
    public boolean isDead() {
        return dead;
    }

    @Override
    public boolean isBall() {
        return true;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Vector2 myPosition = myBody.getPosition();
        batch.draw(region, myPosition.x * 20 - 10 , myPosition.y * 20 - 10,20,20);
    }

    public void setThrowImpulse(int dir) {
        myBody.applyLinearImpulse(dir*3, 0.6f, myBody.getPosition().x, myBody.getPosition().y, true);
    }

    public void setDead() {
        dead = true;
    }
}
