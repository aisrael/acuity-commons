/**
 * Copyright (c) 2008-2009 Acuity Technologies, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Created Jan 1, 2008
 */
package com.acuityph.commons.util;

import java.beans.Introspector;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * StringUtility provides commonly used functions dealing with Strings.
 *
 * @author Alistair A. Israel
 * @since 0.1
 */
public final class StringUtility {

    /**
     * Utility classes should not have a public or default constructor.
     */
    private StringUtility() {
        // noop
    }

    private static final Pattern CONSECUTIVE_SPACES_PATTERN = Pattern.compile(" +");

    /**
     * Replace consecutive spaces in the given tring with a single space. For
     * example, <code>normalizeSpaces("apple  banana   orange")</code> =>
     * <code>"apple banana orange"</code>.
     *
     * @param s
     *        the input string
     * @return the input string with consecutive whitespace condensed
     */
    public static String squeezeSpaces(final String s) {
        return CONSECUTIVE_SPACES_PATTERN.matcher(s).replaceAll(" ");
    }

    /**
     *
     */
    private static final int BYTE_SIZE = 256;

    /**
     *
     */
    private static final int HEX = 16;

    private static final String[] BYTE_TO_HEX = new String[BYTE_SIZE];
    static {
        for (int i = 0; i < BYTE_SIZE; ++i) {
            final String hex = Integer.toHexString(i);
            if (i < HEX) {
                BYTE_TO_HEX[i] = "0" + hex;
            } else {
                BYTE_TO_HEX[i] = hex;
            }
        }
    }

    /**
     *
     */
    private static final int BYTE_MASK = 0xff;

    /**
     * Convert a byte array to its hexadecimal string representation.
     *
     * @param bytes
     *        The byte array to convert
     * @return The hexadecimal string representation of the given bytes
     */
    public static String encodeHex(final byte[] bytes) {
        final int len = bytes.length;
        final StringBuffer sb = new StringBuffer(len * 2);
        for (int i = 0; i < len; ++i) {
            sb.append(BYTE_TO_HEX[bytes[i] & BYTE_MASK]);
        }
        return sb.toString();
    }

    /**
     * Convert a string containing the hexadecimal digits to an array of bytes.
     *
     * @param string
     *        The hexadecimal string to convert
     * @return The converted byte array
     */
    public static byte[] decodeHex(final String string) {
        final int length = string.length();
        if (length % 2 != 0) {
            throw new IllegalArgumentException(
                    "String does not contain an even number of characters!");
        }

        final byte[] bytes = new byte[length / 2];
        for (int i = 0; i < length;) {
            final String hex = string.substring(i, i + 2);
            bytes[i / 2] = (byte) Integer.parseInt(hex, HEX);
            i += 2;
        }

        return bytes;
    }

    /**
     *
     */
    private static final Pattern ESCAPE_QUOTES_PATTERN = Pattern.compile("(\\\\|\")");

    /**
     * @param s
     *        The string to escape
     * @return The string with escaped quotes
     */
    public static String escapeQuotes(final String s) {
        final Matcher m = ESCAPE_QUOTES_PATTERN.matcher(s);
        final StringBuffer sb = new StringBuffer(s.length());
        while (m.find()) {
            if ("\\".equals(m.group())) {
                m.appendReplacement(sb, "\\\\\\\\");
            } else {
                m.appendReplacement(sb, "\\\\\"");
            }
        }
        m.appendTail(sb);
        return sb.toString();
    }

    /**
     *
     */
    private static final Pattern UNESCAPE_QUOTES_PATTERN = Pattern.compile("(\\\\\\\\|\\\\\")");

    /**
     *
     */
    private static final String DOUBLE_BACK_SLASH = "\\\\";

    /**
     * @param s
     *        The string to unescape
     * @return The unescaped string.
     */
    public static String unescapeQuotes(final String s) {
        final Matcher m = UNESCAPE_QUOTES_PATTERN.matcher(s);
        final StringBuffer sb = new StringBuffer(s.length());
        while (m.find()) {
            if (DOUBLE_BACK_SLASH.equals(m.group())) {
                m.appendReplacement(sb, DOUBLE_BACK_SLASH);
            } else {
                m.appendReplacement(sb, "\"");
            }
        }
        m.appendTail(sb);
        return sb.toString();
    }

    /**
     * Merely a delegate to {@link Introspector#decapitalize(String)} (retained
     * for backwards compatibility).
     *
     * @param s
     *        the string to decapitalize
     * @return the decapitalized string
     * @see Introspector#decapitalize(String)
     */
    public static String decapitalize(final String s) {
        return Introspector.decapitalize(s);
    }

    /**
     * Splits a camel case string into individual words.
     *
     * @param s
     *        the camel case string
     * @return the individual words as a string array
     */
    public static String[] splitCamelCase(final String s) {
        final ArrayList<String> words = new ArrayList<String>();
        final int length = s.length();
        int i = 0;
        final StringBuilder sb = new StringBuilder();
        while (i < length) {
            while (i < length && Character.isUpperCase(s.charAt(i))) {
                sb.append(s.charAt(i));
                ++i;
            }
            while (i < length && !Character.isUpperCase(s.charAt(i))) {
                sb.append(s.charAt(i));
                ++i;
            }
            final String word = sb.toString();
            words.add(word);
            sb.setLength(0);
        }
        return words.toArray(new String[words.size()]);
    }

    /**
     * Converts the given string to 'underscore' style. First, the string is
     * split into individual camel case words. Each word is then converted to
     * all lower case, then all the words are joined together by underscores.
     * Converts "camelCase" to "camel_case".
     *
     * @param s
     *        the camel case string
     * @return the underscored string
     */
    public static String underscore(final String s) {
        return underscore(s, Locale.getDefault());
    }

    /**
     * Converts the given string to 'underscore' style. First, the string is
     * split into individual camel case words. Each word is then converted to
     * all lower case, then all the words are joined together by underscores.
     * Converts "camelCase" to "camel_case".
     *
     * @param s
     *        the camel case string
     * @param locale
     *        the Locale to use when converting to lowercase
     * @return the underscored string
     */
    public static String underscore(final String s, final Locale locale) {
        final String[] words = splitCamelCase(s);
        for (int i = 0; i < words.length; ++i) {
            words[i] = words[i].toLowerCase(locale);
        }
        return join(words, '_');
    }

    /**
     * Join a variable argument list using commas.
     *
     * @param args
     *            the objects
     * @return The joined String
     */
    public static String join(final Object... args) {
        return join(",", args);
    }

    /**
     * Join a variable argument list of objects with the given delimiter
     * character.
     *
     * @param args
     *        the objects
     * @param c
     *        the delimiter character
     * @return The joined String
     */
    public static String join(final char c, final Object... args) {
        return join(String.valueOf(c), args);
    }

    /**
     * Join a variable argument list of objects with the given delimiter String.
     *
     * @param args
     *        the objects
     * @param delimiter
     *        the delimiter String
     * @return The joined String
     */
    public static String join(final String delimiter, final Object... args) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < args.length; ++i) {
            sb.append(args[i]);
            if (i + 1 < args.length) {
                sb.append(delimiter);
            }
        }
        return sb.toString();
    }

    /**
     * Join an array of objects with the given delimiter character.
     *
     * @param <T>
     *        a type
     * @param array
     *        the String array
     * @param c
     *        the delimiter character
     * @return The joined String
     */
    public static <T> String join(final T[] array, final char c) {
        return join(array, String.valueOf(c));
    }

    /**
     * Join an array of objects with the given delimiter String.
     *
     * @param <T>
     *        a type
     * @param array
     *        the String array
     * @param delimiter
     *        the delimiter String
     * @return The joined String
     */
    public static <T> String join(final T[] array, final String delimiter) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; ++i) {
            sb.append(array[i]);
            if (i + 1 < array.length) {
                sb.append(delimiter);
            }
        }
        return sb.toString();
    }

    /**
     * Joins a {@link Collection} of arbitrary objects with the given delimiter
     * character.
     *
     * @param <T>
     *        a type
     * @param collection
     *        the collection to join
     * @param c
     *        the delimiter character
     * @return The joined String
     */
    public static <T> String join(final Collection<T> collection, final char c) {
        return join(collection, String.valueOf(c));
    }

    /**
     * Joins a {@link Collection} of arbitrary objects with the given delimiter
     * String.
     *
     * @param <T>
     *        a type
     * @param collection
     *        the collection to join
     * @param delimiter
     *        the delimiter string
     * @return The joined String
     */
    public static <T> String join(final Collection<T> collection, final String delimiter) {
        final StringBuilder sb = new StringBuilder();
        for (final Iterator<T> i = collection.iterator(); i.hasNext();) {
            sb.append(i.next());
            if (i.hasNext()) {
                sb.append(delimiter);
            }
        }
        return sb.toString();
    }

    private static final Pattern SPECIAL_CHARACTERS_PATTERN =
            Pattern.compile("(\n|\t|\b|\f|\r|\"|\'|\\\\)");

    private static final Map<String, String> ESCAPE_MAP = new Hashtable<String, String>();
    static {
        ESCAPE_MAP.put("\n", "\\\\n");
        ESCAPE_MAP.put("\t", "\\\\t");
        ESCAPE_MAP.put("\b", "\\\\b");
        ESCAPE_MAP.put("\f", "\\\\f");
        ESCAPE_MAP.put("\r", "\\\\r");
        ESCAPE_MAP.put("\"", "\\\\\"");
        ESCAPE_MAP.put("\'", "\\\\'");
        ESCAPE_MAP.put("\\", "\\\\\\\\");
    }

    /**
     * Escape special characters from the given string.
     *
     * @param s
     *        the raw string
     * @return the printable string with special characters escaped.
     */
    public static String escape(final String s) {
        final Matcher m = SPECIAL_CHARACTERS_PATTERN.matcher(s);
        final StringBuffer sb = new StringBuffer(s.length());
        while (m.find()) {
            final String escaped = ESCAPE_MAP.get(m.group());
            m.appendReplacement(sb, escaped);
        }
        m.appendTail(sb);
        return sb.toString();
    }

    private static final Pattern ESCAPE_SEQUENCES_PATTERN =
            Pattern.compile("(\\\\n|\\\\t|\\\\b|\\\\f|\\\\r|\\\\\"|\\\\\'|\\\\\\\\)");

    private static final Map<String, String> UNESCAPE_MAP = new Hashtable<String, String>();
    static {
        UNESCAPE_MAP.put("\\n", "\n");
        UNESCAPE_MAP.put("\\t", "\t");
        UNESCAPE_MAP.put("\\b", "\b");
        UNESCAPE_MAP.put("\\f", "\f");
        UNESCAPE_MAP.put("\\r", "\r");
        UNESCAPE_MAP.put("\\\"", "\"");
        UNESCAPE_MAP.put("\\\'", "\'");
        UNESCAPE_MAP.put(DOUBLE_BACK_SLASH, DOUBLE_BACK_SLASH);
    }

    /**
     * Convert escape sequences from the given string.
     *
     * @param s
     *        the escaped string
     * @return the string with special characters
     */
    public static String unescape(final String s) {
        final Matcher m = ESCAPE_SEQUENCES_PATTERN.matcher(s);
        final StringBuffer sb = new StringBuffer(s.length());
        while (m.find()) {
            final String escaped = UNESCAPE_MAP.get(m.group());
            m.appendReplacement(sb, escaped);
        }
        m.appendTail(sb);
        return sb.toString();
    }

    /**
     * @param s
     *        a string
     * @return true if the given string is null or 0-length (<code>""</code>)
     */
    public static boolean isNullOrEmpty(final String s) {
        return null == s || s.length() == 0;
    }

    /**
     * @param s
     *        a string
     * @return true if the given string is not null, and not 0-length
     */
    public static boolean hasLength(final String s) {
        return !isNullOrEmpty(s);
    }

    /**
     * Strip the given suffix, if present, from the given input string.
     *
     * @param s
     *        the string
     * @param suffix
     *        the suffix to remove, if present
     * @return the string with the suffix removed
     */
    public static String chomp(final String s, final String suffix) {
        if (hasLength(suffix) && s.endsWith(suffix)) {
            return s.substring(0, s.lastIndexOf(suffix));
        }
        return s;
    }
}
