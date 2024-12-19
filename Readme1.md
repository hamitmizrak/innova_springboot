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


## Spring Boot
```sh 

```
---

Spring Data Entity, Java Persistence API (JPA) ile çalışan ve veritabanındaki tabloları Java nesneleri ile eşleştiren sınıflardır. Entity sınıfları, bir tabloyu temsil eder ve veritabanı işlemlerinde kullanılır. Bu sınıflar, JPA tarafından sağlanan çeşitli özellikler ve anotasyonlarla zenginleştirilebilir. İşte Spring Data Entity'nin temel özellikleri ve detaylı açıklamaları:

---

### 1. **@Entity**
- Bir sınıfın bir veritabanı tablosunu temsil ettiğini belirtir.
- Bu anotasyon olmadan sınıf bir Entity olarak kabul edilmez.

**Örnek:**
```java
@Entity
public class Customer {
    @Id
    private Long id;
    private String name;
}
```

---

### 2. **@Table**
- Tablo ismini özelleştirmek için kullanılır. Eğer belirtilmezse, sınıf ismi tablo ismi olarak kullanılır.
- `schema` ve `catalog` gibi özelliklerle şema veya katalog adı belirtebilirsiniz.

**Örnek:**
```java
@Entity
@Table(name = "customers", schema = "public")
public class Customer {
    @Id
    private Long id;
    private String name;
}
```

---

### 3. **@Id**
- Birincil anahtar (Primary Key) sütununu belirtir.
- Entity sınıfında bir tane `@Id` alanı olmak zorundadır.

**Örnek:**
```java
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
```

---

### 4. **@GeneratedValue**
- Birincil anahtarın nasıl oluşturulacağını belirtir.
- Stratejiler:
    - `AUTO`: Varsayılan stratejiyi kullanır.
    - `IDENTITY`: Veritabanının otomatik artan özelliğini kullanır.
    - `SEQUENCE`: Bir sıra kullanır.
    - `TABLE`: Bir tablo kullanır.

**Örnek:**
```java
@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
@SequenceGenerator(name = "customer_seq", sequenceName = "customer_sequence", allocationSize = 1)
private Long id;
```

---

### 5. **@Column**
- Sütun özelliklerini tanımlamak için kullanılır.
- Özellikler:
    - `name`: Sütun adını belirler.
    - `nullable`: NULL değer alıp almayacağını belirtir.
    - `unique`: Benzersiz olup olmadığını belirtir.
    - `length`: String türü için maksimum uzunluğu belirtir.
    - `columnDefinition`: Özel bir SQL tanımı sağlar.

**Örnek:**
```java
@Column(name = "full_name", nullable = false, length = 50)
private String name;
```

---

### 6. **@Transient**
- Bir alanın veritabanında bir sütun olarak saklanmasını engeller.
- Bu alan sadece uygulama içinde kullanılabilir.

**Örnek:**
```java
@Transient
private String temporaryData;
```

---

### 7. **@Enumerated**
- Enum türündeki bir alanın nasıl saklanacağını belirtir.
- Türler:
    - `EnumType.STRING`: Enum ismini saklar.
    - `EnumType.ORDINAL`: Enum sırasını saklar.

**Örnek:**
```java
@Enumerated(EnumType.STRING)
private Status status;
```

---

### 8. **@Temporal**
- Tarih ve saat türündeki alanlar için kullanılır.
- Türler:
    - `TemporalType.DATE`: Sadece tarihi saklar.
    - `TemporalType.TIME`: Sadece saati saklar.
    - `TemporalType.TIMESTAMP`: Hem tarihi hem de saati saklar.

**Örnek:**
```java
@Temporal(TemporalType.TIMESTAMP)
private Date createdAt;
```

---

### 9. **@Lob**
- Büyük verileri (BLOB veya CLOB) saklamak için kullanılır.
- Metin veya büyük dosyalar gibi veri türlerini saklayabilirsiniz.

**Örnek:**
```java
@Lob
private String description;
```

---

### 10. **@Embedded ve @Embeddable**
- Bir sınıfın diğer bir sınıfı gömülü olarak kullanmasını sağlar.
- `@Embeddable`: Gömülü sınıfı belirtir.
- `@Embedded`: Gömülü sınıfı kullanan alanı belirtir.

**Örnek:**
```java
@Embeddable
public class Address {
    private String city;
    private String state;
}

@Entity
public class Customer {
    @Id
    private Long id;

    @Embedded
    private Address address;
}
```

---

### 11. **@OneToOne**
- Birebir ilişkiyi temsil eder.
- Özellikler:
    - `mappedBy`: İlişkinin sahibi olan alanı belirtir.
    - `cascade`: İlişkili nesnelerde işlem kaskadını sağlar.

**Örnek:**
```java
@OneToOne(cascade = CascadeType.ALL)
@JoinColumn(name = "address_id", referencedColumnName = "id")
private Address address;
```

---

### 12. **@OneToMany**
- Bire-çok ilişkiyi temsil eder.
- Genellikle `mappedBy` özelliği ile ters ilişkiyi tanımlar.

**Örnek:**
```java
@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
private List<Order> orders;
```

---

### 13. **@ManyToOne**
- Çoktan-bire ilişkiyi temsil eder.
- `fetch`: Lazy veya Eager yükleme modunu belirtir.

**Örnek:**
```java
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "customer_id", nullable = false)
private Customer customer;
```

---

### 14. **@ManyToMany**
- Çoktan-çoka ilişkiyi temsil eder.
- Genellikle bir ara tablo kullanır.

**Örnek:**
```java
@ManyToMany
@JoinTable(
    name = "student_course",
    joinColumns = @JoinColumn(name = "student_id"),
    inverseJoinColumns = @JoinColumn(name = "course_id")
)
private List<Course> courses;
```

---

### 15. **@MappedSuperclass**
- Ortak özelliklerin diğer Entity sınıflarına miras bırakılması için kullanılır.
- Kendisi bir tablo oluşturmaz.

**Örnek:**
```java
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
}
```

---

### 16. **@Inheritance**
- Kalıtım kullanılan Entity sınıflarını belirler.
- Stratejiler:
    - `SINGLE_TABLE`: Tek bir tablo kullanır.
    - `TABLE_PER_CLASS`: Her alt sınıf için ayrı bir tablo oluşturur.
    - `JOINED`: Alt sınıflar arasında birleştirilmiş bir tablo oluşturur.

**Örnek:**
```java
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {
    @Id
    private Long id;
    private String name;
}
```

---

### 17. **@Version**
- Bir alanın Entity'nin versiyonunu saklamasını sağlar.
- Genellikle optimistik kilitleme (Optimistic Locking) için kullanılır.

**Örnek:**
```java
@Version
private int version;
```

---

Bu özellikler ve anotasyonlar, Spring Data Entity'lerde en çok kullanılan ve önemli detaylardır. Spring Data JPA ile çalışırken doğru bir modelleme yapmak için bu özelliklerin nasıl kullanıldığını anlamak kritiktir. Daha fazla özelleştirme gerektiğinde, JPA spesifikasyonu veya Hibernate özellikleri kullanılabilir.



## Spring Boot
```sh 

```
---
@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
@SequenceGenerator(name = "customer_seq", sequenceName = "customer_sequence", allocationSize = 1)
private Long id;
Bu kod parçası, bir Entity sınıfındaki birincil anahtarın (Primary Key) nasıl oluşturulacağını detaylı bir şekilde açıklayan bir yapı sunar. Bu yapı, JPA (Java Persistence API) tarafından desteklenen **@Id**, **@GeneratedValue**, ve **@SequenceGenerator** anotasyonlarını içerir. Bu açıklamalarda her bir bileşeni detaylı bir şekilde ele alacağım.

---

### **1. @Id**
- **Tanım:**
  `@Id`, bir alanın birincil anahtar olduğunu belirtir. Her Entity sınıfında bir ve yalnızca bir `@Id` alanı olmak zorundadır.
- **Kullanımı:**
    - Bu alan, veritabanındaki her kaydın benzersiz olarak tanımlanmasını sağlar.
    - Diğer JPA anotasyonlarıyla birlikte (ör. `@GeneratedValue`) kullanılır.

**Örnek:**
```java
@Id
private Long id;
```

---

### **2. @GeneratedValue**
- **Tanım:**
  `@GeneratedValue`, birincil anahtarın nasıl üretileceğini belirlemek için kullanılır. Birincil anahtarın manuel olarak belirlenmesi yerine otomatik olarak üretilmesini sağlar.

- **Özellikleri:**
    - **strategy:**
      Birincil anahtarın üretim stratejisini belirler. JPA'da desteklenen stratejiler şunlardır:
        1. **GenerationType.AUTO:** Varsayılan stratejiyi kullanır. ORM sağlayıcı, veritabanına uygun en iyi stratejiyi seçer.
        2. **GenerationType.IDENTITY:** Veritabanının otomatik artan birincil anahtar özelliğini kullanır.
        3. **GenerationType.SEQUENCE:** Bir veritabanı dizisi (sequence) kullanarak anahtar üretir.
        4. **GenerationType.TABLE:** Anahtarlar için bir tablo kullanır.

    - **generator:**
      `@SequenceGenerator` gibi bir jeneratör tanımına başvuru yapar. Bu özellik, `GenerationType.SEQUENCE` ile birlikte kullanılır.

**Örnek:**
```java
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
```

---

### **3. @SequenceGenerator**
- **Tanım:**
  `@SequenceGenerator`, birincil anahtar üretimi için kullanılan bir diziyi tanımlar. Bu anotasyon, bir veritabanı dizisiyle (sequence) birlikte çalışır ve JPA'nın birincil anahtar değerlerini bu diziden almasını sağlar.

- **Özellikleri:**
    1. **name:**
        - Bu jeneratör için bir ad tanımlar.
        - `@GeneratedValue(generator = "customer_seq")` içinde kullanılan isimle eşleşmelidir.
        - Aynı projede birden fazla jeneratör tanımlayabilirsiniz, bu nedenle adların benzersiz olması gerekir.
        - **Örnek:** `"customer_seq"`

    2. **sequenceName:**
        - Veritabanında bulunan veya JPA tarafından oluşturulacak olan dizi (sequence) adını belirtir.
        - Bu, veritabanındaki gerçek dizinin adıdır.
        - **Örnek:** `"customer_sequence"`

    3. **allocationSize:**
        - Her bir dizi çağrısında kaç anahtarın önceden ayrılacağını belirler.
        - Varsayılan değer `50`'dir. Ancak, `allocationSize = 1` olarak ayarlandığında her seferinde yalnızca bir değer üretilir.
        - Daha büyük bir değer, performansı artırabilir ancak numaralandırma boşluklarına neden olabilir.

    4. **initialValue:**
        - Dizinin başlangıç değerini belirtir. Varsayılan değer `1`'dir.
        - Eğer burada tanımlanmazsa, dizinin veritabanındaki varsayılan değeri kullanılır.

**Örnek:**
```java
@SequenceGenerator(name = "customer_seq", sequenceName = "customer_sequence", allocationSize = 1)
```

---

### **Kodun Detaylı Çalışma Mantığı**

#### **Açıklama Satır Satır**
```java
@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
@SequenceGenerator(name = "customer_seq", sequenceName = "customer_sequence", allocationSize = 1)
private Long id;
```

1. **@Id:**
    - `id` alanının bu Entity için birincil anahtar olduğunu belirtir.

2. **@GeneratedValue:**
    - Birincil anahtarın otomatik olarak üretilmesini sağlar.
    - Strateji olarak `GenerationType.SEQUENCE` belirtilmiştir, yani veritabanı dizisi (sequence) kullanılacaktır.
    - Kullanılacak jeneratör `generator = "customer_seq"` ile tanımlanmıştır.

3. **@SequenceGenerator:**
    - `"customer_seq"` adında bir jeneratör tanımlanmıştır.
    - Bu jeneratör, `"customer_sequence"` adlı bir veritabanı dizisini kullanacaktır.
    - `allocationSize = 1`, her çağrıda diziden yalnızca bir değer alınacağını belirtir.

---

### **Sequence Mantığı (Veritabanı Tarafı)**

#### **Dizi (Sequence) Nedir?**
- Bir dizi, otomatik artan sayılar üreten bir veritabanı nesnesidir.
- JPA, bu diziyi birincil anahtar değerlerini üretmek için kullanır.

#### **Veritabanında Sequence Oluşturma**
Eğer diziyi manuel olarak oluşturmanız gerekiyorsa, aşağıdaki SQL komutunu kullanabilirsiniz:

**SQL Örneği:**
```sql
CREATE SEQUENCE customer_sequence
START WITH 1
INCREMENT BY 1;
```

Bu, `customer_sequence` adlı bir dizi oluşturur. İlk değer `1` olacak ve her çağrıda `1` artırılacaktır.

---

### **Neden Sequence Kullanılır?**
1. **Performans:**
    - Diziler, diğer yöntemlere göre daha performanslıdır.
    - `allocationSize` ile optimizasyon yapılabilir.

2. **Taşınabilirlik:**
    - `GenerationType.SEQUENCE`, JPA'nın çoğu veritabanı ile uyumludur.

3. **Kontrol:**
    - Dizinin adını, başlangıç değerini ve artış miktarını kontrol edebilirsiniz.

---

### **Tam Çalışan Örnek**
```java
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
    @SequenceGenerator(name = "customer_seq", sequenceName = "customer_sequence", allocationSize = 1)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;
}
```

---

### **Çalışma Süreci**
1. İlk kayıt oluşturulduğunda, JPA veritabanına `customer_sequence` dizisinden bir değer ister.
2. Dizi, başlangıçta `1` değeri döner ve `id` alanına atanır.
3. Her yeni kayıt oluşturulduğunda, dizi bir sonraki değeri (ör. `2`, `3`, `4`) döner.
4. Eğer `allocationSize` farklı ayarlanmışsa, JPA bir seferde birden fazla değer alabilir.

---

Bu açıklamalar, `@Id`, `@GeneratedValue` ve `@SequenceGenerator` anotasyonlarının detaylarını ve nasıl kullanıldıklarını açık bir şekilde ortaya koymaktadır. Bu yapı, JPA ile çalışan bir uygulamada birincil anahtar yönetiminin temel taşlarından biridir.



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

## Spring Api @PathVaraible ile @RequestParam
```sh 
### **Spring API'de `@RequestParam` ve `@PathVariable` Arasındaki Farklar**

Spring Framework'te API geliştirme sırasında sıkça kullanılan `@RequestParam` ve `@PathVariable`, istemci tarafından gönderilen verileri işlemek için kullanılır. Her iki anotasyonun da benzer işlevleri vardır, ancak kullanıldıkları yerler ve veri alma yöntemleri farklıdır. Bu iki anotasyonu ayrıntılı olarak inceleyelim.

---

### **1. `@RequestParam`**

#### **Nedir?**
- `@RequestParam`, **HTTP request**'in **query parameters** kısmındaki verileri almak için kullanılır.
- İstemci tarafında, genellikle `?param=value` formatında gönderilen verilerle çalışır.
- URL'deki parametrelerin adıyla eşleşen bir Java değişkenine veriyi aktarır.

#### **Kullanım**
- Kullanıcıdan gelen sorgu parametrelerini yakalamak için kullanılır.
- Örneğin, aşağıdaki gibi bir URL’den parametreleri alır:
  ```
GET /api/products?category=electronics&page=2
  ```

**Örnek Kod:**
```java
@GetMapping("/api/products")
public List<Product> getProducts(@RequestParam String category, @RequestParam int page) {
    // 'category' ve 'page' parametreleri URL'den alınır.
    return productService.getProductsByCategory(category, page);
}
```

**Örnek Request:**
```
GET /api/products?category=electronics&page=2
```

**Sonuç:**
- `category = "electronics"`
- `page = 2`

---

#### **Özellikleri**
1. **Varsayılan Değer Atama (`defaultValue`):**
    - Parametre eksik gönderildiğinde bir varsayılan değer kullanılabilir.
   ```java
   @GetMapping("/api/products")
   public List<Product> getProducts(@RequestParam(defaultValue = "all") String category) {
       return productService.getProductsByCategory(category);
   }
   ```
   **Örnek:**
   ```
   GET /api/products
   ```
   Sonuç: `category = "all"`

2. **Zorunluluk Kontrolü (`required`):**
    - Parametrenin istekte yer alması zorunlu mu değil mi belirlenebilir.
    - Varsayılan: `required = true`.
   ```java
   @GetMapping("/api/products")
   public List<Product> getProducts(@RequestParam(required = false) String category) {
       return productService.getProductsByCategory(category);
   }
   ```

3. **Çoklu Parametre Alma:**
    - Birden fazla aynı isimli parametre varsa, `List` veya `Array` olarak alabilirsiniz.
   ```java
   @GetMapping("/api/products")
   public List<Product> getProducts(@RequestParam List<String> categories) {
       return productService.getProductsByCategories(categories);
   }
   ```
   **Örnek:**
   ```
   GET /api/products?categories=electronics&categories=furniture
   ```
   Sonuç: `categories = ["electronics", "furniture"]`

4. **Tür Dönüşümü:**
    - `@RequestParam`, temel veri türleri (String, int, boolean vb.) ve özelleştirilmiş türlere otomatik dönüşüm yapar.

---

### **2. `@PathVariable`**

#### **Nedir?**
- `@PathVariable`, **URL’nin path (yol)** kısmındaki değişkenleri almak için kullanılır.
- İstemci tarafından gönderilen değer, genellikle URL'nin bir parçası olarak gönderilir:
  ```
  GET /api/products/{id}
  ```

#### **Kullanım**
- Genellikle, bir kaynağa özgü işlemleri gerçekleştirmek için kullanılır.
- Örneğin, bir ürünün ID’sine göre detaylarını almak.

**Örnek Kod:**
```java
@GetMapping("/api/products/{id}")
public Product getProductById(@PathVariable Long id) {
    // 'id' değişkeni URL'den alınır.
    return productService.getProductById(id);
}
```

**Örnek Request:**
```
GET /api/products/42
```

**Sonuç:**
- `id = 42`

---

#### **Özellikleri**
1. **URL'deki Dinamik Değerleri Alma:**
    - Path değişkenlerini URL’nin belirli bir kısmından alır.
   ```java
   @GetMapping("/api/orders/{orderId}/items/{itemId}")
   public OrderItem getOrderItem(@PathVariable Long orderId, @PathVariable Long itemId) {
       return orderService.getOrderItem(orderId, itemId);
   }
   ```

2. **Çoklu Parametre Kullanımı:**
    - Aynı endpoint üzerinde birden fazla `@PathVariable` kullanılabilir.
      **Örnek:**
   ```
   GET /api/orders/123/items/456
   ```
   Sonuç: `orderId = 123`, `itemId = 456`

3. **Tür Dönüşümü:**
    - Path değişkeni, String olarak gelir ve otomatik olarak belirtilen veri türüne dönüştürülür.
   ```java
   @GetMapping("/api/products/{id}")
   public Product getProduct(@PathVariable("id") Integer productId) {
       return productService.getProductById(productId);
   }
   ```

4. **İsim Eşleşmesi:**
    - Değişken ismi, metodun parametresi ile eşleşmiyorsa `@PathVariable("name")` ile açıkça belirtilir.

---

### **@RequestParam ve @PathVariable Karşılaştırması**

| **Özellik**                | **@RequestParam**                                                                                                                                                              | **@PathVariable**                                                                                              |
|----------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------|
| **Kullanıldığı Yer**        | Query parametrelerini almak için kullanılır (`?key=value`).                                                                                                                  | URL path değişkenlerini almak için kullanılır (`/path/{value}`).                                              |
| **Kullanım Türü**           | İsteğe bağlı parametreler için uygundur.                                                                                                                                      | Zorunlu (mandatory) parametreler için uygundur.                                                               |
| **Özellikler**              | Varsayılan değer atama, çoklu parametre alma, zorunluluk kontrolü yapılabilir.                                                                                               | Daha dinamik URL yapıları için uygundur.                                                                      |
| **Tür Dönüşümü**            | Temel veri türleri ve özelleştirilmiş türlere otomatik dönüşüm yapar.                                                                                                         | Benzer şekilde temel veri türleri ve özelleştirilmiş türlere otomatik dönüşüm yapar.                           |
| **Performans**              | Query string'e dayandığı için URL daha uzun olabilir, ancak daha esnek bir kullanım sunar.                                                                                   | URL path'e dayandığı için daha kısa ve okunabilir bir URL yapısı sağlar.                                       |
| **Örnek URL**               | `/api/products?category=electronics&page=2`                                                                                                                                 | `/api/products/42`                                                                                           |
| **Zorunluluk**              | Parametreler isteğe bağlı olabilir (`required = false`).                                                                                                                     | Path değişkenleri genellikle zorunludur.                                                                      |

---

### **Hangi Durumda Hangisi Kullanılır?**

- **`@RequestParam` Kullanımı:**
    - Birden fazla isteğe bağlı parametre gerektiğinde.
    - Filtreleme veya sıralama gibi sorgu işlemleri yapılacaksa.
    - Örneğin:
      ```
      GET /api/products?category=electronics&sort=asc
      ```

- **`@PathVariable` Kullanımı:**
    - Belirli bir kaynağa erişim gerekiyorsa.
    - Daha temiz ve RESTful bir URL yapısı oluşturulacaksa.
    - Örneğin:
      ```
      GET /api/products/42
      ```

---

### **Gerçek Hayat Senaryosu**

#### **Karma Kullanım Örneği:**
```java
@GetMapping("/api/orders/{orderId}")
public Order getOrderDetails(
    @PathVariable Long orderId,
    @RequestParam(required = false, defaultValue = "false") boolean includeItems) {
    if (includeItems) {
        return orderService.getOrderWithItems(orderId);
    } else {
        return orderService.getOrder(orderId);
    }
}
```

**Örnek İstekler:**
1. **Sipariş Detayları Alma:**
   ```
   GET /api/orders/123
   ```
   Sonuç: Sadece sipariş bilgisi.

2. **Sipariş Detayları ve Öğeler:**
   ```
   GET /api/orders/123?includeItems=true
   ```
   Sonuç: Sipariş bilgisi ve tüm öğeler.

---

### **Sonuç**
- **`@RequestParam`**, esnek sorgu parametreleri için kullanılır.
- **`@PathVariable`**, belirli bir kaynağa dinamik erişim sağlamak için uygundur.
- RESTful bir API geliştirme sırasında bu iki anotasyonu birlikte kullanarak hem okunabilir hem de esnek bir yapı oluşturabilirsiniz.
```
---

## Spring Boot
```sh 

```
---


## Spring Data save ve saveflush
```sh 

```
---

Spring Data JPA'da, `iAddressRepository.save` ve `iAddressRepository.saveAndFlush` metotları, bir varlığı veritabanına kaydetmek için kullanılır, ancak aralarında önemli farklar vardır:

### 1. **`save` Metodu**:
- **Amaç**: Varlığı (entity) kalıcı hale getirir, yani varlığı veritabanına kaydeder veya günceller.
- **Çalışma Şekli**:
    - Varlık `EntityManager` üzerinden yönetilir ve **Persistence Context**'e (kalıcılık bağlamına) eklenir.
    - Ancak, bu işlem doğrudan veritabanına yazılmaz; Persistence Context'te bekletilir ve **transaction commit edildiğinde** ya da `flush` çağrıldığında veritabanına yazılır.
- **Performans**:
    - Daha iyidir çünkü birden fazla değişiklik, tek bir `flush` ile toplu olarak işlenebilir.

   ```java
   AddressEntity address = new AddressEntity();
   address.setStreet("Main Street");
   iAddressRepository.save(address);
   // Değişiklik Persistence Context'e eklenir ama hemen veritabanına yazılmaz.
   ```

### 2. **`saveAndFlush` Metodu**:
- **Amaç**: Varlığı kaydeder ve hemen veritabanına yazılmasını zorlar.
- **Çalışma Şekli**:
    - Varlık hem Persistence Context'e eklenir hem de `EntityManager.flush()` çağrılarak veritabanına **hemen yazılır**.
- **Kullanım Durumu**:
    - Veritabanındaki güncel duruma hemen ihtiyaç duyuluyorsa (örneğin, başka işlemler bu kaydı hemen kullanacaksa) tercih edilir.
- **Performans**:
    - Her çağrıda veritabanına yazma işlemi yapıldığından, çok sık kullanılırsa performansı olumsuz etkileyebilir.

   ```java
   AddressEntity address = new AddressEntity();
   address.setStreet("Main Street");
   iAddressRepository.saveAndFlush(address);
   // Değişiklik hemen veritabanına yazılır.
   ```

### 3. **Önemli Farklar**:
| Özellik                | `save`                                         | `saveAndFlush`                                |
|------------------------|-----------------------------------------------|-----------------------------------------------|
| **Persistence Context**| Sadece Persistence Context'te saklanır.      | Persistence Context'e eklenir ve `flush` yapılır. |
| **Veritabanına Yazım** | Commit veya manuel `flush` ile yapılır.       | Hemen veritabanına yazılır.                   |
| **Performans**         | Daha iyi (toplu işlemler için uygundur).      | Daha düşük (her işlemde yazım yapılır).       |
| **Kullanım Durumu**    | Genel kullanım için uygundur.                 | Veritabanına anında yazılmasını gerektiren durumlarda. |

### 4. **Hangi Durumda Hangisi Kullanılmalı?**
- **`save`**: Veritabanına yazma işlemlerinin toplu olarak yapılmasını tercih ediyorsanız kullanın.
- **`saveAndFlush`**: Veritabanına hemen yazma ihtiyacı olan durumlarda (örneğin, başka bir işlem kaydedilen veriyi hemen kullanacaksa) kullanın.

### Örnek Kullanım Senaryosu:
- Eğer bir sipariş sistemi yazıyorsanız ve sipariş adresini kaydedip ardından bu adresi kullanarak bir başka işlem yapıyorsanız, `saveAndFlush` tercih edilebilir:
   ```java
   AddressEntity address = new AddressEntity();
   address.setStreet("Main Street");
   iAddressRepository.saveAndFlush(address);
   
   // Adresi hemen kullanmayı gerektiren bir işlem:
   Optional<AddressEntity> savedAddress = iAddressRepository.findById(address.getId());
   ```

Sonuç olarak, doğru seçimi yapmak için uygulamanızın gereksinimlerini göz önünde bulundurmanız önemlidir.

## Spring Transaction
```sh 

```
---
### **Spring Boot `@Transactional` Anotasyonu**

#### **Nedir?**
`@Transactional`, Spring Framework'te bir metot veya sınıf üzerinde işlem (transaction) yönetimini etkinleştiren bir anotasyondur. Veritabanı işlemleri sırasında tutarlılığı sağlamak ve otomatik olarak **commit** veya **rollback** işlemlerini gerçekleştirmek için kullanılır.

---

### **@Transactional Kullanım Alanları**
- **Veritabanı işlemlerinin atomik olmasını sağlamak:** Bir işlem ya tamamen başarılı olmalı ya da tamamen başarısız olmalıdır.
- **Hata durumunda rollback:** İşlem sırasında bir hata meydana geldiğinde, tüm yapılan değişiklikler geri alınır.
- **Büyük ölçekli uygulamalarda işlem yönetimini kolaylaştırmak.**

---

### **@Transactional Temel Özellikleri**

1. **Sınıf veya Metot Seviyesi Kullanım**
    - **Sınıf Seviyesi:** Tüm metotlara işlem yönetimi uygular.
    - **Metot Seviyesi:** Sadece ilgili metoda işlem yönetimi uygular.

   **Örnek:**
   ```java
   @Transactional
   public void updateCustomer(Customer customer) {
       customerRepository.save(customer);
   }
   ```

2. **İşlem Propagasyonu (Propagation)**
    - Mevcut bir işlem içinde mi çalışacak, yoksa yeni bir işlem mi başlatacak belirler.
    - Varsayılan: `Propagation.REQUIRED`.

   **Örnek:**
   ```java
   @Transactional(propagation = Propagation.REQUIRES_NEW)
   public void saveLog(Log log) {
       logRepository.save(log);
   }
   ```

3. **İşlem İzolasyonu (Isolation)**
    - Bir işlemin diğer işlemlerle nasıl etkileşim kuracağını belirler.
    - Örnek seviyeler: `READ_COMMITTED`, `READ_UNCOMMITTED`, `REPEATABLE_READ`, `SERIALIZABLE`.

   **Örnek:**
   ```java
   @Transactional(isolation = Isolation.REPEATABLE_READ)
   public void processOrder(Order order) {
       orderRepository.save(order);
   }
   ```

4. **Rollback Stratejisi**
    - Hangi istisnaların rollback’e neden olacağını belirler.
    - Varsayılan: Tüm `RuntimeException` rollback, `CheckedException` rollback değil.

   **Örnek:**
   ```java
   @Transactional(rollbackFor = Exception.class)
   public void performOperation() throws Exception {
       // İşlem sırasında bir hata oluşursa rollback yapılır.
   }
   ```

5. **Read-Only İşlemler**
    - Okuma işlemleri için kullanılır. Performansı artırır.
    - Varsayılan: `readOnly = false`.

   **Örnek:**
   ```java
   @Transactional(readOnly = true)
   public Customer getCustomerById(Long id) {
       return customerRepository.findById(id).orElse(null);
   }
   ```

---

### **@Transactional Parametreleri**

| **Parametre**       | **Açıklama**                                                                                                                                         | **Varsayılan**      |
|---------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------|---------------------|
| **propagation**     | İşlem yayılımı türünü belirtir. (Örneğin, `REQUIRED`, `REQUIRES_NEW`, `NESTED`)                                                                       | `REQUIRED`          |
| **isolation**       | İşlem izolasyon seviyesini belirtir. (Örneğin, `READ_COMMITTED`, `SERIALIZABLE`)                                                                     | Veritabanı varsayılanı |
| **timeout**         | İşlemin zaman aşımını saniye cinsinden belirtir. (Eğer süre aşılırsa işlem geri alınır.)                                                              | `-1` (sonsuz)       |
| **readOnly**        | İşlemin salt okunur olup olmadığını belirtir.                                                                                                        | `false`             |
| **rollbackFor**     | Hangi istisnaların rollback yapılacağını belirtir.                                                                                                   | `RuntimeException`  |
| **noRollbackFor**   | Hangi istisnaların rollback yapılmayacağını belirtir.                                                                                               | Yok                 |

---

### **Örnek Kullanım**

#### **1. Basit Kullanım**
```java
@Transactional
public void saveCustomer(Customer customer) {
    customerRepository.save(customer);
}
```
- Bu metot içinde bir hata oluşursa, yapılan işlemler geri alınır.

---

#### **2. Propagation ve Isolation ile Kullanım**
```java
@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
public void updateOrder(Order order) {
    orderRepository.save(order);
}
```
- Yeni bir işlem başlatır.
- En yüksek izolasyon seviyesi olan `SERIALIZABLE` kullanılır.

---

#### **3. Rollback İçin Özelleştirme**
```java
@Transactional(rollbackFor = Exception.class, noRollbackFor = IllegalArgumentException.class)
public void performTransaction() throws Exception {
    // Exception olduğunda rollback yapılır.
    // IllegalArgumentException durumunda rollback yapılmaz.
}
```

---

#### **4. Read-Only İşlemler**
```java
@Transactional(readOnly = true)
public List<Customer> getAllCustomers() {
    return customerRepository.findAll();
}
```
- Salt okunur modda çalışır, veri değiştirilmez.

---

### **Transaction Yönetiminde Dikkat Edilmesi Gerekenler**

1. **Proxy Mekanizması**
    - Spring, `@Transactional` için bir proxy oluşturur. Bu nedenle, anotasyon yalnızca **public** yöntemlerde çalışır.
    - `private` veya `protected` metotlarda anotasyon etkisizdir.

2. **Transaction İçi Transaction Çağrısı**
    - Aynı sınıf içinde bir metottan başka bir `@Transactional` anotasyonlu metot çağırılırsa, Spring proxy bu çağrıyı algılayamaz. Bunun çözümü, `self-injection` veya farklı bir bean aracılığıyla çağrıdır.

3. **Transaction Sınırlarını Doğru Belirleme**
    - İşlemleri gereksiz yere uzun tutmak performans sorunlarına yol açabilir.
    - Transaction süresince dış kaynak erişimlerini sınırlayın (ör. HTTP çağrıları).

4. **Timeout Ayarları**
    - Uzun süren işlemler için `timeout` belirlemek gereklidir.

---

### **Örnek Senaryo**

Bir e-ticaret uygulamasında sipariş işleme:

**Kod:**
```java
@Service
public class OrderService {

    @Transactional
    public void processOrder(Order order) {
        // Sipariş kaydet
        orderRepository.save(order);

        // Ödeme işlemini gerçekleştir
        paymentService.processPayment(order.getPaymentDetails());

        // Stok güncelle
        stockService.updateStock(order.getItems());
    }
}
```

- Sipariş kaydetme, ödeme işleme ve stok güncelleme işlemleri aynı transaction içinde gerçekleştirilir.
- Bir hata oluşursa, tüm işlemler geri alınır.

---

### **Sonuç**
Spring Boot'ta `@Transactional`, işlem yönetimini kolaylaştıran ve veri tutarlılığını garanti eden güçlü bir araçtır. Doğru yayılım ve izolasyon stratejileri seçilerek, işlem sırasında oluşabilecek çakışmalar ve tutarsızlıklar önlenebilir. Ancak işlem yönetimi sırasında performans ve kapsam konularına dikkat edilmelidir.

## Redis Nedir ?
```sh 
### Redis Nedir?

Redis (Remote Dictionary Server), **açık kaynaklı**, **hafızada çalışan** (in-memory) ve **anahtar-değer (key-value)** yapısında bir veri deposudur. Redis, performansı, esnekliği ve zengin veri yapılarıyla diğer veri depolama çözümlerinden ayrılır. 2009 yılında Salvatore Sanfilippo tarafından geliştirilmiştir ve günümüzde birçok uygulamada **veritabanı**, **önbellek** (cache) ve **mesaj kuyruğu** (message broker) olarak kullanılır.

Redis'in temel özellikleri ve avantajlarını anlamak için detaylı bir inceleme yapalım.

---

### Redis’in Temel Özellikleri

#### 1. **Hafızada Çalışma (In-Memory)**
Redis, tüm veriyi RAM’de tutar ve işlemlerini buradan gerçekleştirir. Bu, **çok yüksek hızlarda okuma/yazma** işlemleri yapılmasını sağlar. Redis'in bu özelliği onu yüksek performans gerektiren uygulamalarda vazgeçilmez bir araç haline getirir.

#### 2. **Zengin Veri Yapıları**
Redis, basit anahtar-değer çiftleriyle çalışmanın ötesinde aşağıdaki veri yapılarını da destekler:
- **Strings**: Temel metin veya ikili veri depolama.
- **Hashes**: Hash tabanlı veri yapılarını depolama (örneğin: bir kullanıcının adı, soyadı, yaşı).
- **Lists**: Sıralı veri listeleri (FIFO/LIFO).
- **Sets**: Benzersiz öğelerden oluşan koleksiyonlar.
- **Sorted Sets**: Sıralı ve benzersiz öğeler.
- **Bitmaps**: Bit düzeyinde işlemler.
- **HyperLogLogs**: Benzersiz öğelerin yaklaşık sayımını yapmak için.
- **Geospatial**: Coğrafi veriler ve sorgular.

#### 3. **Kalıcılık (Persistence)**
Redis, verileri yalnızca RAM’de tutmakla kalmaz; aynı zamanda disk üzerine yazarak kalıcılığı da sağlar:
- **RDB (Redis Database Backup)**: Belirli aralıklarla veri anlık görüntüsü alır.
- **AOF (Append-Only File)**: Her yazma işlemini diske ekleyerek veri kaybını minimuma indirir.

#### 4. **Dağıtılmış Yapı (Cluster Support)**
Redis, **cluster** modunda çalışarak verileri birden fazla sunucuya yayabilir. Bu sayede **yüksek erişilebilirlik** ve **ölçeklenebilirlik** sağlar.

#### 5. **Mesaj Kuyruğu (Pub/Sub)**
Redis, **publish/subscribe (yayınlama/abonelik)** sistemiyle uygulamalar arasında gerçek zamanlı mesajlaşma sağlar.

#### 6. **Atomic İşlemler**
Redis'teki tüm işlemler atomik yapıdadır; yani işlemler ya tamamen gerçekleşir ya da hiç gerçekleşmez.

#### 7. **LUA Script Desteği**
Redis, özelleştirilmiş işlemleri gerçekleştirmek için **Lua** betikleri çalıştırabilir.

#### 8. **Replication (Replikasyon)**
Redis, birden fazla yedeği (slave) olan bir ana (master) yapı kurarak verilerin çoğaltılmasını sağlar. Bu, veri güvenliği ve yük dengeleme açısından önemlidir.

---

### Redis Nerelerde Kullanılır?

#### 1. **Önbellekleme (Caching)**
Redis, veritabanı sorgularını hızlandırmak için en sık kullanılan veri önbellekleme çözümlerinden biridir. Örneğin:
- Sık erişilen kullanıcı oturumları.
- Sık sorgulanan ürün verileri.

#### 2. **Mesajlaşma ve Kuyruk Sistemleri**
Redis'in Pub/Sub mekanizması, sistemler arasında gerçek zamanlı veri iletiminde kullanılır. Ayrıca **List** yapısı sayesinde kuyruk işlemleri gerçekleştirilir.

#### 3. **Gerçek Zamanlı Analitik**
Redis, hızlı okuma/yazma işlemleri sayesinde gerçek zamanlı analitik ve metriklerin tutulmasında kullanılır.

#### 4. **Coğrafi Veri İşleme**
Redis’in geospatial desteği, harita uygulamaları veya konum tabanlı hizmetlerde coğrafi verilerin saklanmasını ve sorgulanmasını kolaylaştırır.

#### 5. **Sayımlar ve Sayaçlar**
Redis'in HyperLogLog veri yapısı, benzersiz ziyaretçi sayımı gibi işlemler için idealdir.

---

### Redis’in Avantajları

1. **Hız**: RAM üzerinde çalıştığı için mikro saniyelerle ifade edilen gecikme süreleri sunar.
2. **Esneklik**: Farklı veri yapıları sayesinde birçok kullanım senaryosuna uygundur.
3. **Kullanım Kolaylığı**: Basit ve anlaşılır komutlarla çalışır.
4. **Kapsamlı Desteği**: Python, Java, Node.js, PHP, Go gibi birçok dil için kütüphaneleri mevcuttur.
5. **Topluluk ve Destek**: Büyük bir topluluk ve iyi belgelenmiş dokümantasyonu vardır.

---

### Redis Komutları

#### Veri İşlemleri
- `SET key value`: Bir anahtar oluşturur ve değer atar.
- `GET key`: Anahtarın değerini getirir.
- `DEL key`: Anahtarı ve değerini siler.

#### Liste İşlemleri
- `LPUSH key value`: Listeye baştan öğe ekler.
- `RPUSH key value`: Listeye sondan öğe ekler.
- `LPOP key`: Listenin başından öğe çeker.

#### Hash İşlemleri
- `HSET key field value`: Bir hash içine alan ve değer ekler.
- `HGET key field`: Hash içindeki belirli bir alanın değerini getirir.

#### Set İşlemleri
- `SADD key value`: Bir sete öğe ekler.
- `SMEMBERS key`: Setin tüm öğelerini getirir.

---

### Redis’in Dezavantajları

1. **Bellek Tüketimi**: Veriler RAM’de saklandığı için büyük veri setlerinde yüksek bellek kullanımı gerektirir.
2. **Veri Kalıcılığı**: Tamamen kalıcı bir depolama çözümü değildir. (Ancak AOF ile veri kaybı minimize edilir.)
3. **Sınırlı Sorgulama**: Geleneksel SQL sorgularını desteklemez.

---

### Redis Kurulumu ve Kullanımı

#### Kurulum
Redis'i Linux tabanlı bir sistemde kurmak için:

```bash
sudo apt update
sudo apt install redis-server
```

#### Redis’i Başlatma ve Durum Kontrolü
Redis hizmetini başlatmak için:
```bash
sudo systemctl start redis
```

Durum kontrolü:
```bash
sudo systemctl status redis
```

#### Redis CLI Kullanımı
Redis ile etkileşime geçmek için komut satırı arayüzü (CLI) kullanılabilir:
```bash
redis-cli
```

---

### Redis ve Rakipleri

Redis, aşağıdaki rakipleriyle kıyaslandığında hız ve esneklik açısından öne çıkar:
- **Memcached**: Yalnızca anahtar-değer çiftlerini destekler, ancak Redis kadar zengin veri yapısına sahip değildir.
- **Cassandra**: Büyük veri depolama için daha uygundur, ancak Redis kadar hızlı değildir.

---

### Sonuç

Redis, yüksek hız, esneklik ve güçlü veri yapıları sunarak modern yazılım geliştirme süreçlerinde kritik bir rol oynar. Veritabanı olarak kullanılabilir, önbellek katmanı oluşturabilir ve mesaj kuyruğu olarak yapılandırılabilir. Hem küçük hem de büyük ölçekli uygulamalar için idealdir.

Redis, performans ve esneklik gerektiren projelerde güçlü bir araçtır ve doğru kullanıldığında yazılım mimarisine büyük katkılar sağlar.
```
---

## Redis Docker Compose
```sh 
# Projemizin sağlıklı çalışması için öncelikler redis servera kurulu olması gerekiyor
docker container run --name spring_redis -p 6379:6379 -d redis
```
---

## Redis pom.xml
```sh 
        <!-- #### REDIS #################################################  -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-cache</artifactId>
        </dependency>

        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
        </dependency>

       <dependency>
           <groupId>org.springframework.boot</groupId>
           <artifactId>spring-boot-starter-data-redis</artifactId>
           <exclusions>
               <exclusion>
                   <groupId>io.lettuce.core</groupId>
                   <artifactId>lettuce-core</artifactId>
               </exclusion>
           </exclusions>
       </dependency>

        <dependency>
            <groupId>redis.clients</groupId>
            <artifactId>jedis</artifactId>
        </dependency>
```
---

## Redis Config
```sh 
package com.hamitmizrak.innova_springboot.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import java.time.Duration;

/*
Eklenmiş Özellikler:
redisTemplate:
Key ve Value için StringRedisSerializer ve GenericJackson2JsonRedisSerializer kullanır.
Genellikle standart CRUD işlemleri için kullanılır.

customCacheConfiguration:
Cache isimleri için bir customPrefix ekler.
1 saatlik bir geçerlilik süresi tanımlar.
Null değerlerin cache yapılmasını engeller.

loggingRedisTemplate:
Redis işlemleri için loglama desteği eklenmiştir.
Transaction desteği aktif hale getirilmiştir.

transactionalRedisTemplate:
RedisTemplate üzerinde transaction desteği sağlanmıştır.
Bu, aynı işlem içindeki birçok Redis komutunun atomik olarak çalıştırılmasını sağlar.

cacheConfiguration:
Daha kısa süreli cache işlemleri için 1 dakikalık TTL belirler.
Null değerlerin cache yapılmasını önler.
Bu yapılandırma, Redis'i hem cache hem de transaction bazlı işlemler için esnek bir şekilde kullanmanıza olanak sağlar. Ayrıca, farklı kullanım senaryoları için ayrı RedisTemplate tanımları sağlanmıştır.
*/


@Configuration
@EnableCaching
public class RedisConfig {

    /**
     * Standart bir RedisTemplate yapılandırması.
     * Key ve Value için sırasıyla StringRedisSerializer ve GenericJackson2JsonRedisSerializer kullanılır.
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate;
    }

    /**
     * Cache Configuration - Cache prefix ekleme ve TTL (Time To Live) belirleme.
     */
    @Bean
    public RedisCacheConfiguration customCacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .prefixCacheNameWith("customPrefix::") // Cache isimlerine özel bir prefix ekler
                .entryTtl(Duration.ofHours(1)) // Cache geçerlilik süresini 1 saat olarak belirler
                .disableCachingNullValues() // Null değerleri cache’lemez
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
    }

    /**
     * RedisTemplate ile özel loglama ve transaction desteği.
     */
    @Bean
    public RedisTemplate<String, Object> loggingRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setEnableTransactionSupport(true); // Transaction desteği
        return template;
    }

    /**
     * Transaction destekli RedisTemplate yapılandırması.
     */
    @Bean
    public RedisTemplate<String, Object> transactionalRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setEnableTransactionSupport(true); // Transaction desteği
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate;
    }

    /**
     * Default Cache Configuration - Özellikle kısa süreli cache kullanımları için.
     */
    @Bean
    public RedisCacheConfiguration cacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(1)) // 1 dakikalık geçerlilik süresi
                .disableCachingNullValues() // Null değerlerini cache’lemez
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
    }
} //end RedisConfig


```
---


## Redis application.properties
```sh 
######################################################################################
### REDIS CONFIG   ########################################################################
######################################################################################
# Redis server adresi ve portu
spring.data.redis.host=localhost
spring.data.redis.port=6379
spring.data.redis.timeout=60000

# Cache yapılandırması
spring.cache.type=redis

```
---


## Redis @Cacheable
```sh 
      // FIND BY ID (Address)
    // REDIS : için aşağıdaki linktten çalışıp çalışmadığını bu linkten anlayabiliriz
    // http://localhost:4444/api/address/v1.0.0/find/1
    @Cacheable(value = "addressFindByIdCache", key = "#id")
    @Override
    public AddressDto objectServiceFindById(Long id) {
        //REDIS
        System.err.println("Redis başlamadan önce ilk burası çalışacak ancak redis çalışıyorsa bunu cache dakikası bitene kadar veya flush yapana kadar görmeyeceksiniz "+id);
        return iAddressRepository.findById(id)
                .map(AddressMapper::AddressEntityToAddressDto)
                .orElseThrow(()-> new _404_NotFoundException(id+" nolu veri yoktur"));
    }
```
---




## Spring Boot
```sh 

```
---
