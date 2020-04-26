public class CustomLinkedList<T> implements CustomList<T> {

    private CustomLinkedListNode<T> first = null;
    private CustomLinkedListNode<T> last = null;
    private int nodeCount = 0;

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(T object) {
        CustomLinkedListNode<T> newNode = new CustomLinkedListNode<>(object);
        nodeCount++;
        if (first == null) {
            first = last = newNode;
        } else {
            last.next = newNode;
            last = newNode;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(T object, int index) {
        if(index > nodeCount || index < 0 ){
            throw new CustomListException("Index out of range");
        }

        CustomLinkedListNode<T> newNode = new CustomLinkedListNode<>(object);

        if (index == 0){
            newNode.next = first;
            first = newNode;
        } else {
            CustomLinkedListNode<T> previosNode = findNodeByIndex(index - 1);
            newNode.next = previosNode.next;
            previosNode.next = newNode;
        }

        if(index == nodeCount){
            last = newNode;
        }

        nodeCount++;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T get(int index) {
        CustomLinkedListNode<T> node = findNodeByIndex(index);
        return node.data;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T update(T object, int index) {
        CustomLinkedListNode<T> node = findNodeByIndex(index);
        T previosData = node.data;
        node.data = object;
        return previosData;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T delete(int index) {
        if (nodeCount == 0) {
            throw new CustomListException("Empty list");
        }
        if (index == 0) {
            CustomLinkedListNode<T> node = first;
            first = first.next;
            nodeCount--;
            return node.data;
        }

        CustomLinkedListNode<T> previosNode = findNodeByIndex(index - 1);
        T previosData = previosNode.next.data;
        previosNode.next = previosNode.next.next;
        nodeCount--;
        return previosData;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return nodeCount;
    }

    private CustomLinkedListNode<T> findNodeByIndex(int index) {
        if (index < 0 || index >= nodeCount) {
            throw new CustomListException("Index out of range");
        }

        if (nodeCount == (index + 1)) {
            return last;
        }

        int currentIndex = 0;
        CustomLinkedListNode<T> iterator = first;
        while (currentIndex != index) {
            currentIndex++;
            iterator = iterator.next;
        }
        return iterator;
    }
}
