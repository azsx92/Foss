1. 백그라운드 vs 포그라운드 차이점
2. 페이로드란?

## 1. 백그라운드 vs 포그라운드: 뜻과 차이점

**포그라운드(Foreground)**

- 사용자가 현재 보고 있거나 직접 상호작용하고 있는 앱이나 서비스 상태를 의미한다.
- 예를 들어, 음악 앱이 화면에 떠 있고 사용자가 조작 중이거나, 피트니스 앱이 실행 중일 때가 포그라운드 상태다[^2][^3][^7][^11].
- 포그라운드 서비스는 사용자가 인지할 수 있도록 항상 알림(Notification)을 표시해야 하며, 시스템 자원이 부족해도 쉽게 종료되지 않는다[^1][^2][^11].
- 사용자가 앱을 직접 보고 있거나, 앱이 중요한 작업(예: 음악 재생, 위치 추적 등)을 수행할 때 사용된다.

**백그라운드(Background)**

- 사용자가 앱을 직접 보고 있지 않거나, 앱이 최소화되어 있는 상태를 의미한다.
- 예를 들어, 앱을 홈 화면으로 내리거나 다른 앱을 실행했을 때 원래 앱은 백그라운드 상태가 된다[^3][^7].
- 백그라운드 서비스는 사용자가 직접 인지하지 못하는 작업(예: 데이터 동기화, 다운로드 등)을 수행한다[^1][^11].
- 시스템 자원이 부족하면 백그라운드 서비스는 쉽게 종료될 수 있다. 또한, 최신 모바일 OS에서는 배터리 및 자원 절약을 위해 백그라운드 작업에 제한을 두는 경우가 많다[^1][^3][^11].

**차이점 요약**


| 구분 | 포그라운드(Foreground) | 백그라운드(Background) |
| :-- | :-- | :-- |
| 사용자 인지 | 예 (알림 필수) | 아니오 |
| 종료 우선순위 | 낮음 (잘 종료되지 않음) | 높음 (자주 종료될 수 있음) |
| 사용 예시 | 음악 재생, 위치 추적, 통화 등 | 데이터 동기화, 알림 수신 등 |
| 시스템 정책 | 알림 반드시 표시, 권한 필요 | 제한적 실행, 자원 부족 시 종료 |


---

## 2. 페이로드란? 메시지의 페이로드의 의미

**페이로드(Payload)란?**

- 컴퓨팅, 네트워크, 프로그래밍에서 *페이로드*는 전송되는 데이터 중 실제로 전달하고자 하는 "실질적인 내용"을 의미한다[^4][^5][^10][^12][^13][^14].
- 헤더(header), 메타데이터, 제어 정보 등은 페이로드가 아니며, 오로지 "목적지에서 소비될 데이터"만을 가리킨다.

> 예시: 택배 상자에서 송장, 완충재 등은 부가 정보이고, 실제 물건이 페이로드다[^12].

**메시지의 페이로드**

- 메시지 프로토콜(예: HTTP, FCM, 네트워크 패킷 등)에서 페이로드는 메시지 본문(body)이나 실제 데이터 부분을 의미한다[^5][^8][^10][^12][^13][^14].
- 예를 들어, FCM(푸시 메시지)에서 페이로드는 알림 내용, 데이터 필드 등 실제로 앱에 전달되어 처리되는 정보다.
- 헤더는 라우팅, 인증, 포맷 정보 등 메시지 전달을 위한 부가 정보이고, 페이로드는 사용자가 궁극적으로 받길 원하는 데이터다.

**정리**

- 페이로드는 "실질적인 데이터" 또는 "메시지의 핵심 내용"을 뜻한다.
- 메시지의 페이로드는 네트워크 전송, API 요청/응답, 푸시 알림 등에서 실제로 전달·소비되는 데이터 부분이다[^5][^6][^8][^10][^12][^13][^14].

<div style="text-align: center">⁂</div>

[^1]: https://stackoverflow.com/questions/3538728/what-is-the-difference-between-a-background-and-foreground-service

[^2]: https://developer.android.com/develop/background-work/services/fgs

[^3]: https://situm.com/docs/foreground-background-execution/

[^4]: https://ssue95.tistory.com/30

[^5]: https://www.techtarget.com/searchsecurity/definition/payload

[^6]: https://developer.apple.com/library/archive/documentation/NetworkingInternet/Conceptual/RemoteNotificationsPG/CreatingtheNotificationPayload.html

[^7]: https://velog.io/@kjo1130/Foreground-vs-Background

[^8]: https://blog.hubspot.com/website/what-is-payload

[^9]: https://velog.io/@jmseb3/Android-ForegroundBackground-구분하기

[^10]: https://en.wikipedia.org/wiki/Payload_(computing)

[^11]: https://developer.android.com/develop/background-work/services

[^12]: https://hanamon.kr/네트워크-http-페이로드-payload란/

[^13]: https://smartproxy.com/glossary/payload

[^14]: https://www.baeldung.com/cs/messages-payload-header-overhead

[^15]: https://developer.apple.com/forums/thread/701945

[^16]: https://developer.android.com/develop/background-work/services/fgs/launch

[^17]: https://source.android.com/docs/automotive/users_accounts/user_system

[^18]: https://hanburn.tistory.com/168

[^19]: https://stackoverflow.com/questions/26879951/how-to-know-if-my-application-is-in-foreground-or-background-android

[^20]: https://developer.apple.com/documentation/uikit/about-the-background-execution-sequence

[^21]: https://firebase.google.com/docs/perf-mon/app-start-foreground-background-traces

[^22]: https://www.youtube.com/watch?v=YZL-_XJSClc

[^23]: https://www.ibm.com/docs/en/was/8.5.5?topic=amfhc-working-message-payload

[^24]: https://experienceleague.adobe.com/en/docs/campaign-standard/using/administrating/configuring-mobile/push-payload

[^25]: https://developer.mozilla.org/en-US/docs/Glossary/Payload_body

[^26]: https://kr.mathworks.com/help/simulink/ug/send-and-receive-messages-using-system-object-block-example.html

[^27]: https://docs.customer.io/journeys/push-custom-payloads/

[^28]: https://ko.wikipedia.org/wiki/페이로드_(%EC%BB%B4%ED%93%A8%ED%8C%85)

[^29]: https://docs.informatica.com/integration-cloud/application-integration/current-version/amqp-connector-guide/amqp-connections/message-payload.html

[^30]: https://gyuios.tistory.com/151

[^31]: https://learn.microsoft.com/en-us/azure/service-bus-messaging/service-bus-messages-payloads

[^32]: https://docs.axoniq.io/axon-framework-reference/4.11/messaging-concepts/anatomy-message/

[^33]: https://firebase.google.com/docs/cloud-messaging/concept-options

[^34]: https://nodered.org/docs/user-guide/messages

[^35]: https://www.reddit.com/r/oneplus/comments/187erac/whats_the_difference_between_foreground_and/

[^36]: https://nezhitsya.tistory.com/140


---
3. super란?
## package com.example.foss.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{
private final CustomErrorCode customErrorCode;

    public CustomException(CustomErrorCode customErrorCode) {
        super(customErrorCode.getMessage());
        this.customErrorCode = customErrorCode;
    }
    }
super가 무슨 용도이고 뭔지 모르겠어 부모의 메시지를 가져오는걸 말하는건가?

`super`는 자바에서 **부모 클래스의 생성자나 메서드를 호출할 때 사용하는 키워드**입니다.
당신이 작성한 코드에서

```java
super(customErrorCode.getMessage());
```

는 **부모 클래스인 `RuntimeException`의 생성자**를 호출하면서, 예외 메시지로 `customErrorCode.getMessage()`의 결과를 전달하는 역할을 합니다[^1][^5][^6].

### 자세한 설명

- `CustomException`은 `RuntimeException`을 상속받으므로, 생성자에서 `super(...)`를 호출하면 `RuntimeException`의 생성자가 실행됩니다.
- `RuntimeException`의 생성자에 메시지를 전달하면, 이 메시지는 예외 객체 내부에 저장되고, 나중에 `getMessage()`로 꺼낼 수 있습니다[^1][^5][^6].
- 즉, **부모의 메시지를 가져오는 게 아니라, 부모(=상위 클래스)에게 메시지를 전달해서 저장하도록 하는 것**입니다.
- 이렇게 하면 예외 발생 시 `e.getMessage()`로 메시지를 쉽게 확인할 수 있습니다.


#### 예시

```java
public class MyException extends RuntimeException {
    public MyException(String message) {
        super(message); // 부모인 RuntimeException의 생성자에 메시지 전달
    }
}
```

이렇게 하면 예외를 던졌을 때 `getMessage()`로 메시지를 바로 확인할 수 있습니다.

> 요약:
> **`super(...)`는 부모 클래스의 생성자를 호출하여, 예외 메시지나 상태 정보를 부모에 전달하는 역할**을 합니다. 부모의 메시지를 가져오는 게 아니라, 부모가 메시지를 저장하게 해주는 것입니다[^1][^5][^6].

<div style="text-align: center">⁂</div>

[^1]: https://programming-tree.tistory.com/120

[^2]: https://veneas.tistory.com/entry/Java-커스텀-예외-만들기Custom-Exception

[^3]: https://velog.io/@wijoonwu/사용자-Exception

[^4]: https://kephilab.tistory.com/84

[^5]: https://curiousjinan.tistory.com/entry/spring-runtime-exception-message

[^6]: https://jinyoungchoi95.tistory.com/38

[^7]: https://jhcode33.tistory.com/entry/Java-Exception

[^8]: https://kadosholy.tistory.com/93

[^9]: https://jinieeee.tistory.com/entry/RuntimeException을-상속받는-사용자-정의-예외-처리

[^10]: https://velog.io/@mini_mouse_/2022.07.28%EC%9E%90%EB%B0%94-%EC%A0%95%EB%A6%AC%EC%98%88%EC%99%B8%EB%A5%BC-%EB%8B%A4%EB%A3%A8%EB%8A%94-%EB%B0%A9%EB%B2%95

---
<img src="https://r2cdn.perplexity.ai/pplx-full-logo-primary-dark%402x.png" class="logo" width="120"/>

# @Component

@Configuration
두개의 차이점이 뭐야 둘다 bean 을 주입해주는 어노테이션 아닌야?

두 어노테이션 모두 Spring에서 **Bean을 등록**하는 데 사용되지만, **역할과 동작 방식에 중요한 차이**가 있습니다.

---

## 1. 공통점

- 둘 다 스프링 컨테이너에 Bean을 등록하는 역할을 합니다.
- `@Configuration`은 내부적으로 `@Component`를 포함하고 있습니다. 즉, `@Configuration`으로 선언된 클래스도 컴포넌트 스캔에 의해 Bean으로 등록됩니다[^1][^2][^6].

---

## 2. 차이점

| 구분 | @Component | @Configuration |
| :-- | :-- | :-- |
| 목적 | 일반적인 Bean 등록(서비스, 레포지토리 등) | 자바 기반의 설정 클래스(Bean 팩토리 역할) |
| 내부 구조 | 단순히 Bean 등록만 함 | @Component 포함 + CGLIB 프록시로 Bean 관리 |
| @Bean 메서드 | @Bean 메서드 사용 가능하나, 각 호출마다 새 객체 생성 | @Bean 메서드가 싱글톤으로 관리됨(프록시로 보장) |
| 주요 용도 | 주로 서비스, 컴포넌트, 레포지토리 등 비즈니스 객체 | 외부 라이브러리, 설정, 복잡한 Bean 생성 등 |
| Bean 간 의존성 | @Bean 메서드 간 의존성 보장 안 됨 | @Bean 메서드 간 의존성 자동 보장(싱글톤) |

### 핵심 차이: **@Bean 메서드의 동작**

- `@Configuration` 클래스의 `@Bean` 메서드는 CGLIB 프록시로 감싸져, 여러 번 호출해도 항상 같은 Bean(싱글톤)을 반환합니다.
- `@Component` 클래스의 `@Bean` 메서드는 프록시가 적용되지 않으므로, 메서드를 여러 번 호출하면 매번 새로운 객체가 생성될 수 있습니다[^1][^2][^5].

---

## 3. 언제 각각을 써야 할까?

- **일반적인 서비스, 컴포넌트, 레포지토리 등**은 `@Component`(혹은 그 하위 어노테이션: `@Service`, `@Repository`, `@Controller`)를 사용합니다.
- **설정 클래스(여러 Bean을 생성/조합, 외부 라이브러리 초기화 등)**는 반드시 `@Configuration`을 사용해야 합니다.

---

## 4. 결론

- `@Configuration`은 `@Component`의 특성을 포함하면서, Bean 간의 의존성과 싱글톤 보장을 위해 프록시를 사용해 더 강력한 설정 기능을 제공합니다.
- 단순 Bean 등록은 `@Component`, 여러 Bean을 생성하거나 Bean 간 의존성이 중요한 설정 클래스는 `@Configuration`을 사용하세요.

---

> **정리:**
> `@Configuration`은 `@Component`의 특성을 포함하지만, Bean 간 관계와 싱글톤 보장을 위해 반드시 설정 클래스에는 `@Configuration`을 사용해야 합니다.
> `@Component`는 단순 Bean 등록용입니다[^1][^2][^4][^5][^6].

<div style="text-align: center">⁂</div>

[^1]: https://stackoverflow.com/questions/39174669/what-is-the-difference-between-configuration-and-component-in-spring

[^2]: https://unluckyjung.github.io/spring/2021/06/11/Spring-Configuration-VS-Component/

[^3]: https://9327144.tistory.com/entry/Spring-Configuration과-Component-그리고-Bean

[^4]: https://yangtaeyoung.github.io/blog/spring/spring-configuration-vs-component/

[^5]: https://velog.io/@hajju/Configuration-Component의-차이점

[^6]: https://www.reddit.com/r/SpringBoot/comments/w6swvh/is_my_understanding_of_the_difference_between/

[^7]: https://velog.io/@hongjunland/Configuration-vs-Component

[^8]: https://silver-programmer.tistory.com/entry/Configuration-Bean-vs-Component

[^9]: https://devoong2.tistory.com/entry/Spring-Component-vs-Configuration

[^10]: https://velog.io/@hyeokjinon/Configuration-vs-Component

[^11]: https://jhkimmm.tistory.com/18

[^12]: https://any-ting.tistory.com/150

[^13]: https://kth990303.tistory.com/395

[^14]: https://blog.naver.com/sthwin/222131873998

[^15]: https://www.youtube.com/watch?v=y9g4CnS-1jY

