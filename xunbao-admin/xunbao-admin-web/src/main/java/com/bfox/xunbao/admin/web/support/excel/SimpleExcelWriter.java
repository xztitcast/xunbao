package com.bfox.xunbao.admin.web.support.excel;

import com.bfox.xunbao.admin.web.entity.SysUser;
import com.bfox.xunbao.admin.web.support.SecuritySubjectManager;
import com.bfox.xunbao.admin.web.support.SpringContextManager;
import com.bfox.xunbao.common.core.LimitModel;
import com.bfox.xunbao.common.core.enums.Activiti;
import com.bfox.xunbao.common.core.enums.BaseEnum;
import com.bfox.xunbao.common.core.injecter.ExcelWriterService;
import com.bfox.xunbao.setup.entity.DataActiviti;
import com.bfox.xunbao.setup.entity.DataCenter;
import com.bfox.xunbao.setup.i.service.DataActivitiService;
import com.bfox.xunbao.setup.i.service.DataCenterService;

import java.util.Date;

/**
 * Excel数据写入实现
 * @author eden
 * @date 2024/8/10 21:29:29
 */
public final class SimpleExcelWriter<T extends LimitModel, R> extends AbstractExcelWriter<T, R> {

    private final DataCenterService dataCenterService;

    private final DataActivitiService dataActivitiService;

    public SimpleExcelWriter(ExcelWriterService<T, R> excelWriterService, T model, Class<R> entityClass) {
        super(new RootExcelWriter<>(excelWriterService, model, entityClass));
        this.dataCenterService = SpringContextManager.getBean(DataCenterService.class);
        this.dataActivitiService = SpringContextManager.getBean(DataActivitiService.class);
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
        DataCenter dataCenter = new DataCenter();
        dataCenter.setFileName(context.getFileName().replace(".xlsx", ".zip"));
        dataCenter.setPath(context.getAttribute().toString());
        dataCenter.setStatus(BaseEnum.ONE);
        dataCenter.setSign(context.getHash());
        dataCenter.setTenantId(subject.getTenantId());
        dataCenter.setTenantName(subject.getTenantName());
        dataCenter.setCreator(subject.getId());
        dataCenter.setCreateName(subject.getUsername());
        dataCenter.setCreated(new Date());
        Long id = dataCenterService.saveEntity(dataCenter);

        DataActiviti dataActiviti = new DataActiviti();
        dataActiviti.setDataId(id);
        dataActiviti.setName(dataCenter.getFileName());
        dataActiviti.setType(Activiti.APPROVAL_FLOW_TYPE_FILE.getType());
        dataActiviti.setTypeName(Activiti.APPROVAL_FLOW_TYPE_FILE.getName());
        dataActiviti.setStatus(BaseEnum.ONE);
        dataActiviti.setTenantId(subject.getTenantId());
        dataActiviti.setTenantName(subject.getTenantName());
        dataActiviti.setCreator(subject.getId());
        dataActiviti.setCreateName(subject.getUsername());
        dataActiviti.setCreated(new Date());

        dataActivitiService.saveEntity(dataActiviti);
    }
}
