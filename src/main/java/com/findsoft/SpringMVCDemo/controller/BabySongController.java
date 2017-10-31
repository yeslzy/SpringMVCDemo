package com.findsoft.SpringMVCDemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.findsoft.SpringMVCDemo.entity.BabySongInfo;
import com.findsoft.SpringMVCDemo.entity.HttpResponseResult;
import com.findsoft.SpringMVCDemo.service.SongService;

@RestController
@RequestMapping(value = "/babysong")
public class BabySongController {
    @Autowired
    private SongService songService;

    @RequestMapping(value = "/songs", method = RequestMethod.GET)
    public List<BabySongInfo> getAllSongs() {
        List<BabySongInfo> result = songService.getSongList();
        return result;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public HttpResponseResult addNewSong(@RequestParam String name) {
        HttpResponseResult result = new HttpResponseResult();
        result.setResultDesc("添加成功");
        result.setResultFlag(1);
        result.setResultObj(name);
        return result;

    }
    @RequestMapping(value="/song/{id}")
    public HttpResponseResult  getOneSong(@PathVariable int id){
        BabySongInfo info=songService.getBabySongById(id);
        HttpResponseResult result = new HttpResponseResult();
        result.setResultDesc("查询id为"+id+"的歌曲成功");
        result.setResultFlag(1);
        result.setResultObj(info);
        return result;
    }
}
