package com.example.com.report;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.MediaStore;
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
                            fileName = cYear + "년_" + cMonth + "월_" + cDay + "일.txt";
                            diary.setText(readDiary());
                        }
                    }, cYear, cMonth - 1, cDay);
                    datePickerDialog.show();
                }
                return false;
            }
        });
        //저장 기능
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        // 옵션 메뉴 구현
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        final float textSize = 36;
        //다시 읽기 옵션 구현
        if (id == R.id.ReRead) {
            diary.setText(readDiary());
            return true;
        } else if(id == R.id.Delete) { //일기 삭제 기능 구현
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle(dateText.getText() + "\n 일기를 삭제하시겠습니까?");
            dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                   File file = new File(filePath + "/" + fileName);
                    file.delete();
                    diary.setText(readDiary());
                    Toast.makeText(getApplicationContext(), fileName + " 이 삭제됨", Toast.LENGTH_SHORT).show();
                }
            });
            dlg.setNegativeButton("취소",null);
            dlg.show();
        } else if(id == R.id.ToBig) {//폰트 사이즈 조절부분 구현
            diary.setTextSize(textSize * 4 / 3);

        } else if(id == R.id.ToSmall) {
            diary.setTextSize(textSize * 2 / 3);
        } else if(id == R.id.ToMidium) {
            diary.setTextSize(textSize);
        }

        return super.onOptionsItemSelected(item);
    }

    //파일을 읽어서 다이어리에 넘겨주는 함수
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

