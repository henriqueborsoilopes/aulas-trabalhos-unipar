package br.unipar.husistema.dto.jsonadapter;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LocalDateTimeAdapter extends XmlAdapter<String, Date> {

    private static final ThreadLocal<DateFormat> formatter = new ThreadLocal<DateFormat>() {
                @Override
                protected DateFormat initialValue() {
                    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                }
            };

    @Override
    public Date unmarshal(String v) throws Exception {
        return formatter.get().parse(v);
    }

    @Override
    public String marshal(Date v) throws Exception {
        return formatter.get().format(v);
    }
}
