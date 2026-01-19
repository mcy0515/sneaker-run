# 👟 SneakerRun (선착순 한정판 스니커즈 판매 플랫폼)

> **대용량 트래픽을 고려한 선착순 구매 서비스**입니다.  
> 동시성 이슈 제어와 배포 자동화에 중점을 두어 개발했습니다.

<br>

## 1. 프로젝트 개요
- **프로젝트명:** SneakerRun
- **개발 인원:** 1인 (Backend)
- **주요 기능:** 회원가입/로그인, 상품 조회, 선착순 주문(재고 차감), 배포 자동화
- **배포 주소:** [Swagger API 문서 바로가기](http://3.39.23.205:8080/swagger-ui/index.html) (현재 운영 중)

<br>

## 2. 사용 기술 (Tech Stack)

### Backend
<img src="https://img.shields.io/badge/Java 17-007396?style=for-the-badge&logo=java&logoColor=white"> <img src="https://img.shields.io/badge/Spring Boot 3.x-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> <img src="https://img.shields.io/badge/JPA (Hibernate)-59666C?style=for-the-badge&logo=hibernate&logoColor=white">

### Database & Cache
<img src="https://img.shields.io/badge/MySQL 8.0-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> <img src="https://img.shields.io/badge/Redis-DC382D?style=for-the-badge&logo=redis&logoColor=white">

### Infra & Tools
<img src="https://img.shields.io/badge/AWS EC2-232F3E?style=for-the-badge&logo=amazonaws&logoColor=white"> <img src="https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white"> <img src="https://img.shields.io/badge/Shell Script-4EAA25?style=for-the-badge&logo=gnu-bash&logoColor=white">

<br>

## 3. 아키텍처 (Architecture)
(여기에 나중에 그림 하나 넣을 겁니다. 일단 비워두세요.)

<br>

## 4. 핵심 문제 해결 (Trouble Shooting) ★

### ① 선착순 주문 시 재고 차감 동시성 이슈 (Concurrency)
- **문제 상황:** JMeter 테스트 결과, 재고가 100개일 때 100명이 동시에 주문하면 **Race Condition**으로 인해 재고가 89개 남는 현상 발생.
- **해결 시도:**
  1. **Java synchronized:** 서버가 여러 대일 경우 동작하지 않으므로 기각.
  2. **DB 비관적 락(Pessimistic Lock):** 데이터 정합성은 보장되나, DB 부하로 인한 성능 저하 우려.
  3. **Redis (최종 채택):** 인메모리 기반이라 속도가 빠르고, 단일 스레드 특성을 이용해 원자적(Atomic) 연산으로 해결.
- **결과:** 테스트 코드 검증 결과, **100명 동시 주문 시 재고 0개 정확히 도달** 확인.

### ② AWS EC2 프리티어 메모리 부족 및 배포 자동화
- **문제 상황:** `t2.micro` (RAM 1GB) 환경에서 Gradle 빌드 시 OOM(Out Of Memory)으로 서버가 멈춤.
- **해결:** 리눅스 **Swap Memory(가상 메모리)**를 2GB 할당하여 빌드 안정성 확보.
- **추가 개선:** 매번 수동 배포하는 번거로움을 해결하기 위해 `Shell Script`를 작성하여, 명령어 한 줄로 **Build -> Stop -> Start**가 되도록 자동화 구축.

<br>

## 5. API 명세 (Swagger)
서버 실행 후 `/swagger-ui/index.html` 접속 시 확인 가능합니다.
- **POST** `/api/users/signup` : 회원가입
- **POST** `/api/products` : 상품 등록
- **POST** `/api/orders` : 선착순 주문 (Redis 연동)
