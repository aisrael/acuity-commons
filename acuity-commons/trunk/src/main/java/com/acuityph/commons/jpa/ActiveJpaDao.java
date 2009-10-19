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

import static com.acuityph.commons.jpa.JpaUtils.constructSelectAllQuery;
import static java.lang.String.format;
import static org.springframework.util.StringUtils.hasLength;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.util.ClassUtils;

import com.acuityph.commons.dao.Dao;

/**
 * <p>
 * ActiveJpaDao extends {@link JpaDaoSupport} and, through reflection (using
 * {@link EntityInspector}) defines commonly used CRUD methods on JPA entities.
 * </p>
 * <p>
 * Typical use involves creating a subclass of this class and in that class'
 * constructor, pass in the entity class to inspect/use:
 * </p>
 *
 * <pre>
 * public class MyEntityJpaDao extends ActiveJpaDao&lt;MyEntity&gt; {
 *     public MyEntityJpaDao() {
 *         super(MyEntity.class);
 *     }
 * }
 * </pre>
 *
 * @param <K>
 *        the entity primary key type
 * @param <E>
 *        an entity type
 * @author Alistair A. Israel
 * @since 0.3.1
 * @see JpaDaoSupport
 * @see EntityInspector
 */
public class ActiveJpaDao<K, E> extends JpaDaoSupport implements Dao<K, E> {

    private final EntityInspector<E> entityInspector;

    private String listAllNamedQueryName;

    private String listAllQuery;

    /**
     * @param entityClass
     *        the entity class
     */
    public ActiveJpaDao(final Class<E> entityClass) {
        this.entityInspector = EntityInspector.inspect(entityClass);
        final String entityName = ClassUtils.getShortName(entityClass);
        if (entityInspector.hasNamedQuery(entityName + ".findAll")
                || entityInspector.hasNamedQuery(entityName + ".listAll")) {
            if (entityInspector.hasNamedQuery(entityName + ".findAll")) {
                listAllNamedQueryName = entityName + ".findAll";
            } else {
                listAllNamedQueryName = entityName + ".listAll";
            }
            listAllQuery = entityInspector.getNamedQueryString(listAllNamedQueryName);
        } else {
            listAllQuery = constructSelectAllQuery(entityName);
        }
    }

    /**
     * @return the entityClass
     */
    public final Class<E> getEntityClass() {
        return entityInspector.getEntityClass();
    }

    /**
     * The named query name to use for {@link #listAll()}. Will default to
     * "Entity.listAll" or "Entity.findAll" if a
     * {@link javax.persistence.NamedQuery} is present on the supplied entity
     * class.
     *
     * @return the listAllNamedQueryName
     */
    public final String getListAllNamedQueryName() {
        return listAllNamedQueryName;
    }

    /**
     * <p>
     * Set the named query name to use for {@link #listAll()}. Will take
     * precedence over the {@link #setListAllQuery(String) listAllQuery} string.
     * </p>
     * <p>
     * No checking is performed to validate that the named query exists (the
     * named query may have been defined externally and not available via
     * inspection for {@link javax.persistence.NamedQuery} annotations).
     * </p>
     *
     * @param listAllNamedQueryName
     *        the listAllNamedQueryName to set
     */
    public final void setListAllNamedQueryName(final String listAllNamedQueryName) {
        this.listAllNamedQueryName = listAllNamedQueryName;
    }

    /**
     * The JPQL query to use for {@link #listAll()}. If no suitable
     * {@link #getListAllNamedQueryName() listAllNamedQueryName} can be found,
     * then this is set by default using
     * {@link JpaUtils#constructSelectAllQuery(String)}.
     *
     * @return the listAllQuery
     * @see JpaUtils#constructSelectAllQuery(String)
     */
    public final String getListAllQuery() {
        return listAllQuery;
    }

    /**
     * Set the JPQL query to use for {@link #listAll()} manually. If no suitable
     * {@link #setListAllNamedQueryName() listAllNamedQueryName} is set, then
     * this query is used instead.
     *
     * @param listAllQuery
     *        the listAllQuery to set
     */
    public final void setListAllQuery(final String listAllQuery) {
        this.listAllQuery = listAllQuery;
    }

    /**
     * Find by primary key.
     *
     * @param id
     *        the primary key
     * @return the found entity instance or null if the entity does not exist
     * @see org.springframework.orm.jpa.JpaTemplate#find(java.lang.Class,
     *      java.lang.Object)
     */
    public final E find(final K id) {
        return getJpaTemplate().find(getEntityClass(), id);
    }

    /**
     * @param queryName
     *        the {@link javax.persistence.NamedQuery} name
     * @return {@link List} of &lt;T&gt;
     * @see org.springframework.orm.jpa.JpaTemplate#findByNamedQuery(String)
     */
    @SuppressWarnings("unchecked")
    public final List<E> findByNamedQuery(final String queryName) {
        return getJpaTemplate().findByNamedQuery(queryName);
    }

    /**
     * @param queryName
     *        the {@link javax.persistence.NamedQuery} name
     * @param params
     *        the parameters to the named query
     * @return {@link List} of &lt;T&gt;
     * @see org.springframework.orm.jpa.JpaTemplate#findByNamedQueryAndNamedParams(String,
     *      Map)
     */
    @SuppressWarnings("unchecked")
    public final List<E> findByNamedQueryAndNamedParams(final String queryName,
            final Map<String, ?> params) {
        return getJpaTemplate().findByNamedQueryAndNamedParams(queryName, params);
    }

    /**
     * @param id
     *        the primary key
     * @return the found entity instance
     * @see org.springframework.orm.jpa.JpaTemplate#getReference(java.lang.Class,
     *      java.lang.Object)
     */
    public final E getReference(final Object id) {
        return getJpaTemplate().getReference(getEntityClass(), id);
    }

    /**
     * Check if the instance belongs to the current persistence context.
     *
     * @param entity
     *        the entity to check
     * @return
     *         <code>true</true if the instance belongs to the current persistence context.
     * @see org.springframework.orm.jpa.JpaTemplate#contains(java.lang.Object)
     */
    public final boolean contains(final E entity) {
        return getJpaTemplate().contains(entity);
    }

    /**
     * Make an entity instance managed and persistent.
     *
     * @param entity
     *        the entity to persist
     * @see org.springframework.orm.jpa.JpaTemplate#persist(java.lang.Object)
     */
    public final void persist(final E entity) {
        getJpaTemplate().persist(entity);
    }

    /**
     * Refresh the state of the instance from the database, overwriting changes
     * made to the entity, if any.
     *
     * @param entity
     *        the entity to refresh
     * @see org.springframework.orm.jpa.JpaTemplate#refresh(java.lang.Object)
     */
    public final void refresh(final E entity) {
        getJpaTemplate().refresh(entity);
    }

    /**
     * Remove the entity instance.
     *
     * @param entity
     *        the entity to remove
     * @see org.springframework.orm.jpa.JpaTemplate#remove(java.lang.Object)
     */
    public final void remove(final E entity) {
        getJpaTemplate().remove(entity);
    }

    /**
     * If the {@link #setListAllNamedQueryName(String) listAllNamedQueryName} is
     * set, then executes that named query and returns the results. Otherwise,
     * if {@link #setListAllQuery(String) listAllQuery} is set, then this
     * returns the results of executing that JPQL query string directly.
     * Otherwise, throws a {@link JpaException}
     * ("Unable to execute listAll(): ...").
     *
     * @return the results of the "listAll" query
     * @see Dao#listAll()
     */
    @SuppressWarnings("unchecked")
    public final List<E> listAll() {
        if (hasLength(listAllNamedQueryName)) {
            return getJpaTemplate().findByNamedQuery(listAllNamedQueryName);
        } else if (hasLength(listAllQuery)) {
            return getJpaTemplate().find(listAllQuery);
        } else {
            throw new JpaException(
                    "Unable to execute listAll(): neither listAllNamedQueryName or listAllQuery are properly set!");
        }
    }

    /**
     * Similar to {@link #findByNamedQueryAndNamedParams(String, Map)}, but
     * expects only a single row to be returned. Returns null if more than one
     * row is returned.
     *
     * @param queryName
     *        the named query name
     * @param params
     *        the query parameters
     * @return object of type T, or null
     */
    public final E findSingleResultByNamedQueryAndNamedParams(final String queryName,
            final Map<String, ?> params) {
        final List<E> list = findByNamedQueryAndNamedParams(queryName, params);
        final int n = list.size();
        if (n == 1) {
            return list.get(0);
        }
        if (n != 0) {
            throw new DataIntegrityViolationException(format(
                    "Named query \"%s\" returned %d results, expecting 0 or 1!", queryName, n));
        }
        return null;
    }

}
