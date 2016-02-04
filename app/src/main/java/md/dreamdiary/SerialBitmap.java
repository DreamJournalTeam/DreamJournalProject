package md.dreamdiary;

import java.io.ObjectInputStream;
import java.io.Serializable;
import android.graphics.*;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;

/**
 * Created by michaeldiamond on 12/3/15.
 */


public class SerialBitmap implements Serializable {

    public Bitmap bitmap;

    // TODO: Finish this constructor
    SerialBitmap(Bitmap b) {
        bitmap = b;
    }

    public Bitmap getImage() {
        return bitmap;
    }

    public void setBitmap(Bitmap bit) {
        bitmap = bit;
    }

    // Converts the Bitmap into a byte array for serialization
    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteStream);
        byte bitmapBytes[] = byteStream.toByteArray();
        out.write(bitmapBytes, 0, bitmapBytes.length);
    }

    // Deserializes a byte array representing the Bitmap and decodes it
    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        int b;
        while((b = in.read()) != -1)
            byteStream.write(b);
        byte bitmapBytes[] = byteStream.toByteArray();
        bitmap = BitmapFactory.decodeByteArray(bitmapBytes, 0, bitmapBytes.length);
    }
}