package com.guigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.parser.JSONToken;
import com.guigu.constant.MessageConstant;
import com.guigu.constant.RedisConstant;
import com.guigu.entity.PageResult;
import com.guigu.entity.QueryPageBean;
import com.guigu.entity.Result;
import com.guigu.pojo.SetMeal;
import com.guigu.service.SetmealService;
import com.guigu.utils.QiNiuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/setmeal")
public class SetmealController {
    @Reference
    private SetmealService setmealService;
//    /**
//     * 套餐上传图片
//     * @param imgFile
//     * @return
//     */
//    @PostMapping("/upload")
//    public Result upload(MultipartFile imgFile){
////- 获取原有图片名称，截取到后缀名
//        String originalFilename = imgFile.getOriginalFilename();
//        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
////- 生成唯一文件名，拼接后缀名
//        String filename = UUID.randomUUID() + extension;
////- 调用七牛上传文件
//        try {
//            QiNiuUtils.uploadViaByte(imgFile.getBytes(), filename);
////- 返回数据给页面
////{
//// flag:
//// message:
//// data:{
//// imgName: 图片名,
//// domain: QiNiuUtils.DOMAIN
//// }
//            //}
//            Map<String,String> map = new HashMap<String,String>();
//            map.put("imgName",filename);
//            map.put("domain", QiNiuUtils.DOMAIN);
//            return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS,map);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
//    }
//    @PostMapping("/add")
//    public Result add(@RequestBody SetMeal setmeal, Integer[] checkgroupIds){
//// 调用业务服务添加
//        setmealService.add(setmeal, checkgroupIds);
//// 响应结果
//        return new Result(true, MessageConstant.ADD_SETMEAL_SUCCESS);
//    }
    @Autowired
    private JedisPool jedisPool;
    //图片上传
    @RequestMapping("/upload")
    public Result upload2(@RequestParam("imgFile")MultipartFile imgFile){
        try{
//获取原始文件名
            String originalFilename = imgFile.getOriginalFilename();
            int lastIndexOf = originalFilename.lastIndexOf(".");
//获取文件后缀
            String suffix = originalFilename.substring(lastIndexOf);
//使用UUID随机产生文件名称，防止同名文件覆盖
            String fileName = UUID.randomUUID().toString() + suffix;
            QiNiuUtils.uploadViaByte(imgFile.getBytes(),fileName);
//图片上传成功
            Result result = new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS,fileName);
//将上传图片名称存入Redis，基于Redis的Set集合存储
            Jedis jedis = jedisPool.getResource();
            jedis.sadd(RedisConstant.SETMEAL_PIC_RESOURCES,fileName);
            jedis.close();
            return result;
        }catch (Exception e){
            e.printStackTrace();
//图片上传失败
            return new Result(false,MessageConstant.PIC_UPLOAD_FAIL);
        }
    }
    /**
     * 添加
     */
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('SETMEAL_ADD')")
    public Result add(@RequestBody SetMeal setmeal, Integer[] checkgroupIds){
// 调用服务更新
        setmealService.add(setmeal, checkgroupIds);
// 添加成功，要记录有用的图片到redis集合中
        Jedis jedis = jedisPool.getResource();
        jedis.sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES, setmeal.getImg());
        jedis.close();
        return new Result(true, MessageConstant.ADD_SETMEAL_SUCCESS);
    }
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('SETMEAL_EDIT')")
    public Result update(@RequestBody SetMeal setmeal, Integer[] checkgroupIds){
// 获取原有图片的名称，判断图片是否更改了，如果更改了，那么旧的图片应该从有用的集合中移除
        SetMeal setmealInDb = setmealService.findById(setmeal.getId());
// 调用服务更新
        setmealService.update(setmeal, checkgroupIds);
        Jedis jedis = jedisPool.getResource();
// 判断是否是需要从有用的集合中删除
        if(!setmealInDb.getImg().equals(setmeal.getImg())){
//图片修改了，旧的就没用，就要删除
            jedis.srem(RedisConstant.SETMEAL_PIC_DB_RESOURCES, setmealInDb.getImg());
        }
// 修改成功，要记录有用的图片到redis集合中
        jedis.sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES, setmeal.getImg());
        jedis.close();
        return new Result(true, MessageConstant.EDIT_SETMEAL_SUCCESS);
    }
    // 删除套餐
 /*
* 删除
*/
    @GetMapping("/delete")
    @PreAuthorize("hasAuthority('SETMEAL_DELETE')")
    public Result deleteById(int id){
// 查一下图片名称
        SetMeal setmeal = setmealService.findById(id);
// 调用业务服务删除
        setmealService.deleteById(id);
// 从redis，保存了数据库存放的图片集合中移除这个被删除的图片
        Jedis jedis = jedisPool.getResource();
        jedis.srem(RedisConstant.SETMEAL_PIC_DB_RESOURCES, setmeal.getImg());
        jedis.close();
        return new Result(true, MessageConstant.DELETE_SETMEAL_SUCCESS);
    }
    /**
     * 分页查询
     */
    @PostMapping("/findPage")
    @PreAuthorize("hasAuthority('SETMEAL_QUERY')")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
// 调用服务分页查询
        PageResult<SetMeal> pageResult = setmealService.findPage(queryPageBean);
        return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS,pageResult);
    }
    /**
     * 通过id查询套餐信息
     */
    @GetMapping("/findById")
    public Result findById(int id){
// 调用服务查询
        SetMeal setmeal = setmealService.findById(id);
// 前端要显示图片需要全路径
// 封装到map中，解决图片路径问题
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("setmeal", setmeal); // formData
        resultMap.put("domain", QiNiuUtils.DOMAIN); // domain
        return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS,resultMap);
    }
    /**
     * 通过id查询选中的检查组ids
     */
    @GetMapping("/findCheckgroupIdsBySetmealId")
    public Result findCheckgroupIdsBySetmealId(int id){
        List<Integer> checkgroupIds = setmealService.findCheckgroupIdsBySetmealId(id);
        return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkgroupIds);
    }

}
