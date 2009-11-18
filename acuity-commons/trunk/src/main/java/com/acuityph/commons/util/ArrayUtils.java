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
 * Created Aug 24, 2009
 */
package com.acuityph.commons.util;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alistair A. Israel
 * @since 0.4.1
 */
public final class ArrayUtils {

    /**
     *
     */
    private ArrayUtils() {
        // noop
    }

    /**
     * @param <T>
     *            a type
     * @param array
     *            an array
     * @return true if the given array is not null and has at least one element
     */
    public static <T> boolean hasElements(final T[] array) {
        return !isNullOrEmpty(array);
    }

    /**
     * @param <T>
     *            a type
     * @param array
     *            an array
     * @return true if the given array is null or is empty
     */
    public static <T> boolean isNullOrEmpty(final T[] array) {
        return array == null || array.length == 0;
    }

    /**
     * @param array
     *            char[]
     * @return Character[]
     */
    public static Character[] box(final char[] array) {
        final int n = array.length;
        final Character[] results = new Character[n];
        for (int i = 0; i < n; ++i) {
            results[i] = Character.valueOf(array[i]);
        }
        return results;
    }

    /**
     * @param array
     *            int[]
     * @return Integer[]
     */
    public static Integer[] box(final int[] array) {
        final int n = array.length;
        final Integer[] results = new Integer[n];
        for (int i = 0; i < n; ++i) {
            results[i] = Integer.valueOf(array[i]);
        }
        return results;
    }

    /**
     * @param array
     *            Integer[]
     * @return int[]
     */
    public static int[] unbox(final Integer[] array) {
        final int n = array.length;
        final int[] results = new int[n];
        for (int i = 0; i < n; ++i) {
            results[i] = array[i].intValue();
        }
        return results;
    }

    /**
     * @param array
     *            array of <code>char</code>
     * @return {@link List}&lt;{@link Character}&gt;
     */
    public static List<Character> asList(final char[] array) {
        final ArrayList<Character> list = new ArrayList<Character>(array.length);
        for (final char c : array) {
            list.add(Character.valueOf(c));
        }
        return list;
    }

    /**
     * @param array
     *            array of <code>int</code>
     * @return {@link List}&lt;{@link Integer}&gt;
     */
    public static List<Integer> asList(final int[] array) {
        final ArrayList<Integer> list = new ArrayList<Integer>(array.length);
        for (final int i : array) {
            list.add(Integer.valueOf(i));
        }
        return list;
    }

    /**
     * Returns the array containing only non-<tt>null</tt> elements from its
     * arguments.
     *
     * @param <T>
     *            a type (can be Object)
     * @param args
     *            the arguments (varargs)
     * @return the array containing only non-<tt>null</tt> elements.
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] compact(final T... args) {
        final List<T> results = new ArrayList<T>();
        for (final T obj : args) {
            if (obj != null) {
                results.add(obj);
            }
        }
        return (T[]) results.toArray();
    }

}
