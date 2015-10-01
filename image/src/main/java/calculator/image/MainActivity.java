package calculator.image;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

    TextView text1, text2;
    Switch chkAgree;
    RadioGroup rGroup1;
    RadioButton rdoDog, rdoCat, rdoRabbit;
    ImageView imgPet;
    Button restart, exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("안드로이드 사진 보기");

        text1 = (TextView) findViewById(R.id.Text1);
        chkAgree = (Switch) findViewById(R.id.ChkAgree);
        text2 = (TextView) findViewById(R.id.Text2);
        rGroup1 = (RadioGroup) findViewById(R.id.Rgroup1);
        rdoDog = (RadioButton) findViewById(R.id.RdoDog);
        rdoCat = (RadioButton) findViewById(R.id.RdoCat);
        rdoRabbit = (RadioButton) findViewById(R.id.RdoRabbit);
        imgPet = (ImageView) findViewById(R.id.ImgPet);
        restart = (Button)findViewById(R.id.Restart);
        exit = (Button)findViewById(R.id.Exit);

        chkAgree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chkAgree.isChecked() == true) {
                    text2.setVisibility(View.VISIBLE);
                    rGroup1.setVisibility(View.VISIBLE);
                    imgPet.setVisibility(View.VISIBLE);
                    restart.setVisibility(View.VISIBLE);
                    exit.setVisibility(View.VISIBLE);
                } else {
                    text2.setVisibility(View.INVISIBLE);
                    rGroup1.setVisibility(View.INVISIBLE);
                    imgPet.setVisibility(View.INVISIBLE);
                    restart.setVisibility(View.INVISIBLE);
                    exit.setVisibility(View.INVISIBLE);
                }
            }
        });

        rdoDog.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                imgPet.setImageResource(R.drawable.dog);
            }
        });

        rdoCat.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                imgPet.setImageResource(R.drawable.cat);
            }
        });

        rdoRabbit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                imgPet.setImageResource(R.drawable.rabbit);
            }
        });

        exit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        restart.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                chkAgree.setChecked(false);
            }
        });
    }
}
