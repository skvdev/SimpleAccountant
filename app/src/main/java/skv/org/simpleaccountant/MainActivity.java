package skv.org.simpleaccountant;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import skv.org.simpleaccountant.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        PlotData plotData = new PlotData();
        plotData.setMin(0f);
        plotData.setMax(100f);
        plotData.setData(null);
        binding.plot.setData(plotData);
    }
}
