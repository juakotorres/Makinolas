package cl.makinolas.atk.stages;

public enum Levels {
  LEVEL1("ezpizi", false, 28, 20),
  FIRSTSTAGE("FirstStage", false, 28, 18),
  LEVEL2("Electric_Campament", false, 28, 16),
  LEVEL3("UpAndDown", false, 26, 16),
  BOSSLEVEL1("bossstage1", true, 24, 16);
  
  public String levelName;
  public boolean bossLevel;
  public int mapx, mapy; // x from 0 to 31, y from 0 to 24
  Levels(String name, boolean boss, int mx, int my){
    levelName = name;
    bossLevel = boss;
    mapx = mx;
    mapy = my;
  }
}
