package utils;

import java.util.List;

public abstract class CSVEncoder<T> implements Encodable<T> {

    private String encodeFieldNames() { // builds CSV header
        String[] fieldNames = getFieldNames();
        StringBuilder sb = new StringBuilder();

        sb.append(fieldNames[0]); // first field without comma
        for (int k = 1; k < fieldNames.length; k += 1) {
            sb.append(","); // separator
            sb.append(fieldNames[k]);
        }

        return sb.toString();
    }

    @Override
    public String encodeRecord(T type) { // encodes a single record
        String[] values = getValues(type);
        StringBuilder sb = new StringBuilder();

        sb.append(values[0]);
        for (int k = 1; k < values.length; k += 1) {
            sb.append(",").append(values[k]);
        }

        return sb.toString();
    }

    @Override
    public String encode(List<T> list) { // encodes full list as CSV
        StringBuilder sb = new StringBuilder();

        sb.append(encodeFieldNames()).append("\n");
        for (T record : list) {
            sb.append(encodeRecord(record)).append("\n");
        }

        return sb.toString();
    }
}
