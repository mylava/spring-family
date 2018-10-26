package cn.mylava.seckill.dao;

import cn.mylava.seckill.bean.Goods;
import cn.mylava.seckill.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface GoodsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKeyWithBLOBs(Goods record);

    int updateByPrimaryKey(Goods record);

    /**
     * 根据ID查询秒杀商品
     * @return
     */
    @Select("select g.*,sg.seckill_price,sg.stock_count,sg.start_date,sg.end_date " +
            "from seckill_goods sg left join goods g on sg.goods_id = g.id " +
            "where g.id=#{goodsId}")
    GoodsVo getGoodsVoById(long goodsId);
}