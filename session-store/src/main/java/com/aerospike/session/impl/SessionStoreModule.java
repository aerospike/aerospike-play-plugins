/*
 * Copyright (C) 2008-2015 Aerospike, Inc.
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

package com.aerospike.session.impl;

import com.aerospike.session.SessionIDProvider;
import com.aerospike.session.SessionStore;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

/**
 * The module for binding SessionStore interface to AerospikeSessionStore
 *
 * @author akshay
 *
 */
public class SessionStoreModule extends AbstractModule {
    /*
     * (non-Javadoc)
     * 
     * @see com.google.inject.AbstractModule#configure()
     */
    @Override
    protected void configure() {
        bind(SessionStore.class).to(AerospikeSessionStore.class).in(
                Singleton.class);
        bind(SessionIDProvider.class).toProvider(
                SessionIDProviderProvider.class);

    }
}
