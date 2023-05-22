package org.example.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateStudentInfo {
    public static final Pattern VALID_EMAIL_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_PHONE_REGEX = Pattern.compile("^\\d{10}$");
    public static boolean validateEmail(String email) {
        Matcher matcher = VALID_EMAIL_REGEX.matcher(email);
        return matcher.matches();
    }
    public static boolean validatePhone(String phone) {
        Matcher matcher = VALID_PHONE_REGEX.matcher(phone);
        return matcher.matches();
    }
}
