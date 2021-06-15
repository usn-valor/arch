package Architect.strategy;

import Architect.strategy.strategy.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        testStrategy(new HashMapStorageStrategy(), 10000);
        testStrategy(new HashBiMapStorageStrategy(), 10000);
    }

    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        Set<Long> set = new HashSet<>();

        for (String x: strings) {
            set.add(shortener.getId(x));
        }
        return set;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        Set<String> set = new HashSet<>();

        for (Long x: keys) {
            set.add(shortener.getString(x));
        }
        return set;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
        Helper.printMessage(strategy.getClass().getSimpleName());

        Set<String> set = new HashSet<>();
        for (int i = 0; i < elementsNumber; i++) {
            set.add(Helper.generateRandomString());
        }

        Shortener shortener = new Shortener(strategy);

        Date beforeForGetIds = new Date();
        Set<Long> setIds = getIds(shortener, set);
        Date afterForGetIds = new Date();
        Helper.printMessage((afterForGetIds.getTime() - beforeForGetIds.getTime()) + " ms");

        Date beforeForGetStrings = new Date();
        Set<String> setStrings = getStrings(shortener, setIds);
        Date afterForGetStrings = new Date();
        Helper.printMessage((afterForGetStrings.getTime() - beforeForGetStrings.getTime()) + " ms");

        if (set.equals(setStrings)) {
            Helper.printMessage("Тест пройден.");
        }
        else {
            Helper.printMessage("Тест не пройден.");
        }
    }
}