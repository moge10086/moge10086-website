package com.moge10086.website.common;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.auth.sts.AssumeRoleRequest;
import com.aliyuncs.auth.sts.AssumeRoleResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author sq
 */
@Component
public class AliyunUtils {
    private static String accessKeyId;
    private static String accessKeySecret;
    private static String endpoint = "sts.cn-hangzhou.aliyuncs.com";
    private static String  roleArn;
    private static String roleSessionName = "SessionTest";
    public static AssumeRoleResponse getUserUploadImageToken() {
        try {
            // regionId表示RAM的地域ID。以华东1（杭州）地域为例，regionID填写为cn-hangzhou。也可以保留默认值，默认值为空字符串（""）。
            String regionId = "";
            // 添加endpoint。适用于Java SDK 3.12.0及以上版本。
            DefaultProfile.addEndpoint("",regionId,"Sts",endpoint);
            // 构造default profile。
            IClientProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
            // 构造client。
            DefaultAcsClient client = new DefaultAcsClient(profile);
            final AssumeRoleRequest request = new AssumeRoleRequest();
            request.setMethod(MethodType.POST);
            request.setRoleArn(roleArn);
            request.setRoleSessionName(roleSessionName);
//            request.setPolicy();
            request.setDurationSeconds(3600L);
            final AssumeRoleResponse response = client.getAcsResponse(request);
//            System.out.println("Expiration: " + response.getCredentials().getExpiration());
//            System.out.println("Access Key Id: " + response.getCredentials().getAccessKeyId());
//            System.out.println("Access Key Secret: " + response.getCredentials().getAccessKeySecret());
//            System.out.println("Security Token: " + response.getCredentials().getSecurityToken());
//            System.out.println("RequestId: " + response.getRequestId());
            return response;
        } catch (ClientException e) {
//            System.out.println("Failed：");
//            System.out.println("Error code: " + e.getErrCode());
//            System.out.println("Error message: " + e.getErrMsg());
//            System.out.println("RequestId: " + e.getRequestId());
        }
        return null;
    }
    @Value("${aliyun.access-key-id}")
    public void setAccessKeyId(String accessKeyId) {
        AliyunUtils.accessKeyId = accessKeyId;
    }
    @Value("${aliyun.access-key-secret}")
    public void setAccessKeySecret(String accessKeySecret) {
        AliyunUtils.accessKeySecret = accessKeySecret;
    }
    @Value("${aliyun.role-arn}")
    public void setRoleArn(String roleArn) {
        AliyunUtils.roleArn = roleArn;
    }

}
