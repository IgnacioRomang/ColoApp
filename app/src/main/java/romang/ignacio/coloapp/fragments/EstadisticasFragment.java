package romang.ignacio.coloapp.fragments;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.preference.PreferenceManager;

import com.androidplot.pie.PieRenderer;
import com.androidplot.pie.Segment;
import com.androidplot.pie.SegmentFormatter;
import com.androidplot.xy.BoundaryMode;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.RectRegion;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYSeries;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import romang.ignacio.coloapp.R;
import romang.ignacio.coloapp.databinding.FragmentEstadisticasBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EstadisticasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EstadisticasFragment extends Fragment {
    private FragmentEstadisticasBinding binding;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EstadisticasFragment() {
        // Required empty public constructor
    }

    public static EstadisticasFragment newInstance(String param1, String param2) {
        EstadisticasFragment fragment = new EstadisticasFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentEstadisticasBinding.inflate(inflater, container, false);
        Float winRate = 70.0f;
        Float loseRate = 30.0f;

        Segment win = new Segment("Win %" + winRate.toString(), winRate);
        Segment lose = new Segment("Loose %" + loseRate.toString(), loseRate);

        SegmentFormatter winFormatter = new SegmentFormatter(getResources().getColor(R.color.md_green_300), Color.TRANSPARENT);
        winFormatter.setOffset(15.0f);

        SegmentFormatter loseFormatter = new SegmentFormatter(getResources().getColor(R.color.md_red_300), Color.TRANSPARENT);

        binding.winRateChart.addSegment(win, winFormatter);
        binding.winRateChart.addSegment(lose, loseFormatter);
        binding.winRateChart.getRenderer(PieRenderer.class).setDonutSize(0, PieRenderer.DonutMode.PERCENT);

        Float rate = 40.0f;
        Float lRate = 60.0f;

        Segment rateS = new Segment("Win %" + rate.toString(), rate);
        Segment loseS = new Segment("Loose %" + lRate.toString(), lRate);

        SegmentFormatter rateFormatter = new SegmentFormatter(getResources().getColor(R.color.md_blue_200), Color.TRANSPARENT);
        rateFormatter.setOffset(15.0f);

        SegmentFormatter lrateFormatter = new SegmentFormatter(getResources().getColor(R.color.md_amber_200), Color.TRANSPARENT);


        binding.profitRatio.addSegment(rateS, rateFormatter);
        binding.profitRatio.addSegment(loseS, lrateFormatter);
        binding.profitRatio.getRenderer(PieRenderer.class).setDonutSize(0, PieRenderer.DonutMode.PERCENT);

        List<Double> xVals = new ArrayList<>();
        List<Double> yVals =new ArrayList<>();

        for(int i=0;i<30;i++){
            xVals.add(new Double(i));
        }
        yVals.add(10.00d);
        int vMin=0,vMax=20;
        Random r= new Random(System.currentTimeMillis());
        for(int e=0;e<29;e++){
            Double profit = (vMin + r.nextInt(vMax - vMin + 1)) / 1.0;
            yVals.add(profit);
        }

        XYSeries series = new SimpleXYSeries(xVals,yVals,"Dinero");

        LineAndPointFormatter format = new LineAndPointFormatter();
        format.getLinePaint().setColor(getResources().getColor(R.color.md_green_300));
        format.getFillPaint().setColor(getResources().getColor(R.color.md_green_300));
        format.getFillPaint().setAlpha(50);

        binding.xyGrafico.addSeries(series,format);

        int nightModeFlags = getContext().getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        Paint paint = new Paint();
        paint.setColor(getResources().getColor(R.color.white));

        //binding.xyGrafico.setRangeBoundaries(0,10, BoundaryMode.FIXED);
        binding.xyGrafico.setDomainBoundaries(0,30, BoundaryMode.FIXED);
        binding.xyGrafico.getGraph().setBackgroundPaint(paint);
        binding.xyGrafico.getGraph().setGridBackgroundPaint(paint);

        RectRegion bounds = binding.xyGrafico.getBounds();

        Integer ini=bounds.getMinY().intValue(), max= bounds.getMaxY().intValue();

        List<Float> numbers = new ArrayList<>();
        Float nuew;
        for(int e=ini;e<max;e++){
            nuew = e/1.0f;
            numbers.add(nuew);
            numbers.add(nuew+0.5f);
        }
        //binding.rangerSlider.setValues(numbers);
        //binding.rangerSlider.setValueTo(ini/1.0f);
        //binding.rangerSlider.setValueFrom(max/1.0f);
        binding.rangerSlider.setValueTo(max/1.0f);
        binding.rangerSlider.setValueFrom(ini/1.0f);
        binding.rangerSlider.setStepSize(15);
        binding.xyGrafico.setDomainBoundaries(ini,max, BoundaryMode.FIXED);
        return binding.getRoot();
    }
}