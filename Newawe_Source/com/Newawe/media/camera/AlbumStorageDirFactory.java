package com.Newawe.media.camera;

import java.io.File;

abstract class AlbumStorageDirFactory {
    public abstract File getAlbumStorageDir(String str);

    AlbumStorageDirFactory() {
    }
}
