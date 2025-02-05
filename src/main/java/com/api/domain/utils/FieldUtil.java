package com.api.domain.utils;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

@UtilityClass
public class FieldUtil {

    public static String cleanDocumentNumber(@NotNull String documentNumber) {
        return documentNumber.replaceAll("[^\\d]", "");
    }

    public static String formatAgencyNumber(@NotNull Integer agencyNumber) {
        return "000" + agencyNumber;
    }
}
