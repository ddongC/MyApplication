package com.example.com.intent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("메인 액티비티");

        Button btnNewActivity = (Button)findViewById(R.id.btnNewActivity);
        final RadioGroup radioGroup = (RadioGroup)findViewById(R.id.RG);
        RadioButton btnAdd = (RadioButton)findViewById(R.id.btnAdd);
        RadioButton btnSub = (RadioButton)findViewById(R.id.btnSub);
        RadioButton btnMul = (RadioButton)findViewById(R.id.btnMul);
        RadioButton btnDiv = (RadioButton)findViewById(R.id.btnDiv);

        btnNewActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edtNum1 = (EditText) findViewById(R.id.edtNum1);
                EditText edtNum2 = (EditText) findViewById(R.id.edtNum2);
                int operator;

                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.btnAdd:
                        operator = 1;
                        break;
                    case R.id.btnSub:
                        operator = 2;
                        break;
                    case R.id.btnMul:
                        operator = 3;
                        break;
                    case R.id.btnDiv:
                        operator = 4;
                        break;
                    default:
                        operator = 0;
                        Toast.makeText(getApplicationContext(),"연산자를 선택하세요", Toast.LENGTH_SHORT).show();
                        break;
                }
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                intent.putExtra("Num1", Integer.parseInt(edtNum1.getText().toString()));
                intent.putExtra("Num2", Integer.parseInt(edtNum2.getText().toString()));
                intent.putExtra("Operator", operator);
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK) {
            int result = data.getIntExtra("Result", 0);
            Toast.makeText(getApplicationContext(), "결과 :" + result, Toast.LENGTH_SHORT).show();
        }
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
