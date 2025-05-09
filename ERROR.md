<img src="https://r2cdn.perplexity.ai/pplx-full-logo-primary-dark%402x.png" class="logo" width="120"/>

# org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'entityManagerFactory' defined in class path resource [org/springframework/boot/autoconfigure/orm/jpa/HibernateJpaConfiguration.class]: Unable to create requested service [org.hibernate.engine.jdbc.env.spi.JdbcEnvironment] due to: Unable to resolve name [org.hibernate.dialect.MariaDB103Dialect] as strategy [org.hibernate.dialect.Dialect]

에러 원인이 뭐지?

이 에러의 핵심 원인은 Hibernate가 지정된 Dialect 클래스(`org.hibernate.dialect.MariaDB103Dialect`)를 찾지 못해서 발생하는 것입니다. 구체적인 원인과 해결 방법은 다음과 같습니다.

---

## 에러 원인

- **Hibernate Dialect 클래스명을 잘못 지정했거나, 해당 Dialect 클래스가 현재 프로젝트의 Hibernate 버전에 존재하지 않아서** 발생합니다.
- Hibernate 6.x 이상에서는 Dialect 클래스명이나 위치가 변경되었거나, 일부 Dialect가 삭제/통합된 경우가 있습니다[^3][^5].
- `org.hibernate.dialect.MariaDB103Dialect`가 프로젝트에 포함된 Hibernate 버전에서 지원되지 않거나, 오타 또는 버전 미스매치가 있을 수 있습니다.

---

## 해결 방법

1. **Hibernate 버전 확인**
  - 사용 중인 Hibernate 버전이 MariaDB103Dialect를 지원하는지 확인하세요.
  - Hibernate 6 이상에서는 Dialect 명칭이 변경되었으니 공식 문서를 참고하세요[^3][^5].
2. **Dialect 설정 변경 또는 제거**
  - Dialect를 명시적으로 지정하지 않아도 Spring Boot가 자동으로 적절한 Dialect를 선택합니다[^5].
  - 필요하다면, Hibernate 6.x에서는 아래와 같이 `org.hibernate.dialect.MariaDBDialect`로 지정하는 것이 더 안전합니다.
  - 예시:

```yaml
spring:
  jpa:
    database-platform: org.hibernate.dialect.MariaDBDialect
```

    - 또는 Dialect 설정을 아예 제거하고, `spring.datasource.url`만 올바르게 지정하면 Spring Boot가 자동 감지합니다.
3. **의존성 및 오타 확인**
  - `org.hibernate.dialect.MariaDB103Dialect`에 오타가 없는지, 그리고 해당 클래스가 실제로 존재하는지 확인하세요.
  - MariaDB Dialect가 없는 경우, Hibernate와 Spring Boot 버전을 맞추거나, 최신 버전으로 업그레이드하세요.

---

## 참고

- MySQL/MariaDB Dialect 관련 문제는 Hibernate 버전 업그레이드, Dialect 명칭 변경, 또는 명시적 설정 필요성 등에서 자주 발생합니다[^1][^3][^5].
- Dialect 설정을 명시하지 않으면 Spring Boot가 자동으로 감지하는 것이 권장되는 방식입니다[^5].

---

**요약:**
`org.hibernate.dialect.MariaDB103Dialect`가 현재 Hibernate 버전에 없거나, Dialect 명칭이 변경되어 발생한 오류입니다.
`org.hibernate.dialect.MariaDBDialect`로 변경하거나, Dialect 설정을 제거하고 Spring Boot의 자동 감지 기능을 사용하는 것이 가장 확실한 해결 방법입니다[^3][^5].

<div style="text-align: center">⁂</div>

[^1]: https://velog.io/@yesue/SpringBoot-Failed-to-initialize-JPA-EntityManagerFactory-Unable-to-create-requested-service-...-due-to-Unable-to-resolve-name-org.hibernate.dialect.MySQL5InnoDBDialect-as-strategy-...-%EC%97%90%EB%9F%AC

[^2]: https://woolfiekim.github.io/project/project-error1/

[^3]: https://devthriver.tistory.com/12

[^4]: https://stackoverflow.com/questions/33519572/unable-to-resolve-name-org-hibernate-dialect-mysql5dialect-as-strategy-org-h

[^5]: https://ppusda.tistory.com/81

[^6]: https://discourse.hibernate.org/t/unable-to-resolve-name-org-hibernate-dialect-mysqlinnodbdialect-as-strategy-org-hibernate-dialect-dialect-with-hibernate-6-2/7936

[^7]: https://alsrudalsrudalsrud.tistory.com/313

[^8]: https://stackoverflow.com/questions/69820438/unable-to-resolve-name-org-hibernate-dialect-mariadbdialect-as-strategy-org-h

[^9]: https://2minmin2.tistory.com/98

[^10]: https://developer.jboss.org/thread/276212

[^11]: https://velog.io/@nays33/오류-해결No-identifier-specified-for-entity

[^12]: https://cyeongy.tistory.com/entry/springjpapropertieshibernatedialect-orghibernatedialectmysql5innodbdialect-사용-불가-Deprecated-문제

[^13]: https://studynin.tistory.com/34

[^14]: https://dev.to/hastycodea/resolving-the-unable-to-resolve-name-orghibernatedialectmysqldialect-error-in-spring-boot-17jj

[^15]: https://stackoverflow.com/questions/75620312/cant-connect-my-spring-boot-application-with-my-mariadb-database

[^16]: https://github.com/spring-projects/spring-framework/issues/31889

[^17]: https://da77777.tistory.com/96

[^18]: https://devje.tistory.com/322

[^19]: https://github.com/spring-projects/spring-boot/issues/29643

[^20]: https://okky.kr/questions/1495465

---