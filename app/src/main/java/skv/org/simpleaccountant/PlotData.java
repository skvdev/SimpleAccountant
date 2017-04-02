package skv.org.simpleaccountant;

import android.support.v4.util.Pair;

import java.util.List;

/**
 * Created by cora32 on 02.04.2017.
 */

public class PlotData {
    private float min;
    private float max;
    private List<Pair<Integer, Float>> data;

    public float getMin() {
        return min;
    }

    public void setMin(float min) {
        this.min = min;
    }

    public float getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = max;
    }

    public List<Pair<Integer, Float>> getData() {
        return data;
    }

    public void setData(List<Pair<Integer, Float>> data) {
        this.data = data;
    }
}
