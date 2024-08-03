package com.bfox.xunbao.admin.web.modelAndView.view;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 区域选择器
 * @author eden
 * @date 2024/8/2 21:38:38
 */
@Getter
@Setter
public class RegionView extends SelectorView<String> {

    private List<RegionView> children = new ArrayList<>();

    public RegionView() {

    }

    public RegionView(String label, String value) {
        this(label, value, false);
    }

    public RegionView(String label, String value, boolean disabled) {
        super(label, value, disabled);
    }
}
