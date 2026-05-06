Phần 1 - Phân tích & Thiết kế:

Công cụ: Dùng Cookie. Vì Session sẽ bị xóa khi tắt trình duyệt, còn Cookie có thể cài đặt thời gian sống (Max-Age) lâu dài trên máy khách.

Chống XSS: Bật cờ HttpOnly. Khi cờ này được bật, JavaScript (document.cookie) không thể đọc được Cookie này, giúp ngăn chặn Hacker lấy cắp thông tin.
