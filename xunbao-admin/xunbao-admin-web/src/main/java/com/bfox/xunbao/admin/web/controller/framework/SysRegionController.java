package com.bfox.xunbao.admin.web.controller.framework;

import com.alibaba.fastjson2.JSON;
import com.bfox.xunbao.admin.web.modelAndView.view.RegionView;
import com.bfox.xunbao.common.core.Constant;
import com.bfox.xunbao.common.core.R;
import com.bfox.xunbao.framework.entity.Region;
import com.bfox.xunbao.framework.i.service.RegionService;
import org.apache.commons.lang.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * <p>
 * 全国行政省市区信息表 前端控制器
 * </p>
 *
 * @author Eden
 * @since 2024-08-01 22:48:47
 */
@RestController
@RequestMapping("/sys/region")
public class SysRegionController {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @DubboReference
    private RegionService regionService;

    /**
     * 查询区域
     * 区域查询为省、市、区，可以选择级别进行查询
     * @param level 级别
     * @return
     */
    @GetMapping("/select")
    public R select(@RequestParam(required = false, defaultValue = "3") Integer level) {
        List<RegionView> dataList = null;
        String redisKey = Constant.RedisKey.SYS_REGION_KEY + level;
        String value = redisTemplate.opsForValue().get(redisKey);
        if(StringUtils.isBlank(value)) {
            List<Region> list = regionService.getRegionList(level);
            dataList = resolveRegionTreeList(list);
            redisTemplate.opsForValue().set(redisKey, JSON.toJSONString(dataList), 60, TimeUnit.MINUTES);
        }else {
            dataList = JSON.parseArray(value, RegionView.class);
        }
        return R.ok(dataList);
    }

    /**
     * 树形转换
     * @param regionList
     * @return
     */
    private List<RegionView> resolveRegionTreeList(final List<Region> regionList) {
        return regionList.stream().filter(r -> r.getPid().equals("0")).map(r -> findNodeTree(regionList, new RegionView(r.getCname(), r.getCid()))).collect(Collectors.toList());
    };

    /**
     * 递归查找子节点
     * @param regionList
     * @param view
     * @return
     */
    private RegionView findNodeTree(final List<Region> regionList, final RegionView view) {
        regionList.stream().filter(r -> r.getPid().equals(view.getValue())).forEach(r -> view.getChildren().add(findNodeTree(regionList, new RegionView(r.getCname(), r.getCid()))));
        return view;
    }
}
