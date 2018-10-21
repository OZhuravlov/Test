package com.study.array;

import java.util.Arrays;
import java.util.Random;

public class ArrayTask {

    public static void main(String[] args) {

        System.out.println("1.принимает массив чаров, возвращает строку состоящую из символов массива");
        char[] charArray = {'a', 'b', 'c', 'd', 'e'};
        System.out.println(Arrays.toString(charArray));
        String stringResult = getStringFromChars(charArray);
        System.out.println("Result: " + stringResult + "\n" + "-------------------------");

        int value;
        int[] intArray;
        int arrIndex;

        System.out.println("2.принимает массив интов, и значение типа инт, возвращает индекс массива в котором значение совпадает с передаваемым, начиная с начала массива. Если значения в массиве нет возвращает -1");
        intArray = generateIntArray(10);
        value = new Random().nextInt(intArray.length);
        System.out.println(Arrays.toString(intArray));
        System.out.println("value to find: " + value);
        arrIndex = getArrayIndexByValueFirst(intArray, value);
        System.out.println("Result: " + arrIndex + "\n" + "-------------------------");

        System.out.println("3.принимает массив интов, и значение типа инт, возвращает индекс массива в котором значение совпадает с передаваемым, начиная с конца массива. Если значения в массиве нет возвращает -1");
        intArray = generateIntArray(10);
        value = new Random().nextInt(intArray.length);
        System.out.println(Arrays.toString(intArray));
        System.out.println("value to find: " + value);
        arrIndex = getArrayIndexByValueLast(intArray, value);
        System.out.println("Result: " + arrIndex + "\n" + "-------------------------");

        System.out.println("5.приминает массив интов и число, выводит на екран только елементы массива которые кратны этому числу");
        intArray = generateIntArray(400);
        value = new Random().nextInt(intArray.length/20) + 20;
        System.out.println(Arrays.toString(intArray));
        System.out.println("value to find: " + value);
        System.out.print("Result: ");
        printMultipleArrayElements(intArray, value);
        System.out.println("-------------------------");

        System.out.println("6.метод принимает массив интов сортирует его по возрастанию");
        intArray = generateIntArray(20);
        System.out.println(Arrays.toString(intArray));
        System.out.println("Result: ");
        sortArray(intArray);
        System.out.println(Arrays.toString(intArray) + "\n" + "-------------------------");

        System.out.println("7.принимает массив байт, если в массиве есть повторяющиеся елементы, возвращает тру");
        byte[] byteArray = generateByteArray(20);
        System.out.println(Arrays.toString(byteArray));
        System.out.println("Result: " + checkDuplicates(byteArray));
        System.out.println("-------------------------");

        System.out.println("8. Принимает массив строк и просто строку, возвращает массив строк которые содержат данную подстроку. т.е. отфильтрованый из первого");
        String[] stringArray = {
                "network administrator",
                "web developer",
                "systems analyst",
                "database administrator",
                "network architect",
                "information security analyst",
                "project manager",
                "system administrator"
        };
        System.out.println(Arrays.toString(stringArray));
        String searchString = "admin";
        System.out.println("1. searchString: " + searchString);
        String[] filteredStrings = getFilteredStrings(stringArray, searchString);
        System.out.println("1. Result: " + Arrays.toString(filteredStrings));
        searchString = "dev";
        System.out.println("2. searchString: " + searchString);
        filteredStrings = getFilteredStrings(stringArray, searchString);
        System.out.println("2. Result: " + Arrays.toString(filteredStrings) + "\n" + "-------------------------");

// 9.принимает 2м массив строк, выводит его на экран
// 10.принимает 2м массив интов, возвращает 2мерный массив чаров, каждый символ в позиции массива соответствует коду символа передаваемого инта
// 11.принимает 2м массив интов, ивертирует массив интов (каждое значение в передаваемом массиве меняет знак)
// 12.принимает 2 массива чаров, проверяет есть ли в 1 массиве, такая же последовательность символов которую представляет собой второй массив. Возвращает булеан
    }

    private static String getStringFromChars(char[] charArray) {
        StringBuilder sb = new StringBuilder();
        for (char el: charArray) {
            sb.append(el);
        }
        return sb.toString();
    }

    private static int getArrayIndexByValueFirst(int[] intArray, int value) {
        for (int i = 0; i < intArray.length; i++) {
            if (intArray[i] == value) {
                return i;
            }
        }
        return -1;
    }

    private static int getArrayIndexByValueLast(int[] intArray, int value) {
        for (int i = intArray.length - 1; i >= 0; i--) {
            if (intArray[i] == value) {
                return i;
            }
        }
        return -1;
    }

    private static void printMultipleArrayElements(int[] intArray, int value) {
        for (int i = 0; i < intArray.length; i++) {
            if (intArray[i]%value == 0) {
                System.out.print(intArray[i] + ",");
            }
        }
        System.out.println();
    }


    private static void sortArray(int[] intArray) {
        int swipeCount;
        int lastIndex = intArray.length-1;
        do {
            swipeCount = 0;
            for (int i = 0; i < lastIndex; i++) {
                if (intArray[i]>intArray[i+1]) {
                    swipe(intArray, i, i+1);
                    swipeCount++;
                }
            }
            if (swipeCount == 0) {
                break;
            }else{
                lastIndex--;
            }
        } while (swipeCount > 0);
    }

    private static void swipe(int[] intArray, int i, int j) {
        int exch = intArray[i];
        intArray[i] = intArray[j];
        intArray[j] = exch;
    }

    private static boolean checkDuplicates(byte[] byteArray) {
        for (int i = 0; i < byteArray.length; i++) {
            for (int j = 0; j < byteArray.length; j++) {
                if (i != j && byteArray[i] == byteArray[j]) {
                    System.out.println("Array[" + i + "] and Array[" + j + "] both are " + byteArray[i]);
                    return true;
                }
            }
        }
        return false;
    }

    private static String[] getFilteredStrings(String[] stringArray, String searchString) {

        String[] filteredStrings = new String[stringArray.length];
        int i = 0;
        for (String string: stringArray
             ) {
            if (string.contains(searchString)){
               filteredStrings[i++] = string;
            }
        }
        return Arrays.copyOf(filteredStrings, i);
    }

    static int[] generateIntArray(int n){
        int [] array = new int[n];
        Random random = new Random();
        for(int i = 0; i < n; i++){
            array[i] = random.nextInt(Math.max(2, array.length-2));
        }

        return array;
    }

    static byte[] generateByteArray (int n){
        byte [] array = new byte[n];
        new Random().nextBytes(array);
        return array;
    }
}