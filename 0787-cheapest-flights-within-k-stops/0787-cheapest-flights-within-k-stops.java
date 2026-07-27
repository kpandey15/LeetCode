class Pair{
    int v ;
    int wt;
    int level;
    public Pair(int v , int wt , int level){
        this.v = v;
        this.wt = wt;
        this.level = level;
    }
}
class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();

        for(int i=0;i<n;i++){
            adj.add(new ArrayList<Pair>());
        }

        for(int i=0;i<flights.length;i++){
            int u = flights[i][0];
            int v = flights[i][1];
            int wt = flights[i][2];
            adj.get(u).add(new Pair(v , wt , 0));
        }


        PriorityQueue<Pair> pq = new PriorityQueue<Pair>((x,y) -> x.level - y.level);

        pq.add(new Pair(src,0 , 0));

        int[] price = new int[n];
        Arrays.fill(price , (int)1e9);

        price[src]=0;

        while(! pq.isEmpty()){
            int u = pq.peek().v;
            int wt = pq.peek().wt;
            int level = pq.peek().level;
            pq.poll();
            
            for(int i=0;i<adj.get(u).size();i++){
                int v = adj.get(u).get(i).v;
                int p = adj.get(u).get(i).wt;

                if(price[v] > wt + p && level < k+1){
                    price[v] = wt + p;
                    pq.add(new Pair(v , wt+p , level+1));
                }
            }       
        }
        return price[dst] == (int)1e9 ? -1 : price[dst];
    }
}