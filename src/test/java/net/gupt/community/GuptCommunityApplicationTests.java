package net.gupt.community;

import net.gupt.community.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest

public class GuptCommunityApplicationTests {

    @Autowired
    private StudentService studentService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void TestAes() {
        try {
            String openID = "o4RnF5EVToxNFniYBCm9q4-LlY0o";
            String unionID = "orKlqt613lJDqhmFlJhO3jgxskCI";
            long time = System.currentTimeMillis() + 360000;
            String uuid = UUID.randomUUID().toString();
            String enc = AesUtil.byteToHexString(AesUtil.encrypt(openID + "|" + unionID + "|" + time + "|" + uuid));
            System.out.println("新的Token：" + enc);
            String dec = new String(AesUtil.decrypt(enc), StandardCharsets.UTF_8);
            String timeResult = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    .format(new Date(Long.parseLong(dec.split("\\|")[2])));
            System.out.println("token时间结果：" + timeResult);
            System.out.println("解密：" + dec);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void decryptToken() {
        try {
            String token = "0265a0fa8e8f9ef9aac13bbdd22d9eed975f5f5aeb0f22e7f0d79e66afb1350df01ec2300cf72dd8c99a017c58e1f85074af9af96676baa8f2c4850ea06a12e1cf5ad0983d73c7e66507cc8b7d99a3ca3f89d65dc010211bbc95b922b9530633f170afe52d8fced36264219099869ba3";
            String dec = new String(AesUtil.decrypt(token), StandardCharsets.UTF_8);
            System.out.println("解密：" + dec);
            String openID = dec.split("\\|")[0];
            System.out.println(openID);
            String timeResult = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    .format(new Date(Long.parseLong(dec.split("\\|")[2])));
            System.out.println("token时间结果：" + timeResult);
            System.out.println(studentService.loginByOpenId(openID));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTokenTime() {
        long time = System.currentTimeMillis();
        String timeStartResult = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(new Date(time));
        System.out.println("token时间结果：" + timeStartResult);
        long endTime = System.currentTimeMillis() + 1000 * 60 * 60 * 2;
        String timeEndResult = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(new Date(endTime));
        System.out.println("token时间结果：" + timeEndResult);
    }

}
