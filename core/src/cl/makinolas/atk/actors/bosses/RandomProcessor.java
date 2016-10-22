package cl.makinolas.atk.actors.bosses;


public class RandomProcessor extends StateProcessor{

    private float[] probs;

    protected RandomProcessor(float mt) {
        super(mt);
    }

    public void setProbabilities(float... prob){
        probs = prob;
    }

    @Override
    public void changeState() {
        float r = (float) Math.random();
        int i;
        for(i=0; i<probs.length && r>probs[i]; r-=probs[i++]);
        setCurrentState(i%probs.length);
    }
}
