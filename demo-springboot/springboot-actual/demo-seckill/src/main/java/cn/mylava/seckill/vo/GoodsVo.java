package cn.mylava.seckill.vo;


import cn.mylava.seckill.bean.Goods;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;




/**
 * comment: 合并goods和seckillGoods的字段，方便显示
 *
 * @author: lipengfei
 * @date: 27/08/2018
 * @company: (C) Copyright 58BTC 2018
 * @since: JDK 1.8
 * @description:
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GoodsVo extends Goods {

    private BigDecimal seckillPrice;

    private Integer stockCount;

    private Date startDate;

    private Date endDate;
}
