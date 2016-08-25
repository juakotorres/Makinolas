package cl.makinolas.atk.actors.items;

import cl.makinolas.atk.actors.Hero;
import cl.makinolas.atk.utils.SaveManager;

import java.util.HashMap;

public class Inventory{

    private HashMap<String,ItemBox> items;
    private Hero hero;
    private ItemBox selItem1, selItem2;
    private int money;

    public Inventory(Hero h){
        items = new HashMap<String, ItemBox>();
        hero = h;
        if(SaveManager.getInstance().hasSaveInstance() && SaveManager.getInstance().getSaveInstance().items!=null){
            fillFromDescriptors(SaveManager.getInstance().getSaveInstance().items);
            money = SaveManager.getInstance().getSaveInstance().money;
        }
        else {
            addItem(new Potion());
            addItem(new Ball.MasterBall(), 4);
            money = 0;
        }
    }

    public void addItem(Item i, int q){
        if(items.containsKey(i.getName())){
            items.get(i.getName()).add(q);
        }
        else{
            items.put(i.getName(),new ItemBox(i,q));
            if(items.size()==1)
                selItem1 = items.get(i.getName());
            else if(items.size()==2)
                selItem2 = items.get(i.getName());
        }
    }

    public void addItem(Item i){
        addItem(i,1);
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

    public ItemDescriptor[] createDescriptors(){
        ItemDescriptor[] itemDescriptors = new ItemDescriptor[items.size()];
        int i = 0;
        for(ItemBox ib : items.values()){
            ItemDescriptor item = new ItemDescriptor();
            item.name = ib.getItem().getName();
            item.quantity = ib.getQuantity();
            itemDescriptors[i] = item;
            i++;
        }
        return itemDescriptors;
    }

    public int getMoney(){
        return money;
    }

    public void earnMoney(int c){
        money += c;
    }

    public boolean payMoney(float c){
        if(money - c >= 0){
            money -= c;
            return true;
        }
        return false;
    }

    public void fillFromDescriptors(ItemDescriptor[] itemDescriptors){
        for(ItemDescriptor id : itemDescriptors){
            addItem(ItemFinder.getInstance().itemForName(id.name),id.quantity);
        }
    }

    public int getItemQuantity(String itemname){
        ItemBox box = items.get(itemname);
        if(box==null) return 0;
        return box.getQuantity();
    }

}
