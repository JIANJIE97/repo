package fun.jianjie.miniorder.test;


import fun.jianjie.miniorder.MiniOrderProductApplication;
import fun.jianjie.miniorder.domain.User;
import fun.jianjie.miniorder.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {MiniOrderProductApplication.class})
public class TestUser {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testFind(){
        User user = userService.findUserByOpenid("oKFP-4wyYvUqFis01gFytWLJhXr8");
        System.out.println(user);
    }

    @Test
    public void testSave(){
        User user = new User();
        user.setOpenid("wfewrvwf");
        userService.saveUser(user);
    }
    @Test
    public void testRedis(){
        System.out.println(redisTemplate.opsForHash().entries("8950638a903b2b6f6baa8ee3822fc5b3"));
    }
}
