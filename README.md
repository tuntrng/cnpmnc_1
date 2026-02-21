| Thành viên | MSSV |
|------------|------|
| Trương Anh Tuấn | 2213810 |

URL: https://student-management-api-8n99.onrender.com/students

Lab structure:

```
student-management
 ├── .env
 ├── pom.xml
 ├── Dockerfile
 ├── student.db (unused)
 ├── README.md
 ├── src
 │   ├── main
 │   │   ├── java
 │   │   │   └── vn.edu.hcmut.cse.adse.lab
 │   │   │        ├── controller
 │   │   │        ├── service
 │   │   │        ├── repository
 │   │   │        ├── entity
 │   │   │        └── StudentManagementApplication.java
 │   │   └── resources
 |   |       └── application.properties
 |	   test
 └── target
 ```
 
Hướng dẫn:
1. Clone repo về.
2. Gõ lệnh ```./mvnw dependency:resolve``` để tải các dependency cần thiêt.
5. Gõ lệnh ```./mvnw spring-boot:run``` để chạy.
   
Nếu có Docker có thể build trực tiếp:
1. Gõ lệnh ```docker build .``` để build image.
2. Gõ lệnh ```docker run -p 8080:8080 student-management``` để chạy (có thể thêm các environment variable như database url, username và password).

Trả lời lý thuyết:

Lab1:

2. Ràng buộc Khóa Chính (Primary Key):
- Cố tình Insert một sinh viên có id trùng với một người đã có sẵn.
- Quan sát thông báo lỗi: UNIQUE constraint failed. Tại sao Database lại chặn thao tác này?

&rarr; Vì ID là khóa chính, mà khóa chính thì không được trùng nhau, hay còn nói là phải độc nhất (unique). Nên nếu chèn entity có trùng ID thì Database sẽ báo lỗi ngay lập tức do việc đó đã vi phạm tính độc nhất của khóa chính.

3. Toàn vẹn dữ liệu (Constraints):
 - Thử Insert một sinh viên nhưng bỏ trống cột name (để NULL).
 - Database có báo lỗi không? Từ đó suy nghĩ xem sự thiếu chặt chẽ này ảnh hưởng gì khi code Java đọc dữ liệu lên?

&rarr; Database không hiện lỗi bởi vì không có ràng buộc rằng trường name cần phải tồn tại giá trị. Nhưng việc không thiết đặt ràng buộc sẽ khiến lỗi nhập lệnh dễ xảy ra cũng như sai xót dữ liệu. Đồng thời nếu ứng dụng cố đọc những giữ liệu khuyết như này thì khi cần phải sử dụng một số phương thức cụ thể (ví dụ như length() hoặc isEmpty() với trường String name). Tuy nhiên, đối với bài lab này thì việc nhập thiếu trường name không ảnh hưởng đáng kể đến vận hành của hệ thống (cùng lắm là ảnh hưởng đến việc tham chiếu đến students/{id} như trong bài làm này).

4. Cấu hình Hibernate:
 - Tại sao mỗi lần tắt ứng dụng và chạy lại, dữ liệu cũ trong Database lại bị mất hết?

&rarr; Vì trong file application.properties có thiết đặt một biến cấu hình mang giá trị:

	spring.jpa.hibernate.ddl-auto=create

Tức là mỗi khi hệ thống reset, hệ thống sẽ lập tức làm mới Database, cũng tức là xóa hết và tạo mới một Database rỗng, khiếnn cho toàn bộ dữ liệu bị mất hết.

Screenshot các module:

	repository/StudentRepository.java
	
<img width="869" height="199" alt="image" src="https://github.com/user-attachments/assets/9eddd121-791c-49d6-a9d7-5c80c3e367d0" />

	entity/Student.java

<img width="995" height="362" alt="image" src="https://github.com/user-attachments/assets/12220d11-3ecd-4e08-a7f3-6aa1ed8b09c0" />

	service/StudentService.java

<img width="643" height="722" alt="image" src="https://github.com/user-attachments/assets/6fdd4613-05f3-44c2-8a43-01c57e04c54c" />

	controller/StudentWebController.java

<img width="1082" height="1719" alt="image" src="https://github.com/user-attachments/assets/e9655b82-f6b1-4a73-a063-c5a088fd92f8" />




