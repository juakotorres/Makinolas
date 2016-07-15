package cl.makinolas.atk.actors.items;

import java.util.HashMap;

public class ItemFinder {

    private static ItemFinder instance = new ItemFinder();
    private static HashMap<String,Class<? extends Item>> map;

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
}
