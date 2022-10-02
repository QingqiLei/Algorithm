import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class PrimitiveRoots {

    static boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        for (int i = 5; i * i <= n; i = i + 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }

    static int power(int x, int y, int p) {
        int res = 1;
        x = x % p;
        while (y > 0) {
            if (y % 2 == 1) {
                res = (res * x) % p;
            }
            y = y >> 1;
            x = (x * x) % p;
        }
        return res;
    }

    static int gcd(int a, int b){
        return a%b == 0? b: gcd(b, a%b);
    }

    static List<Integer> findPrimitive(int n) {
        List<Integer> roots = new ArrayList<>();
        HashSet<Integer> coPrime = new HashSet<>();
        if (!isPrime(n)) return roots;
        int phi = n - 1;
        for(int i = 2; i < n; i++) if(gcd(n, i) == 1) coPrime.add(i);
        for (int r = 2; r <= phi; r++) {
            boolean isRoot = true;
            for (Integer a : coPrime) {
                if (power(r, phi / (a), n) == 1) {
                    isRoot = false;
                    break;
                }
            }
            if (isRoot) roots.add(r);
        }
        return roots;
    }

    static void elgamal(int p, int a, int b,int d, int ke, int x){
//        int r = powerMod(a, ke, p);
//        int s = ((x - d * r) * minusMod(ke, p-1) )%(p-1) ;
        x = 10;
        int r = 17, s = 5;
        if(s < 0) s = s + p-1;
        System.out.println(r+" "+s);
        int t = (powerMod(b,r,p) * powerMod(r, s, p))%p;
        System.out.println(t+" "+powerMod(a, x, p));
    }

    static int powerMod(int x ,int y, int mod){
        int res = 1;
        for(int i = 0; i < y; i++) res = (res * x)%mod;
        return res;
    }

    static int minusMod(int x, int mod){
        for(int i = 1; i < mod; i++){
            if(i * x % mod == 1) return i;
        }
        return -1;
    }

    static void DSA(int p, int q, int a, int d, int hx, int ke){
        int b = powerMod(a,d,p);
        System.out.println(b);
        int r = powerMod(a, ke, p)%q;
        int s = ((hx + d * r) * minusMod(ke, q)) %q;
        System.out.println(r +"  " +s);

        int w = minusMod(s,q);
        int u1 = w * hx % q;
        int u2 = w * r % q;
        int v = ((powerMod(a, u1, p) * powerMod(b, u2,p))%p)%q;
        System.out.println(w +" "+u1+" "+u2);
        System.out.println(v +" "+(r%q));
    }

    public static void main(String[] args) {
//        elgamal(31, 3, 6,67,77,22);
        DSA(59, 29, 3, 23, 21, 8);
//        System.out.println(Integer.toBinaryString(72));

    }



}
