public class BacktrackingEAROP 
{

    static int[][] costMatrix = 
    {
        {0, 15, 25, 35}, {15, 0, 30, 28}, {25, 30, 0, 20}, {35, 28, 20, 0}
    };
    static String[] locations = 
    {
        "Hospital", "Emergency Location B", "Emergency Location C", "Emergency Location D"
    };
    static int btBestCost = Integer.MAX_VALUE;
    static String btBestRoute = "";

    public static String backtracking(int[][] dist) 
    {
        int n = dist.length;
        boolean[] visited = new boolean[n];
        visited[0] = true; 
        btBestCost = Integer.MAX_VALUE;
        btBestRoute = "";

        StringBuilder path = new StringBuilder(locations[0]);
        btHelper(0, dist, visited, n, 1, 0, path);

        return "Backtracking Route: " + btBestRoute + "\nTotal Cost: " + btBestCost;
    }

    private static int btHelper(int pos, int[][] dist, boolean[] visited, int n, int count, int cost, StringBuilder path) 
    {
        if (cost >= btBestCost) return Integer.MAX_VALUE;

        if (count == n) 
        {
            int total = cost + dist[pos][0];
            if (total < btBestCost) 
            {
                btBestCost = total;
                btBestRoute = path + " -> " + locations[0];
            }
            return total;
        }

        int best = Integer.MAX_VALUE;
        for (int next = 1; next < n; next++) 
        {
            if (!visited[next]) 
            {
                visited[next] = true;
                int lengthBefore = path.length();
                path.append(" -> ").append(locations[next]);

                int result = btHelper(next, dist, visited, n, count + 1, cost + dist[pos][next], path);
                best = Math.min(best, result);

                path.setLength(lengthBefore);
                visited[next] = false;
            }
        }
        return best;
    }

    public static void main(String[] args) 
    {
        System.out.println(backtracking(costMatrix));
    }
}