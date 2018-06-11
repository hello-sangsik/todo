# spring boot for todo list

### Environment
`Lombok` 라이브러리를 사용하기 때문에 설정 작업이 필요하다. 

**IntelliJ IDEA**에서는 아래 순서로 설정한다.

  1. File → Settings → Plugins → Browse repositories → `Lombok Plugin` 선택 → **Install plugin** 실행 후 재시작
  2. File → Settings → Build → Build, Execution, Deployment → Compiler → Annotaion Processors → **Enable annotation processing** 체크

### Database
`H2 Database` 를 사용한다.
```
http://localhost:8080/h2-console
```

### API Documentation
`Swagger 2` 기반의 온라인 문서를 사용한다.
```
http://localhost:8080/swagger-ui.html
```

### View Template
미정