package homework.oop1.management;

import homework.oop1.exception.InsufficientBalanceException;

import java.math.BigDecimal;

/**
 * 共享余额管理器
 * 管理金普卡和商务卡的共享额度
 */
public class SharedBalanceManager {
    
    private static SharedBalanceManager instance;
    private BigDecimal sharedCreditLimit;
    private BigDecimal usedAmount;
    
    private SharedBalanceManager(BigDecimal creditLimit) {
        this.sharedCreditLimit = creditLimit;
        this.usedAmount = BigDecimal.ZERO;
    }
    
    /**
     * 获取单例实例
     * @param creditLimit 共享信用额度
     * @return 共享余额管理器实例
     */
    public static synchronized SharedBalanceManager getInstance(BigDecimal creditLimit) {
        if (instance == null) {
            instance = new SharedBalanceManager(creditLimit);
        }
        return instance;
    }
    
    /**
     * 获取单例实例（不初始化）
     * @return 共享余额管理器实例
     */
    public static SharedBalanceManager getInstance() {
        if (instance == null) {
            throw new IllegalStateException("共享余额管理器尚未初始化");
        }
        return instance;
    }
    
    /**
     * 消费
     * @param amount 消费金额
     * @throws InsufficientBalanceException 余额不足时抛出
     */
    public synchronized void consume(BigDecimal amount) {
        if (usedAmount.add(amount).compareTo(sharedCreditLimit) > 0) {
            throw new InsufficientBalanceException(
                String.format("共享额度不足，当前已使用: %s，可用额度: %s，需要: %s", 
                    usedAmount, sharedCreditLimit.subtract(usedAmount), amount)
            );
        }
        usedAmount = usedAmount.add(amount);
    }
    
    /**
     * 还款
     * @param amount 还款金额
     */
    public synchronized void repay(BigDecimal amount) {
        usedAmount = usedAmount.subtract(amount).max(BigDecimal.ZERO);
    }
    
    /**
     * 获取可用余额
     * @return 可用余额
     */
    public synchronized BigDecimal getAvailableBalance() {
        return sharedCreditLimit.subtract(usedAmount);
    }
    
    /**
     * 获取已使用金额
     * @return 已使用金额
     */
    public synchronized BigDecimal getUsedAmount() {
        return usedAmount;
    }
    
    /**
     * 获取共享信用额度
     * @return 共享信用额度
     */
    public BigDecimal getSharedCreditLimit() {
        return sharedCreditLimit;
    }
    
    /**
     * 重置管理器（用于测试）
     */
    public static synchronized void reset() {
        instance = null;
    }
} 