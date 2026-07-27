class Pair{
    int v ;
    int wt;
    public Pair(int v , int wt){
        this.v = v;
        this.wt = wt;
    }
}
class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();

        for(int i=0;i<n+1;i++){
            adj.add(new ArrayList<Pair>());
        }

        for(int i=0;i<times.length;i++){
            int u = times[i][0];
            int v = times[i][1];
            int wt = times[i][2];

            adj.get(u).add(new Pair(v , wt));
        }
        PriorityQueue<Pair> pq = new PriorityQueue<Pair>((x,y) -> x.wt - y.wt);
        pq.add(new Pair(k , 0));

        int[] time = new int[n+1];
        Arrays.fill(time , (int)1e9);
        time[0]=0;
        time[k]=0;

         while(! pq.isEmpty()){
            int u = pq.peek().v;
            int wt = pq.peek().wt;
             pq.poll();

            for(int i=0;i<adj.get(u).size();i++){
                    int v = adj.get(u).get(i).v;
                    int p = adj.get(u).get(i).wt;

                    if(time[v] > wt + p){
                        time[v] = wt + p;
                        pq.add(new Pair(v , wt+p));
                    }
                }
         }

         int max = 0;

         for(int i =0;i<n+1;i++){
            max = Math.max(time[i],max);
         }

         return max == (int)1e9? -1 : max;
    }
}
