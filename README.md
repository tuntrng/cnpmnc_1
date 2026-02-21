| Thành viên | MSSV |
|------------|------|
| Trương Anh Tuấn | 2213810 |

URL: https://student-management-api-8n99.onrender.com/students

Hướng dẫn:
1. Clone repo về.
2. Gõ lệnh "./mvnw dependency:resolve" để tải các dependency cần thiêt.
3. Gõ lệnh "./mvnw spring-boot:run" để chạy.
   
Nếu có Docker có thể build trực tiếp:
1. Gõ lệnh "docker build ." để build image.
2. Gõ lệnh docker "run -p 8080:8080 student-management" để chạy (có thể thêm các environment variable như database url, username và password).

Trả lời lý thuyết:
- Lab1:
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

Screénhot các module:
nah
