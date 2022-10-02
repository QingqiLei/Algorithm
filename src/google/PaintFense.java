package google;

/*
农民伯伯刷篱笆，每块篱笆都会被刷。共有26种颜色的油漆，从A到Z依次变深，
浅色油漆可以被涂上更深的颜色，但深色不能刷回浅色的。给定一条刷完的篱笆，
问至少刷多少次。
eg：AABCABD: AAAAAAA->AABBAAA->AABBABB->AABCABB->AABCABD,5次。
 */
public class PaintFense {
    int minTimes(String colors){
        return paint(colors, 0, colors.length() -1);
    }

    int paint(String colors, int left, int right){
        if(left == right) return 1;
        int paints = 1;
        char color = 'Z';
        for(int i = left; i <= right; i++) color = (char)Math.min((int)colors.charAt(i), (int)color);
        for(int i = left; i <= right; i++){
            if(colors.charAt(i) != color){
                int j = i;
                while(j <= right && colors.charAt(j) != color) j++;
                j--;
                paints += paint(colors, i, j);
                i = j;
            }
        }
        return paints;
    }

    public static void main(String[] args) {
        System.out.println(new PaintFense().minTimes("AABCABD"));
    }
}
