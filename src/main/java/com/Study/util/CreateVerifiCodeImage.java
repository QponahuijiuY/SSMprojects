package com.Study.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @description: 绘制图片验证码
 * @Author: Mutong
 * @Date: 2019.10.12 23:53
 */
public class CreateVerifiCodeImage {

    private static int WIDTH = 90;//图片高度
    private static int HEIGHT = 35;//图片宽度
    private static int FONT_SIZE = 20;//字符大小
    private static char[] verifiCode;//验证码
    private static BufferedImage verifiCodeImage;//验证图片

    /*
    * 获取验证码图片
    * */
    public static BufferedImage getVerifiCodeImage(){
        verifiCodeImage = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_BGR);
        Graphics graphics = verifiCodeImage.getGraphics();

        verifiCode = generateCheckCode();
        drawBackground(graphics);
        drawRands(graphics, verifiCode);

        graphics.dispose();

        return verifiCodeImage;
    }

    public static char[] getVerifiCode() {
        return verifiCode;
    }

    private static char[] generateCheckCode() {
        String chars = "0123456789abcdefghijklmnopqrstuvwxyz" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] rands = new char[4];
        for (int i = 0; i < 4; i++) {
            int rand = (int) (Math.random() * (10 + 26 * 2));
            rands[i] = chars.charAt(rand);
        }
        return rands;
    }

    private static void drawRands(Graphics g, char[] rands) {
        g.setFont(new Font("Console", Font.BOLD, FONT_SIZE));

        for (int i = 0; i < rands.length; i++) {

            g.setColor(getRandomColor());
            g.drawString("" + rands[i], i * FONT_SIZE + 10, 25);
        }
    }

    private static void drawBackground(Graphics g) {

        g.setColor(Color.white);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // 绘制验证码干扰点
        for (int i = 0; i < 200; i++) {
            int x = (int) (Math.random() * WIDTH);
            int y = (int) (Math.random() * HEIGHT);
            g.setColor(getRandomColor());
            g.drawOval(x, y, 1, 1);

        }
    }

    private static Color getRandomColor() {
        Random ran = new Random();
        return new Color(ran.nextInt(220), ran.nextInt(220), ran.nextInt(220));
    }
}
