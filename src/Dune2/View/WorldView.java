package Dune2.View;
import Dune2.Model.*;
import Dune2.Units.*;
import Dune2.TextDisplay.TextDisplayer;
import Dune2.TextDisplay.Message;

import becker.util.IView;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.*;
import java.io.File;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.geom.AffineTransform;

public class WorldView extends JPanel implements IView
{  private World model;
   private ImageInformation imageInfo;

   /* For background (landscape) and forground(units, etc) drawing
   */
   private BufferedImage background;
   private Graphics2D biContext;
   private BufferedImage foreground;
   private Graphics2D bifContext;

   private int mouseX;
   private int mouseY;

   private int startX;
   private int drawX;
   private int startY;
   private int drawY;
   private int lengthDrawX;
   private int lengthDrawY;
   private int endX;
   private int endY;

   private boolean givenCommandThisPress;
   private boolean drawBoxSelector;

   public static final int INDICATOR_MODIFIER = 2;
   public static final int UNIT_HEALTH_BAR_SPACE = 20;
   public static final int BUILDING_HEALTH_BAR_SPACE = 30;

   public WorldView(World theModel, ImageInformation iInfo)
   {  this.model = theModel;
      this.imageInfo = iInfo;
      this.layoutView();
      this.registerListeners();
      //this.loadMap();

      this.model.addView(this);
   }

   private void layoutView()
   {  //this.setPreferredSize(new Dimension(Constants.GRID_SIZE*this.model.getMapX(), Constants.GRID_SIZE*this.model.getMapY()));
      //this.setBackground(Color.green);
   }

   private void registerListeners()
   {  this.addMouseListener(new WorldViewMouseListener());
      this.addMouseMotionListener(new WorldViewMouseMotionListener());
   }

   public void updateView()
   {  if (this.model.getRefreshMap() || this.background == null)
      {  //we reset 'refreshMap' in "gameFlow()' in the model
         System.out.println("Map was redrawn in WorldView.");
         this.loadMap();
         this.loadForeground();
      } else if (this.model.getNumChangedMapCoordinates() > 0)
      {  int[][] changed = this.model.getChangedMapCoordinates();
         for (int i=0; i < this.model.getNumChangedMapCoordinates(); i++)
         {  this.repairMapAt(changed[0][i], changed[1][i]);
         }

         this.model.resetChangedMapCoordinates();
      }
      this.repaint();
   }

   private void repairMapAt(int x, int y)
   {  BufferedImage image = this.imageInfo.getImage(this.model.getMapInfoAt(x, y), null);
      //this.biContext = this.background.createGraphics();
      this.biContext.drawImage(image, x * Constants.GRID_SIZE, y * Constants.GRID_SIZE, Constants.GRID_SIZE, Constants.GRID_SIZE, this);
   }

   private void loadMap()
   {  this.setPreferredSize(new Dimension(Constants.GRID_SIZE * this.model.getMapX(), Constants.GRID_SIZE * this.model.getMapY()));
      this.background = new BufferedImage(this.model.getMapX() * Constants.GRID_SIZE, this.model.getMapY() * Constants.GRID_SIZE, BufferedImage.TYPE_INT_RGB);
      this.biContext = this.background.createGraphics();
      this.biContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      this.biContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);

      for (int i=0; i < this.model.getMapX(); i++)
      {  for (int j=0; j < this.model.getMapY(); j++)
         {  String type = this.model.getMapInfoAt(i, j);
            BufferedImage image = this.imageInfo.getImage(type, null);
            this.biContext.drawImage(image, i * Constants.GRID_SIZE, j * Constants.GRID_SIZE, Constants.GRID_SIZE, Constants.GRID_SIZE, this);
         }
      }
   }

   private void loadForeground()
   {
      this.foreground = new BufferedImage(this.model.getMapX() * Constants.GRID_SIZE, this.model.getMapY() * Constants.GRID_SIZE, Transparency.BITMASK);
      this.bifContext = this.foreground.createGraphics();
      this.bifContext.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
      this.bifContext.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);

      //String type = this.model.getMapInfoAt(i, j);
      //BufferedImage image = this.imageInfo.getImage(type, null);
      //this.biContext.drawImage(image, i * Constants.GRID_SIZE, j * Constants.GRID_SIZE, Constants.GRID_SIZE, Constants.GRID_SIZE, this);
   }

   public void paintComponent(Graphics g)
   {  Graphics2D g2 = (Graphics2D) g;
      //this.loadForeground();


      //DRAW FINAL
      g2.drawImage(this.background, 0, 0, this);
      //this.bifContext.drawImage(this.background, 0, 0, this);

      this.drawBuildings(g2);
      this.drawUnits(g2);
      this.drawProjectiles(g2);
      this.drawExplosions(g2);
      this.drawHighlighter(g2);
      this.drawMovementIndicator(g2);
      this.drawAttackIndicator(g2);
      this.drawPlacingBuilding(g2);
      this.drawFilter(g2);

      //g2.drawImage(this.foreground, 0, 0, this);
      //this.drawCoordinates(g2);
      this.drawMessages(g2);

      if (this.drawBoxSelector)
      {  this.drawBoxSelector(g2);
      }
   }

   private void drawBoxSelector(Graphics2D g2)
   {  g2.setColor(Color.white);

      g2.drawRect(this.drawX, this.drawY, this.lengthDrawX, this.lengthDrawY);
   }

   /* Use this (activated by a trigger) for special in-game effects (ie. night/day
      effects, thunder storm, super-nova sun, etc).
   */
   private void drawFilter(Graphics2D g2)
   {  g2.setColor(new Color(242, 17, 17, 30));
      g2.fillRect(0, 0, this.getWidth(), this.getHeight());
   }

   private void drawMessages(Graphics2D g2)
   {  //g2.setColor(Color.white);
      TextDisplayer tDisplay = this.model.getTextDisplayer();
      Message[] messages = tDisplay.getMessages();
      int numMessages = tDisplay.getNumMessages();

      int numLinesDone = 0;

      double ratioX = (double)this.model.getViewHorizontalValue() / (double)this.model.getViewHorizontal();
      double ratioY = (double)this.model.getViewVerticalValue() / (double)this.model.getViewVertical();

      double lengthX = (double)this.getWidth() - (double)this.model.getWorldViewHorizontal();
      double lengthY = (double)this.getHeight() - (double)this.model.getWorldViewVertical();

      //mutliplying the 20 by that ratio allows the text to remain stationary
      int additionX = 10 + (int)(ratioX*15);//this.model.getWorldViewHorizontal();
      int additionY = this.model.getWorldViewVertical() - 22 + (int)(18*ratioY);

      //int startX = 10;
      //int startY = this.getHeight() - 15;

      int startX = (int)(ratioX * lengthX) + additionX;
      int startY = (int)(ratioY * lengthY) + additionY;

      for (int i=numMessages-1; i >= 0; i--)
      {  if (numLinesDone >= Constants.MAX_LINES_FOR_DISPLAY_TEXT)
         {  break;
         }

         String[] message = messages[i].getMessage();
         int numLines = messages[i].getNumLines();

         for (int j=numLines-1; j >= 0; j--)
         {  g2.setColor(messages[i].getColor());
            g2.drawString(message[j], startX, startY -(numLinesDone*Constants.MIN_DISTANCE_BETWEEN_LINES));
            numLinesDone++;
         }

      }
   }

   private void drawExplosions(Graphics2D g2)
   {  Explosion[] explosions = this.model.getExplosions();
      int numExplosions = this.model.getNumExplosions();

      /* I was forced to put in the "if (explosions[i] != null" if statement because
         I was continuously getting 'null pointed' exceptions when I was drawing
         the explosions.  I can only assume this is because the explosion was
         removed from the array just before i got the explosion array from the model,
         but not before the number of explosions had changed....?  (the drawing of
         the game is not synchronous with the "gameFlow()" method in the model, b/c
         the views are updated through a thread).
         IN SHORT:  W T F?
      */
      for (int i=0; i < numExplosions; i++)
      {  if (explosions[i] != null)
         {  BufferedImage explosionImage = this.imageInfo.getImage(explosions[i].getName(), null, explosions[i].getProgress());
            int x = explosions[i].getX();
            int y = explosions[i].getY();
            g2.drawImage(explosionImage, (x*Constants.GRID_SIZE), (y*Constants.GRID_SIZE), this);
         }
      }
   }

   private void drawMovementIndicator(Graphics2D g2)
   {  if (this.model.getMovementIndicatorTime() > 0)
      {  //g2.drawRoundRect(this.model.getMovementIndicatorX() * Constants.GRID_SIZE, this.model.getMovementIndicatorY() * Constants.GRID_SIZE, Constants.GRID_SIZE, Constants.GRID_SIZE, this.model.getMovementIndicatorTime() * 10, this.model.getMovementIndicatorTime() * 10);
         g2.setColor(Color.green);
         g2.drawOval(this.model.getMovementIndicatorX() * Constants.GRID_SIZE + (Constants.GRID_SIZE/2 - this.model.getMovementIndicatorTime()*this.INDICATOR_MODIFIER), this.model.getMovementIndicatorY() * Constants.GRID_SIZE + (Constants.GRID_SIZE/2 - this.model.getMovementIndicatorTime()*this.INDICATOR_MODIFIER), this.model.getMovementIndicatorTime()*this.INDICATOR_MODIFIER*2, this.model.getMovementIndicatorTime()*this.INDICATOR_MODIFIER*2);
         //g2.drawOval(this.model.getMovementIndicatorX() * Constants.GRID_SIZE + Constants.GRID_SIZE/2, this.model.getMovementIndicatorY() * Constants.GRID_SIZE + Constants.GRID_SIZE/2, this.model.getMovementIndicatorTime()*modifier, this.model.getMovementIndicatorTime()*modifier);
         this.model.decrementMovementIndicatorTime();
      }
   }

   private void drawAttackIndicator(Graphics2D g2)
   {  if (this.model.getAttackIndicatorTime() > 0)
      {  //g2.drawRoundRect(this.model.getMovementIndicatorX() * Constants.GRID_SIZE, this.model.getMovementIndicatorY() * Constants.GRID_SIZE, Constants.GRID_SIZE, Constants.GRID_SIZE, this.model.getMovementIndicatorTime() * 10, this.model.getMovementIndicatorTime() * 10);
         g2.setColor(Color.red);
         g2.drawOval(this.model.getAttackIndicatorX() * Constants.GRID_SIZE + (Constants.GRID_SIZE/2 - this.model.getAttackIndicatorTime()*this.INDICATOR_MODIFIER), this.model.getAttackIndicatorY() * Constants.GRID_SIZE + (Constants.GRID_SIZE/2 - this.model.getAttackIndicatorTime()*this.INDICATOR_MODIFIER), this.model.getAttackIndicatorTime()*this.INDICATOR_MODIFIER*2, this.model.getAttackIndicatorTime()*this.INDICATOR_MODIFIER*2);
         //g2.drawOval(this.model.getAttackIndicatorX() * Constants.GRID_SIZE + Constants.GRID_SIZE/2, this.model.getAttackIndicatorY() * Constants.GRID_SIZE + Constants.GRID_SIZE/2, this.model.getAttackIndicatorTime()*modifier, this.model.getAttackIndicatorTime()*modifier);
         this.model.decrementAttackIndicatorTime();
      }
   }

   private void drawProjectiles(Graphics2D g2)
   {  Projectile[] projectiles = this.model.getProjectiles();
      int numProjectiles = this.model.getNumProjectiles();
      for (int i=0; i < numProjectiles; i++)
      {  BufferedImage projectileImage = this.imageInfo.getImage(projectiles[i].getName(), null);
         int displacementY = -(int)projectiles[i].getProgress();
         int x = projectiles[i].getStartX();
         int y = projectiles[i].getStartY();
         double degree = projectiles[i].getDegree() * (Math.PI / 180);
         if (degree != 0)
         {  AffineTransform aT = g2.getTransform();
            AffineTransform projectileDraw = AffineTransform.getRotateInstance(degree, x*Constants.GRID_SIZE + Constants.GRID_SIZE/2, y*Constants.GRID_SIZE + Constants.GRID_SIZE/2);
            g2.transform(projectileDraw);
            g2.drawImage(projectileImage, (x*Constants.GRID_SIZE), (y*Constants.GRID_SIZE + displacementY), this);
            g2.setTransform(aT);
         } else
         {  g2.drawImage(projectileImage, (x*Constants.GRID_SIZE), (y*Constants.GRID_SIZE + displacementY), this);
         }
      }
   }

   private void drawCoordinates(Graphics2D g2)
   {  g2.setColor(Color.black);
      g2.fillRect(0, 0, 40, 40);
      g2.setColor(Color.yellow);
      int x = (int)(this.mouseX/Constants.GRID_SIZE);
      int y = (int)(this.mouseY/Constants.GRID_SIZE);
      g2.drawString("" + x + "," + y, 10, 10);
   }

   private void drawPlacingBuilding(Graphics2D g2)
   {  if (this.model.getPlacingBuilding())
      {  BufferedImage buildingImage = this.imageInfo.getImage(this.model.getSelectedBuilding().getCurrentConstruction(), this.model.getPlayerHouse(0));
         int x = this.mouseX/Constants.GRID_SIZE;
         int y = this.mouseY/Constants.GRID_SIZE;
         System.out.println(this.model.getSelectedBuilding().getCurrentConstruction());
         g2.drawImage(buildingImage, x*Constants.GRID_SIZE, y*Constants.GRID_SIZE, this);
      }
   }

   private int getOffSetX(int bodyDegree, int progress)
   {  int offSetX = 0;

      if (bodyDegree == 0)
      {  //offSetY = -unit.getProgress();
      } else if (bodyDegree == 45)
      {  double bodyDegree2 = bodyDegree * (Math.PI / 180);
         offSetX = (int)(progress * Math.cos(bodyDegree2));
         //offSetY = (int)((-unit.getProgress()) * Math.sin(bodyDegree2));
      } else if (bodyDegree == 90)
      {  offSetX = progress;
      } else if (bodyDegree == 135)
      {  double bodyDegree2 = bodyDegree * (Math.PI / 180);
         //offSetY = (int)(unit.getProgress() * Math.sin(bodyDegree2));
         offSetX = (int)(progress * Math.sin(bodyDegree2));
      } else if (bodyDegree == 180)
      {  //offSetY = unit.getProgress();
      } else if (bodyDegree == 225)
      {  double bodyDegree2 = bodyDegree * (Math.PI / 180);
         offSetX = (int)((progress) * Math.sin(bodyDegree2));
         //offSetY = (int)((-unit.getProgress()) * Math.sin(bodyDegree2));
      } else if (bodyDegree == 270)
      {  offSetX = -progress;
      } else if (bodyDegree == 315)
      {  double bodyDegree2 = bodyDegree * (Math.PI / 180);
         offSetX = (int)(progress * Math.sin(bodyDegree2));
         //offSetY = (int)(unit.getProgress() * Math.sin(bodyDegree2));
      }

      return offSetX;
   }

   private int getOffSetY(int bodyDegree, int progress)
   {  int offSetY = 0;

      if (bodyDegree == 0)
      {  offSetY = -progress;
      } else if (bodyDegree == 45)
      {  double bodyDegree2 = bodyDegree * (Math.PI / 180);
         //offSetX = (int)(unit.getProgress() * Math.cos(bodyDegree2));
         offSetY = (int)((-progress) * Math.sin(bodyDegree2));
      } else if (bodyDegree == 90)
      {  //offSetX = unit.getProgress();
      } else if (bodyDegree == 135)
      {  double bodyDegree2 = bodyDegree * (Math.PI / 180);
         offSetY = (int)(progress * Math.sin(bodyDegree2));
         //offSetX = (int)(unit.getProgress() * Math.sin(bodyDegree2));
      } else if (bodyDegree == 180)
      {  offSetY = progress;
      } else if (bodyDegree == 225)
      {  double bodyDegree2 = bodyDegree * (Math.PI / 180);
         //offSetX = (int)((unit.getProgress()) * Math.sin(bodyDegree2));
         offSetY = (int)((-progress) * Math.sin(bodyDegree2));
      } else if (bodyDegree == 270)
      {  //offSetX = -unit.getProgress();
      } else if (bodyDegree == 315)
      {  double bodyDegree2 = bodyDegree * (Math.PI / 180);
         //offSetX = (int)(unit.getProgress() * Math.sin(bodyDegree2));
         offSetY = (int)(progress * Math.sin(bodyDegree2));
      }

      return offSetY;
   }


   private void drawHighlighter(Graphics2D g2)
   {
      if (this.model.getSelectedUnits() != null)
      {  Unit[] units = this.model.getSelectedUnits();
         int numUnits = this.model.getNumSelectedUnits();
         for (int a=0; a < numUnits; a++)
         {  //BufferedImage highlighter = this.imageInfo.getImage(Constants.HIGHLIGHTER_UNIT, null, this.model.getHighlighterTime());
            int x = units[a].getCurrentX();
            int y = units[a].getCurrentY();

            String status = units[a].getStatus();
            int bodyDegree = units[a].getBodyDegree();
            int progress = units[a].getProgress();

            int offSetX = 0;
            int offSetY = 0;
            if (status.equals(Constants.MOVE))
            {  offSetX = this.getOffSetX(bodyDegree, progress);
               offSetY = this.getOffSetY(bodyDegree, progress);
            }

            //g2.drawImage(highlighter, x*Constants.GRID_SIZE + offSetX, y*Constants.GRID_SIZE + offSetY, this);

            g2.setColor(Color.yellow);
            int fillSpace = units[a].getHPFillSpace(this.UNIT_HEALTH_BAR_SPACE);
            g2.fillRect(x*Constants.GRID_SIZE+10 + offSetX, y*Constants.GRID_SIZE-7 + offSetY, fillSpace, 4);

            g2.setColor(Color.white);
            g2.drawRect(x*Constants.GRID_SIZE+10 + offSetX, y*Constants.GRID_SIZE-7 + offSetY, this.UNIT_HEALTH_BAR_SPACE, 4);
         }

      } else if (this.model.getSelectedBuilding() != null)
      {  Building building = this.model.getSelectedBuilding();
         //BufferedImage highlighter = this.imageInfo.getImage(Constants.HIGHLIGHTER_BUILDING, null, this.model.getHighlighterTime());
         int x = building.getX();
         int y = building.getY();
         int lengthX = building.getLengthX();
         int widthY = building.getWidthY();

         g2.setColor(Color.white);
         g2.drawRect(x*Constants.GRID_SIZE, y*Constants.GRID_SIZE, lengthX*Constants.GRID_SIZE, widthY*Constants.GRID_SIZE);
         g2.drawRect(x*Constants.GRID_SIZE+1, y*Constants.GRID_SIZE+1, lengthX*Constants.GRID_SIZE-2, widthY*Constants.GRID_SIZE-2);
         g2.drawRect(x*Constants.GRID_SIZE+2, y*Constants.GRID_SIZE+2, lengthX*Constants.GRID_SIZE-3, widthY*Constants.GRID_SIZE-3);

         g2.setColor(Color.yellow);
         int fillSpace = building.getHPFillSpace(this.BUILDING_HEALTH_BAR_SPACE);
         g2.fillRect(x*Constants.GRID_SIZE, y*Constants.GRID_SIZE-7, fillSpace, 4);

         g2.setColor(Color.white);
         g2.drawRect(x*Constants.GRID_SIZE, y*Constants.GRID_SIZE-7, this.BUILDING_HEALTH_BAR_SPACE, 4);

      }
   }


   private void drawUnitHpBar(Unit unit, Color color, Graphics2D g2, int x, int y)
   {  int offSetX = 0;
      int offSetY = 0;
      if (unit.getStatus().equals(Constants.MOVE))
      {  offSetX = this.getOffSetX(unit.getBodyDegree(), unit.getProgress());
         offSetY = this.getOffSetY(unit.getBodyDegree(), unit.getProgress());
      }

      g2.setColor(color);
      int fillSpace = unit.getHPFillSpace(this.UNIT_HEALTH_BAR_SPACE);
      g2.fillRect(x*Constants.GRID_SIZE+10 + offSetX, y*Constants.GRID_SIZE-7 + offSetY, fillSpace, 4);
      g2.setColor(Color.white);
      g2.drawRect(x*Constants.GRID_SIZE+10 + offSetX, y*Constants.GRID_SIZE-7 + offSetY, this.UNIT_HEALTH_BAR_SPACE, 4);
   }

   private void drawUnits(Graphics2D g2)
   {
      //Atreides' units
      int numUnits = this.model.getNumUnits(Constants.ATREIDES);
      Unit[] units = this.model.getUnits(Constants.ATREIDES);

      for (int i=0; i < numUnits; i++)
      {  BufferedImage unitImage = this.imageInfo.getImage(units[i].getName(), Constants.ATREIDES);
         int x = units[i].getCurrentX();
         int y = units[i].getCurrentY();

         //DRAW UNIT HP BAR
         this.drawUnitHpBar(units[i], Color.blue, g2, x, y);

         double bodyDegree = units[i].getBodyDegree() * (Math.PI / 180);
         if (units[i].getStatus().equals(Constants.STOP)) //STOPPING
         {  if (bodyDegree != 0)
            {  AffineTransform aT = g2.getTransform();
               AffineTransform unitDraw = AffineTransform.getRotateInstance(bodyDegree, x*Constants.GRID_SIZE + Constants.GRID_SIZE/2, y*Constants.GRID_SIZE + Constants.GRID_SIZE/2);
               g2.transform(unitDraw);
               g2.drawImage(unitImage, x*Constants.GRID_SIZE, y*Constants.GRID_SIZE, this);
               g2.setTransform(aT);
            } else
            {  g2.drawImage(unitImage, x*Constants.GRID_SIZE, y*Constants.GRID_SIZE, this);
            }
         } else if (units[i].getStatus().equals(Constants.MOVE)) //MOVING
         {  if (bodyDegree != 0)
            {  AffineTransform aT = g2.getTransform();
               AffineTransform unitDraw = AffineTransform.getRotateInstance(bodyDegree, x*Constants.GRID_SIZE + Constants.GRID_SIZE/2, y*Constants.GRID_SIZE + Constants.GRID_SIZE/2);
               g2.transform(unitDraw);
               //g2.drawImage(unitImage, (x*Constants.GRID_SIZE + this.model.getMovementAmountX(units[i])), (y*Constants.GRID_SIZE + this.model.getMovementAmountY(units[i])), this);
               g2.drawImage(unitImage, (x*Constants.GRID_SIZE), (y*Constants.GRID_SIZE + this.model.getMovementAmountY(units[i])), this);
               g2.setTransform(aT);
            } else
            {  g2.drawImage(unitImage, (x*Constants.GRID_SIZE + this.model.getMovementAmountX(units[i])), (y*Constants.GRID_SIZE + this.model.getMovementAmountY(units[i])), this);
            }
         } else if (units[i].getStatus().equals(Constants.LEFT) || units[i].getStatus().equals(Constants.RIGHT)|| units[i].getStatus().equals(Constants.RIGHT_45)|| units[i].getStatus().equals(Constants.LEFT_45))
         {  if (bodyDegree != 0)//TURNING
            {  AffineTransform aT = g2.getTransform();
               AffineTransform unitDraw = AffineTransform.getRotateInstance(bodyDegree, x*Constants.GRID_SIZE + Constants.GRID_SIZE/2, y*Constants.GRID_SIZE + Constants.GRID_SIZE/2);
               g2.transform(unitDraw);
               g2.drawImage(unitImage, (x*Constants.GRID_SIZE), (y*Constants.GRID_SIZE), this);
               g2.setTransform(aT);
            } else
            {  g2.drawImage(unitImage, (x*Constants.GRID_SIZE), (y*Constants.GRID_SIZE), this);
            }
         } else if (units[i].getStatus().equals(Constants.HARVESTING))
         {  //GET HARVESTOR HARVESTING IMAGE
            unitImage = this.imageInfo.getImage(units[i].getStatus(), Constants.ATREIDES);
            if (bodyDegree != 0) //HARVESTOR HARVESTING
            {  AffineTransform aT = g2.getTransform();
               AffineTransform unitDraw = AffineTransform.getRotateInstance(bodyDegree, x*Constants.GRID_SIZE + Constants.GRID_SIZE/2, y*Constants.GRID_SIZE + Constants.GRID_SIZE/2);
               g2.transform(unitDraw);
               g2.drawImage(unitImage, (x*Constants.GRID_SIZE), (y*Constants.GRID_SIZE), this);
               g2.setTransform(aT);
            } else
            {  g2.drawImage(unitImage, (x*Constants.GRID_SIZE + this.model.getMovementAmountX(units[i])), (y*Constants.GRID_SIZE + this.model.getMovementAmountY(units[i])), this);
            }
         }


         if (units[i].getStatus().equals(Constants.STOP) && units[i].hasTurret())
         {  double turretDegree = units[i].getTurretDegree() * (Math.PI / 180); //Draw TURRET STOP
            BufferedImage turretsImage = this.imageInfo.getImage(units[i].getName(), null);
            AffineTransform aT = g2.getTransform();
            AffineTransform turretDraw = AffineTransform.getRotateInstance(turretDegree, x*Constants.GRID_SIZE + Constants.GRID_SIZE/2, y*Constants.GRID_SIZE + Constants.GRID_SIZE/2);
            g2.transform(turretDraw);
            g2.drawImage(turretsImage, x*Constants.GRID_SIZE, y*Constants.GRID_SIZE, this);
            g2.setTransform(aT);
         } else if (units[i].getStatus().equals(Constants.MOVE) && units[i].hasTurret())
         {  double turretDegree = units[i].getTurretDegree() * (Math.PI / 180); //Draw TURRET MOVE
            BufferedImage turretImage = this.imageInfo.getImage(units[i].getName(), null);
            AffineTransform aT = g2.getTransform();
            AffineTransform turretDraw = AffineTransform.getRotateInstance(turretDegree, x*Constants.GRID_SIZE + Constants.GRID_SIZE/2, y*Constants.GRID_SIZE + Constants.GRID_SIZE/2);
            g2.transform(turretDraw);

            int bodyDegree2 = units[i].getBodyDegree();

            int unitProgress = units[i].getProgress();
            //int offSetX = this.getOffSetX(bodyDegree2, unitProgress);
            //int offSetY = this.getOffSetY(bodyDegree2, unitProgress);
            int offSetX = 0;
            int offSetY = 0;

            double drawDegree = units[i].getBodyDegree() - units[i].getTurretDegree();
            if (drawDegree < 0)
            {  drawDegree = drawDegree + 360;
            }
            double tempDegree = drawDegree * (Math.PI / 180);
            if (drawDegree >=0 && drawDegree <= 90)
            {  offSetX = (int)Math.abs((unitProgress * Math.sin(tempDegree)));
               offSetY = -(int)Math.abs((unitProgress * Math.cos(tempDegree)));
            } else if (drawDegree > 90 && drawDegree <= 180)
            {  offSetX = (int)Math.abs((unitProgress * Math.sin(tempDegree)));
               offSetY = (int)Math.abs((unitProgress * Math.cos(tempDegree)));
            } else if (drawDegree > 180 && drawDegree <= 270)
            {  offSetX = -(int)Math.abs((unitProgress * Math.sin(tempDegree)));
               offSetY = (int)Math.abs((unitProgress * Math.cos(tempDegree)));
            } else if (drawDegree > 270 && drawDegree < 360)
            {  offSetX = -(int)Math.abs((unitProgress * Math.sin(tempDegree)));
               offSetY = -(int)Math.abs((unitProgress * Math.cos(tempDegree)));
            }

            g2.drawImage(turretImage, (x*Constants.GRID_SIZE + offSetX), (y*Constants.GRID_SIZE + offSetY), this);
            g2.setTransform(aT);
         } else if ((units[i].getStatus().equals(Constants.LEFT) || units[i].getStatus().equals(Constants.RIGHT) || units[i].getStatus().equals(Constants.LEFT_45) || units[i].getStatus().equals(Constants.RIGHT_45)) && units[i].hasTurret())
         {  double turretDegree = units[i].getTurretDegree() * (Math.PI / 180); //Draw TURRET TURN
            BufferedImage turretImage = this.imageInfo.getImage(units[i].getName(), null);
            AffineTransform aT = g2.getTransform();
            AffineTransform turretDraw = AffineTransform.getRotateInstance(turretDegree, x*Constants.GRID_SIZE + Constants.GRID_SIZE/2, y*Constants.GRID_SIZE + Constants.GRID_SIZE/2);
            g2.transform(turretDraw);
            g2.drawImage(turretImage, x*Constants.GRID_SIZE, y*Constants.GRID_SIZE, this);
            g2.setTransform(aT);
         }
      }




      //Ordos' units
      numUnits = this.model.getNumUnits(Constants.ORDOS);
      units = this.model.getUnits(Constants.ORDOS);

      for (int i=0; i < numUnits; i++)
      {  BufferedImage unitImage = this.imageInfo.getImage(units[i].getName(), Constants.ORDOS);
         int x = units[i].getCurrentX();
         int y = units[i].getCurrentY();

         //DRAW UNIT HP BAR
         this.drawUnitHpBar(units[i], Color.green, g2, x, y);

         double bodyDegree = units[i].getBodyDegree() * (Math.PI / 180);
         if (units[i].getStatus().equals(Constants.STOP)) //STOPPING
         {  if (bodyDegree != 0)
            {  AffineTransform aT = g2.getTransform();
               AffineTransform unitDraw = AffineTransform.getRotateInstance(bodyDegree, x*Constants.GRID_SIZE + Constants.GRID_SIZE/2, y*Constants.GRID_SIZE + Constants.GRID_SIZE/2);
               g2.transform(unitDraw);
               g2.drawImage(unitImage, x*Constants.GRID_SIZE, y*Constants.GRID_SIZE, this);
               g2.setTransform(aT);
            } else
            {  g2.drawImage(unitImage, x*Constants.GRID_SIZE, y*Constants.GRID_SIZE, this);
            }
         } else if (units[i].getStatus().equals(Constants.MOVE)) //MOVING
         {  if (bodyDegree != 0)
            {  AffineTransform aT = g2.getTransform();
               AffineTransform unitDraw = AffineTransform.getRotateInstance(bodyDegree, x*Constants.GRID_SIZE + Constants.GRID_SIZE/2, y*Constants.GRID_SIZE + Constants.GRID_SIZE/2);
               g2.transform(unitDraw);
               //g2.drawImage(unitImage, (x*Constants.GRID_SIZE + this.model.getMovementAmountX(units[i])), (y*Constants.GRID_SIZE + this.model.getMovementAmountY(units[i])), this);
               g2.drawImage(unitImage, (x*Constants.GRID_SIZE), (y*Constants.GRID_SIZE + this.model.getMovementAmountY(units[i])), this);
               g2.setTransform(aT);
            } else
            {  g2.drawImage(unitImage, (x*Constants.GRID_SIZE + this.model.getMovementAmountX(units[i])), (y*Constants.GRID_SIZE + this.model.getMovementAmountY(units[i])), this);
            }
         } else if (units[i].getStatus().equals(Constants.LEFT) || units[i].getStatus().equals(Constants.RIGHT)|| units[i].getStatus().equals(Constants.RIGHT_45)|| units[i].getStatus().equals(Constants.LEFT_45))
         {  if (bodyDegree != 0)//TURNING
            {  AffineTransform aT = g2.getTransform();
               AffineTransform unitDraw = AffineTransform.getRotateInstance(bodyDegree, x*Constants.GRID_SIZE + Constants.GRID_SIZE/2, y*Constants.GRID_SIZE + Constants.GRID_SIZE/2);
               g2.transform(unitDraw);
               g2.drawImage(unitImage, (x*Constants.GRID_SIZE), (y*Constants.GRID_SIZE), this);
               g2.setTransform(aT);
            } else
            {  g2.drawImage(unitImage, (x*Constants.GRID_SIZE), (y*Constants.GRID_SIZE), this);
            }
         } else if (units[i].getStatus().equals(Constants.HARVESTING))
         {  //GET HARVESTOR HARVESTING IMAGE
            unitImage = this.imageInfo.getImage(units[i].getStatus(), Constants.ORDOS);
            if (bodyDegree != 0) //HARVESTOR HARVESTING
            {  AffineTransform aT = g2.getTransform();
               AffineTransform unitDraw = AffineTransform.getRotateInstance(bodyDegree, x*Constants.GRID_SIZE + Constants.GRID_SIZE/2, y*Constants.GRID_SIZE + Constants.GRID_SIZE/2);
               g2.transform(unitDraw);
               g2.drawImage(unitImage, (x*Constants.GRID_SIZE), (y*Constants.GRID_SIZE), this);
               g2.setTransform(aT);
            } else
            {  g2.drawImage(unitImage, (x*Constants.GRID_SIZE + this.model.getMovementAmountX(units[i])), (y*Constants.GRID_SIZE + this.model.getMovementAmountY(units[i])), this);
            }
         }


         if (units[i].getStatus().equals(Constants.STOP) && units[i].getTurretDegree() >= 0)
         {  double turretDegree = units[i].getTurretDegree() * (Math.PI / 180); //Draw TURRET STOP
            BufferedImage turretsImage = this.imageInfo.getImage(units[i].getName(), null);
            AffineTransform aT = g2.getTransform();
            AffineTransform turretDraw = AffineTransform.getRotateInstance(turretDegree, x*Constants.GRID_SIZE + Constants.GRID_SIZE/2, y*Constants.GRID_SIZE + Constants.GRID_SIZE/2);
            g2.transform(turretDraw);
            g2.drawImage(turretsImage, x*Constants.GRID_SIZE, y*Constants.GRID_SIZE, this);
            g2.setTransform(aT);
         } else if (units[i].getStatus().equals(Constants.MOVE) && units[i].getTurretDegree() >= 0)
         {  double turretDegree = units[i].getTurretDegree() * (Math.PI / 180); //Draw TURRET MOVE
            BufferedImage turretImage = this.imageInfo.getImage(units[i].getName(), null);
            AffineTransform aT = g2.getTransform();
            AffineTransform turretDraw = AffineTransform.getRotateInstance(turretDegree, x*Constants.GRID_SIZE + Constants.GRID_SIZE/2, y*Constants.GRID_SIZE + Constants.GRID_SIZE/2);
            g2.transform(turretDraw);

            int bodyDegree2 = units[i].getBodyDegree();

            int unitProgress = units[i].getProgress();
            //int offSetX = this.getOffSetX(bodyDegree2, unitProgress);
            //int offSetY = this.getOffSetY(bodyDegree2, unitProgress);
            int offSetX = 0;
            int offSetY = 0;

            double drawDegree = units[i].getBodyDegree() - units[i].getTurretDegree();
            if (drawDegree < 0)
            {  drawDegree = drawDegree + 360;
            }
            double tempDegree = drawDegree * (Math.PI / 180);
            if (drawDegree >=0 && drawDegree <= 90)
            {  offSetX = (int)Math.abs((unitProgress * Math.sin(tempDegree)));
               offSetY = -(int)Math.abs((unitProgress * Math.cos(tempDegree)));
            } else if (drawDegree > 90 && drawDegree <= 180)
            {  offSetX = (int)Math.abs((unitProgress * Math.sin(tempDegree)));
               offSetY = (int)Math.abs((unitProgress * Math.cos(tempDegree)));
            } else if (drawDegree > 180 && drawDegree <= 270)
            {  offSetX = -(int)Math.abs((unitProgress * Math.sin(tempDegree)));
               offSetY = (int)Math.abs((unitProgress * Math.cos(tempDegree)));
            } else if (drawDegree > 270 && drawDegree < 360)
            {  offSetX = -(int)Math.abs((unitProgress * Math.sin(tempDegree)));
               offSetY = -(int)Math.abs((unitProgress * Math.cos(tempDegree)));
            }

            g2.drawImage(turretImage, (x*Constants.GRID_SIZE + offSetX), (y*Constants.GRID_SIZE + offSetY), this);
            g2.setTransform(aT);
         } else if ((units[i].getStatus().equals(Constants.LEFT) || units[i].getStatus().equals(Constants.RIGHT) || units[i].getStatus().equals(Constants.LEFT_45) || units[i].getStatus().equals(Constants.RIGHT_45)) && units[i].getTurretDegree() >= 0)
         {  double turretDegree = units[i].getTurretDegree() * (Math.PI / 180); //Draw TURRET TURN
            BufferedImage turretImage = this.imageInfo.getImage(units[i].getName(), null);
            AffineTransform aT = g2.getTransform();
            AffineTransform turretDraw = AffineTransform.getRotateInstance(turretDegree, x*Constants.GRID_SIZE + Constants.GRID_SIZE/2, y*Constants.GRID_SIZE + Constants.GRID_SIZE/2);
            g2.transform(turretDraw);
            g2.drawImage(turretImage, x*Constants.GRID_SIZE, y*Constants.GRID_SIZE, this);
            g2.setTransform(aT);
         }
      }




      //Harkonnen' units
      numUnits = this.model.getNumUnits(Constants.HARKONNEN);
      units = this.model.getUnits(Constants.HARKONNEN);

      for (int i=0; i < numUnits; i++)
      {  BufferedImage unitImage = this.imageInfo.getImage(units[i].getName(), Constants.HARKONNEN);
         int x = units[i].getCurrentX();
         int y = units[i].getCurrentY();

         //DRAW UNIT HP BAR
         this.drawUnitHpBar(units[i], Color.red, g2, x, y);

         double bodyDegree = units[i].getBodyDegree() * (Math.PI / 180);
         if (units[i].getStatus().equals(Constants.STOP)) //STOPPING
         {  if (bodyDegree != 0)
            {  AffineTransform aT = g2.getTransform();
               AffineTransform unitDraw = AffineTransform.getRotateInstance(bodyDegree, x*Constants.GRID_SIZE + Constants.GRID_SIZE/2, y*Constants.GRID_SIZE + Constants.GRID_SIZE/2);
               g2.transform(unitDraw);
               g2.drawImage(unitImage, x*Constants.GRID_SIZE, y*Constants.GRID_SIZE, this);
               g2.setTransform(aT);
            } else
            {  g2.drawImage(unitImage, x*Constants.GRID_SIZE, y*Constants.GRID_SIZE, this);
            }
         } else if (units[i].getStatus().equals(Constants.MOVE)) //MOVING
         {  if (bodyDegree != 0)
            {  AffineTransform aT = g2.getTransform();
               AffineTransform unitDraw = AffineTransform.getRotateInstance(bodyDegree, x*Constants.GRID_SIZE + Constants.GRID_SIZE/2, y*Constants.GRID_SIZE + Constants.GRID_SIZE/2);
               g2.transform(unitDraw);
               //g2.drawImage(unitImage, (x*Constants.GRID_SIZE + this.model.getMovementAmountX(units[i])), (y*Constants.GRID_SIZE + this.model.getMovementAmountY(units[i])), this);
               g2.drawImage(unitImage, (x*Constants.GRID_SIZE), (y*Constants.GRID_SIZE + this.model.getMovementAmountY(units[i])), this);
               g2.setTransform(aT);
            } else
            {  g2.drawImage(unitImage, (x*Constants.GRID_SIZE + this.model.getMovementAmountX(units[i])), (y*Constants.GRID_SIZE + this.model.getMovementAmountY(units[i])), this);
            }
         } else if (units[i].getStatus().equals(Constants.LEFT) || units[i].getStatus().equals(Constants.RIGHT)|| units[i].getStatus().equals(Constants.RIGHT_45)|| units[i].getStatus().equals(Constants.LEFT_45))
         {  if (bodyDegree != 0)//TURNING
            {  AffineTransform aT = g2.getTransform();
               AffineTransform unitDraw = AffineTransform.getRotateInstance(bodyDegree, x*Constants.GRID_SIZE + Constants.GRID_SIZE/2, y*Constants.GRID_SIZE + Constants.GRID_SIZE/2);
               g2.transform(unitDraw);
               g2.drawImage(unitImage, (x*Constants.GRID_SIZE), (y*Constants.GRID_SIZE), this);
               g2.setTransform(aT);
            } else
            {  g2.drawImage(unitImage, (x*Constants.GRID_SIZE), (y*Constants.GRID_SIZE), this);
            }
         } else if (units[i].getStatus().equals(Constants.HARVESTING))
         {  //GET HARVESTOR HARVESTING IMAGE
            unitImage = this.imageInfo.getImage(units[i].getStatus(), Constants.HARKONNEN);
            if (bodyDegree != 0) //HARVESTOR HARVESTING
            {  AffineTransform aT = g2.getTransform();
               AffineTransform unitDraw = AffineTransform.getRotateInstance(bodyDegree, x*Constants.GRID_SIZE + Constants.GRID_SIZE/2, y*Constants.GRID_SIZE + Constants.GRID_SIZE/2);
               g2.transform(unitDraw);
               g2.drawImage(unitImage, (x*Constants.GRID_SIZE), (y*Constants.GRID_SIZE), this);
               g2.setTransform(aT);
            } else
            {  g2.drawImage(unitImage, (x*Constants.GRID_SIZE + this.model.getMovementAmountX(units[i])), (y*Constants.GRID_SIZE + this.model.getMovementAmountY(units[i])), this);
            }
         }


         if (units[i].getStatus().equals(Constants.STOP) && units[i].getTurretDegree() >= 0)
         {  double turretDegree = units[i].getTurretDegree() * (Math.PI / 180); //Draw TURRET STOP
            BufferedImage turretsImage = this.imageInfo.getImage(units[i].getName(), null);
            AffineTransform aT = g2.getTransform();
            AffineTransform turretDraw = AffineTransform.getRotateInstance(turretDegree, x*Constants.GRID_SIZE + Constants.GRID_SIZE/2, y*Constants.GRID_SIZE + Constants.GRID_SIZE/2);
            g2.transform(turretDraw);
            g2.drawImage(turretsImage, x*Constants.GRID_SIZE, y*Constants.GRID_SIZE, this);
            g2.setTransform(aT);
         } else if (units[i].getStatus().equals(Constants.MOVE) && units[i].getTurretDegree() >= 0)
         {  double turretDegree = units[i].getTurretDegree() * (Math.PI / 180); //Draw TURRET MOVE
            BufferedImage turretImage = this.imageInfo.getImage(units[i].getName(), null);
            AffineTransform aT = g2.getTransform();
            AffineTransform turretDraw = AffineTransform.getRotateInstance(turretDegree, x*Constants.GRID_SIZE + Constants.GRID_SIZE/2, y*Constants.GRID_SIZE + Constants.GRID_SIZE/2);
            g2.transform(turretDraw);

            int bodyDegree2 = units[i].getBodyDegree();

            int unitProgress = units[i].getProgress();
            //int offSetX = this.getOffSetX(bodyDegree2, unitProgress);
            //int offSetY = this.getOffSetY(bodyDegree2, unitProgress);
            int offSetX = 0;
            int offSetY = 0;

            double drawDegree = units[i].getBodyDegree() - units[i].getTurretDegree();
            if (drawDegree < 0)
            {  drawDegree = drawDegree + 360;
            }
            double tempDegree = drawDegree * (Math.PI / 180);
            if (drawDegree >=0 && drawDegree <= 90)
            {  offSetX = (int)Math.abs((unitProgress * Math.sin(tempDegree)));
               offSetY = -(int)Math.abs((unitProgress * Math.cos(tempDegree)));
            } else if (drawDegree > 90 && drawDegree <= 180)
            {  offSetX = (int)Math.abs((unitProgress * Math.sin(tempDegree)));
               offSetY = (int)Math.abs((unitProgress * Math.cos(tempDegree)));
            } else if (drawDegree > 180 && drawDegree <= 270)
            {  offSetX = -(int)Math.abs((unitProgress * Math.sin(tempDegree)));
               offSetY = (int)Math.abs((unitProgress * Math.cos(tempDegree)));
            } else if (drawDegree > 270 && drawDegree < 360)
            {  offSetX = -(int)Math.abs((unitProgress * Math.sin(tempDegree)));
               offSetY = -(int)Math.abs((unitProgress * Math.cos(tempDegree)));
            }

            g2.drawImage(turretImage, (x*Constants.GRID_SIZE + offSetX), (y*Constants.GRID_SIZE + offSetY), this);
            g2.setTransform(aT);
         } else if ((units[i].getStatus().equals(Constants.LEFT) || units[i].getStatus().equals(Constants.RIGHT) || units[i].getStatus().equals(Constants.LEFT_45) || units[i].getStatus().equals(Constants.RIGHT_45)) && units[i].getTurretDegree() >= 0)
         {  double turretDegree = units[i].getTurretDegree() * (Math.PI / 180); //Draw TURRET TURN
            BufferedImage turretImage = this.imageInfo.getImage(units[i].getName(), null);
            AffineTransform aT = g2.getTransform();
            AffineTransform turretDraw = AffineTransform.getRotateInstance(turretDegree, x*Constants.GRID_SIZE + Constants.GRID_SIZE/2, y*Constants.GRID_SIZE + Constants.GRID_SIZE/2);
            g2.transform(turretDraw);
            g2.drawImage(turretImage, x*Constants.GRID_SIZE, y*Constants.GRID_SIZE, this);
            g2.setTransform(aT);
         }
      }
   }

   private void drawBuildings(Graphics2D g2)
   {
      //Atreides' buildings
      int numBuildings = this.model.getNumBuildings(Constants.ATREIDES);
      Building[] buildings = this.model.getBuildings(Constants.ATREIDES);

      for (int i=0; i < numBuildings; i++)
      {  BufferedImage buildingImage = this.imageInfo.getImage(buildings[i].getName(), Constants.ATREIDES);
         int x = buildings[i].getX();
         int y = buildings[i].getY();

         if (buildings[i].getStatus().equals(Constants.STOP)) //STOPPING
         {  //g2.drawImage(buildingImage, x*Constants.GRID_SIZE, y*Constants.GRID_SIZE, this);
            g2.drawImage(buildingImage, x*Constants.GRID_SIZE, y*Constants.GRID_SIZE, this);
            BufferedImage bImage2 = this.imageInfo.getImage(buildings[i].getName(), Constants.ATREIDES, buildings[i].getBuildingLevel());
            if (bImage2 != null)
            {  g2.drawImage(bImage2, x*Constants.GRID_SIZE, y*Constants.GRID_SIZE, this);
            }
         } else if (buildings[i].getStatus().equals(Constants.MOVE)) //BUILDING
         {  g2.drawImage(buildingImage, x*Constants.GRID_SIZE, y*Constants.GRID_SIZE, this);
            BufferedImage bImage2 = this.imageInfo.getImage(buildings[i].getName(), Constants.ATREIDES, buildings[i].getBuildingLevel());
            if (bImage2 != null)
            {  g2.drawImage(bImage2, x*Constants.GRID_SIZE, y*Constants.GRID_SIZE, this);
            }
         }

         if (buildings[i].getHarvestorDocked())
         {  BufferedImage harvestorDockedImage = this.imageInfo.getImage(Constants.HARVESTOR_DOCKED, Constants.ATREIDES);
            g2.drawImage(harvestorDockedImage, x*Constants.GRID_SIZE, y*Constants.GRID_SIZE, this);
         }
      }




      //Ordos' buildings
      numBuildings = this.model.getNumBuildings(Constants.ORDOS);
      buildings = this.model.getBuildings(Constants.ORDOS);

      for (int i=0; i < numBuildings; i++)
      {  BufferedImage buildingImage = this.imageInfo.getImage(buildings[i].getName(), Constants.ORDOS);
         int x = buildings[i].getX();
         int y = buildings[i].getY();

         if (buildings[i].getStatus().equals(Constants.STOP)) //STOPPING
         {  //g2.drawImage(buildingImage, x*Constants.GRID_SIZE, y*Constants.GRID_SIZE, this);
            g2.drawImage(buildingImage, x*Constants.GRID_SIZE, y*Constants.GRID_SIZE, this);
            BufferedImage bImage2 = this.imageInfo.getImage(buildings[i].getName(), Constants.ORDOS, buildings[i].getBuildingLevel());
            if (bImage2 != null)
            {  g2.drawImage(bImage2, x*Constants.GRID_SIZE, y*Constants.GRID_SIZE, this);
            }
         } else if (buildings[i].getStatus().equals(Constants.MOVE)) //BUILDING
         {  g2.drawImage(buildingImage, x*Constants.GRID_SIZE, y*Constants.GRID_SIZE, this);
            BufferedImage bImage2 = this.imageInfo.getImage(buildings[i].getName(), Constants.ORDOS, buildings[i].getBuildingLevel());
            if (bImage2 != null)
            {  g2.drawImage(bImage2, x*Constants.GRID_SIZE, y*Constants.GRID_SIZE, this);
            }
         }

         if (buildings[i].getHarvestorDocked())
         {  BufferedImage harvestorDockedImage = this.imageInfo.getImage(Constants.HARVESTOR_DOCKED, Constants.ORDOS);
            g2.drawImage(harvestorDockedImage, x*Constants.GRID_SIZE, y*Constants.GRID_SIZE, this);
         }
      }




      //Harkonnen' buildings
      numBuildings = this.model.getNumBuildings(Constants.HARKONNEN);
      buildings = this.model.getBuildings(Constants.HARKONNEN);

      for (int i=0; i < numBuildings; i++)
      {  BufferedImage buildingImage = this.imageInfo.getImage(buildings[i].getName(), Constants.HARKONNEN);
         int x = buildings[i].getX();
         int y = buildings[i].getY();

         if (buildings[i].getStatus().equals(Constants.STOP)) //STOPPING
         {  //g2.drawImage(buildingImage, x*Constants.GRID_SIZE, y*Constants.GRID_SIZE, this);
            g2.drawImage(buildingImage, x*Constants.GRID_SIZE, y*Constants.GRID_SIZE, this);
            BufferedImage bImage2 = this.imageInfo.getImage(buildings[i].getName(), Constants.HARKONNEN, buildings[i].getBuildingLevel());
            if (bImage2 != null)
            {  g2.drawImage(bImage2, x*Constants.GRID_SIZE, y*Constants.GRID_SIZE, this);
            }
         } else if (buildings[i].getStatus().equals(Constants.MOVE)) //BUILDING
         {  g2.drawImage(buildingImage, x*Constants.GRID_SIZE, y*Constants.GRID_SIZE, this);
            BufferedImage bImage2 = this.imageInfo.getImage(buildings[i].getName(), Constants.HARKONNEN, buildings[i].getBuildingLevel());
            if (bImage2 != null)
            {  g2.drawImage(bImage2, x*Constants.GRID_SIZE, y*Constants.GRID_SIZE, this);
            }
         }

         if (buildings[i].getHarvestorDocked())
         {  BufferedImage harvestorDockedImage = this.imageInfo.getImage(Constants.HARVESTOR_DOCKED, Constants.HARKONNEN);
            g2.drawImage(harvestorDockedImage, x*Constants.GRID_SIZE, y*Constants.GRID_SIZE, this);
         }
      }
   }

   private class WorldViewMouseListener implements MouseListener
   {  public void mouseClicked(MouseEvent evt)
      {
      }

      public void mousePressed(MouseEvent evt)
      {  int x = evt.getX();
         int y = evt.getY();
         startX = x;//evt.getX();
         startY = y;//evt.getY();
         endX = x;
         endY = y;
         drawX = x;
         drawY = y;
         lengthDrawX = 0;
         lengthDrawY = 0;

         //Unit unit = model.returnUnitNearXY(x, y);
         //FINISH ABOVE METHOD LATER...MORE EXACT CLICKING
         x = x / Constants.GRID_SIZE;
         y = y / Constants.GRID_SIZE;

         if (model.doesSelectedUnitNeedMovingCoordinates())
         {  model.setSelectedUnitsGoto(x, y);
            model.setSelectedUnitNotMoving();
            model.setMovementIndicatorXY(x, y);
            model.setMovementIndicatorTime();
            givenCommandThisPress = true;
         } else if (model.getPlacingBuilding())
         {  if (model.checkPlaceBuildingAt(x, y))
            {  model.placeBuildingAt(x, y);
            }
            givenCommandThisPress = true;
         } else if (model.doesSelectedUnitNeedHarvestCoordinates())
         {  model.setSelectedUnitsGotoHarvest(x, y);
            model.setSelectedUnitNotHarvest();
            givenCommandThisPress = true;
         } else if (model.doesSelectedUnitNeedAttackCoordinates())
         {  model.setSelectedUnitsAttackAt(x, y);
            model.setSelectedUnitNotAttack();
            model.setAttackIndicatorXY(x, y);
            model.setAttackIndicatorTime();
            givenCommandThisPress = true;
         }else
         {  drawBoxSelector = true;
            //Unit unit = model.getUnitAt(x, y, 0);
            //model.setSelectedUnit(unit);
            //Building building = model.getBuildingAt(x, y, 0);
            //model.setSelectedBuilding(building);
         }
      }

      public void mouseReleased(MouseEvent evt)
      {  //releasedX = evt.getX();
         //releasedY = evt.getY();

         if (!givenCommandThisPress && !model.doesSelectedUnitNeedMovingCoordinates() && !model.getPlacingBuilding() && !model.doesSelectedUnitNeedHarvestCoordinates() && !model.doesSelectedUnitNeedAttackCoordinates())
         {  int x1 = startX / Constants.GRID_SIZE;
            int y1 = startY / Constants.GRID_SIZE;
            int x2 = endX / Constants.GRID_SIZE;
            int y2 = endY / Constants.GRID_SIZE;
            model.setSelectedUnitsOrBuilding(x1, y1, x2, y2);
            drawBoxSelector = false;
         } else
         {
         }

      givenCommandThisPress = false;
      }

      public void mouseEntered(MouseEvent evt)
      {
      }

      public void mouseExited(MouseEvent evt)
      {
      }
   }

   private class WorldViewMouseMotionListener implements MouseMotionListener
   {  public void mouseMoved(MouseEvent evt)
      {  mouseX = evt.getX();
         mouseY = evt.getY();
      }

      /* Note:  This code is somewhat flawed...if I move the cursor around very
         quickly, the box starting position tends to shift around the map, although
         it nevers shifts too far away from the starting position...not important
         enough now to worry about, fix it l8ter.
      */
      public void mouseDragged(MouseEvent evt)
      {  endX = evt.getX();
         endY = evt.getY();
         if (drawBoxSelector)
         {  lengthDrawX = endX - startX;
            lengthDrawY = endY - startY;


            if (lengthDrawX < 0 && lengthDrawY >=0)
            {  lengthDrawX = -(lengthDrawX);
               drawX = endX;
            } else if (lengthDrawX >= 0 && lengthDrawY < 0)
            {  lengthDrawY = -(lengthDrawY);
               drawY = endY;
            } else if (lengthDrawX < 0 && lengthDrawY < 0)
            {  lengthDrawX = -(lengthDrawX);
               lengthDrawY = -(lengthDrawY);
               drawX = endX;
               drawY = endY;
            } else
            {  drawX = startX;
               drawY = startY;
            }

            repaint();
         }
      }
   }
}
