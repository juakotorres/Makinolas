package cl.makinolas.atk.actors.items;

import cl.makinolas.atk.actors.fx.FxManager;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import cl.makinolas.atk.actors.GameActor;
import cl.makinolas.atk.actors.platform.Platform;
import cl.makinolas.atk.actors.enemies.Enemy;

public class BallActor extends GameActor {

    private TextureRegion region;
    private Ball.BallType type;
    private boolean dead;
    private float captureTime, captureX, captureY;
    private BrokeListener listener;

    public BallActor(Ball.BallType t, World myWorld, float x, float y){
        type = t;
        dead = false;
        captureTime = -1;

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
        setDead();
    }

    @Override
    public void interactWithEnemy(Enemy enemy, WorldManifold worldManifold) {
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
    public void act(float delta) {
        super.act(delta);
        if(captureTime > 0){
            myBody.setLinearVelocity(0,0);
            captureTime -= delta * 8;
            if(captureTime <= 0) {
                setDead();
                listener.onBroke(captureX,captureY);
                FxManager.getInstance().addFx(FxManager.Fx.REDFX,captureX*20,captureY*20);
            }
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(captureTime < 0) {
            Vector2 myPosition = myBody.getPosition();
            batch.draw(region, myPosition.x * 20 - 10, myPosition.y * 20 - 10, 20, 20);
        }
        else {
            myBody.setActive(false);
            float r = (float) (40*Math.sin(captureTime));
            r = (r>10)?10:r;
            batch.draw(region, captureX * 20 - 16, captureY * 20 - 16, 16, 16, 32, 32, 1, 1, r);
        }
    }

    public void setThrowImpulse(int dir) {
        myBody.applyLinearImpulse(dir*3, 0.6f, myBody.getPosition().x, myBody.getPosition().y, true);
    }

    public void setDead() {
        if(captureTime > 0) return;
        dead = true;
    }

    public void roll(int n, BrokeListener ls){
        if(captureTime > 0) return;
        captureTime = (float) (n*2*Math.PI);
        captureX = myBody.getPosition().x;
        captureY = myBody.getPosition().y + 1;
        listener = ls;
    }

    public interface BrokeListener {
        public void onBroke(float x, float y);
    }
}
