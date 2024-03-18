# REST API ���������� "�����"

���������� ������������ ���� ������������ �������������������� �����.
������������ ����� ����� ���� �������������� ��� ���������.

����������� ���������:
- ����������� � ����������
- �������� ������� �� ��������� ���������
- ��������� ���������� � ���, ������� �������� �� ���������� ������� �����
- �������� ���� ��������� �� ��������� ���������, ������������� ��� ����
- �������� ������, ������������ ���������������.

����������� ��������������:
- ����������� � ����������
- ����������� ����� ���������� � ���������� ����� ������������������
- ��������� ���� ��������� ��������� �������� ��� ������� ���������
- ���������� ����� ����� ���������
- ����������� ������ ����������
- �������� ������� ����������
- �������� �������� ������� �� ������������� ������� (�� ���������� ����, ������ ��� �����) 
�� ����������� ���������, � ����� �� ����� � �����
- ��������� �� ����� ��������� ������ �� ��������� ������� �� ������� ����
- ��� ����������� ���������.

### �������������� ����������
- Java 17;
- Spring Boot 3;
- Spring Security;
- JWT;
- Spring Data;
- Hibernate;
- PostgreSQL;
- Kafka;
- Docker;
- Swagger / OpenAPI
- JSON

### ������ ����������
����� �������� Spring Boot ���������� ���������� ����������� ������ Postgres, Zookeeper 
� Kafka, �������� � �������� ���������� ���������� ������� ``docker-compose up``.

# REST API

������ �������� �������� �� ������ http://localhost:8082/swagger-ui/index.html#

���� �������� ������� �������� API-����������

## ���������� ������� � ���������

�������� �������� � �������� � ���������:

### ���������� ������
 `POST /api/v1/gather-report/add`
 
 ������ �������: ``{
 "productId": 1,
 "userId": 2,
 "quantity": 87
 }``

### ��������� ��������� ������ ���� ��������� �� ���� �����
 `GET
 /api/v1/gather-report/sum`
 
### ��������� ��������� ������ ���� ��������� ����� ����������
`GET
/api/v1/gather-report/sum/user/{userId}`

### ���������� ������� � ����� �� ����� ��� ������ ���� ���������
`GET
/api/v1/gather-report/product/{productId}`

### ���������� ������� ��������� ��� ������ ���� ���������
`GET
/api/v1/gather-report/product/{productId}/user/{userId}`



## ���������� ������

�������� �������� � ��������, ������������ ����������:

### ���������� ������ ���������
`POST
/api/v1/rating/new`

### ��������� ������ ������ ���������
`GET
/api/v1/rating/user/{userId}`

## ���������� ��������������

�������� �������� � ��������������� �������������:

### ������� ����� �����������
`POST
/api/v1/auth`

## ���������� �������������

�������� �������� � �������� �������� �������������

### ����������� ������ ������������
`POST
/api/v1/user/new`

### ���������� ���������
`PATCH
/api/v1/user/dismiss/{userId}`

## ���������� ���� ���������

�������� �������� � ������ ���������:

### �������� ������� ����� ���������
`PUT
/api/v1/daily-norm/new`

### �������� ����� ���������
`GET
/api/v1/daily-norm/user/{userId}/product/{productId}`

## ���������� ���������

�������� �������� � ������ ���������

### ���������� ������ ���� ���������
`POST
/api/v1/product/new`

## Email-controller
### �������� ��������� ������ �� ��������� ������� �� ������� ���� �� email
`GET
/api/v1/email` 

# COMING SOON
� ��������� ����� ����������� ���������� �������� ��������� ������ ��������� 
� �������� �����, � ������ ������ ����� ����� ����� �������� � ������� �������: `GET
/api/v1/email`   

