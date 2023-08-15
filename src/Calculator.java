import java.util.Scanner;

public class Calculator {
    private String[] expression;

    public Calculator(String[] expression) {
        setExpression(expression);
    }

    public void setExpression(String[] expression) {
        if (expression.length == 3)
            getNumbers(expression);
        else
            throw new IllegalArgumentException("Input should be: 1 + 6 or I + VI");
    }


    private void getNumbers(String[] expression) {
        if (romanOrArabic(expression[0]) == romanOrArabic(expression[2])) {
            int a = romanOrArabic(expression[0]) ? fromRomanToArabic(expression[0]) : toInt(expression[0]);
            String sign = expression[1];
            int b = romanOrArabic(expression[2]) ? fromRomanToArabic(expression[2]) : toInt(expression[2]);

            if (romanOrArabic(expression[0]))
                getRomanResult(a,sign, b);
            else
                getResult(a, sign, b);
        }
        else
            throw new IllegalArgumentException("You should use only one scale of notation");

    }

    private boolean romanOrArabic(String num) {
        return switch (num.toUpperCase()) {
            case "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X" -> true;
            default -> {
                fromRomanToArabic(num.toUpperCase());
                yield false;
            }
        };
    }

    private int fromRomanToArabic(String num) {
        return switch (num.toUpperCase()) {
            case "I" -> 1;
            case "II" -> 2;
            case "III" -> 3;
            case "IV" -> 4;
            case "V" -> 5;
            case "VI" -> 6;
            case "VII" -> 7;
            case "VIII", "IIX" -> 8;
            case "IX" -> 9;
            case "X" -> 10;
            default -> throw new IllegalArgumentException("Did not found a number from 1 to 10");
        };
    }

    private int toInt(String num) {
        int x = 0;
        try {
            x = Integer.parseInt(num);
        } catch (IllegalArgumentException e) {
            e.getStackTrace();
        }

        if (x > 0 && x < 11)
            return x;
        else throw new IllegalArgumentException("First and Second numbers should be between [1,10] and Integer");
    }

    private void getRomanResult(int a, String sign, int b) {
        int result = getArabic(a, sign, b);

        if (result < 0) throw new IllegalArgumentException("Roman numbers cannot be negative");
        else if (result == 0) {
            System.out.println(0);
            return;
        }

        int[] arr = new int[] {100, 50, 10, 5, 1};
        for (int i: arr) {
            if (result / i > 0) {


                switch (i) {
                    case 100 -> System.out.print("C".repeat(result/i));
                    case 50 -> System.out.print("L".repeat(result/i));
                    case 10 -> System.out.print("X".repeat(result/i));
                    case 5 -> System.out.print("V".repeat(result/i));
                    case 1 -> System.out.print("I".repeat(result/i));
                }

                result %= i;
            }
        }
    }

    private int getArabic(int a, String sign, int b) {
        switch (sign) {
            case "+" -> {
                return a + b;
            }
            case "-" -> {
                return a - b;
            }
            case "*" -> {
                return a * b;
            }
            case "/" -> {
                return a / b;
            }
            default -> {
                return -1;
            }
        }
    }

    private void getResult(int a, String sign, int b) {
        System.out.println(getArabic(a, sign, b));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] expression = sc.nextLine().split(" ");

        new Calculator(expression);
    }
}