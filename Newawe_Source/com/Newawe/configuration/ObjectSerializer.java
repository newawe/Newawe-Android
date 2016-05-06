package com.Newawe.configuration;

import android.content.Context;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectSerializer<T> {
    private Context _context;

    public ObjectSerializer(Context context) {
        this._context = context;
    }

    public void serializeAndSaveObject(T object, String filename) throws IOException {
        FileOutputStream fos = this._context.openFileOutput(filename, 0);
        new ObjectOutputStream(fos).writeObject(object);
        fos.close();
    }

    public T loadSerializedObject(String filename) throws IOException, ClassNotFoundException {
        if (this._context.getFileStreamPath(filename).exists()) {
            return new ObjectInputStream(this._context.openFileInput(filename)).readObject();
        }
        throw new IOException();
    }
}
