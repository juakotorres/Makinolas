package cl.makinolas.atk.actors.items;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import cl.makinolas.atk.actors.Hero;

public class Ball extends Item{

    public enum BallType{
        POKEBALL(10, Item.textures[0][3]),
        GREATBALL(20, Item.textures[0][2]),
        ULTRABALL(40, Item.textures[0][1]),
        MASTERBALL(10, Item.textures[0][0]);

        public int catchability;
        public TextureRegion texture;
        BallType(int k, TextureRegion reg){
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
