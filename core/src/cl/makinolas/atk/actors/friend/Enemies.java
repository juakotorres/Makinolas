package cl.makinolas.atk.actors.friend;

public enum Enemies {
  CHARMANDER(62),
  CHARMELEON(142),
  CHARIZARD(240),
  WEEDLE(39),
  KAKUNA(72),
  BEEDRILL(173),
  PIKACHU(105),
  RAICHU(214),
  JIGGLYPUFF(95),
  WIGGLYTUFF(191),
  ZUBAT(49),
  GOLBAT(159),
  MAGNEMITE(65),
  MAGNETON(163),
  GASTLY(62),
  HAUNTER(93),
  GENGAR(225),
  SCYTHER(100),
  EEVEE(65),
  TOTODILE(63),
  CROCONAW(142),
  FERALIGATR(239),
  CROBAT(241),
  PICHU(41),
  IGGLYBUFF(42),
  MAREEP(56),
  FLAAFFY(128),
  AMPHAROS(225),
  SCIZOR(175),
  BAGON(60),
  SHELGON(147),
  SALAMENCE(270),
  SHINX(53),
  LUXIO(127),
  LUXRAY(235),
  GIBLE(60),
  GABITE(144),
  GARCHOMP(270),
  MAGNEZONE(241),
  SNIVY(28),
  SERVINE(145),
  SERPERIOR(238); 
  
  public int baseExperience;
  Enemies(int baseExperience){
    this.baseExperience = baseExperience;    
  }
  
}
