public class DivideAndConquerEAROP 
{

    static int[][] costMatrix = 
    {
        {0, 15, 25, 35}, {15, 0, 30, 28}, {25, 30, 0, 20}, {35, 28, 20, 0}
    };
    static int minTotalCost = Integer.MAX_VALUE;
    static String bestRoute = "";

    public static String divideAndConquer(int[][] dist) 
    {
        int n = dist.length;
        boolean[] visited = new boolean[n];
        visited[0] = true; 
        
        minTotalCost = Integer.MAX_VALUE;
        bestRoute = "";
        StringBuilder startingPath = new StringBuilder("Hospital");
        
        divideAndConquerHelper(0, visited, 0, dist, n, startingPath);
        return "Divide & Conquer Route: " + bestRoute + "\nMinimum Cost: " + minTotalCost;
    }

    private static int divideAndConquerHelper(int pos, boolean[] visited, int currentCost, int[][] dist, int n, StringBuilder path) 
    {
        if (allVisited(visited)) 
        {
            int totalCost = currentCost + dist[pos][0];
            if (totalCost < minTotalCost) 
            {
                minTotalCost = totalCost;
                bestRoute = path.toString() + " -> Hospital";
            }
            return totalCost;
        }

        int localMin = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) 
        {
            if (!visited[i]) 
            {
                visited[i] = true;
                String locName = (i == 1) ? "B" : (i == 2) ? "C" : "D";
                int originalLength = path.length();
                path.append(" -> ").append(locName);
                
                int branchCost = divideAndConquerHelper(i, visited, currentCost + dist[pos][i], dist, n, path);
                localMin = Math.min(localMin, branchCost);
                
                visited[i] = false;
                path.setLength(originalLength); 
            }
        }
        return localMin;
    }

    private static boolean allVisited(boolean[] visited) 
    {
        for (boolean v : visited) 
        {
            if (!v) return false;
        }
        return true;
    }

    public static void main(String[] args) 
    {
        System.out.println(divideAndConquer(costMatrix));
    }
}