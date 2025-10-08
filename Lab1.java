import java.util.Random;

// Варіант 7 
// С = А + В
// double
// Обчислити суму найбільших елементів в стовпцях матриці з непарними номерами та найменших елементів в стовпцях матриці з парними номерами

public class Lab1 {

    public static void main(String[] args) {
        try {
            int rows = 4;
            int cols = 5;

            double[][] matrixA = generateRandomMatrix(rows, cols);
            double[][] matrixB = generateRandomMatrix(rows, cols);

            double[][] matrixC = addMatrices(matrixA, matrixB);

            System.out.println("Matrix A:");
            printMatrix(matrixA);

            System.out.println("\nMatrix B:");
            printMatrix(matrixB);

            System.out.println("\nMatrix C = A + B:");
            printMatrix(matrixC);

            double result = processMatrixC(matrixC);
            System.out.printf("\nResult (max of odd columns + min of even columns): %.4f\n", result);

        } catch (IllegalArgumentException e) {
            System.err.println("Input error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An error occurred while executing the program: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // генерація рандомних матриць
    public static double[][] generateRandomMatrix(int rows, int cols) {
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("Matrix dimensions must be greater than 0.");
        }

        double[][] matrix = new double[rows][cols];
        Random rand = new Random();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = rand.nextDouble() * 10;
            }
        }
        return matrix;
    }

    // С = А + В
    public static double[][] addMatrices(double[][] a, double[][] b) {
        if (a.length != b.length || a[0].length != b[0].length) {
            throw new IllegalArgumentException("Matrices must have the same size to be added.");
        }

        int rows = a.length;
        int cols = a[0].length;
        double[][] result = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = a[i][j] + b[i][j];
            }
        }
        return result;
    }

    // вивід матриць
    public static void printMatrix(double[][] matrix) {
        for (double[] row : matrix) {
            for (double val : row) {
                System.out.printf("%10.4f", val);
            }
            System.out.println();
        }
    }

    // останнє завдання
    public static double processMatrixC(double[][] c) {
        int rows = c.length;
        int cols = c[0].length;
        double sum = 0;

        for (int j = 0; j < cols; j++) {
            if ((j + 1) % 2 == 0) { // мінімум по парних стовпцях
                double min = c[0][j];
                for (int i = 1; i < rows; i++) {
                    if (c[i][j] < min) {
                        min = c[i][j];
                    }
                }
                sum += min;
            } else { // максимум по непарних стовпцях
                double max = c[0][j];
                for (int i = 1; i < rows; i++) {
                    if (c[i][j] > max) {
                        max = c[i][j];
                    }
                }
                sum += max;
            }
        }
        return sum;
    }
}
