package com.Newawe.media.camera;

import android.os.Environment;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang.StringUtils;

public class AlbumStorageController {
    private String _albumName;
    private AlbumStorageDirFactory _albumStorageDirFactory;
    private String _currentPhotoPath;

    public AlbumStorageController(String albumName) {
        this._albumStorageDirFactory = null;
        if (albumName == null || albumName.equals(StringUtils.EMPTY)) {
            albumName = "album_" + new SimpleDateFormat("yyyy-MM-dd").toString();
        }
        this._albumName = albumName;
    }

    public File setUpPhotoFile() throws IOException {
        File f = createImageFile();
        this._currentPhotoPath = f.getAbsolutePath();
        return f;
    }

    public String getCurrentPhotoPath() {
        return this._currentPhotoPath;
    }

    public void setCurrentPhotoPath(String path) {
        this._currentPhotoPath = path;
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return File.createTempFile("IMG_" + timeStamp + "_", ".jpg", getAlbumDir());
    }

    private File getAlbumDir() {
        File storageDir = null;
        if ("mounted".equals(Environment.getExternalStorageState())) {
            storageDir = this._albumStorageDirFactory.getAlbumStorageDir(this._albumName);
            if (!(storageDir == null || storageDir.mkdirs() || storageDir.exists())) {
                Log.e(AlbumStorageController.class.getSimpleName(), "failed to create directory");
                return null;
            }
        }
        Log.e(AlbumStorageController.class.getSimpleName(), "External storage is not mounted READ/WRITE.");
        return storageDir;
    }
}
