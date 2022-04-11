package com.Main;

import com.Main.tile.TileManager;
import entity.Player;
import object.SuperObject;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    //SCREEN SETTINGS
    final int originalTileSize = 16; //16x16 tile
    final int scale = 3;

    public int tileSize = originalTileSize * scale; //48x48 tile
    public int maxScreenCol = 16;
    public int maxScreenRow = 12;
    public int screenWidth = tileSize * maxScreenCol; //768 pixels
    public int screenHeight = tileSize * maxScreenRow; //576 pixels

    //WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    //FPS (TURN ON VSYNC)
    int FPS = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler(this);
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[10];

    /* DISABLE FOR NEW GAMEPLAY
    Set player's default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;
     */

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame(){
        aSetter.setObject();
    }
    public void zoomInOut(int i){

        int oldWorldWidth = tileSize * maxWorldCol; //2400 1200 = 0.5
        tileSize += i;
        int newWorldWidth = tileSize * maxWorldCol; //2350

        player.speed = (double) newWorldWidth/600;

        double multiplier = (double)newWorldWidth/oldWorldWidth;

        System.out.println("tileSize " + tileSize);
        System.out.println("worldWidth " + newWorldWidth);
        System.out.println("player worldX " + player.worldX);

        double newPlayerWorldX = player.worldX * multiplier;
        double newPlayerWorldY = player.worldY * multiplier;

        player.worldX = newPlayerWorldX;
        player.worldY = newPlayerWorldY;

    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    /* DISABLE FOR NEW GAMEPLAY
    public void run() {

        double drawInterval = 1000000000/FPS; //~0.016666 seconds
        double nextDrawTime = System.nanoTime() + drawInterval;


        while (gameThread != null){

            // 1.UPDATE : update information such as character positions
            update();
            // 2.DRAW   : draw the screen with the updated information
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if(remainingTime < 0){
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    */
    //CAN DISABLE FOR OLD GAMEPLAY
    public void run(){
        double drawInterval = 1000000000/FPS; // ~0.016666 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += currentTime - lastTime;
            lastTime = currentTime;

            if(delta >= 1){
                update();
                repaint();
                delta --;
                drawCount ++;
            }
            // VSYNC ʙᴇᴛᴀ
            if(timer >= 1000000000){
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }
    public void update(){
        player.update();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        //TILE
        tileM.draw(g2);
        //OBJECT
        for(int i = 0; i < obj.length; i++){
            if(obj[i] != null){
                obj[i].draw(g2, this);
            }
        }
        //PLAYER
        player.draw(g2);
        g2.dispose();
    }
}
//FlatEngine2.0.220410 OBJECTION

