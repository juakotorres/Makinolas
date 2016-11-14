package cl.makinolas.atk.gamemodes;


import cl.makinolas.atk.GameConstants;
import cl.makinolas.atk.actors.GameActor;
import cl.makinolas.atk.actors.JumpState;
import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.actors.OnGround;
import cl.makinolas.atk.actors.attacks.Attacks;
import cl.makinolas.atk.actors.friend.*;
import cl.makinolas.atk.actors.items.Inventory;
import cl.makinolas.atk.actors.ui.IHero;
import cl.makinolas.atk.minigames.ICharacter;
import cl.makinolas.atk.stages.AbstractStage;
import cl.makinolas.atk.stages.Spot;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;

/**
 * Created by belisariops on 11/8/16.
 */
public class SurvivalHero extends Monsters implements ICharacter, IHero {
    private Friend character;
    protected int walkAnimation;
    protected int hurtAnimation;
    protected int[] attackAnimations;
    protected int countMeleeFrames;
    protected int actualAnimation;
    private BodyDef myBodyDefinition;
    private JumpState jumpState;
    private World myWorld;

    public SurvivalHero(World survivalWorld) {
        this.myWorld = survivalWorld;
        character = new Weedle();
        // Set correct collider.
        myBodyDefinition = new BodyDef();
        myBodyDefinition.type = BodyDef.BodyType.DynamicBody;
        setAnimation();
        changeAnimation(walkAnimation);

        jumpState = new OnGround();
        jumpState.setHero(this);
        myBodyDefinition.fixedRotation = true;


    }

    @Override
    public void act(float delta){
        jumpState.setAnimation(this, delta);

    }


    @Override
    public void checkPosition(float delta) {

    }


    public void setAnimation(){
        setMasterTexture(character.getTexture(),character.getWidth(),character.getHeight());
        walkAnimation = addAnimation(0.15f, character.getWalkAnimation());
        hurtAnimation = addAnimation(0.15f, character.getHurtAnimation());
        attackAnimations = new int[character.getMeleeAnimation().length];
        countMeleeFrames = 0;
        for(int i = 0; i < character.getMeleeAnimation().length; i++){
            attackAnimations[i] = addAnimation(0.15f, character.getMeleeAnimation()[i][1]);
        }
        actualAnimation = 0;
    }

    @Override
    public void damage(int damage, Attacks inflictor) {

    }

    @Override
    public int getMeleeDamage() {
        return 0;
    }

    @Override
    public float getXDirection() {
        return 0;
    }

    @Override
    public void CriticalDamage() {

    }

    @Override
    public float getMonsterWidth() {
        return 0;
    }

    @Override
    public float getMonsterHeight() {
        return 0;
    }

    @Override
    protected void gainExp(int level, Enemies type) {

    }

    @Override
    public void interact(GameActor actor2, WorldManifold worldManifold) {

    }

    @Override
    public void onAirAnimation(float delta) {

    }

    @Override
    public void onGroundAnimation(float delta) {
        changeAnimation(walkAnimation);
    }

    @Override
    public void endInteraction(GameActor actor2, WorldManifold worldManifold) {

    }

    @Override
    public void moveHorizontal(int i, boolean b) {
        System.out.println("aaaaaaa");
        myBody.setLinearVelocity(5*i,0);
    }

    @Override
    public void jump(int i) {

    }

    @Override
    public Inventory getInventory() {
        return null;
    }

    @Override
    public void attackPrimary() {

    }

    @Override
    public void attackSecondary() {

    }

    @Override
    public void prevAllie() {

    }

    @Override
    public void nextAllie() {

    }

    @Override
    public void foo() {

    }

    @Override
    public void isNotPressingSpace() {

    }

    @Override
    public void setDead() {

    }

    public void setWorld(World myWorld, Vector2 initialPosition){
        this.myWorld = myWorld;

        setSizeCollider(initialPosition, true);
        // Guardar animaciones del jugador
        setAnimation();
        changeAnimation(walkAnimation);
    }


    public void setSizeCollider(Vector2 position, boolean first) {
        myBodyDefinition.position.set(position);
        if(!first){
            myWorld.destroyBody(getBody());
        }
        Body myBody = myWorld.createBody(myBodyDefinition);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(getBodySize(character.getWidth()), getBodySize(character.getHeight()));
        myBody.setGravityScale(1);
        myBody.createFixture(shape, 0.5f);
        myBody.resetMassData();
        shape.dispose();

        // Change Body.
        setBody(myBody);
    }

    public float getBodySize(int size){
        return (0.5f*size)/22;
    }
    @Override
    public void setWorld(World myWorld) {
        setWorld(myWorld, new Vector2(2,3));
    }
}
