public class Trie {


    public static int lcd(int a, int b){
        return a%b == 0?b: lcd(b, a%b);
    }

    public static void main(String[] args) {
        System.out.println(lcd(640,32));
        System.out.println(Integer.toBinaryString(17));

    }

}
