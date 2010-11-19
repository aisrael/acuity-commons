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

import static java.text.MessageFormat.format;

import java.lang.reflect.Field;
import java.util.Hashtable;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Helper class for performing reflection on JPA annotated entities.
 *
 * @param <T>
 *        an JPA MyEntity type
 * @author Alistair A. Israel
 * @since 0.3.1
 */
public class EntityInspector<T> {

    private static final Log log = LogFactory.getLog(EntityInspector.class);

    private final Class<T> entityClass;

    private final Map<String, NamedQuery> namedQueries = new Hashtable<String, NamedQuery>();

    private final Field idField;

    private final String idFieldName;

    /**
     * @param <E>
     *        a JPA MyEntity type
     * @param entityClass
     *        the class of &lt;E&gt;
     * @return the EntityInspector for E.class
     */
    public static <E> EntityInspector<E> inspect(final Class<E> entityClass) {
        return new EntityInspector<E>(entityClass);
    }

    /**
     * @param entityClass
     *        the entity class to inspect
     */
    public EntityInspector(final Class<T> entityClass) {
        this.entityClass = entityClass;
        idField = JpaUtils.determineIdField(entityClass);
        if (idField != null) {
            idFieldName = idField.getName();
        } else {
            idFieldName = null;
        }
        inspectNamedQueries();
    }

    /**
     *
     */
    private void inspectNamedQueries() {
        final Entity entityAnnotation = entityClass.getAnnotation(Entity.class);
        if (entityAnnotation == null) {
            log.warn(format("Class<{0}> not annotated with @Entity!", entityClass));
        }
        final NamedQueries nqs = entityClass.getAnnotation(NamedQueries.class);
        if (nqs != null && nqs.value().length > 0) {
            for (final NamedQuery namedQuery : nqs.value()) {
                namedQueries.put(namedQuery.name(), namedQuery);
            }
        } else {
            log.debug(format(
                    "Class<{0}> not annotated with @NamedQueries or has no @NamedQuery!",
                    entityClass));
        }
    }

    /**
     * @return the entityClass
     */
    public final Class<T> getEntityClass() {
        return entityClass;
    }

    /**
     * @return the idField
     */
    public final Field getIdField() {
        return idField;
    }

    /**
     * @return the idFieldName
     */
    public final String getIdFieldName() {
        return idFieldName;
    }

    /**
     * @param name
     *        the NamedQuery name
     * @return true if the inspected entity class has a NamedQuery with the
     *         corresponding name
     */
    public final boolean hasNamedQuery(final String name) {
        return namedQueries.containsKey(name);
    }

    /**
     * @param name
     *        the NamedQuery name
     * @return the {@link NamedQuery#query()} string
     */
    public final String getNamedQueryString(final String name) {
        if (hasNamedQuery(name)) {
            return namedQueries.get(name).query();
        }
        return null;
    }

}
