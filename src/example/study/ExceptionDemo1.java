package example.study;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by wuruixuan on 2017/12/26.
 */

public class ExceptionDemo1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        try {
            int num3 = input.nextInt();

            int num1 = 10;
            int num2 = 0;
            System.out.println(num1 / num2);
        }
        catch (ArithmeticException e) {
            System.out.println("除数不能为0");
        }
        catch (InputMismatchException e) {
            System.out.println("输入类型不匹配");
        }

        finally {// 不管是否出现异常都会执行，可以做一些回收清理的工作
            System.out.println("最终");
        }

        System.out.println("程序结束");
    }
}


