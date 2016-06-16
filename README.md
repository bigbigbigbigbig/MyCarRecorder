# MyCarRecorder
基于Android的行车记录仪软件
##功能描述
循环录制、视频播放、拍照查看、照明、百度地图定位、日期查看、配置信息查看、设置和教程。
##开发工具及平台
eclipse，android4.0以上。
##文件介绍
1. MainActivity.java：主界面加载、按钮监听
2. First.java：欢迎界面加载
3. VideoRecord.java：视频录制
4. VideoList.java：视频列表显示
5. PlayVideo.java：播放视频
6. MyCamera.java：拍照功能
7. MyLight.java：照明功能
8. LocationDemo.java：地图显示和定位（调用百度地图和定位sdk）
9. ShowPic.java：显示图片
10. MyCalendar.java：日期显示
11. DemoApplication.java：初始化百度SDK
12. MySensor.java：获取手机配置信息
13. MySet.java：设置功能
14. MyTeach.java：教程功能
15. PreviewActivity.java：网格布局加载图片
16. SoftWareInfo.java：关于（软件介绍）

##存在的问题
1. 循环录制文件数量控制
2. 摄像头聚焦问题
3. 百度地图定位精确度问题
