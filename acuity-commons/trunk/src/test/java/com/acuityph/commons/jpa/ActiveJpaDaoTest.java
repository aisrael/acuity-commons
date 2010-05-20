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

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.orm.jpa.JpaTemplate;

import dirty.mockito.junit.rules.MagicMocker;

import test.MyEntity;

/**
 * JUnit test for {@link ActiveJpaDao}.
 *
 * @author Alistair A. Israel
 * @since 0.3.1
 */
public final class ActiveJpaDaoTest {

    // CHECKSTYLE:OFF
    @Rule
    public final MagicMocker magicMocker = new MagicMocker();
    // CHECKSTYLE:ON

    @Mock
    private JpaTemplate jpaTemplate;

    /**
     * Typical use case for {@link ActiveJpaDao}.
     *
     * @author Alistair A. Israel
     */
    public static class MyEntityJpaDao extends ActiveJpaDao<Integer, MyEntity> {

        /**
         *
         */
        public MyEntityJpaDao() {
            super(MyEntity.class);
        }

    }

    private MyEntityJpaDao myEntityJpaDao;

    /**
     *
     */
    @Before
    public void setUp() {
        myEntityJpaDao = new MyEntityJpaDao();
        myEntityJpaDao.setJpaTemplate(jpaTemplate);
    }

    /**
     * Test for {@link ActiveJpaDao#listAll()} using auto-discovery.
     */
    @Test
    public void testListAllAutoDiscovery() {
        myEntityJpaDao.listAll();
        verify(jpaTemplate).findByNamedQuery(MyEntity.FIND_ALL);
    }

    /**
     * Test for {@link ActiveJpaDao#listAll()} using
     * {@link ActiveJpaDao#setListAllNamedQueryName(String)}.
     */
    @Test
    public void testListAllSetListAllNamedQueryName() {
        myEntityJpaDao.setListAllNamedQueryName("listAllMyEntities");
        myEntityJpaDao.listAll();
        verify(jpaTemplate).findByNamedQuery("listAllMyEntities");
    }

    /**
     * Test for {@link ActiveJpaDao#listAll()} using
     * {@link ActiveJpaDao#setListAllQuery(String)}.
     */
    @Test
    public void testListAllSetListAllQuery() {
        // force myEntityJpaDao to use listAllQuery
        myEntityJpaDao.setListAllNamedQueryName(null);
        myEntityJpaDao.setListAllQuery("SELECT e FROM MyEntity e");
        myEntityJpaDao.listAll();
        verify(jpaTemplate).find("SELECT e FROM MyEntity e");
    }

    /**
     * Test {@link ActiveJpaDao#listAll()} throws Exception
     */
    @Test
    public void testListAllThrowsException() {
        final String expectedMessage =
                "Unable to execute listAll(): neither listAllNamedQueryName or listAllQuery are properly set!";

        myEntityJpaDao.setListAllNamedQueryName(null);
        myEntityJpaDao.setListAllQuery(null);
        try {
            myEntityJpaDao.listAll();
            Assert.fail("Should have thrown JpaException(\"" + expectedMessage + "\")!");
        } catch (final JpaException e) {
            assertEquals(expectedMessage, e.getMessage());
        }
    }

    /**
     * Test for {@link ActiveJpaDao#find(Object)}.
     */
    @Test
    public void testFind() {
        myEntityJpaDao.find(1);
        verify(jpaTemplate).find(MyEntity.class, Integer.valueOf(1));
    }
}
