public class ZigzagIterator {
    boolean at1 = true;
    ListIterator<Integer> it1 = null, it2 = null;

    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        if (v1 != null) it1 = v1.listIterator();
        if (v2 != null) it2 = v2.listIterator();
    }

    public int next() {
        if (it1 == null) {
            return it2.next();
        }
        if (it2 == null) {
            return it1.next();
        }
        int v;
        if (at1) {
            if (it1.hasNext()) {
                v = it1.next();
            }
            else {
                v = it2.next();
            }
        }
        else {
            if (it2.hasNext()) {
                v = it2.next();
            }
            else {
                v = it1.next();
            }
        }
        at1 = !at1;
        return v;
    }

    public boolean hasNext() {
        if (it1 != null && it1.hasNext()) return true;
        if (it2 != null && it2.hasNext()) return true;
        return false;
    }
}

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */
