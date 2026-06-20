/*
 * @lc app=leetcode id=394 lang=java
 *
 * [394] Decode String
 */

import java.util.*;

// @lc code=start
class Solution {
    public String decodeString(String s) {
        // stores repeat numbers
        Deque<Integer> counts = new ArrayDeque<>();  
        // stores previous strings 
        Deque<StringBuilder> strings = new ArrayDeque<>(); 
        StringBuilder current = new StringBuilder();
        int k = 0;

        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                // c - '0' convert char to int
                // build multi digit number k
                k = k * 10 + (c - '0');              
            } else if (c == '[') {
                counts.push(k);                       
                strings.push(current);                
                current = new StringBuilder();   
                k = 0;  
            } else if (c == ']') {   
                int repeat = counts.pop();
                StringBuilder prev = strings.pop();
                for (int i = 0; i < repeat; i++) {
                    prev.append(current);   
                }         
                current = prev;                      
            } else {
                current.append(c);                    
            }
        }

        return current.toString();
    }
}

// Time: O(n + m)
// Space: O(n + m)
// @lc code=end

