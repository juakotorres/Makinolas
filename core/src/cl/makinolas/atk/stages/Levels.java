package cl.makinolas.atk.stages;

public enum Levels {
  LEVEL1("ezpizi", false, 28, 20, "Background/SuPuente.jpg"),
  FIRSTSTAGE("FirstStage", false, 28, 18, "Background/SandOcean.png"),
  LEVEL2("Electric_Campament", false, 28, 16, "Background/AbandonedMansion.jpg"),
  LEVEL3("UpAndDown", false, 26, 16, "Background/Night.png"),
  BOSSLEVEL1("bossstage1", true, 24, 16, "Background/SuPuente.jpg");
  
  public String levelName;
  public boolean bossLevel;
  public int mapx, mapy; // x from 0 to 31, y from 0 to 24
  public String levelBackground;
  Levels(String name, boolean boss, int mx, int my, String background){
    levelName = name;
    bossLevel = boss;
    mapx = mx;
    mapy = my;
    levelBackground = background;
  }
}
