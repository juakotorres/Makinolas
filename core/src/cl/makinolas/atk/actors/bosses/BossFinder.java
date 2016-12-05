package cl.makinolas.atk.actors.bosses;

import cl.makinolas.atk.actors.Hero;
import com.badlogic.gdx.physics.box2d.World;

import java.util.HashMap;

public class BossFinder {

    private static BossFinder instance = new BossFinder();
    private HashMap<String,Class<? extends Boss>> map;

    private BossFinder(){
        map = new HashMap<>();
        map.put("OldMewtwo",OldMewtwoBoss.class);
        map.put("Groudon", GroudonBoss.class);
    }

    public static BossFinder getInstance() {
        return instance;
    }

    public Boss create(String name, World world, Hero hero){
        try {
            return map.get(name).getDeclaredConstructor(World.class,Hero.class).newInstance(world,hero);
        } catch (Exception e) {
            return null;
        }
    }

}
