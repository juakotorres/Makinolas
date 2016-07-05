package cl.makinolas.atk.actors.items;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import cl.makinolas.atk.actors.Hero;

public class Ball extends Item{

    public enum BallType{
        POKEBALL(1, Item.textures[0][3]),
        GREATBALL(1.5f, Item.textures[0][2]),
        ULTRABALL(2, Item.textures[0][1]),
        MASTERBALL(Float.POSITIVE_INFINITY, Item.textures[0][0]);

        public float catchability;
        public TextureRegion texture;
        BallType(float k, TextureRegion reg){
            catchability = k;
            texture = reg;
        }
    };

    private BallType type;

    public Ball(BallType t){
        type = t;
    }

    @Override
    public String getDescr() {
        return "Throw it to capture a pokemon";
    }

    @Override
    public void use(Hero hero) {
        hero.throwBall(type);
    }

    @Override
    public TextureRegion getImage() {
        return type.texture;
    }

}
