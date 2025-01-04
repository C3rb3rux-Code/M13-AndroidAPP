package com.example.m13proyecto1.extras;

import android.os.Build;

import java.time.LocalDate;

public class Extra {

    public static boolean validateCard(String cardNumber) {
        int sum = 0;
        boolean alternate = false;

        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(cardNumber.charAt(i));

            if (alternate) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }

            sum += digit;
            alternate = !alternate;
        }

        return sum % 10 == 0;
    }

    public static boolean validateCVV(String cvv) {
        int cvvi = Integer.parseInt(cvv);
        return cvvi == 3;
    }

    public static boolean validateDate(String expiredDate) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDate expired = LocalDate.parse(expiredDate);
            return LocalDate.now().isAfter(expired);
        }
        return false;
    }
}
