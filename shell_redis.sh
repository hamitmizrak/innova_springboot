#!/bin/bash


# Projemizin sağlıklı çalışması için öncelikler redis servera kurulu olması gerekiyor
docker container run --name spring_redis -p 6379:6379 -d redis

# Redis Servera bağlanmak için
# 1.YOL: Docker Desktop => Container => Terminal
# 2.YOL: Terminal       => winpty docker exec -it spring_redis bash

# docker exec -it spring_redis bash
# docker exec -it spring_redis redis-cli

# $ redis-cli ping     NOT: Eğer bu ping sonucu PONG geliyorsa redis server çalışıyor demektir.
# $ redis-cli FLUSHALL
# $ redis-cli FLUSHDB
# $ redis-cli DEL id   nOT: id: cache id bileşenidir.

# Spring Boot projesini çalıştır.
mvn spring-boot:run &

# Redis sunucusunun çalıştığından emin olun.
# Terminalden veya komut istemcisinden aşağıdaki komutları kullanabilirsiniz:
# Eğer Redis düzgün çalışıyorsa PONG yanıtı alırsınız.
# Eğer bir bağlantı hatası varsa Redis'in çalıştığından emin olun ve Redis sunucusunu başlatın:
# redis-server
winpty docker exec -it spring_redis bash

# bu komutu Terminale bağlandıktan sonra ping gönderirilir eğer çalışıyorsa pıng döner
redis-cli ping