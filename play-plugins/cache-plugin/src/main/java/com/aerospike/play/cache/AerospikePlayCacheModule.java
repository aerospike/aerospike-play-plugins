/*
 * Copyright 2008-2015 Aerospike, Inc.
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
package com.aerospike.play.cache;

import lombok.RequiredArgsConstructor;

import org.nustaq.serialization.FSTConfiguration;

import play.Configuration;
import play.Environment;
import play.cache.CacheApi;

import com.aerospike.cache.AerospikeCacheConfig;
import com.aerospike.cache.AerospikeClientModule;
import com.aerospike.transcoder.TranscoderModule;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

/**
 * Installs all the dependent modules.
 *
 * @author akshay
 *
 */
@RequiredArgsConstructor
public class AerospikePlayCacheModule extends AbstractModule {

	private final Environment environment;
	@SuppressWarnings("unused")
	private final Configuration configuration;
	
	@Singleton
	@Provides
	FSTConfiguration getFstConfiguration(){
		 FSTConfiguration conf = FSTConfiguration.createDefaultConfiguration();
	        conf.setClassLoader(environment.classLoader());
	        return conf;
	}
    
	/**
     * (non-Javadoc)
     *
     * @see com.google.inject.AbstractModule#configure()
     */
    @Override
    protected void configure() {
        install(new AerospikeClientModule());
        install(new TranscoderModule());
        bind(AerospikeCacheConfig.class).toProvider(PlayConfigReader.class).in(Singleton.class);
        bind(CacheApi.class).to(AerospikeCacheApi.class).in(Singleton.class);
    }

}