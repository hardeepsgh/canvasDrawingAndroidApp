package com.example.a300991511.hardeepsinghcom_314ass3;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import static android.view.View.*;

public class EarthView extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earth_view);

        // Handle Fade Button
        Button button = findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener(){
               public void onClick(View v){
                   performAnimation(R.anim.shakennotstirred);
              }
        });

        performAnimation(R.anim.shakennotstirred);

    }

    private void performAnimation(int animationResourceID)
    {
        // We will animate the imageview
        ImageView reusableImageView = (ImageView)findViewById(R.id.ImageViewForTweening);
       // reusableImageView.setImageResource(R.drawable.green_rect);
        reusableImageView.setVisibility(VISIBLE);

        // Load the appropriate animation
        Animation an =  AnimationUtils.loadAnimation(this, animationResourceID);
        // Register a listener, so we can disable and re-enable buttons
        an.setAnimationListener(new MyAnimationListener());
        // Start the animation
        reusableImageView.startAnimation(an);
    }




    private void toggleButtons(boolean clickableState)
    {
/*
        final Button fadeButton = (Button) findViewById(R.id.ButtonAlpha);
        fadeButton.setClickable(clickableState);

        // Handle Grow Button
        final Button growButton = (Button) findViewById(R.id.ButtonScale);
        growButton.setClickable(clickableState);

        // Handle Move Button
        final Button moveButton = (Button) findViewById(R.id.ButtonTranslate);
        moveButton.setClickable(clickableState);

        // Handle Spin Button
        final Button spinButton = (Button) findViewById(R.id.ButtonRotate);
        spinButton.setClickable(clickableState);

        // Handle Spin Button
        final Button allButton = (Button) findViewById(R.id.ButtonAll);
        allButton.setClickable(clickableState);
*/
    }

    class MyAnimationListener implements Animation.AnimationListener {

        public void onAnimationEnd(Animation animation) {
            // Hide our ImageView
            ImageView reusableImageView = (ImageView)findViewById(R.id.ImageViewForTweening);
            reusableImageView.setVisibility(INVISIBLE);

            // Enable all buttons once animation is over
            toggleButtons(true);

        }

        public void onAnimationRepeat(Animation animation) {
            // what to do when animation loops
        }

        public void onAnimationStart(Animation animation) {
            // Disable all buttons while animation is running
            toggleButtons(false);

        }

    }

}
