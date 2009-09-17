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
 * Created Feb 19, 2008
 */
package com.acuityph.commons.db.sql;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.acuityph.commons.util.StringUtility;

/**
 * {@link SqlQueryBuilder} is a quick and dirty SQL query builder.
 *
 * @author Alistair A. Israel
 * @since 0.1.3
 */
public class SqlQueryBuilder {

    /**
     *
     */
    private static final String SEP = ", ";

    private SqlCommand command;

    private String tableName;

    private final List<String> columnNames = new ArrayList<String>();

    private final List<String> values = new ArrayList<String>();

    /**
     * Constructs a {@link SqlQueryBuilder}.
     *
     * @param command
     *        The {@link SqlCommand}
     * @param tableName
     *        The table name
     */
    public SqlQueryBuilder(final SqlCommand command, final String tableName) {
        this.command = command;
        this.tableName = tableName;
    }

    /**
     * @return the command
     */
    public final SqlCommand getCommand() {
        return this.command;
    }

    /**
     * @param command
     *        the command to set
     */
    public final void setCommand(final SqlCommand command) {
        this.command = command;
    }

    /**
     * @return the tableName
     */
    public final String getTableName() {
        return this.tableName;
    }

    /**
     * @param tableName
     *        the tableName to set
     */
    public final void setTableName(final String tableName) {
        this.tableName = tableName;
    }

    /**
     * @param tableName
     *        the table name
     * @return {@link SqlQueryBuilder}
     */
    public static SqlQueryBuilder insertInto(final String tableName) {
        return new SqlQueryBuilder(SqlCommand.INSERT, tableName);
    }

    /**
     * @param tableName
     *        the table name
     * @return {@link SqlQueryBuilder}
     */
    public static SqlQueryBuilder update(final String tableName) {
        return new SqlQueryBuilder(SqlCommand.UPDATE, tableName);
    }

    /**
     * @param columns
     *        the column names
     * @return this
     */
    public final SqlQueryBuilder columns(final String[] columns) {
        this.columnNames.addAll(Arrays.asList(columns));
        return this;
    }

    /**
     * @param <K>
     *        the map key type
     * @param <V>
     *        the map value type
     * @param map
     *        Map<K, V>
     * @return this
     */
    public final <K, V> SqlQueryBuilder set(final Map<K, V> map) {
        for (final Map.Entry<K, V> entry : map.entrySet()) {
            this.columnNames.add(entry.getKey().toString());
            this.values.add(entry.getValue().toString());
        }
        return this;
    }

    /**
     * @return The SQL query
     */
    public final String build() {
        final StringBuilder sb = new StringBuilder(command.toString()).append(' ');
        switch (command) {
        case INSERT:
            buildInsertStatement(sb);
            break;
        case UPDATE:
            buildUpdateStatement(sb);
            break;
        default:
            break;
        }
        return sb.toString();
    }

    /**
     * @param sb
     *        {@link StringBuilder}
     */
    private void buildInsertStatement(final StringBuilder sb) {
        sb.append("INTO ");
        sb.append(tableName).append(" (");
        sb.append(StringUtility.join(columnNames, SEP));
        sb.append(") VALUES (");

        final List<String> insertedValues = new ArrayList<String>();
        if (this.values != null) {
            for (final String value : values) {
                insertedValues.add(value);
            }
        }
        while (insertedValues.size() < columnNames.size()) {
            insertedValues.add("?");
        }
        sb.append(StringUtility.join(insertedValues, SEP));
        sb.append(")");
    }

    /**
     * @param sb
     *        {@link StringBuilder}
     */
    private void buildUpdateStatement(final StringBuilder sb) {
        sb.append(tableName).append(" SET ");
        final List<String> clauses = new ArrayList<String>(columnNames.size());
        for (int i = 0; i < columnNames.size(); ++i) {
            final String value;
            if (i < values.size()) {
                value = values.get(i);
            } else {
                value = "?";
            }
            clauses.add(columnNames.get(i) + " = " + value);
        }
        sb.append(StringUtility.join(clauses, SEP));
    }

}
