import java.util.Scanner;
import java.util.InputMismatchException;
import java.lang.Math;

public class ScientificCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continueCalculating = true;

        while (continueCalculating) {
            System.out.println("Scientific Calculator");
            System.out.println("Select operation:");
            System.out.println("1. Addition");
            System.out.println("2. Subtraction");
            System.out.println("3. Multiplication");
            System.out.println("4. Division");
            System.out.println("5. Exponentiation");
            System.out.println("6. Square Root");
            System.out.println("7. Sine");
            System.out.println("8. Cosine");
            System.out.println("9. Tangent");
            System.out.println("10. Exit");

            int choice = getValidChoice(scanner);

            if (choice == 10) {
                continueCalculating = false;
                System.out.println("Exiting...");
                continue;
            }

            double result = 0;

            switch (choice) {
                case 1:
                    result = performOperation(scanner, "addition", (a, b) -> a + b);
                    break;
                case 2:
                    result = performOperation(scanner, "subtraction", (a, b) -> a - b);
                    break;
                case 3:
                    result = performOperation(scanner, "multiplication", (a, b) -> a * b);
                    break;
                case 4:
                    result = performOperation(scanner, "division", (a, b) -> {
                        if (b == 0) {
                            System.out.println("Error: Division by zero");
                            return Double.NaN;
                        }
                        return a / b;
                    });
                    break;
                case 5:
                    result = performExponentiation(scanner);
                    break;
                case 6:
                    result = performUnaryOperation(scanner, "square root", Math::sqrt);
                    break;
                case 7:
                    result = performUnaryOperation(scanner, "sine", Math::sin);
                    break;
                case 8:
                    result = performUnaryOperation(scanner, "cosine", Math::cos);
                    break;
                case 9:
                    result = performUnaryOperation(scanner, "tangent", Math::tan);
                    break;
                default:
                    System.out.println("Invalid choice");
                    continue;
            }

            System.out.println("Result: " + result);
        }

        scanner.close();
    }

    private static int getValidChoice(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Enter choice (1-10): ");
                int choice = scanner.nextInt();
                if (choice >= 1 && choice <= 10) {
                    return choice;
                } else {
                    System.out.println("Invalid choice, please enter a number between 1 and 10.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please enter a number.");
                scanner.next(); // clear the invalid input
            }
        }
    }

    private static double performOperation(Scanner scanner, String operation, DoubleBinaryOperator op) {
        double a = getNumber(scanner, "Enter first number for " + operation + ": ");
        double b = getNumber(scanner, "Enter second number for " + operation + ": ");
        return op.applyAsDouble(a, b);
    }

    private static double performExponentiation(Scanner scanner) {
        double base = getNumber(scanner, "Enter base for exponentiation: ");
        double exponent = getNumber(scanner, "Enter exponent: ");
        return Math.pow(base, exponent);
    }

    private static double performUnaryOperation(Scanner scanner, String operation, DoubleUnaryOperator op) {
        double number = getNumber(scanner, "Enter number for " + operation + ": ");
        return op.applyAsDouble(number);
    }

    private static double getNumber(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please enter a valid number.");
                scanner.next(); // clear the invalid input
            }
        }
    }

    @FunctionalInterface
    interface DoubleBinaryOperator {
        double applyAsDouble(double left, double right);
    }

    @FunctionalInterface
    interface DoubleUnaryOperator {
        double applyAsDouble(double operand);
    }
}
