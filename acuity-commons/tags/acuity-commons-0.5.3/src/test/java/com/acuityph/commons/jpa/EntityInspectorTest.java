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
 * Created May 12, 2009
 */
package com.acuityph.commons.jpa;

import static com.acuityph.commons.jpa.EntityInspector.inspect;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.springframework.util.ReflectionUtils.findField;

import java.lang.reflect.Field;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import test.MyEntity;

/**
 * JUnit test for {@link EntityInspector}.
 *
 * @author Alistair A. Israel
 * @since 0.3.1
 */
public final class EntityInspectorTest {

    /**
     *
     */
    private static final Class<MyEntity> ENTITY_CLASS = MyEntity.class;
    private EntityInspector<MyEntity> entityInspector;

    /**
     *
     */
    @Before
    public void setUp() {
        entityInspector = inspect(ENTITY_CLASS);
    }

    /**
     *
     */
    @After
    public void tearDown() {
        entityInspector = null;
    }

    /**
     * Test for {@link EntityInspector#getIdField()}.
     */
    @Test
    public void testGetIdField() {
        final Field expected = findField(ENTITY_CLASS, "id");
        assertEquals(expected, entityInspector.getIdField());
        assertEquals("id", entityInspector.getIdFieldName());
    }

    /**
     * Test method for {@link EntityInspector#hasNamedQuery(String)} .
     */
    @Test
    public void testHasNamedQuery() {
        assertTrue(entityInspector.hasNamedQuery(MyEntity.FIND_ALL));
        assertFalse(entityInspector.hasNamedQuery("Not a query name."));
    }

    /**
     * Test method for {@link EntityInspector#getNamedQueryString(String)} .
     */
    @Test
    public void testGetNamedQueryString() {
        final String actual = entityInspector.getNamedQueryString(MyEntity.FIND_ALL);
        assertEquals("SELECT e FROM MyEntity e", actual);
    }

}
