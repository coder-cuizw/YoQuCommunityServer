package net.gupt.community;

import net.gupt.community.entity.Student;
import net.gupt.community.service.StudentService;
import net.gupt.community.util.AesUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

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
    public void TestRedis() {
        Jedis jedis = new Jedis("119.3.181.96", 6379);
        jedis.auth("guptcommunity");
        jedis.close();
    }

    @Test
    public void TestAes() {
        try {
            String openID = "o4RnF5BXpvp-q00_L5L76_TFww5M";
            String unionID = "orKlqt_vbO7sA97phlgcsPF5v720";
            long time = System.currentTimeMillis();
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
            String token = "0265a0fa8e8f9ef9aac13bbdd22d9eed975f5f5aeb0f22e7f0d79e66afb1350df01ec2300cf72dd8c99a017c58e1f8503e27f3aa1748abdadeefa7d3541dc6196ac17b721939f8e3ebf34f1975ca380a4f6afc4d349df316a7301dd0d2456f1bbefd9f42f332dde762895a3ee44d4e13";
            String dec = new String(AesUtil.decrypt(token), StandardCharsets.UTF_8);
            System.out.println("解密：" + dec);
//            System.out.println(studentService.loginByOpenId("orKlqt1A32ZBJv_pg9HYgFkrQDfc"));
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
