package utils;

import java.util.List;

public abstract class CSVEncoder <T> implements Encodable <T>{
    
    private String encodeFieldNames(){
        String[] fieldNames = getFieldNames();
        StringBuilder sb = new StringBuilder();

        sb.append(fieldNames[0]);
        for (int k = 1; k < fieldNames.length; k+=1){
            sb.append(",");
            sb.append(fieldNames[k]);
        }

        return sb.toString();
    }

    @Override
    public String encodeRecord(T type) {
        String[] values = getValues(type);
        StringBuilder sb = new StringBuilder();

        sb.append(values[0]);
        for (int k = 1; k < values.length; k +=1){
            sb.append("," + values[k]);
        }
        
        return sb.toString();     
    }

    @Override
    public String encode(List<T> list){
        StringBuilder sb = new StringBuilder();

        sb.append(encodeFieldNames() + "\n");
        for (T record : list) {
            sb.append(encodeRecord(record) + "\n");
        }

        return sb.toString();        
    }     
}
