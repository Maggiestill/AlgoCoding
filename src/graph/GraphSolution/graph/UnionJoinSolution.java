package graph.GraphSolution.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UnionJoinSolution {
    static class UnionJoin{

        int[] roots;
        int[] sizes;
        int unionCount;

        UnionJoin(int size) {
            roots = new int[size];
            sizes = new int[size];
            Arrays.fill(roots, -1);
            unionCount = 0;
        }


        void unionBySize(int idx1, int idx2) {
            int root1 = find(idx1);
            int root2 = find(idx2);
            if (root1 == root2) {
                return;
            }
            if (sizes[idx1] >= sizes[idx2]) {
                sizes[roots[idx1]] += sizes[roots[idx2]];
                roots[root2] = root1; // idx2 join idx1
            } else {
                sizes[roots[idx2]] += sizes[roots[idx1]];
                roots[root1] = root2;
            }
            unionCount--;
        }


        int find(int idx) {
            // base case
            if (roots[idx] == idx) {
                return idx;
            }
            roots[idx] = find(roots[idx]);
            sizes[idx] = sizes[roots[idx]];
            return roots[idx];
        }

        void setParent(int idx) { // only setparent for a new island
            if (isValid(idx)) {
                return;
            }
            roots[idx] = idx;
            sizes[idx] = 1;
            unionCount++;
        }

        boolean isValid(int idx) { // whether this idx is an island
            if (roots[idx] != -1) {
                return true;
            }
            return false;
        }
    }

    public List<Integer> numIslands2(int m, int n, int[][] positions) {

        int[][] SHIFT = {{-1, 0},{1, 0},{0, -1},{0, 1}};
        UnionJoin solution = new UnionJoin(m * n);
        List<Integer> result = new ArrayList<>();

        for (int[] position : positions) {
            int idx1 = position[0] * n + position[1];
            solution.setParent(idx1);
            for (int[] dir : SHIFT) {
                int neiI = position[0] + dir[0];
                int neiJ = position[1] + dir[1];
                if (isInBound(neiI, neiJ, m, n) && solution.isValid(neiI * n + neiJ))  {
                    solution.unionBySize(idx1, neiI * n + neiJ);
                    System.out.println("position: i = " + position[0] + " j = " + position[1] + " || nei: neiI = " + neiI + " neiJ = " + neiJ +
                            " || Count = " + solution.unionCount + " || Roots Array = " + Arrays.toString(solution.roots) +
                            " || Sizes Array = " + Arrays.toString(solution.sizes));
                }
            }
            result.add(solution.unionCount);
        }
        return result;
    }

    private boolean isInBound(int i, int j, int m, int n) {
        return i >= 0 && j >= 0 && i < m && j < n;
    }

}
