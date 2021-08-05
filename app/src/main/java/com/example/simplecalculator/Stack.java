package com.example.simplecalculator;

class Stack {
    private int maxSize;
    private int top = -1;
    private double[] stack;

    public Stack(int maxSize) {
        this.maxSize = maxSize;
        stack = new double[maxSize];
    }

    public boolean isFull() {
        return (maxSize - 1) == top;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public double top() throws Exception{
        if (isEmpty()) {
            throw new Exception("Stack is Empty,it can not pop data!");
        }
        return stack[top];
    }

    public void push(double value) throws Exception {
        if (value < Long.MAX_VALUE){
            if (isFull()) {
                throw new Exception("Stack is full,it can not add data!");
            }
            stack[++top] = value;
        }else{
            throw new Exception("Long to large");
        }
    }

    public double pop() throws Exception {
        if (isEmpty()) {
            throw new Exception("Stack is Empty,it can not pop data!");
        }
        double value = stack[top--];
        return value;
    }

    public int length(){
        return top;
    }
}
