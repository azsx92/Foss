## 자꾸 Bean을 생성할 수 없다는 에러가 발생
- org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'FCMInitializer': Invocation of init method failed
  -new ClassPathResource(firebaseConfigPath).getInputStream()
- 더 상세하기 들어가면 application.yml에 환경변수를 설정을 @Value로 받는데 자꾸 안되는다는 것이다.
- 알고 보니 내가 다른 어노테이션으로 설정을 하게되어 Bean을 설정하지 못했던 것이었다.


<img src="https://r2cdn.perplexity.ai/pplx-full-logo-primary-dark%402x.png" class="logo" width="120"/>

# @Value 각각 차이점과 용도가 어떻게되?

이미지에서 보이는 `@Value`는 **세 가지 다른 패키지**에서 제공되고 있습니다. 각각의 차이와 용도는 다음과 같습니다:


| 패키지/종류 | 용도 및 설명 |
| :-- | :-- |
| `org.springframework.beans.factory.annotation.Value` | **Spring Framework에서 제공하는 어노테이션**입니다. <br> - 프로퍼티 파일(`application.properties`/`application.yml`)이나 환경 변수, 시스템 변수, SpEL(Spring Expression Language) 표현식 값을 빈(Bean)에 주입할 때 사용합니다.<br> - 예시: `@Value("${my.prop}")` |
| `lombok.Value` | **Lombok 라이브러리에서 제공하는 어노테이션**입니다.<br> - 불변(immutable) 클래스를 만들 때 사용합니다.<br> - 변수 값을 주입하는 용도가 아니라, 클래스의 모든 필드를 `final`로 만들고, getter만 생성합니다.<br> - 예시: `@Value public class MyClass { ... }` |
| `com.google.api.client.util.Value` | **Google API 클라이언트 라이브러리에서 제공하는 어노테이션**입니다.<br> - 주로 Google API 모델 객체에서 기본값을 지정할 때 사용합니다.<br> - Spring의 프로퍼티 주입과는 관련이 없습니다. |

**Spring에서 프로퍼티 값을 주입할 때는 반드시 `org.springframework.beans.factory.annotation.Value`를 사용해야 합니다.**

> 잘못된 패키지의 `@Value`를 import하면 원하는 값이 주입되지 않고, `null`이 되거나 오류가 발생할 수 있습니다.

추가적으로 궁금한 점이나, 예시 코드가 필요하면 말씀해 주세요!

<div style="text-align: center">⁂</div>

[^1]: image.jpg

[^2]: https://www.baeldung.com/spring-value-annotation

[^3]: https://javarevisited.blogspot.com/2023/08/what-is-value-annotation-in-spring.html

[^4]: https://bcp0109.tistory.com/227

[^5]: https://docs.spring.io/spring-framework/reference/core/beans/annotation-config/value-annotations.html

[^6]: https://velog.io/@tidavid1/Spring에서-Value의-값은-언제-반영될까

[^7]: https://www.digitalocean.com/community/tutorials/spring-value-annotation

[^8]: http://tuhrig.de/why-using-springs-value-annotation-is-bad/

[^9]: https://stackoverflow.com/questions/46206494/spring-difference-between-value-and-ref

[^10]: https://j-sik.tistory.com/109

[^11]: https://dev.to/tiuwill/12-ways-to-use-the-value-annotation-in-spring-for-flexible-and-maintainable-applications-2fg2

[^12]: https://stackoverflow.com/questions/28784211

[^13]: https://stackoverflow.com/questions/50094931/what-is-advantage-of-using-value-annotation-in-spring-boot

[^14]: https://glory-day.tistory.com/86

[^15]: https://stackoverflow.com/questions/68728614/why-value-works-differently-in-spring-core-spring-boot

