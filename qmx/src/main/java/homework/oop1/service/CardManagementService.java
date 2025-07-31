package homework.oop1.service;

import homework.oop1.abstracts.Card;
import homework.oop1.cards.BlackGoldCard;
import homework.oop1.cards.BusinessCard;
import homework.oop1.cards.GoldPlatinumCard;
import homework.oop1.exception.CardNotFoundException;
import homework.oop1.factory.CardFactory;
import homework.oop1.management.SharedBalanceManager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 卡片管理服务类
 * 负责管理用户的所有信用卡
 */
public class CardManagementService {
    
    private List<Card> userCards;
    private BigDecimal sharedCreditLimit;
    
    public CardManagementService(BigDecimal sharedCreditLimit) {
        this.userCards = new ArrayList<>();
        this.sharedCreditLimit = sharedCreditLimit;
    }
    
    /**
     * 申请金普卡
     * @return 申请的金普卡
     */
    public GoldPlatinumCard applyGoldPlatinumCard() {
        GoldPlatinumCard card = CardFactory.createGoldPlatinumCard(sharedCreditLimit);
        userCards.add(card);
        return card;
    }
    
    /**
     * 申请商务卡
     * @return 申请的商务卡
     */
    public BusinessCard applyBusinessCard() {
        BusinessCard card = CardFactory.createBusinessCard(sharedCreditLimit);
        userCards.add(card);
        return card;
    }
    
    /**
     * 申请黑金无限卡
     * @return 申请的黑金无限卡
     */
    public BlackGoldCard applyBlackGoldCard() {
        BlackGoldCard card = CardFactory.createBlackGoldCard();
        userCards.add(card);
        return card;
    }
    
    /**
     * 使用指定卡片消费
     * @param cardNumber 卡片号码
     * @param amount 消费金额
     * @return 是否消费成功
     */
    public boolean consumeWithCard(String cardNumber, BigDecimal amount) {
        Card card = findCardByNumber(cardNumber);
        if (card == null) {
            throw new CardNotFoundException("卡片不存在：" + cardNumber);
        }
        return card.consume(amount);
    }
    
    /**
     * 使用指定卡片还款
     * @param cardNumber 卡片号码
     * @param amount 还款金额
     */
    public void repayWithCard(String cardNumber, BigDecimal amount) {
        Card card = findCardByNumber(cardNumber);
        if (card == null) {
            throw new CardNotFoundException("卡片不存在：" + cardNumber);
        }
        card.repay(amount);
    }
    
    /**
     * 查询所有卡片信息
     * @return 所有卡片信息列表
     */
    public List<String> getAllCardsInfo() {
        List<String> cardsInfo = new ArrayList<>();
        for (Card card : userCards) {
            cardsInfo.add(card.getDetailedCardInfo());
        }
        return cardsInfo;
    }
    
    /**
     * 根据卡片号码查找卡片
     * @param cardNumber 卡片号码
     * @return 找到的卡片，如果不存在返回null
     */
    private Card findCardByNumber(String cardNumber) {
        for (Card card : userCards) {
            if (card.getCardNumber().equals(cardNumber)) {
                return card;
            }
        }
        return null;
    }
    
    /**
     * 获取用户所有卡片
     * @return 卡片列表
     */
    public List<Card> getUserCards() {
        return new ArrayList<>(userCards);
    }
    
    /**
     * 获取共享额度信息
     * @return 共享额度信息字符串
     */
    public String getSharedCreditInfo() {
        SharedBalanceManager sharedManager = SharedBalanceManager.getInstance();
        return String.format("共享额度：%s，已使用：%s，剩余：%s", 
            sharedManager.getSharedCreditLimit(),
            sharedManager.getUsedAmount(),
            sharedManager.getAvailableBalance());
    }
    
    /**
     * 重置服务（用于测试）
     */
    public void reset() {
        userCards.clear();
        SharedBalanceManager.reset();
        CardFactory.resetCardNumberCounter();
    }
} 