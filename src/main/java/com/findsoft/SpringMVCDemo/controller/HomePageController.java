package com.findsoft.SpringMVCDemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private  RedisService redisService;
    
    @RequestMapping(value="/homepage",method=RequestMethod.GET)
    public String requestHomePage(){
        return "Home";//查找Home.jsp
    }
    /**@ResponseBody   返回指定的convert对象，在webconfig类中进行配置
     * @return
     */
    @RequestMapping(value="/songs",method=RequestMethod.GET)
    public @ResponseBody List<BabySongInfo> getSongList(){
        List<BabySongInfo> result=songService.getSongList();
        return result;
    }
    /**
     * 测试redis集成，不返回任何页面
     */
    @RequestMapping(value="/redisTest",method=RequestMethod.GET)
    public void RedisTest(){
       String result =redisService.get("name");
       System.out.println("result "+result);
    }
}
