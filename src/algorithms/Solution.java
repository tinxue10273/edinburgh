class Solution {
    public int reverse(int x) {
        if (x == Integer.MIN_VALUE) {
            return 0;
        }
        boolean flag = true;
        if (x < 0) {
            flag = false;
            x = -x;
        }
        String r = "";
        while (x > 0) {
            r = r + (x % 10);
            x /= 10;
        }
        int res = 0;
        try {
            res = Integer.parseInt(r);
        } catch (NumberFormatException e) {
            return 0;
        }
        return flag ? res : -res;
    }
}