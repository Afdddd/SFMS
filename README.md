# Smart Factory Monitoring System (SFMS)
<br>

## 소개

SFMS 프로젝트는 PLC 개발자로 일하면서 느꼈던 불편함을 개발 공부하면서 개선할 수 있을까? 라는 생각에 시작되었습니다. 공장의 생산 라인을 구축한 후에, 비전문가인 생산 라인 담당자와의 전화 AS에 어려움이 있었습니다. 이 문제를 해결하기 위해, PLC에서 데이터를 클라우드로 전송해 문제를 모니터링할 수 있는 시스템을 구상하게 되었습니다.

생산 라인에서 문제가 발생하면 PLC에서 클라우드로 데이터를 전송하여 문제를 모니터링할 수 있도록 SFMS를 개발하게 되었습니다. 이 시스템은 PLC에서 MQTT 프로토콜을 통해 메시지를 보내고, RabbitMQ 서버에서 MQTT 메시지를 수신하여 데이터를 수집하고 모니터링을 구축하는 것을 목표로 합니다.

<br><br>

## 아키텍처(예상)
![image](https://github.com/user-attachments/assets/5c4ef03a-6241-43e8-938a-f9626b9f1f85)

<br><br>

## 사용 기술(예상)

- **언어 및 프레임워크**: `Java`, `Spring`
- **클라우드 및 가상화**: `AWS EC2`
- **메시지 브로커 및 데이터 수집**: `RabbitMQ`
- **모니터링 도구**: `Prometheus`, `Grafana`
- 배포 : `Docker`, `github actions`
