package com.Main;

import entity.Entity;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }

    public void checkTile(Entity entity){
        double entityLeftWorldX = entity.worldX + entity.solidArea.x;
        double entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        double entityTopWorldY = entity.worldY + entity.solidArea.y;
        double entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        double entityLeftCol = entityLeftWorldX/gp.tileSize;
        double entityRightCol = entityRightWorldX/gp.tileSize;
        double entityTopRow = entityTopWorldY/gp.tileSize;
        double entityBottomRow = entityBottomWorldY/ gp.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction){
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[(int) entityLeftCol] [(int) entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[(int) entityRightCol] [(int) entityTopRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[(int) entityLeftCol] [(int) entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[(int) entityRightCol] [(int) entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[(int) entityLeftCol] [(int) entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[(int) entityLeftCol] [(int) entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[(int) entityRightCol] [(int) entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[(int) entityRightCol] [(int) entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
        }
    }
}
