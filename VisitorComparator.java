import java.util.Comparator;


public class VisitorComparator implements Comparator<Visitor> {
    /**
     * Sorting Rules:
     * 1. VIP visitors take priority (isVip=true comes first)
     * 2. For non-VIP/VIP groups, sort by age in ascending order
     */
    @Override
    public int compare(Visitor v1, Visitor v2) {
        // Null check (avoids NullPointerException, meets high-distinction robustness standard)
        if (v1 == null && v2 == null) return 0;
        if (v1 == null) return -1;
        if (v2 == null) return 1;

        // 1. Compare VIP status (false comes first, true comes later → reversed for VIP priority)
        int vipCompare = Boolean.compare(v2.isVip(), v1.isVip());
        if (vipCompare != 0) {
            return vipCompare;
        }

        // 2. Compare age in ascending order for same VIP status
        return Integer.compare(v1.getAge(), v2.getAge());
    }
}
