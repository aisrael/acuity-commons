/* Component: clpgas-utilities
 *
 * Licensed Materials
 * Property of Chartis Insurance (Chartis Inc.)
 * A division of American International Group, Inc.
 * Copyright (c) 2010 American International Group, Inc.
 * All Rights Reserved
 *
 * Creation Date: Nov 9, 2010 6:54:18 PM
 *
 * History
 * Nov 9, 2010    Initial creation        Alistair.Israel
 */
package com.example;

import java.util.Date;

/**
 * The Sample class.
 */
// CHECKSTYLE:OFF
@SuppressWarnings("unused")
public final class Sample {

    /**
     * The created at.
     */
    private Date createdAt;

    /**
     * The is active.
     */
    private boolean isActive;

    /**
     * The is expired.
     */
    private boolean isExpired;

    /**
     * A private method.
     */
    private void privateMethod() {
    }

    /**
     * A protected method.
     */
    protected void protectedMethod() {
    }

    /**
     * A public method.
     */
    public void publicMethod() {
    }

    /**
     * A public method with one parameter.
     */
    public void publicMethodWithOneParameter(final String s) {
    }

    /**
     * A static method.
     */
    public static void staticMethod() {
    }

    /**
     * Public, returns void, "$set[A-Z]", one parameter, but static
     */
    public static void setFactory(final Object o) {
    }

    /**
     * Public, returns void, "$get[A-Z]", one parameter, but static
     */
    public static Object getFactory() {
        return null;
    }

    /**
     * Gets the created at.
     *
     * @return the createdAt
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the created at.
     *
     * @param createdAt
     *            the createdAt to set
     */
    public void setCreatedAt(final Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Public, returns something, "$get[A-Z]" but expects parameter
     */
    public Character getCharacterAt(final int index) {
        return null;
    }

    /**
     * Checks if is active.
     *
     * @return the isActive
     */
    private boolean isActive() {
        return isActive;
    }

    /**
     *
     */
    private void setActive(final boolean isActive) {
        this.isActive = isActive;
    }

    /**
     *
     */
    public boolean isExpired() {
        return isExpired;
    }

    /**
     *
     */
    public void setExpired() {
        this.isExpired = true;
    }

}