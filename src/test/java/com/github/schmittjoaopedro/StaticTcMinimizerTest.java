package com.github.schmittjoaopedro;

import com.github.schmittjoaopedro.problem.Instance;
import com.github.schmittjoaopedro.problem.Reader;
import com.github.schmittjoaopedro.problem.Solution;
import org.junit.Test;

import java.nio.file.Paths;
import java.util.Random;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class StaticTcMinimizerTest {

    private static int maxIterations = 25000;

    private static final String pdptw200Directory;

    static {
        pdptw200Directory = Paths.get(StaticNvMinimizerTest.class.getClassLoader().getResource("pdptw/200-tasks").getFile().substring(1)).toString();
    }

    @Test
    public void minimizeCost_initial_solution() throws Exception {
        Instance instance = Reader.getInstance(Paths.get(pdptw200Directory, "lr1_2_10.txt").toFile());
        Solver solver = new Solver(instance, new Random(1), maxIterations, true, true);
        solver.init();
        Solution solutionBest = solver.getSolutionBest();
        assertThat(solutionBest.feasible).isTrue();
        assertThat(solutionBest.totalCost).isEqualTo(5070.736543598931);
        assertThat(solutionBest.tours.size()).isEqualTo(16);
        assertThat(solutionBest.requestIds.size()).isEqualTo(16);
    }

    @Test
    public void minimizeCost_lc1_2_3_Test() throws Exception {
        Instance instance = Reader.getInstance(Paths.get(pdptw200Directory, "lc1_2_3.txt").toFile());
        Solver solver = new Solver(instance, new Random(1), maxIterations, false, true);
        solver.init();
        solver.run();
        Solution solutionBest = solver.getSolutionBest();
        assertThat(solutionBest.feasible).isTrue();
        assertThat(solutionBest.totalCost).isEqualTo(2772.182174325899);
        assertThat(solutionBest.tours.size()).isEqualTo(18);
        assertThat(solutionBest.requestIds.size()).isEqualTo(18);
        int counter = 0;
        assertThat(solver.getLogs().get(counter++)).isEqualTo("Insertion heuristic = [F = true, NV =  20, TC =   4385.17]");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  19, TC =   4043.29] at iteration 1");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  19, TC =   4030.96] at iteration 3");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  19, TC =   3878.39] at iteration 4");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  19, TC =   3614.39] at iteration 6");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  19, TC =   3562.18] at iteration 8");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  19, TC =   3501.16] at iteration 9");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  19, TC =   3436.15] at iteration 10");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  19, TC =   3359.63] at iteration 13");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  19, TC =   3303.36] at iteration 14");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  19, TC =   3247.61] at iteration 18");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  19, TC =   3242.41] at iteration 20");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  18, TC =   3200.83] at iteration 25");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  18, TC =   3158.51] at iteration 27");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  18, TC =   3154.65] at iteration 28");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  18, TC =   3154.60] at iteration 29");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  18, TC =   3072.81] at iteration 52");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  18, TC =   3017.37] at iteration 69");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  18, TC =   3009.46] at iteration 76");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  18, TC =   2998.17] at iteration 77");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  18, TC =   2835.40] at iteration 79");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  18, TC =   2789.69] at iteration 138");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  18, TC =   2787.45] at iteration 152");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  18, TC =   2786.63] at iteration 201");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  18, TC =   2779.72] at iteration 202");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  18, TC =   2776.65] at iteration 210");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  18, TC =   2774.91] at iteration 425");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  18, TC =   2772.18] at iteration 584");
    }

    @Test
    public void minimizeCost_lr1_2_10_Test() throws Exception {
        Instance instance = Reader.getInstance(Paths.get(pdptw200Directory, "lr1_2_10.txt").toFile());
        Solver solver = new Solver(instance, new Random(1), maxIterations, false, true);
        solver.init();
        solver.run();
        Solution solutionBest = solver.getSolutionBest();
        assertThat(solutionBest.feasible).isTrue();
        assertThat(solutionBest.totalCost).isEqualTo(3435.143305937722);
        assertThat(solutionBest.tours.size()).isEqualTo(13);
        assertThat(solutionBest.requestIds.size()).isEqualTo(13);
        int counter = 0;
        assertThat(solver.getLogs().get(counter++)).isEqualTo("Insertion heuristic = [F = true, NV =  16, TC =   5070.74]");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  16, TC =   4643.14] at iteration 1");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  15, TC =   4213.96] at iteration 2");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  15, TC =   4135.03] at iteration 3");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  14, TC =   4097.84] at iteration 4");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  14, TC =   4091.94] at iteration 6");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  14, TC =   3884.05] at iteration 11");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  14, TC =   3866.80] at iteration 14");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  14, TC =   3840.43] at iteration 15");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  14, TC =   3826.02] at iteration 18");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  13, TC =   3750.66] at iteration 21");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  13, TC =   3723.21] at iteration 27");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  13, TC =   3719.73] at iteration 30");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  13, TC =   3699.19] at iteration 31");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  13, TC =   3688.72] at iteration 34");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  13, TC =   3651.08] at iteration 38");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  13, TC =   3633.56] at iteration 173");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  13, TC =   3631.41] at iteration 174");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  13, TC =   3610.16] at iteration 180");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  13, TC =   3609.04] at iteration 182");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  13, TC =   3575.23] at iteration 184");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  13, TC =   3563.68] at iteration 1824");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  13, TC =   3561.86] at iteration 1904");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  13, TC =   3526.15] at iteration 1905");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  13, TC =   3521.81] at iteration 2798");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  13, TC =   3511.31] at iteration 4268");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  13, TC =   3507.04] at iteration 4269");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  13, TC =   3496.68] at iteration 4470");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  13, TC =   3496.29] at iteration 4490");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  13, TC =   3492.90] at iteration 4491");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  13, TC =   3486.93] at iteration 4492");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  13, TC =   3481.72] at iteration 4493");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  13, TC =   3465.86] at iteration 4495");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  13, TC =   3463.40] at iteration 7485");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  13, TC =   3462.76] at iteration 7492");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  13, TC =   3462.30] at iteration 8586");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  13, TC =   3461.48] at iteration 8588");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  13, TC =   3460.06] at iteration 8592");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  13, TC =   3455.94] at iteration 8679");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  13, TC =   3455.37] at iteration 8685");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  13, TC =   3451.90] at iteration 10223");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  13, TC =   3447.67] at iteration 10224");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  13, TC =   3447.17] at iteration 10403");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  13, TC =   3446.76] at iteration 10407");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  13, TC =   3445.85] at iteration 10412");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  13, TC =   3444.26] at iteration 10850");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  13, TC =   3442.63] at iteration 10854");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  13, TC =   3441.78] at iteration 11366");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  13, TC =   3440.40] at iteration 11928");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  13, TC =   3438.40] at iteration 11929");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  13, TC =   3437.88] at iteration 12128");
        assertThat(solver.getLogs().get(counter++)).isEqualTo("New best = [F = true, NV =  13, TC =   3435.14] at iteration 12169");
    }
}
