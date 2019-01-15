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
> 由于 onCreateViewHolder 和 onBindViewHolder 都会被多次调用，直接在这两个方法中 new 一个onClickListener 的实例都会造成对象的反复创建和销毁<br />
所以相对比较好的做法就是在 Adapter 中定义一个接口回调方法，并在 Adapter 中实例化一个这个接口类型的成员变量 <br />
在 onCreateViewHolder 方法中调用 ViewHolder 的构造方法，传递这个 listener 的引用给 ViewHolder <br />
在 ViewHolder 的构造方法中为 view 设定 setOnClickListener， 在 onClick 中调用这个接口的回调，把 view 和  `getLayoutPosition()` 传递给回调方法 <br />
这样在回调方法中就可以根据传入的view和pos，得到点击的view和位置，做出相关处理。<br />
示例代码： [ArticlesListAdapter.java](https://github.com/chinalwb/wan_android/blob/a1923244afb3d7a2097f516e2db2704d2bcd1b48/WanAndroid/app/src/main/java/com/chinalwb/wanandroid/main/ui/ArticlesListAdapter.java)

3. Glide
4. Retrofit
5. OKHttp
6. ARouter

Notes:
1. Fragment#setArguments(Bundle bundle) 跟 直接在Fragment中加上一个setter/getter有什么区别？
A: 区别在于，setArguments设定的参数，在Fragment经历destroy和re-create的过程中会被保存下来，这些参数不需要程序在 onSaveInstanceState 方法中做特殊的处理，就可以在下一次 onCreate 的时候继续获取。对比来说, 如果用setter/getter来存取的话，需要自己考虑 onSaveInstanceState 的实现。

### 注意事项
1. 不要在子组件styles.xml中添加theme, app组件需要用base里面的theme
