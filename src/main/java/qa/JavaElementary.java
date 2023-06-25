package qa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Random;

public class JavaElementary {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(JavaElementary.class);
        logger.info("Start program\n");
        logger.info("Math block");
        byte b = (byte) (127 + 1); // было 0111 1111 -> стало 1000 0000 -> это -128, а не 128
        int integer = 5 / 2, i2 = 1 % 2, i3 = 2_000_000_000;
        double d = 0;
        for (int j = 0; j < 10; j++) {
            d += 0.1;
        }
        BigDecimal zero = new BigDecimal(0);
        BigDecimal pointOne = new BigDecimal("0.1");
        for (int i = 0; i < 10; i++) {
            zero = zero.add(pointOne);
        }
        logger.info("(byte)(127 + 1) will be equal " + b + " but not 128 (was 0111 1111 -> became 1000 0000)");
        logger.info("5 / 2 will be equal " + integer + " but not 2.5");
        logger.info("1 % 2 will be equal " + i2 + " but not zero");
        logger.info("int " + i3 + " we can write like 2_000_000_000");
        logger.info("double d = 0; d += 0.1 ten times will be equal " + d + " but not 1");
        logger.info("BigDecimal(0); BigDecimal += 0.1 ten times will be equal " + zero + "\n");

        logger.info("Logical block");
        Random random = new Random();
        int sum = 0;
        int x = random.nextInt(10);
        int y = random.nextInt(10);
        sum += x > y ? 5 : x == y ? 3 : 0;
        System.out.println(sum);
        logger.info("sum += x > y ? 5 : x == y ? 3 : 0; -> its like sum = sum + (if x>y then +5, if x==y then +3, else 0)" +
                " random sum is equal " + sum + " now \n");

        logger.info("Floating point with integer block");
        int num = 5;
        double doub = 4.5;
        logger.info("Integer + double automatic cast to double " + (num + doub) + "\n");
        logger.info("End program");

    }
}
