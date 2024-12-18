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

# THY - BT Akademi Spring Boot - Redis - Dockerize
[GitHub](https://github.com/hamitmizrak/thy_springboot_redis)
---


## Version
```sh 
git -v
mvn -v
java --version
javac --version
docker version
docker-compose version
```
---

## Git Init
```sh 
git init
git add .
git commit -m "spring boot init"
git remote add origin URL
git push -u origin master

git clone https://github.com/hamitmizrak/thy_springboot_redis
```
---

## Git Codes
```sh
git status
git logs

```

## PostContructor
`@PostConstruct` anotasyonu, Java'da bir sınıfın yapılandırılması tamamlandıktan hemen sonra çalıştırılması gereken bir metodu işaretlemek için kullanılır.
Bu anotasyon, Spring tarafından yönetilen bean'lerde özellikle faydalıdır. `@PostConstruct` anotasyonu, **bağımlılıkların injection** (yerleştirilmesi) işlemi tamamlandıktan sonra,
sınıfın başlatma aşamasında belirli bir işlemi gerçekleştirmek için kullanılır.

### Ne Zaman ve Nasıl Kullanılır?
`@PostConstruct` anotasyonu, bir sınıfın veya bileşenin bağımlılıkları (dependencies) Spring tarafından atandıktan sonra otomatik olarak çalışan bir metodu belirtir.
Örneğin, veri hazırlığı, bağlantı oluşturma, bir değişkenin başlatılması gibi işlemler burada yapılabilir.
Bu metod, sınıfın constructor'ından **sonra**, ancak herhangi bir metodun çağrılmasından **önce** çalışır.

### Örnek Kodu Açıklaması:
```java
private ApiResult apiResult;
@PostConstruct
public void springData() {
    apiResult = new ApiResult();
}
```

#### 1. **`@PostConstruct` Anotasyonu:**
- **Ne yapar?** Bu metodun, sınıfın tüm bağımlılıkları atandıktan sonra çalıştırılmasını sağlar.
- Yani, `springData()` metodu, sınıfın diğer bileşenleri veya bağımlılıkları (örneğin, `apiResult` gibi) Spring tarafından hazır hale getirildikten sonra çalıştırılır.
- **Neden kullanılır?** Eğer belirli bir nesne veya değişken (örneğin `apiResult`) sınıf başlatıldığında otomatik olarak başlatılmak isteniyorsa,
- bu anotasyon kullanılır. Böylece her seferinde constructor’da veya manuel olarak başlatmak zorunda kalmazsınız.

#### 2. **`apiResult = new ApiResult();`**
- **Ne yapar?** `springData()` metodu çalıştığında, `apiResult` nesnesi yeni bir `ApiResult` örneği olarak başlatılır.
- Bu sayede sınıfın diğer metotları bu `apiResult` örneğini kullanabilir.
- **Bağlam:** `apiResult` burada bir sınıf değişkeni (instance variable) olarak tanımlanmıştır.
- Eğer bu değişkenin başlatılması gerekiyorsa, `@PostConstruct` anotasyonu ile bağımlılıkların atandıktan hemen sonra bu işlemi gerçekleştirebilirsiniz.

### Özet:
- **`@PostConstruct`**, Spring tarafından yönetilen bir sınıfın yaşam döngüsünde constructor'dan sonra,
- ancak diğer metodların çağrılmasından önce çalıştırılacak bir metodu belirtir.
- Bu anotasyon ile sınıfın ilk kurulum aşamasında gerekli hazırlıkları yapmak için kullanılabilir,
- örneğin: veri başlatma, bağlantı kurma ya da değişkenlere ilk değer atama gibi işlemler.



---
## GET attributes
@GetMapping(
name = "/addres_list_name",
value = "/list",
//path = "/list2"
params = "active=true",
consumes = "application/json",
produces = "application/json",
headers = "X-API-VERSION=1"
)
@Override
public ResponseEntity<List<AddressDto>> addressApiList() {
return ResponseEntity.status(HttpStatus.OK).body(iAddressService.addressServiceList());
}

Bu komutta kullanılan `@GetMapping` anotasyonu, Spring Framework'deki RESTful API geliştirme işlemlerinde kullanılan bir anotasyondur. Bu anotasyon, bir HTTP GET isteğine yanıt veren bir metodun yapılandırılmasını sağlar. Kodda verilen her bir özelliği (attribute) detaylı bir şekilde inceleyip, nasıl çalıştığını ve ne anlama geldiğini açıklayacağım. Ayrıca, her bir özelliğin API'nin nasıl çalışmasını etkilediğini de ayrıntılı olarak açıklayacağım.

### Komut:
```java
@GetMapping(
    name = "/addres_list_name",
    value = "/list",
    path = "/list",
    params = "active=true",
    consumes = "application/json",
    produces = "application/xml",
    headers = "X-API-VERSION=1"
)
@Override
public ResponseEntity<List<AddressDto>> addressApiList() {
    return ResponseEntity.status(HttpStatus.OK).body(iAddressService.addressServiceList());
}
```

## Kısaca
    /*
    İşte `@GetMapping` anotasyonundaki parametrelerin kısa ve madde madde açıklamaları:

1. **`name`:**
    - Metoda bir isim verir (genellikle içsel kullanım içindir, dışarıdan görünmez).
    - Örnek: `name = "/address_list_name"`

2. **`value`:**
    - GET isteğinin URL yolunu belirtir (endpoint).
    - Örnek: `value = "/list"`

3. **`path`:**
    - `value` ile aynı işlevi görür, endpoint yolunu belirtir.
    - Örnek: `path = "/list2"`

4. **`params`:**
    - GET isteğinde belirli bir query parametresinin bulunmasını zorunlu kılar.
    - Örnek: `params = "active=true"`
    - EndPoint: http://lcaolhost:4444/list?active=true

5. **`consumes`:**
    - İstek veri formatını (Content-Type) belirtir, sadece bu formatta gelen verileri kabul eder.
    - Örnek: `consumes = "application/json"`

6. **`produces`:**
    - Yanıt veri formatını belirtir, API'nin döneceği formatı tanımlar.
    - Örnek: `produces = "application/json"`
    - - Örnek: `produces = "application/xml"`

7. **`headers`:**
    - İstek başlığında (header) belirli bir bilginin bulunmasını zorunlu kılar.
    - Örnek: `headers = "X-API-VERSION=1"`
      */
## name attribute örnek vermek
"Bu isim, Spring'in dahili mekanizmasında kullanılabilir" ifadesi, Spring'in kendi yapısında bu ismi referans olarak kullanabileceği anlamına gelir.
Ancak, bu isim dışarıya görünmez, yani API'yi çağıran istemciler bu ismi görmez veya bilmezler.
Bu isim, genellikle Spring'in içsel işlemlerinde, logging (kayıt tutma), izleme veya debugging (hata ayıklama) gibi durumlarda kullanılabilir.

### Örnek:
Spring uygulamanızda birden fazla endpoint olabilir ve her birine `name` özelliği ile benzersiz isimler verebilirsiniz. Bu isimler, özellikle monitoring (izleme) veya hata ayıklama araçları tarafından kullanılabilir.

#### Kullanım Senaryosu:
1. **İzleme ve Debugging:** Uygulamanız bir monitoring aracı ile izleniyorsa, belirli bir endpoint'in adı bu araçlarda kolayca görüntülenebilir ve loglarda daha anlaşılır bir bilgi sağlar.

2. **Logging:** Loglarınızda hangi metodun hangi isimle çağrıldığını belirtmek isteyebilirsiniz. Örneğin, hata aldığınızda bu endpoint'e verilen isim loglarda gözükebilir.

### Örnek:
```java
@GetMapping(
    name = "getAddressListEndpoint",
    value = "/list",
    produces = "application/json"
)
public ResponseEntity<List<AddressDto>> addressApiList() {
    log.info("Calling the endpoint: getAddressListEndpoint");
    return ResponseEntity.ok(iAddressService.addressServiceList());
}
```

Bu örnekte, `log.info("Calling the endpoint: getAddressListEndpoint");` satırı, bu endpoint'e yapılan çağrıların loglanmasını sağlar. Loglarda bu metodun adı `getAddressListEndpoint` olarak görünür. Bu, metodun ne işe yaradığını anlamayı kolaylaştırır.

### Spring Actuator ile Kullanım:
Eğer Spring Actuator kullanıyorsanız, uygulamanızın `name` ile tanımlanmış metodlarını izleme veya raporlama araçlarıyla daha anlamlı hale getirebilirsiniz.

```bash
curl http://localhost:8080/actuator/mappings
```

Bu komutla, Spring Actuator üzerinde tanımlı tüm endpoint'leri görebilirsiniz ve bu noktada `name` ile verilmiş isimler bu mapping bilgisi içinde görünür.

### `@GetMapping` Anotasyonunun Ayrıntılı Açıklaması:
Spring Framework'de `@GetMapping` anotasyonu, bir HTTP GET isteğini belirli bir metoda eşlemek için kullanılır.
Bu metoda gelen GET isteği, `value` ve diğer koşullara göre doğru URL'ye ve isteklere yönlendirilir.

#### 1. **`name`:**
```java
name = "/addres_list_name"
```
- **Açıklama:** `name` özelliği, Spring'in dahili kullanımında metodlara bir referans ismi vermek için kullanılır. Normalde bu isim dışarıya yansıtılmaz ve kullanıcının göremeyeceği bir özelliktir.
- **Anlamı:** Bu metot için bir isim tanımlanmıştır: "/addres_list_name". Bu, proje içinde Spring'in bu metodu izleyebilmesi ve gerektiğinde referans verebilmesi için kullanılabilir.
- **Kullanımı:** Özellikle büyük projelerde, belirli metodlara referans vermek ve metodların izlenebilirliğini artırmak için faydalıdır.
- Ancak genellikle küçük projelerde bu özelliğin kullanımı zorunlu değildir ve isteğe bağlıdır.

#### 2. **`value`:**
```java
value = "/list"
```
- **Açıklama:** `value` özelliği, GET isteğinin URL yolunu tanımlar.
- Bu, istemcinin hangi URL'ye istek yapacağını belirleyen temel yoldur.
- **Anlamı:** `/list` olarak belirlenen bu URL, istemcinin `http://localhost:4444/api/address/list` adresine GET isteği yaparak bu metoda ulaşmasını sağlar.
- Uygulamanın çalıştığı kök URL ve sınıf düzeyinde tanımlanan yol (muhtemelen `/api/address`) bu yolun tam halini belirler.
- **Kullanımı:** İstemci, bu API'ye erişmek istediğinde `http://localhost:4444/api/address/list` yoluna bir GET isteği yapar.
- Bu URL, servise dışarıdan erişim için kapı görevi görür.

#### 3. **`params`:**
```java
params = "active=true"
```
- **Açıklama:** `params` özelliği, HTTP isteğinin belirli bir query parametresini içermesi gerektiğini belirtir. Burada, URL'de belirli parametreler bulunması zorunlu kılınmıştır.
- **Anlamı:** API çağrısı yapılırken, URL'de `active=true` query parametresi olmalıdır. Örneğin: `http://localhost:4444/api/address/list?active=true` şeklinde bir GET isteği yapılması gerekir.
- Eğer bu parametre belirtilmezse veya değeri farklı olursa, istek bu metoda yönlendirilmez.
- **Kullanımı:** `params` özelliği ile aynı endpoint'e farklı parametreler göndererek farklı işlemler gerçekleştirebiliriz.
- Parametre kontrolü, isteğin doğru metodla eşleşmesini sağlar.

#### 4. **`consumes`:**
```java
consumes = "application/json"
```
- **Açıklama:** `consumes` özelliği, API'ye gönderilecek olan isteğin veri formatını belirtir.
- Yani bu metod, yalnızca belirli bir formatta (bu örnekte `application/json`) gönderilen istekleri kabul eder.
- **Anlamı:** Bu API, sadece JSON formatındaki verileri kabul eder.
- Bu, istemcinin isteği yaparken `Content-Type: application/json` başlığı ile veri göndermesi gerektiği anlamına gelir.
- Eğer istek başka bir formatta (örneğin XML) gönderilirse, API bunu işleyemez ve 415 (Unsupported Media Type) hatası döner.
- **Kullanımı:** `consumes` özelliği, API'nin yalnızca belirli formatta veri almasını sağlamak için kullanılır.
- Genellikle JSON (`application/json`), XML (`application/xml`), veya form verisi (`application/x-www-form-urlencoded`) formatlarıyla çalışır.

#### 5. **`produces`:**
```java
produces = "application/json"
```
- **Açıklama:** `produces` özelliği, bu metodun istemciye hangi formatta yanıt döndüreceğini belirtir.
- Bu durumda, API JSON formatında bir yanıt döndürecektir.
- **Anlamı:** Yanıt, `application/json` formatında dönecektir. İstemci bu endpoint'i çağırırken `Accept: application/json` başlığını ekleyebilir ve yanıtın JSON formatında olduğunu bilmelidir.
- Eğer istemci farklı bir format beklerse (örneğin XML), 406 (Not Acceptable) hatası dönebilir.
- **Kullanımı:** Bu özellik, API'nin belirli bir formatta yanıt döndürmesini zorunlu kılar.
- JSON, XML, HTML gibi farklı formatlar arasında seçim yapabilirsiniz.

#### 6. **`headers`:**
```java
headers = "X-API-VERSION=1"
```
- **Açıklama:** `headers` özelliği, HTTP isteğinde belirli bir başlığın (header) bulunmasını zorunlu kılar.
- Bu örnekte, `X-API-VERSION` başlığı kullanılmaktadır.
- **Anlamı:** Bu API'yi çağırırken HTTP isteğine `X-API-VERSION: 1` başlığı eklenmelidir.
- Bu genellikle API versiyonlaması için kullanılır.
- İstemci bu başlığı göndermezse ya da farklı bir değer gönderirse, bu metot çalışmaz ve genellikle 400 (Bad Request) veya 404 (Not Found) hatası dönebilir.
- **Kullanımı:** `headers` özelliği, API versiyonlama, güvenlik veya farklı ihtiyaçlar doğrultusunda isteklere eklenen başlıkları kontrol etmek için kullanılır.
- Versiyonlamada API'nin farklı sürümlerini desteklemek için başlıklar sıkça kullanılır.

### Metodun Gövdesi:
```java
@Override
public ResponseEntity<List<AddressDto>> addressApiList() {
    return ResponseEntity.status(HttpStatus.OK).body(iAddressService.addressServiceList());
}
```
#### 1. **`@Override`:**
- **Açıklama:** Bu anotasyon, bir metotun üst sınıfta veya bir interface'de tanımlandığını ve burada tekrar tanımlandığını belirtir. Bu, metodun bir interface olan `IAddressApi`'dan alındığını gösterir.
- **Anlamı:** Bu metot, `IAddressApi` interface’inde tanımlanmış olmalıdır. Bu, Spring'te Interface-Implementation ilişkisini sağlar.

#### 2. **`ResponseEntity`:**
- **Açıklama:** `ResponseEntity`, HTTP yanıtlarını döndürmek için kullanılan bir sınıftır.
- Yanıtın durum kodu ve gövdesi gibi HTTP yanıtı ile ilgili tüm bilgileri içerir.
- **Anlamı:** Bu metod, HTTP yanıtı olarak bir liste döndürür (`List<AddressDto>`).
- Bu liste, Spring'in HTTP yanıtı olarak işleyebileceği şekilde paketlenir.
- Ayrıca, yanıt durum kodu olarak `HttpStatus.OK` (200) belirlenmiştir.
- **Kullanımı:** `ResponseEntity`, API yanıtının durum kodunu (`HttpStatus`), başlıklarını ve gövdesini kontrol etmek için güçlü bir yapı sağlar.
- Yanıtın gövdesi burada `iAddressService.addressServiceList()` tarafından sağlanan veri olacaktır.

#### 3. **`iAddressService.addressServiceList()`:**
- **Açıklama:** Bu, `iAddressService` adında bir servis tarafından sağlanan bir metottur.
- `addressServiceList()` metodu, adresleri listeleyen bir servis çağrısıdır.
- **Anlamı:** Bu servis çağrısı, adresleri (`AddressDto`) içeren bir liste döndürür ve bu liste API'ye yanıt olarak istemciye iletilir.
- **Kullanımı:** Servis katmanında iş mantığı genellikle burada yapılır.
- Bu, veritabanı veya başka bir kaynaktan verilerin alınmasını ve işlenmesini sağlar. Burada işlenen veri, API yanıtı olarak döndürülecektir.

### API’nin Nasıl Çalışacağı:
Bu metod, `http://localhost:4444/api/address/list` URL'sine yapılan GET isteklerini karşılar. Ancak bu

isteğin çalışabilmesi için birkaç koşul vardır:

1. **URL Parametresi:** İstek, `active=true` query parametresini içermelidir. Örneğin: `http://localhost:4444/api/address/list?active=true`.
2. **Başlıklar (Headers):** HTTP isteği, `X-API-VERSION: 1` başlığını içermelidir. Bu, API'nin doğru versiyonuna eriştiğinizi garanti eder.
3. **İçerik Tipi (Content-Type):** İstek verisi JSON formatında olmalıdır, yani `Content-Type: application/json` başlığı ile gönderilmelidir.
4. **Yanıt Formatı (Produces):** Yanıt JSON formatında döndürülecektir. İstemci, bu yanıtı alacaksa `Accept: application/json` başlığı ile çağrı yapabilir.

### Özet:
Bu API'nin GET metoduna gelen istek, belirli bir URL, query parametre, başlık ve içerik türü ile sınırlandırılmıştır.
Sadece `active=true` parametresi ile, `X-API-VERSION=1` başlığı ile ve `application/json` formatında gelen istekler işlenir.
Yanıt olarak JSON formatında bir `AddressDto` listesi döndürülür.
Bu yapı, API'nin versiyonlama, veri formatı yönetimi ve parametre kontrolü gibi gelişmiş özelliklerini kullanarak RESTful bir mimari sunar.

## @EnableWebMvc
---
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    // CORS yapılandırması
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Tüm URL'ler için geçerli
                .allowedOrigins("http://localhost:3000")  // İzin verilen köken
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // İzin verilen HTTP metodları
                .allowedHeaders("*");  // İzin verilen başlıklar
    }

    // Statik kaynaklar için yapılandırma
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")  // Kaynakların URL deseni
                .addResourceLocations("/public/", "classpath:/static/")  // Kaynakların yerleri
                .setCachePeriod(3600);  // Cache süresi (saniye)
    }

    // Interceptor eklemek için yapılandırma
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CustomInterceptor())  // CustomInterceptor sınıfı ile Interceptor eklenir
                .addPathPatterns("/api/**")  // Hangi URL deseninde geçerli olacağı
                .excludePathPatterns("/api/login", "/api/register");  // Hariç tutulacak URL desenleri
    }
}


Spring Boot'ta `WebConfig` sınıfı genellikle özel yapılandırmalar yapmak için kullanılır. Özellikle CORS yapılandırması, statik kaynakların yönetimi, `Interceptor` ekleme gibi çeşitli web ayarlarını özelleştirmek amacıyla tercih edilir. Aşağıda bir `WebConfig` örneği ve açıklamaları yer almaktadır:

### WebConfig Örneği

```java
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    // CORS yapılandırması
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Tüm URL'ler için geçerli
                .allowedOrigins("http://localhost:3000")  // İzin verilen köken
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // İzin verilen HTTP metodları
                .allowedHeaders("*");  // İzin verilen başlıklar
    }

    // Statik kaynaklar için yapılandırma
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")  // Kaynakların URL deseni
                .addResourceLocations("/public/", "classpath:/static/")  // Kaynakların yerleri
                .setCachePeriod(3600);  // Cache süresi (saniye)
    }

    // Interceptor eklemek için yapılandırma
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CustomInterceptor())  // CustomInterceptor sınıfı ile Interceptor eklenir
                .addPathPatterns("/api/**")  // Hangi URL deseninde geçerli olacağı
                .excludePathPatterns("/api/login", "/api/register");  // Hariç tutulacak URL desenleri
    }
}
```

### Açıklamalar:

1. **@Configuration**: Spring Boot'ta yapılandırma sınıflarını işaretler. `WebConfig`, Spring'e bu sınıfın yapılandırma amaçlı olduğunu söyler.

2. **@EnableWebMvc**: Bu anotasyon, Spring MVC'nin tam kontrolünü elde etmek için kullanılır. Ancak, Spring Boot varsayılan olarak birçok MVC ayarını otomatik olarak yapar. Bu nedenle, genellikle bu anotasyona ihtiyaç duyulmaz. Eğer manuel kontrol gerekiyorsa eklenir.

3. **addCorsMappings(CorsRegistry registry)**: Bu metot, Cross-Origin Resource Sharing (CORS) yapılandırması sağlar. Bu yapılandırmada:
    - `addMapping("/**")`: Tüm URL'ler için CORS kısıtlamalarını uygular.
    - `allowedOrigins("http://localhost:3000")`: Sadece `localhost:3000` kökeninden gelen istekleri kabul eder.
    - `allowedMethods("GET", "POST", "PUT", "DELETE")`: İzin verilen HTTP metodlarını tanımlar.
    - `allowedHeaders("*")`: Tüm başlıklara izin verir.

4. **addResourceHandlers(ResourceHandlerRegistry registry)**: Bu metot, statik kaynakların (CSS, JS, resimler vb.) nerede bulunduğunu ve bu kaynaklara nasıl erişileceğini tanımlar.
    - `addResourceHandler("/resources/**")`: `/resources/` ile başlayan URL'ler bu statik kaynaklara erişir.
    - `addResourceLocations("/public/", "classpath:/static/")`: Kaynakların dosya sisteminde ve classpath içinde nerede olduğunu belirtir.

5. **addInterceptors(InterceptorRegistry registry)**: Bu metot, istekleri işleme aşamasında öncesinde veya sonrasında ek davranışlar ekleyen Interceptor'lar tanımlar.
    - `addInterceptor(new CustomInterceptor())`: `CustomInterceptor` sınıfı ile bir Interceptor eklenir.
    - `addPathPatterns("/api/**")`: Bu Interceptor sadece `/api/` ile başlayan URL'lerde çalışır.
    - `excludePathPatterns("/api/login", "/api/register")`: `/api/login` ve `/api/register` URL'leri Interceptor'dan hariç tutulur.

Bu şekilde, Spring Boot projelerinizde CORS, statik kaynak yönetimi ve Interceptor'ları yönetebilirsiniz. `WebMvcConfigurer` arayüzü, bu yapılandırmaları yapmanıza imkan tanır.


`.setCachePeriod(3600)` metodu, statik kaynaklar için cache (önbellekleme) süresini saniye cinsinden ayarlayan bir işlevdir. Buradaki 3600 değeri, cache süresinin 3600 saniye, yani 1 saat olduğunu belirtir. Bu süre boyunca tarayıcı veya istemci, statik kaynakları (örneğin CSS, JavaScript, resim dosyaları gibi) yeniden indirmek yerine önbellekten kullanır.

Önbellekleme, sunucu üzerindeki yükü azaltmak ve uygulamanın performansını artırmak için kullanılır. Statik kaynaklar sık sık değişmez, bu yüzden istemcilerin her seferinde sunucudan bu dosyaları indirmesine gerek yoktur. Önbellekleme süresi şu anlama gelir:

- **Cache süresi dolmadan**: İstemci (tarayıcı gibi), kaynağı tekrar sunucudan talep etmez, bunun yerine daha önce aldığı ve önbelleğe kaydettiği versiyonu kullanır.
- **Cache süresi dolduktan sonra**: İstemci, kaynağın güncellenmiş olup olmadığını kontrol etmek için sunucuya yeni bir talep gönderir.

Örneğin, `.setCachePeriod(3600)` kullanıldığında, istemci bir CSS dosyasını ilk defa aldığında, bu dosya 1 saat boyunca istemcinin cache belleğinde tutulur ve bu süre dolmadan sunucuya tekrar bu dosya için istek göndermez.

Bu yöntem özellikle büyük dosyalar ve sık güncellenmeyen statik içerikler için faydalıdır, çünkü istemciler sunucudan gereksiz yere veri indirmez ve böylece uygulamanızın performansı artar.


`public void addResourceHandlers(ResourceHandlerRegistry registry)` metodu, Spring Boot'ta statik kaynakların (CSS, JavaScript, resim dosyaları vb.) nasıl yönetileceğini ve nerede bulunacağını yapılandırmak için kullanılan bir metottur. Bu metot, Spring MVC'nin varsayılan olarak sağladığı statik kaynak yönetimini özelleştirmemizi sağlar.

### Kullanım Amacı
Bu metot ile uygulamanızın kaynaklarının nerede yer aldığını ve bu kaynaklara nasıl erişileceğini belirleyebilirsiniz. Statik kaynaklar genellikle `src/main/resources/static` dizininde tutulur. Ancak, bu dizini değiştirmek, farklı dizinler eklemek veya önbellekleme ayarları gibi ek yapılandırmalar yapmak istediğinizde `addResourceHandlers` metodunu kullanabilirsiniz.

### Parametre: `ResourceHandlerRegistry`
`ResourceHandlerRegistry`, statik kaynakların tanımlandığı bir yapılandırma aracıdır. Bu parametre ile Spring MVC'ye hangi URL desenlerine karşı hangi kaynakların sunulacağını belirleriz.

### Örnek

```java
@Override
public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/resources/**")  // URL deseni
            .addResourceLocations("classpath:/static/", "/public/")  // Kaynakların konumları
            .setCachePeriod(3600);  // Önbellekleme süresi (saniye)
}
```

### Açıklamalar:

1. **`addResourceHandler("/resources/**")`**: Bu, URL desenini belirtir. Yani uygulamanızda `/resources/` ile başlayan herhangi bir URL, tanımladığınız kaynak dosyalarına yönlendirilecektir. Örneğin, `/resources/css/style.css` gibi bir URL bu yapılandırmaya uygun olacaktır.

2. **`addResourceLocations("classpath:/static/", "/public/")`**: Bu metot, kaynak dosyalarının yerini tanımlar. Burada iki farklı kaynak dizini belirtilmiş:
    - `classpath:/static/`: Bu, `src/main/resources/static/` dizinini ifade eder. Spring Boot projelerinde genellikle statik dosyalar burada tutulur.
    - `/public/`: Bu da proje klasörünüzdeki bir dizini ifade eder. Eğer dosyalarınız `public` klasöründe bulunuyorsa, buraya erişim de sağlar.

3. **`setCachePeriod(3600)`**: Bu, yukarıda açıkladığımız gibi, kaynakların istemci tarafında ne kadar süreyle önbellekte saklanacağını (saniye cinsinden) belirler. 3600 saniye, yani 1 saat önbellekleme süresi sağlar.

### Bu Yapılandırmanın Avantajları
- **Statik dosya erişimini özelleştirme**: Varsayılan dizinlerin dışında, farklı dizinlerden de statik kaynakları sunabilirsiniz.
- **Önbellekleme**: Performansı artırmak için istemcinin statik dosyaları önbelleğe almasını sağlayabilirsiniz.
- **Farklı yollar tanımlama**: Farklı URL desenleriyle farklı kaynakları yönetebilir, proje düzeninizi daha esnek hale getirebilirsiniz.

### Ne Zaman Kullanılır?
- Statik kaynakları varsayılan dizin dışına taşımak veya başka bir dizinden sunmak istiyorsanız.
- Önbellekleme ayarlarını kontrol etmek istiyorsanız.
- Birden fazla kaynak dizinini yapılandırmanız gerekiyorsa.
- Özel URL desenleri ile statik kaynaklara erişimi ayarlamak istiyorsanız.

Bu metodun Spring MVC'deki temel rolü, uygulamanızın statik dosyalarını daha esnek bir şekilde yönetmenize yardımcı olmaktır.


`addInterceptors(InterceptorRegistry registry)` metodu, Spring Boot'ta istekleri karşılamadan önce veya sonra ek işlemler gerçekleştirmek için **Interceptor**'ları yapılandırmak amacıyla kullanılır. Interceptor'lar, web uygulamalarındaki HTTP isteklerinin giriş ve çıkışında ek işlemler yapabilen sınıflardır. Özellikle kimlik doğrulama, yetkilendirme, loglama, performans izleme gibi işlemler için kullanılırlar.

### Interceptor Nedir?
Bir **Interceptor**, uygulamaya gelen veya giden HTTP isteklerinin öncesinde ya da sonrasında çalışır. Herhangi bir işlemi tamamlamadan önce belirli kontroller yapabilir veya isteğe ek bilgiler ekleyebilir.

### Metodun Detaylı Açıklaması

```java
@Override
public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new CustomInterceptor())  // CustomInterceptor sınıfı ile Interceptor eklenir
            .addPathPatterns("/api/**")  // Hangi URL deseninde geçerli olacağı
            .excludePathPatterns("/api/login", "/api/register");  // Hariç tutulacak URL desenleri
}
```

### Adım Adım Açıklama:

#### 1. **`addInterceptor(new CustomInterceptor())`**:
Bu kısım, `CustomInterceptor` adlı sınıfı kullanarak bir Interceptor ekler. `CustomInterceptor`, bizim tanımladığımız bir sınıf olup `HandlerInterceptor` arayüzünü ya da `HandlerInterceptorAdapter` sınıfını genişletir.

   ```java
   public class CustomInterceptor implements HandlerInterceptor {

       @Override
       public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
           // İstek işlenmeden önce çalışır
           return true;  // Eğer false dönerse istek devam etmez
       }

       @Override
       public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
           // İstek işlendikten sonra ama cevap istemciye gitmeden önce çalışır
       }

       @Override
       public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
           // İstek tamamlandıktan sonra çalışır, genellikle temizleme işlemleri için kullanılır
       }
   }
   ```

- **`preHandle`**: İstek sunucuya ulaşmadan önce çalışır. Burada, kimlik doğrulama, loglama, izin kontrolü gibi işlemler yapılabilir. Eğer `false` dönerse istek işlenmez ve burada durdurulur.
- **`postHandle`**: İstek başarıyla işlendiğinde ama yanıt henüz istemciye gitmeden önce çalışır. Bu aşamada, model veya view ile ek işlemler yapılabilir.
- **`afterCompletion`**: İstek tamamen tamamlandığında çalışır. Bu aşamada genellikle temizleme, loglama gibi işlemler yapılır.

#### 2. **`addPathPatterns("/api/**")`**:
Bu kısımda, Interceptor'ın hangi URL'lerde aktif olacağını belirleriz. Örneğin, `"/api/**"` deseni, `/api/` ile başlayan tüm URL'lere uygulanır. Bu, `/api/v1/products`, `/api/orders`, `/api/customers` gibi URL'ler olabilir.

Yani, `/api/**` ile eşleşen her istek Interceptor tarafından işlenecektir.

#### 3. **`excludePathPatterns("/api/login", "/api/register")`**:
Bu kısım, belirli URL'leri Interceptor'dan hariç tutmak için kullanılır. Yani, `/api/login` ve `/api/register` URL'leri Interceptor tarafından işlenmeyecektir.

Örneğin, kimlik doğrulama ve kayıt gibi işlemler genellikle tüm kullanıcılara açık olur, bu yüzden bu URL'ler Interceptor'ın denetiminden hariç tutulabilir. Bu, özellikle oturum açmamış kullanıcıların da bu işlemleri yapabilmesini sağlar.

### Özet:

Bu yapılandırma şunu sağlar:

1. **Interceptor eklenir**: `CustomInterceptor`, uygulamaya bir Interceptor olarak eklenmiştir. Bu Interceptor, gelen HTTP isteklerini işleyebilir veya kontrol edebilir.

2. **Path desenleri belirlenir**: Interceptor, sadece `/api/**` ile başlayan URL'lerde çalışacaktır. Bu da, API'ye yapılan isteklerin tamamında bu Interceptor'ın kullanılacağı anlamına gelir.

3. **Hariç tutulan URL'ler belirlenir**: `/api/login` ve `/api/register` URL'leri, Interceptor'ın dışında bırakılmıştır. Yani, bu iki URL'ye yapılan istekler Interceptor tarafından işlenmeyecek.

### Ne zaman kullanılır?
- **Kimlik doğrulama ve yetkilendirme**: Kullanıcıların yetkilerini denetlemek için her isteğin başında kimlik doğrulama kontrolü yapmak amacıyla kullanılabilir.
- **Loglama**: Tüm isteklerin ve yanıtların loglanması gerekiyorsa Interceptor ile bu işlemler gerçekleştirilir.
- **Performans takibi**: İsteklerin ne kadar sürede tamamlandığını takip etmek için kullanılabilir.
- **İstek değiştirme**: Giden ve gelen istek/yanıt başlıklarına eklemeler yapma gibi işlemler de Interceptor ile yapılabilir.

### Örnek Kullanım Senaryosu:

Diyelim ki bir e-ticaret uygulamanız var ve `/api/**` ile başlayan tüm isteklerde kullanıcı kimliği doğrulaması yapmak istiyorsunuz. Ancak `/api/login` ve `/api/register` yolları herkese açık olmalı. İşte bu durumda, `addInterceptors` metoduyla bir Interceptor ekleyip bu yollar hariç diğer tüm API isteklerine kimlik doğrulama kontrolü ekleyebilirsiniz.

```java
public class CustomInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        if (token == null || !isValidToken(token)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;  // İstek işlenmez ve hata döner
        }
        return true;  // İstek işlenmeye devam eder
    }

    private boolean isValidToken(String token) {
        // Token doğrulama işlemi
        return token.equals("geçerli-token");
    }
}
```

Bu yapılandırma ile, `/api/**` yollarına gelen istekler için `Authorization` başlığı ile gelen token'lar doğrulanacak, ancak `/api/login` ve `/api/register` gibi yollara gelen istekler bu kontrolün dışında tutulacaktır.







---
`@CrossOrigin` Spring Boot'ta bir anotasyon olup, **Cross-Origin Resource Sharing (CORS)** ile ilgili ayarları yönetmek için kullanılır. CORS, bir kaynağın (örneğin, bir API'nin) başka bir alan adı (domain) üzerindeki istemciler tarafından kullanılmasına izin verip vermeyeceğini belirleyen bir güvenlik mekanizmasıdır.

Tarayıcılar, bir web sayfasının başka bir alan adından veri istemesine normalde izin vermezler (aynı kaynaktan gelmeyen istekler kısıtlanır). Ancak bazı durumlarda bir web uygulamasının, başka bir alan adındaki bir API'den veri çekmesi gerekebilir. İşte burada **CORS** devreye girer ve bu isteklere izin verilip verilmeyeceğini belirler.

### `@CrossOrigin` Anotasyonu Nedir?
`@CrossOrigin`, Spring Boot'ta CORS ayarlarını yapılandırmak için kullanılan bir anotasyondur. Bu anotasyon, belirli bir API veya tüm uygulama genelinde başka bir alan adından gelen isteklere izin vermek için kullanılır.

### Kullanımı

1. **Bir Denetleyici Seviyesinde (Controller Level)**:
   Bir denetleyicinin (controller) tüm yöntemlerine başka bir alan adından gelen isteklere izin vermek için bu anotasyon kullanılabilir.

   ```java
   @RestController
   @CrossOrigin(origins = "http://example.com")
   public class MyController {

       @GetMapping("/data")
       public String getData() {
           return "Cross-origin data";
       }
   }
   ```

   Bu örnekte, `http://example.com` alan adından gelen istekler `getData()` yöntemine erişebilir. Eğer `origins` parametresi belirtilmezse, tüm alan adlarına izin verilir.

2. **Yöntem Seviyesinde (Method Level)**:
   Sadece belirli bir yöntem için CORS ayarlarını uygulamak isterseniz, bunu yöntem seviyesinde kullanabilirsiniz.

   ```java
   @RestController
   public class MyController {

       @CrossOrigin(origins = "http://example.com")
       @GetMapping("/restricted-data")
       public String getRestrictedData() {
           return "Restricted cross-origin data";
       }
   }
   ```

   Bu durumda, sadece `http://example.com` alan adından gelen istekler `getRestrictedData()` metoduna erişebilir.

### `@CrossOrigin` Anotasyonunun Parametreleri

1. **`origins`**:
   İsteklerin kabul edileceği alan adlarını belirtir. Bir veya birden fazla alan adı ekleyebilirsiniz. Varsayılan olarak, tüm alan adlarına (`*`) izin verilir.

   ```java
   @CrossOrigin(origins = {"http://example.com", "http://another-domain.com"})
   ```

2. **`methods`**:
   Hangi HTTP yöntemlerinin (GET, POST, PUT, DELETE vs.) izin verileceğini belirtir. Eğer belirtilmezse, varsayılan olarak tüm yöntemlere izin verilir.

   ```java
   @CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST})
   ```

3. **`allowedHeaders`**:
   İzin verilen başlıkları belirtir. Varsayılan olarak, tüm başlıklara izin verilir.

   ```java
   @CrossOrigin(allowedHeaders = {"Content-Type", "Authorization"})
   ```

4. **`exposedHeaders`**:
   Tarayıcıya açığa çıkarılacak başlıkları belirtir. Varsayılan olarak, CORS başlıkları tarayıcıya açılmaz.

   ```java
   @CrossOrigin(exposedHeaders = {"X-Custom-Header"})
   ```

5. **`allowCredentials`**:
   Kimlik doğrulama bilgileri (çerezler gibi) gönderilip gönderilmeyeceğini belirtir. Varsayılan olarak `false`'tur.

   ```java
   @CrossOrigin(allowCredentials = "true")
   ```

6. **`maxAge`**:
   Tarayıcının, bu ayarların geçerliliğini önbelleğe alacağı süreyi (saniye cinsinden) belirtir. Varsayılan olarak `1800` (30 dakika)'dır.

   ```java
   @CrossOrigin(maxAge = 3600)
   ```

### Global CORS Yapılandırması
Sadece belirli denetleyicilere değil, uygulamanın tamamına CORS yapılandırmasını eklemek için `WebMvcConfigurer` arabirimi kullanılır.

```java
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://example.com")
                .allowedMethods("GET", "POST")
                .allowedHeaders("Content-Type")
                .allowCredentials(true)
                .maxAge(3600);
    }
}
```

Bu yapılandırma ile, uygulamanın tüm yollarına (`/**`) `http://example.com` alan adından gelen GET ve POST isteklerine izin verilir.

### Özetle:
- `@CrossOrigin` anotasyonu, Spring Boot uygulamanızda farklı alan adlarından gelen istekleri kabul etmek için kullanılır.
- Bu anotasyon ile belirli API'ler için CORS politikası belirlenebilir.
- `origins`, `methods`, `allowedHeaders`, `exposedHeaders`, `allowCredentials`, ve `maxAge` gibi parametrelerle daha ince ayarlar yapabilirsiniz.

CORS politikalarını doğru ayarlamak, güvenlik açısından kritik olabilir. Uygulamanızın dışarıya açık alan adlarından gelen istekleri ne zaman ve nasıl kabul edeceğini dikkatli bir şekilde belirlemelisiniz.

## CORS DEVAM
```java
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://example.com")
                .allowedMethods("GET", "POST")
                .allowedHeaders("Content-Type")
                .allowCredentials(true)
                .maxAge(3600);
    }
}
```
Bu kod parçası, Spring Boot'ta **CORS (Cross-Origin Resource Sharing)** yapılandırmasını yönetmek için kullanılan bir sınıfı gösterir. Spring Boot uygulamalarında farklı alan adlarından gelen istekleri yönetmek için CORS yapılandırması yapmak gerekebilir ve bu yapılandırma `WebMvcConfigurer` arayüzü kullanılarak sağlanır. Kod parçasını detaylıca açıklayalım:

### @Configuration Anotasyonu
`@Configuration` anotasyonu, bu sınıfın bir **Spring yapılandırma sınıfı** olduğunu belirtir. Spring, bu anotasyonla işaretlenmiş sınıfları, başlatma sırasında konfigürasyon bilgilerini içeren sınıflar olarak tanır. Bu sınıfın içinde tanımladığınız metodlar ve yapılandırmalar Spring IOC Container (Bağımlılık Enjeksiyon Yöneticisi) tarafından yönetilir ve uygulamanın başlatılması sırasında yüklenir.

Örneğin, bu sınıf bir CORS yapılandırmasını içeren bir sınıf olduğu için, Spring Boot bu sınıfı otomatik olarak tanıyacak ve CORS ayarlarını uygulamanın tümüne yayacaktır.

### WebMvcConfigurer Arayüzü
`WebMvcConfigurer`, Spring MVC için yapılandırmaları özelleştirmenize olanak tanıyan bir arayüzdür. Bu arayüz, Spring MVC'nin varsayılan yapılandırmasını değiştirmek istediğinizde kullanılır. `WebMvcConfigurer` kullanarak aşağıdaki gibi yapılandırmaları özelleştirebilirsiniz:
- CORS (Cross-Origin Resource Sharing) ayarları
- View Resolver (görünüm çözümleyici) ayarları
- Kaynak (resource) ayarları
- Yol eşlemesi (path mapping) ayarları

Bu arayüzdeki metodları override ederek, Spring MVC'nin işleyişini uygulamaya özgü hale getirebilirsiniz.

### addCorsMappings(CorsRegistry registry) Metodu
Bu metod, CORS yapılandırmasını tanımladığınız yer. Bu metod Spring tarafından çağrılır ve **CORS politikalarını** özelleştirmenize olanak tanır. İçinde **CorsRegistry** nesnesini kullanarak, hangi yolların (URL endpoint'lerinin) hangi alan adlarından gelen isteklere izin vereceğini belirleyebilirsiniz.

#### Detaylı Parametreler:
- **registry.addMapping("/**")**:
  Bu kısım, hangi yolların CORS yapılandırmasına tabi olduğunu tanımlar. Burada `"/**"` kullanarak uygulamanızdaki tüm yolları belirtmiş oluyorsunuz. Yani bu yapılandırma, uygulamanın tüm API endpoint'leri için geçerlidir.

  Eğer sadece belirli bir yol için CORS ayarı yapmak isterseniz, o yolu belirtebilirsiniz. Örneğin, sadece `/api/**` yolları için izin vermek istiyorsanız:

  ```java
  registry.addMapping("/api/**")
  ```

- **allowedOrigins("http://example.com")**:
  Bu kısım, hangi alan adlarından gelen isteklere izin verileceğini tanımlar. Örnekte sadece `http://example.com` alan adından gelen isteklere izin veriyorsunuz. Eğer bu kısmı `*` olarak ayarlarsanız, tüm alan adlarından gelen isteklere izin verilmiş olur:

  ```java
  allowedOrigins("*")
  ```

  Ancak, güvenlik açısından genelde belirli bir alan adı tanımlamak daha güvenlidir.

- **allowedMethods("GET", "POST")**:
  Bu kısım, hangi HTTP yöntemlerine izin verileceğini belirler. Örnekte sadece **GET** ve **POST** yöntemlerine izin verilmektedir. Eğer diğer yöntemlere de (PUT, DELETE vb.) izin vermek isterseniz bunları ekleyebilirsiniz:

  ```java
  allowedMethods("GET", "POST", "PUT", "DELETE")
  ```

  Varsayılan olarak tüm yöntemler (GET, POST, PUT, DELETE vb.) desteklenir, ancak bu metod ile belirli yöntemleri sınırlayabilirsiniz.

- **allowedHeaders("Content-Type")**:
  Bu kısım, hangi başlıkların (headers) CORS istekleri sırasında kabul edileceğini belirler. Örneğin, sadece **Content-Type** başlığına izin vermek için kullanılır. Eğer tüm başlıklara izin vermek isterseniz `*` kullanabilirsiniz:

  ```java
  allowedHeaders("*")
  ```

  İstek sırasında hangi başlıkların geçerli olacağını belirlemek, uygulamanızın güvenliğini arttırmaya yardımcı olabilir.

- **allowCredentials(true)**:
  Bu kısım, **kimlik doğrulama bilgileri** (örneğin, çerezler veya kimlik doğrulama başlıkları gibi) ile yapılacak isteklere izin verilip verilmeyeceğini belirler. Eğer `true` olarak ayarlanmışsa, kimlik doğrulama bilgileri ile gelen istekler kabul edilir. Bu ayarı dikkatli kullanmak gerekir çünkü uygulamanızın güvenlik risklerini artırabilir.

- **maxAge(3600)**:
  Bu kısım, tarayıcının CORS önbellekleme süresini (saniye cinsinden) tanımlar. Burada `3600` olarak ayarlanmış ve bu, CORS ayarlarının tarayıcıda **1 saat** boyunca geçerli olacağı anlamına gelir. Tarayıcı bu süre boyunca aynı kaynak için CORS doğrulamasını tekrar etmez ve bu da performans açısından fayda sağlar.

### Örnek Senaryo:
Bu CORS yapılandırması ile, uygulamanızın tüm yollarına (`/**`) `http://example.com` alan adından gelen GET ve POST isteklerine izin veriyorsunuz. Bu istekler sırasında kimlik doğrulama bilgileri (çerezler vb.) kabul ediliyor ve tarayıcı bu yapılandırmayı 1 saat boyunca önbelleğe alıyor.

### Spring Boot'ta Global CORS Yapılandırması
Bu yapılandırma, Spring Boot uygulamanızın global CORS ayarlarını yapılandırmanızı sağlar. Yani bu sınıf ve metod, tüm denetleyicilere (controller) ve API endpoint'lerine uygulanır. Eğer sadece belirli endpoint'ler için CORS ayarı yapmak isterseniz, `@CrossOrigin` anotasyonunu ilgili denetleyici veya metodun üstüne ekleyebilirsiniz.

### Genel Özeti:
Bu kod parçası, Spring Boot'ta **CORS** yapılandırmasını yönetmek için kullanılan bir sınıf örneğidir. Spring'in `WebMvcConfigurer` arayüzünü uygulayarak, uygulamanızın tüm yollarında CORS politikalarını belirlemenize olanak tanır. Bu sayede, uygulamanızın başka bir alan adından gelen isteklere nasıl yanıt vereceğini kontrol edebilirsiniz.

---
## Spring Boot Jpa Terimler
Spring Data JPA ile kullanılan bu terimler, bir veritabanı üzerinde otomatik olarak veri tablolarının yönetilmesine olanak tanıyan JPA (Java Persistence API) sağlayıcısının yapılandırma seçenekleridir. Her bir yapılandırma seçeneği, JPA'nın veritabanı şeması üzerinde nasıl bir işlem yapacağını belirler. Bu işlemler uygulamanızın başlatılması sırasında veritabanı ile nasıl etkileşimde bulunulacağını kontrol eder. Detaylı olarak açıklarsak:

### 1. `none`
- **Açıklama:**
  `none` ayarı, JPA'nın uygulama başlatılırken veritabanı üzerinde herhangi bir şema yönetimi işlemi yapmamasını sağlar. Yani JPA, tabloları yaratmaz, güncellemez veya doğrulamaz.

- **Kullanım Durumu:**
  Eğer veritabanı şema yönetimini tamamen dış bir araçla veya manuel olarak yapmak istiyorsanız ve JPA'nın şema üzerinde hiçbir etkisi olmaması gerekiyorsa `none` kullanılır.

- **Özellikleri:**
  Veritabanı üzerinde hiç değişiklik yapmaz. Tabloların ve diğer yapıların zaten hazır olduğu varsayılır.

### 2. `update`
- **Açıklama:**
  `update` ayarı, mevcut şemanın korunmasını ve yalnızca gerekli değişikliklerin (örneğin yeni sütunlar veya tablolar ekleme) uygulanmasını sağlar. Ancak, bu işlem sırasında var olan veriler korunur. `update` modunda JPA, mevcut tabloya yeni alanlar veya ilişkiler ekler ancak var olan alanları değiştirmez veya silmez.

- **Kullanım Durumu:**
  Geliştirme sırasında, veritabanındaki tabloları yeniden oluşturmadan sadece yeni eklemeler yapmak istediğinizde `update` ayarı kullanılabilir.

- **Özellikleri:**
    - Veritabanındaki tabloların varlığını kontrol eder.
    - Yeni alanlar ekler ancak mevcut verileri silmez veya değiştirmez.

### 3. `create`
- **Açıklama:**
  `create` ayarı, uygulama başlatıldığında veritabanındaki tüm mevcut tabloları silip yeniden oluşturur. Bu, veritabanında yeni tablolar oluştururken sıfırdan başlamak anlamına gelir. Var olan tablolar ve veriler tamamen silinir ve ardından şemaya uygun yeni tablolar yaratılır.

- **Kullanım Durumu:**
  Genellikle test veya geliştirme ortamlarında, veritabanı şemasının sürekli olarak sıfırdan oluşturulması gerektiğinde kullanılır. Canlı ortamda kullanılması önerilmez çünkü mevcut tüm veriler kaybolur.

- **Özellikleri:**
    - Var olan tabloları siler.
    - Tabloları yeniden oluşturur ve boş bir şema ile başlar.

### 4. `create-drop`
- **Açıklama:**
  `create-drop` ayarı, `create` moduna benzer bir şekilde başlar; uygulama başlatıldığında veritabanındaki tüm tabloları silip yeniden oluşturur. Ancak, `create-drop`'un farkı uygulama kapatıldığında, JPA'nın oluşturduğu tabloların tekrar silinmesidir. Yani, tabloyu oluşturur, uygulama kapanınca ise siler.

- **Kullanım Durumu:**
  Test senaryolarında veya geçici veritabanı kullanımında yararlıdır. Örneğin, uygulama kapandığında tüm test verilerinin silinmesi isteniyorsa `create-drop` kullanılabilir.

- **Özellikleri:**
    - Uygulama başlatıldığında tabloları oluşturur.
    - Uygulama kapatıldığında oluşturulan tabloları siler.

### 5. `validate`
- **Açıklama:**
  `validate` ayarı, veritabanındaki mevcut tabloların uygulamadaki JPA varlıklarına (entities) uygun olup olmadığını kontrol eder. Ancak, bu işlem sırasında herhangi bir tablo oluşturulmaz, değiştirilmez veya silinmez. Sadece var olan şemanın doğruluğu kontrol edilir. Eğer tablo yapısı JPA varlıklarına uygun değilse hata verir ve uygulama başlamaz.

- **Kullanım Durumu:**
  Canlı sistemlerde, şemanın doğru yapılandırıldığından emin olmak için kullanılabilir. Veritabanında herhangi bir değişiklik yapmadığı için, mevcut yapıyı korur ve sadece doğrulama yapar.

- **Özellikleri:**
    - Tablo yapısının JPA varlıklarıyla uyumlu olup olmadığını kontro
```sh 

```
---

## Spring Boot
```sh 

```
---

## Spring Boot
```sh 

```
---

## Spring Boot
```sh 

```
---

## Spring Boot
```sh 

```
---

## Spring Boot
```sh 

```
---

## Spring Boot
```sh 

```
---

## Spring Boot
```sh 

```
---

## Spring Boot
```sh 

```
---

## Spring Boot
```sh 

```
---
