# blog
- KaKao와 Naver의 open api를 사용한 블로그 검색 서비스를 제공합니다.

# 환경
- Java 17 / Spring Boot 3.0.4
- gradle.kotlin 버젼 기반
- DB: H2, flyway
- Spock 기반 테스트 케이스

# 빌드 결과물

# 프로젝트 구성
```
blog
├── apps                  
│   └── blog-api              - 블로그 app(Controller)
├── buildSrc                  - 그래들 의존성 버전 관리
└── libs
    ├── adapter-http          - http를 사용한 외부 통신
    ├── adapter-persistence   - jpa 기반의 db 통신
    └── application           - 어플리케이션의 비즈니스 로직
```

# 특징
1. KAKAO, NAVER 오픈 api의 연동을 통해 각 블로그 검색을 수행할 수 있다.
2. 각 블로그 검색 api 수행시, 자동으로 통계 데이터가 적재되어진다.
3. CircuitBreakers의 연동을 통해 KAKAO 서버의 수행 실패시 NAVER 연동 결과를 가져온다.
4. 통계에 대한 디비는 H2디비의 로컬 디비를 사용하며, flyway의 수행을 통해 첫 데이터는 자동 init되어 진다.
5. 통계 디비에 대한 쿼리는 query dsl을 사용하여 쿼리를 수행하게 된다.
6. 테스트는 spock을 통해 어플리케이션 단의 테스트 코드를 수행한다.

# API spec
## 블로그 검색

### 요청
``
GET /api/v1/blog
``

Parameter

| Name      | Type      | Description                                  | Required |
|-----------|-----------|----------------------------------------------|----------|
| `type`    | `String`  | 검색 타입(KAKAO, NAVER)                          | O        |
| `keyword` | `String`  | 검색 키워드                                       | O        |
| `url`     | `String`  | url 기반으로한 특정 블로그 검색                          | X        |
| `sort`    | `String`  | KAKAO- ACCURACY, RECENCY / NAVER - sim, date | X        |
| `page`    | `Integer` | 요청 페이지 번호                                    | X        |
| `countPerPage`    | `Integer` | 한 페이지의 사이                                    | X        |


Response
```json
{
  "success": true,
  "code": 200,
  "message": null,
  "data": {
    "documents": [
      {
        "title": "경기 김포시 통진읍 토지 경매 2022타경<b>123</b>",
        "content": "안녕하세요. 법무법인 올곧음 경매팀장 변재형입니다. 경기 김포시 통진읍 토지 경매 2022타경<b>123</b>를 살펴보겠습니다. 경매는 아는 만큼 보이고 보이는 만큼 돈을 법니다. 경매 컨설팅은 변호사가 있는 곳이 안전하고 정확하며 빠릅니다. 컨설팅 비용이 비슷할 땐 이왕이면 경매전문 신동렬 변호사가 있는 법무법인...",
        "url": "http://auctionbyun.tistory.com/485",
        "name": "변팀장의 경매 컨설팅",
        "thumbnail": "",
        "createdAt": "2023-03-16 11:40:12"
      }
      ..
    ],
    "pagination": {
      "totalPageCount": 800,
      "totalItemCount": 1529659,
      "currentPage": 1,
      "countPerPage": 10,
      "hasNext": true
    }
  }
}
```


## 2. 검색어 통계 조회
``
GET /api/v1/blog
``


Parameter

| Name    | Type     | Description              | Required |
|---------|----------|--------------------------|----------|
| `type`  | `String` | 검색 타입(ALL, KAKAO, NAVER) | X        |
| `limit` | `Number` | 인기 검색의 제한 수              | X        |

Response
```json
{
  "success": true,
  "code": 200,
  "message": null,
  "data": [
    {
      "keyword": "NO.1",
      "type": "KAKAO",
      "count": 10000000,
      "createdAt": "2023-03-18 00:00:00",
      "modifiedAt": "2023-03-18 00:00:00"
    },
    {
      "keyword": "INTERNET",
      "type": "KAKAO",
      "count": 20000,
      "createdAt": "2023-03-18 00:00:00",
      "modifiedAt": "2023-03-18 00:00:00"
    }
  ]
}
```