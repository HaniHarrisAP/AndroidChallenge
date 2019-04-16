package com.thedeveloper.android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText et1=(EditText)findViewById(R.id.et1);
        final EditText et2=(EditText)findViewById(R.id.et2);
        Button btn=(Button)findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=et1.getText().toString().toLowerCase().trim();
                String password=et2.getText().toString().toLowerCase().trim();
                if(username.length()==0 && password.length()==0)
                {
                    Toast.makeText(Register.this,"Enter UserName and Password",Toast.LENGTH_LONG).show();
                    et1.requestFocus();
                }
                else  if(username.length()==0)
                {
                    Toast.makeText(Register.this,"Enter UserName",Toast.LENGTH_LONG).show();
                    et1.requestFocus();
                }
                else  if(password.length()==0)
                {
                    Toast.makeText(Register.this,"Enter Password",Toast.LENGTH_LONG).show();
                    et2.requestFocus();
                }
                else
                {
                    DatabaseHandler db = new DatabaseHandler(Register.this);

                    Users userdetails = db.getUsers2(username);

                    if(userdetails!=null)
                    {
                        Toast.makeText(Register.this,"User Exist",Toast.LENGTH_LONG).show();
                        et1.requestFocus();
                    }
                    else
                    {
                        String encryptedString = AES.encrypt(password,"123456");
                        db.addUsers(new Users(username,encryptedString));
                        Toast.makeText(Register.this,"User Added",Toast.LENGTH_LONG).show();
                    }

                }

            }
        });


    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.slide_in2,R.anim.slide_out2);
    }
}
