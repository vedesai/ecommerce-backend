// AI Generated Code by Deloitte + Cursor (BEGIN)
package com.crestandthread.ecommerce.util;

import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

public final class SlugUtil {

    private static final Pattern NON_LATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");
    private static final Pattern EDGES_DASHES = Pattern.compile("(^-|-$)");

    private SlugUtil() {
    }

    public static String generateSlug(String text) {
        if (text == null || text.isBlank()) {
            return "";
        }

        String noWhitespace = WHITESPACE.matcher(text).replaceAll("-");
        String normalized = Normalizer.normalize(noWhitespace, Normalizer.Form.NFD);
        String slug = NON_LATIN.matcher(normalized).replaceAll("");
        slug = EDGES_DASHES.matcher(slug).replaceAll("");
        
        return slug.toLowerCase(Locale.ENGLISH);
    }
}
// AI Generated Code by Deloitte + Cursor (END)