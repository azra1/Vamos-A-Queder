package com.techfreaks.vamosaquedar;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //FrameLayout frameLayout=findViewById(R.id.backgroundFrameLayout);
        //frameLayout.addView(new MyGifView(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== R.id.action_add)
        {
            startActivity(new Intent(Main2Activity.this, Postevent.class));
        }
        else if(item.getItemId()==R.id.action_logout)
        {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(Main2Activity.this);
            builder1.setTitle("Log Out");
            builder1.setMessage("Are you sure you want to LOG OUT?");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "Yes",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            FirebaseAuth.getInstance().signOut();
                            startActivity(new Intent(Main2Activity.this, MainActivity.class));
                            Toast.makeText(getApplicationContext(),"Successfully logged out",Toast.LENGTH_SHORT).show();
                            dialog.cancel();
                        }
                    });

            builder1.setNegativeButton(
                    "Cancel",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();

        }
        return super.onOptionsItemSelected(item);
    }
}
