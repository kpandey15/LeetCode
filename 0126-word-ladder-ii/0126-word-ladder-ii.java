import java.util.*;

class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        List<List<String>> result = new ArrayList<>();
        
        // If the end word isn't even in the dictionary, no path exists
        if (!wordSet.contains(endWord)) return result;
        
        Queue<String> q = new LinkedList<>();
        q.add(beginWord);
        // Remove beginWord from set to prevent cycling back to it
        if (wordSet.contains(beginWord)) wordSet.remove(beginWord);

        HashMap<String, ArrayList<String>> hm = new HashMap<>();
        boolean found = false;
        
        while (!q.isEmpty()) {
            int size = q.size();
            Set<String> nextLevel = new HashSet<>();
            
            for (int i = 0; i < size; i++) {
                String currWord = q.poll();
                char[] ch = currWord.toCharArray();
                
                for (int j = 0; j < ch.length; j++) {
                    char orgChar = ch[j];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (orgChar == c) continue;
                        ch[j] = c;
                        String newWord = String.valueOf(ch);
                        
                        if (wordSet.contains(newWord)) {
                            // If this is the first time we see this word on THIS level, queue it
                            if (!nextLevel.contains(newWord)) {
                                q.add(newWord);
                                nextLevel.add(newWord);
                            }
                            // Always add the parent-child relationship to our graph
                            hm.computeIfAbsent(newWord, k -> new ArrayList<>()).add(currWord);
                            
                            if (newWord.equals(endWord)) {
                                found = true;
                            }
                        }
                    }
                    // IMPORTANT: Reset the character back before moving to the next position
                    ch[j] = orgChar; 
                }
            }
            if (found) break; // Stop BFS once the shortest path is found
            
            // Remove all words visited on this level from the dictionary
            wordSet.removeAll(nextLevel);
        }

        // Only trigger DFS if we actually reached the end word
        if (found) {
            dfs(beginWord, endWord, hm, new ArrayList<>(), result);
        }
        
        return result;
    }
    
    public void dfs(String beginWord, String endWord, HashMap<String, ArrayList<String>> hm, ArrayList<String> currList, List<List<String>> result) {
        currList.add(endWord);
        
        if (endWord.equals(beginWord)) {
            // Create a COPY of the list before reversing and adding to result
            List<String> path = new ArrayList<>(currList);
            Collections.reverse(path);
            result.add(path);
        } else {
            // Use getOrDefault as a safety net, though 'found' guarantees it exists
            for (String prevWord : hm.getOrDefault(endWord, new ArrayList<>())) {
                dfs(beginWord, prevWord, hm, currList, result);
            }
        }
        
        // IMPORTANT: Backtrack by removing the current word before returning
        currList.remove(currList.size() - 1);
    }
}