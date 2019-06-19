package com.hoping.owl.flymock.placeholder.manager;

import com.hoping.owl.flymock.placeholder.PlaceholderHandle;
import com.hoping.owl.flymock.placeholder.PlaceholderManager;
import com.hoping.owl.flymock.util.ClassUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by houping wang on 2019/4/10
 * local处理对象，加载所有实现PlaceholderManager的接口
 *
 * @author houping wang
 */
public class LocalPlaceholderManager extends AbstractPlaceholderManager implements PlaceholderManager {

    /**
     * 扫描包路径
     */
    private Set<String> scanPackages = new LinkedHashSet<>();

    private static final String LOCAL_PACKAGE = "com.hoping.owl.flymock.placeholder.handle";

    public LocalPlaceholderManager(String... scanPackages) {
        this.scanPackages.add(LOCAL_PACKAGE);
        if (scanPackages != null) {
            this.scanPackages.addAll(Arrays.asList(scanPackages));
        }
    }

    @Override
    public void init() {
        Set<Class<?>> allClasses = new LinkedHashSet<>();
        for (String scanPackage : scanPackages) {
            Set<Class<?>> classes = ClassUtil.getClasses(scanPackage);
            allClasses.addAll(classes);
        }
        Set<Class<?>> interfaceClasses = ClassUtil.getByInterface(PlaceholderHandle.class, allClasses);
        for (Class<?> interfaceClass : interfaceClasses) {
            try {
                PlaceholderHandle o = (PlaceholderHandle) interfaceClass.newInstance();
                hashMap.put(o.key(), o);
            } catch (Exception e) {
                LOGGER.error("[动态生成占位符处理器失败]" + e.getMessage(), e);
            }
        }
    }
}
