class Pair{
    int effort;
    int i;
    int j;
    public Pair(int effort , int i , int j){
        this.effort = effort;
        this.i = i;
        this.j = j;
    }
}
class Solution {
    public int minimumEffortPath(int[][] heights) {
        int n = heights.length;
        int m = heights[0].length;
        int[][] efforts = new int[n][m];

        for(int[] eff : efforts){
            Arrays.fill(eff , (int)1e9);
        }

        efforts[0][0] = 0;

        PriorityQueue<Pair> pq = new PriorityQueue<Pair>((x,y) -> x.effort - y.effort);

        pq.add(new Pair(0 , 0 ,0));

        while(! pq.isEmpty()){
            int effort = pq.peek().effort;
            int i = pq.peek().i;
            int j = pq.peek().j;

            pq.poll();
            if(i > 0){    
                int currEffort =  Math.abs(heights[i][j]-heights[i-1][j]);
                int maxEffort =  currEffort > effort ?currEffort : effort ;

                if(maxEffort < efforts[i-1][j]){
                    efforts[i-1][j] = maxEffort;
                    pq.add(new Pair(maxEffort , i-1,j));
                }
               
            }
            if(j > 0){
                int currEffort =  Math.abs(heights[i][j]-heights[i][j-1]);
                int maxEffort =  currEffort > effort ? currEffort : effort ;
                if(maxEffort < efforts[i][j-1]){
                    efforts[i][j-1] = maxEffort;
                    pq.add(new Pair(maxEffort , i,j-1));
                }
            }
            if(i < n-1){
                int currEffort =  Math.abs(heights[i][j]-heights[i+1][j]);
                int maxEffort =  currEffort > effort ?currEffort : effort ;
                if(maxEffort < efforts[i+1][j]){
                    efforts[i+1][j] = maxEffort;
                    pq.add(new Pair(maxEffort , i+1,j));
                }
            }
            if(j < m-1){
                int currEffort =  Math.abs(heights[i][j]-heights[i][j+1]);
                int maxEffort =  currEffort > effort ?currEffort : effort ;
                if(maxEffort < efforts[i][j+1]){
                    efforts[i][j+1] = maxEffort;
                    pq.add(new Pair(maxEffort , i,j+1));
                }
            }

        }
        return efforts[n-1][m-1]; 

    }
}