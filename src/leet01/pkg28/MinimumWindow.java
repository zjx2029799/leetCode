import java.util.HashMap;

public class MinimumWindow {
    public static void main(String[] args){
        String str0 = "ADOBECODEBANC";
        String tar = "ABC";
        Solution s = new Solution();
        System.out.print(s.minWindow(str0,tar).toString());

    }
}



class Solution {
    public String minWindow(String s, String t) {
        char[] orgChar = s.toCharArray();
        char[] tarChar = t.toCharArray();
        HashMap<Character,Integer> map = new HashMap<>();
        for(char c : tarChar){
            Integer freq = map.get(c);
            if(freq==null){
                map.put(c,1);
            }else {
                map.put(c,freq+1);
            }
        }
        int left = 0;
        int right = 0;
        int count = t.length();
        int startindex = -1;
        int gloMin = Integer.MAX_VALUE;
        while(right<orgChar.length){
            Integer freq = map.get(orgChar[right]);
            if(freq!=null){
                map.put(orgChar[right],freq-1);
                if(freq>0) count--;

            }
            right++;
            while(count==0){
                if(right-left<gloMin){
                    gloMin = right -left;
                    startindex = left;
                }
                Integer exist = map.get(orgChar[left]);
                if(exist!=null){
                    map.put(orgChar[left],exist+1);
                    if(exist == 0) count++;
                }
                left++;
            }
        }
        return gloMin == Integer.MAX_VALUE ? new String(): new String(orgChar,startindex,gloMin);
    }
}