package cl.makinolas.atk.stages;

public enum Levels {
  LEVEL1("level1", false),
  FIRSTSTAGE("FirstStage", false),
  LEVEL2("level2", false),
  LEVEL3("level3", false),
  BOSSLEVEL1("bossstage1", true);


  
  public String levelName;
  public boolean bossLevel;
  Levels(String name, boolean boss){
    levelName = name;
    bossLevel = boss;
  }
}
