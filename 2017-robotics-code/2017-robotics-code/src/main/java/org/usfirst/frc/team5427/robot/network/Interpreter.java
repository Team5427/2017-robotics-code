package org.usfirst.frc.team5427.robot.network;

import java.io.*;
import java.lang.invoke.SerializedLambda;

/**
 * Interprets byte arrays received over the network
 */
public abstract class Interpreter {

    abstract public void interpret(byte[] buff, int numFromStream);

    public static String getStringByteBuffer(byte[] buff) {
        String str = "[";

        for (int i = 0; i < buff.length; i++)
            str += buff[i] + ",";

        return str + "]";
    }

    // TODO figure out what this does
    public static byte[] getBufferedSegment(byte[] buff, int startPos, int length) {
        byte[] temp = new byte[length];

        for (int i = 0; i < length; i++) {
            System.out.println("buffereing");
            temp[i] = buff[startPos + i];
        }

        return temp;
    }

    /**
     * Adds the elements of the reference byte array to the target array at a given index
     * target array has to be at least the size of the reference array, and the index given
     * to the length of the reference must exist in the target array
     *
     * @param target    Array to add elements from the reference array
     * @param reference Array used to add to the target
     * @param index
     */
    public static void addByteArray(byte[] target, byte[] reference, int index) {
        int endIndex = index + reference.length;
        for (int i = index; i < endIndex; i++) {
            target[i] = reference[i - index];
        }
    }

    /**
     * Merges a byte array to a target byte array. The target must have the appropriate size to fit the elements of
     * the reference array
     *
     * @param target              Array to add elements from the reference array
     * @param startTargetIndex    Starting index to add in target array
     * @param reference           Array used to add to the target
     * @param startReferenceIndex Starting index of reference to add to target
     * @param lengthReference     Number of elements in reference from startReferenceIndex to add to target
     */
    public static void addByteArray(byte[] target, int startTargetIndex, byte[] reference,
                                    int startReferenceIndex, int lengthReference) {

        for (int i = startReferenceIndex; i < lengthReference; i++) {
            target[i - startReferenceIndex] = reference[i];
        }
    }

    public static byte[] merge(byte[]... arr) {
        int size = 0;
        for (byte[] i : arr) {
            size += i.length;
        }

        byte[] buff = new byte[size];
        int index = 0;
        for (byte[] i : arr) {
            for (byte j : i) {
                buff[index++] = j;
            }
        }
        // TODO finish merging arrays

        return buff;
    }

    public static int byteArrayToInt(byte[] b)
    {
        return   b[3] & 0xFF |
                (b[2] & 0xFF) << 8 |
                (b[1] & 0xFF) << 16 |
                (b[0] & 0xFF) << 24;
    }

    public static byte[] intToByteArray(int a)
    {
        return new byte[] {
                (byte) ((a >> 24) & 0xFF),
                (byte) ((a >> 16) & 0xFF),
                (byte) ((a >> 8) & 0xFF),
                (byte) (a & 0xFF)
        };
    }

    public static byte[] longToBytes(long l) {
        byte[] val = new byte[8];
        for (int i = 7; i >= 0; i--) {
            val[i] = (byte)(l & 0xFF);
            l >>= 8;
        }
        return val;
    }

    public static long bytesToLong(byte[] b) {
        long val = 0;
        for (int i = 0; i < 8; i++) {
            val <<= 8;
            val |= (b[i] & 0xFF);
        }
        return val;
    }

    public static byte[] doubleToBytes(double d) {
        return longToBytes(Double.doubleToRawLongBits(d));
    }

    public static double bytesToDouble(byte[] b) {
        return Double.longBitsToDouble(bytesToLong(b));
    }

    /**
     * Returns a serializeable object to a byte array
     *
     * @param obj to be converted to a byte array
     * @return byte array of object
     */
    public static byte[] serialize(Serializable obj) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput objOut = null;
        byte[] buff = null;

        try {
            objOut = new ObjectOutputStream(bos);
            objOut.writeObject(obj);
            objOut.flush();
            buff = bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bos.close();
                objOut.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return buff;
    }

    /**
     * Returns object from a byte array
     *
     * @param buffer byte array to convert into an object
     * @return object from the byte array
     */
    public static Object deserialize(byte[] buffer) {
        ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
        ObjectInputStream is = null;
        Object object = null;

        try {
            is = new ObjectInputStream(bais);
            object = is.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bais.close();
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return object;
    }

    /**
     * Returns object from a byte array
     *
     * @param buffer byte array to convert into an object
     * @param startingIndex index to begin deserialize
     * @param length of buffer to deserialize
     * @return object from the byte array
     */
    public static Object deserialize(byte[] buffer, int startingIndex, int length) {
        byte[] sendBuffer = new byte[length];
        for (int i = 0; i < length; i++) {
            sendBuffer[i] = buffer[i + startingIndex];
        }

        return deserialize(sendBuffer);
    }

    public static String toStringByteArray(byte[] buff) {
        String str = "[";
        int length = buff.length - 1;
        for (int i = 0; i < length; i++) {
            str += Integer.toHexString(buff[i]) + ", ";
        }

        str += Integer.toHexString(buff.length - 1) + "]";

        return str;
    }
}