class Solution {
public:
    double median2(vector<int>& nums, int x, int y) {
        int s = nums.size();
        if (s == 1) {
            int z = nums[0];
            if (z < x) {
                return 1.0 * x;
            }
            else if (z < y) {
                return 1.0 * z;
            }
            else {
                return 1.0 * y;
            }
        }
        else if (s == 2) {
            int z = nums[0], w = nums[1];
            if (y <= z) {
                return (y + z) / 2.0;
            }
            else if (y <= w) {
                if (x <= z) {
                    return (y + z) / 2.0;
                }
                else {
                    return (x + y) / 2.0;
                }
            }
            else {
                if (x <= z) {
                    return (z + w) / 2.0;
                }
                else {
                    return (x + w) / 2.0;
                }
            }
        }
        else if (s % 2) {
            int m = nums[s / 2], prev_m = nums[s / 2 - 1], next_m = nums[s / 2 + 1];
            if (y <= m) {
                if (y <= prev_m) {
                    return 1.0 * prev_m;
                }
                else {
                    return 1.0 * y;
                }
            }
            else if (x <= m) {
                return 1.0 * m;
            }
            else {
                if (x <= next_m) {
                    return 1.0 * x;
                }
                else {
                    return 1.0 * next_m;
                }
            }
        }
        else {
            int ll = nums[s / 2 - 2], rr = nums[s / 2 + 1];
            int l = nums[s / 2 - 1], r = nums[s / 2];
            if (y <= ll) {
                return (ll + l) / 2.0;
            }
            else if (y <= l) {
                return (y + l) / 2.0;
            }
            else if (y <= r) {
                if (x <= l) {
                    return (y + l) / 2.0;
                }
                else {
                    return (x + y) / 2.0;
                }
            }
            else if (y <= rr) {
                if (x <= l) {
                    return (l + r) / 2.0;
                }
                else {
                    return (x + r) / 2.0;
                }
            }
            else {
                if (x <= l) {
                    return (l + r) / 2.0;
                }
                else if (x <= rr) {
                    return (x + r) / 2.0;
                }
                else {
                    return (r + rr) / 2.0;
                }
            }
            
        }
        
    }
    
    double median1(vector<int>& nums, int x) {
        int s = nums.size();
        if (s == 1) {
            return (nums[0] + x) / 2.0;
        }
        else if (s % 2) {
            int m = nums[s / 2], prev_m = nums[s / 2 - 1], next_m = nums[s / 2 + 1];
            if (x <= prev_m) {
                return (prev_m + m) / 2.0;
            }
            else if (x <= next_m) {
                return (x + m) / 2.0;
            }
            else {
                return (next_m + m) / 2.0;
            }
            
        }
        else {
            int left = nums[s / 2 - 1], right = nums[s / 2];
            if (x <= left) {
                return 1.0 * left;
            }
            else if (x <= right) {
                return 1.0 * x;
            }
            else {
                return right;
            }
        }
    }
    
    
    double median(vector<int>& nums) {
        int s = nums.size();
        return (nums[s / 2] + nums[(s - 1) / 2]) / 2.0;
    }

    double findMedianSortedArrays(vector<int>& nums1, vector<int>& nums2) {
        if (nums1.size() < nums2.size()) {
            return findMedianSortedArrays(nums2, nums1);
        }
        double m1 = median(nums1);
        int s2 = nums2.size();
        if (s2 == 0) {
            return m1;
        }
        if (s2 == 1) {
            return median1(nums1, nums2[0]);
        }
        if (s2 == 2) {
            return median2(nums1, nums2[0], nums2[1]);
        }
        double m2 = median(nums2);
        if (m1 < m2) {
            vector<int> new_nums1(nums1.begin() + (nums2.size() - 1) / 2, nums1.end());
            vector<int> new_nums2(nums2.begin(), nums2.end() - (nums2.size() - 1) / 2);
            return findMedianSortedArrays(new_nums1, new_nums2);
        }
        else if (m1 > m2) {
            vector<int> new_nums1(nums1.begin(), nums1.end() - (nums2.size() - 1) / 2);
            vector<int> new_nums2(nums2.begin() + (nums2.size() - 1) / 2, nums2.end());
            return findMedianSortedArrays(new_nums1, new_nums2);
        }
        else {
            return m1;
        }
    }
};