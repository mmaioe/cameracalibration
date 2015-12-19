package mmaioe.com.linearsystem;

import org.netlib.lapack.Dgesv;
import org.netlib.util.intW;

/**
 * Created by maoito on 12/19/15.
 */
public class LinearSolver {
    /**
     *
     * @param _A
     * @param _B
     * @return
     */
    public static double[] solveWithSquareMatrix(double[][] _A, double[] _B) {
        int N = _A.length;
        int NRHS = 1;
        int LDA = _A.length;
        int LDB = _B.length;

        double[] A = new double[_A.length*_A.length];
        double[] B = _B;

        intW info = new intW(0);

        int[] IPIV = new int[_A.length];
        for(int i=0;i<IPIV.length;i++) IPIV[i] = 0;

        for(int i=0;i<_A.length;i++){
            for(int j=0;j<_A[i].length;j++){
                A[i+j*_A.length] = _A[i][j];
            }
        }

        //Solve Ah = B
        Dgesv.dgesv(N, NRHS, A, 0, LDA, IPIV, 0, B, 0, LDB, info);

        return B;
    }

}