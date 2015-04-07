package cancer;

import java.io.*;

/**
 * @title Cancer
 * @author Steven Biro
 * @teacher Mr. J. Carron
 * @date 25-Mar-2015 12:23:37 PM
 * @purpose The purpose of this program is to count the cancer spots in a given file.
 */
public class Cancer {

    public static String grid[][] = new String[15][14];//setup array, accounting for extra blank created later
    public static int tumorSpots = 0;

    public static void main(String[] args) {
        int x = 0;
        String display = "", line;
        try {
            BufferedReader br = new BufferedReader(//get file 
                    new FileReader("file.txt"));
            while ((line = br.readLine()) != null) {
                grid[x] = line.split("");//split the line into elements, this creates an empty emlement at index 0
                x++;
            }

        } catch (FileNotFoundException ex) {//catch errors associated with reading files
            System.out.println("error - cant find file");
            System.exit(1);
        } catch (IOException ex) {
            System.out.println("error - IOException");
            System.exit(2);
        }

        for (int i = 0; i < 15; i++) {//build, then display the grid
            for (int j = 0; j < 15; j++) {

                display += grid[i][j];

            }
            display += "\n";

        }
        System.out.print(display);
        display = "";//reser display

        for (int i = 0; i < 15; i++) {//calculate the new display and then build it
            for (int j = 0; j < 15; j++) {
                if ("-".equals(grid[i][j])) {
                    floodFill(i, j);
                    tumorSpots++;//add up the times his is excecuted, this is the amount of cancer spots.
                }
                display += grid[i][j];

            }
            display += "\n";

        }
//output the amount of cancer spots and the new file, with the spots missing.
        System.out.println("The file had " + tumorSpots + " cancer spots in it\nThe new grid is:");
        System.out.println(display);

    }

    public static void floodFill(int row, int col) {

        if (grid[row][col].equals("-")) {//change "-" to " "
            grid[row][col] = " ";
            floodFill(row - 1, col - 1);//do this for all adjacent places, excecuting this again.
            floodFill(row - 1, col);
            floodFill(row - 1, col + 1);
            floodFill(row, col - 1);
            floodFill(row, col + 1);
            floodFill(row + 1, col - 1);
            floodFill(row + 1, col);
            floodFill(row + 1, col + 1);

        }

    }

}
