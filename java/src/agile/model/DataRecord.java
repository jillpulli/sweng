package agile.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataRecord<K,V> {

    private K[] headers;
    private List<Map<K,V>> records = new ArrayList<>();

    public DataRecord() {
        headers = null;
    }

    public DataRecord(K[] headers) {
        this.headers = headers;
    }

    public V getDataValue(int row, K columnKey) {
        return records.get(row).get(columnKey);
    }

    public K[] getHeaders() {
        return headers;
    }

    public int getNumberOfHeaders() {
        return headers.length;
    }

    public Map<K,V> getRecord(int row) {
        return records.get(row);
    }

    public DataRecord<K,V> setHeaders(K[] headers) {
        this.headers = headers;
        return this;
    }

    public int size() {
        return records.size();
    }

    public void addRecord(V[] data) {
        addRecord(records.size(), data);
    }

    public void addRecord(int row, V[] data) {
        Map<K,V> record = new HashMap<>();
        for (int i = 0, len = headers.length; i < len; i++)
            record.put(headers[i], data[i]);
        records.add(row, record);
    }
}
