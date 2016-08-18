package cl.makinolas.atk.stages;

import java.util.Observable;

public class CameraPosition extends Observable{
  private float positionX;
  private float positionY;
  
  public CameraPosition(float positionX, float positionY){
    this.positionX = positionX;
    this.positionY = positionY;
  }
  
  public CameraPosition() {
    this.positionX = 0;
    this.positionY = 0;
  }

  public void setPosition(float x, float y){
    synchronized (this){
      this.positionX = x;
      this.positionY = y;
    }
    
    setChanged();
    notifyObservers();
  }
  
  public float getPositionX(){
    return positionX;
  }
  
  public float getPositionY(){
    return positionY;
  }
}