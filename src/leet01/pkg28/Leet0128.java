/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leet01.pkg28;

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
