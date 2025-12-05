import java.util.*;
import lab5.*;

// Варіант 7 
// Set
// Однозв’язний список

public class Lab6 {

    /**
     * основний метод для демонстрації роботи колекції
     * @param args аргументи командного рядка
     */
    public static void main(String[] args) {

        // створюємо кілька вагонів 
        PassengerCar car1 = new PassengerCar("PC-01", 3, 50, 120.0); // пасажирський вагон
        SleeperCar car2 = new SleeperCar("SL-01", 5, 20, 60.0, 10);  // спальний вагон
        DiningCar car3 = new DiningCar("DC-01", 2, 10, 40.0);         // вагон-ресторан

        // 1. порожній конструктор
        LinkedRailCarSet<RailCar<?>> set1 = new LinkedRailCarSet<>();
        set1.add(car1);
        set1.add(car2);

        // 2. конструктор з одним елементом
        LinkedRailCarSet<RailCar<?>> set2 = new LinkedRailCarSet<>(car3);

        // 3. конструктор з колекцією
        List<RailCar<?>> list = Arrays.asList(car1, car2, car3);
        LinkedRailCarSet<RailCar<?>> set3 = new LinkedRailCarSet<>(list);

        // виводимо всі вагони
        System.out.println("Set1:");
        printSet(set1);
        System.out.println("\nSet2:");
        printSet(set2);
        System.out.println("\nSet3:");
        printSet(set3);
    }

    /**
     * допоміжний метод для виведення вмісту колекції та її розміру
     * @param set колекція вагонів
     */
    private static void printSet(LinkedRailCarSet<RailCar<?>> set) {
        for (RailCar<?> car : set) {
            System.out.println("  " + car);
        }
        System.out.println("Total cars: " + set.size());
    }
}

/**
 * реалізація колекції Set через однозв’язний список
 * @param <E> тип елементів колекції
 */
class LinkedRailCarSet<E> implements Set<E> {

    /**
     * вузол однозв’язного списку.
     * @param <E> тип зберігаємого елемента
     */
    private static class Node<E> {
        E value;      // значення вузла
        Node<E> next; // посилання на наступний вузол

        Node(E value) { this.value = value; }
    }

    private Node<E> head; // голова списку
    private int size;     // кількість елементів

    /** порожній конструктор */
    public LinkedRailCarSet() {}

    /**
     * конструктор із одним елементом
     * @param element елемент для додавання
     */
    public LinkedRailCarSet(E element) { add(element); }

    /**
     * конструктор із колекцією елементів
     * @param collection колекція елементів для додавання
     */
    public LinkedRailCarSet(Collection<? extends E> collection) { addAll(collection); }

    /** @return кількість елементів у колекції */
    @Override
    public int size() { return size; }

    /** @return true якщо колекція порожня */
    @Override
    public boolean isEmpty() { return size == 0; }

    /**
     * перевіряє, чи містить колекція заданий елемент
     * @param o елемент для перевірки
     * @return true якщо елемент є в колекції
     */
    @Override
    public boolean contains(Object o) {
        for (E e : this) if (Objects.equals(e, o)) return true;
        return false;
    }

    /**
     * додає елемент у колекцію, якщо його ще немає
     * @param e елемент для додавання
     * @return true якщо елемент додано
     */
    @Override
    public boolean add(E e) {
        if (contains(e)) return false;
        Node<E> node = new Node<>(e);
        if (head == null) head = node;
        else {
            Node<E> cur = head;
            while (cur.next != null) cur = cur.next;
            cur.next = node;
        }
        size++;
        return true;
    }

    /**
     * видаляє елемент із колекції
     * @param o елемент для видалення
     * @return true якщо елемент було видалено
     */
    @Override
    public boolean remove(Object o) {
        Node<E> cur = head, prev = null;
        while (cur != null) {
            if (Objects.equals(cur.value, o)) {
                if (prev == null) head = cur.next;
                else prev.next = cur.next;
                size--;
                return true;
            }
            prev = cur;
            cur = cur.next;
        }
        return false;
    }

    /** очищує колекцію */
    @Override
    public void clear() { head = null; size = 0; }

    /**
     * додає усі елементи з колекції
     * @param c колекція елементів
     * @return true якщо колекція змінилася
     */
    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean changed = false;
        for (E e : c) if (add(e)) changed = true;
        return changed;
    }

    /**
     * перевіряє, чи містить колекція всі елементи заданої колекції
     * @param c колекція для перевірки
     * @return true якщо всі елементи є в колекції
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) if (!contains(o)) return false;
        return true;
    }

    /**
     * видаляє усі елементи, які містяться у заданій колекції
     * @param c колекція елементів для видалення
     * @return true якщо колекція змінилася
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        for (Object o : c) if (remove(o)) modified = true;
        return modified;
    }

    /**
     * залишає у колекції лише ті елементи, що є в заданій колекції
     * @param c колекція елементів для збереження
     * @return true якщо колекція змінилася
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        boolean modified = false;
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            E element = it.next();
            if (!c.contains(element)) {
                it.remove();
                modified = true;
            }
        }
        return modified;
    }

    /** @return масив Object[] з усіма елементами */
    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size];
        int i = 0;
        for (E e : this) arr[i++] = e;
        return arr;
    }

    /**
     * перетворює колекцію у масив заданого типу
     * @param a масив, у який копіюються елементи
     * @param <T> тип елементів масиву
     * @return масив з елементами колекції
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) a = (T[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
        int i = 0;
        for (E e : this) a[i++] = (T) e;
        if (a.length > size) a[size] = null;
        return a;
    }

    /**
     * повертає ітератор для колекції
     * @return ітератор
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private Node<E> current = head;
            private Node<E> lastReturned = null;

            @Override
            public boolean hasNext() { return current != null; }

            @Override
            public E next() {
                if (current == null) throw new NoSuchElementException();
                lastReturned = current;
                E val = current.value;
                current = current.next;
                return val;
            }

            @Override
            public void remove() {
                if (lastReturned == null) throw new IllegalStateException();
                LinkedRailCarSet.this.remove(lastReturned.value);
                lastReturned = null;
            }
        };
    }
}
