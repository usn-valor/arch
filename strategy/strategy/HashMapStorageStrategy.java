package Architect.strategy.strategy;

import java.util.HashMap;
import java.util.Map;

public class HashMapStorageStrategy implements StorageStrategy {

    private HashMap<Long, String> data = new HashMap<>();

    @Override
    public boolean containsKey(Long key) {
        return data.containsKey(key);
    }

    @Override
    public boolean containsValue(String value) {
        return data.containsValue(value);
    }

    @Override
    public void put(Long key, String value) {
        data.put(key, value);
    }

    @Override
    public Long getKey(String value) {
        Long l = null;
        for (Map.Entry<Long, String> pair: data.entrySet()) {
            if (pair.getValue().equals(value))
                l = pair.getKey();
        }
        return l;
    }

    @Override
    public String getValue(Long key) {
        return data.get(key);
    }
}