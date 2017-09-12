package org.adebayo.olumide.tiphelper;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.SeekBar;
import android.view.View;
import android.widget.TextView;

/*
@author: olumide adebayo
 */

public class TipHelperActivity extends Activity {

    private static final int seekShift=16;
    private boolean clearTotal=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_helper);

        //set seekbar's listener
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

        /*
        setting a listener on the 'total' EditText in order to be able to
        clear it after an error message is printed
         */
        EditText total = findViewById(R.id.total);
        total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(clearTotal){
                    EditText total = findViewById(R.id.total);
                    total.setText(null);
                }
                clearTotal=false;
            }
        });
    }

    /*
    recipient of onclick, calculates the tips for each row
     */
    public void calculateTips(View v){

        TextView _v;
        String s_total=null;
        EditText _e = findViewById(R.id.total);

        //make sure total is not empty
        if(_e.getText() != null && _e.getText().length()>0) {
            s_total = _e.getText().toString();
        }else{
            String _msg="Total Amount missing!!!";
            _e.setText(_msg);
            clearTotal=true;
            return;
        }
        //get total as a long value
        long _total = Long.parseLong(s_total);

        _e = findViewById(R.id.sliderText);

        //enable momentarily to interact
        _e.setEnabled(true);

        String s_sliderText=_e.getText().toString();

        //disable
        _e.setEnabled(false);

        //if custom tip rate was selected
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

    /*
    calculate the total ++tip
     */
    private double calculateThisTip(long total,long rate){
            return
                    total + ( (total*rate)/100);
    }
}
