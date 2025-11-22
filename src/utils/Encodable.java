package utils;

import java.util.List;

public interface Encodable <T> {
    public abstract String encodeRecord(T type);
    public abstract String encode(List<T> type);
    public abstract String[] getFieldNames();
    public abstract String[] getValues(T type);
}
