//package com.aston.rapidride.service.impl;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.MockedStatic;
//import org.mockito.Mockito;
//import org.springframework.util.StreamUtils;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.InputStream;
//import java.net.URL;
//import java.nio.file.Paths;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class PhotoServiceImplTest {
//
//    @InjectMocks
//    private PhotoServiceImpl photoService;
//
//    private static final String TEST_URL = "http://example.com/test.jpg";
//    private static final String SAVE_DIR = "save_photos/";
//
//    @BeforeEach
//    void setUp() {
//        new File(SAVE_DIR).mkdirs(); // Создаем директорию для тестов
//    }
//
//    @Test
//    void savePhoto_success() {
//        try (MockedStatic<StreamUtils> mockedStreamUtils = Mockito.mockStatic(StreamUtils.class)) {
//            URL url = new URL(TEST_URL);
//            String fileName = Paths.get(SAVE_DIR, Paths.get(url.getPath()).getFileName().toString()).toString();
//
//            InputStream inputStream = Mockito.mock(InputStream.class);
//            Mockito.when(url.openStream()).thenReturn(inputStream);
//
//            FileOutputStream outputStream = Mockito.mock(FileOutputStream.class);
//            mockedStreamUtils.when(() -> StreamUtils.copy(inputStream, outputStream)).thenReturn(100L);
//
//            photoService.savePhoto(TEST_URL);
//
//            File savedFile = new File(fileName);
//            assertTrue(savedFile.exists(), "File should be saved successfully");
//
//            // Удаляем файл после теста
//            savedFile.delete();
//        } catch (Exception e) {
//            fail("Exception should not be thrown");
//        }
//    }
//
//    @Test
//    void savePhoto_invalidUrl() {
//        String invalidUrl = "invalid_url";
//        try {
//            photoService.savePhoto(invalidUrl);
//            fail("Exception should be thrown for invalid URL");
//        } catch (Exception e) {
//            assertFalse(e instanceof java.net.MalformedURLException, "Expected MalformedURLException");
//        }
//    }
//}
