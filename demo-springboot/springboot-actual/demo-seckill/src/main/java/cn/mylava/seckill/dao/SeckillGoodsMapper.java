package cn.mylava.seckill.dao;

import cn.mylava.seckill.bean.SeckillGoods;
import cn.mylava.seckill.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface SeckillGoodsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SeckillGoods record);

    int insertSelective(SeckillGoods record);

    SeckillGoods selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SeckillGoods record);

    int updateByPrimaryKey(SeckillGoods record);

    @Update("update seckill_goods set stock_count = stock_count - 1 where goods_id = #{goodsId}")
    int reduceStock(SeckillGoods seckillGoods);

    /**
     * 查询秒杀商品列表
     * @return
     */
    @Select("select g.*,sg.seckill_price,sg.stock_count,sg.start_date,sg.end_date " +
            "from seckill_goods sg left join goods g on sg.goods_id = g.id")
    List<GoodsVo> listSeckillGoods();
}