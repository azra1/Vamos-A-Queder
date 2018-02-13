package com.techfreaks.vamosaquedar;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import static android.media.MediaRecorder.VideoSource.CAMERA;
import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;

public class Postevent extends AppCompatActivity {
    Button submit;
    EditText address, eventname, hosted, brief, date;
    EditText category;
    Context context;
    static final int REQUEST_IMAGE_CAPTURE = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity activity = (Activity) context;
        setContentView(R.layout.activity_postevent);
        submit = findViewById(R.id.submit);
        address = findViewById(R.id.address);
        eventname = findViewById(R.id.eventname);
        hosted = findViewById(R.id.hosted);
        brief = findViewById(R.id.date);
        category = findViewById(R.id.editText);
        date = findViewById(R.id.brief);
        submit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //Category pop up work started
        category.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupWindow popUp = popupWindowsort();
                popUp.showAsDropDown(v, 0, 0);

            }
        });
        date.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Calendar newCalendar = Calendar.getInstance();
                int month = newCalendar.get(Calendar.MONTH);
                int year = newCalendar.get(Calendar.YEAR);
                int currentDay = newCalendar.get(Calendar.DAY_OF_MONTH);
                showDatePicker(Postevent.this, year, month, currentDay);
            }

        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.post_event_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add_photos) {
            final CharSequence[] items = {"Choose from Library",
                    "Cancel"};

            AlertDialog.Builder builder = new AlertDialog.Builder(Postevent.this);
            builder.setTitle("Add Photo!");
        }
        selectImage();
        return super.onOptionsItemSelected(item);
    }

    private PopupWindow popupWindowsort() {

        // initialize a pop up window type
        final PopupWindow popupWindow = new PopupWindow(this);

        String[] sortList = {"Theme Based", "Social Services", "Free Event", "Open for All", "Others"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,
                sortList);
        // the drop down list is a list view
        final ListView listViewSort = new ListView(this);

        // set our adapter and pass our pop up window contents
        listViewSort.setAdapter(adapter);

        // set on item selected
        listViewSort.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        category.setText("Theme Based");
                        break;
                    case 1:
                        category.setText("Social Services");
                        break;
                    case 2:
                        category.setText("Free Event");
                        break;
                    case 3:
                        category.setText("Open for All");
                        break;
                    default:
                        category.setText("Others");
                        break;
                }
                popupWindow.dismiss();

            }
        });

        // some other visual settings for popup window
        popupWindow.setFocusable(true);
        popupWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);

        // set the listview as popup content
        popupWindow.setContentView(listViewSort);

        return popupWindow;
    }

    public void showDatePicker(Context context, int initialYear, int initialMonth, int initialDay) {

        DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int year, int month, int day) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(year, month, day);
                        // epochTime = calendar.getTime().getTime();
                        date.setText(day + "/" + (month + 1) + "/" + year);
                    }
                }, initialYear, initialMonth, initialDay);
        datePickerDialog.setTitle("Select Date");
        datePickerDialog.show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Uri selectedimg = data.getData();
            Log.i("ImageData", selectedimg + "");
        }
    }


    private void selectImage() {
        final CharSequence[] items = {"Choose from Library",
                "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(Postevent.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (items[item].equals("Choose from Library")) {
                        galleryIntent();
                    }

                else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }



    private void galleryIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Choose Picture"), 1);


    }

}

