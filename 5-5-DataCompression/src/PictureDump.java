import java.awt.Color;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.StdOut;

public class PictureDump {

	public static void main(String[] args) {
		int width = Integer.parseInt(args[0]);
        int height = Integer.parseInt(args[1]);
        Picture pic = new Picture(width, height);
        int count = 0;
        /*按照width列，height行打印，1为黑色，0位白色*/
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                pic.set(j, i, Color.RED);
                if (!BinaryStdIn.isEmpty()) {
                    count++;
                    boolean bit = BinaryStdIn.readBoolean();
                    if (bit) pic.set(j, i, Color.BLACK);//1为黑色
                    else     pic.set(j, i, Color.WHITE);//0为白色
                }
            }
        }
        pic.show();
        StdOut.println(count + " bits");

	}

}
