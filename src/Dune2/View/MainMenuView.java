package Dune2.View;
import Dune2.Model.*;

import becker.util.IView;
import becker.util.Utilities;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.*;

public class MainMenuView extends JPanel
{  private World model;
   private ImageInformation imageInfo;

   private JFrame holder;

   private int newGameX = 110;
   private int newGameY = 150;
   private int loadGameX = 110;
   private int loadGameY = 170;
   private String newGame = "New Game";
   private String loadGame = "Load Game";
   private int maxLength = 65;
   private int maxHeight = 10;
   private Color newGameColor = Color.white;
   private Color loadGameColor = Color.white;

   public MainMenuView(World theModel, ImageInformation iInfo)
   {  this.model = theModel;
      this.imageInfo = iInfo;

      this.layoutView();
      this.registerListeners();

      //this.model.addView(this);
      this.repaint();
   }

   private void layoutView()
   {  this.setPreferredSize(new Dimension(300, 400));
      this.setBackground(Color.black);
      this.holder = new JFrame(Constants.PROGRAM_NAME);
      this.holder.setResizable(false);
      this.holder.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      this.holder.setSize(new Dimension(300, 400));
      this.holder.getContentPane().add(this);
      this.holder.setVisible(true);
   }

   private void registerListeners()
   {  this.addMouseListener(new ThisMouseListener());
      this.addMouseMotionListener(new ThisMouseMotionListener());
   }

   public void closeView()
   {  this.holder.dispose();
   }

   public void paintComponent(Graphics g)
   {  Graphics2D g2 = (Graphics2D)g;
      g2.setColor(Color.lightGray);
      g2.fillRect(0, 0, 300, 400);
      g2.setColor(this.newGameColor);
      g2.drawString(this.newGame, this.newGameX, this.newGameY);
      g2.setColor(this.loadGameColor);
      g2.drawString(this.loadGame, this.loadGameX, this.loadGameY);
   }

   /*
   public void updateView()
   {  if (this.model.getAtMainMenu())
      {  this.holder.setVisible(true);
         this.repaint();
      } else
      {  this.holder.setVisible(false);
      }
   }
   */

   private class ThisMouseListener implements MouseListener
   {  public void mouseClicked(MouseEvent evt)
      {
      }

      public void mousePressed(MouseEvent evt)
      {  int x = evt.getX();
         int y = evt.getY();

         if (x >= newGameX && x <= (newGameX + maxLength) && y >= newGameY-10 && y <= newGameY)
         {  //new game
            newGameColor = Color.orange;
            loadGameColor = Color.white;
         } else if (x >= loadGameX && x <= (loadGameX + maxLength) && y >= loadGameY-10 && y <= loadGameY)
         {  //load game
            loadGameColor = Color.orange;
            newGameColor = Color.white;
         } else
         {  newGameColor = Color.white;
            loadGameColor = Color.white;
         }
         repaint();
      }

      public void mouseReleased(MouseEvent evt)
      {  int x = evt.getX();
         int y = evt.getY();
         if (x >= newGameX && x <= (newGameX + maxLength) && y >= newGameY-10 && y <= newGameY)
         {  //new game
            newGameColor = Color.red;
            loadGameColor = Color.white;
            Utilities.sleep(500);
            JFileChooser loadsave = new JFileChooser(Constants.MAPS_PATH);
            Dune2FileFilter loadFilter = new Dune2FileFilter(false, true, false);
            loadsave.setFileFilter(loadFilter);


            JPanel p = new JPanel();
            loadsave.rescanCurrentDirectory();
            int loadOption = loadsave.showOpenDialog(p);
            if (loadOption == JFileChooser.APPROVE_OPTION)
            {  String path = loadsave.getSelectedFile().getAbsolutePath();
               model.newGame(path);
            }
         } else if (x >= loadGameX && x <= (loadGameX + maxLength) && y >= loadGameY-10 && y <= loadGameY)
         {  //load game
            loadGameColor = Color.red;
            newGameColor = Color.white;
            Utilities.sleep(500);
            JFileChooser loadsave = new JFileChooser(Constants.SAVED_GAME_PATH);
            Dune2FileFilter loadFilter = new Dune2FileFilter(true, false, false);
            loadsave.setFileFilter(loadFilter);


            JPanel p = new JPanel();
            loadsave.rescanCurrentDirectory();
            int loadOption = loadsave.showOpenDialog(p);
            if (loadOption == JFileChooser.APPROVE_OPTION)
            {  String path = loadsave.getSelectedFile().getAbsolutePath();
               model.loadFile(path);
            }
         } else
         {  newGameColor = Color.white;
            loadGameColor = Color.white;
         }

         repaint();
      }

      public void mouseEntered(MouseEvent evt)
      {
      }

      public void mouseExited(MouseEvent evt)
      {
      }
   }

   private class ThisMouseMotionListener implements MouseMotionListener
   {  public void mouseMoved(MouseEvent evt)
      {  int x = evt.getX();
         int y = evt.getY();
         if (x >= newGameX && x <= (newGameX + maxLength) && y >= newGameY-10 && y <= newGameY)
         {  //new game
            newGameColor = Color.red;
            loadGameColor = Color.white;

         } else if (x >= loadGameX && x <= (loadGameX + maxLength) && y >= loadGameY-10 && y <= loadGameY)
         {  //load game
            loadGameColor = Color.red;
            newGameColor = Color.white;

         } else
         {  newGameColor = Color.white;
            loadGameColor = Color.white;
         }

         repaint();
      }

      public void mouseDragged(MouseEvent evt)
      {
      }
   }
}
