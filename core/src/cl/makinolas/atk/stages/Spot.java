package cl.makinolas.atk.stages;

public class Spot {

    private int xPosition;
    private int yPosition;
    private Spot northLevel;
    private Spot southLevel;
    private Spot eastLevel;
    private Spot westLevel;
    private Levels level;

    protected Spot(){}

    public Spot(int xPosition, int yPosition, Spot northLevel, Spot southLevel, Spot eastLevel, Spot westLevel){

        this.xPosition = xPosition;
        this.yPosition = yPosition;

        if(!northLevel.isNullSpot()) northLevel.setSouthLevel(this);
        if(!southLevel.isNullSpot()) southLevel.setNorthLevel(this);
        if(!westLevel.isNullSpot())  westLevel.setEastLevel(this);
        if(!eastLevel.isNullSpot()) eastLevel.setWestLevel(this);

        this.northLevel = northLevel;
        this.southLevel = southLevel;
        this.eastLevel = eastLevel;
        this.westLevel = westLevel;
    }

    public int getXPosition(){
        return xPosition;
    }

    public int getYPosition(){
        return yPosition;
    }

    public Spot UpMove(){
        return northLevel.changeNorthLevel(this);
    }

    public Spot DownMove(){
        return southLevel.changeSouthLevel(this);
    }

    public Spot LeftMove() {
        return westLevel.changeWestLevel(this);
    }

    public Spot RightMove(){
        return eastLevel.changeEastLevel(this);
    }

    protected Spot changeWestLevel(Spot spot) {
        return this;
    }

    protected Spot changeEastLevel(Spot spot) {
        return this;
    }

    protected Spot changeSouthLevel(Spot spot) {
        return this;
    }

    protected Spot changeNorthLevel(Spot spot) {
        return this;
    }

    public boolean isNullSpot(){
        return false;
    }

    private void setSouthLevel(Spot southLevel) {
        this.southLevel = southLevel;
    }

    private void setNorthLevel(Spot northLevel) {
        this.northLevel = northLevel;
    }

    private void setEastLevel(Spot eastLevel) {
        this.eastLevel = eastLevel;
    }

    private void setWestLevel(Spot westLevel) {
        this.westLevel = westLevel;
    }

    public Levels getLevel(){
        return level;
    }

    protected void setLevel(Levels level) {
        this.level = level;
    }
}
