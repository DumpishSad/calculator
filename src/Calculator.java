import java.util.Scanner;

public class Calculator {

    public static void calculate(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение (например, 2 + 3):");
        String input = scanner.nextLine();

        try {
            String[] parts = input.split(" ");
            if(parts.length != 3){
                throw new IllegalAccessException("Неверный ввод!");
            }

            String firstOperandStr = parts[0];
            String operator = parts[1];
            String secondOperandStr = parts[2];

            boolean isRoman = isRomanNumber(firstOperandStr) && isRomanNumber(secondOperandStr);
            boolean isArabic = isArabicNumber(firstOperandStr) && isArabicNumber(secondOperandStr);

            if (!isRoman && !isArabic) {
                throw new IllegalArgumentException("Числа должны быть либо арабскими <= 10, либо римскими <= 10");
            }

            int firstOperand = isArabic ? Integer.parseInt(firstOperandStr) : RomanToDecimal(firstOperandStr);
            int secondOperand = isArabic ? Integer.parseInt(secondOperandStr) : RomanToDecimal(secondOperandStr);

            if (firstOperand < 1 || firstOperand > 10 || secondOperand < 1 || secondOperand > 10) {
                throw new IllegalArgumentException("Числа должны быть от 1 до 10 включительно");
            }

            int result;
            switch (operator) {
                case "+":
                    result = firstOperand + secondOperand;
                    break;
                case "-":
                    result = firstOperand - secondOperand;
                    break;
                case "*":
                    result = firstOperand * secondOperand;
                    break;
                case "/":
                    if (secondOperand == 0) {
                        throw new ArithmeticException("Деление на ноль");
                    }
                    result = firstOperand / secondOperand;
                    break;
                default:
                    throw new IllegalArgumentException("Неверная операция");
            }
            if (isRoman) {
                if (result <= 0) {
                    throw new IllegalArgumentException("Результат не может быть меньше 1 для римских чисел");
                }
                System.out.println(toRoman(result));
            } else {
                System.out.println(result);
            }
        }

        catch (Exception e){
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static boolean isArabicNumber(String str) {
        try {
            int num = Integer.parseInt(str);
            return num >= 1 && num <= 10;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isRomanNumber(String str) {
        return str.matches("^(I|II|III|IV|V|VI|VII|VIII|IX|X)$");
    }

    private static int RomanToDecimal(String romanNumber) {
        int decimal = 0;
        String romanNumeral = romanNumber.toUpperCase();

        switch (romanNumeral) {
            case "I":
                decimal = 1;
                break;
            case "II":
                decimal = 2;
                break;
            case "III":
                decimal = 3;
                break;
            case "IV":
                decimal = 4;
                break;
            case "V":
                decimal = 5;
                break;
            case "VI":
                decimal = 6;
                break;
            case "VII":
                decimal = 7;
                break;
            case "VIII":
                decimal = 8;
                break;
            case "IX":
                decimal = 9;
                break;
            case "X":
                decimal = 10;
                break;
            default:
                throw new IllegalArgumentException("Неверный символ в римском числе");
        }
        return decimal;
    }
    private static String toRoman(int number) {
        if (number < 1 || number > 100) {
            throw new IllegalArgumentException("Число должно быть от 1 до 100");
        }
        String[] ROMAN_SYMBOLS = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
                "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX",
                "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L",
                "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};
        return ROMAN_SYMBOLS[number];
    }
}
