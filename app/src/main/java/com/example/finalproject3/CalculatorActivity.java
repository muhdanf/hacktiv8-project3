package com.example.finalproject3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.finalproject3.Database.SQLiteHelper;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView txtShow, txtHistory,txtResult;
    private Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn0,btnAc,btnDel,btnTitik;
    private Button btnBagi,btnKali,btnKurang,btnTambah,btnEquals;
    private SQLiteHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        txtResult = (TextView) findViewById(R.id.txt_layer);
        txtShow = (TextView) findViewById(R.id.txt_calculate);
        txtShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CalculatorActivity.this, HistoryActivity.class);
                startActivity(i);
            }
        });
        txtHistory = (TextView) findViewById(R.id.txtHistory);
        txtShow.setText("");
        txtHistory.setText("");
        txtResult.setText("");
        assignId(btn1,R.id.btn_1);
        assignId(btn2,R.id.btn_2);
        assignId(btn3,R.id.btn_3);
        assignId(btn4,R.id.btn_4);
        assignId(btn5,R.id.btn_5);
        assignId(btn6,R.id.btn_6);
        assignId(btn7,R.id.btn_7);
        assignId(btn8,R.id.btn_8);
        assignId(btn9,R.id.btn_9);
        assignId(btn0,R.id.btn_0);
        assignId(btnAc,R.id.btn_ac);
        assignId(btnDel,R.id.btn_delete);
        assignId(btnTitik,R.id.btn_titik);
        assignId(btnBagi,R.id.btn_bagi);
        assignId(btnKali,R.id.btn_kali);
        assignId(btnKurang,R.id.btn_kurang);
        assignId(btnTambah,R.id.btn_tambah);
        assignId(btnEquals,R.id.btn_equals);


    }

    void assignId(Button btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();
        String calculateData = txtShow.getText().toString();

        if (buttonText.equals("AC")){
            txtShow.setText("");
            txtHistory.setText("");
            txtResult.setText("0");
            return;
        }

        if(buttonText.equals("Del")){
            calculateData = calculateData.substring(0,calculateData.length()-1);
        }
        else{
            calculateData = calculateData+buttonText;
        }
        txtShow.setText(calculateData);
        calculateData = calculateData.replace("x","*");

        String finalResult = getResult(calculateData);
        if(!finalResult.equals("error")){
            txtResult.setText(finalResult);
        }
        if(buttonText.equals("=")){
            txtShow.setText(txtResult.getText());
            database = new SQLiteHelper(this);
            String data = calculateData + txtResult.getText().toString();
            database.addData(data);
            System.out.println("data berhasil di tambahkan");
            return;
        }

    }

    String getResult(String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();
            String finalResult =  context.evaluateString(scriptable, data,"Javascript",1,null).toString();
            return finalResult;
        }catch (Exception e){
            return "error";
        }
    }
}