package com.ycdyng.findviewlab;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    private ImageView avatarImageView;
    private TextView titleTextView;
    private TextView subTitleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        avatarImageView = (ImageView) findViewById(R.id.avatar_image_view);
        avatarImageView.setImageResource(R.mipmap.ic_launcher);

        titleTextView = (TextView) findViewById(R.id.title_text_view);
        titleTextView.setText("FindView");

        subTitleTextView = (TextView) findViewById(R.id.sub_title_text_view);
        subTitleTextView.setText("helper you find view.");

    }

}