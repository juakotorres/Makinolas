package cl.makinolas.atk.utils;

import cl.makinolas.atk.actors.friend.FriendDescriptor;
import cl.makinolas.atk.actors.items.ItemDescriptor;

/**
 * This class must contain all the data of a saved instance as public fields
 */
public class SaveInstance {

    public float heroX, heroY;
    public FriendDescriptor[] friends;
    public ItemDescriptor[] items;
    public float money;

}
