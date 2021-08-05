package com.example.simplecalculator;

import android.util.Log;

class Calculator extends Exception {
    Stack numberStack = new Stack(10);
    Stack operatorStack = new Stack(10);
    int index = 0;
    String result;

    public Calculator(String result) {
        this.result = result;
    }

    public double calculator() {
        String keepNum = "";
        while (index < result.length() || numberStack.length() > 0) {
            if (index < result.length()) {
                char ch = result.substring(index, ++index).charAt(0);
                if (isNumber(ch)) {
                    int val = index;
                    while (isNumber(ch)) {
                        keepNum = keepNum + ch;
                        if (val == result.length()) {
                            break;
                        }
                        ch = result.substring(val, ++val).charAt(0);
                    }
                    if (val - index > 1) {
                        index = val - 1;
                    }
                    if (val == result.length()) {
                        index = result.length();
                    }
                    try {
                        numberStack.push(Long.parseLong(keepNum));
                    } catch (Exception e) {
                        Log.e("ERROR PUSH", e.getLocalizedMessage());
                    }
                    keepNum = "";
                } else {
                    if (!operatorStack.isEmpty()) {
                        double top = 0;
                        try {
                            top = operatorStack.top();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        while (operatorStack.length() >= 0 && priority(top) >= priority(ch)) {
                            double a = 0;
                            double b = 0;
                            double c = 0;
                            try {
                                a = numberStack.pop();
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e("ERROR POP", e.getLocalizedMessage());

                            }
                            try {
                                b = numberStack.pop();
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e("ERROR POP", e.getLocalizedMessage());
                            }
                            try {
                                c = operatorStack.pop();
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e("ERROR POP", e.getLocalizedMessage());

                            }
                            double re = op(a, b, c);
                            try {
                                numberStack.push(re);
                            } catch (Exception e) {

                                Log.e("ERROR PUSH", e.getLocalizedMessage());
                            }
                        }
                        try {
                            operatorStack.push(ch);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e("ERROR PUSH", e.getLocalizedMessage());
                        }
                    } else {
                        try {
                            operatorStack.push(ch);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e("ERROR PUSH", e.getLocalizedMessage());
                        }
                    }
                }
            } else {
                double a = 0;
                double b = 0;
                double c = 0;
                try {
                    a = numberStack.pop();
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("ERROR POP", e.getLocalizedMessage());

                }
                try {
                    b = numberStack.pop();
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("ERROR POP", e.getLocalizedMessage());
                }
                try {
                    c = operatorStack.pop();
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("ERROR POP", e.getLocalizedMessage());

                }
                double re = op(a, b, c);
                try {
                    numberStack.push(re);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("ERROR PUSH", e.getLocalizedMessage());
                }
            }
        }
        double top = 0;
        try {
            top = numberStack.top();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return top;
    }


    public double op(double a, double b, double op) {
        double re = 0;
        switch ((int) op) {
            case '+':
                re = a + b;
                break;
            case '-':
                re = b - a;
                break;
            case 'x':
                re = a * b;
                break;
            case '/':
                re = b / a;
                break;
        }
        return re;
    }

    public int priority(double ch) {
        int value = 0;
        if (ch == 'x' || ch == '/') {
            value = 1;
        }
        if (ch == '-' || ch == '+') {
            value = 0;
        }
        return value;
    }

    public boolean isNumber(char ch) {
        boolean is = true;
        if (ch == 'x' || ch == '/' || ch == '+' || ch == '-') {
            is = false;
        }
        return is;
    }

    public boolean isOperator(char ch) {
        return ch == 'x' || ch == '/' || ch == '+' || ch == '-';
    }


}
