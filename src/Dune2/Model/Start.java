package Dune2.Model;
import Dune2.Sound.*;
import Dune2.View.*;

import javax.swing.*;
import becker.util.Utilities;
//import becker.util.Test;
import java.awt.Image;
import java.awt.Color;
import java.awt.*;

public class Start
{  private static GraphicsDevice gd;

  public static void main(String[] args)
  {  JFrame frame = new JFrame(Constants.PROGRAM_NAME);
     frame.setSize(800,600);
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

     ImageInformation imgInfo = new ImageInformation();
     SoundInformation soundInfo = new SoundInformation();
     World theWorld = new World(imgInfo, soundInfo, frame);
     JPanel view = new TopLevelView(theWorld, imgInfo);
     frame.setContentPane(view);

     //BuildingView buildingView = new BuildingView(theWorld, imgInfo);
     //MainMenuView mainView = new MainMenuView(theWorld, imgInfo);

     MenuFrame menu = new MenuFrame(theWorld);

     frame.setJMenuBar(menu);

     frame.setVisible(false);
     //GameThread gT = new GameThread(theWorld);

     theWorld.setAtMainMenu(); //displays main menu screen

     //System.out.println("full screen");

     //gd.setFullScreenWindow(frame);
     //full screen attempt...didn't go well --> doesn't work...whatever
  }
} 
