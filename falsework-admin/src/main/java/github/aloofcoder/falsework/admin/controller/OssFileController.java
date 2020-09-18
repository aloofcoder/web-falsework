package github.aloofcoder.falsework.admin.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import github.aloofcoder.falsework.admin.config.AliyunOssConfig;
import github.aloofcoder.falsework.common.util.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author hanle
 * @date 2020/9/17 15:15
 */
@RestController
@RequestMapping("/file/oss")
@SecurityRequirement(name = "Authorization")
@Tag(name = "OSS文件管理", description = "OSS文件管理前端控制器")
public class OssFileController {

    @Autowired
    private AliyunOssConfig ossConfig;
    @Autowired
    private OSS ossClient;

    @Operation(summary = "生成阿里云OSS上传签名")
    @GetMapping("/sign")
    public R getOssPutSign() throws UnsupportedEncodingException {
        System.out.println(ossConfig);
        String bucket = ossConfig.getBucket();
        String endpoint = ossConfig.getEndpoint();
        String accessKeyId = ossConfig.getAccessId();

        String host = String.format("https://%1$s.%2$s", bucket, endpoint);
        // callbackUrl为 上传回调服务器的URL，请将下面的IP和Port配置为您自己的真实信息。
//        String callbackUrl = "http://88.88.88.88:8888";
        // 用户上传文件时指定的前缀
        String dir = "website/";

        // 设置签名有效时间
        long expireTime = 30;
        long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
        Date expiration = new Date(expireEndTime);

        // PostObject请求最大可支持的文件大小为5 GB，即CONTENT_LENGTH_RANGE为5*1024*1024*1024。
        // 限制最大上传为10M
        PolicyConditions policyConds = new PolicyConditions();
        policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 10485760);
        policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);


        String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
        byte[] binaryData = postPolicy.getBytes("utf-8");
        String encodedPolicy = BinaryUtil.toBase64String(binaryData);
        String postSignature = ossClient.calculatePostSignature(postPolicy);

        Map<String, String> respMap = new LinkedHashMap<String, String>();
        respMap.put("accessid", accessKeyId);
        respMap.put("policy", encodedPolicy);
        respMap.put("signature", postSignature);
        respMap.put("dir", dir);
        respMap.put("host", host);
        String fileName = UUID.randomUUID().toString().replaceAll("-", "");
        respMap.put("fileName", fileName);
        respMap.put("expire", String.valueOf(expireEndTime / 1000));

        // 设置回调
//        JSONObject jasonCallback = new JSONObject();
//        jasonCallback.put("callbackUrl", callbackUrl);
//        jasonCallback.put("callbackBody",
//                "filename=${object}&size=${size}&mimeType=${mimeType}&height=${imageInfo.height}&width=${imageInfo.width}");
//        jasonCallback.put("callbackBodyType", "application/x-www-form-urlencoded");
//        String base64CallbackBody = BinaryUtil.toBase64String(jasonCallback.toString().getBytes());
//        respMap.put("callback", base64CallbackBody);

        return R.ok().put("data", respMap);
    }
}
