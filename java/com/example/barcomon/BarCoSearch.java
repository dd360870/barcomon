package com.example.barcomon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BarCoSearch extends AppCompatActivity {

    private TextView formatTxt, contentTxt;
    private EditText editReview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_co_search);

        formatTxt = (TextView)findViewById(R.id.scan_format_review);
        contentTxt = (TextView)findViewById(R.id.scan_content_review);
        editReview = (EditText)findViewById(R.id.edit_review);

        editReview.setText("Input");
        //改变默认的单行模式
        editReview.setSingleLine(false);
//水平滚动设置为False
        editReview.setHorizontallyScrolling(false);

        IntentIntegrator scanIntegrator = new IntentIntegrator(this);
        scanIntegrator.initiateScan();

        //Bundle bundle_from_MainActivity = getIntent().getExtras();

        // String scanFormat =  bundle_from_MainActivity.getString("scanFormat");
        //String scanContent = bundle_from_MainActivity.getString("scanContent");

        //formatTxt.setText(scanFormat);
        //contentTxt.setText(scanContent);

    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {


            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();
            formatTxt.setText("FORMAT: " + scanFormat);
            contentTxt.setText("CONTENT: " + scanContent);

        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private void UpdateDatabase(){

    }
}
