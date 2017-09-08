package org.adebayo.olumide.tiphelper;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.SeekBar;
import android.view.View;
import android.widget.TextView;

public class TipHelperActivity extends Activity {

    private static final int seekShift=16;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_helper);

        SeekBar seekBar = findViewById(R.id.seekBar);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                EditText sliderText = findViewById(R.id.sliderText);
                sliderText.setEnabled(true);
                sliderText.setText((seekShift+i)+"");
                sliderText.setEnabled(false);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void calculateTips(View v){

        TextView _v;
        EditText _e = findViewById(R.id.total);
        String s_total = _e.getText().toString();

        long _total = Long.parseLong(s_total);

        _e = findViewById(R.id.sliderText);

        _e.setEnabled(true);

        String s_sliderText=_e.getText().toString();

        _e.setEnabled(false);



        if( s_sliderText != null){
            double _customTip = calculateThisTip(_total,Long.parseLong(s_sliderText) );
            _v = findViewById(R.id.custompt);
            _v.setText(""+_customTip);
        }

        double _tip = 0;
        _tip = calculateThisTip(_total,5);
        _v = findViewById(R.id.fivept);
        _v.setText(""+_tip);

        _tip = calculateThisTip(_total,10);
        _v = findViewById(R.id.tenpt);
        _v.setText(""+_tip);

        _tip = calculateThisTip(_total,15);
        _v = findViewById(R.id.fifteenpt);
        _v.setText(""+_tip);



    }

    private double calculateThisTip(long total,long rate){
            return
                    total + ( (total*rate)/100);
    }
}
