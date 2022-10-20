package com.example.finalproject3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CalculatorActivity extends AppCompatActivity {

    private TextView txtShow, txtHistory;
    private String temp = "", temp2 = " ";
    private int firstNum;
    private int secondNum;
    private String operation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        txtShow = (TextView) findViewById(R.id.txt_layer);
        txtHistory = (TextView) findViewById(R.id.txtHistory);
        txtShow.setText("");
        txtHistory.setText("");
    }

    public void btn_manage(View view) {
        int angka = 0;
        int viewID = view.getId();
        switch (viewID) {
            case R.id.btn_1:
                angka = 1;
                break;
            case R.id.btn_2:
                angka = 2;
                break;
            case R.id.btn_3:
                angka = 3;
                break;
            case R.id.btn_4:
                angka = 4;
                break;
            case R.id.btn_5:
                angka = 5;
                break;
            case R.id.btn_6:
                angka = 6;
                break;
            case R.id.btn_7:
                angka = 7;
                break;
            case R.id.btn_8:
                angka = 8;
                break;
            case R.id.btn_9:
                angka = 9;
                break;
            case R.id.btn_0:
                angka = 0;
                break;
            default:
                break;
        }
        temp += String.valueOf(angka);
        temp2 += String.valueOf(angka);
        txtHistory.setText(temp2);
        txtShow.setText(temp);
    }

    public void btn_operator(View view) {
        int viewID = view.getId();
        switch (viewID) {
            case R.id.btn_kali:
                firstNum = Integer.valueOf(txtShow.getText().toString());
                txtShow.setText("");
                operation = "*";
                temp = "";
                temp2 += operation;
                txtHistory.setText(" " + temp2 + " ");
                break;
            case R.id.btn_bagi:
                firstNum = Integer.valueOf(txtShow.getText().toString());
                txtShow.setText("");
                operation = "/";
                temp = "";
                temp2 += operation;
                txtHistory.setText(" " + temp2 + " ");
                break;
            case R.id.btn_tambah:
                firstNum = Integer.valueOf(txtShow.getText().toString());
                txtShow.setText("");
                operation = "+";
                temp = "";
                temp2 += operation;
                txtHistory.setText(" " + temp2 + " ");
                break;
            case R.id.btn_kurang:
                firstNum = Integer.valueOf(txtShow.getText().toString());
                txtShow.setText("");
                operation = "-";
                temp = "";
                temp2 += operation;
                txtHistory.setText(" " + temp2 + " ");
                break;
            case R.id.btn_equals:
                int answer;
                secondNum = Integer.valueOf(txtShow.getText().toString());
                switch (operation) {
                    case "+":
                        answer = firstNum + secondNum;
                        txtShow.setText(String.valueOf(answer));
                        temp2 = "";
                        break;
                    case "-":
                        answer = firstNum - secondNum;
                        txtShow.setText(String.valueOf(answer));
                        temp2 = "";
                        break;
                    case "*":
                        answer = firstNum * secondNum;
                        txtShow.setText(String.valueOf(answer));
                        temp2 = "";
                        break;
                    case "/":
                        answer = firstNum / secondNum;
                        txtShow.setText(String.valueOf(answer));
                        temp2 = "";
                        break;
                }
                temp = "";
            default:
                break;
        }


    }
}