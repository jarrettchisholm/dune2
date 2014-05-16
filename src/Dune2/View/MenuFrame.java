package Dune2.View;
import Dune2.Model.*;

import becker.util.IView;
import becker.io.TextInput;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.*;
import java.io.File;

public class MenuFrame extends JMenuBar
{  private JMenu fileMenu;
   private JMenuItem newGame;
   private JMenuItem save;
   private JMenuItem load;
   private JMenuItem main;
   private JMenuItem exit;

   private World model;

   //private JFileChooser loadsave = new JFileChooser(Constants.SAVED_GAME_PATH);

  public MenuFrame(World theModel)
  {  this.model = theModel;
     this.layoutView();
     this.registerListeners();

  }

  private void layoutView()
  {  // FILE menu
      this.fileMenu = new JMenu("File");

      ImageIcon newIcon = new ImageIcon(Constants.IMAGE_PATH + "newImage.GIF");
      this.newGame = new JMenuItem("New Game", newIcon);
      ImageIcon saveIcon = new ImageIcon(Constants.IMAGE_PATH + "saveImage.GIF");
      this.save = new JMenuItem("Save Game", saveIcon);
      ImageIcon loadIcon = new ImageIcon(Constants.IMAGE_PATH + "loadImage.GIF");
      this.load = new JMenuItem("Load Game", loadIcon);
      this.main = new JMenuItem("Main Menu");
      this.exit = new JMenuItem("Exit");

      this.add(this.fileMenu);

      this.fileMenu.add(this.newGame);
      this.fileMenu.add(this.save);
      this.fileMenu.add(this.load);
      this.fileMenu.add(this.main);
      this.fileMenu.addSeparator();
      this.fileMenu.add(this.exit);
  }

  private void registerListeners()
   {  this.load.addMouseListener(new LoadMouseListener());
      this.save.addMouseListener(new SaveMouseListener());
      this.newGame.addMouseListener(new NewGameMouseListener());
      this.fileMenu.addMouseListener(new FileMenuMouseListener());
      this.main.addMouseListener(new MainMouseListener());
      this.exit.addMouseListener(new ExitMouseListener());
   }

   private class SaveMouseListener implements MouseListener
   {  public void mouseClicked(MouseEvent evt)
      {
      }

      public void mousePressed(MouseEvent evt)
      {
      }

      public void mouseReleased(MouseEvent evt)
      {  model.pauseGame();
         JPanel p = new JPanel();

         JFileChooser loadsave = new JFileChooser(Constants.SAVED_GAME_PATH);
         Dune2FileFilter saveFilter = new Dune2FileFilter(false, false, true);
         loadsave.setFileFilter(saveFilter);

         loadsave.rescanCurrentDirectory();
         int loadOption = loadsave.showSaveDialog(p);
         if (loadOption == JFileChooser.APPROVE_OPTION)
         {  String path = loadsave.getSelectedFile().getAbsolutePath();
            model.saveFile(path);
         }
         model.resumeGame();
      }

      public void mouseEntered(MouseEvent evt)
      {
      }

      public void mouseExited(MouseEvent evt)
      {  model.resumeGame();
      }
   }

   private class NewGameMouseListener implements MouseListener
   {  public void mouseClicked(MouseEvent evt)
      {
      }

      public void mousePressed(MouseEvent evt)
      {
      }

      public void mouseReleased(MouseEvent evt)
      {  model.pauseGame();
         JFileChooser newGame = new JFileChooser(Constants.MAPS_PATH);
         Dune2FileFilter newFilter = new Dune2FileFilter(false, true, false);
         newGame.setFileFilter(newFilter);


         JPanel p = new JPanel();
         newGame.rescanCurrentDirectory();
         int loadOption = newGame.showOpenDialog(p);
         if (loadOption == JFileChooser.APPROVE_OPTION)
         {  String path = newGame.getSelectedFile().getAbsolutePath();
            model.newGame(path);
         }
         model.resumeGame();
      }

      public void mouseEntered(MouseEvent evt)
      {
      }

      public void mouseExited(MouseEvent evt)
      {
      }
   }

   private class MainMouseListener implements MouseListener
   {  public void mouseClicked(MouseEvent evt)
      {
      }

      public void mousePressed(MouseEvent evt)
      {
      }

      public void mouseReleased(MouseEvent evt)
      {  model.pauseGame();
         JFrame f = new JFrame();
         int yes = JOptionPane.YES_OPTION;
         yes = JOptionPane.showConfirmDialog(f, "Exit game and return to main menu?","Exit Game",JOptionPane.YES_NO_OPTION);
         if (yes == JOptionPane.YES_OPTION)
         {  model.setAtMainMenu();
         } else
         {  model.resumeGame();
         }
      }

      public void mouseEntered(MouseEvent evt)
      {
      }

      public void mouseExited(MouseEvent evt)
      {
      }
   }

   private class FileMenuMouseListener implements MouseListener
   {  public void mouseClicked(MouseEvent evt)
      {
      }

      public void mousePressed(MouseEvent evt)
      {
      }

      public void mouseReleased(MouseEvent evt)
      {
      }

      public void mouseEntered(MouseEvent evt)
      {
      }

      public void mouseExited(MouseEvent evt)
      {
      }
   }

   private class ExitMouseListener implements MouseListener
   {  public void mouseClicked(MouseEvent evt)
      {
      }

      public void mousePressed(MouseEvent evt)
      {
      }

      public void mouseReleased(MouseEvent evt)
      {  System.exit(0);
      }

      public void mouseEntered(MouseEvent evt)
      {
      }

      public void mouseExited(MouseEvent evt)
      {
      }
   }


   private class LoadMouseListener implements MouseListener
   {  public void mouseClicked(MouseEvent evt)
      {
      }

      public void mousePressed(MouseEvent evt)
      {
      }

      public void mouseReleased(MouseEvent evt)
      {  model.pauseGame();
         JPanel p = new JPanel();

         JFileChooser loadsave = new JFileChooser(Constants.SAVED_GAME_PATH);
         Dune2FileFilter loadFilter = new Dune2FileFilter(true, false, false);
         loadsave.setFileFilter(loadFilter);

         loadsave.rescanCurrentDirectory();
         int loadOption = loadsave.showOpenDialog(p);
         if (loadOption == JFileChooser.APPROVE_OPTION)
         {  String path = loadsave.getSelectedFile().getAbsolutePath();
            model.loadFile(path);
         }
         model.resumeGame();
      }

      public void mouseEntered(MouseEvent evt)
      {
      }

      public void mouseExited(MouseEvent evt)
      {
      }
   }

}
