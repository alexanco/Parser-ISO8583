package com.alexanco.api.iso8583;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.alexanco.api.Constants;


@Service
public class FieldParseManager {
    public String getField62Value(int field, String data) {
        int size = 41;
        int index = 1;

        String aux = data.substring(9);

        HashMap<Integer, String> ret = new HashMap<Integer, String>();

        for (int start = 0; start < aux.length(); start += size) {

            String value = aux.substring(start, Math.min(aux.length(), start + size));

            value = value.substring(6);

            value = value.substring(1, 33);

            ret.put(index, value);

            index++;
        }

        return ret.get(field);

    }

    public String changePanField(String pan, String field) {

        return pan + field.substring(16);

    }

    public String changeKsnField(String data, String ksn) {

        String result = "";

        int offset = 0;

        for (int start = 0; start < data.length(); start += offset) {

            String id = data.substring(start, start + 3);

            String lengthMessage = data.substring(start + 3, start + 6);

            String value = "";
            offset = Integer.parseInt(lengthMessage) + 6;

            if (Integer.parseInt(id) == Constants.PINBLOCK_FIELD_VALUE) {
                value = ksn;

            } else {
                value = data.substring(start + 6, start + offset);
            }

            result += id + lengthMessage + value;

        }

        return result;

    }

}
