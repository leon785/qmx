package homework.oop1.interfaces;

import java.math.BigDecimal;

/**
 * 额度管理接口
 * 定义额度管理的基本操作
 */
public interface LimitManager {
    
    /**
     * 获取当前可用额度
     * @return 当前可用额度
     */
    BigDecimal getAvailableLimit();
    
    /**
     * 获取信用额度
     * @return 信用额度
     */
    BigDecimal getCreditLimit();
    
    /**
     * 消费
     * @param amount 消费金额
     * @return 是否消费成功
     */
    boolean consume(BigDecimal amount);
    
    /**
     * 还款
     * @param amount 还款金额
     */
    void repay(BigDecimal amount);
    
    /**
     * 检查是否有足够额度
     * @param amount 需要检查的金额
     * @return 是否有足够额度
     */
    boolean hasSufficientLimit(BigDecimal amount);
} 