class Solution {
    public String[] findRestaurant(String[] list1, String[] list2) {
        if (list1.length > list2.length) {
            String[] tmp = list1;
            list1 = list2;
            list2 = tmp;
        }
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < list1.length; ++i) {
            map.put(list1[i], i);
        }
        int indexSum = Integer.MAX_VALUE;
        int best_i = 0;
        Integer i = 0;
        List<String> result = new ArrayList<>();
        for (int j = 0; j < list2.length; ++j) {
            i = map.get(list2[j]);
            if (i == null) continue;
            if (i + j < indexSum) {
                result.clear();
                result.add(list2[j]);
                
                indexSum = i + j;
                best_i = i;
            }
            else if (i + j == indexSum) {
                result.add(list2[j]);
            }
        }
        return result.toArray(new String[0]);
    }
}
