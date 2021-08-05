package com.example.simplecalculator;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button button1, button2, button3, button4, button5, button6, button7, button8, button9, button0;
    Button buttonClearAll, buttonClear;
    ImageButton buttonBackSpace;
    Button buttonAddComma, buttonPercentage, buttonDivide, buttonMultiple, buttonAdd, buttonMinus, buttonEqual;
    TextView textViewResult;


    private SpannableStringBuilder mSpannableStringBuilder;

    private static final Map<String, Integer> COLORS_MAP = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        changeColor(R.color.statusbarcolor, R.color.bottommenucolor);

        mSpannableStringBuilder = new SpannableStringBuilder();

        COLORS_MAP.put("+", Color.GREEN);
        COLORS_MAP.put("/", Color.GREEN);
        COLORS_MAP.put("-", Color.GREEN);
        COLORS_MAP.put("x", Color.GREEN);

        initializeVariable();
        setFunctionalButton();
    }

    public void initializeVariable() {
        editText = findViewById(R.id.textView);
        editText.setText("");
        editText.setShowSoftInputOnFocus(false);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            String lastText = null;

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (s.length() > 0){
//                    editText.removeTextChangedListener(this);
//                    int starter = s.length() - 1;
//                    String lastChar = s.toString().substring(starter);
//
//                    SpannableString lastSpannableChar = new SpannableString(lastChar);
//
//                    // pick the color based on the last char
//                    int color = pickColorByChar(lastChar);
//
//                    // Span to set char color
//                    ForegroundColorSpan fcs = new ForegroundColorSpan(color);
//
//                    // Set the text color for the last character
//                    lastSpannableChar.setSpan(fcs, 0, 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
//
//                    // append the last char to the string builder so you can keep the previous span
//                    mSpannableStringBuilder.append(lastSpannableChar);
//
//                    editText.setText(mSpannableStringBuilder);
//                    editText.setSelection(editText.getText().length()); //this is to move the cursor position
//
//                    editText.addTextChangedListener(this);
//                }
                Log.d("", "lastText='" + lastText + "'");
                Log.d("", "s='" + s + "'");
                if (!s.toString().equals(lastText)) {
                    lastText = s.toString();

                    String res = "";
                    char[] split = s.toString().toCharArray();
                    for (char c : split) {
                        String color = null;
                        if (c == '+' || c == '/' || c == 'x' || c == '-') {
                            color = "#FF9404";
                        }
                        // etc...
                        if (color != null) {
                            res += "<font color=\"" + color + "\">" + c
                                    + "</font>";
                        } else {
                            res += c;
                        }
                    }
                    int selectStart = editText.getSelectionStart();
                    int selectEnd = editText.getSelectionEnd();
                    editText.setText(Html.fromHtml(res));
                    editText.setSelection(selectStart, selectEnd);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        button0 = findViewById(R.id.zero_btn);
        button1 = findViewById(R.id.one_btn);
        button2 = findViewById(R.id.two_btn);
        button3 = findViewById(R.id.three_btn);
        button4 = findViewById(R.id.four_btn);
        button5 = findViewById(R.id.five_btn);
        button6 = findViewById(R.id.six_btn);
        button7 = findViewById(R.id.seven_btn);
        button8 = findViewById(R.id.eight_btn);
        button9 = findViewById(R.id.nine_btn);

        buttonClear = findViewById(R.id.clear_btn);
        buttonClearAll = findViewById(R.id.clear_everything_btn);
        buttonBackSpace = findViewById(R.id.backspace_btn);

        buttonAdd = findViewById(R.id.addition_btn);
        buttonMultiple = findViewById(R.id.multipy_btn);
        buttonDivide = findViewById(R.id.divide_btn);
        buttonMinus = findViewById(R.id.subtract_btn);

        buttonEqual = findViewById(R.id.equal_btn);

        buttonAddComma = findViewById(R.id.dot_btn);
        buttonPercentage = findViewById(R.id.percent_btn);

        textViewResult = findViewById(R.id.result);
    }

    public void changeColor(int statusBarColor, int navBarColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), statusBarColor));
            window.setNavigationBarColor(ContextCompat.getColor(getApplicationContext(), navBarColor));
        }
    }

    public void appendDigitOnScreen(String text) {
        String textBefore = editText.getText().toString();

        if (textBefore.length() > 0 && textBefore.charAt(0) == '0') {
            editText.getText().clear();
            editText.getText().insert(editText.getSelectionStart(), text);
        } else {
            if (text.equals("-") || text.equals("+") || text.equals("x") || text.equals("/")) {
                if (textBefore.length() > 1) {
                    String lastText = textBefore.substring(textBefore.length() - 1, textBefore.length());

                    if (!lastText.equals("-") && !lastText.equals("+") && !lastText.equals("/") && !lastText.equals("x")) {
                        editText.getText().insert(editText.getSelectionStart(), text);

                        Log.e("DATA LAST", String.valueOf(textBefore.charAt(textBefore.length() - 1)));
                        Log.e("KONDISI", String.valueOf(!lastText.equals("-") || !lastText.equals("+") || !lastText.equals("/") || !lastText.equals("x")));
                    }
                }
            } else {
                editText.getText().insert(editText.getSelectionStart(), text);
            }
        }


    }

    public void setFunctionalButton() {
        button1.setOnClickListener(v -> {
            appendDigitOnScreen("1");
        });
        button2.setOnClickListener(v -> {
            appendDigitOnScreen("2");
        });
        button3.setOnClickListener(v -> {
            appendDigitOnScreen("3");
        });
        button4.setOnClickListener(v -> {
            appendDigitOnScreen("4");
        });
        button5.setOnClickListener(v -> {
            appendDigitOnScreen("5");
        });
        button6.setOnClickListener(v -> {
            appendDigitOnScreen("6");
        });
        button7.setOnClickListener(v -> {
            appendDigitOnScreen("7");
        });
        button8.setOnClickListener(v -> {
            appendDigitOnScreen("8");
        });
        button9.setOnClickListener(v -> {
            appendDigitOnScreen("9");
        });
        button0.setOnClickListener(v -> {
            appendDigitOnScreen("0");
        });
        buttonAddComma.setOnClickListener(v -> appendDigitOnScreen("."));
        buttonAdd.setOnClickListener(v -> appendDigitOnScreen("+"));
        buttonMinus.setOnClickListener(v -> appendDigitOnScreen("-"));
        buttonDivide.setOnClickListener(v -> appendDigitOnScreen("/"));
        buttonMultiple.setOnClickListener(v -> appendDigitOnScreen("x"));

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteText();
            }
        });

        buttonBackSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteText();
            }
        });


        buttonClearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.getText().clear();
                textViewResult.setText("0");
            }
        });

        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calculator calculator = new Calculator(editText.getText().toString());
                textViewResult.setText(String.valueOf(calculator.calculator()));
            }
        });
    }

    public void deleteText() {
        if (editText.getText().toString().length() > 0 && editText.getSelectionStart() > 0) {
            editText.getText().delete(editText.getSelectionStart() - 1, editText.getSelectionStart());
        } else if (editText.getText().toString().length() > 0 && editText.getSelectionStart() == 0) {
            editText.getText().delete(editText.getSelectionStart(), editText.getSelectionStart() + 1);
        }
    }

}