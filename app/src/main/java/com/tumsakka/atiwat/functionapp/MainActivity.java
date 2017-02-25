package com.tumsakka.atiwat.functionapp;

import android.content.Intent;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import static com.tumsakka.atiwat.functionapp.R.drawable.user;

public class MainActivity extends AppCompatActivity {

    private Button btnloginButton;
    private EditText editTextuser, editTextpass;
    private String jsonres;
    private Boolean Status;
    private String Message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextuser = (EditText) findViewById(R.id.usertext);
        editTextpass = (EditText) findViewById(R.id.passtext);
        btnloginButton = (Button) findViewById(R.id.btnlogin);

        btnloginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "5555",Toast.LENGTH_SHORT).show();

                if (editTextuser.getText().toString().equals("") || editTextuser.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "กรุณากรอกให้ครบทุกช่อง", Toast.LENGTH_SHORT).show();
                }else {
//                    Toast.makeText(MainActivity.this, "Login Pass", Toast.LENGTH_LONG).show();
                    try {
                        Myconfig myconfig = new Myconfig();
                        GetDataToServer getDataToServer = new GetDataToServer(MainActivity.this);
                        getDataToServer.execute(myconfig.getServiceLogin() +
                                "user=" + editTextuser.getText().toString() + "&" +
                                "pass=" + editTextpass.getText().toString());

                        jsonres = getDataToServer.get();
                        JSONObject jsonObject = new JSONObject(jsonres);
                        Status = jsonObject.getBoolean("status");
                        Message = jsonObject.getString("message");

                        if (Status == true) {
                            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this, Message ,Toast.LENGTH_LONG).show();
                        }

                        Toast.makeText(MainActivity.this, Status.toString() ,Toast.LENGTH_LONG).show();
                    } catch (Exception e) {


                    }



                }
            }
        });

    }// Main Class
}
