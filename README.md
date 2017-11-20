Android客户端，用户可以进行密码修改，密码强度校验。具体实现功能如下：

1.旧密码、新密码和密码确认不能为空。

2.新密码与密码确认要相等。

3.根据密码，利用进度条动态显示密码强弱，密码强度标准如下：
  
  
  低：低于八位密码，或密码中仅包含字母或数字中的一种。
  
  高：八位以上，且包含特殊字符。
  
  中：其他情况。

4.使用ConstraintLayout布局进行界面设计。
  具体效果如下图所示：
  ![image](https://github.com/cuibin1991/Passwordstrength/blob/master/samples/001.jpg)
  
   密码强度低
  
  
  ![image](https://github.com/cuibin1991/Passwordstrength/blob/master/samples/002.jpg)
  
   密码强度中
  
  
  ![image](https://github.com/cuibin1991/Passwordstrength/blob/master/samples/003.jpg)
  
   密码强度高
  
  
