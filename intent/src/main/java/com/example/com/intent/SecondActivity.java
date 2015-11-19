package com.example.com.intent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by com on 2015-11-19.
 */
public class SecondActivity extends Activity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        setTitle("Second 액티비티");

        Intent inIntent = getIntent();
        int resultValue;
        final int operator = inIntent.getIntExtra("Operator", 0);
        //Toast.makeText(getApplicationContext(), String.valueOf(operator), Toast.LENGTH_SHORT).show();
        switch (operator) {
            case 1:
                resultValue = inIntent.getIntExtra("Num1", 0) + inIntent.getIntExtra("Num2", 0);
                break;
            case 2:
                resultValue = inIntent.getIntExtra("Num1", 0) - inIntent.getIntExtra("Num2", 0);
                break;
            case 3:
               resultValue = inIntent.getIntExtra("Num1", 0) * inIntent.getIntExtra("Num2", 0);
                break;
            case 4:
                resultValue = inIntent.getIntExtra("Num1", 0) / inIntent.getIntExtra("Num2", 0);
                break;
            default:
                resultValue = 0;
                Toast.makeText(getApplicationContext(), "잘못 입력하셨습니다",Toast.LENGTH_SHORT).show();
                break;
        }
        final int result = resultValue;

        Button btnReturn = (Button) findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent outIntent = new Intent(getApplicationContext(), MainActivity.class);
                outIntent.putExtra("Result", result);
                setResult(RESULT_OK, outIntent);
                finish();
            }
        });
    }
}
