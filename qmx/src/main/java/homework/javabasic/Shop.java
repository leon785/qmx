package homework.javabasic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import homework.javabasic.exception.InsufficientPointsException;
import homework.javabasic.exception.OutOfStockException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Random;


public class Shop {

    private final static int ALL_PRODUCT_MAX = 10;
    private final static int ALL_USER_MAX = 5;
    private final static int MIN_USER_CREDIT = 1000;
    private static final Logger LOGGER = LoggerFactory.getLogger(Shop.class);

    private Map<Integer, Product> allProducts;
    private List<User> allUsers;
    private Random random;

    /**
     * 加载商品，加载用户
     */
    public Shop() {
        this.allProducts = new HashMap<>(ALL_PRODUCT_MAX);
        this.allUsers = new ArrayList<>();
        this.random = new Random();

        allProducts.put(1, new Product(1, "精美礼品盒", 15, 50));
        allProducts.put(2, new Product(2, "定制马克杯", 1200, 30));
        allProducts.put(3, new Product(3, "蓝牙耳机", 2000, 200));
        allProducts.put(4, new Product(4, "智能手表", 1800, 500));
        allProducts.put(5, new Product(5, "无线充电器", 1600, 150));
        allProducts.put(6, new Product(6, "便携充电宝", 1400, 100));
        allProducts.put(7, new Product(7, "蓝牙音箱", 1300, 250));
        allProducts.put(8, new Product(8, "游戏手柄", 1100, 300));
        allProducts.put(9, new Product(9, "机械键盘", 1700, 400));
        allProducts.put(10, new Product(10, "电竞鼠标", 1900, 350));

        for (int i = 1; i <= ALL_USER_MAX; i++) {
            // 每个用户初始积分1000-5000之间随机
            int credits = MIN_USER_CREDIT + random.nextInt(4001);
            allUsers.add(new User(i, credits));
        }
    }

    public void purchase(User user, Product product) throws OutOfStockException, InsufficientPointsException {
        // 对商品对象加锁，保证同一商品的并发安全
        synchronized (product) {
            // 再对用户加锁，防止同一用户积分被多线程同时修改
            synchronized (user) {
                // 双重检查
                if (product.getAmount() <= 0) {
                    throw new OutOfStockException("商品 " + product.getDescription() + " 已售罄");
                }
                if (user.getCreditLeft() < product.getCredit()) {
                    throw new InsufficientPointsException("用户 " + user.getId() + " 积分不足");
                }
                // 扣减库存和积分
                product.setAmount(product.getAmount() - 1);
                user.setCreditLeft(user.getCreditLeft() - product.getCredit());
                user.addPurchased(product);
                LOGGER.info("[并发] 用户 {} 成功购买商品 {}，剩余积分: {}，商品剩余数量: {}",
                        user.getId(), product.getDescription(), user.getCreditLeft(), product.getAmount()
                );
            }
        }
    }

    // 多线程模拟5个用户并发抢购同一商品
    public void simulatePurchases() {
        // 只用前5个用户
        List<User> fiveUsers = allUsers.subList(0, 5);
        int productId = 1; // 所有用户都抢购同一个商品
        Product product = allProducts.get(productId);

        Runnable task = () -> {
            User user = fiveUsers.get(random.nextInt(5));
            try {
                purchase(user, product);
            } catch (Exception e) {
                LOGGER.warn("[并发] 购买失败: {}", e.getMessage());
            }
        };

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 20; i++) { // 共20次抢购
            Thread t = new Thread(task);
            threads.add(t);
            t.start();
        }
        for (Thread t : threads) {
            try { t.join(); } catch (InterruptedException ignored) {}
        }
        LOGGER.info("=== 并发抢购结束 ===");
        LOGGER.info("商品 {} 最终剩余数量: {}", product.getDescription(), product.getAmount());
        for (User user : fiveUsers) {
            LOGGER.info("用户 {} 剩余积分: {}，已购: {}", user.getId(), user.getCreditLeft(), user.getPurchased().size());
        }
    }

    // 展示所有商品信息
    public void displayProducts() {
        LOGGER.info("\n【商品列表】");
        for (Product product : allProducts.values()) {
            LOGGER.info("商品ID: {}, 名称: {}, 剩余数量: {}, 所需积分: {}",
                    product.getId(), product.getDescription(), product.getAmount(), product.getCredit()
            );
        }
    }

    // 展示所有用户信息
    public void displayUsers() {
        LOGGER.info("\n【用户列表】");
        for (User user : allUsers) {
            LOGGER.info("用户ID: {}, 剩余积分: {}, 已购商品数: {}",
                    user.getId(), user.getCreditLeft(), user.getPurchased().size()
            );
        }
    }


}
