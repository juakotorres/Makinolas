package cl.makinolas.atk.stages;

public class NullSpot extends Spot {

    public NullSpot(){
        super();
    }

    @Override
    protected Spot changeWestLevel(Spot spot) {
        return spot;
    }

    @Override
    protected Spot changeEastLevel(Spot spot) {
        return spot;
    }

    @Override
    protected Spot changeSouthLevel(Spot spot) {
        return spot;
    }

    @Override
    protected Spot changeNorthLevel(Spot spot) {
        return spot;
    }

    @Override
    public boolean isNullSpot(){
        return true;
    }

}
