package cl.makinolas.atk.gamemodes;


import cl.makinolas.atk.GameConstants;
import cl.makinolas.atk.actors.GameActor;
import cl.makinolas.atk.actors.JumpState;
import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.actors.OnGround;
import cl.makinolas.atk.actors.attacks.Attacks;
import cl.makinolas.atk.actors.bosses.Boss;
import cl.makinolas.atk.actors.enemies.Enemy;
import cl.makinolas.atk.actors.friend.*;
import cl.makinolas.atk.actors.items.Inventory;
import cl.makinolas.atk.actors.platform.Platform;
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
    private long cooldownTimer;
    private boolean[] isSinging = {false, false, false, false};
    private float xVelocity;
    private boolean isHero;

    public SurvivalHero(World survivalWorld) {
        this.myWorld = survivalWorld;
        character = new Weedle();
        // Set correct collider.
        myBodyDefinition = new BodyDef();
        myBodyDefinition.type = BodyDef.BodyType.DynamicBody;
        setAnimation();
        changeAnimation(walkAnimation);
        parent = character;

        jumpState = new OnGround();
        jumpState.setHero(this);
        myBodyDefinition.fixedRotation = true;
        myWorld.setGravity(new Vector2(0,30));
        cooldownTimer = 0;
        xVelocity = 0;
        isHero = true;


    }

    @Override
    public void act(float delta){
        //jumpState.setAnimation(this, delta);
        myBody.setLinearVelocity(xVelocity,myBody.getLinearVelocity().y);


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
    public void setSpeed(float x, float y) {
        //mplayer.PlayJumpSound();
        myBody.setLinearVelocity(x, y);
    }

    @Override
    public boolean isHero() {
        return true;
    }

    @Override
    public void interactWithMonster(Enemy enemy) {

    }

    @Override
    public void landedPlatform(WorldManifold worldManifold, Platform platform) {
            setState(new OnGround());
    }

    @Override
    public void interactWithMonster(Boss boss) {
            
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
    public float getRelativeY() {
        return 0;
    }

    @Override
    public float getRelativeX() {
        return 0;
    }

    @Override
    public void sing() {

    }

    @Override
    public void setState(JumpState state) {
        this.jumpState = state;
        jumpState.setHero(this);
    }

    @Override
    public void unSing() {

    }

    @Override
    public void sleep() {

    }

    @Override
    public void Awake() {

    }

    @Override
    public void paraliza3() {

    }

    @Override
    public void desparaliza3() {

    }

    public void unSleep() {

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
    public void gainExp(int level, Enemies type) {

    }

    @Override
    public void interact(GameActor actor2, WorldManifold worldManifold) {
            actor2.interactWithHero(this,worldManifold);
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
        myBody.setLinearVelocity(10*i,myBody.getLinearVelocity().y);
        xVelocity = 10 * i;
        isFacingRight = b;
    }

    @Override
    public void jump(int i) {
            jumpState.jump();
    }

    @Override
    public Inventory getInventory() {
        return null;
    }

    @Override
    public void attackPrimary() {
        if(!isSinging[0] && cooldownTimer < System.currentTimeMillis() && character.getMagic() >= character.getAttackMagicRequirement()){
            character.setMagic(character.getMagic() - character.getAttackMagicRequirement());
            //mplayer.PlayProyectileSound();
            GameActor fireball = character.getFriendAttack(myWorld, myBody.getPosition().x,myBody.getPosition().y,isFacingRight, this);
            ((AbstractStage) getStage()).addGameActor(fireball);
            ((Attacks) fireball).getSpriteState().secondaryEfectsToSource(this);
            cooldownTimer = System.currentTimeMillis() + ((Attacks)fireball).getSpriteState().getCooldown();
        }
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
    public void interactWithPlatform(Platform platform, WorldManifold worldManifold){
        platform.interactWithHero(this, worldManifold);
        setState(new OnGround());

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

    public void stopMovement() {
        myBody.setLinearVelocity(0,myBody.getLinearVelocity().y);
        xVelocity = 0;
    }

    @Override
    public void pressingLeft() {

    }

    @Override
    public void pressingRight() {

    }

    @Override
    public void notPressingLeft() {

    }

    @Override
    public void notPressingRight() {

    }

    @Override
    public void isNotPressingPrimaryAttack() {

    }
}
