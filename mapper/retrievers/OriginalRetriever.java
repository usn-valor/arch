package Architect.mapper.retrievers;

import Architect.mapper.storage.Storage;

public class OriginalRetriever implements Retriever {

    Storage storage;

    public OriginalRetriever(Storage storage) {
        this.storage = storage;
    }

    @Override
    public Object retrieve(long id) {
        return storage.get(id);
    }
}