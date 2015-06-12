/*
 * Copyright (C) 2015 Aeroshift Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.aerospike.session;

import java.util.Map;

/**
 * A store for session data. A session is identified by a string session id. The
 * session store allows one to store arbitrary key value pairs for a session.
 *
 * <p>
 * This session store is meant to used on each node to serve a cluster of
 * web-applications
 * </p>
 *
 * <p>
 * <h2>Expiry</h2>
 * The store can be configured with a session timeout at startup. A session
 * expires if there has been no get / put / touch operation for that session id
 * for a session timeout interval.
 * </p>
 *
 *
 * @author ashish
 *
 */
public interface SessionStore {
    /**
     * Add a key value pair associated with the session identified by sessionId.
     * If a key value pair with the key already exists, the value will be
     * overwritten. As a side effect marks this session as in use and reset the
     * ttl to the session store expiry if set.
     *
     * @param sessionId
     *            the session id.
     * @param key
     *            the key
     * @param value
     *            the value
     */
    void put(final String sessionId, final String key, final Object value)
            throws SessionStoreException;

    /**
     * Add batch of key value pairs associated with the session identified by
     * sessionId. If a key value pair with a key already exists, the value will
     * be overwritten. As a side effect marks this session as in use and reset
     * the ttl to the session store expiry if set.
     *
     * @param sessionId
     *            the session id.
     * @param map
     *            the key value pairs to write to the session store.
     * @throws SessionStoreException
     *             if session storage failed to read.
     */
    void putAll(final String sessionId, final Map<String, Object> map)
            throws SessionStoreException;

    /**
     * Read the value for a key associated with a session identified by
     * sessionId.
     *
     * @param sessionId
     *            the session id.
     * @param key
     *            the key.
     *
     * @throws SessionNotFound
     *             if the session has expired or does not exist.
     * @throws SessionStoreException
     *             if session storage failed to read.
     */
    Object get(final String sessionId, final String key)
            throws SessionNotFound, SessionStoreException;

    /**
     * Read all key value pairs associated with a session identified by
     * sessionId.
     *
     * @param sessionId
     *            the session id.
     *
     * @throws SessionNotFound
     *             if the session has expired or does not exist.
     * @throws SessionStoreException
     *             if session storage failed to read.
     */
    Map<String, Object> getAll(final String sessionId) throws SessionNotFound,
            SessionStoreException;

    /**
     * Touch this session so that its expiry is advanced.
     *
     * @param sessionId
     *            the session id.
     *
     * @throws SessionStoreException
     *             if session storage failed to update the session expiry.
     */
    void touch(final String sessionId) throws SessionNotFound,
            SessionStoreException;

    /**
     * Destroy a session identified by the sessionId.
     *
     * @param sessionId
     *
     * @throws SessionStoreException
     *             if session storage failed to destroy the session..
     */
    void destroy(final String sessionId) throws SessionStoreException;

    /**
     * Indicates if a session, identified by the sessionId, exists.
     *
     * @param sessionId
     *
     * @throws SessionStoreException
     *             if session storage failed to read.
     */
    boolean exists(final String sessionId) throws SessionStoreException;
}
