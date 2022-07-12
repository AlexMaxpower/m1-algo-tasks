package practicum;

import java.util.*;
import java.util.stream.Collectors;

public class Algorithms {

    /**
     * Отсортируйте список, НЕ используя методы стандартной библиотеки (напр. Collections.sort).
     */
    public static List<Integer> sort(List<Integer> list) {
        List<Integer> sortedList = new ArrayList<>(list);
        int indexJ;
        for (int i = 0; i < list.size(); i++) {
            int min = sortedList.get(i);
            indexJ = i;
            for (int j = i; j < list.size(); j++) {
                if (sortedList.get(j) < min) {
                    min = sortedList.get(j);
                    indexJ = j;
                }
            }
            sortedList.set(indexJ, sortedList.get(i));
            sortedList.set(i, min);
        }
        return sortedList;
    }

    /**
     * Удалите дубликаты из списка.
     * <p>
     * Усложнение: не используйте дополнительные структуры данных
     * для хранения промежуточных значений.
     * (списки, массивы, хэш-таблицы, множества и т.п.).
     * К списку-результату это не относится.
     */
    public static List<Integer> removeDuplicates(List<Integer> list) {
        List<Integer> setList = new ArrayList<>(list);
        for (int i = 0; i < setList.size(); i++) {
            int number = setList.get(i);
            for (int j = i + 1; j < setList.size(); j++) {
                if (setList.get(j) == number) {
                    setList.remove(j);
                    j--;
                }
            }
        }
        return setList;
    }

    /**
     * Проверьте, является ли список "палиндромом".
     * Палиндром -- это список, который в обе стороны читается одинаково.
     * Например:
     * палиндромы: [1 2 1], [3 2 1 2 3], [2 2 2], []
     * не палиндромы: [1 2 3], [2 2 3], [3 2 1 3 2]
     * <p>
     * Доп. условие: у алгоритма должна быть линейная сложность, O(n)
     */
    public static boolean isPalindrome(List<Integer> list) {
        boolean palindrome = true;
        List<Integer> tempList = new ArrayList<>(list);
        for (int i = 0; i < tempList.size() / 2; i++) {
            if (tempList.get(i) != tempList.get(tempList.size() - i - 1)) {
                palindrome = false;
                break;
            }
        }
        return palindrome;
    }

    /**
     * Объедините два отсортированных списка в один отсортированный список.
     * Например:
     * [1 3 5] + [2 4 6] = [1 2 3 4 5 6]
     * [1 2 3] + [1 3 5] = [1 1 2 3 3 5]
     * [] + [1] = [1]
     * [7] + [1 4] = [1 4 7]
     * <p>
     * Доп. условие: у алгоритма должна быть линейная сложность, O(n).
     */
    public static List<Integer> mergeSortedLists(List<Integer> a, List<Integer> b) {
        List<Integer> c = new ArrayList<>();
        int k = 0;
        int l = 0;
        while ((k < a.size()) && (l < b.size())) {
            if ((a.get(k) <= b.get(l))) {
                c.add(a.get(k));
                k++;
            } else {
                c.add(b.get(l));
                l++;
            }
        }
        while (k < a.size()) {
            c.add(a.get(k));
            k++;
        }
        while (l < b.size()) {
            c.add(b.get(l));
            l++;
        }
        return c;
    }

    /**
     * Проверьте, что в массиве нет дубликатов.
     * Верните true, если дубликатов нет, иначе false.
     * <p>
     * Усложнение: не используйте дополнительные структуры данных
     * (списки, массивы, хэш-таблицы, множества и т.п.).
     */
    public static boolean containsEveryElementOnce(int[] array) {
        boolean isNotContains = true;
        for (int i = 0; i < array.length; i++) {
            int number = array[i];
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] == number) {
                    isNotContains = false;
                    break;
                }
            }
        }
        return isNotContains;
    }

    /**
     * Определите, является ли один массив перестановкой другого.
     * Т.е. в массивах хранится один и тот же набор элементов, но (возможно) в разном порядке.
     * <p>
     * Для решения нжуно использовать одну хэш-таблицу.
     * <p>
     * Например:
     * [1 2 3] и [3 2 1] = true
     * [1 1 2] и [1 2 1] = true
     * [1 2 3] и [1 2 3] = true
     * [] и [] = true
     * <p>
     * [1 2] и [1 1 2] = false, разный набор элементов
     */
    public static boolean isPermutation(int[] a, int[] b) {
        boolean permutation = true;
        if (a.length != b.length) {
            permutation = false;
        } else {
            List<Integer> copyList = Arrays.stream(b).boxed().collect(Collectors.toList());
            for (int i = 0; i < a.length; i++) {
                if (copyList.contains(a[i])) {
                    copyList.remove((Integer) a[i]);
                } else {
                    permutation = false;
                    break;
                }
            }
        }
        return permutation;
    }

    /**
     * Сложная задача.
     * <p>
     * В памяти компьютера изображения (часто) хранятся в виде двумерного массива.
     * Напишите метод, который повернёт "изображение" на 90 градусов вправо.
     * "Изображение" в данном примере -- двумерный массив целых чисел.
     * <p>
     * Например:
     * на входе:
     * [ [1 2]
     * [3 4]
     * [5 6] ]
     * <p>
     * на выходе:
     * [ [5 3 1]
     * [6 4 2] ]
     */
    public static int[][] rotateRight(int[][] image) {
        int[][] rotateImage = new int[image[0].length][image.length];
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                rotateImage[j][image.length - i - 1] = image[i][j];
            }
        }
        return rotateImage;
    }
}
