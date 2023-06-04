package org.example.utils;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {

    private final String datePattern = "dd/MM/yyyy";
    private final SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

    @Override
    public Object stringToValue(String text) throws ParseException {
        return dateFormatter.parseObject(text);
    }

    @Override
    public String valueToString(Object value) throws ParseException {

        if (value != null) {
            Calendar cal;
            if(value.getClass().toString().contains("Date")){
                cal = Calendar.getInstance();
                cal.setTime((Date)value);
            }
            else
                cal = (Calendar) value;
            return dateFormatter.format(cal.getTime());
        }

        return "";
    }

}
