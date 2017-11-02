package com.findsoft.SpringMVCDemo.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.findsoft.SpringMVCDemo.entity.BabySongInfo;

@Component
public class SongService {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**�޸Ķ���ͬʱɾ��redis�еĻ���
     * �Զ���key��ֻɾ��id��Ӧ�Ļ�����󣬷����ɾ��ȫ�����棬����������Ҫ��
     *
     * @param id
     * @param name
     * @return
     */
    @CacheEvict(value="getTheSong",key="#p0+'song'")
    public int updateBabySong(int id, String name) {
        int result = 0;
        String sql = "update baby_song_info set name=? where id= ?";
        result = jdbcTemplate.update(sql, new Object[] { name, id });

        return result;
    }
    
    @CacheEvict(value="getTheSong",key="#p0+'song'")
    public int deleteTheSong(int id) {
        int result = 0;
        String sql = "delete from  baby_song_info  where id= ?";
        result = jdbcTemplate.update(sql, new Object[] { id });
        return result;
    }
    public int addNewBabySong(String songName, String authorName, String singer, String country) {
        int result = 0;
        String sql = "insert into baby_song_info(name,author,singer,country,is_foreign,is_recommend,file_url,cover_img_url,tdesc,type) values(?,?,?,?,'0','1','','','','1')";
        result = jdbcTemplate.update(sql, new Object[] { songName, authorName, singer, country });
        return result;
    }

    /**
     * ����ʹ��ע�����뻺�棬Ҳ����ʹ��redisTemplate,��������Ҫ̫ˬ��
     * ���ܵ�������key��ʧЧʱ�䣬����Ĭ��ʧЧʱ�������
     * @return
     */
    public List<BabySongInfo> getSongList() {
        String cacheKey="babySongList";
        @SuppressWarnings("unchecked")
        List<BabySongInfo>result=(List<BabySongInfo>) redisTemplate.opsForValue().get(cacheKey);
        if(result==null){
            result = new ArrayList<BabySongInfo>();
            String sql = "select * from baby_song_info  order by create_date desc";
            List list = jdbcTemplate.queryForList(sql);
            System.out.println("query songList from db");
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    Map map = (Map) list.get(i);
                    BabySongInfo babySongInfo = convertMap2Object(map);
                    result.add(babySongInfo);
                }
            }
            redisTemplate.opsForValue().set("babySongList", result, 1, TimeUnit.MINUTES);
        }
        
        return result;
    }
    /**����id��ȡ���󣬲�����redis�����У��´λ�ȡ��ͬid�Ķ���ʱ��ֱ�Ӵӻ�����ȡ
     * @param id
     * @return
     */
    @Cacheable(value="getTheSong",key="#p0+'song'")
    public BabySongInfo getBabySongById(int id){
        String sql=" select * from baby_song_info where id=? ";
        Map map=jdbcTemplate.queryForMap(sql, new Object[]{id});
        System.out.println("query from db");
        return this.convertMap2Object(map);
    }
    private BabySongInfo convertMap2Object(Map map) {
        BabySongInfo result = new BabySongInfo();
        Integer bdId = map.get("id") == null ? new Integer(0) : (Integer) map.get("id");
        result.setId(bdId.intValue());
        String name = map.get("name") == null ? "" : map.get("name").toString();
        result.setName(name);
        String author = map.get("author") == null ? "" : map.get("author").toString();
        result.setAuthor(author);
        String singer = map.get("singer") == null ? "" : map.get("singer").toString();
        result.setSinger(singer);
        String country = map.get("country") == null ? "" : map.get("country").toString();
        result.setCountry(country);
        String isForeign = map.get("is_foreign") == null ? "" : map.get("is_foreign").toString();
        result.setIsForeign(isForeign);
        String isRecommend = map.get("is_recommend") == null ? "" : map.get("is_recommend").toString();
        result.setIsRecommend(isRecommend);
        String fileUrl = map.get("file_url") == null ? "" : map.get("file_url").toString();
        result.setFileUrl(fileUrl);
        String coverImgUrl = map.get("cover_img_url") == null ? "" : map.get("cover_img_url").toString();
        result.setCoverImgUrl(coverImgUrl);
        String desc = map.get("desc") == null ? "" : map.get("desc").toString();
        result.setDesc(desc);
        String type = map.get("type") == null ? "" : map.get("type").toString();
        result.setType(type);
        Date createDate = map.get("create_date") == null ? new Date() : (Date) map.get("create_date");
        result.setCreateDate(sdf.format(createDate));
        return result;
    }
}
