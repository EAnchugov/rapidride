
Rapid Ride
Rapid Ride - веб-приложение для управления поездками, построенное с использованием современных технологий и упакованное в Docker-контейнер для удобного развёртывания и масштабирования. Приложение запускается на порту 9090.

Используемые технологии
Java: Основной язык программирования для разработки приложения.
Spring Framework: Для упрощения разработки, конфигурирования и развёртывания.
Hibernate: ORM-фреймворк для работы с базой данных.
Docker: Для контейнеризации приложения, обеспечивая лёгкость в развёртывании и масштабировании.
MinIO: Объектное хранилище для хранения файлов.

Быстрый старт
Клонируйте репозиторий:

sh
git clone https://github.com/yourusername/rapid-ride.git
cd rapid-ride
Запустите Docker Compose:

sh
docker-compose up -dЪ


Откройте браузер и перейдите по адресу:

http://localhost:9090/ping

Требования
Docker и Docker Compose должны быть установлены на вашей машине.
Порты 9090 должен быть свободен для использования.

Конфигурация

Приложение настроено для использования следующих переменных среды в docker-compose.yml:
SPRING_DATASOURCE_URL

SPRING_DATASOURCE_USERNAME

SPRING_DATASOURCE_PASSWORD



POSTGRES_DB

POSTGRES_USER

POSTGRES_PASSWORD

MINIO_ROOT_USER

MINIO_ROOT_PASSWORD
