import java.util.Arrays;

public class DynamicProgrammingEAROP 
{

    static int[][] costMatrix = 
    {
        {0, 15, 25, 35}, {15, 0, 30, 28}, {25, 30, 0, 20}, {35, 28, 20, 0}
    };
    static String[] locations = 
    {
        "Hospital", "Emergency Location B", "Emergency Location C", "Emergency Location D"
    };

    public static String dynamicProgramming(int[][] dist) 
    {
        int n = dist.length;
        int VISITED_ALL = (1 << n) - 1;
        int[][] memo = new int[1 << n][n];
        String[][] paths = new String[1 << n][n];

        for (int[] row : memo) Arrays.fill(row, -1);

        int totalCost = dpHelper(0, 1, dist, memo, VISITED_ALL, paths);
        return "DP Route: Hospital" + paths[1][0] + "\nTotal Cost: " + totalCost;
    }

    private static int dpHelper(int pos, int mask, int[][] dist, int[][] memo, int VISITED_ALL, String[][] paths) 
    {
        if (mask == VISITED_ALL) 
        {
            paths[mask][pos] = " -> " + locations[0];
            return dist[pos][0];
        }
        if (memo[mask][pos] != -1) return memo[mask][pos];

        int ans = Integer.MAX_VALUE;
        String bestPath = "";

        for (int nxt = 0; nxt < dist.length; nxt++) 
        {
            if ((mask & (1 << nxt)) == 0) 
            {
                int newCost = dist[pos][nxt] + dpHelper(nxt, mask | (1 << nxt), dist, memo, VISITED_ALL, paths);
                if (newCost < ans) 
                {
                    ans = newCost;
                    bestPath = " -> " + locations[nxt] + paths[mask | (1 << nxt)][nxt];
                }
            }
        }
        paths[mask][pos] = bestPath;
        return memo[mask][pos] = ans;
    }

    public static void main(String[] args) 
    {
        System.out.println(dynamicProgramming(costMatrix));
    }
}