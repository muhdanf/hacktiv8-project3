package com.example.finalproject3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView txtShow, txtHistory,txtResult;
    private Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn0,btnAc,btnDel,btnTitik;
    private Button btnBagi,btnKali,btnKurang,btnTambah,btnEquals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        txtResult = (TextView) findViewById(R.id.txt_layer);
        txtShow = (TextView) findViewById(R.id.txt_calculate);
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

//    public void btn_manage(View view) {
//        int angka = 0;
//        int viewID = view.getId();
//        switch (viewID) {
//            case R.id.btn_1:
//                angka = 1;
//                break;
//            case R.id.btn_2:
//                angka = 2;
//                break;
//            case R.id.btn_3:
//                angka = 3;
//                break;
//            case R.id.btn_4:
//                angka = 4;
//                break;
//            case R.id.btn_5:
//                angka = 5;
//                break;
//            case R.id.btn_6:
//                angka = 6;
//                break;
//            case R.id.btn_7:
//                angka = 7;
//                break;
//            case R.id.btn_8:
//                angka = 8;
//                break;
//            case R.id.btn_9:
//                angka = 9;
//                break;
//            case R.id.btn_0:
//                angka = 0;
//                break;
//            default:
//                break;
//        }
//        temp += String.valueOf(angka);
//        temp2 += String.valueOf(angka);
//        txtHistory.setText(temp2);
//        txtShow.setText(temp);
//    }
//
//    public void btn_operator(View view) {
//        int viewID = view.getId();
//        switch (viewID) {
//            case R.id.btn_kali:
//                firstNum = Integer.valueOf(txtShow.getText().toString());
//                txtShow.setText("");
//                operation = "*";
//                temp = "";
//                temp2 += operation;
//                txtHistory.setText(" " + temp2 + " ");
//                break;
//            case R.id.btn_bagi:
//                firstNum = Integer.valueOf(txtShow.getText().toString());
//                txtShow.setText("");
//                operation = "/";
//                temp = "";
//                temp2 += operation;
//                txtHistory.setText(" " + temp2 + " ");
//                break;
//            case R.id.btn_tambah:
//                firstNum = Integer.valueOf(txtShow.getText().toString());
//                txtShow.setText("");
//                operation = "+";
//                temp = "";
//                temp2 += operation;
//                txtHistory.setText(" " + temp2 + " ");
//                break;
//            case R.id.btn_kurang:
//                firstNum = Integer.valueOf(txtShow.getText().toString());
//                txtShow.setText("");
//                operation = "-";
//                temp = "";
//                temp2 += operation;
//                txtHistory.setText(" " + temp2 + " ");
//                break;
//            case R.id.btn_equals:
//                int answer;
//                secondNum = Integer.valueOf(txtShow.getText().toString());
//                switch (operation) {
//                    case "+":
//                        answer = firstNum + secondNum;
//                        txtShow.setText(String.valueOf(answer));
//                        temp2 = "";
//                        break;
//                    case "-":
//                        answer = firstNum - secondNum;
//                        txtShow.setText(String.valueOf(answer));
//                        temp2 = "";
//                        break;
//                    case "*":
//                        answer = firstNum * secondNum;
//                        txtShow.setText(String.valueOf(answer));
//                        temp2 = "";
//                        break;
//                    case "/":
//                        answer = firstNum / secondNum;
//                        txtShow.setText(String.valueOf(answer));
//                        temp2 = "";
//                        break;
//                }
//                temp = "";
//            default:
//                break;
//        }
//
//
//    }

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
        if(buttonText.equals("=")){
            txtShow.setText(txtResult.getText());
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