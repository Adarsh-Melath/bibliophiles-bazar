package com.adarsh.backend.feature.category.domain;

import java.text.Normalizer;
import java.util.regex.Pattern;

public final class SlugGenerator {

    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("\\s");

    private SlugGenerator() {
    }

    public static String slugGenerator(String type) {
        String noWhitespace = WHITESPACE.matcher(type).replaceAll("-");
        String normalized = Normalizer.normalize(noWhitespace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase();
    }
}