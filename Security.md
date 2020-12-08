### Spring Web Security 개념

스프링 시큐리티의 기본 동작 방식은 서블릿의 여러 종류의 필터와 인터셉터를 이용해서 처리
> 필터
>> 서블릿에서 말하는 단순한 필터를 의미

> 인터셉터(Interceptor)
>> 스프링에서 필터와 유사한 역할을 수행

>필터와 인터셉터의 공통점과 차이점
>> 공통점: 특정한 서블릿이나 컨트롤러의 접근에 관여한다는 점에서 유사

>> 차이점: 필터는 스프링과 무관하게 서블릿 자원, 인터셉터는 스프링의 빈으로 관리되면서 스프링의 컨텍스트 내의 속함

``스프링 시큐리티를 이용하게 되면 인터셉터와 필터를 이용하면서 별도의 컨텍스트를 생성해서 처리됨
스프링 시큐리티는 현재 동작하는 스프링 컨텍스트 내에서 동작하기 때문에 이미 컨텍스트에 포함된 여러 빈들을
같이 이용해서 다양한 방식의 인증 처리가 가능하도록 설계할 수 있습니다.``

-------------

### Spring Web Security 설정

pom.xml에 스프링 시큐리티 관련된 태그 라이브러리를 활용할 수 있도록 추가해줍니다.<br>
![securityPom](https://user-images.githubusercontent.com/73210774/101458145-d8705a80-3979-11eb-9b64-520561591c6a.png)<br>


스프링 시큐리티는 단독으로 설정할 수 있기 때문에 설정파일은 별도로 작성하는 것이 좋음<br>
`security-context.xml 생성(ibatis(mybatis(?)) 사용시)<br>`
![securityIbatis](https://user-images.githubusercontent.com/73210774/101459248-5c771200-397b-11eb-95a0-1608df55fb55.png)<br>
`security-config.java 생성(jpa 사용시)<br>`
![securityJpa](https://user-images.githubusercontent.com/73210774/101459735-f63ebf00-397b-11eb-86dd-af4ce12fb297.png)

