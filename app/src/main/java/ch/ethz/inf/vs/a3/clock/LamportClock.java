package ch.ethz.inf.vs.a3.clock;

public class LamportClock implements Clock {

    private int time;



    @Override
    public void update(Clock other) {

    }

    @Override
    public void setClock(Clock other) {

    }

    @Override
    public void tick(Integer pid) {

    }

    @Override
    public boolean happenedBefore(Clock other) {
        return false;
    }

    @Override
    public String toString(){
        return null;
    }


    @Override
    public void setClockFromString(String clock) {

    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getTime() {
        return this.time;
    }
}
