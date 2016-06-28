package cl.makinolas.atk.actors.items;

import cl.makinolas.atk.actors.GameActor;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class BallActor extends GameActor {

    private TextureRegion region;
    private Ball.BallType type;

    public BallActor(Ball.BallType t, World myWorld, float x, float y){
        type = t;

        BodyDef myBodyDefinition = new BodyDef();
        myBodyDefinition.type = BodyDef.BodyType.DynamicBody;
        myBodyDefinition.position.set(x,y);

        Body myBody = myWorld.createBody(myBodyDefinition);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.5f,0.5f);

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
    public void interact(GameActor actor2) {
        actor2.interactWithBall(this);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Vector2 myPosition = myBody.getPosition();
        batch.draw(region, myPosition.x * 20 - region.getRegionWidth() / 2 , myPosition.y * 20 - region.getRegionHeight() / 2);
    }

    public void setThrowImpulse(int dir) {
        myBody.applyLinearImpulse(dir*5, 2f, myBody.getPosition().x, myBody.getPosition().y, true);
    }
}
