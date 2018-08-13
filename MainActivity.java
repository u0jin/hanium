package unithon4.com.apitest;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Intent intent;
    SpeechRecognizer mRecognizer;
    TextView textView;
    private final int MY_PERMISSIONS_RECORD_AUDIO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.RECORD_AUDIO)) {

            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.RECORD_AUDIO}, MY_PERMISSIONS_RECORD_AUDIO
                );
            }
        }

        intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getPackageName());
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ko-KR");

        mRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        mRecognizer.setRecognitionListener(recognitionListener);


        textView = (TextView) findViewById(R.id.textView);

        Button button = (Button) findViewById(R.id.Btn1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRecognizer.startListening(intent);
            }
        });

    }

            private RecognitionListener recognitionListener = new RecognitionListener() {
                @Override
                public void onReadyForSpeech(Bundle bundle) {
                }

                @Override
                public void onBeginningOfSpeech() {
                }

                @Override
                public void onRmsChanged(float v) {
                }

                @Override
                public void onBufferReceived(byte[] bytes) {
                }

                @Override
                public void onEndOfSpeech() {
                }

                @Override
                public void onError(int i) {
                    textView.setText("너무 늦게 말하면 오류뜹니다");

                }

                @Override
                public void onResults(Bundle bundle) {
                    String key = "";
                    key = SpeechRecognizer.RESULTS_RECOGNITION;
                    ArrayList<String> mResult = bundle.getStringArrayList(key);

                    String[] rs = new String[mResult.size()];
                    mResult.toArray(rs);

                    textView.setText(rs[0]);
                }

                @Override
                public void onPartialResults(Bundle bundle) {
                }

                @Override
                public void onEvent(int i, Bundle bundle) {
                }
            };
        }