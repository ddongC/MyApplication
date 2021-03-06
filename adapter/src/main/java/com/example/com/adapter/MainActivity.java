package com.example.com.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Objects;
import java.util.zip.Inflater;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("그리드 뷰 영화 포스터");

        final GridView gv = (GridView)findViewById(R.id.gridView1);
        MyGridAdapter gAdapter = new MyGridAdapter(this);
        gv.setAdapter(gAdapter);
    }

    public class MyGridAdapter extends BaseAdapter {
        LayoutInflater inflater;
        Context context;
        Integer[] posterID = {
                R.drawable.movie0, R.drawable.movie1, R.drawable.movie2,
                R.drawable.movie3, R.drawable.movie4, R.drawable.movie5,
                R.drawable.movie6, R.drawable.movie7, R.drawable.movie8,
                R.drawable.movie9
        };
        String[] posterName = {
                "PAWFASSIONAL", "ROCKY", "THE SHINING", "Feng Shui", "ROBOCOP", "GHOST WRITER",
                "TITANIC", "Malabar Princess", "MATILDA", "Swinging Barmaids"
        };


        public MyGridAdapter(Context c) {
            context = c;
            inflater = LayoutInflater.from(context);
        }

        public int getCount() {
            return posterID.length;
        }

        public Object getItem(int arg0) {
            return 0;
        }

        public long getItemId(int arg0) {
            return  0;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.dialog, null);

            ImageView imageView = (ImageView)view.findViewById(R.id.ivPoster);
            TextView textView = (TextView)view.findViewById(R.id.ivPosterName);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setPadding(5, 5, 5, 5);

            final int pos = position;
            imageView.setImageResource(posterID[pos]);
            textView.setText(posterName[pos]);

            imageView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    View dialogView = (View) View.inflate(MainActivity.this, R.layout.dialog, null);
                    AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                    ImageView ivPoster = (ImageView) dialogView.findViewById(R.id.ivPoster);
                    ivPoster.setImageResource(posterID[pos]);
                    dlg.setTitle(posterName[pos]);
                    dlg.setIcon(R.drawable.images);
                    dlg.setView(dialogView);
                    dlg.setNegativeButton("닫기", null);
                    dlg.show();
                }
            });
            return view;
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
