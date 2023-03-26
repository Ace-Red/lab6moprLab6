import java.sql.Array;

public class Main {
    private static float matrix [][] = { {5, 5,  1,  10,  6,  4,  10,  5,  9},
            {9,  7,  1,  2,  1,  7,  10,  7,  6},
            {1,  4,  5,  1,  9,  5,  6,  1,  3},
            {2,  10,  4,  3,  10,  4,  5,  4,  4},
            {5,  3,  6,  6,  1,  8,  5,  10,  10},
            {4,  1,  5,  2,  1,  7,  2,  7,  4},
            {2,  10,  2,  4,  2,  4,  4,  3,  1},
            {10,  9,  3,  9,  9,  7,  5,  1,  10},
            {9,  4,  10,  2,  7,  4,  7,  6,  2},
            {3,  6,  4,  7,  10,  7,  7,  4,  9},
            {1, 10,  2,  10,  10,  9,  9,  7,  3},
            {10,  10,  10,  6,  4,  9,  8,  4,  9},
            {6,  3,  10,  4,  10,  5,  6,  2,  5},
            {10,  4,  10,  6,  5,  10,  2,  5,  8},
            {3,  4,  5,  9,  8,  6,  6,  5,  3}};
    /*private static final float[][] matrix = {
            {5, 8, 4},
            {7, 6, 8},
            {8, 8, 6},
            {7, 4, 6}
    };*/
    //private static final float[] weight = {0.3f, 0.4f, 0.3f};
    private static final float[] weight = {0.05f, 0.18f, 0.02f, 0.14f, 0.21f, 0.15f, 0.06f, 0.07f, 0.12f};

    public static void main(String[] args) {
        System.out.println("Початкова матриця:");
        showMatrix(matrix);
        double[][] matrixWithWeights = multiplyColumnToElementWeight(method1());

        findDistanceToPIS(matrixWithWeights, findPositiveIdealPoint(matrixWithWeights));
        findDistanceToNIS(matrixWithWeights, findNegativeIdealPoint(matrixWithWeights));
        resultLab(findSimilarityPIS(findDistanceToPIS(matrixWithWeights, findPositiveIdealPoint(matrixWithWeights)), findDistanceToNIS(matrixWithWeights, findNegativeIdealPoint(matrixWithWeights))));
    }

    public static double[][] method1() {
        System.out.println("Обчислення нормалізованих оцінок альтернатив:");
        double[][] newMatrix = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                newMatrix[i][j] = (matrix[i][j]) / (Math.sqrt(addToMethod1(j)));
            }
        }
        showMatrix(newMatrix);
        return newMatrix;
    }

    //sum all elements in row(method1)
    public static float addToMethod1(int n) {
        float result = 0;
        for (float[] floats : matrix) {
            result += floats[n] * floats[n];
        }
        return result;
    }

    public static void showMatrix(float[][] matrix) {
        for (float[] floats : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(floats[j] + " ");
            }
            System.out.println();
        }
    }

    public static void showMatrix(double[][] matrix) {
        for (double[] doubles : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(String.format("%.4f", doubles[j]) + " ");
            }
            System.out.println();
        }
    }

    public static void showArray(double[] array) {
        for (double valuation : array) {
            System.out.print(String.format("%.4f", valuation) + " ");
        }
        System.out.println();
    }

    public static double[][] multiplyColumnToElementWeight(double[][] matrix) {
        System.out.println("Обчислення зважених нормалізованих оцінок");
        double[][] newMatrix = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                newMatrix[j][i] = matrix[j][i] * weight[i];
            }
        }
        showMatrix(newMatrix);
        return newMatrix;
    }

    public static double[] findPositiveIdealPoint(double[][] matrixWithWeights) {
        System.out.println("Обчислення позитивної ідеальної точки");
        double max = 0;
        double[] positiveIdealPoint = new double[matrixWithWeights[0].length];
        for (int i = 0; i < matrixWithWeights[0].length; i++) {
            max = matrixWithWeights[0][i];
            for (double[] matrixWithWeight : matrixWithWeights) {
                if (matrixWithWeight[i] > max) {
                    max = matrixWithWeight[i];
                }
            }
            positiveIdealPoint[i] = max;
        }
        showArray(positiveIdealPoint);
        return positiveIdealPoint;
    }

    public static double[] findNegativeIdealPoint(double[][] matrixWithWeights) {
        System.out.println("Обчислення негативної ідеальної точки");
        double min = 0;
        double[] negativeIdealPoint = new double[matrixWithWeights[0].length];
        for (int i = 0; i < matrixWithWeights[0].length; i++) {
            min = matrixWithWeights[0][i];
            for (double[] matrixWithWeight : matrixWithWeights) {
                if (matrixWithWeight[i] < min) {
                    min = matrixWithWeight[i];
                }
            }
            negativeIdealPoint[i] = min;
        }
        showArray(negativeIdealPoint);
        return negativeIdealPoint;
    }

    public static double[] findDistanceToPIS(double[][] matrix, double[] array) {
        System.out.println("Обчислення відстаней кожної альтернативи до позитивної ідеальної точки PIS");
        double[] distance = new double[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            double sum = 0;
            for (int j = 0; j < matrix[0].length; j++) {

                sum += Math.pow((matrix[i][j] - array[j]), 2);
            }

            distance[i] = Math.sqrt(sum);
        }
        showArray(distance);
        return distance;
    }

    public static double[] findDistanceToNIS(double[][] matrix, double[] array) {
        System.out.println("Обчислення відстаней кожної альтернативи до негативної ідеальної точки PIS");
        double[] distance = new double[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            double sum = 0;
            for (int j = 0; j < matrix[0].length; j++) {

                sum += Math.pow((matrix[i][j] - array[j]), 2);
            }

            distance[i] = Math.sqrt(sum);
        }
        showArray(distance);
        return distance;
    }

    public static double[] findSimilarityPIS(double[] PIS, double[] NIS) {
        System.out.println("Встановлення наближеності кожної альтернативи до позитивної ідеальної точки PIS");
        double[] arrayC = new double[PIS.length];
        for (int i = 0; i < arrayC.length; i++) {
            arrayC[i] = NIS[i] / (PIS[i] + NIS[i]);
        }
        showArray(arrayC);
        return arrayC;
    }

    public static void resultLab(double[] array) {
        double[] newArray = new double[array.length];
        System.arraycopy(array, 0, newArray, 0, array.length);
        double max = -1;
        int maxIndex = -1;
        System.out.println("Впорядкування: ");
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (max < array[j]) {
                    max = array[j];
                    maxIndex = j;
                }
            }
            System.out.printf("%.4f", max);
            System.out.print(" A" + (maxIndex+1));
            System.out.println();
            array[maxIndex] = -1;
            max = -1;
            maxIndex = -1;

        }

        for (int i = 0; i < newArray.length; i++) {
            if (max < newArray[i]) {
                max = newArray[i];
                maxIndex = i;
            }
        }

        System.out.print("Вибір найкращої альтернативи – за максимальним значенням подібності PIS. Альтернатива A" + (maxIndex+1) + " – найкращий вибір" );
        System.out.println();

    }

}
