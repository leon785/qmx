package homework.oop1.cards;

import homework.oop1.abstracts.Card;
import homework.oop1.enums.Privilege;
import homework.oop1.interfaces.LimitManager;
import homework.oop1.interfaces.PointManager;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * 黑金无限卡
 * 具有高积分倍率，独立额度
 */
public class BlackGoldCard extends Card implements PointManager {
    
    private BigDecimal usedAmount;
    private int currentPoints;
    private final int pointRate;
    
    public BlackGoldCard(String cardNumber) {
        super(cardNumber);
        this.usedAmount = BigDecimal.ZERO;
        this.currentPoints = 0;
        this.pointRate = 100; // 每消费1元获得100积分
    }
    
    @Override
    protected List<String> getPrivileges() {
        return Arrays.asList(
            Privilege.AIRPORT_LOUNGE.getDisplayName(),
            Privilege.DOMESTIC_TRANSFER.getDisplayName()
        );
    }
    
    @Override
    public String getCardInfo() {
        return String.format("黑金无限卡，剩余额度：无限，积分：%d", getCurrentPoints());
    }
    
    // LimitManager 接口实现
    @Override
    public BigDecimal getAvailableLimit() {
        return new BigDecimal(Integer.MAX_VALUE); // 无限额度
    }
    
    @Override
    public BigDecimal getCreditLimit() {
        return new BigDecimal(Integer.MAX_VALUE); // 无限额度
    }
    
    @Override
    public boolean consume(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("消费金额必须大于0");
        }
        
        // 黑金卡额度无限，直接消费
        usedAmount = usedAmount.add(amount);
        // 消费成功后添加积分
        addPointsByConsumption(amount);
        return true;
    }
    
    @Override
    public void repay(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("还款金额必须大于0");
        }
        usedAmount = usedAmount.subtract(amount).max(BigDecimal.ZERO);
    }
    
    @Override
    public boolean hasSufficientLimit(BigDecimal amount) {
        return true; // 黑金卡额度无限
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