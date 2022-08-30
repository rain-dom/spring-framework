package com.dzp.springframework.thread;

import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.DefaultPropertySourceFactory;
import org.springframework.core.io.support.EncodedResource;

/**
 * 加载自定义Yaml格式配置文件读取.
 */
public class YamlConfigFactory extends DefaultPropertySourceFactory {

    /**
     * yaml文件完整限定名.
     */
    private static final String YAML_SUFFIX_COMPLETE = ".yaml";

    /**
     * yaml文件通用限定名.
     */
    private static final String YAML_SUFFIX_GENERAL = ".yml";


    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
        if (!resource.getResource().exists()) {
            return new PropertiesPropertySource(name, new Properties());
        }
        String resourceName = name != null ? name : resource.getResource().getFilename();
        //仅当配置文件名以”.yml“或者”.yaml“结尾的方式通过YamlPropertiesFactoryBean读取
        boolean match = resourceName != null
                && (resourceName.endsWith(YAML_SUFFIX_COMPLETE) || resourceName.endsWith(YAML_SUFFIX_GENERAL));
        if (match) {
            Properties properties = loadYaml(resource);
            return new PropertiesPropertySource(resourceName, properties);
        }
        return super.createPropertySource(name, resource);

    }


    /**
     * 通过YamlPropertiesFactoryBean读取Yaml文件属性.
     *
     * @param resource  待读取的资源文件
     * @return {@link Properties} 读取的配置集合
     */
    private Properties loadYaml(EncodedResource resource) {
        YamlPropertiesFactoryBean yamlFactory = new YamlPropertiesFactoryBean();
        yamlFactory.setResources(resource.getResource());
        yamlFactory.afterPropertiesSet();
        return yamlFactory.getObject();
    }
}
