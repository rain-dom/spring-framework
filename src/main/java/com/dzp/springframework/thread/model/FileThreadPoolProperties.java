package com.dzp.springframework.thread.model;

import com.dzp.springframework.thread.YamlConfigFactory;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@EqualsAndHashCode(callSuper = true)
@Component
@PropertySource(value = "thread/thread-config.yml", factory = YamlConfigFactory.class)
@ConfigurationProperties(prefix = "file-pool")
public class FileThreadPoolProperties extends BaseThreadPoolProperties {


}
