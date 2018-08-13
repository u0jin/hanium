package com.example.yesterday.yesterday.UI;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yesterday.yesterday.ClientLoginInfo;
import com.example.yesterday.yesterday.LoginSharedPreference;
import com.example.yesterday.yesterday.server.LoginServer;
import com.example.yesterday.yesterday.R;
import com.kakao.auth.ISessionCallback;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.exception.KakaoException;

public class LoginActivity extends AppCompatActivity {
    EditText id_text, pw_text;
    Button login_btn,kakaoLogout_btn,join_btn;
    CheckBox auto_check;
    TextView jsonText;
    String sId, sPw;
    ClientLoginInfo client;
    String result = "";
    Intent intent;
    //자동 로그인 SharedPreferences 객체 생성
    SharedPreferences loginSetting;
    SharedPreferences.Editor editor;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

            id_text = (EditText) findViewById(R.id.id_text);
            pw_text = (EditText) findViewById(R.id.pw_text);
            login_btn = (Button) findViewById(R.id.login_btn);
            kakaoLogout_btn = (Button) findViewById(R.id.kakaoLogout_btn);
            join_btn = (Button) findViewById(R.id.join_btn);
            auto_check = (CheckBox) findViewById(R.id.auto_check);

            client = new ClientLoginInfo();

            loginSetting = getSharedPreferences("loginSetting",0);
            editor = loginSetting.edit();

            if(loginSetting.getBoolean("Auto_Login_enabled",false)){
                id_text.setText(loginSetting.getString("ID",""));
                pw_text.setText(loginSetting.getString("PW",""));
                auto_check.setChecked(true);
            }

            auto_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        editor.putString("ID",id_text.getText().toString());
                        editor.putString("PW",pw_text.getText().toString());
                        editor.putBoolean("Auto_Login_enabled",true);
                        editor.commit();
                    }
                    else{
                        editor.clear();
                        editor.commit();
                    }
                }
            });

        login_btn.setOnClickListener(new View.OnClickListener() {  // 로그인 버튼 리스너
            @Override
            public void onClick(View v) {
                // 사용자가 입력한 id와 pw값을 받아옴 ..... 리스너 안에서 가져와야함 ㅠ
                sId = id_text.getText().toString();   // id
                sPw = pw_text.getText().toString();   // password


                // AsyncTask 객체 생성, 호출
                try {
                    result  = new LoginServer(sId,sPw).execute().get();
                } catch (Exception e){
                    e.getMessage();
                }

                if(result.equals("fail")){
                    Toast.makeText(getApplicationContext(), "로그인 실패 입니다.", Toast.LENGTH_LONG).show();
                }
                else{
                    client.setType("회원");
                    client.setName(result);
                    intent = new Intent(getApplicationContext(), HomeActivity.class);
                    intent.putExtra("client",client);
                    startActivity(intent);
                }
            }
        });

        join_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), JoinActivity.class);
                startActivity(intent);
            }
        });

        //카카오톡 로그아웃 나중에 메인화면 안에서 다시 구성
        kakaoLogout_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                onClickLogout();
                Toast.makeText(getApplicationContext(), "카카오톡 로그아웃 되었습니다.", Toast.LENGTH_LONG).show();
            }
        });

        //카카오톡 로그인
        requestMe();
    }

    //카카오톡 로그아웃
    private void onClickLogout(){
        UserManagement.requestLogout(new LogoutResponseCallback() {
            @Override
            public void onCompleteLogout() {

            }
        });
    }

    // 카카오톡 로그인을 위한 콜백 클래스
    private class SessionCallback implements ISessionCallback {
        @Override
        public void onSessionOpened() {
            UserManagement.requestMe(new MeResponseCallback() {

                @Override
                public void onSessionClosed(ErrorResult errorResult) {
                }
                @Override
                public void onNotSignedUp() {
                }

                @Override
                public void onSuccess(UserProfile userProfile) {
                    //로그인에 성공하면 로그인한 사용자의 일련번호, 닉네임, 이미지url등을 리턴합니다.
                    //사용자 ID는 보안상의 문제로 제공하지 않고 일련번호는 제공합니다.
                    long number = userProfile.getId();

                    Log.i("userProfile",Long.toString(number));
                }
            });
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            Log.i("SessionError","kakaoSession failed");
        }
    }

    public void requestMe() {
        //카카오톡 로그인 유저의 정보를 받아오는 함수
        UserManagement.requestMe(new MeResponseCallback() {
            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                Log.d("onSessionClosed", "onSessionClosed1 =" + errorResult);
            }
            @Override
            public void onNotSignedUp() {
                //카카오톡 회원이 아닐시
                Log.d("NotSignedUp", "onNotSignedUp ");
            }
            @Override
            public void onSuccess(UserProfile result) { //로그인 성공, 원하는 정보 가져오기
                Log.e("UserProfile", result.toString());
                Log.e("UserProfile", result.getId() + "");
                //회원 정보 입력
                client.setName(result.getNickname());
                client.setType("카카오");
                //homeActivity로 전달
                intent = new Intent(getApplicationContext(), HomeActivity.class);
                intent.putExtra("client",client);
                //client.setBirth(result.get);
                startActivity(intent);
            }
        });
    }
}