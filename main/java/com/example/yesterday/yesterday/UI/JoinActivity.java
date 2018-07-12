package com.example.yesterday.yesterday.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yesterday.yesterday.R;
import com.example.yesterday.yesterday.server.JoinServer;

public class JoinActivity extends AppCompatActivity {

    EditText new_id_text,new_pw_text,new_name_text;
    Button join_btn;
    String new_id,new_pw,new_name;
    private static final String  WEBIP = "192.168.0.72";
    String result;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        new_id_text = (EditText) findViewById(R.id.new_id_text);
        new_pw_text = (EditText) findViewById(R.id.new_pw_text);
        new_name_text = (EditText) findViewById(R.id.new_name_text);
        join_btn = (Button) findViewById(R.id.join_btn);


        join_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new_id = new_id_text.getText().toString();
                new_pw = new_pw_text.getText().toString();
                new_name = new_name_text.getText().toString();

                try {
                    result = new JoinServer(new_id, new_pw, new_name).execute().get();
                } catch(Exception e){
                    e.getMessage();
                }

                if(result.equals("success")){

                    intent = new Intent(getApplicationContext(), HomeActivity.class);
                    intent.putExtra("name",new_name);
                    startActivity(intent);
                }
                else if(result.equals("overlap")){
                    Toast.makeText(getApplicationContext(), "아이디가 이미 존재합니다.", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "로그인 실패", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
