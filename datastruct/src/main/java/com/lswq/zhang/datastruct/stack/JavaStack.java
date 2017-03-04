package com.lswq.zhang.datastruct.stack;


import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by zhangsw on 2016/12/9.
 */
public class JavaStack {

    private static boolean isPaired(char left, char right){
        return (left == '(' && right == ')') || (left == '[' && right == ']') || (left == '{' && right == '}');
    }


    public static boolean checkBalanceByStack(String expression) {

        Stack<Character> index = new Stack<>();

        char[] chars = expression.toCharArray();

        boolean isBalanced = true;

        for (char c: chars) {

            if (!isBalanced) {
                break;
            }

            switch (c) {
                case '{' :
                case '[' :
                case '(' :
                    index.push(c);
                    break;
                case ')' :
                case ']' :
                case '}' :
                    Character pop = index.pop();
                    if (!isPaired(pop, c)) {
                        isBalanced = false;
                    }
                    break;
                default:
                    break;
            }
        }


        if (!index.empty()) {
            isBalanced = false;
        } else {
            isBalanced = true;
        }

        return isBalanced;
    }


    public static boolean checkBalanceByDeque(String expression) {

        Deque<Character> deque = new LinkedList<>();

        char[] chars = expression.toCharArray();

        boolean isBalanced = true;

        for (char c: chars) {

            switch (c) {
                case '{' :
                case '[' :
                case '(' :
                case ')' :
                case ']' :
                case '}' :
                    deque.push(c);
                    break;
                default:
                    break;
            }
        }

        if (deque.size() % 2 == 0) {
            int size = deque.size() / 2;
            char left;
            char right;
            for (int i = 0; i < size; i++) {
                right = deque.pollFirst();
                left = deque.pollLast();
                isBalanced = isPaired(left, right);
                if (!isBalanced) {
                    break;
                }
            }
        }

        System.err.println("hello");


        return isBalanced;
    }


    public static void main(String[] args) {

        String expression = "{[(a)]}";

        boolean isBalanced = checkBalanceByStack(expression);

        isBalanced = checkBalanceByDeque(expression);

        if (isBalanced) {
            System.out.println(expression + " is Balanced!");
        } else {
            System.out.println(expression + " is not Balanced!");
        }


    }
}
