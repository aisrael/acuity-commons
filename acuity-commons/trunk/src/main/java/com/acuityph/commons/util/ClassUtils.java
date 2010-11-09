/**
 * Copyright (c) 2008-2010 Acuity Technologies, Inc.
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
 * Created Nov 9, 2010
 */
package com.acuityph.commons.util;

import static java.lang.reflect.Modifier.isPrivate;
import static java.lang.reflect.Modifier.isStatic;
import static org.springframework.util.StringUtils.hasLength;

import java.lang.reflect.Method;

import org.springframework.util.Assert;

/**
 * @author Alistair A. Israel
 */
public final class ClassUtils {

    /**
     * Utility classes should not have a public or default constructor.
     */
    private ClassUtils() {
        // noop
    }

    /**
     * Extracts the property name from a property setter or getter method name. If the given methodName matches
     * <tt>set([A-Z].*)|get([A-Z].*)</tt> then returns the part after <tt>set</tt> or <tt>get</tt> with its first
     * character converted to lowercase. Otherwise, returns the empty string.
     *
     * @param methodName
     *        the method name
     * @return the property name
     */
    public static String getPropertyName(final String methodName) {
        if (isPropertyAccessorName(methodName)) {
            final String propertyName = methodName.substring(3);
            return StringUtility.decapitalize(propertyName);
        }
        return "";
    }

    /**
     * @param methodName
     *        the method name to check
     * @return true if the given method name matches a property accessor (getter or setter) pattern
     */
    private static boolean isPropertyAccessorName(final String methodName) {
        return hasLength(methodName) && methodName.length() > 3
                && (methodName.startsWith("set") || methodName.startsWith("get"))
                && Character.isUpperCase(methodName.charAt(3));
    }

    /**
     * Checks if the given method matches the JavaBean property setter signature.
     *
     * @param method
     *        the method to check
     * @return {@code true}, if the method matchers the JavaBean property setter signature
     */
    public static boolean isPropertySetter(final Method method) {
        Assert.notNull(method, "method is null!");
        final int mod = method.getModifiers();
        final boolean expectsOneParameter = method.getParameterTypes().length == 1;
        final boolean returnsVoid = void.class.equals(method.getReturnType());
        return !isPrivate(mod) && !isStatic(mod) && expectsOneParameter && returnsVoid
                && isPropertySetterName(method.getName());
    }

    /**
     * @param methodName
     *        the method name to check
     * @return true if the given method name matches a property getter pattern
     */
    private static boolean isPropertySetterName(final String methodName) {
        return hasLength(methodName) && methodName.length() > 3 && methodName.startsWith("set")
                && Character.isUpperCase(methodName.charAt(3));
    }

    /**
     * Checks if is property getter.
     *
     * @param method
     *        the method
     * @return true, if is property getter
     */
    public static boolean isPropertyGetter(final Method method) {
        Assert.notNull(method, "method is null!");
        final int mod = method.getModifiers();
        final boolean expectsNoParameters = method.getParameterTypes().length == 0;
        final boolean returnsSomething = !void.class.equals(method.getReturnType());
        return !isPrivate(mod) && !isStatic(mod) && expectsNoParameters && returnsSomething
                && isPropertyGetterName(method.getName());
    }

    /**
     * @param methodName
     *        the method name to check
     * @return true if the given method name matches a property getter pattern
     */
    private static boolean isPropertyGetterName(final String methodName) {
        return hasLength(methodName) && methodName.length() > 3 && methodName.startsWith("get")
                && Character.isUpperCase(methodName.charAt(3));
    }
}
