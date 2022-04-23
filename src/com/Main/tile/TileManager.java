package com.Main.tile;

import com.Main.GamePanel;
import com.Main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[] [];

    public TileManager(GamePanel gp){
        this.gp = gp;

        tile = new Tile[10];
        mapTileNum = new int [gp.maxWorldCol] [gp.maxWorldRow];

        getTileImage();
        loadMap("/maps/world01.txt");
    }
    public void getTileImage() {

            setup(0, "grass01", false);
            setup(1, "wall", true);
            setup(2, "water01", true);
            setup(3, "earth", false);
            setup(4, "tree", true);
            setup(5, "sand", false);
    }

    public void setup(int index, String imageName, boolean collision){

        UtilityTool uTool = new UtilityTool();

        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void loadMap(String filePath){
        try{
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow){
                String line = br.readLine();
                while(col < gp.maxWorldCol){
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col] [row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();
        }catch(Exception e){

        }
    }
    public void draw(Graphics2D g2){
        int worldCol = 0;
        int worldRow = 0;

        /* DISABLE FOR STABILITY
        int x = 0;
        int y = 0;
        */

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){
            int tileNum = mapTileNum[worldCol] [worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            double screenX = worldX - gp.player.worldX + gp.player.screenX;
            double screenY = worldY - gp.player.worldY + gp.player.screenY;

            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
               worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
               worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
               worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                g2.drawImage(tile[tileNum].image, (int)screenX, (int)screenY, null);
            }
            worldCol++;
            //x += gp.tileSize; DISABLE FOR STABILITY
            if(worldCol == gp.maxWorldCol){
                worldCol = 0;
                //x = 0; DISABLE FOR STABILITY
                worldRow++;
                //y += gp.tileSize; DISABLE FOR STABILITY

            }
        }

        /* DISABLE FOR STABILITY (WASTE TIME)
        g2.drawImage(tile[1].image,0, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[1].image,48, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[1].image,96, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[1].image,144, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[1].image,192, 0, gp.tileSize, gp.tileSize, null);

        g2.drawImage(tile[1].image,0, 48, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image,48, 48, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image,96, 48, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image,144, 48, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[1].image,192, 48, gp.tileSize, gp.tileSize, null);

        g2.drawImage(tile[1].image,0, 96, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image,48, 96, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image,96, 96, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image,144, 96, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image,192, 96, gp.tileSize, gp.tileSize, null);

        g2.drawImage(tile[1].image,0, 144, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image,48, 144, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image,96, 144, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image,144, 144, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[1].image,192, 144, gp.tileSize, gp.tileSize, null);

        g2.drawImage(tile[1].image,0, 192, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[2].image,48, 192, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[2].image,96, 192, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[2].image,144, 192, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[1].image,192, 192, gp.tileSize, gp.tileSize, null);
        */
    }
}
