package com.lswq.factory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultNamespaceHandlerResolver {


    /**
     * The location to look for the mapping files. Can be present in multiple JAR files.
     */
    public static final String DEFAULT_HANDLER_MAPPINGS_LOCATION = "META-INF/spring.handlers";


    /**
     * Logger available to subclasses
     */
    protected final Log logger = LogFactory.getLog(getClass());

    /**
     * ClassLoader to use for NamespaceHandler classes
     */
    private final ClassLoader classLoader;

    /**
     * Resource location to search for
     */
    private final String handlerMappingsLocation;

    /**
     * Stores the mappings from namespace URI to NamespaceHandler class name / instance
     */
    public volatile Map<String, Object> handlerMappings;


    /**
     * Create a new {@code DefaultNamespaceHandlerResolver} using the
     * default mapping file location.
     * <p>This constructor will result in the thread context ClassLoader being used
     * to load resources.
     *
     * @see #DEFAULT_HANDLER_MAPPINGS_LOCATION
     */
    public DefaultNamespaceHandlerResolver() {
        this(null, DEFAULT_HANDLER_MAPPINGS_LOCATION);
    }

    /**
     * Create a new {@code DefaultNamespaceHandlerResolver} using the
     * supplied mapping file location.
     *
     * @param classLoader             the {@link ClassLoader} instance used to load mapping resources
     *                                may be {@code null}, in which case the thread context ClassLoader will be used)
     * @param handlerMappingsLocation the mapping file location
     */
    public DefaultNamespaceHandlerResolver(ClassLoader classLoader, String handlerMappingsLocation) {
        Assert.notNull(handlerMappingsLocation, "Handler mappings location must not be null");
        this.classLoader = (classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader());
        this.handlerMappingsLocation = handlerMappingsLocation;
    }

    /**
     * Load the specified NamespaceHandler mappings lazily.
     */
    private Map<String, Object> getHandlerMappings() {
        if (this.handlerMappings == null) {
            synchronized (this) {
                if (this.handlerMappings == null) {
                    try {
                        Properties mappings =
                                PropertiesLoaderUtils.loadAllProperties(this.handlerMappingsLocation, this.classLoader);
                        if (logger.isDebugEnabled()) {
                            logger.debug("Loaded NamespaceHandler mappings: " + mappings);
                        }
                        Map<String, Object> handlerMappings = new ConcurrentHashMap<>(mappings.size());
                        CollectionUtils.mergePropertiesIntoMap(mappings, handlerMappings);
                        this.handlerMappings = handlerMappings;
                    } catch (IOException ex) {
                        throw new IllegalStateException(
                                "Unable to load NamespaceHandler mappings from location [" + this.handlerMappingsLocation + "]", ex);
                    }
                }
            }
        }
        return this.handlerMappings;
    }


    @Override
    public String toString() {
        return "NamespaceHandlerResolver using mappings " + getHandlerMappings();


    }
}
