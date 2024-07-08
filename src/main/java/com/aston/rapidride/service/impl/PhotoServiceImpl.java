package com.aston.rapidride.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Paths;

@Service
public class PhotoServiceImpl {

    private static final String SAVE_DIR = "save_photos/";

    public void savePhoto(String photoUrl) throws Exception {
        URL url = new URL(photoUrl);
        String fileName = Paths.get(SAVE_DIR, Paths.get(url.getPath()).getFileName().toString()).toString();

        try (InputStream in = url.openStream()) {
            FileOutputStream out = new FileOutputStream(fileName);
            StreamUtils.copy(in, out);
        }
    }
}
