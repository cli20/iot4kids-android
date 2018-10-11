package com.adalaachref.iot4kids;
import android.content.Context;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Spinner extends LinearLayout{

    public Spinner(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        View.inflate(context, R.layout.spinner, this);
        WebView web = (WebView) findViewById(R.id.web);
        web.loadUrl("file:///android_asset/booting.gif");
    }
    public void setText(String text){
        TextView t = (TextView) findViewById(R.id.txtloading);
        t.setText(text);
    }
    public void refresh(){
        WebView web = (WebView) findViewById(R.id.web);
        web.loadUrl("file:///android_asset/booting.gif");
    }
}