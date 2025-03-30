package com.bfox.xunbao.framework.view;

import com.bfox.xunbao.framework.entity.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 订单信息
 * @Author Eden
 * @Date 2025/3/30 22:10
 */
@Getter
@Setter
@NoArgsConstructor
public class OrderInfoView extends OrderView {

    private static final long serialVersionUID = 1L;

    /**
     * 奖励
     */
    private BigDecimal bonus;

    /**
     * 保证金(保证金最大是预算的双倍)
     */
    private BigDecimal bond;

    /**
     * 联系方式(文本)
     * QQ:1234566 / 微信:5878552
     */
    private String contactText;

    /**
     * 联系方式(微信二维码)
     */
    private String contact;

    /**
     * 图片
     */
    private String url;

    /**
     * 开发语言
     */
    private String develop;

    /**
     * 文案
     */
    private String description;

    public OrderInfoView (Order order) {
        this.id = order.getId();
        this.url = order.getUrl();
        this.name = order.getName();
        this.cycle = order.getCycle();
        this.status = order.getStatus();
        this.type = order.getType();
        this.bonus = order.getBonus();
        this.bond = order.getBond();
        this.contact = order.getContact();
        this.serialNumber = order.getSerialNumber();
        this.publishTime = order.getPublishTime();
        this.contactText = order.getContactText();
        this.description = order.getDescription();
    }
}
