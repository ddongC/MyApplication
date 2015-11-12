package com.example.com.diaryreport;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends Activity {
    //전역변수 설정
    TextView dateText;
    EditText diary;
    Button save;
    DatePickerDialog datePickerDialog;
    int cYear, cMonth, cDay;
    String fileName, filePath;
    File myFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("일기장 과제");
        //변수에 ID대입
        dateText = (TextView) findViewById(R.id.DateText);
        diary = (EditText) findViewById(R.id.Diary);
        save = (Button) findViewById(R.id.SAVE);
        //현재 년월일 변수에 대입
        Calendar calendar = Calendar.getInstance();
        cYear = calendar.get(Calendar.YEAR);
        cMonth = calendar.get(Calendar.MONTH) + 1;
        cDay = calendar.get(Calendar.DAY_OF_MONTH);


        fileName = cYear + "년_" + cMonth + "월_" + cDay + "일.txt";
        myFile = getExternalFilesDir("mydiary");
        filePath = myFile.getAbsolutePath();
        diary.setText(readDiary());
        //TextView에 날짜 표시 및 터치리스너 기능 구현
        dateText.setText(Integer.toString(cYear) + "년 " + Integer.toString(cMonth) + "월 " + Integer.toString(cDay) + "일");
        dateText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    //터치입력시 DatePicker Dialog로 날짜 변경하는 기능
                    datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            cYear = year;
                            cMonth = monthOfYear + 1;
                            cDay = dayOfMonth;
                            dateText.setText(Integer.toString(cYear) + "년 " + Integer.toString(cMonth) + "월 " + Integer.toString(cDay) + "일");
                        }
                    }, cYear, cMonth - 1, cDay);
                    datePickerDialog.show();
                }
                return false;
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fileName = cYear + "년_" + cMonth + "월_" + cDay + "일.txt";
                File file = new File(filePath + "/" + fileName);
                try {
                    FileOutputStream fos = new FileOutputStream(file, true);
                    String diaryContext = diary.getText().toString();
                    fos.write(diaryContext.getBytes());
                    fos.close();
                } catch (IOException e) {

                }
                Toast.makeText(getApplicationContext(), fileName + " 이 저장됨", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.ReRead) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    String readDiary() {
        String diaryContext = null;
        FileInputStream fis;
        File file = new File(filePath + "/" + fileName);
        try {
            fis = new FileInputStream(file);
            byte[] txt = new byte[500];
            fis.read(txt);
            fis.close();
            diaryContext = (new String(txt)).trim();
        } catch (IOException e) {

        }
        return diaryContext;
    }
}
