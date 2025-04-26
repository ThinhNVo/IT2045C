import java.util.ArrayList;

public class SortedList {
    private ArrayList<String> list;
    private boolean itemFound = false;

    public SortedList() {
        list = new ArrayList<>();
    }

    public void add(String item) {
        int low = 0, high = list.size() - 1, mid, target;

        while (low <= high) {
            mid = (low + high) / 2;
            target = list.get(mid).compareTo(item);
            if (target == 0) {
                break;
            }
            if (target > 0) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        list.add(low, item);
    }

    public int search(String item) {
        int low = 0, high = list.size() - 1, mid, target;

        if (!list.contains(item)) {
            while (low <= high) {
                mid = (low + high) / 2;
                target = list.get(mid).compareTo(item);

                if (target > 0) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            return low;
        }
        itemFound = true;
        return list.indexOf(item);
    }

    public String get(int index) {
        if (!itemFound) { return getListItems() + "\nItem was not found but will at index: " + index; }
        itemFound = false;
        return getListItems() + "\nItem was found: " + list.get(index);
    }

    public int size() {
        return list.size();
    }

    public String getListItems() {
        StringBuilder items = new StringBuilder();
        int order = 0;
        for (String s : list) {
            items.append(order).append(":").append(s).append("\n");
            order++;
        }
        return items.toString();
    }


}
