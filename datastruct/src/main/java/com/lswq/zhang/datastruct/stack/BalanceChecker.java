package com.lswq.zhang.datastruct.stack;

/**
 * Created by zhangsw on 2016/12/9.
 */
public class BalanceChecker {
    /*
     * @Task:使用栈判断表达式的括号匹配
     * @param: expression 待检查的字符串
     * @return 若括号匹配则返回true
     */
    public static boolean checkBalance(String expression){
        StackI<Character> openDelimiterStack = new SequenceStack<Character>();
        int characterCount = expression.length();
        boolean isBalanced = true;
        int index = 0;//标记是否已经到达表达式末尾
        char nextCharacter = ' ';//标记表达式中下一个待判断的字符
        for(; isBalanced && (index < characterCount); index++){
            nextCharacter = expression.charAt(index);
            switch(nextCharacter){//只对表达式中的括号进行处理,其他字符都不满足switch中的case
                case '(': case '[': case '{':
                    openDelimiterStack.push(nextCharacter);//只有表达式中的左括号才能入栈
                    break;
                case ')': case ']': case '}':
                    if(openDelimiterStack.empty())
                        isBalanced = false;//当碰到右括号时,此时表达式还未结束,但栈中已经没有左括号了,说明右括号多于左括号
                    else{//栈中还有左括号
                        char openDelimiter = openDelimiterStack.pop();
                        isBalanced = isPaired(openDelimiter, nextCharacter);//判断此时栈中左括号与表达式字符右括号是否匹配
                    }
                    break;
                default: break;//对于 非括号字符直接执行  break;
            }//end switch
        }//end for

        //表达式中的右括号已经判断完了,但还有左括号(栈未空),说明左括号个数多于右括号
        if(!openDelimiterStack.empty())
            isBalanced = false;
        return isBalanced;
    }
    //判断左右括号是否匹配
    private static boolean isPaired(char left, char right){
        return (left == '(' && right == ')') ||
                (left == '[' && right == ']') ||
                (left == '{' && right == '}');
    }

    //for test purpose
    public static void main(String[] args) {
        String expression = "a {b [c (d + e)/2 - f] + 1}";
        boolean isBalanced = checkBalance(expression);
        if(isBalanced)
            System.out.println(expression + " is Balanced!");
        else
            System.out.println(expression + " is not Balanced!");
    }
}
