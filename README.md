# company-internship-2025



\# Internship 2025 – Microservice Project



Bu repo, microservice projesinin kodlarını içeriyor.

İlk günümde geliştirme ortamını kurup altyapıyı çalışır hale getirdim.

Bundan sonraki günlerde adım adım backend ve frontend servislerini ekleyeceğim.



\## (20.08.2025)

\- Java 17, Node.js, Git ve Docker’ı bilgisayarıma kurdum.

\- Docker çalışırken çıkan sorunları (WSL güncelleme, policy ayarları vb.) çözdüm.

\- Staj için GitHub reposunu oluşturdum ve ilk commitimi attım.

\- PostgreSQL, RabbitMQ (management paneli ile) ve MailHog servislerini Docker Compose ile çalıştırdım.

\- RabbitMQ yönetim paneline ve MailHog arayüzüne giriş yaparak test ettim.





\## 📦 Altyapıyı Çalıştırma

Servisi çalıştırmak için kullandığım komut:

```bash

docker compose -f infrastructure/docker-compose.yml up -d



