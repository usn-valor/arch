package Architect.mapper.storage;

public interface Storage {
    void add(Object storedObject);

    Object get(long id);
}
