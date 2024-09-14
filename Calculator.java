import java.util.Scanner;

public class Calculator {
    private double result;

    public void doCalculation() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter first number: ");
        double num1 = scanner.nextDouble();
        System.out.println("Enter an operator (+, -, *, /): ");
        char operator = scanner.next().charAt(0);
        System.out.println("Enter second number: ");
        double num2 = scanner.nextDouble();

        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    System.out.println("Error: Division by zero.");
                    result = Double.NaN;
                }
                break;
            default:
                System.out.println("Error: Invalid operator.");
                result = Double.NaN;
                break;
        }
        scanner.close(); // Close the scanner to avoid resource leaks
    }

    public double getResult() {
        return result;
    }

    public static void main(String[] args) {
        Main calculator = new Main();
        calculator.doCalculation();
        System.out.println("Result: " + calculator.getResult());
    }
}
