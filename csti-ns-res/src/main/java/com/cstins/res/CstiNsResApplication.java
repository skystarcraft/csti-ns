package com.cstins.res;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Import(FdfsClientConfig.class)
@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaRepositories(basePackages = "com.cstins.res.dao")
@EntityScan("com.cstins.res.entity")
@ComponentScan(value = {"com.cstins.res.dao", "com.cstins.res.service", "com.cstins.res.api","com.cstins.res.tools"})
public class CstiNsResApplication {

    public static void main(String[] args) {
        SpringApplication.run(CstiNsResApplication.class, args);
    }

}
