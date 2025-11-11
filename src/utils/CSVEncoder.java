package utils;

import java.util.List;

public abstract class CSVEncoder <T> implements Encodable <T>{
    public String encodeRecord(T type){
        String[] values = getValues(type);
        String output = values[0];
        for(int k = 1; k < values.length; k++){
            output += ","+values[k];
        }
        
        return output;
    }
    @Override
    public String getAll(List<T> list){
        String output = "";
        for (T record : list) {
            output += encodeRecord(record) + "\n";
        }
        return output;
    }
}
