# Innova Spring Boot
[GitHub Address](https://github.com/hamitmizrak/innova_springboot.git)
---

## Git
```sh 
git clone https://github.com/hamitmizrak/innova_springboot.git

git init
git add .
git commit -m "spring init"
git push -u master

git status
git log

git pull
git pull origin master

```
---

## Permalink
```sh
    http://localhost:4444
    http://localhost:4444/h2-console
    http://localhost:4444/swagger-ui/index.html
    http://localhost:4444/actuator/health
```
---

## Lombok Error
```sh 
```
---
`ApiResult` sınıfında, `validationErrors` adında bir alan tanımlanmış, ancak bu alan için bir `setValidationErrors` metodu bulunmuyor. Bu durum, Lombok kütüphanesinin `@Data` anotasyonu tarafından otomatik olarak oluşturulması beklenen setter metodunun neden çağrılamadığını açıklıyor olabilir.

---

### Çözüm Yöntemleri:

1. **Lombok Doğrulaması**:
    - `@Data` anotasyonu genellikle tüm getter ve setter metodlarını otomatik oluşturur. Ancak, Lombok doğru çalışmazsa, `setValidationErrors` metodu eksik olabilir. Bu durumda, aşağıdaki komutla Lombok'un doğru kurulu olup olmadığını kontrol edin:

        - **Maven Projesi Kullanıyorsanız**: `mvn clean install`
        - **IDE'de Lombok Eklentisi**:
            - IntelliJ IDEA kullanıyorsanız:
                - *File > Settings > Plugins > Marketplace* kısmından "Lombok" eklentisinin kurulu olduğundan emin olun.
                - Ayrıca, *Annotation Processing* ayarını etkinleştirin (*File > Settings > Build, Execution, Deployment > Compiler > Annotation Processors > Enable annotation processing*).

2. **Setter Metodunu Elle Ekleyin**:
   Lombok düzgün çalışmıyorsa, `setValidationErrors` metodunu `ApiResult` sınıfına manuel olarak ekleyin:

   ```java
   public void setValidationErrors(Map<String, String> validationErrors) {
       this.validationErrors = validationErrors;
   }
   ```

3. **Yeniden Derleme**:
   Lombok sorunlarını çözmek veya manuel olarak eklenen setter metodunu görmek için projeyi yeniden derleyin:

   ```bash
   mvn clean compile
   ```

4. **Doğru Kullanım**:
   Eğer metodu manuel olarak eklediyseniz, `CustomErrorHandleWebRequest` sınıfında şu kodu doğrulayabilirsiniz:

   ```java
   apiResult.setValidationErrors(validationErrors);
   ```

---

### Nihai Kontrol:
- Eğer hata devam ediyorsa, projenin tüm bağımlılıklarını ve Lombok konfigürasyonunu gözden geçirin.
- Alternatif olarak, `@Setter` veya `@Getter` gibi Lombok anotasyonlarını doğrudan `validationErrors` alanına eklemeyi deneyebilirsiniz:

   ```java
   @Setter
   private Map<String, String> validationErrors;
   ```

## Spring
```sh 
```
---

## Spring
```sh 
```
---

## Spring
```sh 
```
---

## Spring
```sh 
```
---

## Spring
```sh 
```
---

## Spring
```sh 
```
---

## Spring
```sh 
```
---

## Spring
```sh 
```
---

## Spring
```sh 
```
---

## Spring
```sh 
```
---

## Spring
```sh 
```
---

## Spring
```sh 
```
---

## Spring
```sh 
```
---

## Spring
```sh 
```
---

