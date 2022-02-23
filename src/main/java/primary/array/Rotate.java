package primary.array;

import java.util.Arrays;

/**
 * 旋转数组
 * https://leetcode-cn.com/problems/rotate-array/solution/xuan-zhuan-shu-zu-by-leetcode-solution-nipk/
 */

/**
 * 求最大公约数法
 * 当nums.length和k存在最大公约数count>1时,nums在循环遍历时会以count个数字为一组循环,
 * 就无法遍历到nums中所有的数,所以要分成length/count组,每次循环移位操作后回到头部,要切入下一组.
 */
public class Rotate {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        int count = gcd(k, n);

        for (int start = 0; start < count; ++start) {
            int current = start;
            int prev = nums[start];
            do {
                int next = (current + k) % n;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
            } while (start != current);
        }
    }

    //求最大公约数
    public int gcd(int x, int y) {
        return y > 0 ? gcd(y, x % y) : x;
    }

    /**
     * 方法2数组翻转
     */
    public void rotate2(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start += 1;
            end -= 1;
        }
    }

    /**
     * 方法3 用一个新数组,直接放到新数组中,比较简单.
     */
    public void rotate3(int[] nums, int k) {
        int n = nums.length;
        int[] newArr = new int[n];
        for (int i = 0; i < n; ++i) {
            newArr[(i + k) % n] = nums[i];
        }
        System.arraycopy(newArr, 0, nums, 0, n);
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2};
        new Rotate().rotate(nums, 2);
    }
}
