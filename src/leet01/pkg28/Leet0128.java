/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leet01.pkg28;
/*
Given a string, find the length of the longest substring without repeating characters.
Examples:
Given "abcabcbb", the answer is "abc", which the length is 3.
Given "bbbbb", the answer is "b", with the length of 1.
Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
*/
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int stringLength=s.length();
        int targetLength=-1;
        String expand=new String();
        String subToAdd=new String();
        for(int i=0;i<stringLength;i++){
            subToAdd=s.substring(i,i+1);
            if(i==0){
                expand.concat(subToAdd);
            }
            else{
                char currentChar=s.charAt(i);
                System.out.printf( currentChar+"    "+subToAdd); 
                if(expand.indexOf(currentChar)==-1){
                    expand.concat(subToAdd);
                    System.out.printf("%",i);
                 }
                 else{
                      targetLength=i;
                      break;
                  }
                }
            }
            return targetLength;
        }
        public int main(){
            return lengthOfLongestSubstring("abcabcbb");
            
        }
    }
