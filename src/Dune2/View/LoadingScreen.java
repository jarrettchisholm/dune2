package Dune2.View;
import Dune2.Model.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.*;
import becker.util.*;
import becker.io.TextInput;

public class LoadingScreen extends JFrame
{  private ImageInformation imageInfo;
   public static final int NUM_LOADS = 40;
   private JPanel holder;
   private JLabel whatLoading;
   private JTextArea problems;

   public LoadingScreen(ImageInformation iInfo)
   {  this.imageInfo = iInfo;
      this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      this.setTitle("Loading " + Constants.PROGRAM_NAME);
      this.layoutView();


      int numTimes = 0;

      while(!this.imageInfo.prepareAllImages())
      {
         if (this.whatLoading.getText().equals("Loading Images..."))
         {  this.whatLoading.setText("Loading Images");
         } else if (this.whatLoading.getText().equals("Loading Images"))
         {  this.whatLoading.setText("Loading Images.");
         } else if (this.whatLoading.getText().equals("Loading Images."))
         {  this.whatLoading.setText("Loading Images..");
         } else if (this.whatLoading.getText().equals("Loading Images.."))
         {  this.whatLoading.setText("Loading Images...");
         }

         Utilities.sleep(100);

         numTimes++;
         if (numTimes > NUM_LOADS)
         {  break;
         }
      }

      this.whatLoading.setText("DONE");
      Utilities.sleep(1500);

      this.imageInfo.bufferImages();

      this.setVisible(false);
   }

   private void layoutView()
   {  Dimension dim = new Dimension(300, 500);

      this.setSize(dim);
      this.holder = new JPanel();
      this.holder.setPreferredSize(dim);

      this.whatLoading = new JLabel("Loading Images...");
      this.whatLoading.setForeground(Color.darkGray);
      this.whatLoading.setPreferredSize(new Dimension(150, 30));
      //IBETextInput in = new IBETextInput(Constants.DEFAULT_FILES + "Loading.txt");
      String text1 = "                Dune 2 remake \n                  Version 0.21 \n                 TEST EDITION \n\nCHANGES from v0.20: \n-Fixed 'arrayoutofbounds' error for AI unit movement \n-Tweaked unit movement --> Units will now attempt to find a path from a to b if it did not already find a path the first time.  Of course, the unit checks for a path every so many seconds, and after a fixed number of attempts, the unit gives up.  \n-Added events \n-Added 1 condition (IF BRING) \n-Added 3 actions (DISPLAY TEXT, CREATE UNITS, ORDER) \n-Changed background music to actual Dune 2 music \n-Made minor changes to the AI tactical code and constuction code \n\nDEVELOPER NOTES: \n-Fix the null images! \n-Test 'New Game' and 'Load Game' methods.  \n-Update the 'Save Game' method with events \n-Create more elaborate tactical AI";
      String text2 = "\n\nFUTURE ADDITIONS: \n-Add battle scaring (ie. if a tank blows up, have remnants of the tank on the field where it used to be --> maybe even a tank on fire, immovale/impassible for a short period of time) \n-Link many dune midi sequences together, and get some dune 2 battle midis and play them accordingly \n-Add more units \n-Add more sounds\n-FIX HARVESTORS! \n-Create transport units \n-Complete the AI tactical code \n-'Spruce up' the radar view \n-Add many more conditions/actions for events \n-Make a map editor that allows for placement of events, terrain, and units \n-Fix certain button 'disappearing' acts \n-And much, much more!";
      this.problems = new JTextArea(text1 + text2);
      this.problems.setWrapStyleWord(true);
      this.problems.setLineWrap(true);
      this.problems.setBackground(Color.lightGray);
      this.problems.setEditable(false);
      //this.problems.setPreferredSize(new Dimension(150, 200));
      JScrollPane probPane = new JScrollPane(this.problems);
      probPane.setPreferredSize(new Dimension(200, 300));
      this.holder.add(this.whatLoading);
      this.holder.add(probPane);
      this.holder.setBackground(Color.lightGray);

      this.setContentPane(this.holder);
      this.setBackground(Color.black);
      this.setLocation(300, 250);

      this.setVisible(true);
   }
}
