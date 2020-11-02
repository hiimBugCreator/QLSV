Alter procedure loginWithAccount
(@username varchar(100) , @password varchar(50) )

as 
	begin
			select * from taikhoan where (tentaikhoan = @username or email=@username) and matkhau = @password
	end

alter procedure search
(@keyword varchar(250),@type int)
as
	begin
			IF (@type = 2) --hoc sinh
				BEGIN
				   select * from hocsinh where ([hovaten]=@keyword or [mshs]=@keyword)
				END
			ELSE
				IF (@type = 3) --giao vien
				BEGIN
				   select * from giaovien where ([hovaten]=@keyword or [msgv]=@keyword)
				END
	end