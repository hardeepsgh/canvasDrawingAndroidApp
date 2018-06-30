package com.example.a300991511.hardeepsinghcom_314ass3;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

public class Canvas extends AppCompatActivity {

    ImageView reusableImageView;
    TextView textView;
    //
    int startx = 10;
    int starty = 10;
    int endx=300;
    int endy=300;
    int strokeWidth ;
    RadioGroup rGroup;
    RadioButton checkedRadioButton;
    //
    ImageView _up_arrow ;
    ImageView _down_arrow ;
    ImageView _left_arrow ;
    ImageView _right_arrow ;

    Paint paint;
    Bitmap bitmap;
    android.graphics.Canvas canvas;
    //
    Handler mHandler = new Handler();

    Button _btnStart ;
    Button _btnClear  ;
    Spinner _widthSpinner;
    SpinnerAdapter _widthSpinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);
        strokeWidth = 15 ;
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(strokeWidth);

        //creating a bitmap as content view for the image
        bitmap = Bitmap.createBitmap((int) getWindowManager()
                .getDefaultDisplay().getWidth(), (int) getWindowManager()
                .getDefaultDisplay().getHeight(), Bitmap.Config.ARGB_8888);
        canvas = new android.graphics.Canvas(bitmap);
        //
        reusableImageView = (ImageView)findViewById(R.id.ImageViewForDrawing);
        reusableImageView.setImageBitmap(bitmap);
        reusableImageView.setVisibility(View.VISIBLE);
        textView = (TextView) findViewById(R.id.textView2);
         _widthSpinner = findViewById(R.id.spinner);


         // START HERE ADD SPINNER ADAPTER https://developer.android.com/guide/topics/ui/controls/spinner
        //
        //reusableImageView.setImageResource(R.drawable.green_rect);

        _btnStart = (Button) findViewById(R.id.btnStart);
        _btnClear = (Button) findViewById(R.id.btnClear) ;
        _btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                canvas = new android.graphics.Canvas(bitmap);

            }
        });
        _btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bitmap.eraseColor(5);
                canvas = new android.graphics.Canvas(bitmap);
            }
        });

         _widthSpinner.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                strokeWidth = Integer.parseInt(parent.getItemAtPosition(position).toString());
                paint.setStrokeWidth(strokeWidth);

            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });





        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.width_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        _widthSpinner.setAdapter(adapter);




        // This will get the radiogroup
        rGroup = (RadioGroup)findViewById(R.id.radioGroup1);
// This will get the radiobutton in the radiogroup that is checked
         checkedRadioButton = (RadioButton)rGroup.findViewById(rGroup.getCheckedRadioButtonId());

        // This overrides the radiogroup onCheckListener
        rGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                // This will get the radiobutton that has changed in its check state
                RadioButton checkedRadioButton = (RadioButton)group.findViewById(checkedId);
                // This puts the value (true/false) into the variable
                boolean isChecked = checkedRadioButton.isChecked();
                // If the radiobutton that has changed in check state is now checked...
                if (isChecked)
                {

                    switch (checkedRadioButton.getText().toString()){
                        case "RED" :{
                            paint.setColor(Color.RED);
                            break;
                        }case "BLUE" :{
                            paint.setColor(Color.BLUE);
                            break;
                        }case "GREEN" :{
                            paint.setColor(Color.GREEN);
                            break;
                        }
                    }

                }
            }
        });


        _up_arrow = findViewById(R.id.imageView);
        _down_arrow = findViewById(R.id.imageView2);
        _left_arrow = findViewById(R.id.imageView3);
        _right_arrow = findViewById(R.id.imageView4);

        _up_arrow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                endy=endy-5;
                drawLine( canvas);
            }
        });

        _up_arrow.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                endy=endy-5;
                drawLine( canvas);
                return true;
            }
        });

        _down_arrow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                endy=endy+5;
                drawLine( canvas);

            }
        });

        _down_arrow.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                endy=endy+5;
                drawLine( canvas);
                return true;
            }
        });

        _left_arrow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                reusableImageView.setFocusable(true);
                reusableImageView.requestFocus();
                endx=endx-5;
                drawLine( canvas);
                //moveRect(canvas);
                reusableImageView.invalidate();
            }
        });

        _left_arrow.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                reusableImageView.setFocusable(true);
                reusableImageView.requestFocus();
                endx=endx-5;
                drawLine( canvas);
                //moveRect(canvas);
                reusableImageView.invalidate();
                return true;
            }
        });

        _right_arrow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //reusableImageView.setVisibility(View.VISIBLE);
                reusableImageView.setFocusable(true);
                reusableImageView.requestFocus();
                endx=endx+5;
                drawLine( canvas);
                //moveRect(canvas);
                reusableImageView.invalidate();
            }
        });
        _right_arrow.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                //reusableImageView.setVisibility(View.VISIBLE);
                reusableImageView.setFocusable(true);
                reusableImageView.requestFocus();
                endx=endx+5;
                drawLine( canvas);
                //moveRect(canvas);
                reusableImageView.invalidate();
                return true;
            }
        });



    }
    public void drawLine(android.graphics.Canvas canvas)
    {
       textView.setText("y = "+String.valueOf(endy));
        //canvas.drawLine(100,100,300,300,paint);
        canvas.drawLine(startx, starty, endx, endy, paint);
        startx=endx;
        starty=endy;

    }
    //Activate the DPAD on emulator:
    //change the settings in config.ini file in .android folder
    //hw.dPad=yes
    //hw.mainKeys=yes
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        switch(keyCode)
        {
            case KeyEvent.KEYCODE_DPAD_DOWN:
                //reusableImageView.setVisibility(View.VISIBLE);
                //reusableImageView.setFocusable(true);
                //reusableImageView.requestFocus();
                endy=endy+5;
                drawLine( canvas);
                //moveRect(canvas);
                //reusableImageView.invalidate();

                return true;


            case KeyEvent.KEYCODE_DPAD_RIGHT:
                //reusableImageView.setVisibility(View.VISIBLE);
                reusableImageView.setFocusable(true);
                reusableImageView.requestFocus();
                endx=endx+5;
                drawLine( canvas);
                //moveRect(canvas);
                reusableImageView.invalidate();

                return true;

            case KeyEvent.KEYCODE_DPAD_UP:
                //reusableImageView.setVisibility(View.VISIBLE);
                //reusableImageView.setFocusable(true);
                //reusableImageView.requestFocus();
                endy=endy-5;
                drawLine( canvas);
                //moveRect(canvas);
                //reusableImageView.invalidate();

                return true;


            case KeyEvent.KEYCODE_DPAD_LEFT:
                //reusableImageView.setVisibility(View.VISIBLE);
                reusableImageView.setFocusable(true);
                reusableImageView.requestFocus();
                endx=endx-5;
                drawLine( canvas);
                //moveRect(canvas);
                reusableImageView.invalidate();

                return true;

        }
        return false;
    }
}
