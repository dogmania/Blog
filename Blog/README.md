# REST API
## 웹의 장점을 최대한 활용하는 REST API
REST는 Representational State Transfer를 줄인 표현이다. 명확하고 쉬운 API를 말한다. REST API는 URL의 설계 방식을 말한다.
### REST API의 특징
RESTAPI는 서버/클라이언트 구조, 무상태, 캐시 처리 기능, 계층화, 인터페이스 일관성과 같은 특징이 있다.
### REST API의 장점과 단점
- 장점: 
  - URL만 보고도 무슨 행동을 하는 API인지 명확하게 알 수 있다.
  - 상태가 없기 때문에 클라이언트와 서버의 역할이 명확하게 분리된다.
  - HTTP 표준을 사용하는 모든 플랫폼에서 사용할 수 있다.
- 단점:
  - HTTP 메서드(GET, POST 등)의 개수에 제한이 있다
  - 설계를 하기 위해 공식적으로 제공되는 표준 규약이 없다

그럼에도 불구하고 주소와 메서드만 보고 요청의 내용을 파악할 수 있다는 강력한 장점이 있어 많은 개발자가 사용한다.
### REST API를 사용하는 방법
#### 규칙1. URL에는 동사를 쓰지 말고, 자원을 표시해야 한다
ex) /students/1
#### 규칙2. 동사는 HTTP 메서드로
- POST: create
- GET: read
- PUT: update
- DELETE: delete

이들을 묶어서 CRUD라고 한다.

# 블로그 개발을 위한 엔티티 구성하기
## 엔티티 구성하기
domain 패키지를 만들고 Article 클래스를 정의한다. Article 클래스에는 Builder 어노테이션이 존재하는데, 이는 롬복에서 지원하는 어노테이션이다. 이것을 생성자 위에 입력하면 빌더 패턴 방식으로 객체를 생성할 수 있어 편리하다. 

빌더 패턴을 사용하면 어느 필드에 어떤 값이 들어가는 지 명시적으로 파악할 수 있다. 예를 들어 Article 객체를 생성할 때 title에는 abc를 content에는 def값으로 초기화한다고 하자
```java
new Article("abc", "def");

Article.builder()
  .title("abc")
  .content("def")
  .build()
```
첫번째와 두번째를 비교해보면 왜 빌더 패턴이 유용한지 알 수 있다. 

또한 Article 클래스 상단에 @Getter 어노테이션을 추가하면 클래스 필드에 별도의 코드 없이 모든 필드에 대한 접근자 메서드를 만들 수 있게 된다. 다시 말해, getId() 이런 함수를 만들지 않아도 된다. @NoArgsConstructor를 추가하면 기본 생성자 코드를 작성하지 않아도 된다.
이렇게 롬복의 애너테이션을 활용하면 코드를 반복해 입력할 필요가 없어져서 가독성이 향상된다.

## 리포지터리 만들기
repository 패키지를 생성하여 BlogRepository 인터페이스 파일을 만들고 JpaRepository를 상속받는다.

# 블로그 글 작성을 위한 API 구현하기
## 서비스 메서드 코드 작성하기
### 블로그에 글을 추가하는 코드
서비스 계층에서 요청을 받을 객체인 AddArticleRequest 객체를 생성하고 BlogService 클래스를 생성한 다음에 블로그 글 추가 메서드인 save()를 구현한다

#### AddArticleRequest
```java
@NoArgsConstructor  // 기본 생성자 추가
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자 추가
@Getter
public class AddArticleRequest {
    
    private String title;
    private String content;
    
    public Article toEntity() {
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }
}
```
위의 코드에서 toEntity()는 빌더 패턴을 사용하여 DTO를 엔티티로 만들어주는 메서드이다.

#### BlogService
```java
@RequiredArgsConstructor    // final이 붙거나 @NotNull이 붙은 필드의 생성자 추가
@Service    // 빈으로 등록
public class BlogService {
    private final BlogRepository blogRepository;
    
    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }
}
```
@RequiredArgsConstructor는 빈을 생성자로 생성하는 롬복에서 지원하는 애너테이션이다. final 키워드나 @NotNull이 붙은 필드로 생성자를 만들어준다. @Service 애너테이션은 해당 클래스를 빈으로 서블릿 컨테이너에 등록해준다. 

현재 BlogRepository에는 아무런 코드가 존재하지 않는다. 그럼에도 save() 메서드를 호출할 수 있는 것은 save() 메서드는 JpaRepository에서 지원하는 저장 메서드 save()이고 BlogRepository가 JpaRepository를 상속받고 있기 때문이다. 따라서 AddArticleRequest 클래스에 저장된 값들을 article 데이터베이스에 저장할 수 있다.

## 컨트롤러 메서드 코드 작성하기
#### BlogApiService
```java
@RequiredArgsConstructor
@RestController // Http Response Body에 객체 데이터를 Json 형식으로 반환하는 컨트롤러
public class BlogApiController {
    private final BlogService blogService;
    
    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request) { // @RequestBody로 요청 본문 값 매핑
        Article savedArticle = blogService.save(request);
        
        // 요청한 자원이 성공적으로 생성되었으며 저장된 블로그 글 정보를 응답 객체에 담아 전송
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);
    }
}
```
@RestController 애너테이션을 클래스에 붙이면 HTTP 응답으로 객체 데이터를 Json 형식으로 반환한다. @RequestBody 애너테이션은 HTTP를 요청할 때 응답에 해당하는 값을 @RequestBody 애너테이션이 붙은 대상 객체인 AddArticleRequest에 매핑한다. ResponseEntity.status().body()는 응답 코드로 201, 즉, Created를 응답하고 테이블에 저장된 객체를 반환한다.

#### 꼭 알아두면 좋을 응답 코드
- 200: 요청이 성공적으로 수행되었음
- 201: 요청이 성공적으로 수행되었고 새로운 리소스가 생성되었음
- 400: 요청 값이 잘못되어 요청에 실패했음
- 403: 권한이 없어 요청에 실패했음
- 404: 요청 값으로 찾은 리소스가 없어 요청에 실패했음
- 500: 서버 상에 문제가 있어 요청에 실패했음

## API 실행 테스트
실제 데이터를 확인하기 위해 h2를 활성화한다. application.yml 파일에 다음 코드를 추가한다
```yml
spring:
  jpa:
    ...생략...
  datasource:
    url: jdbc:h2:mem:testdb
  h2:
    console:
      enabled: true
```
### 1. 포스트맨으로 요청 보내기
### 2. 스프링 부트 서버를 켠 상태로 유지하고 localhost:8080/h2-console에 접속
각 항목의 값을 잘 입력하고 connect 클릭 -> sql 구문 사용해서 데이터가 잘 저장되어 있는지 확인

## 반복 작업을 줄여 줄 테스트 코드 작성하기
매번 H2 콘솔에 접속해 쿼리를 입력해 확인하는 것은 번거로움. 테스트 코드를 작성하면 작업이 줄어든다. BlogApiController 클래스에 option + enter 클릭 -> create test 클릭
#### BlogApiControllerTest
```java
@SpringBootTest // 테스트용 애플리케이션 컨텍스트
@AutoConfigureMockMvc   // MockMvc 생성 및 자동 구성
class BlogApiControllerTest {
    
    @Autowired
    protected MockMvc mockMvc;
    
    @Autowired
    protected ObjectMapper objectMapper; // 직렬화, 역직렬화를 위한 클래스
    
    @Autowired
    private WebApplicationContext context;
    
    @Autowired
    BlogRepository blogRepository;
    
    @BeforeEach
    public void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
        blogRepository.deleteAll();
    }
}
```
- ObjectMapper 클래스로 만든 객체는 자바 객체를 Json 데이터로 변환하는 직렬화 또는 반대로 Json 데이터를 자바에서 사용하기 위해 자바 객체로 변환하는 역직렬화할 때 사용한다.
- 다음으로는 블로그 글 생성 API를 테스트하는 코드를 작성한다.
```java
@SpringBootTest // 테스트용 애플리케이션 컨텍스트
@AutoConfigureMockMvc   // MockMvc 생성 및 자동 구성
class BlogApiControllerTest {
    
    // 생략..
    
    @DisplayName("addArticle: 블로그 글 추가에 성공한다.")
    @Test
    public void addArticle() throws Exception {
        // given
        final String url = "/api/articles";
        final String title = "title";
        final String content = "content";
        final AddArticleRequest userRequest = new AddArticleRequest(title, content);
        
        // 객체 Json으로 직렬화
        final String requestBody = objectMapper.writeValueAsString(userRequest);
        
        // when
        // 설정한 내용을 바탕으로 요청 전송
        ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody));
        
        // then
        result.andExpect(status().isCreated());

        List<Article> articles = blogRepository.findAll();
        
        assertThat(articles.size()).isEqualTo(1);   // 크기가 1인지 검증
        assertThat(articles.get(0).getTitle()).isEqualTo(title);
        assertThat(articles.get(0).getContent()).isEqualTo(content);
    }
}
```
- writeValueAsString() 메서드를 사용해서 객체를 Json으로 직렬화
- MockMvc를 사용해 HTTP 메서드, URL, 요청 본문, 요청 타입 등을 설정한 뒤 설정한 내용을 바탕으로 테스트 요청 전송
- contentType은 요청을 보낼 때 Json, XML 등 다양한 타입 중 하나를 골라 요청을 보낸다

# 블로그 글 목록 조회를 위한 API 구현하기
## 서비스 메서드 코드 작성하기
BlogService 파일에 다음 함수를 추가한다.
```java
    public List<Article> findAll() {
        return blogRepository.findAll();
    }
```
- JPA 지원 메서드인 findAll()을 호출해 article 테이블에 저장되어 있는 모든 데이터를 조회한다.

## 컨트롤러 메서드 코드 작성하기
먼저 응답을 위한 DTO를 만든다. ArticleResponse 파일을 생성하고 다음과 같이 코드 작성
```java
@Getter
public class ArticleResponse {
    
    private final String title;
    private final String content;
    
    public ArticleResponse(Article article) {
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}
```
다음으로는 BlogApiController 파일에 다음의 함수를 추가
```java
    @GetMapping("/api/articles/")
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {
        List<ArticleResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();
        
        return ResponseEntity.ok()
                .body(articles);
    }
```
상태 코드 200과 함께 리스트를 HTTP 응답으로 반환한다

## 실행 테스트하기
테스트를 쉽게 하기 위해 data.sql 파일을 생성하고 다음과 같이 코드를 작성한다
```sql
INSERT INTO article (title, content) VALUES ('제목 1', '내용 1')
INSERT INTO article (title, content) VALUES ('제목 2', '내용 2')
INSERT INTO article (title, content) VALUES ('제목 3', '내용 3')
```
이제 스프링 부트를 다시 실행하고 포스트맨에서 글 전체 조회를 하면 sql로 입력한 데이터가 조회되는 것을 확인할 수 있다.

## 테스트 코드 작성하기
BlogApiController 파일에 다음 코드를 추가
```java
    @DisplayName("findAllArticles: 블로그 글 목록 조회에 성공한다.")
    @Test
    public void findAllArticles() throws Exception {
        // given
        final String url = "/api/articles";
        final String title = "title";
        final String content = "content";
        
        blogRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());
        
        //when
        final ResultActions resultActions = mockMvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON));
        
        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].content").value(content))
                .andExpect(jsonPath("$[0].title").value(title));
    }
```

# 블로그 글 조회 API 구현하기
## 서비스 메서드 코드 작성하기 
BlogService에 다음 코드 추가
```java
    public Article findById(long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
    }
```
JPA에서 제공하는 findById 메서드를 사용하고 조회 결과가 없으면 IllegalArgumentException 예외를 발생한다.

## 컨트롤러 메서드 코드 작성하기
BlogApiController에 다음 코드 추가
```java
    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id) {
        Article article = blogService.findById(id);
        
        return ResponseEntity.ok()
                .body(new ArticleResponse(article));
    }
```
@PathVariable 애너테이션은 URL에서 값을 가져오는 애너테이션이다. 이 애너테이션이 붙은 메서드의 동작 원리는 /api/articles/3 GET 요청을 받으면 id에 3이 들어온다. 그리고 앞서 만든 findById() 메서드로 넘어가 3번 블로그 글을 찾는다.

## 테스트 코드 작성하기
BlogApiControllerTest에 다음 코드를 추가
```java
    @DisplayName("findArticle: 블로그 글 조회에 성공한다.")
    @Test
    public void findArticle() throws  Exception {
        //given
        final String url = "/api/articles/{id}";
        final String title = "title";
        final String content = "content";

        Article savedArticle = blogRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());

        //when
        final ResultActions resultActions = mockMvc.perform(get(url, savedArticle.getId()));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value(content))
                .andExpect(jsonPath("$.title").value(title));
    }
```

# 블로그 글 삭제 API 구현하기
## 서비스 메서드 코드 작성하기
BlogService에 다음 코드 추가
```java
    public void delete(long id) {
        blogRepository.deleteById(id);
    }
```

## 컨트롤러 메서드 코드 작성하기
BlogApiController에 다음 코드 추가
```java
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable long id) {
        blogService.delete(id);

        return ResponseEntity.ok()
                .build();
    }
```
### 왜 delete에서만 build()를 호출하는 것인가?
ResponseEntity.ok()는 기본적으로 ResponseEntity.BodyBuilder 객체를 반환한다. 이때 body() 함수를 사용하면 BodyBuilder에 본문을 추가하여 원하는 데이터를 반환할 수 있는 것.

그런데 delete 함수에는 본문에 추가할 데이터가 없기 때문에 body() 함수를 호출하지 않고 대신 build() 함수를 호출하여 ResponseEntity<Void>를 반환할 수 있도록 한다. build()를 호출하지 않으면 ResponseEntity 객체가 생성되지 않는다.

## 테스트 코드 작성하기
BlogApiControllerTest에 다음 코드 추가
```java
    @DisplayName("deleteArticle: 블로그 글 삭제에 성공한다.")
    @Test
    public void deleteArticle() throws Exception {
        //given
        final String url = "/api/articles/{id}";
        final String title = "title";
        final String content = "content";

        Article savedArticle = blogRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());

        //when
        mockMvc.perform(delete(url, savedArticle.getId()))
                .andExpect(status().isOk());

        //then
        List<Article> articles = blogRepository.findAll();

        assertThat(articles).isEmpty();
    }
```