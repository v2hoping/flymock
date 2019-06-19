package com.hoping.owl.flymock;

/**
 * Created by houping wang on 2019/4/15
 *
 * @author houping wang
 */
public class MockConfig {

    public MockConfig(String scanPackage) {
        this.scanPackage = scanPackage;
    }

    /**
     * handle实现路径
     */
    private String scanPackage;

    public String getScanPackage() {
        return scanPackage;
    }
}
