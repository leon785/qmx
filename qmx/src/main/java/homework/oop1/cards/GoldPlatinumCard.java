package homework.oop1.cards;

import homework.oop1.abstracts.Card;
import homework.oop1.exception.InsufficientBalanceException;
import homework.oop1.interfaces.LimitManager;
import homework.oop1.interfaces.PointManager;
import homework.oop1.management.SharedBalanceManager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 金普卡
 * 具有积分功能，与商务卡共享额度
 */
public class GoldPlatinumCard extends Card implements PointManager {
    
    private SharedBalanceManager sharedManager;
    private int currentPoints;
    private final int pointRate;
    
    public GoldPlatinumCard(String cardNumber, BigDecimal sharedCreditLimit) {
        super(cardNumber);
        this.sharedManager = SharedBalanceManager.getInstance(sharedCreditLimit);
        this.currentPoints = 0;
        this.pointRate = 1; // 每消费1元获得1积分
    }
    
    @Override
    protected List<String> getPrivileges() {
        // 金普卡没有特殊权益
        return new ArrayList<>();
    }
    
    @Override
    public String getCardInfo() {
        return String.format("金普卡，剩余额度：%s，积分：%d", 
            getAvailableLimit(), 
            getCurrentPoints());
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
            // 消费成功后添加积分
            addPointsByConsumption(amount);
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
    
    // PointManager 接口实现
    @Override
    public int getCurrentPoints() {
        return currentPoints;
    }
    
    @Override
    public void addPoints(int points) {
        if (points < 0) {
            throw new IllegalArgumentException("积分不能为负数");
        }
        this.currentPoints += points;
    }
    
    @Override
    public boolean consumePoints(int points) {
        if (points < 0) {
            throw new IllegalArgumentException("消费积分不能为负数");
        }
        if (currentPoints >= points) {
            currentPoints -= points;
            return true;
        }
        return false;
    }
    
    @Override
    public int getPointRate() {
        return pointRate;
    }
    
    @Override
    public void addPointsByConsumption(BigDecimal amount) {
        int earnedPoints = amount.intValue() * pointRate;
        addPoints(earnedPoints);
    }
} 