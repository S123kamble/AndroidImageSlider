package com.felixtechlabs.androidimageslider.view;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.felixtechlabs.androidimageslider.R;

public class ViewDialog {

    Button button1,button2,button3,button4,button5,
            button6,button7,button8,button9,button0,
            buttonAdd,buttonSub,buttonMul,buttonDiv,buttonDot,
            buttonEqual,buttonClear;

    TextView answer;
    double value1,value2;
    boolean add,sub,mul,div;

    public void showDialog(Activity activity, String msg){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.calcy);

        dialog.show();


        button1 = dialog.findViewById(R.id.button1);
        button2 = dialog.findViewById(R.id.button2);
        button3 = dialog.findViewById(R.id.button3);
        button4 = dialog.findViewById(R.id.button4);
        button5 = dialog.findViewById(R.id.button5);
        button6 = dialog.findViewById(R.id.button6);
        button7 = dialog.findViewById(R.id.button7);
        button8 = dialog.findViewById(R.id.button8);
        button9 = dialog.findViewById(R.id.button9);
        button0 = dialog.findViewById(R.id.button0);

        buttonAdd = dialog.findViewById(R.id.buttonAdd);
        buttonSub = dialog.findViewById(R.id.buttonSub);
        buttonMul = dialog.findViewById(R.id.buttonMul);
        buttonDiv = dialog.findViewById(R.id.buttonDiv);

        buttonDot = dialog.findViewById(R.id.buttonDot);
        buttonEqual = dialog.findViewById(R.id.buttonEqual);
        buttonClear = dialog.findViewById(R.id.buttonClear);

        answer = dialog.findViewById(R.id.answer);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer.setText(answer.getText()+"1");
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer.setText(answer.getText()+"2");
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer.setText(answer.getText()+"3");
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer.setText(answer.getText()+"4");
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer.setText(answer.getText()+"5");
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer.setText(answer.getText()+"6");
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer.setText(answer.getText()+"7");
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer.setText(answer.getText()+"8");
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer.setText(answer.getText()+"9");
            }
        });
        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer.setText(answer.getText()+"0");
            }
        });
        buttonDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer.setText(answer.getText()+".");
            }
        });
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                value1 = Double.parseDouble(answer.getText()+"");
                add=true;
                answer.setText(null);
            }
        });
        buttonSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                value1 = Double.parseDouble(answer.getText()+"");
                sub=true;
                answer.setText(null);
            }
        });
        buttonMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               try {
                   value1 = Double.parseDouble(answer.getText() + "");
               }catch (NumberFormatException e) {
                   mul = true;
               }
                   answer.setText(null);

            }
        });
        buttonDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                value1 = Double.parseDouble(answer.getText()+"");
                div=true;
                answer.setText(null);
            }
        });
        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                value2 = Double.parseDouble(answer.getText()+"");
                if (add==true){
                    answer.setText(value1+value2+"");
                    add=false;
                }
                if (sub==true){
                    answer.setText(value1-value2+"");
                    sub=false;
                }
                if (mul==true){
                    answer.setText(value1*value2+"");
                    mul=false;
                }
                if (div==true){
                    answer.setText(value1/value2+"");
                    div=false;
                }
            }
        });
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer.setText(null);
            }
        });
    }
}
