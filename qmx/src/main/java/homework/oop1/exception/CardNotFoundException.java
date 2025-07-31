package homework.oop1.exception;

/**
 * 卡片未找到异常
 * 当查询的卡片不存在时抛出
 */
public class CardNotFoundException extends RuntimeException {
    
    public CardNotFoundException(String message) {
        super(message);
    }
    
    public CardNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
} 