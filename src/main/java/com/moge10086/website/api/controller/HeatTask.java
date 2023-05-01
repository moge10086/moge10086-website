package com.moge10086.website.api.controller;

import com.moge10086.website.domain.model.PostHeat;
import com.moge10086.website.domain.vo.post.BasePostVO;
import com.moge10086.website.mapper.PostBaseMapper;
import com.moge10086.website.service.PostShowService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Tag(name = "热度定时任务", description = "热度定时任务")
@RestController
@RequestMapping("heat")
@Validated
public class HeatTask {
    @Resource
    PostShowService postShowService;
    @Resource
    PostBaseMapper postBaseMapper;

    @Operation(summary = "执行热度定时任务", description = "执行热度定时任务,成功则返回true")
    @PostMapping(value = "/calcHeat")
    public Boolean calcHeat() {
        //获取指定时间内的需要计算的post（帖子id，发布时间，浏览量、收藏量、点赞）
        List<BasePostVO> basePosts = postBaseMapper.listPostBase();
        LocalDateTime now = LocalDateTime.now();
        //计算热度
        List<PostHeat> postHeats = basePosts.stream().map(a -> {
            PostHeat postHeat=new PostHeat();
            postHeat.setPostId(a.getPostId());
            postHeat.setUpdateTime(Date.from(now.atZone( ZoneId.systemDefault()).toInstant()));
            LocalDateTime publishTime = a.getUpdateTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            Long hours=ChronoUnit.HOURS.between(publishTime,now);
//        ((浏览量*1+点赞*5+收藏*10+评论*20)+10)/((发布时间-目前时间)^1.5+2)
            Double heat=(1+a.getReadCount()+a.getPraiseCount()* 5L +a.getFavoriteCount()*10+a.getCommentCount()*20)/(Math.pow(hours,1.5)+2);
            postHeat.setHeat(heat);
            return postHeat;
        }).toList();
        //插入/更新热度值
        postBaseMapper.insertHeat(postHeats);
        return true;
    }
}
