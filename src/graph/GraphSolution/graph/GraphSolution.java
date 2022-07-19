package graph.GraphSolution.graph;

import java.util.*;

public class GraphSolution {
    public String alienOrder(String[] words) {

        // Step 01: Find all unique characters O(words.length * word.length())
        //          Map<Character, Integer indegree> indegree map, initailly set to 0
        // Step 02: Find all the edges, put it into the adjacent list  O(words.length * word.length())
        //          inorder to deduplicate, we can convert the adjacent list to Map<Character, Set<Character>> to record the graph
        // Step 03: Topological Sort  O(V + E)
        //           use string builder to record the result
        // return String.length() == indegree.size() ? string : "";

        // corner case
        if (words == null || words.length == 0) {
            return "";
        }

        // Step 01: Find all unique chars
        Map<Character, Integer> indegree = new HashMap<>();
        for (String word : words) {
            for (int i = 0 ; i <= word.length() - 1; i ++) {
                indegree.put(word.charAt(i), 0);
            }
        }

        // Step 02: Build Adjacent List
        Map<Character, Set<Character>> graph = new HashMap<>();
        for (Map.Entry<Character, Integer> entry : indegree.entrySet()) {
            graph.put(entry.getKey(), new HashSet<>());
        }
        for (int j = 0; j <= words.length - 2; j ++) {
            String word1 = words[j];
            String word2 = words[j + 1];
            int length =  Math.min(word1.length(), word2.length());
            for (int i = 0; i <= length - 1; i ++) {
                if (word1.charAt(i) != word2.charAt(i)) {
                    char start = word1.charAt(i);
                    char end = word2.charAt(i);
                    if (graph.get(start).add(end)) {
                        indegree.put(end, indegree.get(end) + 1);
                    }
                    break;
                } else if (i == length - 1) {
                    return "";
                }
            }
        }

        for (Map.Entry<Character, Set<Character>> entry : graph.entrySet()) {
            System.out.println(entry);
        }

        for (Map.Entry<Character, Integer> entry : indegree.entrySet()) {
            System.out.println(entry);
        }

        // Step 03: Topological Sort
        return topologicalSort(indegree, graph);
    }

    private String topologicalSort(Map<Character, Integer> indegree, Map<Character, Set<Character>> graph) {
        StringBuilder sb = new StringBuilder();
        // Initialization
        Queue<Character> workQueue = new ArrayDeque<>();
        for (Map.Entry<Character, Integer> entry : indegree.entrySet()) {
            if (entry.getValue() == 0) {
                workQueue.offer(entry.getKey());
            }
        }
        // Start sort
        while (!workQueue.isEmpty()) {
            char cur = workQueue.poll();
            sb.append(cur);
            for (char nei : graph.get(cur)) {
                int neiIndegree = indegree.get(nei);
                indegree.put(nei, neiIndegree - 1);
                if (neiIndegree - 1 == 0) {
                    workQueue.offer(nei);
                }
            }
        }
        return sb.length() == indegree.size() ? new String(sb) : "";
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////LC 490 The Maze/////////////////////////////////////////////////////////
    public boolean hasPath(int[][] matrix, int[] start, int[] dest) {
        // step 01: count
        int[][] SHIFT = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        return dfs(matrix, start, dest, SHIFT);
    }

    private boolean dfs(int[][] matrix, int[] start, int[] dest, int[][] SHIFT) {
        // collect answer
        System.out.println("Start Points: i = " + start[0] + " j = " + start[1]);

        if (start[0] == dest[0] && start[1] == dest[1]) {
            return true;
        }
        // base case
        if (matrix[start[0]][start[1]] == -1) {
            return false;
        }


        // marked visited
        matrix[start[0]][start[1]] = -1;

        for (int[] dir : SHIFT) {
            // dir[0] dir[1]
            int neiI = start[0];
            int neiJ = start[1];
            System.out.println("Start Points: i = " + start[0] + " j = " + start[1] + " dir[0] = " + dir[0] + " dir[1] = " + dir[1]);
            while(neiI + dir[0] >= 0  && neiJ + dir[1] >= 0 && neiI + dir[0] < matrix.length  && neiJ + dir[1] < matrix[0].length
                    && matrix[neiI+ dir[0]][neiJ + dir[1]] != 1) {
                neiI += dir[0];
                neiJ += dir[1];
                System.out.println("neiI = " + neiI + " neiJ = " + neiJ);
            } // 01: in the bound || 02： neiI & neiJ next step  1
            if (dfs(matrix, new int[]{neiI, neiJ}, dest, SHIFT)) {
                return true;
            }
        }
        matrix[start[0]][start[1]] = 0;
        return false;
    }

    private boolean checkValid(int i, int j, int[][] matrix) {
        if (i < 0 ){
            return false;
        }
        if (j < 0 ){
            return false;
        }
        if (i >= matrix.length) {
            return false;
        }
        if (j >= matrix[0].length) {
            return false;
        }
        if (matrix[i][i] != 0) {
            return false;
        }
        return true;


//        if (neiI + dir[0] >= 0  && neiJ + dir[1] >= 0 && neiI + dir[0] < matrix.length  && neiJ + dir[1] < matrix[0].length
//                && matrix[neiI+ dir[0]][neiJ + dir[1]] == 0) {
//            return true;
//        }

    }







//    private int getCount(int[][] matrix, int[][] SHIFT) {
//        int count = 0;
//        for (int i = 0; i <= matrix.length - 1; i ++) {
//            for (int j = 0; j < matrix[0].length; j ++) {
//                if (matrix[i][j] == 1) {
//                    continue;
//                }
//                for (int[] dir : SHIFT) {
//                    int newI = i + dir[0];
//                    int newJ = j + dir[1];
//                    if (newI < 0 || newJ < 0 || newI >= matrix.length || newJ >= matrix[0].length || matrix[newI][newJ] == 1) {
//                        count++;
//                        break;
//                    }
//                }
//
//            }
//        }
//        System.out.println("Bound Points = " + count);
//        return count;
//    }





    public static void main(String[] args) {

        //GraphSolution solution = new GraphSolution();
        //////////////////////////Alien Dictionary////////////////////////

//        String[] words = {"abc","ab"}; //{"ac","ab","zc","zb"};
//        String alien = solution.alienOrder(words);
//        System.out.println("Alien Solution is" + alien);
//
//        /////////////////////////LC 490 the Maze//////////////////////////
//        int[][] matrix = {{0,0,1,0,0},{0,0,0,0,0},{0,0,0,1,0},{1,1,0,1,1},{0,0,0,0,0}};
//        int[] start = {0,4};
//        int[] end = {3,2};
//        boolean maze = solution.hasPath(matrix, start, end);
//        System.out.println("490 Maze Solution is: " + maze);



        UnionJoinSolution solution = new UnionJoinSolution();


        //////////////////////////Number of Island II////////////////////////

        int m = 3;
        int n = 3;
        //int[][] positions = {{0,0},{0,1},{1,2},{2,1},{1,0},{0,0},{2,2},{1,2},{1,1},{0,1}};
        //int[][] positions = {{0,0},{0,1},{1,2},{2,1},{1,0},{0,0},{2,2},{1,2},{1,1},{0,1}};
        int[][] positions = {{0,0},{0,1},{1,2},{2,2}, {0, 2}};
        solution.numIslands2(m, n, positions);

    }

}
