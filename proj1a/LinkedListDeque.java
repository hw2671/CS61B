public class LinkedListDeque<Item> {

    // class for the actual nodes
    public class ListNode<Item> {
        private ListNode prev;
        private Item item;
        private ListNode next;

        public ListNode(ListNode p, Item i, ListNode n) {
            prev = p;
            item = i;
            next = n;
        }
    }


    private int size = 0;
    // sentinel node.
    private ListNode middleMan = new ListNode(null, null, null);

    public LinkedListDeque() {
        middleMan = new ListNode(null, null, null);
    }

    public LinkedListDeque(LinkedListDeque other) {
        middleMan = new ListNode(null, null, null);
        int sizeOfOtherLinkedList = other.size();

        for (int i = 0; i < sizeOfOtherLinkedList; i++) {
            this.addLast( (Item) other.get(i) );
        }
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item i) {
        size += 1;

        if(middleMan.next == null) {
            middleMan.next = new ListNode(middleMan, i, middleMan);
        } else {
            middleMan.next = new ListNode(middleMan, i, middleMan.next);
        }
    }

    public void addLast(Item i) {
        if(middleMan.prev == null) {
            addFirst(i);
        } else {
            size += 1;
            middleMan.prev = new ListNode(middleMan.prev, i, middleMan);
        }
    }

    public void printDeque() {
        String output = "";

        if (this.isEmpty()) {
            System.out.println(output + "List is empty");
        } else {
            ListNode ptr = middleMan.next;
            while (ptr != middleMan) {
                output += ptr.item + " ";
                ptr = ptr.next;
            }
            System.out.println(output);
        }
    }

    public Item removeFirst() {
        if (this.isEmpty()) {
            return null;
        } else if (size == 1) {
            Item i = (Item) middleMan.next.item;
            middleMan.next = null;
            size -= 1;
            return i;
        }

        // remove prev and next references from 'first item'
        ListNode first = middleMan.next;
        middleMan.next = first.next;
        first.next.prev = middleMan;

        // clear out first to prevent loitering
        Item i = (Item) first.item;
        first = null;
        size -= 1;
        return i;

    }

    public Item removeLast() {
        if (this.isEmpty()) return null;

        if (size == 1) {
            Item i = (Item) middleMan.next.item;
            middleMan.next = null;
            size -= 1;
            return i;
        }

        // remove prev and next references from 'last item'
        ListNode last = middleMan.prev;
        middleMan.prev = last.prev;
        last.prev = middleMan;

        // clear out last item to prevent loitering
        Item i = (Item) last.item;
        last = null;
        size -= 1;
        return i;
    }

    // get ith element in list (must be iterative)
    public Item get(int index) {
        int i = 0;
        ListNode ptr = middleMan.next;

        while(i <= index) {
            if (i == index) return (Item) ptr.item;
            ptr = ptr.next;
            i++;
        }

        return null;
    }

    // recursive get
    public Item getRecursive(int index) {
        if (index > size) return null;
        return (Item) getRecursiveNode(index, middleMan.next);
    }

    private Item getRecursiveNode (int index, ListNode node) {
        if (index == 0) {
            return (Item) node.item;
        }

        return (Item) getRecursiveNode(index - 1, node.next);
    }
}
