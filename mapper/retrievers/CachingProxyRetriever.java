package Architect.mapper.retrievers;

import Architect.mapper.cache.LRUCache;
import Architect.mapper.storage.Storage;

public class CachingProxyRetriever implements Retriever {

    private OriginalRetriever retriever;
    private LRUCache cache;

    public CachingProxyRetriever(Storage storage) {
        this.retriever = new OriginalRetriever(storage);
        this.cache = new LRUCache(5);
    }

    @Override
    public Object retrieve(long id) {
        Object obj;
        if ((obj = cache.find(id)) != null)
            return obj;
        else {
            obj = retriever.retrieve(id);
            cache.set(id, obj);
        }
        return obj;
    }
}