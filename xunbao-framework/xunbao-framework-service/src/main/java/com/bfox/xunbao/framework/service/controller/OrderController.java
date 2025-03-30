package com.bfox.xunbao.framework.service.controller;


import com.bfox.xunbao.common.core.P;
import com.bfox.xunbao.common.core.R;
import com.bfox.xunbao.common.core.S;
import com.bfox.xunbao.common.core.utils.AESBUtil;
import com.bfox.xunbao.framework.i.service.OrderService;
import com.bfox.xunbao.framework.model.OrderModel;
import com.bfox.xunbao.framework.view.OrderInfoView;
import com.bfox.xunbao.framework.view.OrderView;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 小程序任务订单控制器(援军令)
 * @Author Eden
 * @Date 2025/3/26 21:53
 */
@Slf4j
@RestController
@RequestMapping("/framework/order")
public class OrderController {

    @Autowired
    private OrderService orderService;



    /**
     * 数据列表
     * @param model
     * @return
     */
    @PostMapping("/list")
    public R list(@RequestBody OrderModel model) {
        P<OrderView> dataList = this.orderService.getDataList(model);
        return R.ok(dataList);
    }

    /**
     * 详细数据
     * @param id
     * @return
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") String id) {
        String decrypt = AESBUtil.decrypt(id);
        if(StringUtils.isBlank(decrypt)) {
            log.info("前端传递参数id:{}", id);
            return R.error(S.PARAMETER_BOUNDS_ERROR);
        }
        OrderInfoView view = this.orderService.getInfo(Long.valueOf(decrypt));
        return R.ok(view);
    }
}
