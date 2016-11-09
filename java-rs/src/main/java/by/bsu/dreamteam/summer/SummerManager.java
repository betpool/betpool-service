package by.bsu.dreamteam.summer;

import javax.ejb.Stateless;

@Stateless
public class SummerManager {

    public String calculateSum(long n) {
        long result = 0;
        for (long i = 1; i <= n; ++i) {
            result += i;
        }
        return String.format("Sum(%d)=%d", n, result);
    }

}
