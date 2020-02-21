package ch.ethz.inf.vs.a3.clock;

import java.util.HashMap;
import java.util.Map;

public class VectorClock implements Clock {

    private Map<Integer, Integer> vector = new HashMap<>(0);

    @Override
    public void update(Clock other) {
        Map<Integer, Integer>  vc = ((VectorClock) other).vector;
        for (int key : vc.keySet()) {
            if (vector.get(key) == null) {
                vector.put(key, vc.get(key));
            } else if (vc.get(key) > vector.get(key)) {
                vector.put(key, vc.get(key));
            }
        }
    }

    @Override
    public void setClock(Clock other) {
        vector = new HashMap<>(0);
        Map<Integer, Integer>  vc = ((VectorClock) other).vector;
        for (int key : vc.keySet()) {
                vector.put(key, vc.get(key));
        }
    }

    @Override
    public void tick(Integer pid) {

    }

    @Override
    public boolean happenedBefore(Clock other) {
        return false;
    }

    @Override
    public void setClockFromString(String clock) {

    }


    @Override
    public String toString() {
        return null;
    }


    public void addProcess(int i, int testTime) {
        this.vector.put(i, testTime);
    }

    public int getTime(Integer pid) {
        return this.vector.get(pid);
    }

}
