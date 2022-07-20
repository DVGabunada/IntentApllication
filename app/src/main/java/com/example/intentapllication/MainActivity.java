package com.example.intentapllication;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnWebListenerMethod();
        btnMapListenerMethod();
        btnCallLisnterMethod();
        btnSendListenerMethod();
    }

    private void btnWebListenerMethod() {
        ImageButton btnWeb = findViewById(R.id.btnWeb);
        Uri web = Uri.parse("https:");
        Intent intent = new Intent(Intent.ACTION_VIEW, web);
        btnWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

    }

    private void btnMapListenerMethod(){
        ImageButton btnMap = findViewById(R.id.btnMap);
        Uri map = Uri.parse("geo:0,0?g=Municipality+ofLiloan+Cebu+Philippines");
        Intent intent = new Intent(Intent.ACTION_VIEW, map);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }

    private void btnCallLisnterMethod(){
        ImageButton btnCall = findViewById(R.id.btnCall);
        Uri number = Uri.parse("tel:09235354873");
        Intent intent = new Intent(Intent.ACTION_DIAL, number);
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }



    private void btnSendListenerMethod() {
        Intent intent = new Intent(this, MessageActivity.class);
        Button btnSend = findViewById(R.id.btnSend);

        ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == RESULT_OK){
                            //TODO handle intent
                            Intent outIntent = result.getData();
                            boolean good = outIntent.getBooleanExtra(MessageActivity.EXTRA_RESULT, false);
                            if(good){
                                Toast.makeText(MainActivity.this, "This is a good message", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(MainActivity.this, "This is a bad message", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etMessage = findViewById(R.id.etMessage);
                String message = etMessage.getText().toString();
                intent.putExtra(MessageActivity.EXTRA_MESSAGE, message);
                launcher.launch(intent);
            }
        });
    }
}