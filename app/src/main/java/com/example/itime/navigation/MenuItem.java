package com.example.itime.navigation;

public class MenuItem {
    private String name;
    private int icon;
    private int secondIcon;
    private int view;

    //定义几个菜单项的状态
    public static final int DELETABLE = 0;
    public static final int UNDELETABLE = 1;
    public static final int ADDIBLE = 2;
    public static final int CHANGECOLOR = 3;

    //菜单项的状态
    private int state = UNDELETABLE;//默认不可删除


    public MenuItem(String name, int icon, int secondIcon){
        this.name = name;
        this.icon = icon;
        this.secondIcon = secondIcon;
    }
    public MenuItem(String name, int icon){
        this.name = name;
        this.icon = icon;

    }

    public String getName() {
        return name;
    }

    public int getIcon() {
        return icon;
    }

    public int getSecondIcon() {
        return secondIcon;
    }

    public int getView() {
        return view;
    }

    public void setState(int state) {

        this.state = state;
    }

    public int getState() {
        return state;
    }
}
