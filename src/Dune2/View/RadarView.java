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
import java.awt.image.DataBuffer;
import java.awt.geom.AffineTransform;

public class RadarView extends JPanel implements IView
{  private World model;
   private ImageInformation imageInfo;
   private int gridSizeX; //compressed GRID SIZE X
   private int gridSizeY; //compressed GRID SIZE Y
   private double atX; //ratio for width (x)
   private int drawX; //coordinates to draw (x)
   private double atY; //ratio for height (y)
   private int drawY; //coordinates to draw (y)
   private int lengthX; //how long to draw the x
   private int lengthY; //how long to draw the y
   private boolean changingView; //is the user using the radar map to change the viewable area?

   private int draggedX; //these are both coordinates of the mouse when 'dragging' it
   private int draggedY;

   private int numDrags;
   private static final int MAX_DRAGS = 5;
   //private JPanel holder;

   public RadarView(World theModel, ImageInformation iInfo)
   {  this.model = theModel;
      this.imageInfo = iInfo;

      this.layoutView();
      this.registerListeners();
      this.model.addView(this);

   }

   private void layoutView()
   {  this.setPreferredSize(new Dimension(Constants.RADAR_VIEW_X, Constants.RADAR_VIEW_Y));
      this.setBackground(Color.black);
   }

   private void registerListeners()
   {  this.addMouseListener(new thisMouseListener());
      this.addMouseMotionListener(new thisMouseMotionListener());
   }

   public void updateView()
   {  this.gridSizeX = (int)(Constants.RADAR_VIEW_X / this.model.getMapX());
      this.gridSizeY = (int)(Constants.RADAR_VIEW_Y / this.model.getMapY());
      this.repaint();
   }

   public void paintComponent(Graphics g)
   {  Graphics2D g2 = (Graphics2D) g;
      g2.fillRect(0, 0, Constants.RADAR_VIEW_X, Constants.RADAR_VIEW_Y);

      this.drawBuildings(g2);
      this.drawUnits(g2);
      this.drawUnderAttack(g2);
      this.drawViewable(g2);
   }

   private void drawViewable(Graphics2D g2)
   {
      if (this.model.hasViewChanged())
      {  this.updateValuesForViewable();
         this.model.resetViewChanged();
      }

      g2.setColor(Color.white);
      g2.drawRect(drawX, drawY, lengthX, lengthY);
   }

   public void resetViewChangedInModel()
   {  this.model.resetViewChanged();
   }

   public void updateValuesForViewable()
   {  this.atX = ((double)this.model.getViewHorizontalValue() / ((double)this.model.getViewHorizontal()+1.0));
      this.drawX = (int)(this.atX*(this.getWidth() - this.lengthX));
      this.atY = ((double)this.model.getViewVerticalValue() / ((double)this.model.getViewVertical()+1.0));
      this.drawY = (int)(this.atY*(this.getHeight() - this.lengthY));
      this.lengthX = (int)((double)this.getWidth() * ((double)this.model.getWorldViewHorizontal() / (1.0+(double)(Constants.GRID_SIZE * this.model.getMapX()))));
      this.lengthY = (int)((double)this.getHeight() * ((double)this.model.getWorldViewVertical() / (1.0+(double)(Constants.GRID_SIZE * this.model.getMapY()))));
   }

   private void drawUnderAttack(Graphics2D g2)
   {  if (this.model.isUnitUnderAttack())
      {  g2.setColor(Color.red);
         int x = this.model.getUnitUnderAttackX() * this.gridSizeX;
         int y = this.model.getUnitUnderAttackY() * this.gridSizeY;
         g2.drawRect(x-5, y-5, 10, 10);
         this.model.increaseUnitUnderAttackLength();
      }

      if (this.model.isBuildingUnderAttack())
      {  g2.setColor(Color.red);
         int x = this.model.getBuildingUnderAttackX() * this.gridSizeX;
         int y = this.model.getBuildingUnderAttackY() * this.gridSizeY;
         g2.drawRect(x-5, y-5, 10, 10);
         this.model.increaseBuildingUnderAttackLength();
      }

   }

   private void drawUnits(Graphics2D g2)
   {
      //Atreides' units
      int numUnits = this.model.getNumUnits(Constants.ATREIDES);
      Unit[] units = this.model.getUnits(Constants.ATREIDES);
      for (int i=0; i < numUnits; i++)
      {
         int x = units[i].getCurrentX();
         int y = units[i].getCurrentY();

         g2.setColor(Color.blue);
         g2.fillRect(x*this.gridSizeX, y*this.gridSizeY, 2, 2);
      }


      //Ordos' units
      numUnits = this.model.getNumUnits(Constants.ORDOS);
      units = this.model.getUnits(Constants.ORDOS);
      for (int i=0; i < numUnits; i++)
      {
         int x = units[i].getCurrentX();
         int y = units[i].getCurrentY();

         g2.setColor(Color.green);
         g2.fillRect(x*this.gridSizeX, y*this.gridSizeY, 2, 2);
      }


      //Harkonnen' units
      numUnits = this.model.getNumUnits(Constants.HARKONNEN);
      units = this.model.getUnits(Constants.HARKONNEN);
      for (int i=0; i < numUnits; i++)
      {
         int x = units[i].getCurrentX();
         int y = units[i].getCurrentY();

         g2.setColor(Color.red);
         g2.fillRect(x*this.gridSizeX, y*this.gridSizeY, 2, 2);
      }
   }

   private void drawBuildings(Graphics2D g2)
   {
      //Atreides' units
      int numBuildings = this.model.getNumBuildings(Constants.ATREIDES);
      Building[] buildings = this.model.getBuildings(Constants.ATREIDES);
      for (int i=0; i < numBuildings; i++)
      {
         int x = buildings[i].getX();
         int y = buildings[i].getY();
         int lengthX = buildings[i].getLengthX();
         int widthY = buildings[i].getWidthY();

         g2.setColor(Color.blue);
         g2.drawRect(x*this.gridSizeX, y*this.gridSizeY, 2*lengthX, 2*widthY);
      }


      //Ordos' units
      numBuildings = this.model.getNumBuildings(Constants.ORDOS);
      buildings = this.model.getBuildings(Constants.ORDOS);
      for (int i=0; i < numBuildings; i++)
      {
         int x = buildings[i].getX();
         int y = buildings[i].getY();
         int lengthX = buildings[i].getLengthX();
         int widthY = buildings[i].getWidthY();

         g2.setColor(Color.green);
         g2.drawRect(x*this.gridSizeX, y*this.gridSizeY, 2*lengthX, 2*widthY);
      }


      //Harkonnen' units
      numBuildings = this.model.getNumBuildings(Constants.HARKONNEN);
      buildings = this.model.getBuildings(Constants.HARKONNEN);
      for (int i=0; i < numBuildings; i++)
      {
         int x = buildings[i].getX();
         int y = buildings[i].getY();
         int lengthX = buildings[i].getLengthX();
         int widthY = buildings[i].getWidthY();

         g2.setColor(Color.red);
         g2.drawRect(x*this.gridSizeX, y*this.gridSizeY, 2*lengthX, 2*widthY);
      }
   }

   private class thisMouseListener implements MouseListener
   {  public void mouseClicked(MouseEvent evt)
      {
      }

      public void mousePressed(MouseEvent evt)
      {  changingView = true;
      }

      public void mouseReleased(MouseEvent evt)
      {  changingView = false;
      }

      public void mouseEntered(MouseEvent evt)
      {
      }

      public void mouseExited(MouseEvent evt)
      {
      }
   }

   private class thisMouseMotionListener implements MouseMotionListener
   {  public void mouseMoved(MouseEvent evt)
      {
      }

      public void mouseDragged(MouseEvent evt)
      {  draggedX = evt.getX();
         draggedY = evt.getY();
         numDrags++;

         if (changingView && numDrags >= MAX_DRAGS)
         {
            updateValuesForRadarFromRadar();
            numDrags = 0;
         }


      }
   }

   private void changingViewableOrientation()
   {  this.model.updateViewHorizontal((int)(this.atX * (double)(this.model.getViewHorizontal()+1.0)), this.model.getViewHorizontal());
      this.model.updateViewVertical((int)(this.atY * (double)(this.model.getViewVertical()+1.0)), this.model.getViewVertical());
      this.model.setRadarChanged();
   }

   /* The RadarViewThread uses this --> doesn't need the World object
   */
   public boolean hasViewInModelChanged()
   {  return this.model.hasViewChanged();
   }

   /* The RadarViewThread uses this --> knows when to call 'updatevaluesforradarfromradar()' method
   */
   public boolean isChangingView()
   {  return this.changingView;
   }

   /* The RadarViewThread uses this --> knows when the game is running
   */
   public boolean isGameRunning()
   {  return this.model.isGameRunning();
   }

   public synchronized void updateValuesForRadarFromRadar()
   {
      //normal (aka middle) viewing
      if ( this.draggedY <= (this.getHeight() - this.lengthY/2) && this.draggedX <= (this.getWidth() - this.lengthX/2) && this.draggedX >=this.lengthX/2 && this.draggedY >= this.lengthY/2)
      {  this.atX = ((double)this.draggedX - (double)(this.lengthX/2)) / ((double)this.getWidth() - (double)this.lengthX);
         this.atY = ((double)this.draggedY - (double)(this.lengthY/2)) / ((double)this.getHeight() - (double)this.lengthY);
         this.drawX = this.draggedX - this.lengthX/2;
         this.drawY = this.draggedY - this.lengthY/2;
         this.changingViewableOrientation();
      } else if ( this.draggedY > (this.getHeight() - this.lengthY/2) && this.draggedX <= (this.getWidth() - this.lengthX/2) && this.draggedX >=this.lengthX/2 && this.draggedY >= this.lengthY/2)
      {  //viewing right/middle
         this.draggedY = this.getHeight() - this.lengthY/2; // this is the maximum y value

         this.atX = ((double)this.draggedX - (double)(this.lengthX/2)) / ((double)this.getWidth() - (double)this.lengthX);
         this.atY = ((double)this.draggedY - (double)(this.lengthY/2)) / ((double)this.getHeight() - (double)this.lengthY);
         this.drawX = this.draggedX - this.lengthX/2;
         this.drawY = this.draggedY - this.lengthY/2;
         this.changingViewableOrientation();
      } else if ( this.draggedY <= (this.getHeight() - this.lengthY/2) && this.draggedX > (this.getWidth() - this.lengthX/2) && this.draggedX >=this.lengthX/2 && this.draggedY >= this.lengthY/2)
      {  //viewing top/middle
         this.draggedX = this.getWidth() - this.lengthX/2; // this is the maximum x value

         this.atX = ((double)this.draggedX - (double)(this.lengthX/2)) / ((double)this.getWidth() - (double)this.lengthX);
         this.atY = ((double)this.draggedY - (double)(this.lengthY/2)) / ((double)this.getHeight() - (double)this.lengthY);
         this.drawX = this.draggedX - this.lengthX/2;
         this.drawY = this.draggedY - this.lengthY/2;
         this.changingViewableOrientation();
      } else if ( this.draggedY <= (this.getHeight() - this.lengthY/2) && this.draggedX <= (this.getWidth() - this.lengthX/2) && this.draggedX < this.lengthX/2 && this.draggedY >= this.lengthY/2)
      {  //viewing left/middle
         this.draggedX = this.lengthX/2; // this is the minimum x value

         this.atX = ((double)this.draggedX - (double)(this.lengthX/2)) / ((double)this.getWidth() - (double)this.lengthX);
         this.atY = ((double)this.draggedY - (double)(this.lengthY/2)) / ((double)this.getHeight() - (double)this.lengthY);
         this.drawX = this.draggedX - this.lengthX/2;
         this.drawY = this.draggedY - this.lengthY/2;
         this.changingViewableOrientation();
      } else if ( this.draggedY <= (this.getHeight() - this.lengthY/2) && this.draggedX <= (this.getWidth() - this.lengthX/2) && this.draggedX >= this.lengthX/2 && this.draggedY < this.lengthY/2)
      {  //viewing top/middle
         this.draggedY = this.lengthY/2; // this is the minimum y value

         this.atX = ((double)this.draggedX - (double)(this.lengthX/2)) / ((double)this.getWidth() - (double)this.lengthX);
         this.atY = ((double)this.draggedY - (double)(this.lengthY/2)) / ((double)this.getHeight() - (double)this.lengthY);
         this.drawX = this.draggedX - this.lengthX/2;
         this.drawY = this.draggedY - this.lengthY/2;
         this.changingViewableOrientation();
      } else if ( this.draggedY <= (this.getHeight() - this.lengthY/2) && this.draggedX > (this.getWidth() - this.lengthX/2) && this.draggedX >= this.lengthX/2 && this.draggedY < this.lengthY/2)
      {  //viewing top/right
         this.draggedY = this.lengthY/2; // this is the minimum y value
         this.draggedX = this.getWidth() - this.lengthX/2; //this is the maximum x value

         this.atX = ((double)this.draggedX - (double)(this.lengthX/2)) / ((double)this.getWidth() - (double)this.lengthX);
         this.atY = ((double)this.draggedY - (double)(this.lengthY/2)) / ((double)this.getHeight() - (double)this.lengthY);
         this.drawX = this.draggedX - this.lengthX/2;
         this.drawY = this.draggedY - this.lengthY/2;
         this.changingViewableOrientation();
      } else if ( this.draggedY > (this.getHeight() - this.lengthY/2) && this.draggedX > (this.getWidth() - this.lengthX/2) && this.draggedX >= this.lengthX/2 && this.draggedY >= this.lengthY/2)
      {  //viewing bottom/right
         this.draggedY = this.getHeight() - this.lengthY/2; // this is the maximum y value
         this.draggedX = this.getWidth() - this.lengthX/2; //this is the maximum x value

         this.atX = ((double)this.draggedX - (double)(this.lengthX/2)) / ((double)this.getWidth() - (double)this.lengthX);
         this.atY = ((double)this.draggedY - (double)(this.lengthY/2)) / ((double)this.getHeight() - (double)this.lengthY);
         this.drawX = this.draggedX - this.lengthX/2;
         this.drawY = this.draggedY - this.lengthY/2;
         this.changingViewableOrientation();
      } else if ( this.draggedY > (this.getHeight() - this.lengthY/2) && this.draggedX <= (this.getWidth() - this.lengthX/2) && this.draggedX < this.lengthX/2 && this.draggedY >= this.lengthY/2)
      {  //viewing bottom/left
         this.draggedY = this.getHeight() - this.lengthY/2; // this is the maximum y value
         this.draggedX = this.lengthX/2; //this is the minimum x value

         this.atX = ((double)this.draggedX - (double)(this.lengthX/2)) / ((double)this.getWidth() - (double)this.lengthX);
         this.atY = ((double)this.draggedY - (double)(this.lengthY/2)) / ((double)this.getHeight() - (double)this.lengthY);
         this.drawX = this.draggedX - this.lengthX/2;
         this.drawY = this.draggedY - this.lengthY/2;
         this.changingViewableOrientation();
      } else if ( this.draggedY <= (this.getHeight() - this.lengthY/2) && this.draggedX <= (this.getWidth() - this.lengthX/2) && this.draggedX < this.lengthX/2 && this.draggedY < this.lengthY/2)
      {  //viewing top/left
         this.draggedY = this.lengthY/2; // this is the minimum y value
         this.draggedX = this.lengthX/2; //this is the minimum x value

         this.atX = ((double)this.draggedX - (double)(this.lengthX/2)) / ((double)this.getWidth() - (double)this.lengthX);
         this.atY = ((double)this.draggedY - (double)(this.lengthY/2)) / ((double)this.getHeight() - (double)this.lengthY);
         this.drawX = this.draggedX - this.lengthX/2;
         this.drawY = this.draggedY - this.lengthY/2;
         this.changingViewableOrientation();
      }
   }
}
