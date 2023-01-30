package com.caitb.library_manager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author
 */
@MapperScan("com.caitb.library_manager.base.mapper")
@SpringBootApplication
public class LibraryManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(LibraryManagerApplication.class, args);
    }
}
