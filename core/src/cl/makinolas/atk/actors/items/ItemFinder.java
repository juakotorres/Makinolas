package cl.makinolas.atk.actors.items;

import java.util.HashMap;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;

import cl.makinolas.atk.stages.AbstractStage;

public class ItemFinder {

    private static ItemFinder instance = new ItemFinder();
    private static HashMap<String,Class<? extends Item>> map;
    private String[] droppableItems = {"LeppaBerry","OranBerry"};

    private ItemFinder(){
        Class<? extends Item>[] itemClasses = new Class[]{Potion.class, OranBerry.class, LeppaBerry.class, MaxPotion.class,
                Ball.PokeBall.class, Ball.GreatBall.class, Ball.UltraBall.class, Ball.MasterBall.class};
        map = new HashMap<String, Class<? extends Item>>(itemClasses.length);
        for(Class<? extends Item> c : itemClasses){
            map.put(c.getSimpleName(),c);
        }
    }

    public static ItemFinder getInstance() {
        return instance;
    }

    public Item itemForName(String name){
        try {
            return map.get(name).newInstance();
        } catch (Exception e) {
            return null;
        }
    }

    public void requestDrop(float x, float y, Stage parent, World world){
        int r = (int) (Math.random()*50);
        if(r < 6){
            ((AbstractStage) parent).addGameActor(new ItemActor(itemForName(droppableItems[r/3]),world,x,y));
        }
    }

}
