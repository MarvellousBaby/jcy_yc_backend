package com.jcy.jcyycback.entity.system;

import com.jcy.jcyycback.common.service.BaseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @description:
 * @author: PanYu
 * @create: 2020-11-06 17:12
 **/
@Data
@Api(value = "香烟查处基类")
public class CigaretteFlow extends BaseEntity {

    @ApiModelProperty(value = "机构id")
    private Integer orgId;
    @ApiModelProperty(value = "查处时间")
    private Date investigateTime;
    @ApiModelProperty(value = "查处地")
    private String investigateAddress;
    @ApiModelProperty(value = "归属地")
    private String cigaretteAttribution;
    @ApiModelProperty(value = "查处香烟条数")
    private Integer number;
    @ApiModelProperty(value = "查处金额")
    private Integer price;


}
