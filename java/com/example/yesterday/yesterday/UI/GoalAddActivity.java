package com.example.yesterday.yesterday.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.yesterday.yesterday.R;

public class GoalAddActivity extends AppCompatActivity {

    Button button;
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_add);

        button=(Button)findViewById(R.id.goal_add_button);
        text=(TextView)findViewById(R.id.goal_edit_text);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("NAME",text.getText().toString());
                setResult(RESULT_OK,intent);

                finish();
            }
        });
    }
}
