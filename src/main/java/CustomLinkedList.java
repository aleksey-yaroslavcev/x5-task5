public class CustomLinkedList<T> implements CustomList<T> {

    private class Node<T> {
        public T data;
        public Node<T> next = null;

        public Node(T sourceData) {
            data = sourceData;
        }
    }

    private Node<T> first = null;
    private Node<T> last = null;
    private int nodeCount = 0;

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(T object) {
        Node<T> newNode = new Node<>(object);
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

        Node<T> newNode = new Node<>(object);

        if (index == 0){
            newNode.next = first;
            first = newNode;
        } else {
            Node<T> previosNode = findNodeByIndex(index - 1);
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
        checkValidIndex(index);
        return findNodeByIndex(index).data;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T update(T object, int index) {
        checkValidIndex(index);
        Node<T> node = findNodeByIndex(index);
        T previousData = node.data;
        node.data = object;
        return previousData;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T delete(int index) {
        checkValidIndex(index);

        Node<T> previousNode = index == 0 ? null : findNodeByIndex(index - 1);
        Node<T> nodeToDelete = previousNode == null ? first : previousNode.next;
        T dataToDelete = nodeToDelete.data;

        if(nodeCount == 1){
            first = last = null;
        } else {
            if(index == nodeCount -1){
                last = previousNode;
            } else if (index == 0) {
                first = first.next;
            } else {
                previousNode.next = nodeToDelete.next;
            }
        }

        nodeCount--;
        return dataToDelete;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return nodeCount;
    }

    private Node<T> findNodeByIndex(int index) {
        if (nodeCount == (index + 1)) {
            return last;
        }

        int currentIndex = 0;
        Node<T> iterator = first;
        while (currentIndex != index) {
            currentIndex++;
            iterator = iterator.next;
        }
        return iterator;
    }

    private void checkValidIndex(int index){
        if (index < 0 || index >= nodeCount) {
            throw new CustomListException("Index out of range");
        }
    }
}
