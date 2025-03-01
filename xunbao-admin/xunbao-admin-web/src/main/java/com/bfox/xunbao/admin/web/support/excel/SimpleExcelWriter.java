package com.bfox.xunbao.admin.web.support.excel;

import com.bfox.xunbao.admin.web.entity.SysDataActiviti;
import com.bfox.xunbao.admin.web.entity.SysDataCenter;
import com.bfox.xunbao.admin.web.entity.SysUser;
import com.bfox.xunbao.admin.web.service.SysDataActivitiService;
import com.bfox.xunbao.admin.web.service.SysDataCenterService;
import com.bfox.xunbao.admin.web.support.SecuritySubjectManager;
import com.bfox.xunbao.admin.web.support.SpringContextManager;
import com.bfox.xunbao.common.core.LimitModel;
import com.bfox.xunbao.common.core.enums.Activiti;
import com.bfox.xunbao.common.core.enums.BaseEnum;
import com.bfox.xunbao.common.core.injecter.ExcelWriterService;

import java.util.Date;

/**
 * Excel数据写入实现
 * @author eden
 * @date 2024/8/10 21:29:29
 */
public final class SimpleExcelWriter<T extends LimitModel, R> extends AbstractExcelWriter<T, R> {

    private final SysDataCenterService sysDataCenterService;

    private final SysDataActivitiService sysDataActivitiService;

    public SimpleExcelWriter(ExcelWriterService<T, R> excelWriterService, T model, Class<R> entityClass) {
        super(new RootExcelWriter<>(excelWriterService, model, entityClass));
        this.sysDataCenterService = SpringContextManager.getBean(SysDataCenterService.class);
        this.sysDataActivitiService = SpringContextManager.getBean(SysDataActivitiService.class);
    }

    @Override
    public void write(ExcelContext context) {
        super.write(context);
        this.save(context);
    }

    @Override
    protected void save(ExcelContext context) {
        SysUser subject = SecuritySubjectManager.getSubject();
        // filename入库
        SysDataCenter dataCenter = new SysDataCenter();
        dataCenter.setFileName(context.getFileName().replace(".xlsx", ".zip"));
        dataCenter.setPath(context.getAttribute().toString());
        dataCenter.setStatus(BaseEnum.ONE);
        dataCenter.setSign(context.getHash());
        dataCenter.setCreator(subject.getId());
        dataCenter.setCreateName(subject.getUsername());
        dataCenter.setCreated(new Date());
        sysDataCenterService.save(dataCenter);

        SysDataActiviti dataActiviti = new SysDataActiviti();
        dataActiviti.setDataId(dataCenter.getId());
        dataActiviti.setName(dataCenter.getFileName());
        dataActiviti.setType(Activiti.APPROVAL_FLOW_TYPE_FILE.getType());
        dataActiviti.setTypeName(Activiti.APPROVAL_FLOW_TYPE_FILE.getName());
        dataActiviti.setStatus(BaseEnum.ONE);
        dataActiviti.setCreator(subject.getId());
        dataActiviti.setCreateName(subject.getUsername());
        dataActiviti.setCreated(new Date());

        sysDataActivitiService.save(dataActiviti);
    }
}
