package com.macbitsgoa.icef.fragments.base;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.macbitsgoa.icef.BaseActivityCallback;
import com.macbitsgoa.icef.R;


public abstract class BaseActivity extends AppCompatActivity implements BaseActivityCallback {

    protected Toolbar toolbar;
    protected Snackbar snack;
    private ProgressDialog progress;
    private networkStatereceiver receiver;
    private IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);


    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (receiver == null)
            receiver = new networkStatereceiver();
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            int colorPrimary = ContextCompat.getColor(this, R.color.colorPrimary);
            setColour(colorPrimary);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        isNetworkAvailable(getApplicationContext());
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        receiver = new networkStatereceiver();
        registerReceiver(receiver, filter);
    }

    public void setColour(int colour) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(colour);
            getWindow().setNavigationBarColor(colour);

        }
    }

    public void setToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
        if (toolbar != null) {
            toolbar.setNavigationOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            onBackPressed();
                        }
                    });
        }
    }

    public void hideToolbar() {
        if (getToolbar() != null)
            getToolbar().setVisibility(View.GONE);
    }

    protected void showBackButton() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Displays a toast in current activity. In this method the duration
     * supplied is Short by default. If you want to specify duration
     * use {@link BaseActivity#showToast(String, int)} method.
     *
     * @param message Message that the toast must show.
     */
    public void showToast(String message) {
        showToast(message, Toast.LENGTH_SHORT);
    }

    /**
     * Displays a toast in current activity. The duration can of two types:
     * <ul>
     * <li>SHORT</li>
     * <li>LONG</li>
     * </ul>
     *
     * @param message   Message that the toast must show.
     * @param toastType Duration for which the toast must be visible.
     */
    public void showToast(String message, int toastType) {
        Toast.makeText(BaseActivity.this, message, toastType).show();
    }

    public Snackbar showSnack(String message, int length) {
        try {
            return showSnack(message, length, null);
        } catch (Exception e) {
            return showSnack(message, length, getWindow().getDecorView());
        }
    }

    @SuppressLint("NewApi")
    public Snackbar showSnack(String message, int length, View view) {
        try {
            snack = Snackbar.make(view, message, length);
            TextView snackBarText = snack.getView().findViewById(R.id.snackbar_text);
            snackBarText.setTextColor(Color.WHITE);
            snackBarText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            snack.getView().setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.snackBar));

            CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams)
                    snack.getView().getLayoutParams();
            snack.getView().setLayoutParams(params);

            snack.show();
            return snack;
        } catch (Exception e) {
            Log.d("Snack Error", e.getMessage());
            return null;
        }
    }

    public Snackbar showSnack(String message) {
        return showSnack(message, Snackbar.LENGTH_LONG);
    }

    public Snackbar getSnack() {
        return snack;
    }

    public void showProgressDialog() {
        showProgressDialog("working", this);
    }

    public void showProgressDialog(String message) {
        showProgressDialog(message, this);
    }

    @Override
    public void showProgressDialog(String message, Context ctx) {
        if (progress == null) {
            progress = new ProgressDialog(ctx, ProgressDialog.STYLE_SPINNER);
            progress.setCancelable(false);
        }
        if (isNetworkAvailable(getApplicationContext())) {
            try {
                progress.setMessage(message);
                progress.show();
            } catch (Exception ignored) {
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(receiver);
        hideProgressDialog();
    }

    @Override
    public void hideProgressDialog() {
        if (progress != null && progress.isShowing()) {
            progress.dismiss();
            progress = null;
        }
    }

    public void setActionBarTitle(String title) {
        if (getSupportActionBar() != null) {
            setTitle(title);
        }
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    public void setToolbarTitle(String title) {
        setActionBarTitle(title);
    }

    public boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        if (!(connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected())) {
            showSnack("Internet  unavailable", Snackbar.LENGTH_INDEFINITE);
            hideProgressDialog();
            return false;
        } else {
            if (snack != null && ((TextView) snack.getView().findViewById(R.id.snackbar_text)).getText().equals("Internet  unavailable"))
                snack.dismiss();
            return true;
        }

    }

    public void makeActivityFullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected void onDestroy() {
        try {
            unregisterReceiver(receiver);
        } catch (Exception ignored) {
        }
        super.onDestroy();
    }

    class networkStatereceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            isNetworkAvailable(BaseActivity.this);
        }
    }
}