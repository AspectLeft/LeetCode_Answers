class Solution {
    private int find(int l, int r, int[] window, int value) {
        if (l == r) return l;
        int mid = (l + r) / 2;
        if (window[mid] == value) return mid;
        if (window[mid] < value) return find(mid + 1, r, window, value);
        return find(l, mid - 1, window, value);
    }
    
    private void bubble(int[] window, int start) {
        while (start + 1 < window.length && window[start] > window[start + 1]) {
            int tmp = window[start];
            window[start] = window[start + 1];
            window[start + 1] = tmp;
            start++;
        }
        while (start > 0 && window[start] < window[start - 1]) {
            int tmp = window[start];
            window[start] = window[start - 1];
            window[start - 1] = tmp;
            start--;
        }
    }

    public double[] medianSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new double[]{};
        if (k == 1) {
            double[] doubles = new double[nums.length];
            for (int i = 0; i < nums.length; ++i) {
                doubles[i] = nums[i];
            }
            return doubles;
        }
        double[] doubles = new double[nums.length - k + 1];
        if (k % 2 == 1) {
            int l = 0, r = k - 1;
            int[] initSub = new int[k];
            System.arraycopy(nums, 0, initSub, 0, k);
            Arrays.sort(initSub);
            int median = initSub[k / 2];
            doubles[0] = median;
            int prev, next;
            r++;
            while (r <= nums.length - 1) {
                prev = nums[l];
                next = nums[r];
                l++;
                r++;
                if (prev == next) {
                    doubles[l] = median;
                }
                else  {
                    int index = find(0, k - 1, initSub, prev);
                    initSub[index] = next;
                    bubble(initSub, index);
                    median = initSub[k / 2];
                    doubles[l] = median;
                }
            }
        }
        else {
            int l = 0, r = k - 1;
            int[] initSub = new int[k];
            System.arraycopy(nums, 0, initSub, 0, k);
            Arrays.sort(initSub);
            double median = (1.0 * initSub[k / 2 - 1] + initSub[k / 2]) / 2.0;
            doubles[0] = median;
            int prev, next;
            r++;
            while (r <= nums.length - 1) {
                prev = nums[l];
                next = nums[r];
                l++;
                r++;
                if (prev == next) {
                    doubles[l] = median;
                }
                else  {
                    int index = find(0, k - 1, initSub, prev);
                    initSub[index] = next;
                    bubble(initSub, index);
                    median = (1.0 * initSub[k / 2 - 1] + initSub[k / 2]) / 2.0;
                    doubles[l] = median;
                }
            }
            
        }

        return doubles;

    }


}


/*
Sample answer on leetcode
class Solution { // from other's submission seems to be using tree
    public double[] medianSlidingWindow(int[] nums, int k) {
        Node root = null;
        int n = nums.length;
        double[] res = new double[n - k + 1];
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                root = new Node(nums[i]);
            } else {
                add(root, nums[i]);
            }
            if (i - k + 1 >= 0) {
                res[i - k + 1] = query(root, k / 2 + 1);
                if (k % 2 == 0) {
                    res[i - k + 1] += query(root, k / 2);
                    res[i - k + 1] /= 2.0;
                }
                remove(root, nums[i - k + 1]);
            }
        }
        return res;
    }
    
    public void add(Node root, int val) {
        if (root.val == val) {
            root.count++;
        } else if (root.val > val) {
            root.leftCount++;
            if (root.left == null) {
                root.left = new Node(val);
            } else {
                add(root.left, val);
            }
        } else {
            if (root.right == null) {
                root.right = new Node(val);
            } else {
                add(root.right, val);
            }
        }
    }
    
    public void remove(Node root, int val) {
        if (root.val == val) {
            root.count--;
        } else if (root.val < val) {
            remove(root.right, val);
        } else {
            root.leftCount--;
            remove(root.left, val);
        }
    }
    
    public int query(Node root, int k) {
        if (root.leftCount < k && root.count + root.leftCount >= k) {
            return root.val;
        }
        if (k <= root.leftCount) {
            return query(root.left, k);
        }
        return query(root.right, k - root.count - root.leftCount);
    }
}

class Node {
    int val;
    int leftCount;
    int count;
    Node left;
    Node right;
    
    public Node(int val) {
        this.val = val;
        count = 1;
    }
}

*/
