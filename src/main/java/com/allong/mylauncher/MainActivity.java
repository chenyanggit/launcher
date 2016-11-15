package com.allong.mylauncher;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangjian on 2016/10/21 11:55.
 */
public class MainActivity extends Activity {
    private GridView list;
    private List<AppDetail> apps;
    private PackageManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.view_main);
        initView();

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent = new Intent();
//                //包名 和 启动界面全称
//                intent.setComponent(new ComponentName("zmsoft.rest.phone", "zmsoft.rest.phone.ui.AppSplash"));
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
//            }
//        }, 100L);


    }

    private void initView() {
        list = (GridView) this.findViewById(R.id.gridview);
        loadApps();
        loadGridView();
        addClickListener();
    }

    private void loadApps() {

        manager = getPackageManager();

        apps = new ArrayList<>();

        Intent i = new Intent(Intent.ACTION_MAIN, null);

        i.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> availableActivities = manager.queryIntentActivities(i, 0);

        for (ResolveInfo ri : availableActivities) {
            if(ri.activityInfo.packageName.contains("zmsoft")){

                AppDetail app = new AppDetail();

                app.setLabel(ri.loadLabel(manager));

                app.setName(ri.activityInfo.packageName);

                app.setIcon(ri.activityInfo.loadIcon(manager));

                apps.add(app);
            }

        }

    }

    private void loadGridView() {
        ArrayAdapter<AppDetail> adapter = new ArrayAdapter<AppDetail>(this,
                R.layout.list_item,
                apps) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                if (convertView == null) {

                    convertView = getLayoutInflater().inflate(R.layout.list_item, null);

                }
                ImageView appIcon = (ImageView) convertView.findViewById(R.id.item_app_icon);

                appIcon.setImageDrawable(apps.get(position).getIcon());

                TextView appLabel = (TextView) convertView.findViewById(R.id.item_app_label);

                appLabel.setText(apps.get(position).getLabel());

                TextView appName = (TextView) convertView.findViewById(R.id.item_app_name);

                appName.setText(apps.get(position).getName());
                return convertView;
            }
        };
        list.setAdapter(adapter);
    }

    private void addClickListener() {

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int pos,
                                    long id) {
                Intent i = manager.getLaunchIntentForPackage(apps.get(pos).name.toString());
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                //什么也不做
            }
        }
        return true;
    }
}
