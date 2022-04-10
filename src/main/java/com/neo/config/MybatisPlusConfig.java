package com.neo.config;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @class: MybatisPlusConfig
 * @description: MybatisPlus配置类,并添加分页插件和防全表更新插件
 * @author: Neo
 * @create: 2022/4/9 15:59
 */

@Configuration
@MapperScan("com.neo.mapper")
public class MybatisPlusConfig {


    /**
     * 分页插件，一级缓存和二级缓存遵循mybatis规则
     * @return mybatisplus的拦截器
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor interceptor=new MybatisPlusInterceptor();

        //分页插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());

        //防全表更新插件
        interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());


        return interceptor;
    }

    /**
     * 避免缓存出现问题（该属性会在旧插件移除后一并移除）
     * @return 定制配置
     */
    @Bean
    public ConfigurationCustomizer configurationCustomizer(){
        return configuration -> configuration.setUseDeprecatedExecutor(false);
    }
}
