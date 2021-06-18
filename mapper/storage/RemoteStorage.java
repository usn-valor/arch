package Architect.mapper.storage;

import Architect.mapper.SQLClient;

import java.util.HashMap;

public class RemoteStorage implements Storage {

    private long id = 0;
    private HashMap<Long, Object> storageMap = new HashMap<>();

    @Override
    public void add(Object o) {
        SQLClient.connect();
        SQLClient.setNewNickName(id++, (String) o);
        SQLClient.disconnect();
    }

    @Override
    public Object get(long id) {
        Object obj;
        SQLClient.connect();
        System.out.println("Getting a value for id #" + id + " from RemoteStorage...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ignored) {

        }
        obj = SQLClient.getNickname(id);
        SQLClient.disconnect();
        return obj;
    }
}