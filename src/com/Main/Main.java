package com.Main;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
	    JFrame windows = new JFrame();
        windows.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windows.setResizable(false);
        windows.setTitle("Dungeon Adventure 2D" + " " + "Build 1120" + " " + "\uD83C\uDDE9\uD83C\uDDEA\uD83C\uDDFB\uD83C\uDDEA\uD83C\uDDF1\uD83C\uDDF4\uD83C\uDDF5\uD83C\uDDEA\uD83C\uDDF7");

        GamePanel gamePanel = new GamePanel();
        windows.add(gamePanel);

        windows.pack();

        windows.setLocationRelativeTo(null);
        windows.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
}
//Build_On_Java_SDK17//version:0.1.1097//BasedOn:FlatEngine2.0+&IntellijIDEA2021.3.3//Dawn.AllCopyrightsReserved
//opensourceGame//PleaseReportBugsAt:"hoangbinhminh2508@gmail.com"
/*
* allVersionWasCreatedOn: 1021 Legacy
* previousBuildWas: 1097 ʙᴇᴛᴀ
* nextBuildWas: 1120 🇩🇪🇻🇪🇱🇴🇵🇪🇷
* Zoom IN & OUT was disable because bugs' performance
* deadlineStartOn:4/4/2022
* deadlineEndOn:1/5/2022
* 30% DONE + 0% DLC (May not have)
* TODO: Create a base game with inventory, stamina and monsters (Build 1263 Early Access)
*/
