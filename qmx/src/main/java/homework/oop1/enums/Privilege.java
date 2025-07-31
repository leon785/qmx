package homework.oop1.enums;

/**
 * 信用卡权益枚举
 */
public enum Privilege {
    AIRPORT_LOUNGE("机场贵宾厅"),
    DOMESTIC_TRANSFER("境内接送机");
    
    private final String displayName;
    
    Privilege(String displayName) {
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