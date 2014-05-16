package Dune2.View;
import Dune2.Model.*;

import becker.util.IView;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.GraphicsDevice;
import java.awt.*;
import java.io.File;

public class TopLevelView extends JPanel implements IView
{  private ImageInformation imageInfo;
   private World model;
   private MenuView menuView;
   private RadarView radarView;
   private WorldView worldView;
   private CommandView commandView;
   private UnitInfoView unitInfo;
   private JScrollPane worldViewPanel;

   private int compareVertical;
   private int compareHorizontal;

   public TopLevelView(World theModel, ImageInformation iInfo)
   {  this.model = theModel;
      this.imageInfo = iInfo;

      LoadingScreen loadingScreen = new LoadingScreen(iInfo);

      this.menuView = new MenuView(theModel, iInfo);
      this.radarView = new RadarView(theModel, iInfo);
      this.worldView = new WorldView(theModel, iInfo);
      this.commandView = new CommandView(theModel, iInfo);
      this.unitInfo = new UnitInfoView(theModel, iInfo);

      this.layoutView();
      this.registerListeners();
      
      this.model.addView(this);
      //Cursor cursor = new Cursor(Cursor.CROSSHAIR_CURSOR);
      //this.setCursor(cursor);
   }

//   JScrollPane(this.container, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS

   private void layoutView()
   {  this.setLayout(new BorderLayout());
      
      //this.worldView.setPreferredSize(new Dimension(Constants.GRID_SIZE * this.model.getMapX(), Constants.GRID_SIZE * this.model.getMapY()));
      this.worldViewPanel = new JScrollPane(this.worldView, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
      this.worldViewPanel.setPreferredSize(new Dimension(600,600));
      this.add(this.worldViewPanel, BorderLayout.CENTER);

      JPanel radarAndCommandView = new JPanel();
      radarAndCommandView.setPreferredSize(new Dimension(200, 500));
      radarAndCommandView.add(this.radarView);
      radarAndCommandView.add(this.unitInfo);
      radarAndCommandView.add(this.commandView);

      this.add(radarAndCommandView, BorderLayout.EAST);

   }

   private void registerListeners()
   {
   }

   public void updateModelWithNewRadarInformation(int hValue, int hMax, int hWidth, int vValue, int vMax, int vHeight)
   {  this.model.setViewChanged();

      this.model.updateViewHorizontal(hValue, hMax - hWidth);
      this.model.updateViewVertical(vValue, vMax - vHeight);

      this.model.updateWorldViewHorizontal(this.worldViewPanel.getWidth());
      this.model.updateWorldViewVertical(this.worldViewPanel.getHeight());

      this.compareVertical = vValue;
      this.compareHorizontal = hValue;
   }

   public void updateView()
   {  JScrollBar vertical = this.worldViewPanel.getVerticalScrollBar();
      JScrollBar horizontal = this.worldViewPanel.getHorizontalScrollBar();

      if (this.model.getRefreshMap())
      {  this.worldView.setPreferredSize(new Dimension(Constants.GRID_SIZE * this.model.getMapX(), Constants.GRID_SIZE * this.model.getMapY()));
         this.worldViewPanel.setViewportView(this.worldView);
         this.updateModelWithNewRadarInformation(horizontal.getValue(), horizontal.getMaximum(), horizontal.getWidth(), vertical.getValue(), vertical.getMaximum(), vertical.getHeight());
      }

      if (this.model.hasRadarChanged())
      {  vertical.setValue(this.model.getViewVerticalValue());
         horizontal.setValue(this.model.getViewHorizontalValue());
         this.compareVertical = vertical.getValue();
         this.compareHorizontal = horizontal.getValue();
         this.model.resetRadarChanged();
      }  else if (this.compareVertical != vertical.getValue() || this.compareHorizontal != horizontal.getValue())
      {  this.updateModelWithNewRadarInformation(horizontal.getValue(), horizontal.getMaximum(), horizontal.getWidth(), vertical.getValue(), vertical.getMaximum(), vertical.getHeight());
         /*
         this.model.setViewChanged();

         this.model.updateViewHorizontal(horizontal.getValue(), horizontal.getMaximum() - horizontal.getWidth());
         this.model.updateViewVertical(vertical.getValue(), vertical.getMaximum() - vertical.getHeight());

         this.model.updateWorldViewHorizontal(this.worldViewPanel.getWidth());
         this.model.updateWorldViewVertical(this.worldViewPanel.getHeight());

         this.compareVertical = vertical.getValue();
         this.compareHorizontal = horizontal.getValue();
         */
      }
      //System.out.println(vertical.getValue() + ", " + (vertical.getMaximum() - vertical.getHeight()) + " : " + horizontal.getValue() + ", " + (horizontal.getMaximum() - horizontal.getWidth()));

   }
} 