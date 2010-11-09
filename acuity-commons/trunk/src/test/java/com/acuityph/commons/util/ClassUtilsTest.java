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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Method;
import java.util.Date;

import org.junit.Test;

import com.example.Sample;

/**
 * @author Alistair A. Israel
 */
public final class ClassUtilsTest {

    /**
     * Test for {@link ClassUtils#getPropertyName(java.lang.String)}
     *
     * @throws Exception
     *             should not get thrown
     */
    @Test
    public void testGetPropertyName() throws Exception {
        assertEquals("", ClassUtils.getPropertyName(null));
        assertEquals("", ClassUtils.getPropertyName(""));
        assertEquals("", ClassUtils.getPropertyName("not a method name"));
        assertEquals("", ClassUtils.getPropertyName("notASetter"));
        assertEquals("property", ClassUtils.getPropertyName("setProperty"));
        final Method method = Sample.class.getMethod("getCreatedAt");
        assertEquals("createdAt", ClassUtils.getPropertyName(method.getName()));
    }

    /**
     * Test for {@link ClassUtils#isPropertySetter(Method)}
     *
     * @throws Exception
     *             should not get thrown
     */
    @Test
    public void testIsPropertySetter() throws Exception {
        // public, one parameter, returns void, but wrong naming convention
        final Method publicMethodWithOneParameter = Sample.class.getDeclaredMethod("publicMethodWithOneParameter",
                String.class);
        assertFalse(ClassUtils.isPropertySetter(publicMethodWithOneParameter));

        // one parameter, returns void, set[A-Z], but private
        final Method setActive = Sample.class.getDeclaredMethod("setActive", boolean.class);
        assertFalse(ClassUtils.isPropertySetter(setActive));

        // public, returns void, set[A-Z], but no parameters
        final Method setExpired = Sample.class.getDeclaredMethod("setExpired");
        assertFalse(ClassUtils.isPropertySetter(setExpired));

        // public, one parameter, returns void, set[A-Z], but static
        final Method setFactory = Sample.class.getDeclaredMethod("setFactory", Object.class);
        assertFalse(ClassUtils.isPropertySetter(setFactory));

        final Method setCreatedAt = Sample.class.getDeclaredMethod("setCreatedAt", Date.class);
        assertTrue(ClassUtils.isPropertySetter(setCreatedAt));
    }

    /**
     * Test for {@link ClassUtils#isPropertyGetter(Method)}
     *
     * @throws Exception
     *             should not get thrown
     */
    @Test
    public void testIsPropertyGetter() throws Exception {
        // public, returns something, get* but expects parameter
        final Method getCharacterAt = Sample.class.getDeclaredMethod("getCharacterAt", int.class);
        assertFalse(ClassUtils.isPropertyGetter(getCharacterAt));

        // public, one parameter, returns something, get*, but static
        final Method getFactory = Sample.class.getDeclaredMethod("getFactory");
        assertFalse(ClassUtils.isPropertyGetter(getFactory));

        final Method getCreatedAt = Sample.class.getDeclaredMethod("getCreatedAt");
        assertTrue(ClassUtils.isPropertyGetter(getCreatedAt));
    }
}
