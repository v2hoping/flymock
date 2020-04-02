package com.hoping.owl.starter.util;

import com.hoping.owl.flymock.rule.RuleUnit;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by houping wang on 2019/4/24
 *
 * @author houping wang
 */
public class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringUtil.applicationContext == null) {
            SpringUtil.applicationContext = applicationContext;
        }
    }

    /**
     * 获取applicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 通过name获取Bean.
     *
     * @param name name
     * @return bean
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * 通过class获取Bean.
     *
     * @param clazz 类型
     * @return bean
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 通过name,以及Clazz返回指定的Bean
     *
     * @param name  名称
     * @param clazz 类型
     * @return bean
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

    /**
     * 通过类型获得所有接口
     *
     * @param clazz clazz
     * @return bean map
     */
    public static <T> Map<String, T> getBeans(Class<T> clazz) {
        return getApplicationContext().getBeansOfType(clazz);
    }

    public static Map<String, Set<Map.Entry<String, Object>>> getMapPropertyByPrefix(Set<String> prefixArray) {
        AbstractEnvironment aEnv = (AbstractEnvironment) applicationContext.getEnvironment();
        MutablePropertySources propertySources = aEnv.getPropertySources();
        Map<String, Set<Map.Entry<String, Object>>> map = new HashMap<>();
        propertySources.forEach(propertySource -> {
            if (propertySource instanceof MapPropertySource) {
                MapPropertySource mps = (MapPropertySource) propertySource;
                Set<Map.Entry<String, Object>> entries = mps.getSource().entrySet();
                for (Map.Entry<String, Object> entry : entries) {
                    for (String prefix : prefixArray) {
                        if (entry.getKey().startsWith(prefix)) {
                            Set<Map.Entry<String, Object>> prefixSet = map.get(prefix);
                            if (prefixSet == null) {
                                prefixSet = new HashSet<>();
                            }
                            prefixSet.add(entry);
                        }
                    }
                }
            }
        });
        return map;
    }

    public static Map<String, Set<RuleUnit>> getMapPropertyRuleUnitByPrefix(Set<String> prefixArray) {
        AbstractEnvironment aEnv = (AbstractEnvironment) applicationContext.getEnvironment();
        MutablePropertySources propertySources = aEnv.getPropertySources();
        Map<String, Set<RuleUnit>> map = new HashMap<>();
        propertySources.forEach(propertySource -> {
            if (propertySource instanceof MapPropertySource) {
                MapPropertySource mps = (MapPropertySource) propertySource;
                Set<Map.Entry<String, Object>> entries = mps.getSource().entrySet();
                for (Map.Entry<String, Object> entry : entries) {
                    for (String prefix : prefixArray) {
                        if (entry.getKey().startsWith(prefix)) {
                            map.computeIfAbsent(prefix, k -> new HashSet<>()).add(new RuleUnit(entry));
                        }
                    }
                }
            }
        });
        return map;
    }

}
