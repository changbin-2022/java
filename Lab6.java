import java.util.*;
import lab5.*;

// Варіант 7 
// Set
// Однозв’язний список

public class Lab6 {

    public static void main(String[] args) {

        // створюємо кілька вагонів (класи з lab5)
        PassengerCar car1 = new PassengerCar("PC-01", 3, 50, 120.0); // пасажирський вагон
        SleeperCar car2 = new SleeperCar("SL-01", 5, 20, 60.0, 10);  // спальний вагон
        DiningCar car3 = new DiningCar("DC-01", 2, 10, 40.0);         // вагон-ресторан

        // створюємо колекцію LinkedRailCarSet
        LinkedRailCarSet<RailCar<?>> set = new LinkedRailCarSet<>();

        // додаємо елементи у колекцію
        set.add(car1);
        set.add(car2);
        set.add(car3);

        // виводимо всі вагони з колекції
        System.out.println("Train cars in the set:");
        for (RailCar<?> car : set) {
            System.out.println("  " + car);
        }

        // виводимо загальну кількість вагонів
        System.out.println("\nTotal cars: " + set.size());
    }
}

/**
 * реалізація колекції Set через однозв’язний список
 * @param <E> тип елементів (у нас RailCar<?>)
 */
class LinkedRailCarSet<E> implements Set<E> {

    // вузол однозв’язного списку
    private static class Node<E> {
        E value;      // значення елемента
        Node<E> next; // посилання на наступний вузол
        Node(E value) { this.value = value; }
    }

    private Node<E> head; // голова списку
    private int size;     // кількість елементів у списку

    /** порожній конструктор */
    public LinkedRailCarSet() {}

    /** конструктор із одним елементом */
    public LinkedRailCarSet(E element) {
        add(element);
    }

    /** конструктор із колекцією елементів */
    public LinkedRailCarSet(Collection<? extends E> collection) {
        addAll(collection);
    }

    /** повертає кількість елементів у колекції */
    @Override
    public int size() { return size; }

    /** перевіряє, чи порожня колекція */
    @Override
    public boolean isEmpty() { return size == 0; }

    /** перевіряє наявність елемента у колекції */
    @Override
    public boolean contains(Object o) {
        for (E e : this) if (Objects.equals(e, o)) return true;
        return false;
    }

    /** повертає ітератор для проходження колекції */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private Node<E> current = head;

            @Override
            public boolean hasNext() { return current != null; }

            @Override
            public E next() {
                if (current == null) throw new NoSuchElementException();
                E val = current.value;
                current = current.next;
                return val;
            }
        };
    }

    /** додає елемент у колекцію, якщо його ще немає */
    @Override
    public boolean add(E e) {
        if (contains(e)) return false; // не додаємо дублікат
        Node<E> node = new Node<>(e);
        if (head == null) head = node; // якщо список порожній
        else {
            Node<E> cur = head;
            while (cur.next != null) cur = cur.next; // шукаємо кінець списку
            cur.next = node; // додаємо новий вузол
        }
        size++;
        return true;
    }

    /** видаляє елемент із колекції */
    @Override
    public boolean remove(Object o) {
        Node<E> cur = head, prev = null;
        while (cur != null) {
            if (Objects.equals(cur.value, o)) {
                if (prev == null) head = cur.next; // видалення голови
                else prev.next = cur.next;         // видалення середнього або останнього вузла
                size--;
                return true;
            }
            prev = cur;
            cur = cur.next;
        }
        return false;
    }

    /** очищає колекцію */
    @Override
    public void clear() { head = null; size = 0; }

    /** додає усі елементи з колекції */
    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean changed = false;
        for (E e : c) if (add(e)) changed = true;
        return changed;
    }

    /** перетворює колекцію у масив Object[] */
    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size];
        int i = 0;
        for (E e : this) arr[i++] = e;
        return arr;
    }

    /** перетворює колекцію у масив заданого типу */
    @SuppressWarnings("unchecked")
    @Override
    public <T> T[] toArray(T[] a) {
        int i = 0;
        for (E e : this) if (i < a.length) a[i++] = (T) e;
        return a;
    }

    /** перевіряє, чи містить колекція всі елементи заданої колекції */
    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) if (!contains(o)) return false;
        return true;
    }

    /** залишає у колекції лише елементи з заданої колекції */
    @Override
    public boolean retainAll(Collection<?> c) {
        boolean modified = false;
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            if (!c.contains(it.next())) {
                it.remove();
                modified = true;
            }
        }
        return modified;
    }

    /** видаляє усі елементи, які містяться у заданій колекції */
    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        for (Object o : c) if (remove(o)) modified = true;
        return modified;
    }
}
