Todo:

0. √ CardView 加： 公众号 、 作者、分类、时间。
1. √ 主页面的CoordinatorLayout -> hide toolbar
2. √ NavigationDrawer
3. √ 首页（文章） | 公众号
4. √ 公众号 (TabHost + ViewPager)
5. √ 文章详情
6. √ MPV
7. √ 组件化
8. √ 贡献者
9. 本地缓存
10. 启动页
11. Banner
12. 多渠道打包
13. 自定义View - 图标（新）等
14. RecyclerView显示动画
15. 页面切换过渡动画
16. Behavior
17. 引入RxJava

Issues:
1. 屏幕切换
2. RecylerAdapter.ViewHolder setOnClickListener 的最佳实践？
3. Glide
4. Retrofit
5. OKHttp
6. ARouter

Notes:
1. Fragment#setArguments(Bundle bundle) 跟 直接在Fragment中加上一个setter/getter有什么区别？
A: 区别在于，setArguments设定的参数，在Fragment经历destroy和re-create的过程中会被保存下来，这些参数不需要程序在 onSaveInstanceState 方法中做特殊的处理，就可以在下一次 onCreate 的时候继续获取。对比来说, 如果用setter/getter来存取的话，需要自己考虑 onSaveInstanceState 的实现。

### 注意事项
1. 不要在子组件styles.xml中添加theme, app组件需要用base里面的theme
