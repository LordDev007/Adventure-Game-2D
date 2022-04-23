package com.Main;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
	    JFrame windows = new JFrame();
        windows.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windows.setResizable(false);
        windows.setTitle("Dungeon Adventure 2D" + " " + "1146.200" + " " + "ʙᴇᴛᴀ");

        GamePanel gamePanel = new GamePanel();
        windows.add(gamePanel);

        windows.pack();

        windows.setLocationRelativeTo(null);
        windows.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
}
//Build_On_Java_SDK17//version:0.1.1146.200//BasedOn:FlatEngine3.0+&IntellijIDEA2021.3.3//Dawn.AllCopyrightsReserved
//opensourceGame//PleaseReportBugsAt:"hoangbinhminh2508@gmail.com"
/*
* allVersionWasCreatedOn: 1021 Legacy
* previousBuildWas: 1146 ʙᴇᴛᴀ
* nextBuildWas: 1152 🇩🇪🇻🇪🇱🇴🇵🇪🇷
* Zoom IN & OUT was disable because bugs' performance
* deadlineStartOn:4/4/2022
* deadlineEndOn:1/5/2022
* 37% DONE + 0% DLC (May not have)
* TODO: Create a base game with inventory, stamina and monsters (Build 1263 Early Access)
*/
