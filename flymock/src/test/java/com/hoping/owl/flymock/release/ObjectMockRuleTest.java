package com.hoping.owl.flymock.release;

import com.hoping.owl.flymock.Mock;
import com.hoping.owl.flymock.TypeReference;
import com.hoping.owl.flymock.object.Template;
import com.hoping.owl.flymock.object.TemplateMock;
import com.hoping.owl.flymock.rule.Rule;
import org.junit.Test;

/**
 * Created by houping wang on 2020/4/1
 *
 * @author houping wang
 */
public class ObjectMockRuleTest {

    @Test
    public void test() {
        Rule rule = new Rule();
        rule.putContainRule("userId", "1");
        rule.putContainRule("name", "@cname()");
        rule.putContainRule("time", "@datetime()");
        rule.putEqualRules("mobile", "19442615230");
        rule.putContainRule("url", "@url()");
        rule.putContainRule("email", "@email()");
        Template<User> template = TemplateMock.template(new TypeReference<User>() {}, rule);
        User user = template.mockType();
        String originTemplate = template.getOriginTemplate();
        System.out.println(originTemplate);
        String s = template.mockToJson();
        System.out.println(s);
    }

    public static class User {

        private Integer userId;

        private String account;

        private String password;

        private String name;

        private String otherAccount;

        private String createdTime;

        private String modifiedTime;

        private String idNumber;

        private Integer serviceState;

        private Integer type;

        private String mobile;

        private Integer state;

        private String urlPic;

        private String myEmail;

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOtherAccount() {
            return otherAccount;
        }

        public void setOtherAccount(String otherAccount) {
            this.otherAccount = otherAccount;
        }

        public String getCreatedTime() {
            return createdTime;
        }

        public void setCreatedTime(String createdTime) {
            this.createdTime = createdTime;
        }

        public String getModifiedTime() {
            return modifiedTime;
        }

        public void setModifiedTime(String modifiedTime) {
            this.modifiedTime = modifiedTime;
        }

        public String getIdNumber() {
            return idNumber;
        }

        public void setIdNumber(String idNumber) {
            this.idNumber = idNumber;
        }

        public Integer getServiceState() {
            return serviceState;
        }

        public void setServiceState(Integer serviceState) {
            this.serviceState = serviceState;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public Integer getState() {
            return state;
        }

        public void setState(Integer state) {
            this.state = state;
        }

        public String getUrlPic() {
            return urlPic;
        }

        public void setUrlPic(String urlPic) {
            this.urlPic = urlPic;
        }

        public String getMyEmail() {
            return myEmail;
        }

        public void setMyEmail(String myEmail) {
            this.myEmail = myEmail;
        }
    }
}
