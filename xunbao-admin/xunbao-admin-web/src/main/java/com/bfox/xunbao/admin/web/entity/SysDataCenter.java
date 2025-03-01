package com.bfox.xunbao.admin.web.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.bfox.xunbao.common.mybatis.entity.CreateEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 数据中心表
 * </p>
 *
 * @author Eden
 * @since 2024-09-08 18:49:55
 */
@Getter
@Setter
@TableName("tb_data_center")
public class SysDataCenter extends CreateEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 下载文件名
     */
    private String fileName;

    /**
     * 下载路径
     */
    private String path;

    /**
     * 1待审核 2审核中 3审核成功 4审核驳回
     */
    private Integer status;

    /**
     * 文件签名保证唯一
     */
    private String sign;

    /**
     * 审核驳回详细备注
     */
    private String remark;

}
