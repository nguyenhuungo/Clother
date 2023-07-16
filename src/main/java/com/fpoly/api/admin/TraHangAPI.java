package com.fpoly.api.admin;

import com.fpoly.api.admin.base.BaseController;
import com.fpoly.entity.TraHang;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/tra-hang")
public class TraHangAPI extends BaseController<TraHang,Long> {
}
