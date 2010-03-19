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
 * Created May 25, 2009
 */
package com.acuityph.commons.xml;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.core.io.Resource;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * Utility methods for working with XML.
 *
 * @author Alistair A. Israel
 */
public final class XmlUtility {

    /**
     * Utility classes should not have a public or default constructor.
     */
    private XmlUtility() {
        // noop
    }

    /**
     * @param resource
     *        the XML resource
     * @return {@link Document}
     * @throws IOException
     *         on exception
     * @throws ParserConfigurationException
     *         on exception
     * @throws SAXException
     *         on exception
     */
    public static Document readDocument(final Resource resource) throws IOException,
            ParserConfigurationException, SAXException {
        final InputStream is = resource.getInputStream();
        if (is != null) {
            try {
                final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                final DocumentBuilder db = dbf.newDocumentBuilder();
                return db.parse(is);
            } finally {
                is.close();
            }
        }
        return null;

    }

    /**
     * @param resource
     *        the XML resource
     * @return the {@link Document}
     */
    public static Document quietlyReadDocument(final Resource resource) {
        try {
            return readDocument(resource);
        } catch (final IOException e) {
            throw new XmlException(e);
        } catch (final ParserConfigurationException e) {
            throw new XmlException(e);
        } catch (final SAXException e) {
            throw new XmlException(e);
        }
    }

}
