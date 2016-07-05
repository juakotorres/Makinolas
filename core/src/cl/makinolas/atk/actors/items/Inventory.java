package cl.makinolas.atk.actors.items;

import cl.makinolas.atk.actors.Hero;

import java.util.HashMap;

public class Inventory{

    private HashMap<String,ItemBox> items;
    private Hero hero;
    private ItemBox selItem1, selItem2;

    public Inventory(Hero h){
        items = new HashMap<String, ItemBox>();
        hero = h;
        addItem(new Potion());
        addItem(new Ball(Ball.BallType.MASTERBALL));
        addItem(new Ball(Ball.BallType.MASTERBALL));
        addItem(new Ball(Ball.BallType.MASTERBALL));
    }

    public void addItem(Item i){
        if(items.containsKey(i.getName())){
            items.get(i.getName()).addOne();
        }
        else{
            items.put(i.getName(),new ItemBox(i));
            if(items.size()==1)
                selItem1 = items.get(i.getName());
            else if(items.size()==2)
                selItem2 = items.get(i.getName());
        }
    }

    public ItemBox getSelItem1() {
        return selItem1;
    }

    public ItemBox getSelItem2() {
        return selItem2;
    }

    private void useItem(ItemBox itemBox){
        if(itemBox.getQuantity()>0)
            itemBox.use(hero);
    }

    public void useItem(String name){
        useItem(items.get(name));
    }

    public void useSelItem1(){
        useItem(selItem1);
    }

    public void useSelItem2(){
        useItem(selItem2);
    }

}
