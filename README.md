### - SpringBoot, SpringMVC
### - Database : H2
### - Template Engine : Thymeleaf
<br/>

### 기능
- 상품 목록 조회
- 상품 상세 조회
- 상품 등록
- 상품 수정
<br/>

### 요구사항 분석 - 서비스 제공 흐름
#### 상품 목록 -> 상품 등록
1. 클라이언트가 상품 목록(controller)에 들어가면 상품 목록(view) 렌더링 
2. 상품 목록(view)에서 상품 등록 폼(controller)로 이동 가능 
3. 상품 등록 폼(controller)에서 상품 등록 폼(view)를 보여 준다. 
4. 상품 등록 폼(view)에서 값을 입력 후 등록 버튼 클릭 
5. 상품 저장(controller)으로 이동
6. 상품 저장(controller)에서 상품 상세(view)로 이동 - 내부 호출

<br/>

#### 상품 목록 -> 상품 상세 -> 상품 수정
1. 상품 목록에서 상품 상세(controller)로 이동하면 상품 상세(view)로 이동
2. 상품 상세(view)에서 상품 수정 폼(controller)로 이동하면 상품 수정 폼(view)로 이동
3. 상품 수정 폼(view)에서 상품 수정 버튼을 누르면 상품 수정 폼(view)으로 이동
4. 상품 수정 폼(view)에서 저장 버튼을 누르면 상품 수정(controller)로 이동
5. 이후 상품 상세(controller)로 Redirect
