package homework.oop1.exception;

/**
 * 余额不足异常
 * 当卡片余额不足以完成消费时抛出
 */
public class InsufficientBalanceException extends RuntimeException {
    
    public InsufficientBalanceException(String message) {
        super(message);
    }
    
    public InsufficientBalanceException(String message, Throwable cause) {
        super(message, cause);
    }
} 