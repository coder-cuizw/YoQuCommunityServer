package net.gupt.community;

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
        Jedis jedis = new Jedis("106.54.133.193", 6379);
        jedis.auth("guptcommunity");
        jedis.close();
    }

    @Test
    public void TestAes() {
        try {
            String openID = "o4RnF5BXpvp-q00_L5L76_TFww5M";
            String unionID = "orKlqt_vbO7sA97phlgcsPF5v720";
           // String openID = "o4RnF5Mf0DwKYn1McGj8lxzz-D5Q";
           // String unionID = "orKlqt1A32ZBJv_pg9HYgFkrQDfc";
            //28天
            long time = System.currentTimeMillis() + 2591506059L;
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
            String token = "c4fa35e37fb31a96181607b5a7c14ea10efa5bfe9d23e78611be85cdc0db08a2234323e6379e4b9850e9b40d973a2e8616f05bc69023604ef956474a68afc8867c783bbac9edc0b7b78bc17990f95e8d65c4af047f4ccc7db7d5a51c26db6f948d92241ca4aa44f580fa6d0b1bcf5d3c";
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

    @Test
    public void testDesc(){
        try {
            String descKey = "7eb74f7d1b8403564e33d2ef4865b4dfcdee5e753bc2a854dbc31053ad2f07c0";
            String dec = new String(AesUtil.decrypt(descKey),StandardCharsets.UTF_8);
            System.out.println("解密后key："+dec);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }
}
