class Pair{                                                                                                                                                            int v;
    int time;
      public Pair(int v, int time){                                                                                                                                                                                                              
          this.v=v;                                                                                 
          this.time = time; 
        }                                                                                                                                                                                                                                          
  }                                                                                                                                                      class Solution {                                                                                                                                                                                                                               
      public int countPaths(int n, int[][] roads) {
          int MOD = (int)1e9 + 7; 
          ArrayList<ArrayList<Pair>> adj = new ArrayList<>(); 
          for(int i = 0; i < n; i++){ 
            adj.add(new ArrayList<Pair>()); 
            } 
            for(int i = 0; i < roads.length; i++){ 
                int u = roads[i][0];                          
              int v = roads[i][1];
              int time = roads[i][2]; 
              adj.get(u).add(new Pair(v, time)); 
              adj.get(v).add(new Pair(u, time)); 
              }
              long[] times = new long[n];
              long[] ways = new long[n]; 
              Arrays.fill(times, (long)1e18); 
              times[0] = 0; 
              ways[0] = 1;
            PriorityQueue<Pair> pq = new PriorityQueue<Pair>((x,y) -> x.time - y.time);   
            pq.add(new Pair(0, 0)); 
            while(!pq.isEmpty()){
            Pair curr = pq.poll();
              int u = curr.v;
              long time = curr.time;
              if(time > times[u]) continue; 
              for(int i = 0; i < adj.get(u).size(); i++){
                  int v = adj.get(u).get(i).v;              
                  int p = adj.get(u).get(i).time; 
                   if(times[v] > time + p){ 
                    times[v] = time + p;
                      ways[v] = ways[u];
                       pq.add(new Pair(v, (int)(time + p)));                                                                                                                                                                                      
                  } else if(times[v] == time + p){
                    ways[v] = (ways[v] + ways[u]) % MOD;
                      }
                      }
                      }                                                                                                                                                                                                                                      
          return (int) ways[n-1];                         // CHANGED: return ways[n-1]
      }                                                                                                                                                                                                                                          
  }