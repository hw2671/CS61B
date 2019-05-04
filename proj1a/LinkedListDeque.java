public class LinkedListDeque<T> {

    // class for the actual nodes
    public class ListNode<T> {
        private ListNode prev;
        private T T;
        private ListNode next;

        public ListNode(ListNode p, T i, ListNode n) {
            prev = p;
            T = i;
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
            this.addLast((T) other.get(i));
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(T i) {
        size += 1;

        if (middleMan.next == null) {
            middleMan.next = new ListNode(middleMan, i, middleMan);
        } else {
            middleMan.next = new ListNode(middleMan, i, middleMan.next);
        }
    }

    public void addLast(T i) {
        if (middleMan.prev == null) {
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
                output += ptr.T + " ";
                ptr = ptr.next;
            }
            System.out.println(output);
        }
    }

    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        } else if (size == 1) {
            T i = (T) middleMan.next.T;
            middleMan.next = null;
            size -= 1;
            return i;
        }

        // remove prev and next references from 'first T'
        ListNode first = middleMan.next;
        middleMan.next = first.next;
        first.next.prev = middleMan;

        // clear out first to prevent loitering
        T i = (T) first.T;
        first = null;
        size -= 1;
        return i;

    }

    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }

        if (size == 1) {
            T i = (T) middleMan.next.T;
            middleMan.next = null;
            size -= 1;
            return i;
        }

        // remove prev and next references from 'last T'
        ListNode last = middleMan.prev;
        middleMan.prev = last.prev;
        last.prev = middleMan;

        // clear out last T to prevent loitering
        T i = (T) last.T;
        last = null;
        size -= 1;
        return i;
    }

    // get ith element in list (must be iterative)
    public T get(int index) {
        int i = 0;
        ListNode ptr = middleMan.next;

        while (i <= index) {
            if (i == index) {
                return (T) ptr.T;
            }
            ptr = ptr.next;
            i++;
        }

        return null;
    }

    // recursive get
    public T getRecursive(int index) {
        if (index > size) {
            return null;
        }
        return (T) getRecursiveNode(index, middleMan.next);
    }

    private T getRecursiveNode(int index, ListNode node) {
        if (index == 0) {
            return (T) node.T;
        }

        return (T) getRecursiveNode(index - 1, node.next);
    }
}
