package cl.makinolas.atk.utils;

import cl.makinolas.atk.actors.friend.FriendDescriptor;
import cl.makinolas.atk.actors.items.ItemDescriptor;

/**
 * This class must contain all the data of a saved instance as public fields
 */
public class SaveInstance {

    public FriendDescriptor[] friends;
    public String name;
    public boolean sex; // M true, F false
    public ItemDescriptor[] items;
    public int money;
    public boolean[] levelsUnlocked;

}
