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

        private final double[] row;
        private double res;

        Worker(final double[] row) {
            this.row = row;
        }

        @Override
        public void run() {
            double sum = 0;
            for (int j = 0; j < this.row.length; j++) {
                sum += this.row[j];
            }
            this.res = sum;
        }

        public double getResult() {
            return this.res;
        }
    }
}
