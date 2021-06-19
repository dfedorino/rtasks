package com.dfedorino.rtasks.third_level;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class DrunkardGame {
    /**
     * В игре в пьяницу карточная колода раздается поровну двум игрокам. Далее они вскрывают по одной верхней карте,
     * и тот, чья карта старше, забирает себе обе вскрытые карты, которые кладутся под низ его колоды. Тот, кто
     * остается без карт – проигрывает.
     *
     * Для простоты будем считать, что все карты различны по номиналу, а также, что самая младшая карта побеждает
     * самую старшую карту ("шестерка берет туза").
     *
     * Игрок, который забирает себе карты, сначала кладет под низ своей колоды карту первого игрока, затем карту
     * второго игрока (то есть карта второго игрока оказывается внизу колоды).
     *
     * Напишите программу, которая моделирует игру в пьяницу и определяет, кто выигрывает. В игре участвует 10 карт,
     * имеющих значения от 0 до 9, большая карта побеждает меньшую, карта со значением 0 побеждает карту 9.
     *
     * Входные данные
     * Программа получает на вход две строки: первая строка содержит 5 чисел, разделенных пробелами — номера карт
     * первого игрока, вторая – аналогично 5 карт второго игрока. Карты перечислены сверху вниз, то есть каждая строка
     * начинается с той карты, которая будет открыта первой.
     *
     * Выходные данные
     * Программа должна определить, кто выигрывает при данной раздаче, и вывести слово first или second, после чего
     * вывести количество ходов, сделанных до выигрыша. Если на протяжении 10^6 ходов игра не заканчивается, программа
     * должна вывести слово botva.
     * @param f - массив чисел, представляющих карты первого игрока
     * @param s - массив чисел, представляющих карты второго игрока
     * @return строка с результатом
     */
    public String play(int[] f, int[] s) {
        Queue<Integer> first = Arrays.stream(f).collect(ArrayDeque::new, ArrayDeque::offer, ArrayDeque::addAll);
        Queue<Integer> second = Arrays.stream(s).collect(ArrayDeque::new, ArrayDeque::offer, ArrayDeque::addAll);
        Integer firstPlayerCard = first.poll();
        Integer secondPlayerCard = second.poll();
        int count = 0;
        while (firstPlayerCard != null && secondPlayerCard != null) {
            if (count == 1_000_000) {
                return "botva";
            }
            boolean firstWins;
            if (firstPlayerCard == 9 & secondPlayerCard == 0) {
                firstWins = false;
            } else if (firstPlayerCard == 0 & secondPlayerCard == 9) {
                firstWins = true;
            } else {
                firstWins = firstPlayerCard > secondPlayerCard;
            }
            if (firstWins) {
                first.offer(firstPlayerCard);
                first.offer(secondPlayerCard);
            } else {
                second.offer(firstPlayerCard);
                second.offer(secondPlayerCard);
            }
            count++;

            firstPlayerCard = first.poll();
            secondPlayerCard = second.poll();
        }
        String winner = first.size() != 0 ? "first" : "second";
        return winner + " " + count;
    }
}
