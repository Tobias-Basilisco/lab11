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



}
