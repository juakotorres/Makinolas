package cl.makinolas.atk.actors.items;

import cl.makinolas.atk.actors.Hero;

public class ItemBox {

    private Item item;
    private int quantity;

    public ItemBox(Item i, int q){
        item = i;
        quantity = q;
    }

    public ItemBox(Item i){
        this(i,1);
    }

    public Item getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void use(Hero hero){
        item.use(hero);
        quantity--;
    }

    public void addOne() {
        quantity++;
    }
}
