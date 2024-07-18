package com.example.pet_pet.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class dbHelper extends SQLiteOpenHelper {
    static String DB_NAME = "PetShop";
    static int DB_VERSION = 27;

    public dbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        ///1. Bảng tài khoản
        String taiKhoan = "CREATE TABLE TAIKHOAN(" +
                "mataikhoan integer primary key autoincrement," +
                " tendangnhap text not null," +
                " matkhau text not null," +
                " hoten text not null," +
                " email text not null," +
                " sodienthoai text not null," +
                " diachi text not null," +
                " sotien integer not null," +
                "loaitaikhoan text not null," +
                " anhtaikhoan text not null)";
        sqLiteDatabase.execSQL(taiKhoan);
        sqLiteDatabase.execSQL("INSERT INTO TAIKHOAN VALUES(1,'hoc','123','Nguyễn Công Học','hoc@gmail.com','0787613866','hà nam',10000,'admin','https://i.pinimg.com/474x/4a/4e/2b/4a4e2bb5dc8078b76c2a160deeb92882.jpg')");
        sqLiteDatabase.execSQL("INSERT INTO TAIKHOAN VALUES(2,'hoc','123','Nguyễn Công Học','hoc@gmail.com','0787613866','hà nam',10000,'admin','https://t3.ftcdn.net/jpg/05/64/28/18/360_F_564281876_b90Cpe6MxpjC8ZVSR49Dl1UvKIjPDNK6.jpg')");
        sqLiteDatabase.execSQL("INSERT INTO TAIKHOAN VALUES(3,'hoc','123','Nguyễn Công Học','hoc@gmail.com','0787613866','hà nam',10000,'admin','https://i.pinimg.com/originals/26/82/bf/2682bf05bc23c0b6a1145ab9c966374b.png')");
        sqLiteDatabase.execSQL("INSERT INTO TAIKHOAN VALUES(4,'khach','123','học','h@gmail.com','0787613866','nghe an',10000,'khachhang','https://e7.pngegg.com/pngimages/811/219/png-clipart-dog-cartoon-cuteness-kitten-avatar-mammal-face-thumbnail.png')");
        sqLiteDatabase.execSQL("INSERT INTO TAIKHOAN VALUES(5,'khach','123','hoc','h@gmail.com','0787613866','nghe an',10000,'khachhang','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSR089qbmlk25UijJ0nXXJof8ibc80Jq-UuDA&usqp=CAU')");
        sqLiteDatabase.execSQL("INSERT INTO TAIKHOAN VALUES(6,'khach','123','hoc','h@gmail.com','0787613866','nghe an',10000,'khachhang','https://i.pinimg.com/originals/26/82/bf/2682bf05bc23c0b6a1145ab9c966374b.png')");


        //2. Bảng loại sản phẩm
        String loaiSanPham = "CREATE TABLE LOAISANPHAM(" +
                "maloaisanpham integer primary key autoincrement," +
                " tenloaisanpham text not null)";
        sqLiteDatabase.execSQL(loaiSanPham);
        sqLiteDatabase.execSQL("INSERT INTO LOAISANPHAM VALUES(1,'Đồ ăn cho chó')");
        sqLiteDatabase.execSQL("INSERT INTO LOAISANPHAM VALUES(2,'Đồ ăn cho mèo')");
        sqLiteDatabase.execSQL("INSERT INTO LOAISANPHAM VALUES(3,'Phụ kiện cho chó')");
        sqLiteDatabase.execSQL("INSERT INTO LOAISANPHAM VALUES(4,'Phụ kiện cho mèo')");


        ///3. Bảng sản phẩm
        String sanPham = "CREATE TABLE SANPHAM(" +
                " masanpham integer primary key autoincrement," +
                " tensanpham text not null," +
                " gia integer not null," +
                " maloaisanpham integer REFERENCES LOAISANPHAM(maloaisanpham)," +
                " mota text not null," +
                " anhsanpham text not null," +
                " soluong integer not null," +
                " soluongbanra integer not null)";
        sqLiteDatabase.execSQL(sanPham);
        sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(1,'Hạt Beneful cho chó',10,1,'Nếu như Classic Pets là loại thức ăn phù hợp với giống chó lớn thì Beneful là hãng thức ăn phù hợp với các dòng chó nhỏ như Corgi, Bull pháp, Poodle…Lượng axit amin cũng trong cơ thể chú cún sẽ được điều chỉnh cân bằng nhờ hàm lượng protein có trong sản phẩm. Dạng hạt mềm sẽ giúp chú chó của bạn dễ dàng tiêu hóa và hấp thụ các dưỡng chất từ sản phẩm.','https://bizweb.dktcdn.net/100/346/633/files/thuc-an-kho-cho-cho-meo-ra-doi-nhu-the-nao.jpg?v=1553479214146',12,0)");
        sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(2,'Thức ăn cho chó Smartheart',50,1,'Chó trưởng thành thì đòi hỏi nguồn dinh dưỡng cân bằng và đầy đủ để mang lại cho chúng một sức khỏe và tình trạng tốt. Thức ăn dành cho chó trưởng thành SmartHeart có thể đáp ứng nhu cầu dinh dưỡng cho chó trưởng thành sử dụng thành phần có chất lượng tốt nhất, bổ sung Dầu cá (giàu DHA vàAxit béo Omege – 3) và Lecithin, giàu Colin, giúp tăng cường sự phát triển chức năng não và hệ thần kinh, tăng cường sức khỏe tim mạch.','https://pethouse.com.vn/wp-content/uploads/2023/01/ezgif-5-1e317ae8fd-800x800.jpg',10,0)");
        sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(3,'Thức ăn cho chó Classic pets',20,1,'Dòng sản phẩm Classic pets đặc chế dành cho chó con mới lớn chứa nhiều protein, chất béo, vitamin, khoáng chất, DHA, omega 3 và 6, canxi. Sản phẩm có nhiều hương vị. Hạt thức ăn hơi cứng nên bạn cần ngâm nước cho mềm trước khi cho cún cưng ăn.','https://bizweb.dktcdn.net/100/091/443/products/hieuunganh-com-5e918dd032c21.png?v=1586597434450',10,0)");
        sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(4,'Thức ăn cho chó Royal canin',30,1,' Thành phần: Bắp, bột gia cầm, gạo, đậu nành, dầu gà, đậu nành giàu chất béo, men bia khô, lecithin, dầu cá, muối i-ot, vitamin và khoáng chát, chất chống oxy hóa, hương vị bò nướng và màu thực phẩm.','https://www.petmart.vn/wp-content/uploads/2021/06/thuc-an-cho-cho-poodle-con-royal-canin-poodle-puppy2.jpg',10,0)");
        sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(5,'Thức ăn cho chó FIBS',40,1,'FIBS làm từ thịt gà, gạo lứt, đậu nành, giàu chất chống oxy hóa từ vitamin E giúp cung cấp dinh dưỡng tối ưu cho chó con. Sản phẩm chứa men vi sinh probiotic hỗ trợ tiêu hóa, tránh đầy bụng hay chán ăn. Công thức chứa sữa non, thảo dược giúp tăng cường đề kháng, ngăn ngừa bệnh tật hiệu quả. Sản phẩm thiết kế hạt cực nhỏ giúp chó con dễ nhai nuốt.','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSUpL9qDcvaqEL85UX1lrU8RHRkD2AAnpcWCw&usqp=CAU',0,0)");

        sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(6,'Thức ăn cho mèo từ 3 tháng trở lên Catsrang',30,2,'Catsrang có khả năng ngăn ngừa lông vón cục. Với đặc tính dễ dàng tiêu hóa, Catsrang giúp mèo đi phân rắn và giảm mùi khó chịu. Với hàm lượng dinh dưỡng cân bằng, Catsrang rất thích hợp trong việc cải thiện da lông và phòng tránh bệnh quáng gà ở mèo. Không sử dụng chất kháng sinh, chất bảo quản, phẩm màu và hương liệu nhân tạo. Xuất xứ Hàn Quốc.','https://cdn-img-v2.webbnc.net/uploadv2/web/12/12107/product/2019/10/17/04/14/1571285687_thuc-an-meo-catsrang-400g-nhap-khau-han-quoc.jpg',10,0)");
        sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(7,'Royal Canin Fit 32 - Thức ăn cho mèo trưởng thành',20,2,'Thức ăn cho mèo ROYAL CANIN FIT32 Hỗ trợ sức khỏe của mèo bằng việc cung cấp các chất dinh dưỡng chính xác dựa trên nghiên cứu của các nhà khoa học ROYAL CANIN. ROYAL CANIN FIT32 giúp hỗ trợ hệ dưỡng chất cân bằng, giữ cân nặng lý tưởng và điều chỉnh búi lông.','https://cdn-img-v2.webbnc.net/uploadv2/web/12/12107/product/2019/10/19/02/39/1571452755_fit32-400gr.jpg',10,0)");
        sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(8,'Thức ăn cho mèo từ 3 tháng trở lên Catsrang',20,2,'Thức ăn cho mèo Whiskas vị cá biển 480g. Thức ăn cho mèo ROYAL CANIN FIT32 Hỗ trợ sức khỏe của mèo bằng việc cung cấp các chất dinh dưỡng chính xác dựa trên nghiên cứu của các nhà khoa học ROYAL CANIN. ROYAL CANIN FIT32 giúp hỗ trợ hệ dưỡng chất cân bằng, giữ cân nặng lý tưởng và điều chỉnh búi lông.','https://product.hstatic.net/200000352097/product/81mhps6datl._sx425__9709e5b761104a48b6f76ba280cb2148_large.jpg',10,0)");
        sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(9,'Thức ăn cho mèo pate dạng thịt hầm',20,2,'Thức ăn cho mèo Whiskas vị cá biển 480g. Thức ăn cho mèo ROYAL CANIN FIT32 Hỗ trợ sức khỏe của mèo bằng việc cung cấp các chất dinh dưỡng chính xác dựa trên nghiên cứu của các nhà khoa học ROYAL CANIN. ROYAL CANIN FIT32 giúp hỗ trợ hệ dưỡng chất cân bằng, giữ cân nặng lý tưởng và điều chỉnh búi lông.','https://bizweb.dktcdn.net/100/229/172/products/salmon.jpg?v=1596675020147',10,0)");
        sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(10,'THỨC ĂN CHO MÈO Me-O Delite Dạng Pate',20,2,'THỨC ĂN CHO MÈO Me-O delite Dạng Pate Vị cá ngừ với gà xé nấu đông Gói 70g Xuất xứ Thái Lan => Shop xin cam kết Hạn sử dụng của sản phẩm vẫn còn xa (Sản phẩm chất lượng, Hàng luôn mới sản xuất).','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRUu1pgO1OKtG_yNw06ju2vxlzLrB-5SoM9OA&usqp=CAU',10,0)");



        sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(11,'Vòng lục lạc cho mèo dây đeo cổ',20,4,'Mỗi chiếc vòng cổ đều có chuông nhỏ bằng đồng chống gỉ xinh xắn, khoá bằng nhựa cứng, chất liệu dây vải dù chắc chắn. Có thể điều chỉnh to nhỏ tuỳ ý, nhiều mẫu mã cho bạn lựa chọn.','https://down-vn.img.susercontent.com/file/2dc8c1198d104481092ad7068274d5d1',10,0)");
        sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(12,'Đồ Chơi Cần Câu Gỗ Cho Thú Cưng Chó Mèo',20,4,'Mỗi chiếc vòng cổ đều có chuông nhỏ bằng đồng chống gỉ xinh xắn, khoá bằng nhựa cứng, chất liệu dây vải dù chắc chắn. Có thể điều chỉnh to nhỏ tuỳ ý, nhiều mẫu mã cho bạn lựa chọn.','https://salt.tikicdn.com/cache/w1200/ts/product/1d/13/4a/472ddc8513eb86499c31f5392bf3be6c.jpg',10,0)");
        sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(13,'Chuồng cho thú cưng',20,4,'Chuồng là không gian riêng cho vật nuôi ăn, ngủ và đi vệ sinh đúng chỗ. Dụng cụ này giúp bạn cố định các \"boss cưng\" một chỗ để tránh chúng nghịch phá lúc bận rộn hay vắng nhà. Ngoài ra thì công dụng quan trọng hơn hết của chuồng chính là giữ cho thú cưng đi vệ sinh đúng chỗ, phòng tình trạng phóng uế bừa bãi trong nhà.','https://mcdn.coolmate.me/uploads/November2021/phu-kien-cho-meo-3.jpg',10,0)");
        sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(14,'Dây yếm đeo cho mèo',20,4,'Những chiếc vòng cổ với thiết kế bắt mắt giúp thú cưng nhà bạn nổi bật và xinh xắn hơn. Cả mèo lẫn chó khi đeo vòng cổ đều vô cùng đáng yêu đúng không nào? Ngoài ra, nếu nuôi chó, bạn có thể trang bị thêm một sợi dây dắt để dễ dàng điều khiển cún khi ra ngoài, tránh chạy lung tung hay va vào người khác. Vòng cổ hiện nay có rất nhiều mẫu được ưa chuộng như vòng có chuông, vòng vải cổ đệm hay vòng cổ phát sáng.','https://img.ws.mms.shopee.vn/c6da6d9bb2050c9323cfa45d56642cc6',10,0)");
        sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(15,'Bát đựng thực ăn và nước',20,4,'Mỗi chiếc vòng cổ đều có chuông nhỏ bằng đồng chống gỉ xinh xắn, khoá bằng nhựa cứng, chất liệu dây vải dù chắc chắn. Có thể điều chỉnh to nhỏ tuỳ ý, nhiều mẫu mã cho bạn lựa chọn.','https://mcdn.coolmate.me/uploads/November2021/phu-kien-cho-meo-4_9.jpg',10,0)");

        sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(16,'Set kẹp tóc dành cho chó',20,3,'Vòng đeo cổ hay bảng tên đều cần thiết trong việc đưa chó mèo đi dạo. Trong trường hợp thú cưng của bạn đi lạc thì người đi được sẽ biết được địa chỉ, số điện thoại trên cổ và đưa chúng trở lại với bạn. Thật tiện lợi phải không nào? Hiện nay cũng có nhiều loại dây hay bảng tên có chức năng trị các bọ chét, rận giúp đảm bảo sức khỏe, an toàn cho chúng.','https://cunsieupham.com/wp-content/uploads/2019/03/set-k%E1%BA%B9p-t%C3%B3c-d%C3%A0nh-cho-ch%C3%B3-m%C3%A8o-juniehouse-1.jpg',10,0)");
        sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(17,'Vòng lục lạc cho chó dây đeo cổ',20,3,'Mỗi chiếc vòng cổ đều có chuông nhỏ bằng đồng chống gỉ xinh xắn, khoá bằng nhựa cứng, chất liệu dây vải dù chắc chắn. Có thể điều chỉnh to nhỏ tuỳ ý, nhiều mẫu mã cho bạn lựa chọn.','https://bizweb.dktcdn.net/100/458/454/products/image-1666347236182.png?v=1692700603947',10,0)");
        sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(18,'Nệm cho chó hình trái bơ',20,3,'Nệm Cho Thú Cưng Trái Bơ sản phẩm đang bán chạy nhất hiện nay. Nệm Cho Thú Cưng Trái Bơ với thiết kế siêu dễ thương dành cho bé 5kg đổ lại. Sản phẩm được thiết kế riêng cho các bé Chó Mèo nhỏ. Nệm có 2 màu Xanh & Vàng cho mọi người lựa chọn.','https://www.phukienthucungdep.com/upload/product/normal/nem-cho-thu-cung-trai-bo.jpg',10,0)");
        sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(19,'Vòng đeo cổ kim loại cho chó',20,3,'Mỗi chiếc vòng cổ đều có chuông nhỏ bằng đồng chống gỉ xinh xắn, khoá bằng nhựa cứng, chất liệu dây vải dù chắc chắn. Có thể điều chỉnh to nhỏ tuỳ ý, nhiều mẫu mã cho bạn lựa chọn.','https://ampet.vn/wp-content/uploads/2022/11/Phu-kien-cho-cho-4.jpg',10,0)");
        sqLiteDatabase.execSQL("INSERT INTO SANPHAM VALUES(20,'Vòng da cho chó lơn Paw',20,3,'Vòng cổ cho chó lớn PAW được là từ chất liệu da và có đính cườm cao cấp. Vòng cổ có thiết kế nhỏ gọn, chất lượng cao, chất liệu da lộn rất mềm mại.','https://ampet.vn/wp-content/uploads/2022/11/Vong-co-cho-cho-lon-PAW-1-600x600.jpg',10,0)");



        //4. Bảng giỏ hàng
        String gioHang = "CREATE TABLE GIOHANG(" +
                "magiohang integer primary key autoincrement, " +
                "mataikhoan integer REFERENCES TAIKHOAN(mataikhoan)," +
                " masanpham integer REFERENCES SANPHAM(masanpham)," +
                " soluong integer not null)";
        sqLiteDatabase.execSQL(gioHang);


        //5. Bảng đơn hàng
        String donHang = "CREATE TABLE DONHANG(" +
                " madonhang integer primary key autoincrement," +
                " mataikhoan integer REFERENCES TAIKHOAN(mataikhoan)," +
                " ngaydathang text not null," +
                " tongtien integer not null," +
                " trangthai text not null)";
        sqLiteDatabase.execSQL(donHang);
        sqLiteDatabase.execSQL("INSERT INTO DONHANG VALUES(1,2,'16/11/2023',100,'Đã nhận hàng')");
        sqLiteDatabase.execSQL("INSERT INTO DONHANG VALUES(2,5,'16/12/2023',200,'Đã nhận hàng')");
        sqLiteDatabase.execSQL("INSERT INTO DONHANG VALUES(3,2,'17/09/2023',300,'Đã nhận hàng')");
        sqLiteDatabase.execSQL("INSERT INTO DONHANG VALUES(4,4,'18/01/2023',400,'Đã nhận hàng')");
        sqLiteDatabase.execSQL("INSERT INTO DONHANG VALUES(5,3,'19/11/2023',50,'Đã nhận hàng')");



        //6. Bảng chi tiết đơn hàng
        String chiTietDonHang = "CREATE TABLE CHITIETDONHANG(" +
                "machitietdonhang integer primary key autoincrement," +
                " madonhang integer REFERENCES DONHANG(madonhang)," +
                " masanpham integer REFERENCES SANPHAM(masanpham)," +
                "soluong integer not null," +
                " dongia integer not null," +
                " thanhtien integer not null)";
        sqLiteDatabase.execSQL(chiTietDonHang);
        sqLiteDatabase.execSQL("INSERT INTO CHITIETDONHANG VALUES(1,2,5,5,20,20)");
        sqLiteDatabase.execSQL("INSERT INTO CHITIETDONHANG VALUES(2,2,1,4,30,30)");
        sqLiteDatabase.execSQL("INSERT INTO CHITIETDONHANG VALUES(3,3,2,3,30,30)");
        sqLiteDatabase.execSQL("INSERT INTO CHITIETDONHANG VALUES(4,2,3,2,30,30)");
        sqLiteDatabase.execSQL("INSERT INTO CHITIETDONHANG VALUES(5,3,5,5,10,10)");

        //7. Bảng đánh giá
        String danhGia = "CREATE TABLE DANHGIA(" +
                "madanhgia integer primary key autoincrement," +
                " mataikhoan integer REFERENCES TAIKHOAN(mataikhoan)," +
                " masanpham integer REFERENCES SANPHAM(masanpham)," +
                " danhgia text not null," +
                " nhanxet text not null," +
                " ngaydanhgia text not null)";
        sqLiteDatabase.execSQL(danhGia);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if (i != i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS TAIKHOAN");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS SANPHAM");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS LOAISANPHAM");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS GIOHANG");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS DONHANG");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS CHITIETDONHANG");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS DANHGIA");
            onCreate(sqLiteDatabase);

        }
    }

}
