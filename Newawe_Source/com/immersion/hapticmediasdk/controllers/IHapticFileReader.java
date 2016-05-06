package com.immersion.hapticmediasdk.controllers;

import com.immersion.hapticmediasdk.models.HapticFileInformation;
import com.immersion.hapticmediasdk.models.NotEnoughHapticBytesAvailableException;

public interface IHapticFileReader {
    boolean bufferAtPlaybackPosition(int i);

    void close();

    long getBlockOffset(long j);

    int getBlockSizeMS();

    byte[] getBufferForPlaybackPosition(int i) throws NotEnoughHapticBytesAvailableException;

    byte[] getEncryptedHapticHeader();

    int getHapticBlockIndex(long j);

    HapticFileInformation getHapticFileInformation();

    void setBlockSizeMS(int i);

    void setBytesAvailable(int i);
}
