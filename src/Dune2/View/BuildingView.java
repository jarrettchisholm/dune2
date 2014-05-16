package Dune2.View;
import Dune2.Model.*;
import Dune2.Units.*;

import becker.util.IView;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class BuildingView extends JPanel
{  private World model;
   private ImageInformation imageInfo;
   private int listIndex = 0;
   private int buildingIndex = 0;
   private Building[] buildings;
   private Unit[] units;
   private JFrame frame;
   private boolean viewBuildings;
   private boolean viewUnits;

   public BuildingView(World theModel, ImageInformation iInfo)
   {  this.model = theModel;
      this.imageInfo = iInfo;

      this.layoutView();
      this.registerListeners();

      //this.model.addView(this);

      this.frame.setVisible(true);
      this.buildings = this.model.getAvailableBuildings();
      this.units = this.model.getAvailableUnits();
      this.repaint();
   }

   private void layoutView()
   {  this.setPreferredSize(new Dimension(800, 600));
      this.frame = new JFrame("building frame");
      this.frame.setSize(800, 600);
      this.frame.setBackground(Color.black);
      this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      this.frame.setResizable(false);
      this.frame.setContentPane(this);
      this.frame.setVisible(false);
      //this.setBackground(Color.black);
   }

   private void registerListeners()
   {  this.addMouseListener(new buildingViewMouseListener());
   }

   public void closeView()
   {  this.frame.dispose();
   }

   public void paintComponent(Graphics g)
   {  Graphics2D g2 = (Graphics2D) g;

      g2.setColor(Color.black);
      g2.fillRect(0, 0, 800, 600);

      this.drawUpArrow(g2);
      this.drawDownArrow(g2);
      this.drawTypeToBuildButton(g2);
      this.drawExitButton(g2);
      this.drawBuildButton(g2);
      this.drawSmallImages(g2);
      this.drawLargeImages(g2);
      this.drawName(g2);
      this.drawCost(g2);
      this.drawCurrentCash(g2);
   }

   private void drawUpArrow(Graphics2D g2)
   {  g2.setColor(Color.yellow);
      g2.drawRect(0, 0, 100, 30);
      g2.drawString("UP", 2, 12);
   }

   private void drawDownArrow(Graphics2D g2)
   {  g2.setColor(Color.yellow);
      g2.drawRect(0, 530, 100, 30);
      g2.drawString("DOWN", 2, 542);
   }

   private void drawTypeToBuildButton(Graphics2D g2)
   {  g2.setColor(Color.yellow);
      g2.drawRect(0, 40, 100, 30);
      g2.drawString("Building", 2, 52);
      g2.drawLine(50, 40, 50, 70);
      g2.drawString("Unit", 52, 52);
   }

   private void drawExitButton(Graphics2D g2)
   {  g2.setColor(Color.yellow);
      g2.drawRect(690, 530, 100, 30);
      g2.setColor(Color.red);
      g2.drawString("EXIT", 731, 550);
   }

   private void drawBuildButton(Graphics2D g2)
   {  g2.setColor(Color.yellow);
      g2.drawRect(490, 530, 100, 30);
      g2.setColor(Color.red);
      g2.drawString("BUILD", 527, 550);
   }

   private void drawSmallImages(Graphics2D g2)
   {  //Draw buildings if applicable
      if (this.buildings != null && this.viewBuildings && this.buildings.length < 4)
      {  for (int i=0; i < this.buildings.length; i ++)
         {  BufferedImage buildingImage = this.imageInfo.getImage(this.buildings[i + this.listIndex].getName(), null, 1);
            g2.drawImage(buildingImage, 0, buildingImage.getWidth()*(i+1) + 5, this);
         }
      } else if (this.buildings != null && this.viewBuildings)
      {  for (int i=0; i < 4; i ++)
         {  BufferedImage buildingImage = this.imageInfo.getImage(this.buildings[i + this.listIndex].getName(), null, 1);
            g2.drawImage(buildingImage, 0, buildingImage.getWidth()*(i+1) + 5, this);
         }
      }

      //Draw units if applicable
      if (this.units != null && this.viewUnits && this.units.length < 4)
      {  for (int i=0; i < this.units.length; i++)
         {  BufferedImage unitImage = this.imageInfo.getImage(this.units[i + this.listIndex].getName(), null, 1);
            g2.drawImage(unitImage, 0, unitImage.getWidth()*(i+1) + 5, this);
         }
      } else if (this.units != null && this.viewUnits)
      {  for (int i=0; i < 4; i ++)
         {  BufferedImage unitImage = this.imageInfo.getImage(this.units[i + this.listIndex].getName(), null, 1);
            g2.drawImage(unitImage, 0, unitImage.getWidth()*(i+1) + 5, this);
         }
      }
   }

   private void drawLargeImages(Graphics2D g2)
   {  //Draw buildings if applicable
      if (this.buildings != null & this.viewBuildings)
      {  BufferedImage buildingImage = this.imageInfo.getImage(this.buildings[this.buildingIndex].getName(), null, 0);
         g2.drawImage(buildingImage, 150, 94, this);
      }

      if (this.units != null && this.viewUnits)
      {  BufferedImage unitImage = this.imageInfo.getImage(this.units[this.buildingIndex].getName(), null, 0);
         g2.drawImage(unitImage, 150, 94, this);
      }
   }

   private void drawName(Graphics2D g2)
   {  g2.setColor(Color.yellow);
      if (this.buildings != null && this.viewBuildings)
      {  g2.drawString("Name: " + this.buildings[this.buildingIndex].getName(), 450, 40);
      }

      if (this.units != null && this.viewUnits)
      {  g2.drawString("Name: " + this.units[this.buildingIndex].getName(), 450, 40);
      }
   }

   private void drawCost(Graphics2D g2)
   {  g2.setColor(Color.yellow);
      if (this.buildings != null && this.viewBuildings)
      {  g2.drawString("Cost: " + this.buildings[this.buildingIndex].getCost(), 450, 50);
      }

      if (this.units != null && this.viewUnits)
      {  g2.drawString("Cost: " + this.units[this.buildingIndex].getCost(), 450, 50);
      }
   }

   private void drawCurrentCash(Graphics2D g2)
   {  g2.setColor(Color.red);
      g2.drawString("Available Funds: "+ model.getPlayerFunds(0), 450, 60);
   }

   private class buildingViewMouseListener implements MouseListener
   {  public void mouseClicked(MouseEvent evt)
      {
      }

      public void mousePressed(MouseEvent evt)
      {  int x = evt.getX();
         int y = evt.getY();

         if (x > 690 && y > 520)
         {  model.leaveBuilding();
            buildingIndex = 0;
            listIndex = 0;
         } else if (x > 490 && x < 590 && y > 530 && y < 560)
         {  if (buildings != null && viewBuildings && model.canBuildBuilding(buildings[buildingIndex]))
            {  model.leaveBuilding();
               buildingIndex = 0;
               listIndex = 0;
            } else if (units != null && viewUnits && model.canBuildUnit(units[buildingIndex]))
            {
               model.leaveBuilding();
               buildingIndex = 0;
               listIndex = 0;
            }
         }else if (x > 0 && x < 89 && y > 92 && y < 160)
         {  buildingIndex = 0 + listIndex;
         } else if (x > 0 && x < 89 && y > 180 && y < 251)
         {  buildingIndex = 1 + listIndex;
         } else if (x > 0 && x < 89 && y > 271 && y < 337)
         {  if (buildings !=null && buildings.length > 2)
            {  buildingIndex = 2 + listIndex;
            } else if (units !=null && units.length > 2)
            {  buildingIndex = 2 + listIndex;
            }
         } else if (x > 0 && x < 89 && y > 360 && y < 428)
         {  if (buildings != null && buildings.length > 3)
            {  buildingIndex = 3 + listIndex;
            } else if (units != null && units.length > 3)
            {  buildingIndex = 3 + listIndex;
            }
         } else if (x > 0 && y > 0 && x < 100 && y < 30)
         {  if (listIndex != 0)
            {  listIndex = listIndex - 1;
            }
         } else if (x > 0 && y > 530 && x < 100 && y < 560)
         {  if (buildings!= null && (buildings.length - listIndex) > 4)
            {  listIndex = listIndex + 1;
            } else if (units != null && (units.length - listIndex) > 4)
            {  listIndex = listIndex + 1;
            }
         } else if (x > 0 && y > 40 && x < 50 && y < 70)
         {  viewBuildings = true;
            viewUnits = false;
            listIndex = 0;
            buildingIndex = 0;
         } else if (x > 50 && y > 40 && x < 100 & y < 70)
         {  viewBuildings = false;
            viewUnits = true;
            listIndex = 0;
            buildingIndex = 0;
         }

         repaint();
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


}
