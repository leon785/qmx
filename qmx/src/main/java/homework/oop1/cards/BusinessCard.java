package homework.oop1.cards;

import homework.oop1.abstracts.Card;
import homework.oop1.exception.InsufficientBalanceException;
import homework.oop1.interfaces.LimitManager;
import homework.oop1.management.SharedBalanceManager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 商务卡
 * 无积分功能，与金普卡共享额度
 */
public class BusinessCard extends Card {
    
    private SharedBalanceManager sharedManager;
    
    public BusinessCard(String cardNumber, BigDecimal sharedCreditLimit) {
        super(cardNumber);
        this.sharedManager = SharedBalanceManager.getInstance(sharedCreditLimit);
    }
    
    @Override
    protected List<String> getPrivileges() {
        // 商务卡没有特殊权益
        return new ArrayList<>();
    }
    
    @Override
    public String getCardInfo() {
        return String.format("商务卡，剩余额度：%s，积分：0", 
            getAvailableLimit());
    }
    
    // LimitManager 接口实现
    @Override
    public BigDecimal getAvailableLimit() {
        return sharedManager.getAvailableBalance();
    }
    
    @Override
    public BigDecimal getCreditLimit() {
        return sharedManager.getSharedCreditLimit();
    }
    
    @Override
    public boolean consume(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("消费金额必须大于0");
        }
        
        try {
            sharedManager.consume(amount);
            return true;
        } catch (InsufficientBalanceException e) {
            return false;
        }
    }
    
    @Override
    public void repay(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("还款金额必须大于0");
        }
        sharedManager.repay(amount);
    }
    
    @Override
    public boolean hasSufficientLimit(BigDecimal amount) {
        return sharedManager.getAvailableBalance().compareTo(amount) >= 0;
    }
} 