package cl.makinolas.atk.actors;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by nbravo on 11-07-16.
 */
public class Level extends Actor{

    private String myName;

    private Level myBefore;
    private Level myAfter;

    private int beforeKey;
    private int afterKey;

    public Level(String name, int x, int y) {
        myName = name;
        setPosition(x, y);
    }

    public Level(String name, int x, int y, Level before) {
        this(name,x,y);
        myBefore = before;
        myBefore.setAfter(this);
        setKey(calculateKey());
    }

    private void setAfter(Level after){
        myAfter = after;
    }

    public Level getAfter() {
        return myAfter;
    }

    public Level getBefore() { return myBefore;}

    private void setAfterKey(int key){
        afterKey = key;
    }

    private void setKey(int key) {
        myBefore.setAfterKey(key);
        switch (key) {
            case Input.Keys.UP:
                beforeKey = Input.Keys.DOWN;
                break;
            case Input.Keys.DOWN:
                beforeKey = Input.Keys.UP;
                break;
            case Input.Keys.LEFT:
                beforeKey = Input.Keys.RIGHT;
                break;
            case Input.Keys.RIGHT:
                beforeKey = Input.Keys.LEFT;
                break;
        }
    }

    boolean canGoForward(int key){
        return afterKey == key;
    }

    boolean canGoBackward(int key){
        return beforeKey == key;
    }

    private int calculateKey(){
        float dx = myBefore.getX() - getX();
        float dy = myBefore.getY() - getY();
        if (dx > 0){
            if(dy > 0) {
                if (dx > dy)
                    return Input.Keys.LEFT;
                return Input.Keys.DOWN;
            }
            else{
                if(dx > -dy)
                    return Input.Keys.LEFT;
                return Input.Keys.UP;
            }
        }
        else{
            if(dy > 0){
                if (-dx > dy)
                    return Input.Keys.RIGHT;
                return Input.Keys.DOWN;
            }
            else{
                if(-dx > -dy)
                    return Input.Keys.RIGHT;
                return Input.Keys.UP;
            }
        }
    }
}
