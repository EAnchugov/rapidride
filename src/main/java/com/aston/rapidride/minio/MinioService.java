package com.aston.rapidride.minio;

import io.minio.*;
import io.minio.http.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.InputStream;

@Service
public class MinioService {

    @Autowired
    private MinioClient minioClient;

    @Value("${minio.bucket-name}")
    private String bucketName;
    @Value("${rapidride.minio.url}")
    private String minio;

    //TODO Сделать превязку Car, ФОтографии и пути до этой фотографии.
    public void addFile(String objectName, String filePath) {
        //Получаем ссылки на картинки
        //Проверяем на уникальность имени
        //Если уникально - создаем
        //если нет - генерируем рандомное имя

        //Если создали - атдейтим саr уникальным именем файла
        try {
            MinioClient minioClient = MinioClient.builder()
                    .endpoint(minio)
                    .credentials("minioadmin", "minioadmin")
                    .build();
            //TODO сделать передачу путей и имени файла
//            String objectName = "minio.png";  // Имя объекта в MinIO
//            String filePath = "D:\\minio.png";  // Путь к вашему файлу

            // Создайте бакет, если он не существует
            boolean isExist = minioClient.bucketExists(
                    BucketExistsArgs.builder().bucket(bucketName).build()
            );
            if (!isExist) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }

            try (FileInputStream fis = new FileInputStream(filePath)) {
                minioClient.putObject(
                        PutObjectArgs.builder()
                                .bucket(bucketName)
                                .object(objectName)
                                .stream(fis, fis.available(), -1)
                                .contentType("image/png")  // Укажите тип содержимого как image/png
                                .build()
                );
            }

            System.out.println("File uploaded successfully.");
        } catch (Exception e) {
            System.err.println("Error occurred: " + e.getMessage());
        }
    }


    public InputStream getFile(String objectName) throws Exception {
        return minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(bucketName)
                        .object(objectName)
                        .build()
        );
    }


    //TODO Связать этот метод см CAR
    public String getPresignedObjectUrl(String objectName) throws Exception {
        GetPresignedObjectUrlArgs args = GetPresignedObjectUrlArgs.builder()
                .method(Method.GET)
                .bucket(bucketName)
                .object(objectName)
                .build();
        return minioClient.getPresignedObjectUrl(args);
    }
}
