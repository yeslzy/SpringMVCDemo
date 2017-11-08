package com.findsoft.SpringMVCDemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.findsoft.SpringMVCDemo.entity.BabySongInfo;
import com.findsoft.SpringMVCDemo.service.RedisService;
import com.findsoft.SpringMVCDemo.service.SongService;
@Controller
public class HomePageController {
    @Autowired
    private SongService songService;

    @RequestMapping(value = "/homepage", method = RequestMethod.GET)
    public String requestHomePage() {
        return "Home";// 查找Home.jsp
    }

    /**
     * @ResponseBody 返回指定的convert对象，在webconfig类中进行配置
     * @return
     */
    @RequestMapping(value = "/songs", method = RequestMethod.GET)
    public @ResponseBody List<BabySongInfo> getSongList() {
        List<BabySongInfo> result = songService.getSongList();
        return result;
    }

    /**
     * 测试redis集成，不返回任何页面
     */
    @RequestMapping(value = "/redisTest", method = RequestMethod.GET)
    public void RedisTest() {
        
        // String result =stringRedisTemplate.opsForValue().get("name");
        // System.out.println("result "+result);

        // 此处的实体类 BabySongInfo 一定要实现java.io.Serializable,否则不能直接放入redis
        // BabySongInfo info=songService.getBabySongById(10);
        // redisTemplate.opsForValue().set("song", info);

        // BabySongInfo song=(BabySongInfo)
        // redisTemplate.opsForValue().get("song");
        // System.out.println("song is "+song.getName());

        // redisTemplate.delete("song");
    }
}
