package homework.oop1.abstracts;

import homework.oop1.interfaces.LimitManager;

import java.math.BigDecimal;
import java.util.List;

/**
 * 抽象信用卡类
 * 只负责卡片的基本属性
 */
public abstract class Card implements LimitManager {
    
    protected String cardNumber;
    
    public Card(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    
    /**
     * 获取卡片信息
     * @return 卡片信息字符串
     */
    public abstract String getCardInfo();
    
    /**
     * 获取卡片详细信息（包括权益）
     * @return 卡片详细信息字符串
     */
    public String getDetailedCardInfo() {
        StringBuilder info = new StringBuilder(getCardInfo());
        
        // 添加权益信息
        List<String> privileges = getPrivileges();
        if (!privileges.isEmpty()) {
            info.append("，权益：").append(String.join("、", privileges));
        }
        
        return info.toString();
    }
    
    /**
     * 获取卡片权益列表
     * @return 权益列表
     */
    protected abstract List<String> getPrivileges();
    
    // Getter
    public String getCardNumber() {
        return cardNumber;
    }

    @Override
    public abstract boolean consume(BigDecimal amount);

    @Override
    public abstract void repay(BigDecimal amount);
} 