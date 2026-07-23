class Pair{
    int dis;
    int i;
    int j;
    public Pair(int dis , int i , int j){
        this.dis = dis;
        this.i = i;
        this.j = j;
    }
}
class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        if(grid[0][0] == 1)
            return -1;
        int[][] dist = new int[grid.length][grid[0].length];
        PriorityQueue<Pair> pq = new PriorityQueue<Pair>((x,y) -> x.dis - y.dis);

        for(int[] ar : dist){
            Arrays.fill(ar , (int)1e9);
        }

        dist[0][0] = 1;

        pq.add(new Pair(1 , 0 , 0));
        while(! pq.isEmpty()){
            int dis = pq.peek().dis;
            int i = pq.peek().i;
            int j = pq.peek().j;

            pq.poll();

            if(i == grid.length-1 && j == grid[0].length-1)
                break;
            if(i > 0){
                if(dist[i-1][j] > dis+1 && grid[i-1][j] != 1){
                    dist[i-1][j] = dis+1;
                    pq.add(new Pair(dis+1 , i-1 , j));
                }

                if(j > 0){
                    if(dist[i-1][j-1] > dis+1 && grid[i-1][j-1] != 1){
                        dist[i-1][j-1] = dis+1;
                        pq.add(new Pair(dis+1 , i-1 , j-1));
                    }
                }

                if(j <= grid[0].length-2){
                    if(dist[i-1][j+1] > dis+1+1 && grid[i-1][j+1] != 1){
                        dist[i-1][j+1] = dis+1+1;
                        pq.add(new Pair(dis+1 , i-1 , j+1));
                    }
                }
            }

            if(j > 0){
                if(dist[i][j-1] > dis+1 && grid[i][j-1] != 1){
                    dist[i][j-1] = dis+1;
                    pq.add(new Pair(dis+1 , i, j-1));
                }
                if(i <= grid.length-2){
                    if(dist[i+1][j-1] > dis+1 && grid[i+1][j-1] != 1){
                        dist[i+1][j-1] = dis+1;
                        pq.add(new Pair(dis+1 , i+1 , j-1));
                    }
                }
            }
            if(j <= grid[0].length-2){
                if(dist[i][j+1] > dis+1 && grid[i][j+1] != 1){
                    dist[i][j+1] = dis+1;
                    pq.add(new Pair(dis+1, i , j+1));
                }
                if(i <= grid.length-2){
                    if(dist[i+1][j+1] > dis+1 && grid[i+1][j+1] != 1){
                        dist[i+1][j+1] = dis+1;
                        pq.add(new Pair(dis+1, i+1 , j+1));
                    }
                }
            }
            if(i <= grid.length-2){
                if(dist[i+1][j] > dis+1 && grid[i+1][j] != 1){
                    dist[i+1][j] = dis+1;
                    pq.add(new Pair(dis+1, i+1 , j));
                }
            }          
        }

        if(dist[grid.length-1][grid[0].length-1] == 1e9)
            return -1;
        
        return dist[grid.length-1][grid[0].length-1];

    }
}