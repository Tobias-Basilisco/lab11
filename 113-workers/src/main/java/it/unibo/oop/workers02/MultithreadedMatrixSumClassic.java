package it.unibo.oop.workers02;

public class MultithreadedMatrixSumClassic implements SumMatrix{
    
    private final int nthread;

    /**
     * Builds a multithreaded matrix sum.
     *
     * @param nthread
     *            no. of thread performing the sum.
     */
    public MultithreadedMatrixSumClassic(final int nthread) {
        this.nthread = nthread;
    }

    @Override
    public double sum(double[][] matrix){

        

        return 0;
    }

    private static final class Worker extends Thread {

        private final double[][] matrix;
        private final int rowStart;
        private final int colStart;
        private final int rowStep;
        private final int colStep;
        private double res;

        Worker(final double[][] matrix, final int rowStart, final int colStart, final int rowStep, final int colStep) {
            this.matrix = matrix;
            this.rowStart = rowStart;
            this.colStart = colStart;
            this.rowStep = rowStep;
            this.colStep = colStep;
        }

        @Override
        public synchronized void run() {
            double sum = 0;
            for (int i = rowStart; i < rowStart + rowStep && i < matrix.length; i++){
                for (int j = colStart; j < colStart + colStep && j < matrix[i].length; j++) {
                    sum += matrix[i][j];
                }
            }
            this.res = sum;
        }

        public double getResult() {
            return this.res;
        }
    }
}
