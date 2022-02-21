package primary.array;

/**
 * 删除排序数组中的重复项
 * 给你一个 升序排列 的数组 nums ，
 * 请你 原地 删除重复出现的元素，
 * 使每个元素 只出现一次 ，返回删除后数组的新长度。
 * 元素的 相对顺序 应该保持 一致 。
 */
public class RemoveDuplicates {
    public int removeDuplicates(int[] nums) {
        if (nums == null) {
            return 0;
        }

        //重复数位置记录指针
        int count = 0;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            count = i;

            //计算count时一定要非常注意越界问题
            while (true) {
                if (count >= len - 1) {
                    break;
                }
                if (nums[count + 1] != nums[count]) {
                    break;
                }
                count++;
            }

            if (count != i) {
                moveForward(nums, count, count - i, len);
                len -= count - i;
            }
        }

        return len;
    }

    //从start位置开始的数向前移动step步
    public static void moveForward(int[] nums, int start, int step, int length) {
        if (start - step < 0) {
            System.out.println("moveForward over 0 ! ! !");
            return;
        }
        for (; start < length; start++) {
            nums[start - step] = nums[start];
        }
    }


    //leetcode官方解法
    //https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/solution/shan-chu-pai-xu-shu-zu-zhong-de-zhong-fu-tudo/
    //不需要在意有多少个重复元素,也不需要整体前移,只要将找到的新数字往前覆盖即可
    //用一快一慢两个指针即可
    public int removeDuplicates2(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int fast = 1, slow = 1;
        while (fast < n) {
            if (nums[fast] != nums[fast - 1]) {
                nums[slow] = nums[fast];
                ++slow;
            }
            ++fast;
        }
        return slow;
    }


    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int len = new RemoveDuplicates().removeDuplicates(nums);
        for (int i = 0; i < len; i++) {
            System.out.print(nums[i] + ", ");
        }
    }

}
