package com.jakefidler.calculator_jfidl3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.mariuszgromada.math.mxparser.*;

public class MainActivity extends AppCompatActivity {

    private TextView previousCalculation;
    private EditText display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        previousCalculation = findViewById(R.id.previousCalculationView);

        display = findViewById(R.id.display);
        display.setShowSoftInputOnFocus(false);
    }

    private void updateText(String strToAdd){
        String oldStr = display.getText().toString();
        int cursorPos = display.getSelectionStart();
        String leftStr = oldStr.substring(0, cursorPos);
        String rightStr = oldStr.substring(cursorPos);
        if (getString(R.string.display).equals(display.getText().toString())){
            display.setText(strToAdd);
        }
        else{
            display.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
        }

        display.setSelection(cursorPos + 1);
    }

    public void clearButton(View view) {
        display.setText("");
        previousCalculation.setText("");
    }

    public void parenthesisButton(View view) {
        int cursorPos = display.getSelectionStart();
        int openPar = 0;
        int closePar = 0;
        int textLen = display.getText().length();

        for (int i = 0; i < cursorPos; i++){
            if (display.getText().toString().substring(i, i+1).equals("(")){
                openPar += 1;
            }
            if (display.getText().toString().substring(i, i+1).equals(")")){
                closePar += 1;
            }
        }

        if (openPar == closePar || display.getText().toString().substring(textLen-1, textLen).equals("(")){
            updateText("(");
            display.setSelection(cursorPos + 1);
        }
        else if (closePar < openPar && !display.getText().toString().substring(textLen-1, textLen).equals("(")){
            updateText(")");
            display.setSelection(cursorPos + 1);
        }
    }

    public void exponentButton(View view) {
        updateText("^");
    }

    public void divideButton(View view) {
        updateText("/");
    }

    public void sevenButton(View view) {
        updateText("7");
    }

    public void eightButton(View view) {
        updateText("8");
    }

    public void nineButton(View view) {
        updateText("9");
    }

    public void multiplyButton(View view) {
        updateText("*");
    }

    public void fourButton(View view) {
        updateText("4");
    }

    public void fiveButton(View view) {
        updateText("5");
    }

    public void sixButton(View view) {
        updateText("6");
    }

    public void subtractButton(View view) {
        updateText("-");
    }

    public void oneButton(View view) {
        updateText("1");
    }

    public void twoButton(View view) {
        updateText("2");
    }

    public void threeButton(View view) {
        updateText("3");
    }

    public void addButton(View view) {
        updateText("+");
    }

    public void invertButton(View view) {
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();

        if (textLen != 0){
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.insert(0, "-(");
            selection.append(")");
            display.setText(selection);
            display.setSelection(cursorPos + 3);
        }
    }

    public void zeroButton(View view) {
        updateText("0");
    }

    public void decimalButton(View view) {
        updateText(".");
    }

    public void equalsButton(View view) {
        String userExp = display.getText().toString();

        previousCalculation.setText(userExp);

        Expression exp = new Expression(userExp);
        String result = String.valueOf(exp.calculate());

        display.setText(result);
        display.setSelection(result.length());
    }

    public void deleteButton(View view) {
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();

        if (cursorPos != 0 && textLen != 0){
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorPos - 1, cursorPos, "");
            display.setText(selection);
            display.setSelection(cursorPos - 1);
        }
    }
}