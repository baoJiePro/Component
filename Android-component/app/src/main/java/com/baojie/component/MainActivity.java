package com.baojie.component;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.baojie.component.bean.Apple;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerData();

        reflectData();

        reflectData2();
    }

    private void reflectData2() {
        try {
            Class<?> aClass = Class.forName("com.baojie.component.bean.Apple");
            Constructor<?> constructor = aClass.getConstructor(int.class);
            Apple o = (Apple) constructor.newInstance(20);

            Field price = aClass.getDeclaredField("price");
            price.setAccessible(true);
            price.set(o, 25);

            String priv = Modifier.toString(price.getModifiers());
            System.out.println("priv:" + priv);

            Method getPrice = aClass.getMethod("getPrice");
            int invoke = (int) getPrice.invoke(o);
            System.out.println("baojie" + invoke);

            Field[] fields = aClass.getFields();
            for(Field field : fields){
                System.out.println(field.getName());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private void reflectData() {
        Apple apple = new Apple();
        apple.setPrice(4);

        try {
            Class cls = Class.forName("com.baojie.component.bean.Apple");
            Method method = cls.getMethod("setPrice", int.class);
            Constructor constructor = cls.getConstructor();
            Object o = constructor.newInstance();
            method.invoke(o, 14);

            Method getPrice = cls.getMethod("getPrice");
            System.out.println("apple:" + getPrice.invoke(o));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    private void registerData() {
        LocalBroadcastManager instance = LocalBroadcastManager.getInstance(this);
        instance.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

            }
        }, new IntentFilter("LOCAL_ACTION"));
    }
}
