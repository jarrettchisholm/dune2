package Dune2.TextInput;

import java.io.*;
import becker.io.TextInput;

public class IBETextInput extends TextInput
{  private String path;
   private FileInputStream in;
   private DataInputStream data;

   public IBETextInput(String path)
   {  super(path);

      /*
      this.path = path;
      try
      {  this.in = new FileInputStream(path);
      } catch (Exception e)
      {  System.out.println("IBE --> IOError: " + e.toString());

      }

      this.data = new DataInputStream(this.in);
      */
   }

   /*  Does the same thing as readLine from becker.io.TextInput, but has an unlimited [buffer] length
   */
   public String readLongLine()
   {  String text = "";
      while(!super.eolIsAvailable())
      {  if (super.tokenIsAvailable())
         {  text = text + super.readToken() + " ";
         } else
         {  break;
         }
      }
      super.readLine();
      return text;
   }

   /*
   public String readLine()
   {  try
      {  String input = this.data.readLine();
         return input;
      } catch (Exception e)
      {  System.out.println("IBE --> IOError: " + e.toString());
      }

      return null;
   }

   public int readInt()
   {  try
      {  String input = this.data.readLine();
         System.out.println(input);
         int number = this.toInt(input);
         return number;
      } catch (Exception e)
      {  System.out.println("IBE --> IOError: " + e.toString());
      }

      return -1;
   }

   private int toInt(String number)
   {  int returnable = 0;
      for (int i=0; i < number.length(); i++)
      {  if (number.charAt(i) == '0')
         {  returnable = returnable + 0;
         } else if (number.charAt(i) == '1')
         {  returnable = returnable + 1;
         } else if (number.charAt(i) == '2')
         {  returnable = returnable + 2;
         } else if (number.charAt(i) == '3')
         {  returnable = returnable + 3;
         } else if (number.charAt(i) == '4')
         {  returnable = returnable + 4;
         } else if (number.charAt(i) == '5')
         {  returnable = returnable + 5;
         } else if (number.charAt(i) == '6')
         {  returnable = returnable + 6;
         } else if (number.charAt(i) == '7')
         {  returnable = returnable + 7;
         } else if (number.charAt(i) == '8')
         {  returnable = returnable + 8;
         } else if (number.charAt(i) == '9')
         {  returnable = returnable + 9;
         }
         System.out.print(returnable + ": ");
      }

      return returnable;
   }

   public double readDouble()
   {  try
      {  return this.data.readDouble();
      } catch (Exception e)
      {  System.out.println("IBE --> IOError: " + e.toString());
      }

      return -1.00;
   }

   public void close()
   {  try
      {  this.in.close();
      } catch (Exception e)
      {  System.out.println("IBE --> IOError" + e.toString());
      }
   }
   */
} 