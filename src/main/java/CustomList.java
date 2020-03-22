public interface CustomList<T> {
    /**
     * Добавить новый элемент в контейнер (в конец)
     * @param object новый элемент
     */
    void add(T object);

    /**
     * Добавить новый элемент в контейнер (в произвольное место)
     * @param object новый элемент
     * @param index место вставки элемента
     */
    void add(T object, int index);

    /**
     * Получить элемент по индексу
     * @param index индекс элемента
     * @return искомый элемент
     */
    T get(int index);

    /**
     * Обновить элемент по индексу
     * @param object новый элемент
     * @param index индекс обновляемого элемента
     * @return элемент который был в контейнере по данному индексу
     */
    T update(T object, int index);

    /**
     * Удалить элемент по индексу
     * @param index индекс удаляемого элемента
     * @return элемент, который был удалён
     */
    T delete(int index);

    /**
     * Вернуть количество элементов в контейнере
     * @return количество элементов
     */
    int size();
}
