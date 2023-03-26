public class Main {
    /*private static float matrix [][] = { {5, 5,  1,  10,  6,  4,  10,  5,  9},
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
            {3,  4,  5,  9,  8,  6,  6,  5,  3}};*/
    private static final float[][] matrix = {
            {5,8,4},
            {7,6,8},
            {8,8,6},
            {7,4,6}
    };
    private static final float[] weight = {0.3f,0.4f,0.3f};


    public static void main(String[] args) {
        System.out.println("Початкова матриця:");
        showMatrix(matrix);
        multiplyColumnToElementWeight(method1());
    }
    public static double[][] method1(){
        System.out.println("Обчислення нормалізованих оцінок альтернатив:");
        double[][] newMatrix = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                    newMatrix[i][j] = (matrix[i][j])/(Math.sqrt(addToMethod1(j)));
            }
        }
        showMatrix(newMatrix);
        return newMatrix;
    }
    //sum all elements in row(method1)
    public static float addToMethod1(int n){
        float result = 0;
        for (float[] floats : matrix) {
            result += floats[n] * floats[n];
        }
        return result;
    }
    public static void showMatrix(float[][] matrix){
        for (float[] floats : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(floats[j] + " ");
            }
            System.out.println();
        }
    }
    public static void showMatrix(double[][] matrix){
        for (double[] floats : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(String.format("%.2f",floats[j]) + " ");
            }
            System.out.println();
        }
    }
    public static double[][] multiplyColumnToElementWeight(double[][] matrix){
        System.out.println("Обчислення зважених нормалізованих оцінок");
        double[][] newMatrix = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                newMatrix[j][i] = matrix[j][i]*weight[i];
            }
        }
        showMatrix(newMatrix);
        return newMatrix;
    }
}
