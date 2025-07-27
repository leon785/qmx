package homework.javabasic;


public class Main {
    public static void main(String[] args) {
        Shop shop = new Shop();

        System.out.println("=== 小招礼品屋积分购买系统 ===");

        // 1. 程序启动时加载商品信息到内存
        System.out.println("1. 加载商品信息...");
        shop.displayProducts();

        // 2. 初始化用户信息
        System.out.println("\n2. 初始化用户信息...");
        shop.displayUsers();

        // 3. 模拟5个用户争用购买行为
        System.out.println("\n3. 开始模拟购买行为...");
        shop.simulatePurchases();

        // 5. 显示最终状态
        System.out.println("\n=== 最终状态 ===");
        shop.displayProducts();
        shop.displayUsers();

    }

}
