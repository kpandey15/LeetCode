class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> wordSet = new HashSet<>();
        int level = 1;
        for(int i=0;i<wordList.size();i++){
            wordSet.add(wordList.get(i));
        }
        Queue<String> q = new LinkedList<>();
        q.add(beginWord);
        while(! q.isEmpty()){
            int size = q.size();

            for(int i=0;i<size;i++){
                String currentWord = q.poll();
                if(currentWord.equals(endWord))
                    return level;

                char[] wordChars = currentWord.toCharArray();
                for(int c=0;c<wordChars.length;c++){
                    char originalChar = wordChars[c];
                    for(char j = 'a';j<='z';j++){

                        if(originalChar == j) continue;

                        wordChars[c] = j;
                        String newWord = String.valueOf(wordChars);

                        // If the new word is in the dictionary, it's a valid next step
                        if (wordSet.contains(newWord)) {
                            q.add(newWord);
                            wordSet.remove(newWord); // Remove to prevent visiting it again (acts as 'visited' set)
                        }
                    }                       
                    // Restore the original character before moving to the next position
                    wordChars[c] = originalChar;
                }
            }
            level++;            
        }
        return 0;
    }
}