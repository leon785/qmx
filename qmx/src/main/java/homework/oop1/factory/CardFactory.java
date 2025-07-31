package homework.oop1.factory;

import homework.oop1.cards.BlackGoldCard;
import homework.oop1.cards.BusinessCard;
import homework.oop1.cards.GoldPlatinumCard;
import homework.oop1.enums.CardType;

import java.math.BigDecimal;

/**
 * 信用卡工厂类
 * 负责创建不同类型的信用卡
 */
public class CardFactory {
    
    private static int cardNumberCounter = 1;
    
    /**
     * 创建金普卡
     * @param sharedCreditLimit 共享信用额度
     * @return 金普卡实例
     */
    public static GoldPlatinumCard createGoldPlatinumCard(BigDecimal sharedCreditLimit) {
        String cardNumber = generateCardNumber(CardType.GOLD_PLATINUM);
        return new GoldPlatinumCard(cardNumber, sharedCreditLimit);
    }
    
    /**
     * 创建商务卡
     * @param sharedCreditLimit 共享信用额度
     * @return 商务卡实例
     */
    public static BusinessCard createBusinessCard(BigDecimal sharedCreditLimit) {
        String cardNumber = generateCardNumber(CardType.BUSINESS);
        return new BusinessCard(cardNumber, sharedCreditLimit);
    }
    
    /**
     * 创建黑金无限卡
     * @return 黑金无限卡实例
     */
    public static BlackGoldCard createBlackGoldCard() {
        String cardNumber = generateCardNumber(CardType.BLACK_GOLD);
        return new BlackGoldCard(cardNumber);
    }
    
    /**
     * 生成卡片号码
     * @param cardType 卡片类型
     * @return 卡片号码
     */
    private static String generateCardNumber(CardType cardType) {
        String prefix;
        switch (cardType) {
            case GOLD_PLATINUM:
                prefix = "6225";
                break;
            case BUSINESS:
                prefix = "6226";
                break;
            case BLACK_GOLD:
                prefix = "6227";
                break;
            default:
                prefix = "6228";
        }
        
        String cardNumber = prefix + String.format("%012d", cardNumberCounter++);
        return cardNumber;
    }
    
    /**
     * 重置卡片号码计数器（用于测试）
     */
    public static void resetCardNumberCounter() {
        cardNumberCounter = 1;
    }
} 