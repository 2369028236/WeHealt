package com.guigu.job;

import com.alibaba.dubbo.config.annotation.Reference;
import com.guigu.constant.RedisConstant;
import com.guigu.service.SetmealService;
import com.guigu.utils.QiNiuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Set;

@Component("cleanImgJob")
public class ClearImgJob {
    @Autowired
    private JedisPool jedisPool;
    /**
     * 清理垃圾图片的执行方法
     */
    public void clearImg(){
// 获取 redis的连接
        Jedis jedis = jedisPool.getResource();
// 计算2个set集合的差集 所有七牛图片-保存到数据库
// 需要删除的图片
        Set<String> need2Delete = jedis.sdiff(RedisConstant.SETMEAL_PIC_RESOURCES,
                RedisConstant.SETMEAL_PIC_DB_RESOURCES);
// 调用七牛删除
        QiNiuUtils.removeFiles(need2Delete.toArray(new String[]{}));
// 删除redis上的图片, 两边的图片已经同步了
        jedis.del(RedisConstant.SETMEAL_PIC_RESOURCES, RedisConstant.SETMEAL_PIC_DB_RESOURCES);
    }
    /**
     * 订阅服务
     */
    @Reference
    private SetmealService setmealService;
    public void cleanImg(){
// 查出7牛上的所有图片
        List<String> imgIn7Niu = QiNiuUtils.listFile();
// 查出数据库中的所有图片
        List<String> imgInDb = setmealService.findImgs();
// 7牛的-数据库的 imgIn7Niu剩下的就是要删除的
// imgIn7Niu.size(7) - 3 = imgIn7Niu.size(4)
// imgInDb里的对象在 imgIn7Niu 能找到就把这删除
        imgIn7Niu.removeAll(imgInDb);
// 删除7牛上的垃圾图片
        String[] strings = imgIn7Niu.toArray(new String[]{});
        QiNiuUtils.removeFiles(strings);
    }
}
