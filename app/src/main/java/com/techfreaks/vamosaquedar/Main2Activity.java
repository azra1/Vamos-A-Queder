package com.techfreaks.vamosaquedar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

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
        return super.onOptionsItemSelected(item);
    }
}
