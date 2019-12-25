# DoAn
+Tạo database và import data
Bước 1: Mở MySQL Workbench 8.0 CE
Bước 2: Chọn Sever -> Data import
Bước 3: Chọn Import fromt  Self-Contained Project, chọn đường dẫn đến file script (vd: D:\CongnghewebvaUngdung\Source\quanlynhansu.sql)
Bước 4:Chọn tab Import Process -> Start Import
Bước 5: Resfresh ở Navigator Schemas, một database mới sẽ được tạo ra và có phần dữ liệu được backup.

+Mở source code và chạy
Bước 1: Mở elclips -> chọn workspace có chưa source code -> Chọn Launch
Bước 2: Cài đặt maven và STS4(Spring tool Suite 4) cho eclips
Bước 3: Chọn File -> Open Project From File System... -> Directory...-> Dẫn đến thư mục có chưa source code.
Bước 4: Sau khi Project Explorer hiện lên và tất cả các thư viện được tải về bởi maven, 
Bước 5: Tìm đến file "application.properties" để thay đổi một thông số (tên database, username, password) đúng với Mysql bạn đang sử dụng.
Bước 6:Chuột phải vào project -> chọn Run As -> Spring Boot App
Bước 7: Chờ cho ứng dụng build và console xuất hiện thông báo "Started QuanLyNhanSuApplication in....." là thành công. Nếu không thì làm lại các bước trên.

+Truy cập website:
Bước 1: Kết nối máy tính hoặc laptop với intenet
Bước 2: Mở trình duyệt web (Cốc cốc, Google Chrome, Microsof Edge,...) 
Bước 3: Nhập trên thanh URL : localhost:8080 và nhấn enter, web sẽ tự động chuyển về trang login và cho phép chung ta đăng nhập
Bước 4: Nhập username và password hợp lệ để có thể truy cập trang web. Ở đây cấp 1 account với vai trò là quản trị viên 
có thể sử dụng tất cả các chức năng của trang web
Username: mr.hnqtin@gmail.com	
Password: 123456

