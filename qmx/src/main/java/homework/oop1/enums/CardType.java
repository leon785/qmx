package homework.oop1.enums;

/**
 * 信用卡类型枚举
 */
public enum CardType {
    GOLD_PLATINUM("金普卡"),
    BUSINESS("商务卡"),
    BLACK_GOLD("黑金无限卡");
    
    private final String displayName;
    
    CardType(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    @Override
    public String toString() {
        return displayName;
    }
} 