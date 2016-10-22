package cl.makinolas.atk.actors.bosses;

public class SequencialProcessor extends StateProcessor {

    private int lastState;

    protected SequencialProcessor(float mt) {
        super(mt);
        lastState = -1;
    }

    @Override
    public void changeState() {
        lastState = (lastState + 1)%states.length;
        setCurrentState(lastState);
    }
}
