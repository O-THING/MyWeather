package com.example.androidlearn4_2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.androidlearn4_2.db.City;
import com.example.androidlearn4_2.db.County;
import com.example.androidlearn4_2.db.Province;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;
import org.litepal.crud.LitePalSupport;

import java.util.ArrayList;
import java.util.List;

// 用于遍历省市县数据的碎片
public class ChooseAreaFragment extends Fragment {
    // 获取控件
    private TextView titletext;
    private Button backbutton;
    private ListView listview;

    // 在这里，ArrayAdapter用于将一个字符串列表（dataList）与ListView（listview）连接起来
    /*ArrayAdapter的构造函数接受三个参数：
        Context context：上下文对象，用于访问资源和主题等。
        int resource：布局资源ID，指定列表项的布局。这里使用的是android.R.layout.simple_list_item_1，表示每个列表项将显示为一个文本视图。
        List<T> objects：数据集合，这里的T是泛型参数，表示数据的类型。在这个例子中，T是String，即数据集合中的元素都是字符串。
    */
    private ArrayAdapter<String> adapter;  // 这个适配器用于处理字符串类型的数据
    private List<String> dataList = new ArrayList<String>();  // 声明了一个字符串类型的列表dataList，并初始化为一个ArrayList对象。
    // 这个列表将用来存储需要显示在ListView中的数据
    /*
    创建和返回Fragment的视图。这个方法有三个参数：
        LayoutInflater inflater：用于将XML布局文件实例化为View对象。
        ViewGroup container：Fragment的容器，通常是一个Activity中的布局。
        Bundle savedInstanceState：一个Bundle对象，用于保存和恢复Fragment的状态。
     */

    private int currentLevel;  // 当前选中的级别，根据这个和下面三个常量比较确定要显示的是省/市/县数据
    private static final int LEVEL_PROVINCE = 0;
    private static final int LEVEL_CITY = 1;
    private static final int LEVEL_COUNTY = 2;

    // 数据列表（？），用于更新数据库（？）
    private List<Province> provinceList;
    private List<City> cityList;
    private List<County> countyList;

    // ListView被选中的Item
    private Province selectedProvince;  // 选中的省
    private City selectedCity;
    private County selectedCounty;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.choose_area,container,
                false);
        /* inflate方法是用于将布局文件（通常是XML格式）转换为一个视图（View）对象
           inflate方法：主要是用于加载整个布局文件，创建一个视图层次结构。它将XML布局文件中的所有组件都转换为Java对象，并且可以指定父视图，用于构建复杂的视图结构。
           比如在一个自定义的ViewGroup中，你可以通过inflate方法将子布局文件加载进来，作为这个ViewGroup的子视图。
           返回的是一个View对象，这个对象代表了整个布局文件所对应的视图层次结构。
        */
        /* findViewById方法：侧重于在已经加载的布局中查找具体的组件。它是在布局加载完成后，用于获取布局中的某个特定组件，以便进行事件绑定、修改属性等操作。
           setContentView方法和findViewById方法通常是配合使用，在调用findViewById之前，必须先通过setContentView加载布局文件
           因为findViewById是在已经加载的布局中进行查找操作的。
           返回的是一个具体的视图组件对象
        * */
        /* 使用LayoutInflater的inflate方法来加载名为choose_area的布局文件，并将其转换为View对象
           R.layout.choose_area是资源文件中的一个引用，指向XML布局文件
           container是这个View将要放置的容器
           false表示不添加到container中，因为我们会在下面的代码中手动添加
         */
        TextView titletext = (TextView) view.findViewById(R.id.title_text);
        Button backbutton = (Button) view.findViewById(R.id.back_button);
        ListView listview = (ListView) view.findViewById(R.id.list_view);
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,
                dataList);  // android.R.layout.simple_list_item_1是 Android 系统提供的内置布局资源之一。
        // 它是一个简单的列表项布局，通常用于显示单个文本项。
        listview.setAdapter(adapter);  // 将这个适配器设置给ListView

        return view;  // 返回Fragment的视图
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // 如果当前级别为省
                if (currentLevel == LEVEL_PROVINCE) {
                    selectedProvince = provinceList.get(position);
                    queryCities();  // 查询选中省的市列表
                } else if (currentLevel == LEVEL_CITY) {  // 如果当前级别为市
                    selectedCity = cityList.get(position);
                    queryCounties();
                }
            }
        });
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentLevel==LEVEL_COUNTY){
                    queryCities();
                } else if (currentLevel==LEVEL_CITY) {
                    queryProvinces();
                }
            }
        });
        queryProvinces();  // 默认显示省列表
    }

    /*
    查询所有的省，优先从数据库查询，没有则去服务器查询
    */
    private void queryProvinces(){
        titletext.setText("中国");
        backbutton.setVisibility(View.GONE);  // 确实根目录不能返回了，移除返回键
        provinceList= LitePal.findAll(Province.class);  // 查询Province表所有的数据
        if (provinceList.size()>0){  // 从数据库查询
            dataList.clear();  // 注意：先清空
            for (Province province:provinceList){
                dataList.add(province.getProvinceName());  // 只显示名字（？）
            }
            adapter.notifyDataSetChanged();  // 通知与该适配器绑定的视图组件（如ListView、Spinner等），适配器的数据集已经发生了变化，需要重新加载数据并刷新视图
            // 一般应用场景：更新ListView列表数据后、下拉刷新、动态更改数据后。

            listview.setSelection(0);  // 设置ListView的选中项位置。参数position是一个整数，表示要选中的项的索引位置，索引从0开始
            // 当调用setSelection(int position)方法时，ListView会滚动到指定位置的项，使其成为当前可见的选中项。
            // 如果指定位置的项已经在可见范围内，ListView不会进行滚动操作，但该项会被设置为选中状态。
            // 注意：setSelection方法只是改变了ListView的选中项位置，并不会触发ListView的选中事件（如OnItemClickListener）。
            // 如果需要触发选中事件，可以使用performItemClick方法。

            currentLevel=LEVEL_PROVINCE;
        } else {  // 从服务器查询
            String address="http://guolin.tech/api/china";
            queryFromServer(address,"province");
        }
    }

    /*
    查询省中所有的市，优先从数据库查询，没有则去服务器查询
    */
    private void queryCities(){

    }

    /*
    查询市中所有的县，优先从数据库查询，没有则去服务器查询
    */
    private void queryCounties(){

    }

    /*
    根据传入的地址和类型从服务器上查询省市县数据
     */
    private void queryFromServer(String address,final String type){

    }
}


