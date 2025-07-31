package homework.oop1.interfaces;

import java.math.BigDecimal;

/**
 * 积分管理接口
 * 定义积分管理的基本操作
 */
public interface PointManager {
    
    /**
     * 获取当前积分
     * @return 当前积分数量
     */
    int getCurrentPoints();
    
    /**
     * 增加积分
     * @param points 增加的积分数量
     */
    void addPoints(int points);
    
    /**
     * 消费积分
     * @param points 消费的积分数量
     * @return 是否消费成功
     */
    boolean consumePoints(int points);
    
    /**
     * 获取积分倍率
     * @return 积分倍率（每消费1元获得的积分）
     */
    int getPointRate();
    
    /**
     * 根据消费金额计算并添加积分
     * @param amount 消费金额
     */
    void addPointsByConsumption(BigDecimal amount);
} 