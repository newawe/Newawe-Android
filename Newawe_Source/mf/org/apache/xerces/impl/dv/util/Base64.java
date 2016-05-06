package mf.org.apache.xerces.impl.dv.util;

import org.apache.commons.lang.CharUtils;
import org.apache.commons.lang.StringUtils;

public final class Base64 {
    private static final int BASELENGTH = 128;
    private static final int EIGHTBIT = 8;
    private static final int FOURBYTE = 4;
    private static final int LOOKUPLENGTH = 64;
    private static final char PAD = '=';
    private static final int SIGN = -128;
    private static final int SIXBIT = 6;
    private static final int SIXTEENBIT = 16;
    private static final int TWENTYFOURBITGROUP = 24;
    private static final byte[] base64Alphabet;
    private static final boolean fDebug = false;
    private static final char[] lookUpBase64Alphabet;

    static {
        int i;
        base64Alphabet = new byte[BASELENGTH];
        lookUpBase64Alphabet = new char[LOOKUPLENGTH];
        for (i = 0; i < BASELENGTH; i++) {
            base64Alphabet[i] = (byte) -1;
        }
        for (i = 90; i >= 65; i--) {
            base64Alphabet[i] = (byte) (i - 65);
        }
        for (i = 122; i >= 97; i--) {
            base64Alphabet[i] = (byte) ((i - 97) + 26);
        }
        for (i = 57; i >= 48; i--) {
            base64Alphabet[i] = (byte) ((i - 48) + 52);
        }
        base64Alphabet[43] = (byte) 62;
        base64Alphabet[47] = (byte) 63;
        for (i = 0; i <= 25; i++) {
            lookUpBase64Alphabet[i] = (char) (i + 65);
        }
        i = 26;
        int j = 0;
        while (i <= 51) {
            lookUpBase64Alphabet[i] = (char) (j + 97);
            i++;
            j++;
        }
        i = 52;
        j = 0;
        while (i <= 61) {
            lookUpBase64Alphabet[i] = (char) (j + 48);
            i++;
            j++;
        }
        lookUpBase64Alphabet[62] = '+';
        lookUpBase64Alphabet[63] = '/';
    }

    protected static boolean isWhiteSpace(char octect) {
        return octect == ' ' || octect == CharUtils.CR || octect == '\n' || octect == '\t';
    }

    protected static boolean isPad(char octect) {
        return octect == PAD;
    }

    protected static boolean isData(char octect) {
        return octect < '\u0080' && base64Alphabet[octect] != -1;
    }

    protected static boolean isBase64(char octect) {
        return isWhiteSpace(octect) || isPad(octect) || isData(octect);
    }

    public static String encode(byte[] binaryData) {
        if (binaryData == null) {
            return null;
        }
        int lengthDataBits = binaryData.length * EIGHTBIT;
        if (lengthDataBits == 0) {
            return StringUtils.EMPTY;
        }
        int numberQuartet;
        byte val1;
        int fewerThan24bits = lengthDataBits % TWENTYFOURBITGROUP;
        int numberTriplets = lengthDataBits / TWENTYFOURBITGROUP;
        if (fewerThan24bits != 0) {
            numberQuartet = numberTriplets + 1;
        } else {
            numberQuartet = numberTriplets;
        }
        char[] encodedData = new char[(numberQuartet * FOURBYTE)];
        int i = 0;
        int dataIndex = 0;
        int encodedIndex = 0;
        while (i < numberTriplets) {
            byte val2;
            byte val3;
            int dataIndex2 = dataIndex + 1;
            byte b1 = binaryData[dataIndex];
            dataIndex = dataIndex2 + 1;
            byte b2 = binaryData[dataIndex2];
            dataIndex2 = dataIndex + 1;
            byte b3 = binaryData[dataIndex];
            byte l = (byte) (b2 & 15);
            byte k = (byte) (b1 & 3);
            if ((b1 & SIGN) == 0) {
                val1 = (byte) (b1 >> 2);
            } else {
                val1 = (byte) ((b1 >> 2) ^ 192);
            }
            if ((b2 & SIGN) == 0) {
                val2 = (byte) (b2 >> FOURBYTE);
            } else {
                val2 = (byte) ((b2 >> FOURBYTE) ^ 240);
            }
            if ((b3 & SIGN) == 0) {
                val3 = (byte) (b3 >> SIXBIT);
            } else {
                val3 = (byte) ((b3 >> SIXBIT) ^ 252);
            }
            int i2 = encodedIndex + 1;
            encodedData[encodedIndex] = lookUpBase64Alphabet[val1];
            encodedIndex = i2 + 1;
            encodedData[i2] = lookUpBase64Alphabet[(k << FOURBYTE) | val2];
            i2 = encodedIndex + 1;
            encodedData[encodedIndex] = lookUpBase64Alphabet[(l << 2) | val3];
            encodedIndex = i2 + 1;
            encodedData[i2] = lookUpBase64Alphabet[b3 & 63];
            i++;
            dataIndex = dataIndex2;
        }
        if (fewerThan24bits == EIGHTBIT) {
            b1 = binaryData[dataIndex];
            k = (byte) (b1 & 3);
            if ((b1 & SIGN) == 0) {
                val1 = (byte) (b1 >> 2);
            } else {
                val1 = (byte) ((b1 >> 2) ^ 192);
            }
            i2 = encodedIndex + 1;
            encodedData[encodedIndex] = lookUpBase64Alphabet[val1];
            encodedIndex = i2 + 1;
            encodedData[i2] = lookUpBase64Alphabet[k << FOURBYTE];
            i2 = encodedIndex + 1;
            encodedData[encodedIndex] = PAD;
            encodedIndex = i2 + 1;
            encodedData[i2] = PAD;
            i2 = encodedIndex;
        } else {
            if (fewerThan24bits == SIXTEENBIT) {
                b1 = binaryData[dataIndex];
                b2 = binaryData[dataIndex + 1];
                l = (byte) (b2 & 15);
                k = (byte) (b1 & 3);
                if ((b1 & SIGN) == 0) {
                    val1 = (byte) (b1 >> 2);
                } else {
                    val1 = (byte) ((b1 >> 2) ^ 192);
                }
                if ((b2 & SIGN) == 0) {
                    val2 = (byte) (b2 >> FOURBYTE);
                } else {
                    val2 = (byte) ((b2 >> FOURBYTE) ^ 240);
                }
                i2 = encodedIndex + 1;
                encodedData[encodedIndex] = lookUpBase64Alphabet[val1];
                encodedIndex = i2 + 1;
                encodedData[i2] = lookUpBase64Alphabet[(k << FOURBYTE) | val2];
                i2 = encodedIndex + 1;
                encodedData[encodedIndex] = lookUpBase64Alphabet[l << 2];
                encodedIndex = i2 + 1;
                encodedData[i2] = PAD;
            }
            i2 = encodedIndex;
        }
        return new String(encodedData);
    }

    public static byte[] decode(String encoded) {
        if (encoded == null) {
            return null;
        }
        char[] base64Data = encoded.toCharArray();
        int len = removeWhiteSpace(base64Data);
        if (len % FOURBYTE != 0) {
            return null;
        }
        int numberQuadruple = len / FOURBYTE;
        if (numberQuadruple == 0) {
            return new byte[0];
        }
        int dataIndex;
        char d1;
        char d4;
        int i = 0;
        byte[] decodedData = new byte[(numberQuadruple * 3)];
        int dataIndex2 = 0;
        int encodedIndex = 0;
        while (i < numberQuadruple - 1) {
            char d2;
            char d3;
            dataIndex = dataIndex2 + 1;
            d1 = base64Data[dataIndex2];
            if (isData(d1)) {
                dataIndex2 = dataIndex + 1;
                d2 = base64Data[dataIndex];
                if (isData(d2)) {
                    dataIndex = dataIndex2 + 1;
                    d3 = base64Data[dataIndex2];
                    if (isData(d3)) {
                        dataIndex2 = dataIndex + 1;
                        d4 = base64Data[dataIndex];
                        if (isData(d4)) {
                            byte b1 = base64Alphabet[d1];
                            byte b2 = base64Alphabet[d2];
                            byte b3 = base64Alphabet[d3];
                            byte b4 = base64Alphabet[d4];
                            int i2 = encodedIndex + 1;
                            decodedData[encodedIndex] = (byte) ((b1 << 2) | (b2 >> FOURBYTE));
                            encodedIndex = i2 + 1;
                            decodedData[i2] = (byte) (((b2 & 15) << FOURBYTE) | ((b3 >> 2) & 15));
                            i2 = encodedIndex + 1;
                            decodedData[encodedIndex] = (byte) ((b3 << SIXBIT) | b4);
                            i++;
                            encodedIndex = i2;
                        }
                    }
                }
                dataIndex = dataIndex2;
            }
            return null;
        }
        dataIndex = dataIndex2 + 1;
        d1 = base64Data[dataIndex2];
        if (isData(d1)) {
            dataIndex2 = dataIndex + 1;
            d2 = base64Data[dataIndex];
            if (isData(d2)) {
                b1 = base64Alphabet[d1];
                b2 = base64Alphabet[d2];
                dataIndex = dataIndex2 + 1;
                d3 = base64Data[dataIndex2];
                dataIndex2 = dataIndex + 1;
                d4 = base64Data[dataIndex];
                if (isData(d3) && isData(d4)) {
                    b3 = base64Alphabet[d3];
                    b4 = base64Alphabet[d4];
                    i2 = encodedIndex + 1;
                    decodedData[encodedIndex] = (byte) ((b1 << 2) | (b2 >> FOURBYTE));
                    encodedIndex = i2 + 1;
                    decodedData[i2] = (byte) (((b2 & 15) << FOURBYTE) | ((b3 >> 2) & 15));
                    i2 = encodedIndex + 1;
                    decodedData[encodedIndex] = (byte) ((b3 << SIXBIT) | b4);
                    return decodedData;
                } else if (isPad(d3) && isPad(d4)) {
                    if ((b2 & 15) != 0) {
                        return null;
                    }
                    tmp = new byte[((i * 3) + 1)];
                    System.arraycopy(decodedData, 0, tmp, 0, i * 3);
                    tmp[encodedIndex] = (byte) ((b1 << 2) | (b2 >> FOURBYTE));
                    return tmp;
                } else if (isPad(d3) || !isPad(d4)) {
                    return null;
                } else {
                    b3 = base64Alphabet[d3];
                    if ((b3 & 3) != 0) {
                        return null;
                    }
                    tmp = new byte[((i * 3) + 2)];
                    System.arraycopy(decodedData, 0, tmp, 0, i * 3);
                    i2 = encodedIndex + 1;
                    tmp[encodedIndex] = (byte) ((b1 << 2) | (b2 >> FOURBYTE));
                    tmp[i2] = (byte) (((b2 & 15) << FOURBYTE) | ((b3 >> 2) & 15));
                    return tmp;
                }
            }
            dataIndex = dataIndex2;
        }
        return null;
    }

    protected static int removeWhiteSpace(char[] data) {
        if (data == null) {
            return 0;
        }
        int len = data.length;
        int i = 0;
        int newSize = 0;
        while (i < len) {
            int newSize2;
            if (isWhiteSpace(data[i])) {
                newSize2 = newSize;
            } else {
                newSize2 = newSize + 1;
                data[newSize] = data[i];
            }
            i++;
            newSize = newSize2;
        }
        return newSize;
    }
}
