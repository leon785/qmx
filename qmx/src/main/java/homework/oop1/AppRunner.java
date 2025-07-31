package homework.oop1;

import homework.oop1.service.CardManagementService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AppRunner {
    
    public List<String> queryAll() {
        List<String> queryResult = new ArrayList<>();

        // 初始化卡片管理服务，共享额度10000元
        CardManagementService cardService = new CardManagementService(new BigDecimal("10000.0"));
        
        try {
            // 需求1：申请金普卡并查询信息
            cardService.applyGoldPlatinumCard();
            
            // 需求2：金普卡消费500.5元，然后申请商务卡
            List<String> userCards = cardService.getUserCards().stream()
                .map(card -> card.getCardNumber())
                .collect(java.util.stream.Collectors.toList());
            String goldCardNumber = userCards.get(0);
            
            // 金普卡消费
            cardService.consumeWithCard(goldCardNumber, new BigDecimal("500.5"));
            
            // 申请商务卡
            cardService.applyBusinessCard();
            
            // 需求3：商务卡消费888.88元
            userCards = cardService.getUserCards().stream()
                .map(card -> card.getCardNumber())
                .collect(java.util.stream.Collectors.toList());
            String businessCardNumber = userCards.get(1);
            
            // 商务卡消费
            cardService.consumeWithCard(businessCardNumber, new BigDecimal("888.88"));
            
            // 需求4：申请黑金无限卡并消费101111元
            cardService.applyBlackGoldCard();
            
            userCards = cardService.getUserCards().stream()
                .map(card -> card.getCardNumber())
                .collect(java.util.stream.Collectors.toList());
            String blackGoldCardNumber = userCards.get(2);
            
            // 黑金卡消费
            cardService.consumeWithCard(blackGoldCardNumber, new BigDecimal("101111.0"));
            
            // 查询所有卡片信息
            queryResult = cardService.getAllCardsInfo();
            
        } catch (Exception e) {
            System.err.println("操作失败：" + e.getMessage());
            e.printStackTrace();
        }

        return queryResult;
    }
}