package com.example.itime;
//开发日记：11/14实现滑动菜单

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;


import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.itime.navigation.MenuItem;
import com.example.itime.navigation.MenuItemAdapter;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.rtugeek.android.colorseekbar.ColorSeekBar;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity {

    //主布局
    private DrawerLayout drawerLayout;

    //主布局上部分可以实现收缩
    CollapsingToolbarLayout collapsingToolbarLayout;

    //主页面的标题栏
    Toolbar toolbar;


    //滑动菜单
    private NavigationView navigationView;

    //滑动菜单里面的listview以及相关配置
    private List<MenuItem> navMenuList;
    private ListView nav_listView;
    private MenuItemAdapter menuItemAdapter;
    private int lableState = 0;
    private List<MenuItem> labelItemList;

    //悬浮按钮
    private FloatingActionButton floatingActionButton;

    //主页面的recyclerview以及配适器
    private RecyclerView recyclerView;
    private List<CountdownItem> countdownItemList = new ArrayList<>();
    private CountdownItemAdapter countdownItemAdapter;


    //顶部的滑动显示
    private ViewPager viewPager;
    private ArrayList<ViewPagerFragment> viewPagerFragmentList;
    private ViewPagerFragmentAdapter viewPagerFragmentAdapter;

    //尝试添加倒计时显示
    private List<CountDownTimer> countDownTimerList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ///////////////////////////////////////////////////////////////////////////////////////////////////
        //toolbar加载
        toolbar = findViewById(R.id.toolbar);
        //toolbar外面的布局
        collapsingToolbarLayout =  findViewById(R.id.collapsing_toolbar);
        //折叠之后的颜色
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.parseColor("#FFFFFF"));
        collapsingToolbarLayout.setExpandedTitleColor(Color.parseColor("#00000000"));

        //collapsing里面的图片
        //ImageView imageView = findViewById(R.id.main_imageview);
        //下面的内容
        //TextView textView = findViewById(R.id.main_text);
        //加载图片
        //imageView.setImageResource(R.drawable.kiki);
        //textView.setText(getSrting());
        //////////////////////////////////////////////////////////////////////////////////////////
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.mainActivity_drawerlayout);

        navigationView = findViewById(R.id.nav_view);
        //加入这个能正常显示颜色
        navigationView.setItemIconTintList(null);


        //接下来完成nav里面的菜单项的配置
        //初始化滑动菜单的菜单list
        initNavigationMeun();
        menuItemAdapter = new MenuItemAdapter(MainActivity.this,R.layout.menu_item,navMenuList);
        nav_listView = findViewById(R.id.nav_listvew);
        nav_listView.setAdapter(menuItemAdapter);
        //点击事件
        nav_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 1){
                    lableChange();
                }
                MenuItem menuItem = navMenuList.get(i);
                if (menuItem.getState() == MenuItem.ADDIBLE){
                    //Toast.makeText(MainActivity.this, "点击添加按钮", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawers();
                    buildAddLabelDialog();
                }else if(menuItem.getState() == MenuItem.CHANGECOLOR){
                    drawerLayout.closeDrawers();
                    buildChooseColorDialog();
                }
            }
        });
        //长按点击事件
        nav_listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                MenuItem menuItem = navMenuList.get(i);
                if (menuItem.getState() == MenuItem.DELETABLE){
                    navMenuList.remove(menuItem);
                    menuItemAdapter.setLocation(navMenuList.size());
                    menuItemAdapter.notifyDataSetChanged();
                }

                return true;
            }
        });

        //主菜单的toolbal构建
        drawerLayout = findViewById(R.id.mainActivity_drawerlayout);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        //加上了一张图片作为弹出菜单的按钮
        actionBar.setHomeAsUpIndicator(R.drawable.main_menu);

        //悬浮按钮
        floatingActionButton = findViewById(R.id.floatingactionbutton);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this, "kiki", Toast.LENGTH_SHORT).show();
                //测试添加一条记录
                /*{CountDownItem countDownItem = new CountDownItem("22小时","jiejie","2019年12月13日","yes",R.drawable.jiejie);
                countDownItemList.add(countDownItem);
                countDownItemAdapter.notifyDataSetChanged();
                int size = countDownItemList.size();
                viewPagerFragmentList.add(new ViewPagerFragment(countDownItemList.indexOf(countDownItem),
                        size,
                        countDownItem.getTitle(),
                        countDownItem.getTime(),
                        countDownItem.getCountdown(),
                        countDownItem.getImageId()));
                for (ViewPagerFragment viewPagerFragment :viewPagerFragmentList) {
                    viewPagerFragment.setIdAndLength(viewPagerFragmentList.indexOf(viewPagerFragment),size);
                }
                viewPagerFragmentAdapter.notifyDataSetChanged();}*/

                //测试减一条记录
                /*{
                    countDownItemList.remove(1);
                    countDownItemAdapter.notifyDataSetChanged();
                    viewPagerFragmentList.remove(1);
                    int size = countDownItemList.size();
                    for (ViewPagerFragment viewPagerFragment :viewPagerFragmentList) {
                        viewPagerFragment.setIdAndLength(viewPagerFragmentList.indexOf(viewPagerFragment),size);
                    }
                    viewPagerFragmentAdapter.notifyDataSetChanged();
                }*/
                //Intent intent = new Intent(MainActivity.this, AddCountdownItemActivity.class);
                //startActivity(intent);

                //先测试一下颜色选择器可不可以用
                buildChooseColorDialog();
            }
        });

        //首页配置
        recyclerView = findViewById(R.id.main_recyclerview);
        initCountdownItemList();
        countdownItemAdapter = new CountdownItemAdapter(countdownItemList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(countdownItemAdapter);



        //实现顶部viewpage和recyclerView的联动
        viewPager = findViewById(R.id.viewpager);
        viewPagerFragmentList = new ArrayList<>();
        initViewPageFragmentList();
        viewPagerFragmentAdapter = new ViewPagerFragmentAdapter(getSupportFragmentManager());
        viewPagerFragmentAdapter.setDatas(viewPagerFragmentList);
        viewPager.setAdapter(viewPagerFragmentAdapter);

        initCountdownTime();

    }

    //点击
    @Override
    public boolean onOptionsItemSelected(@NonNull android.view.MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home://弹出滑动菜单
                drawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }

    //下面这个函数是侧滑菜单的菜单项显示做准备的
    //这个是 完成菜单的初始化
    public void initNavigationMeun(){
        navMenuList = new ArrayList<>();
        MenuItem menuItem = new MenuItem("计时",R.drawable.nav_menu_timekeeping);
        navMenuList.add(menuItem);
        menuItem = new MenuItem("标签",R.drawable.nav_menu_label,R.drawable.nav_menu_expand_more);
        navMenuList.add(menuItem);
        menuItem = new MenuItem("小部件",R.drawable.nav_menu_extension);
        navMenuList.add(menuItem);
        menuItem = new MenuItem("主题色",R.drawable.nav_menu_color);
        menuItem.setState(MenuItem.CHANGECOLOR);
        navMenuList.add(menuItem);
        menuItem = new MenuItem("高级版",R.drawable.nav_menu_star);
        navMenuList.add(menuItem);
        menuItem = new MenuItem("设置",R.drawable.nav_menu_settings);
        navMenuList.add(menuItem);
        menuItem = new MenuItem("关于",R.drawable.nav_menu_about);
        navMenuList.add(menuItem);
        menuItem = new MenuItem("帮助与反馈",R.drawable.nav_menu_help_feedback);
        navMenuList.add(menuItem);








    }

    //这个是完成菜单伸缩的添加
    public void lableChange(){
        if(lableState == 0){
            labelItemList= new ArrayList<>();
            MenuItem menuItem = new MenuItem("生日",R.drawable.nav_menu_label_add);
            menuItem.setState(MenuItem.DELETABLE);
            labelItemList.add(menuItem);


            menuItem = new MenuItem("学习",R.drawable.nav_menu_label_add);
            menuItem.setState(MenuItem.DELETABLE);
            labelItemList.add(menuItem);


            menuItem = new MenuItem("工作",R.drawable.nav_menu_label_add);
            menuItem.setState(MenuItem.DELETABLE);
            labelItemList.add(menuItem);


            menuItem = new MenuItem("节假日",R.drawable.nav_menu_label_add);
            menuItem.setState(MenuItem.DELETABLE);
            labelItemList.add(menuItem);

            menuItem = new MenuItem("长按删除标签",R.drawable.nav_menu_label_add);
            menuItem.setState(MenuItem.DELETABLE);
            labelItemList.add(menuItem);

            menuItem = new MenuItem("添加标签",R.drawable.nav_menu_label_add);
            menuItem.setState(MenuItem.ADDIBLE);//点击这个按钮可以添加标签
            labelItemList.add(menuItem);

            for(int i=0;i<labelItemList.size();i++){
                navMenuList.add(i+2,labelItemList.get(i));
            }

        } else{
            for(int i=0;i<labelItemList.size();i++){
                navMenuList.remove(2);
            }

        }
        menuItemAdapter.setChange(lableState);
        menuItemAdapter.setLocation(navMenuList.size());
        menuItemAdapter.notifyDataSetChanged();
        lableState = (lableState+1)%2;
    }



    private void initCountdownItemList() {
        countdownItemList.add(new CountdownItem("9天","wawa","2019年11月12日","yes",R.drawable.wawa));
        countdownItemList.add(new CountdownItem("1分钟","coco","2019年11月30日","yes",R.drawable.coco));
        countdownItemList.add(new CountdownItem("22小时","titi","2019年12月12日","yes",R.drawable.titi));

    }


    private void initViewPageFragmentList(){
        viewPagerFragmentList = new ArrayList<>();
        int size = countdownItemList.size();
        for (CountdownItem countdownItem :countdownItemList){
            viewPagerFragmentList.add(new ViewPagerFragment(countdownItemList.indexOf(countdownItem),
                    size, countdownItem.getTitle(),
                    countdownItem.getTime(),
                    countdownItem.getCountdown(),
                    countdownItem.getImageId()));
        }



    }

    //下面是自定义的弹出菜单的编辑标签的dialog的构造函数
    private void buildAddLabelDialog(){
        View view1 = getLayoutInflater().inflate(R.layout.add_menu_itme_dialog, null);
        final MyDialog myDialog = new MyDialog(MainActivity.this, 0, 0, view1);
        myDialog.setCancelable(true);
        myDialog.show();
        //设置大小
        final WindowManager.LayoutParams params = myDialog.getWindow().getAttributes();
        params.width = 1000;
        params.height = 600;
        myDialog.getWindow().setAttributes(params);
        //注册点击事件
        myDialog.getWindow().findViewById(R.id.add_label_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().findViewById(R.id.add_label_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = myDialog.getWindow().findViewById(R.id.add_label_edittext);
                String label = "";
                label = editText.getText().toString();
                if(!label.equals("")){
                    Toast.makeText(MainActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                    myDialog.dismiss();
                }else {
                    Toast.makeText(MainActivity.this, "请输入标签名称", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    //现在构建一个选择颜色的对话框
    private void buildChooseColorDialog(){

        //先保存原有的颜色
        final ColorStateList colorStateList = floatingActionButton.getBackgroundTintList();
        final Drawable drawable = collapsingToolbarLayout.getContentScrim();

        View view1 = getLayoutInflater().inflate(R.layout.choose_color_dialog, null);
        final MyDialog myDialog = new MyDialog(MainActivity.this, 0, 0, view1);
        myDialog.setCancelable(true);
        myDialog.show();
        //设置大小
        final WindowManager.LayoutParams params = myDialog.getWindow().getAttributes();
        params.width = 1000;
        params.height = 600;
        myDialog.getWindow().setAttributes(params);
        //设置一下颜色的样式
        ColorSeekBar colorSeekBar = myDialog.getWindow().findViewById(R.id.colorSlider);
        colorSeekBar.setMaxPosition(100);
        colorSeekBar.setColorSeeds(R.array.material_colors); // material_colors is defalut included in res/color,just use it.
        colorSeekBar.setColorBarPosition(10); //0 - maxValue
        //colorSeekBar.setAlphaBarPosition(10); //0 - 255
        colorSeekBar.setShowAlphaBar(false);
        colorSeekBar.setBarHeight(5); //5dpi
        colorSeekBar.setThumbHeight(30); //30dpi
        colorSeekBar.setBarMargin(10);
        colorSeekBar.setOnColorChangeListener(new ColorSeekBar.OnColorChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onColorChangeListener(int colorBarPosition, int alphaBarPosition, int color) {
                //textView.setTextColor(color);
                //colorSeekBar.getAlphaValue();

                collapsingToolbarLayout.setContentScrimColor(color);
                floatingActionButton.setBackgroundTintMode(PorterDuff.Mode.SRC_ATOP);
                floatingActionButton.setBackgroundTintList(createColorStateList(color,color,color,color));

            }
        });

        //设置取消和确定的点击事件
        { myDialog.getWindow().findViewById(R.id.choose_color_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                collapsingToolbarLayout.setContentScrim(drawable);
                floatingActionButton.setBackgroundTintList(colorStateList);
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().findViewById(R.id.choose_color_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myDialog.dismiss();
            }
        });}
    }

    //为悬浮按钮设置颜色
    private ColorStateList createColorStateList(int normal, int pressed, int focused, int unable) {
        int[] colors = new int[] { pressed, focused, normal, focused, unable, normal };
        int[][] states = new int[6][];
        states[0] = new int[] { android.R.attr.state_pressed, android.R.attr.state_enabled };
        states[1] = new int[] { android.R.attr.state_enabled, android.R.attr.state_focused };
        states[2] = new int[] { android.R.attr.state_enabled };
        states[3] = new int[] { android.R.attr.state_focused };
        states[4] = new int[] { android.R.attr.state_window_focused };
        states[5] = new int[] {};
        ColorStateList colorList = new ColorStateList(states, colors);
        return colorList;
    }

    public void initCountdownTime(){
        for (final CountdownItem countdownItem:countdownItemList) {
            CountDownTimer countDownTimer = new CountDownTimer((long) (10 * 1000), 1000) {
                @Override
                public void onTick(long l) {
                    if (!MainActivity.this.isFinishing()) {
                        int remainTime = (int) (l / 1000L);
                        //Log.e("zpan","======remainTime=====" + remainTime);
                        countdownItem.setCountdown("ki"+remainTime+"秒");
                        countdownItemAdapter.notifyDataSetChanged();

                    }
                }

                @Override
                public void onFinish() {

                }
            };
            countDownTimerList.add(countDownTimer);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        for (CountDownTimer countDownTimer :countDownTimerList) {
            if(countDownTimer != null){
                countDownTimer.cancel();
                countDownTimer = null;
            }
        }

    }
}
